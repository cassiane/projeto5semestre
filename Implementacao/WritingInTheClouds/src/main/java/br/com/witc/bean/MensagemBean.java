/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.EmailUtils;
import br.com.witc.modelo.Mensagem;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author cstads
 */
@ManagedBean
@SessionScoped
public class MensagemBean {

    private Mensagem mensagem = new Mensagem();

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public void enviaEmail() {
        try {
            EmailUtils.enviaEmail(mensagem);
        } catch (EmailException ex) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Occoreu um erro ao enviar a mensagem.", "Erro"));
            //Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpaCampos() {
        mensagem = new Mensagem();
    }
}
