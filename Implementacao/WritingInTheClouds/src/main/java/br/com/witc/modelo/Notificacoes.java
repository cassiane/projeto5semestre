/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.NotificacoesDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author cassiane.santos
 */
@Entity
public class Notificacoes implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String texto;
    @ManyToOne
    @JoinColumn(name = "idUsuarioRemetente")
    private Usuario amigo;
    @ManyToOne
    @JoinColumn(name = "idUsuarioDestinatario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idDesafiosUsuarios")
    private DesafiosUsuarios desafio;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the amigo
     */
    public Usuario getAmigo() {
        return amigo;
    }

    /**
     * @param amigo the amigo to set
     */
    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return the desafio
     */
    public DesafiosUsuarios getDesafio() {
        return desafio;
    }

    /**
     * @param desafio the desafio to set
     */
    public void setDesafio(DesafiosUsuarios desafio) {
        this.desafio = desafio;
    }
    
    /**
     * Salva a notificacao do usuario 
     * @param notificacao 
     */
    public void salvarNotificacao(Notificacoes notificacao){
        NotificacoesDAO dao = new NotificacoesDAO();
        dao.salvarNotificacaoUsuario(notificacao);
    }
    
    /**
     * Lista as notificações do usuário logado
     * @param idUsuario
     * @return 
     */
    public List<Notificacoes> listarNotificacoes(int idUsuario){
        NotificacoesDAO dao = new NotificacoesDAO();        
        return dao.listarNotificacoesUsuarios(idUsuario);
    }
}
