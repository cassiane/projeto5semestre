/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.TipoPerfil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author 10070187
 */
public class TipoPerfilBean {
    private final ControladorCadastro controlador;
    private final TipoPerfil tipoPerfil;

    public TipoPerfilBean(ControladorCadastro controlador, TipoPerfil tipoPerfil) {
        this.controlador = new ControladorCadastro();
        this.tipoPerfil = new TipoPerfil();
    }
    
    public String cadastrarTipoPerfil(){
        this.tipoPerfil.setAtivo(true);        
        try {
            this.controlador.cadastrarTipoPerfil(tipoPerfil);
            return "timelineAdmin"; 
        } catch (TipoPerfilException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR,"Não foi possível cadastrar este perfil - "+ex.getMessage());
        }
        return "index"; 
    }
    
    /**
     * Envia à viewer uma mensagem com o status da operação
     *
     * @param sev A severidade da mensagem
     * @param msg A mensagem a ser apresentada
     */
    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
}
