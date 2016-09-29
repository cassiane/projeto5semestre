/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import java.io.Serializable;
import java.util.Calendar;
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
//@Table(name = "historicolivros")
public class HistoricoLivros implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
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
    
    public void salvar() {
        HistoricoLivroDAO dao = new HistoricoLivroDAO();
        dao.salvarHistorico(this);
    }
}
