/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.LivroException;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoTexto;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author root
 */
public class LivroDAO {
    Session sessao;

    /**
     *
     */
    public LivroDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }
    
    /**
     *
     * @param livro
     */
    public void salvarLivro(Livro livro){
        
        try {
           sessao.saveOrUpdate(livro);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
    
    public List<Livro> listarLivrosPerfil(Perfil perfil){

        String consulta ="select Livro.* from Livro inner join HistoricoLivros on Livro.id=HistoricoLivros.idLivro inner join Perfil on Perfil.id = HistoricoLivros.idPerfil  where idPerfil=:id";
       List<Livro> lista ;
        lista= sessao.createSQLQuery(consulta).addEntity("livro",Livro.class).setInteger("id",perfil.getId()).list();
        return lista;
       

    }

    /**
     * Carrega um livro 
     * @param idLivro O id do livro a ser carregado
     * @return Livro Um objeto livro
     * @throws br.com.witc.excessao.LivroException Caso o livro não seja encontrado
     */
    public Livro carregarLivro(int idLivro) throws LivroException {        
        Livro tmpLivro = (Livro) sessao.load(Livro.class, idLivro);
        if (tmpLivro == null) {
            throw new LivroException("Nenhum livro encontrado com id " + idLivro);
        }
        return tmpLivro;
    }
    
    /**
     * Lista os livros publicados na Biblioteca Virtual
     * @param tp O Tipo Texto para pesquisa
     * @return Uma lista de livros publicados na Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso não haja livros publicados na Biblioteca
     */
    public List<Livro> listarLivrosPorTipoTexto(TipoTexto tp) throws BibliotecaVirtualVaziaException {
        List<Livro> tmpLstLivro = sessao.createCriteria(Livro.class)
                .add(Restrictions.like("disponivelBiblioteca", true))
                .add(Restrictions.eq("tipoTexto", tp))
                .addOrder(Order.asc("tipoTexto"))
                .list();
        
        if (tmpLstLivro.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        return tmpLstLivro;
    }
    
    /**
     * Lista os livros publicados na Biblioteca Virtual     
     * @param valorPesquisa O valor a ser pesquisado
     * @return Um objeto Map, com os livros encontrados
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso não haja livros publicados na Biblioteca
     */    
    public Map<String, List<Livro>> listarLivrosPublicadosPesquisa(String valorPesquisa) 
            throws BibliotecaVirtualVaziaException {        
        String sql = "SELECT DISTINCT l FROM Livro l "
                + "INNER JOIN l.tipoTexto AS tp "
                + "INNER JOIN l.historicoLivros AS hl "
                + "INNER JOIN hl.perfil AS p "
                + "INNER JOIN p.usuario AS u "
                + "WHERE ("
                + "u.nome LIKE '%" + valorPesquisa + "%' OR "
                + "l.classificacao LIKE '%" + valorPesquisa + "%' OR "
                + "tp.tipoTexto LIKE '%" + valorPesquisa + "%' OR "
                + "l.titulo LIKE '%" + valorPesquisa + "%') AND "
                + "l.disponivelBiblioteca = true "
                + "ORDER BY l.tipoTexto";
        
        String newKey;
        String oldKey = "";
        Map<String, List<Livro>> tmpMap = new HashMap();
        List<Livro> tmpLstLivro =  new ArrayList();
        for (Livro livro : (List<Livro>) sessao.createQuery(sql).list())  {
            newKey = livro.getTipoTexto().getTipoTexto();
            if (!newKey.equals(oldKey)) {
                tmpLstLivro = new ArrayList();
                oldKey = newKey;
            } 
            tmpLstLivro.add(livro);                    
            tmpMap.put(newKey, tmpLstLivro);                    
        }
                
        if (tmpMap.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Não foi possível localizar livros com os critérios informados.");
        }        
        return tmpMap;
    }    
    
    /**
     * Verifica se o livro está disponível para edição do usuário logado
     * @param idLivro O id do Livro
     * @param idPerfil O id do perfil do usuário logado
     * @return True, caso o livro esteja disponível para edição e false, caso contrário
     */
    public boolean estaDisponivelEdicaoUsuario(int idLivro, int idPerfil) {
        Livro livro = (Livro) sessao.createQuery("FROM Livro WHERE id=:idLivro")
                .setString("idLivro", String.valueOf(idLivro))                
                .uniqueResult();
        
        sessao.refresh(livro);
        return (livro.getBookLock() == idPerfil) || (livro.getBookLock() == 0);
    }
    public boolean estaDisponivelRevisaoUsuario(int idLivro, int idPerfil) {
        Livro livro = (Livro) sessao.createQuery("FROM Livro WHERE id=:idLivro")
                .setString("idLivro", String.valueOf(idLivro))                
                .uniqueResult();
        
        sessao.refresh(livro);
        return ((livro.getBookLock() == idPerfil) || (livro.getBookLock() == 0)) && livro.isDisponivelRevisao();
    }
    
    /**     
     * @param idPerfil O id do perfil do usuário
     * @return Uma lista de livros publicados pelo usuário
     */
    public List<Livro> listarLivrosPublicadosPerfil(int idPerfil) {
        List<Object[]> lstObjetos = sessao.createQuery("FROM Livro AS l "
                + "INNER JOIN l.historicoLivros AS hl "                
                + "INNER JOIN hl.perfil AS p "
                + "WHERE p.id=:idPerfil AND l.disponivelBiblioteca = true "
                + "ORDER BY dataConclusao ASC")                 
                .setString("idPerfil", String.valueOf(idPerfil))
                .list();
        
        List<Livro> lstLivro = new ArrayList();
        for (Object[] arrObj : lstObjetos) {
            for (Object obj : arrObj) {
                if (obj instanceof Livro) {
                    lstLivro.add((Livro)obj);
                }
            }
        }
        
        return lstLivro;
    }
    
    public Livro carregarHistoricoConvite(int livro) {
        return (Livro) sessao.createQuery("FROM Livro WHERE id=:liv")
                .setParameter("liv", livro)
                .uniqueResult();
    } 
    
     public List<Livro> listarLivrosRevisao() {
        List<Livro> tmpLstLivros = sessao.createCriteria(Livro.class)
                .add(Restrictions.eq("disponivelRevisao", true))
                .list();          
        return tmpLstLivros;
    }
}
