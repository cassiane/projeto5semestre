/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;
import br.com.witc.modelo.HistoricoLivro;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author root
 */
public class HistoricoDAO {
    Session sessao;

    public HistoricoDAO() {
         this.sessao = getSessionFactory().getCurrentSession();
    }
    
     public void criarHistorico(HistoricoLivro historicoLivro){
        
           try {
           sessao.saveOrUpdate(historicoLivro);
        } catch (ConstraintViolationException e) {
          
                sessao.clear();    
            } 
    }
    
}
