/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.UsuarioInvalidoException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author marcelo.lima
 */
public class ControladorCadastro {


    private Usuario usuario;

    public ControladorCadastro(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Cadastra um usuário no sistemap
     *
     * @param usuario O usuário a ser cadastrado no sistema
     * @return Uma string contendo a próxima página a ser enviada para o usuário
     * @throws br.com.witc.excessao.UsuarioInvalidoException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public String cadastrarUsuario(Usuario usuario) throws UsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {
        usuario.CadastrarUsuario(usuario);
        return "timeline";
    }

    /**
     * Listar os amigos do usuario logado
     *
     * @return A lista de amigos
     */
    public List<Usuario> listarAmigos() {
        return this.usuario.listarAmigos();
    }

    /**
     * Retorna a imagem do amigo do usuario
     *
     * @param usufoto Usuario amigo
     * @return A imagem
     */
    public StreamedContent getAmigosFoto(Usuario usufoto) {
        if (usufoto.getFoto() == null) {
            return null;
        }
        StreamedContent foto = new DefaultStreamedContent(new ByteArrayInputStream(usufoto.getFoto()), "image/png");
        return foto;
    }

    public List<Usuario> listarSugestao() {
        return this.usuario.listarSugestao();
    }

    public void usuarioLogado(Usuario usuario) {
        this.usuario = usuario;
    }

    public void solicitarAmizade(int idSugestao) {
        this.usuario.solicitarAmizade(idSugestao);
    }

    public List<Usuario> listarSolicitacao() {
        return this.usuario.listarSolicitacao();
    }

    public void aceitarAmizade(int idAceitar) {
        this.usuario.aceitarAmizade(idAceitar);
    }

    public void removerAmizade(int idAmizade) {
        this.usuario.removerAmizade(idAmizade);
    }

}
