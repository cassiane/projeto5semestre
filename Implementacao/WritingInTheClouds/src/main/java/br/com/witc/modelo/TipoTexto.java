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
public class TipoTexto {
    @Id
    @GeneratedValue
    private int id;
    private String tipoTexto;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(String tipoTexto) {
        this.tipoTexto = tipoTexto;
    }
}
