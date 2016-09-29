/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.TipoGeneroException;
import br.com.witc.persistencia.TipoGeneroDAO;
import br.com.witc.persistencia.TipoTextoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author root
 */
@Entity
public class TipoGenero implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String tipoGenero;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoGenero other = (TipoGenero) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tipoGenero, other.tipoGenero)) {
            return false;
        }
        return true;
    }

    List<TipoGenero> getLstTipoGenero() throws TipoGeneroException {
        TipoGeneroDAO tipoGeneroDAO = new TipoGeneroDAO();
        return tipoGeneroDAO.getLstTipoGenero();
    }
    
}
