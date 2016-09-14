/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.PerfilTemLivro;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author root
 */
public class LivroDAO {
    Session sessao;
    PerfilTemLivro perfilTemLivro;
   
   

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
        perfilTemLivro = new PerfilTemLivro();
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
    //busca todos os livros do usuario passando o seu perfuil padrao
    public List <Livro> carregarTodosLivrosUsuario(Perfil perfilPadrao){
         Criteria crit = sessao.createCriteria(Livro.class);
         crit.add(Restrictions.eq("idPerfil",perfilPadrao));
           List<Livro> list = (List<Livro>) crit.list();
           if(!list.isEmpty()){
               return list;
           }
           return null;
    }
    
    
}
