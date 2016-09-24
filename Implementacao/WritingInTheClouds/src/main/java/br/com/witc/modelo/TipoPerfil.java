/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.TipoPerfilDAO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author 00026108
 */
@Entity
public class TipoPerfil implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String tipoPerfil;
    private boolean ativo;
    TipoPerfilDAO dao;
    /**
     * @return 
     */
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
    public String getTipoPerfil() {
        return tipoPerfil;
    }
    /**
     * @param tipoPerfil 
     */
    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
    /**
     * @param b 
     */
    public void setAtivo(boolean b) {
        this.ativo = b;
    }

    public void cadastrarTipoPerfil() {
        dao.cadastrarTipoPerfil(this);
    }
}
