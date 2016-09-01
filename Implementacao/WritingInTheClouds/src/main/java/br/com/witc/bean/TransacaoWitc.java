/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.persistencia.HibernateUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.context.internal.ManagedSessionContext;

/**
 *
 * @author marcelo.lima
 */
public class TransacaoWitc {
    private Session sessaoHibernate;

    public void iniciar() {
        sessaoHibernate = HibernateUtil.getSessionFactory().openSession();
        sessaoHibernate.setFlushMode(FlushMode.MANUAL);
    }
    
    public void finalizar() {
        if (sessaoHibernate.isOpen()) {
            sessaoHibernate.flush();
            sessaoHibernate.close();            
        }
    }
    
    public void iniciarRequisicao() {
        ManagedSessionContext.bind(sessaoHibernate);
        if(!sessaoHibernate.getTransaction().isActive()) {
            sessaoHibernate.beginTransaction();        
        }
    }
    
    public void finalizarRequisicao() {        
        if(sessaoHibernate.isOpen() && sessaoHibernate.getTransaction().isActive()) {
            sessaoHibernate.flush();
            sessaoHibernate.getTransaction().commit();
        }
        ManagedSessionContext.unbind(HibernateUtil.getSessionFactory());
    }    
}
