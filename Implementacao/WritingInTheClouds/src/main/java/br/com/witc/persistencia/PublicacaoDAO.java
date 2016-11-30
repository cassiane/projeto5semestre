/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TimelineException;
import br.com.witc.modelo.Publicacao;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author marcelo.lima
 */
public class PublicacaoDAO {

    Session sessao;

    public PublicacaoDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
    }

    /**
     * @param usuario O usuario da página visualizada no momento
     * @return Uma lista contendo as publicações dos Amigos
     * @throws br.com.witc.excessao.TimelineException Caso não haja publicação
     * dos amigos
     */
    public List<Publicacao> listarPublicacoesAmigos(Usuario usuario) throws TimelineException {
        List<Publicacao> lstTimeline = sessao.createCriteria(Publicacao.class)
                .add(Restrictions.eq("usuario", usuario))
                .addOrder(Order.desc("dataPublicacao"))
                .list();

        if (lstTimeline.isEmpty()) {
            throw new TimelineException("Nenhuma publiação para mostrar");
        }

        return lstTimeline;
    }
    
    public void salvarPublicacao(Publicacao tl) {        
        sessao.saveOrUpdate(tl);        
    }
}
