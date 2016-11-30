/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Notificacoes;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author cassiane.santos
 */
public class NotificacoesDAO {
    Session sessao;
    
    public NotificacoesDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
    }
    
    /**
     * Lista de notificacoes do usuário
     * @param idUsuario id do usuário 
     * @return retorna a lista de objetos Notificacoes
     */
    public List<Notificacoes> listarNotificacoesUsuarios(int idUsuario) {          
        return sessao.createQuery("from Notificacoes where idUsuarioDestinatario = :idUsuario")                
                .setInteger("idUsuario", idUsuario)
                .list();
    }
    
    /**
     * Salva as notificacoes quando o usuário desafia ou termina o desafio
     * @param notificacao  
     */
    public void salvarNotificacaoUsuario(Notificacoes notificacao){
        sessao.saveOrUpdate(notificacao);
    }
    
    /**
     * Exclui a notificação do desafio após usuário ter concluído
     * @param idDesafiosUsuarios 
     */
    public void excluirNotificao(int idDesafiosUsuarios) {        
        Notificacoes not = carregarNotificacao(idDesafiosUsuarios);
        sessao.delete(not);
    }
    
    /**
     * Carrega uma notificacao a partir do id do desafio dos usuarios 
     * @param idDesafiosUsuarios 
     * @return  
     */
    public Notificacoes carregarNotificacao(int idDesafiosUsuarios){
        return (Notificacoes) sessao.createQuery("from Notificacoes where idDesafiosUsuarios = :idDesafiosUsuarios")
                .setInteger("idDesafiosUsuarios", idDesafiosUsuarios)
                .uniqueResult();
    }
}
