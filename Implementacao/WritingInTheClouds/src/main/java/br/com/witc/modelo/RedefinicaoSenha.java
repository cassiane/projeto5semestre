/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.Properties;
import java.util.Date;

import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo.lima
 */
@Entity
public class RedefinicaoSenha implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataSolicitacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataUtilizacao;
    private String hashRecuperacaoSenha;
    
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
     * @return the dataSolicitacao
     */
    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao the dataSolicitacao to set
     */
    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * @return the dataUtilizacao
     */
    public Calendar getDataUtilizacao() {
        return dataUtilizacao;
    }

    /**
     * @param dataUtilizacao the dataUtilizacao to set
     */
    public void setDataUtilizacao(Calendar dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    /**
     * @return the hashRecuperacaoSenha
     */
    public String getHashRecuperacaoSenha() {
        return hashRecuperacaoSenha;
    }

    /**
     * @param hashRecuperacaoSenha the hashRecuperacaoSenha to set
     */
    public void setHashRecuperacaoSenha(String hashRecuperacaoSenha) {
        this.hashRecuperacaoSenha = hashRecuperacaoSenha;
    }
    
    /**
     * Envia um email com o link para redefinição de senha
     * @throws MessagingException Caso ocorra algum problema no envio do email
     */
    public void EnviarEmailRecuperacao() throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("witcapp@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(usuario.getEmail(), false));
        msg.setSubject("Recuperar senha WitC");
        String link = "localhost:8080/?usuario=" + this.usuario.getEmail() + "?hash=" + this.hashRecuperacaoSenha;
        msg.setText("Para criar uma nova senha clique no link " + link);
        msg.setHeader("X-Mailer", "WitC");
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", "witcapp@gmail.com", "cmmvwitc");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }    
}
