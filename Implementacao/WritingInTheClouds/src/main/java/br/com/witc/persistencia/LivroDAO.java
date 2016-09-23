/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
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

        String consulta ="select Livro.* from Livro inner join HistoricoLivros on Livro.id=HistoricoLivros.idLivro inner join Perfil on Perfil.id = HistoricoLivros.idPerfil  where idPerfil=:id";
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
    
    
    
    
}
