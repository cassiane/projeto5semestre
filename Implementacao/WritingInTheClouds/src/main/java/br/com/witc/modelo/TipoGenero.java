/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

/**
 *
 * @author root
 */
class TipoGenero {
   
    private int id;
    private String tipoGenero;

    public TipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
    
}
