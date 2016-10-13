/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.ConvidadoPerfilDAO;
import java.io.Serializable;
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
    @OneToOne
    @JoinColumn(name="idPerfil")
    private Perfil idPerfil;
    @Id
    @OneToOne
    @JoinColumn(name="idPerfilConvidado")
    private Perfil idPerfilConvidado;
    @Id
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
    
    /**
     * Deletar da tabela o registro atual
     * @param perfil
     * @param livro
     */
    public void remover(Perfil perfil, Livro livro) {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        ConvidadoPerfil remove = dao.carregar(perfil, livro);
        dao.remover(remove);
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
     * @param perfil
     * @param livro
     */
    public void aceitarEdicao(Perfil perfil, Livro livro) {
        HistoricoLivro historico = new HistoricoLivro();
        historico.setLivro(livro);
        historico.setPerfil(perfil);
        TipoStatus status = new TipoStatus().carregarTipoStatus(1);
        status.carregarTipoStatus(1);
        historico.setStatus(status);
        historico.setDataInicio(Calendar.getInstance());
        historico.salvarHistorico(historico);
        this.remover(perfil, livro);
        //historico.salvarHistoricoConvite(this.idPerfilConvidado.getId(), this.idLivro.getId());
    }

    /**
     * Metodo para remover a solicitação
     */
    public void negarEdicao() {
        ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
        dao.remover(this);
    }
}
