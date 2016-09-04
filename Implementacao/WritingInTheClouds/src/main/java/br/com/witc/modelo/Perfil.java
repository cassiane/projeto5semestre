/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author root
 */
public class Perfil {
    @Id
    @GeneratedValue
    private int id;
    private int qualificacao;
    private String pseudonimo;
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
}
