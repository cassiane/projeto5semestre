/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoricoLivros;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class HistoricoLivroDAO {
    Session sessao;

    public HistoricoLivroDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }
    
     public void salvarHistorico(HistoricoLivros hist){
         
          try {
           sessao.saveOrUpdate(hist);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
     
    
}
