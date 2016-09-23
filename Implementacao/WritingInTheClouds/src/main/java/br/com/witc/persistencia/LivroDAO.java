/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
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
    public void criarLivro(Livro livro){
        
        try {
           sessao.saveOrUpdate(livro);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
    
    public List<Livro> listarLivrosPerfil(Perfil perfil){

        String consulta ="select livro.* from livro inner join historicolivros on livro.id=historicolivros.idLivro inner join perfil on perfil.id = historicolivros.idPerfil  where idPerfil=:id";
       List<Livro> lista ;
        lista= sessao.createSQLQuery(consulta).addEntity("livro",Livro.class).setInteger("id",perfil.getId()).list();
        return lista;
       

    }
    /**
     *
     * @param livro
     */
    public void editarLivro(Livro livro){
        //update livro no banco
         try {
           sessao.saveOrUpdate(livro);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
    
    
    /**
     *
     * @param id
     * @return Livro
     */
    public Livro carregarLivro(int id){
        //carrega livro no banco
        return (Livro) sessao.load(Livro.class, id);
        
    }
    
    /**
     * Lista os livros publicados na Biblioteca Virtual
     * @return Uma lista de livros publicados na Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso não haja livros publicados na Biblioteca
     */
    public List<Livro> listarLivrosPublicados() throws BibliotecaVirtualVaziaException {
        List<Livro> tmpLstLivro = sessao.createCriteria(Livro.class)
                .add(Restrictions.like("disponivelBiblioteca", true))
                .addOrder(Order.asc("tipoTexto"))
                .list();
        
        if (tmpLstLivro.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        return tmpLstLivro;
    }
    
    /**
     * Lista os livros publicados na Biblioteca Virtual     
     * @param campoPesquisa O campo a ser pesquisado
     * @param valorPesquisa O valor a ser pesquisado
     * @return Uma lista de livros publicados na Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso não haja livros publicados na Biblioteca
     */
    @SuppressWarnings("unchecked")
    public List<Livro> listarLivrosPublicados(String campoPesquisa, String valorPesquisa) 
            throws BibliotecaVirtualVaziaException {
        String sql = "FROM Livro AS l ";
        switch (campoPesquisa) {
            case "autor":
                sql += "INNER JOIN l.tipoTexto AS tp "
                     + "INNER JOIN l.historicoLivros AS hl "
                     + "INNER JOIN hl.perfil AS p "
                     + "INNER JOIN p.usuario AS u "
                     + "WHERE u.nome LIKE ";
                break;
            case "classificacao":
                sql += "INNER JOIN l.tipoTexto tp "
                     + "WHERE l.classificacao LIKE ";
                break;
            case "tipoTexto":
                sql += "INNER JOIN l.tipoTexto tp  "
                     + "WHERE tp.tipoTexto LIKE ";
                break;
            case "titulo":
                sql += "INNER JOIN l.tipoTexto tp  "
                     + "WHERE l.titulo LIKE ";
        }
        sql += "'%" + valorPesquisa + "%' ORDER BY l.tipoTexto";
                        
        List<Livro> tmpLstLivro = new ArrayList();
        List<Object[]> lstResult = sessao.createQuery(sql).list();
        for (Object[] o : lstResult) {            
            for (Object o1 : o) {
                if (o1 instanceof Livro) {
                    tmpLstLivro.add((Livro) o1);
                    break;
                }
            }
        }
        
        
        if (tmpLstLivro.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Não foi possível localizar livros com os critérios informados.");
        }
        return tmpLstLivro;
    }    
    
    /**
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia
     */
    public Map<String,List<Livro>> getBibliotecaVirtual() throws BibliotecaVirtualVaziaException {
        List<Livro> lstLivros = listarLivrosPublicados();        
        return getMapBibliotecaVirtual(lstLivros);
    }
    
    /**
     * @param campoPesquisa O campo a ser pesquisado
     * @param valorPesquisa O valor a ser pesquisado
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia
     */
    public Map<String,List<Livro>> getBibliotecaVirtual(String campoPesquisa, String valorPesquisa) 
            throws BibliotecaVirtualVaziaException {
        List<Livro> lstLivros = listarLivrosPublicados(campoPesquisa, valorPesquisa);        
        return getMapBibliotecaVirtual(lstLivros);
    }
    
    /**
     * Monta um Map com base na lista de livros enviada
     * @param lstLivros A lista com os livros
     * @return Um objeto Map com base na lista de livros enviada     
     */
    private Map<String, List<Livro>> getMapBibliotecaVirtual(List<Livro> lstLivros) {
        Map<String,List<Livro>> tmpBiblioteca = new HashMap();
        List<Livro> lstLivrosTipoTexto = new ArrayList();
        String key = lstLivros.get(0).getTipoTexto().toString();            
        for (Livro livro : lstLivros) {            
            if (key.equals(livro.getTipoTexto().toString())) {
                lstLivrosTipoTexto.add(livro);
            } else {
                tmpBiblioteca.put(key, lstLivrosTipoTexto);

                key = livro.getTipoTexto().toString();
                lstLivrosTipoTexto = new ArrayList();
                lstLivrosTipoTexto.add(livro);                    
            }
        }
        tmpBiblioteca.put(key, lstLivrosTipoTexto);
        return tmpBiblioteca;
    }
    
    
}
