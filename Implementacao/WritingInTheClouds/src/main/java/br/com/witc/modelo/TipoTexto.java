/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.TipoTextoException;
import br.com.witc.persistencia.TipoTextoDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author root
 */
@Entity
public class TipoTexto implements Serializable {
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

    @Override
    public String toString() {
        return tipoTexto;
    }        
    
    /**     
     * @return Uma lista contendo os tipos de textos cadastrados no sistema
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema
     */
    public List<TipoTexto> getLstTipoTexto() throws TipoTextoException {
        TipoTextoDAO tipoTextoDAO = new TipoTextoDAO();
        return tipoTextoDAO.getLstTipoTexto();
    }
    
    // Utilizado para a implementação do convert
    @Override
    public int hashCode() {
        final int primo = 19;
        int resultado = 2;
        resultado = primo * resultado + this.getId();
        return resultado;
    }

    // Utilizado para a implementação do convert
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;        
        if (getClass() != obj.getClass())
            return false;
        TipoTexto tipoTexto = (TipoTexto) obj;
        return this.getId() == tipoTexto.getId();
    }            
}
