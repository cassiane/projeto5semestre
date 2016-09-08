/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author cstads
 */
public class EmailUtils {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "writingclouds@gmail.com";
    private static final String PASSWORD = "dev2semestre";
    private static final String EMAILORIGEM = "writingclouds@gmail.com";

    public static Email conectaEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static String enviaEmail(Mensagem mensagem) throws EmailException {
        Email email = new SimpleEmail();
        email = conectaEmail();
        email.setSubject(mensagem.getTitulo());
        email.setMsg(mensagem.getMensagem());
        email.addTo(mensagem.getDestino());
        String resposta = email.send();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + mensagem.getDestino(), "Informação"));
        return "E-mail enviado com sucesso para: " + mensagem.getDestino();
    }
}
