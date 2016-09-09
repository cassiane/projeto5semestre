/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.persistencia.RecuperarContaDAO;
import java.util.Properties;

import javax.mail.*;

import javax.mail.internet.*;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo.lima
 */
@Entity
public class RecuperarConta implements Serializable {
    @Id
    @GeneratedValue
    private int id;    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataSolicitacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataUtilizacao;
    private String hashRecuperacaoSenha;
    private boolean inutilizado;
    
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
     * @return the inutilizado
     */
    public boolean isInutilizado() {
        return inutilizado;
    }

    /**
     * @param inutilizado the inutilizado to set
     */
    public void setInutilizado(boolean inutilizado) {
        this.inutilizado = inutilizado;
    }    
    
    /**
     * Persiste um objeto RecuperarConta no BD
     */
    public void salvar() {
        RecuperarContaDAO recuperarDAO = new RecuperarContaDAO();
        recuperarDAO.salvar(this);
    }
    
    /**
     * Envia um email com o link para redefinição de senha
     * @throws MessagingException Caso ocorra algum problema no envio do email
     */
    public void EnviarEmailRecuperacao() throws MessagingException {       
        Properties props = new Properties();
        props.put("mail.smtp.user", "witcapp@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("witcapp@gmail.com", "cmmvwitc");
                }
          });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("witcapp@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(this.usuario.getEmail()));
        message.setSubject("Redefinição de senha do WitC");
        String link = "http://localhost:8084/WritingInTheClouds/faces/redefinirSenha.xhtml?usuario=" + this.usuario.getEmail() + "&hash=" + this.hashRecuperacaoSenha;
        message.setText("Prezado ," + this.usuario.getNome()
                + "\n\n Estás recebendo este email porque solicitaste a recuperação da tua conta. "
                + "Por favor, clique no link abaixo e siga as instruções da página."
                + "\n\n" + link
                + "\n\nAtenciosamente,"
                + "\n\nEquipe WitC");
        
        try {
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", 587, "witcapp@gmail.com", "cmmvwitc");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException e) {
            
        }
                
        this.dataSolicitacao = Calendar.getInstance();
        
        RecuperarContaDAO redefinicaoDAO = new RecuperarContaDAO();
        redefinicaoDAO.salvar(this);
    }    
    
    /**
     * Verifica se o link da página de redefinição de senha é válido
     * @throws LinkRecuperacaoInvalidoException Caso o link seja inválido
     */
    public void verificarLink() throws LinkRecuperacaoInvalidoException {
        RecuperarContaDAO recuperarDAO = new RecuperarContaDAO();
        recuperarDAO.verificarLink(this);
    }
}
