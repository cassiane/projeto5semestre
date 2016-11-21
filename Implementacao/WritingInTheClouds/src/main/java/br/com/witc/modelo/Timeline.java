/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.TimelineException;
import br.com.witc.persistencia.TimelineDAO;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo.lima
 */
@Entity
public class Timeline implements Serializable {
    @GeneratedValue
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="idAmigo")
    private Usuario amigo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataPublicacao;
    private String publicacao;
    
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
     * @return the amigo
     */
    public Usuario getAmigo() {
        return amigo;
    }

    /**
     * @param amigo the amigo to set
     */
    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    /**
     * @return the dataPublicacao
     */
    public Calendar getDataPublicacao() {
        return dataPublicacao;
    }

    /**
     * @param dataPublicacao the dataPublicacao to set
     */
    public void setDataPublicacao(Calendar dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    /**
     * @return the publicacao
     */
    public String getPublicacao() {
        return publicacao;
    }

    /**
     * @param publicacao the publicacao to set
     */
    public void setPublicacao(String publicacao) {
        this.publicacao = publicacao;
    }        
    
    /**        
     * @return Uma lista contendo as publicações dos Amigos
     * @throws br.com.witc.excessao.TimelineException Caso não haja publicação dos amigos
     */
    public List<Timeline> getPublicacoesAmigos() throws TimelineException {
        TimelineDAO timelineDAO = new TimelineDAO();
        return timelineDAO.listarPublicacoesAmigos(this.usuario);
    }        
    
    public void salvarMensagemPublicacao() {
        TimelineDAO timelineDAO = new TimelineDAO();
        timelineDAO.salvarPublicacao(this);
    }
}
