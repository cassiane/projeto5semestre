/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author root
 */
@Entity
public class Livro implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    private int nroPaginas;
    private byte[] capa;
    private String classificacao;
    private boolean disponivelBiblioteca;
    private boolean reportadoConteudoImproprio;
    private int qualificacao;
    private String texto;
    @OneToOne
    private Perfil perfil;
    /*
    @ManyToOne
    @Column(name="idTipoGenero")
    private TipoGenero tipoGenero;
    @ManyToOne
    @Column(name="idTipoTexto")
    private TipoTexto tipoTexto;
   */

    public Livro(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public Livro() {
       
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isDisponivelBiblioteca() {
        return disponivelBiblioteca;
    }

    public void setDisponivelBiblioteca(boolean disponivelBiblioteca) {
        this.disponivelBiblioteca = disponivelBiblioteca;
    }

    public boolean isReportadoConteudoImproprio() {
        return reportadoConteudoImproprio;
    }

    public void setReportadoConteudoImproprio(boolean reportadoConteudoImproprio) {
        this.reportadoConteudoImproprio = reportadoConteudoImproprio;
    }

    public int getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(int qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public void criarLivro(Perfil[]autores){
        
    }
    public void editarLivro(){
        
    }
    public void carregarLivro(int id){
        
    }
}
