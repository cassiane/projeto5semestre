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

/**
 *
 * @author cassiane.santos
 */
public class NotificacoesDAO {
    Session sessao;
    
    public NotificacoesDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
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
}
