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
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
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
     */
    public List<Livro> listarLivrosPublicados() {
        return sessao.createCriteria(Livro.class)
                .add(Restrictions.like("disponivelBiblioteca", true))
                .addOrder(Order.asc("tipoTexto"))
                .list();
    }
    
    /**
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia
     */
    public Map<String,List<Livro>> getBibliotecaVirtual() throws BibliotecaVirtualVaziaException {
        Map<String,List<Livro>> tmpBiblioteca = new HashMap();
        List<Livro> lstLivros = listarLivrosPublicados();        
        
        if (!lstLivros.isEmpty()) {            
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
        } else {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        
        return tmpBiblioteca;
    }
    
    
}
