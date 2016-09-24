/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.TipoTextoException;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.TipoTextoDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    private Integer nroPaginas;
    private byte[] capa;
    private String classificacao;
    private boolean disponivelBiblioteca;
    private boolean reportadoConteudoImproprio;
    private Integer qualificacao;
    private String texto;
    @OneToOne
    @JoinColumn(name="idTipoTexto")
    private TipoTexto tipoTexto;
    @OneToOne
    @JoinColumn(name="idTipoGenero")
    private TipoGenero tipoGenero;
    @OneToMany(mappedBy = "livro")
    private List<HistoricoLivro> historicoLivros;
   

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

    public Integer getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(Integer nroPaginas) {
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

    public Integer getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(Integer qualificacao) {
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

    public TipoTexto getTipoTexto() {
        return tipoTexto;
    }

    public void setTipoTexto(TipoTexto tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    public TipoGenero getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(TipoGenero tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
    
    /**     
     * @param idLivro O id do livro
     * @return O array de byte que representa a imagem
     */
    public byte[] getCapaPorId(int idLivro) {
        LivroDAO livroDAO = new LivroDAO();
        return livroDAO.carregarLivro(idLivro).getCapa();
    }

    /**
     * @return the historicoLivros
     */
    public List<HistoricoLivro> getHistoricoLivros() {
        return historicoLivros;
    }

    /**
     * @param historicoLivros the historicoLivros to set
     */
    public void setHistoricoLivros(List<HistoricoLivro> historicoLivros) {
        this.historicoLivros = historicoLivros;
    }
    
    /**
     * Lista os livros na biblioteca com o Tipo Texto enviado
     * @param tp O Tipo Texto para pesquisa
     * @return Uma lista de Livro
     * @throws BibliotecaVirtualVaziaException Caso não seja encontrado nenhum livro com o critério informado
     */
    public List<Livro> listarLivrosPorTipoTexto(TipoTexto tp) 
            throws BibliotecaVirtualVaziaException {
        LivroDAO livroDAO = new LivroDAO();
        return livroDAO.listarLivrosPorTipoTexto(tp);
    }
    
    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de pesquisa
     * @param tp O Tipo Texto para pesquisa
     * @param campoPesquisa O campo a ser pesquisado
     * @param valorPesquisa O valor a ser pesquisado
     * @return Um objeto Map, com os livros encontrados
     * @throws BibliotecaVirtualVaziaException Caso não sejam encontrados livros
     */
    public List<Livro> listarLivrosPorTipoTexto(TipoTexto tp, String campoPesquisa, String valorPesquisa) 
            throws BibliotecaVirtualVaziaException {                        
        LivroDAO livroDAO = new LivroDAO();
        return livroDAO.listarLivrosPublicados(tp, campoPesquisa, valorPesquisa);
    }    
}
