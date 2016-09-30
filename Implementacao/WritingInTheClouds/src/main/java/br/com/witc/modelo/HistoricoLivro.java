/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author root
 */
@Entity
@Table(name = "historicolivros")
public class HistoricoLivro implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataInicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar  dataConclusao;        
    @ManyToOne
    @JoinColumn(name="idLivro")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name="idPerfil")
    private Perfil perfil;
    @OneToOne
    @JoinColumn(name="idTipoStatus")
    private TipoStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Calendar dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }
    
    public String getNomeUsuarioABNT() {
        return this.perfil.getNomeUsuarioABNT();
    }
    
    public void salvarHistorico(HistoricoLivro hist){
        HistoricoLivroDAO historicoLivroDAO = new HistoricoLivroDAO();
        historicoLivroDAO.salvarHistorico(hist);
    }
    
    /**     
     * @param idLivro O id do livro pesquisado
     * @return Uma lista com os registros daquele livro no BD
     */ 
    public List<HistoricoLivro> listarHistoricoLivro(int idLivro) {
        HistoricoLivroDAO historicoLivroDAO = new HistoricoLivroDAO();
        return historicoLivroDAO.listarHistoricoLivro(idLivro);
    }
    
    /**
     * Carrega o histórico de um determinado livro e de um determinado perfil
     * @param livro O livro buscado
     * @param perfil O perfil do usuário
     * @return Um objeto HistoricoLivro
     */
    public HistoricoLivro carregarHistoricoLivroUsuario(Livro livro, Perfil perfil) {
        HistoricoLivroDAO historicoLivroDAO = new HistoricoLivroDAO();
        return historicoLivroDAO.carregarHistoricoLivroUsuario(livro, perfil);
    }
    
    /**
     * Verifica se o livro já foi finalizado pelo usuário
     * @param idLivro O id do livro buscado
     * @param idPerfil O id do perfil do usuário
     * @return Um objeto HistoricoLivro
     */
    public boolean estaFinalizadoUsuario(int idLivro, int idPerfil) {
        HistoricoLivroDAO historicoLivroDAO = new HistoricoLivroDAO();
        return historicoLivroDAO.estaFinalizadoUsuario(idLivro, idPerfil);
    }
    
    /**
     * 
     * @param livro O livro a ser persistido
     * @param perfil O perfil do usuário criador do livro     */
    public void finalizarLivroUsuario(Livro livro, Perfil perfil) {
        HistoricoLivroDAO historicoLivroDAO = new HistoricoLivroDAO();
        HistoricoLivro historico = historicoLivroDAO.carregarHistoricoLivroUsuario(livro, perfil);

        historico.setDataConclusao(Calendar.getInstance());
        historicoLivroDAO.salvarHistorico(historico);
    }
}
