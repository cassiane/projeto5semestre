/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
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
