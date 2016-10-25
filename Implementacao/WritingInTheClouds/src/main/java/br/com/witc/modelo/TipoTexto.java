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
import javax.persistence.*;

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
    @ManyToOne(cascade=CascadeType.ALL)
           @JoinTable(name="usuario_tem_tipotexto",
                     joinColumns={@JoinColumn(name="idUsuario",  
                      referencedColumnName="id")},  
                     inverseJoinColumns={@JoinColumn(name="idTipoTexto",   
                      referencedColumnName="id")})  
    private Usuario usuario;
    
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
     * // Utilizado para a implementação do convert
     * @return 
     */    
    @Override
    public int hashCode() {
        final int primo = 19;
        int resultado = 2;
        resultado = primo * resultado + this.getId();
        return resultado;
    }
    
    /**
     * / Utilizado para a implementação do convert
     * @param obj
     * @return 
     */    
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
