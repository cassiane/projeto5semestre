/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

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
    
    /**
     * retorna os dados daquele tipo de texto
     * @param id
     * @return 
     */
    public TipoTexto carregarTipoTexto(int id) {
        TipoTextoDAO dao = new TipoTextoDAO();
        return dao.carregarTipoTexto(id);
    }
    
    /**
     * Persiste o tipo de texto no banco de dados
     * @param tipoTexto 
     */
    public void salvarTipoTexto(TipoTexto tipoTexto) {
        TipoTextoDAO dao = new TipoTextoDAO();
        dao.salvarTipoTexto(tipoTexto);
    }
    
    /**
     * Retorna lista de tipos de dados
     * @return 
     */
    List<TipoTexto> listarTipoTexto() {
        TipoTextoDAO dao = new TipoTextoDAO();
        return dao.listarTiposTexto();
    }
}
