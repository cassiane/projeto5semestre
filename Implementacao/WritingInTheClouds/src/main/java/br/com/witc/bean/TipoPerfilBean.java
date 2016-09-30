/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.persistencia.TipoPerfilDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author 10070187
 */
@ManagedBean
@SessionScoped
public class TipoPerfilBean {
    private final ControladorCadastro controlador;
    private TipoPerfil tipoPerfil;
    private TipoPerfilDAO dao; 

    public TipoPerfilBean(){
        this.controlador = new ControladorCadastro();
        this.tipoPerfil = new TipoPerfil();
    }
    /**
     * Cadastra um novo perfil
     * @return 
     */
    public String cadastrarTipoPerfil(){   
        try {
            this.controlador.cadastrarTipoPerfil(tipoPerfil);
            return "tiposPerfis"; 
        } catch (TipoPerfilException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR,"Não foi possível cadastrar este perfil - "+ex.getMessage());
        }
        return "timeline"; 
    }
    
    /**
     * Chamada para a tela de criação de um novo perfil 
     * @return 
     */
    public String novoPerfil(){
        this.tipoPerfil = new TipoPerfil();
        return "novoTipoPerfil"; 
    }
    
    /**
     * Retorna uma lista de perfis
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfil(){   
        return this.controlador.listarTipoPerfil();
    }
    
    /**
     * Retorna a tela de edição do perfil selecionado na lista
     * @param id
     * @return 
     */
    public String editarPerfil(int id){      
        tipoPerfil = dao.carregarTipoPerfil(id);
        return "novoTipoPerfil";
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
    
    /**
     * @return 
     */
    public ControladorCadastro getControlador() {
        return controlador;
    }
    
    /**
     * @return 
     */
    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }
}
