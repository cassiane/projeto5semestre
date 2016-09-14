/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.persistencia.RecuperarContaDAO;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author marcelo.lima
 */
@Entity
public class RecuperarConta implements Serializable {
    @Id
    @GeneratedValue
    private int id;    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataSolicitacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dataUtilizacao;
    private String hashRecuperacaoSenha;
    private boolean inutilizado;
    
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
     * @return the dataSolicitacao
     */
    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao the dataSolicitacao to set
     */
    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * @return the dataUtilizacao
     */
    public Calendar getDataUtilizacao() {
        return dataUtilizacao;
    }

    /**
     * @param dataUtilizacao the dataUtilizacao to set
     */
    public void setDataUtilizacao(Calendar dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    /**
     * @return the hashRecuperacaoSenha
     */
    public String getHashRecuperacaoSenha() {
        return hashRecuperacaoSenha;
    }

    /**
     * @param hashRecuperacaoSenha the hashRecuperacaoSenha to set
     */
    public void setHashRecuperacaoSenha(String hashRecuperacaoSenha) {
        this.hashRecuperacaoSenha = hashRecuperacaoSenha;
    }
    
    /**
     * @return the inutilizado
     */
    public boolean isInutilizado() {
        return inutilizado;
    }

    /**
     * @param inutilizado the inutilizado to set
     */
    public void setInutilizado(boolean inutilizado) {
        this.inutilizado = inutilizado;
    }    
    
    /**
     * Persiste um objeto RecuperarConta no BD
     */
    public void salvar() {
        RecuperarContaDAO recuperarDAO = new RecuperarContaDAO();
        recuperarDAO.salvar(this);
    }
    
    /**
     * Envia um email com o link para redefinição de senha
     * @throws EmailException Caso ocorra algum problema no envio do email
     */
    public void EnviarEmailRecuperacao() throws EmailException {       
        Mensagem mensagem = new Mensagem();
        mensagem.setDestino(this.usuario.getEmail());
        mensagem.setTitulo("Redefinição de senha do WitC");
        String link = "http://localhost:8084/WritingInTheClouds/faces/redefinirSenha.xhtml?usuario=" + this.usuario.getEmail() + "&hash=" + this.hashRecuperacaoSenha;
        mensagem.setMensagem("Prezado ," + this.usuario.getNome()
                + "\n\n Você está recebendo este email porque solicitou a recuperação da sua conta. "
                + "Por favor, clique no link abaixo e siga as instruções da página."
                + "\n\n" + link
                + "\n\nAtenciosamente,"
                + "\n\nEquipe WitC");
        
        //EmailUtils.enviaEmail(mensagem);
        
        this.dataSolicitacao = Calendar.getInstance();
        
        RecuperarContaDAO redefinicaoDAO = new RecuperarContaDAO();
        redefinicaoDAO.salvar(this);
    }    
    
    /**
     * Verifica se o link da página de redefinição de senha é válido
     * @return Um objeto RecuperarConta
     * @throws LinkRecuperacaoInvalidoException Caso o link seja inválido
     */
    public RecuperarConta verificarLink() throws LinkRecuperacaoInvalidoException {
        RecuperarContaDAO recuperarDAO = new RecuperarContaDAO();
        return recuperarDAO.verificarLink(this);
    }
}
