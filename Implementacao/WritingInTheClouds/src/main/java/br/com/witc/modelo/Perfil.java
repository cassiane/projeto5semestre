/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private int idTipoPerfil;
    private String pseudonimo;
   @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    
    
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
    
    
    public int getIdTipoPerfil() {
        return idTipoPerfil;
    }

    public void setIdTipoPerfil(int tipoPerfil) {
        this.idTipoPerfil= tipoPerfil;
    }
    
    // cria perfil padrao para um determinado usuario;
    public void criarPerfilPadrao(Usuario usuario){
        Perfil p = new Perfil();
       
        p.setUsuario(usuario);
        p.setPseudonimo(usuario.getNome());
        p.setQualificacao(0);
        p.setIdTipoPerfil(1);
        
        PerfilDAO dao = new PerfilDAO();
        dao.criarPerfil(p);
        
        
    }

}
