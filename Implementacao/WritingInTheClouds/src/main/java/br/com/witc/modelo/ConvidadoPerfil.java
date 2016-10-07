/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.ConvidadoPerfilDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author 10070124
 */
@Entity
public class ConvidadoPerfil implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name="idPerfil")
    private Perfil idPerfil;
    @OneToOne
    @JoinColumn(name="idPerfilConvidado")
    private Perfil idPerfilConvidado;
    @OneToOne
    @JoinColumn(name="idLivro")
    private Livro idLivro;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataSolicitacao;

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfil getIdPerfilConvidado() {
        return idPerfilConvidado;
    }

    public void setIdPerfilConvidado(Perfil idPerfilConvidado) {
        this.idPerfilConvidado = idPerfilConvidado;
    }

    public Livro getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Livro idLivro) {
        this.idLivro = idLivro;
    }

    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public ConvidadoPerfil() {
    }

    /**
     * Construtor setando a data
     * @param dataSolicitacao 
     */
    public ConvidadoPerfil(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    /**
     * Metodo para persistir o registro da solicitação de edição
     */
    public void salvar() {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        dao.salvar(this);
    }
    
    /*
    public List<ConvidadoPerfil> carregar(Perfil idPerfilConvidado) {
        List<ConvidadoPerfil> list = new ArrayList<ConvidadoPerfil>();
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        list = dao.carregar(idPerfilConvidado);
        return list;
    }
    */
    
    /**
     * Deletar da tabela o registro atual
     */
    public void remover() {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        dao.remover(this);
    }

    /**
     * Buscar a lista de solicitações de edição do usuario ativo
     * @return a lista de solicitações do usuario ativo
     */
    public List<ConvidadoPerfil> carregarlista() {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        return dao.carregar(this.idPerfilConvidado);
    }

    /**
     * Metodo para gravar a aceitação da solicitação
     */
    public void aceitarEdicao() {
        HistoricoLivro historico = new HistoricoLivro();
        //historico.setLivro(this.idLivro);
        //historico.setPerfil(this.idPerfilConvidado);
        //TipoStatus status = new TipoStatus().carregarTipoStatus(1);
        //status.carregarTipoStatus(1);
        //historico.setStatus(status);
        //historico.setDataInicio(Calendar.getInstance());
        historico.salvarHistoricoConvite(this.idPerfilConvidado.getId(), this.idLivro.getId());
        //this.remover();
    }

    /**
     * Metodo para remover a solicitação
     */
    public void negarEdicao() {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        dao.remover(this);
    }
}
