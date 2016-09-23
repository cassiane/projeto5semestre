/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author 00026108
 */
@Entity
//@Table(name="tipostatus")
public class TipoStatus implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
   
    private String tipoStatus;

    public TipoStatus(String status) {
        this.tipoStatus = status;
    }

    public TipoStatus() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(String tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

 
    
    
}
