/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoriasDesafios;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;

/**
 *
 * @author cassiane.santos
 */
public class HistoriasDesafiosDAO {
    Session sessao;

    public HistoriasDesafiosDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }
    /**
     * Salvar a historia do desafio
     * @param historiasDesafios 
     */
    public void salvarHistoriaDesafio(HistoriasDesafios historiasDesafios) {
        sessao.saveOrUpdate(historiasDesafios);
    }
    
}
