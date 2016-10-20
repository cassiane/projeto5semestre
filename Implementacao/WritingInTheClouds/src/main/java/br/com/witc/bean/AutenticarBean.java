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
import br.com.witc.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    private String email;
    private String senha;

    public AutenticarBean() {
        this.controlador = new ControladorAutenticacao();              
    }    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }               
    
    /**     
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.controlador.setUsuario(usuario);
    }
    
    /**     
     * @return O email do usuário logado no sistema
     */
    public String getNomeCompletoUsuario() {
        return this.controlador.getNomeCompletoUsuario();
    }
    
    /**
     * @return O status do usuário logado no sistema
     */
    public String getStatusUsuario() {
        return this.controlador.getStatusUsuario();
    }
    
    /**
     * Acessa o controle para setar o amigo
     * @param id Codigo do amigo
     */
    public void setAmigoUsuario(int id) {
        this.controlador.setAmigoUsuario(id);
    }
    
    /**
     * Acessa o controle para zerar o amigo
     */
    public void setAmigoUsuario() {
        this.controlador.setAmigoUsuario();
    }
    
    /**
     * Acessa o controle para verificar se é para mostrar o usuario ou o amigo
     * @return False se for amigo
     */
    public boolean isAmigo() {
        return this.controlador.getAmigoUsuario().getId() == 0;
    }

    /**
     * Retornando o id do usuário amigo
     * @return Codigo do amigo
     */
    public int getIdAmigoUsuario() {
        if (this.isAmigo()) {
            return 0;
        } else {
            return this.controlador.getAmigoUsuario().getId();
        }
    }
    
    /**
     * Acessa o controle para atualizar o status do usuario
     * @param status Codigo do status (Enum do banco)
     */
    public void atualizarStatusUsuario(int status) {
        this.controlador.atualizarStatusUsuario(status);
    }
    
    /**     
     * @return A quantidade de amigos do usuário logado no sistema
     */
    public String getNumeroAmigosUsuarioLogado() {
        int numAmigos = this.controlador.getNumeroAmigosUsuarioLogado();
        if (numAmigos == 1) {
            return "1 amigo";
        }
        return String.valueOf(numAmigos) + " amigos";
    }        
    
    /**
     * Unica forma que achei para buscar o usuario
     * @return Retorna o usuario logado
     */
    public Usuario usuarioLogado() {
        return this.controlador.getUsuario();
    }
    
    /**
     * Autentica um usuário no sistema
     * @return A página a ser visualizada pelo usuário após o login     
     */
    public String efetuarLogin() {        
        try {
            this.controlador.efetuarLogin(this.email, this.senha);
            this.controlador.retornarPerfilUsuarioLogado();          
            return "timeline";
        } catch(LoginInvalidoException e) {
            enviarMensagem(SEVERITY_ERROR, e.getMessage());            
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(SEVERITY_ERROR, "Problemas na geração do hash da senha!");     
        }
        return "index";
    }   
    
    /**
     * Verifica se o usuário logado é administrador 
     * @return 
     */
    public boolean verificarAdministrador(){
        try {
            return this.controlador.getTipoPerfil().toLowerCase().contains("admin");        
        } catch(NullPointerException ex) {
            // cadastro de usuario
            return false;
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
