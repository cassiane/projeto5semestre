/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.TipoTextoException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.persistencia.TipoPerfilDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author 10070187
 */
public class TipoTextoBean {
    private final ControladorCadastro controlador;
    private TipoTexto tipoTexto;
    private TipoPerfilDAO dao; 

    public TipoTextoBean(){
        this.controlador = new ControladorCadastro();
        this.tipoTexto = new TipoTexto();
    }
    /**
     * Cadastra um novo perfil
     * @return 
     */
    public String cadastrarTipoPerfil(){   
        try {
            this.controlador.cadastrarTipoTexto(tipoTexto);
            return "tiposTexto"; 
        } catch (TipoTextoException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR,"Não foi possível cadastrar este tipo de texto. - "+ex.getMessage());
        }
        return "timeline"; 
    }
    
    /**
     * Chamada para a tela de criação de um novo perfil 
     * @return 
     */
    public String novoPerfil(){
        this.tipoTexto = new TipoTexto();
        return "novoTipoTexto"; 
    }
    
    /**
     * Retorna uma lista de perfis
     * @return 
     */
    public List<TipoTexto> listarTipoTexto(){   
        return this.controlador.listarTipoTexto();
    }
    
    /**
     * Retorna a tela de edição do perfil selecionado na lista
     * @param id
     * @return 
     */
    public String editarTipoTexto(int id){ 
        tipoTexto = this.controlador.carregarTipoTexto(id);
        return "novoTipoTexto";
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
