/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.DesafiosUsuarios;
import br.com.witc.modelo.HistoriasDesafios;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author cassiane.santos
 */
public class HistoriasDesafiosDAO {
    Session sessao;

    public HistoriasDesafiosDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
    }
    /**
     * Salvar a historia do desafio
     * @param historiasDesafios 
     */
    public void salvarHistoriaDesafio(HistoriasDesafios historiasDesafios) {
        sessao.saveOrUpdate(historiasDesafios);
    }
    
    /**
     * Carrega um objeto desafiosUsuarios
     * para retorna o texto e os atributos do desafio
     * @param desafiosUsuarios
     * @return 
     */
    public HistoriasDesafios carregarHistoriasDesafios(DesafiosUsuarios desafiosUsuarios) {
        return (HistoriasDesafios) sessao.createQuery("from HistoriasDesafios where idDesafiosUsuarios =:id")
                .setInteger("id", desafiosUsuarios.getId())
                .uniqueResult();
    }
    
    /**
     * Faz o load do historia desafios
     * @param idHistoriasDesafios
     * @return 
     */
    public HistoriasDesafios carregarHistoriasDesafiosPorId(int idHistoriasDesafios) {
        return (HistoriasDesafios) sessao.load(HistoriasDesafios.class, idHistoriasDesafios);
    }
    
}
