/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.TimelineException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author marcelo.lima
 */
public class ControladorPublicacao {

    private Usuario usuario;
    private Publicacao timeline;

    public ControladorPublicacao() {
        this.usuario = new Usuario();
        this.timeline = new Publicacao();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return the timeline
     */
    public Publicacao getTimeline() {
        return timeline;
    }

    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(Publicacao timeline) {
        this.timeline = timeline;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @param idPerfil O id do perfil visualizado
     * @return Uma lista contendo as publicações dos Amigos
     * @throws br.com.witc.excessao.TimelineException Caso não haja publicação
     * dos amigos
     */
    public List<Publicacao> getPublicacoesAmigos(int idPerfil) throws TimelineException {
        Perfil perfil = new Perfil();
        Usuario usuario = perfil.getUsuarioPorIdPerfil(idPerfil);
        this.timeline.setUsuario(usuario);
        return this.timeline.getPublicacoesAmigos();
    }

    /**
     * @param idAmigo o id do usuário amigo
     * @return Um array de bytes com a foto
     */
    public byte[] getFotoAmigo(int idAmigo) {
        Usuario user = this.usuario.carregarAmigo(idAmigo);
        return user.getFoto();
    }

    public void salvarMensagemPublicacao(String mensagem) {
        List<Usuario> lstAmigos = this.usuario.listarAmigos();
        Usuario usuarioLogado = this.usuario;
        if (lstAmigos != null) {
            for (Usuario usr : lstAmigos) {
                this.timeline = new Publicacao();
                this.timeline.setUsuario(usr);
                this.timeline.setAmigo(usuarioLogado);
                this.timeline.setDataPublicacao(Calendar.getInstance());
                this.timeline.setMensagemPublicacao(mensagem);
                this.timeline.salvarMensagemPublicacao();
            }
        }
        this.timeline = new Publicacao();
    }
}
