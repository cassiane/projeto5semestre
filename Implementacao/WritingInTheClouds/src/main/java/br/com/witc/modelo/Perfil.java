/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author root
 */
@Entity
public class Perfil implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private float avaliacao;
    private int qtdAvaliacoes;
    private float somaAvaliacoes;
    private String pseudonimo;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idTipoPerfil")
    private TipoPerfil tipoPerfil;
    private boolean perfilPadrao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
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

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public boolean isPerfilPadrao() {
        return perfilPadrao;
    }

    public void setPerfilPadrao(boolean perfilPadrao) {
        this.perfilPadrao = perfilPadrao;
    }

    /**
     * Método que retorna o perfil do usuario logado
     *
     * @param usuario
     * @return
     */
    public static Perfil retornarPerfilUsuarioLogado(Usuario usuario) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarPerfil(usuario);
    }

    /**
     * @return O nome do usuario em formato ABNT
     */
    public String getNomeUsuarioABNT() {
        return this.usuario.getNomeABNT();
    }
    
    /**     
     * @return O nome do usuário
     */
    public String getNomeUsuario() {        
        return this.usuario.getNome().split(" ")[0];
    }
    
    /**     
     * @return O sobrenome do usuário
     */
    public String getSobrenomeUsuario() {        
        return this.usuario.getSobrenome().split(" ")[this.usuario.getSobrenome().split(" ").length - 1];
    }
    
    public Perfil carregarPerfil(Usuario usuario) {
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.carregarPerfil(usuario);
    }

    /**
     * Persistir a busca de amigos editores
     *
     * @param idLivro
     * @return lista de amigos editores
     */
    public List<Perfil> carregarListaAmigoEditor(int idLivro) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarListaAmigoEditor(this.getUsuario().getId(), idLivro);
    }

    /**
     * Acessa a persistencia para desativar o perfil padrão
     * @param perfil Perfil do usuario
     */
    public void desativarPerfil(Perfil perfil) {
        PerfilDAO dao = new PerfilDAO();
        perfil.setPerfilPadrao(false);
        dao.salvarPerfil(perfil);
    }

    /**
     * Acessa a persistencia para criar ou atualizar o perfil
     * @param newPerfil Perfil do usuario
     */
    public void criarPerfil(Perfil newPerfil) {
        PerfilDAO dao = new PerfilDAO();
        dao.salvarPerfil(newPerfil);
    }

    /**
     * Acessa a persistencia para buscar os perfis do usuario
     * @param usuario Usuario logado
     * @return Lista de perfis
     */
    public List<Perfil> listarPerfisUsuario(Usuario usuario) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarListaPerfilUsuario(usuario);
    }   
    
    /**     
     * @param idPerfil O id do perfil a ser carregado
     * @return Um objeto Perfil, com o perfil carregado
     */
    public Perfil carregarPerfilPorId(int idPerfil) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregaPerfilID(idPerfil);
    }
}
