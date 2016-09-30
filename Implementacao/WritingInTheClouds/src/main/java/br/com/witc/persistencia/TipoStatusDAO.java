/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.TipoStatus;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class TipoStatusDAO {
    Session sessao;

    public TipoStatusDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
    }
    
    public void salvar(TipoStatus status){
          try {
           sessao.saveOrUpdate(status);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
    public TipoStatus carregarTipoStatus (int id){
        
       TipoStatus s = (TipoStatus) sessao.createQuery("FROM TipoStatus WHERE id=:id ").setInteger("id",id)               
                .uniqueResult();
                
        if (s==null){
        return null; // lançar excessao
    }
        return s;
    }
}
