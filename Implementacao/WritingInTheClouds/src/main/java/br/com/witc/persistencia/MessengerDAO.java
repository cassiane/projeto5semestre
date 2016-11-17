/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Messenger;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author vanderson
 */
public class MessengerDAO {
    private final Session sessao;

    public MessengerDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }
    
    public void enviarMsn(Messenger msn) {
        try {
            sessao.saveOrUpdate(msn);
        } catch (ConstraintViolationException e) {
            sessao.clear();
        }
    }
    
    public List<Messenger> listarMsn(int send, int receive) {
        try {
            List<Messenger> lismsn = sessao.createQuery("FROM Messenger WHERE (idSend = :send AND idReceive = :receive) OR (idSend = :receive AND idReceive = :send) ORDER BY dateSend")
                    .setInteger("send", send)
                    .setInteger("receive", receive)
                    .list();
            return lismsn;
        } catch (Exception e) {
            return null;
        }
    }
}
