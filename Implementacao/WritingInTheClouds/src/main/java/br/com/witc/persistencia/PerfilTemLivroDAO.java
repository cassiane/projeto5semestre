/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.PerfilTemLivro;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class PerfilTemLivroDAO {
    Session sessao;

    public PerfilTemLivroDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }
    
    public void salvar(PerfilTemLivro perfilTemLivro){
            try {
           sessao.saveOrUpdate( perfilTemLivro);
        } catch (ConstraintViolationException e) {
          
                sessao.clear();    
            } 
        
    }   
    public void salvarPerfilLivro (Perfil perfil, Livro livro){
        PerfilTemLivro temp = new PerfilTemLivro();
        temp.setLivro(livro);
        temp.setPerfil(perfil);
        try{
        sessao.saveOrUpdate(temp);
        }catch(ConstraintViolationException e) {
          
                sessao.clear();    
            } 
            
        
    }
}
