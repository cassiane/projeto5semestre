/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
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
        String consulta ="select livro.id,livro.titulo from livro inner join historicolivros on livro.id=historicolivros.idLivro inner join perfil on perfil.id = historicolivros.idPerfil  where idPerfil=:id";
        return sessao.createSQLQuery(consulta).setInteger("id",perfil.getId()).list();

       
    }
    /**
     *
     */
    public void editarLivro(){
        //update livro no banco
    }
    
    /**
     *
     * @param id
     */
    public void carregarLivro(int id){
        //carrega livro no banco
        
    }
    
    
    
    
}
