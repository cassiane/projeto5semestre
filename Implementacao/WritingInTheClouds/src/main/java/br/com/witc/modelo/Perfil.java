/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author root
 */
@Entity
public class Perfil implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int qualificacao;
    private String pseudonimo;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="idTipoPerfil")
    private TipoPerfil tipoPerfil;
    
    
    public int getId() {
        return id;
    }
    /**
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return 
     */
    public int getQualificacao() {
        return qualificacao;
    }
    /**
     * @param qualificacao 
     */
    public void setQualificacao(int qualificacao) {
        this.qualificacao = qualificacao;
    }
    /**
     * @return 
     */
    public String getPseudonimo() {
        return pseudonimo;
    }
    /**
     * @param pseudonimo 
     */
    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }
    /**
     * @return 
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * @param usuario 
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   
    /**
     * @return 
     */
    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }
    /**
     * @param tipoPerfil 
     */
    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
        
    /**
     * Método que retorna o perfil do usuario logado
     * @param usuario
     * @return 
     */
    public static Perfil retornarPerfilUsuarioLogado(Usuario usuario) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarPerfil(usuario); 
    }    
}
