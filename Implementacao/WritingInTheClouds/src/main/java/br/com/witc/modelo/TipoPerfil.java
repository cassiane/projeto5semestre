/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.TipoPerfilDAO;
import java.io.Serializable;
import java.util.List;
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
     * Cadastra o tipo de perfil
     */
    public void cadastrarTipoPerfil() {
        setTipoPerfil(getTipoPerfil().toUpperCase());
        TipoPerfilDAO daoTipoPerfil = new TipoPerfilDAO();
        daoTipoPerfil.salvarTipoPerfil(this);
    }
    
    /**
     * Listar tipos de perfis
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfil() {
       TipoPerfilDAO dao = new TipoPerfilDAO();
       return dao.listarTiposPerfil();        
    }
}
