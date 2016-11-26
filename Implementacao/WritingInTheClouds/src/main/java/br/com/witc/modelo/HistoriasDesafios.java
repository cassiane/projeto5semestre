/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoriasDesafiosDAO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author root
 */
@Entity
public class HistoriasDesafios implements Serializable {      
    @Id
    @GeneratedValue
    private int id;
    private String classificacao;
    private boolean disponivelBiblioteca;
    private boolean reportadoConteudoImproprio;
    private float avaliacao;
    private int qtdAvaliacoes;
    private float somaAvaliacoes;
    private String texto;
    @OneToOne
    @JoinColumn(name="idTipoTexto")
    private TipoTexto tipoTexto;    
    @OneToOne
    @JoinColumn(name = "idDesafiosUsuarios")
    private DesafiosUsuarios desafiosUsuarios;

    public HistoriasDesafios() {
        this.desafiosUsuarios = new DesafiosUsuarios();
    }
    
    
    /**
     * @return the qtdAvaliacoes
     */
    public int getQtdAvaliacoes() {
        return qtdAvaliacoes;
    }

    /**
     * @param qtdAvaliacoes the qtdAvaliacoes to set
     */
    public void setQtdAvaliacoes(int qtdAvaliacoes) {
        this.qtdAvaliacoes = qtdAvaliacoes;
    }
    
    /**
     * @return the somaAvaliacoes
     */
    public float getSomaAvaliacoes() {
        return somaAvaliacoes;
    }

    /**
     * @param somaAvaliacoes the somaAvaliacoes to set
     */
    public void setSomaAvaliacoes(float somaAvaliacoes) {
        this.somaAvaliacoes = somaAvaliacoes;
    }  
    
    /**
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
    /**
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoriasDesafios other = (HistoriasDesafios) obj;
        return this.getId() == other.getId();
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
     * @return the classificacao
     */
    public String getClassificacao() {
        return classificacao;
    }

    /**
     * @param classificacao the classificacao to set
     */
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * @return the disponivelBiblioteca
     */
    public boolean isDisponivelBiblioteca() {
        return disponivelBiblioteca;
    }

    /**
     * @param disponivelBiblioteca the disponivelBiblioteca to set
     */
    public void setDisponivelBiblioteca(boolean disponivelBiblioteca) {
        this.disponivelBiblioteca = disponivelBiblioteca;
    }

    /**
     * @return the reportadoConteudoImproprio
     */
    public boolean isReportadoConteudoImproprio() {
        return reportadoConteudoImproprio;
    }

    /**
     * @param reportadoConteudoImproprio the reportadoConteudoImproprio to set
     */
    public void setReportadoConteudoImproprio(boolean reportadoConteudoImproprio) {
        this.reportadoConteudoImproprio = reportadoConteudoImproprio;
    }

    /**
     * @return the avaliacao
     */
    public float getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the tipoTexto
     */
    public TipoTexto getTipoTexto() {
        return tipoTexto;
    }

    /**
     * @param tipoTexto the tipoTexto to set
     */
    public void setTipoTexto(TipoTexto tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    /**
     * @return the desafiosUsuarios
     */
    public DesafiosUsuarios getDesafiosUsuarios() {
        return desafiosUsuarios;
    }

    /**
     * @param desafiosUsuarios the desafiosUsuarios to set
     */
    public void setDesafiosUsuarios(DesafiosUsuarios desafiosUsuarios) {
        this.desafiosUsuarios = desafiosUsuarios;
    }
    
    /**
     * Salvar a historia do desafio
     * @param historiasDesafios 
     */
    public void salvarHistoriaDesafio(HistoriasDesafios historiasDesafios) {
        HistoriasDesafiosDAO dao = new HistoriasDesafiosDAO();        
        dao.salvarHistoriaDesafio(historiasDesafios);
    }
    
    /**
     * Carrega um objeto HistoriasDesafios
     * @param desafiosUsuarios
     * @return 
     */
    HistoriasDesafios carregarHistoriasDesafios(DesafiosUsuarios desafiosUsuarios) {
        HistoriasDesafiosDAO dao = new HistoriasDesafiosDAO();
        return dao.carregarHistoriasDesafios(desafiosUsuarios);
    }
    
    /**
     * Carrega a história do desafio por id  
     * @param idHistoriasDesafios
     * @return 
     */
    HistoriasDesafios carregarHistoriasDesafiosPorId(int idHistoriasDesafios) {
        HistoriasDesafiosDAO dao = new HistoriasDesafiosDAO();
        return dao.carregarHistoriasDesafiosPorId(idHistoriasDesafios);
    }
}
