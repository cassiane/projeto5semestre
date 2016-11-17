/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.DesafiosUsuariosDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author cassiane.santos
 */
@Entity
public class DesafiosUsuarios implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idUsuarioDesafiante")
    private Usuario usuarioDesafiante;
    @OneToOne
    @JoinColumn(name = "idDesafio")
    private Desafios desafio;
    private int numeroPalavras;
    
    public List<DesafiosUsuarios> listarDesafiosUsuarios(int idUsuario) {
        DesafiosUsuariosDAO dao = new DesafiosUsuariosDAO();
        return dao.listarDesafiosUsuarios(idUsuario);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the numeroPalavras
     */
    public int getNumeroPalavras() {
        return numeroPalavras;
    }

    /**
     * @param numeroPalavras the numeroPalavras to set
     */
    public void setNumeroPalavras(int numeroPalavras) {
        this.numeroPalavras = numeroPalavras;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioDesafiante
     */
    public Usuario getUsuarioDesafiante() {
        return usuarioDesafiante;
    }

    /**
     * @param usuarioDesafiante the usuarioDesafiante to set
     */
    public void setUsuarioDesafiante(Usuario usuarioDesafiante) {
        this.usuarioDesafiante = usuarioDesafiante;
    }

    /**
     * @return the desafio
     */
    public Desafios getDesafio() {
        return desafio;
    }

    /**
     * @param desafio the desafio to set
     */
    public void setDesafio(Desafios desafio) {
        this.desafio = desafio;
    }
    
    /**
     * Salva na tabela de ligação entre o desafio o usuario e o usuario que desafiou
     * @param des
     * @return 
     */
    public int salvarDesafiosUsuarios(DesafiosUsuarios des) {
        DesafiosUsuariosDAO dao = new DesafiosUsuariosDAO();
        return dao.salvarDesafioUsuario(des);
    }
    
    /**
     * carrega um objeto desafiosUsuarios pelo id
     * @param idDesafiosUsuarios
     * @return 
     */
    DesafiosUsuarios carregarDesafioUsuario(int idDesafiosUsuarios) {
        DesafiosUsuariosDAO dao = new DesafiosUsuariosDAO();
        return dao.carregarDesafioUsuario(idDesafiosUsuarios);
    }
}
