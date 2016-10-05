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
    private Integer qualificacao;
    private String pseudonimo;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="idTipoPerfil")
    private TipoPerfil tipoPerfil;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(Integer qualificacao) {
        this.qualificacao = qualificacao;
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
    
    /**
     * Método que retorna o perfil do usuario logado
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
    
    public Perfil carregarPerfil (Usuario usuario){
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.carregarPerfil(usuario);

    /**
     * Persistir a busca de amigos editores
     * @return lista de amigos editores
     */
    public List<Perfil> carregarListaAmigoEditor(int idLivro) {
        PerfilDAO dao = new PerfilDAO();
        return dao.carregarListaAmigoEditor(this.getUsuario().getId(), idLivro);
    }
}
