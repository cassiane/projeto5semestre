/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.io.Serializable;
import java.util.List;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(int qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    /**
     * Persistir a busca de amigos editores
     * @return lista de amigos editores
     */
    public List<Perfil> carregarListaAmigoEditor() {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarListaAmigoEditor(this.getUsuario().getId());
    }
}
