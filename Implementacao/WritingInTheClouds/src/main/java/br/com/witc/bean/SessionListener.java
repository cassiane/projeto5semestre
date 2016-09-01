/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author marcelo.lima
 */
public class SessionListener implements HttpSessionListener {
    private final TransacaoWitc transacao = new TransacaoWitc();
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        transacao.iniciar();
        se.getSession().setAttribute("transacaoCorrente", transacao);
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {        
        transacao.finalizar();
    }    
}
