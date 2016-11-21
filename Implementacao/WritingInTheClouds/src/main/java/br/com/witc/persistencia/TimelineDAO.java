/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TimelineException;
import br.com.witc.modelo.Timeline;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author marcelo.lima
 */
public class TimelineDAO {

    Session sessao;

    public TimelineDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }

    /**
     * @param usuario O usuario da página visualizada no momento
     * @return Uma lista contendo as publicações dos Amigos
     * @throws br.com.witc.excessao.TimelineException Caso não haja publicação
     * dos amigos
     */
    public List<Timeline> listarPublicacoesAmigos(Usuario usuario) throws TimelineException {
        List<Timeline> lstTimeline = sessao.createCriteria(Timeline.class)
                .add(Restrictions.eq("usuario", usuario))
                .addOrder(Order.desc("dataPublicacao"))
                .list();

        if (lstTimeline.isEmpty()) {
            throw new TimelineException("Nenhuma publiação para mostrar");
        }

        return lstTimeline;
    }
    
    public void salvarPublicacao(Timeline tl) {
        sessao.save(tl);
    }
}
