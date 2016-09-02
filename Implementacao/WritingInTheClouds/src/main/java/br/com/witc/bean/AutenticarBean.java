/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.LoginInvalidoException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.witc.modelo.ControladorAutenticacao;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author marcelo.lima
 */
@ManagedBean
@SessionScoped
public class AutenticarBean {
    private final ControladorAutenticacao controlador;

    public AutenticarBean() {
        this.controlador = new ControladorAutenticacao();              
    }    
    
    /**     
     * @return O nome do usuário logado no sistema
     */
    public String getNomeUsuario() {
        return this.controlador.getNomeUsuario();
    }
    
    /**
     * Autentica um usuário no sistema
     * @param login O login do usuário
     * @param senha A senha do usuário
     * @return A página a ser visualizada pelo usuário após o login     
     */
    public String efetuarLogin(String login, String senha) {        
        try {
            return this.controlador.efetuarLogin(login, senha);
        } catch(LoginInvalidoException e) {
            enviarMensagem(SEVERITY_ERROR, e.getMessage());
            return "index";            
        }
    }    
    
    /**
     * Realiza o logout do usuário
     * @return A próxima página a ser visualizada pelo usuário após o logout
     */
    public String efetuarLogoff() {
        getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }    
    
    /**
     * Envia à viewer uma mensagem com o status da operação
     * @param sev A severidade da mensagem
     * @param msg A mensagem a ser apresentada
     */
    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = getCurrentInstance();        
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }          
}
