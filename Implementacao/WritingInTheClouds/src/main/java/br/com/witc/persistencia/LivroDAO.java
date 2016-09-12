/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
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
       
        //salva livro no banco
        
        try {
           sessao.saveOrUpdate(livro);
        } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
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
    
    public List <Livro> carregarTodosLivrosUsuario(Usuario usuario){
         
  List<Livro> livrosUsuario = sessao.createSQLQuery("SELECT idLivro FROM perfil_tem_livro "
                + "WHERE idUsuario = :usuario ")
                .setParameter("usuario", usuario.getId()).list();
               
        return livrosUsuario;
        
    }
    
    
}
