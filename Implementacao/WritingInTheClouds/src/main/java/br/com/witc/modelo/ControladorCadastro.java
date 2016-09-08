/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.mail.MessagingException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author marcelo.lima
 */
public class ControladorCadastro {


    private Usuario usuario;

    public ControladorCadastro() {
        this.usuario = new Usuario();
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
     * @throws br.com.witc.excessao.UsuarioInvalidoException
     */
    public List<Usuario> listarAmigos() throws UsuarioInvalidoException {
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

    public List<Usuario> listarSugestao() throws UsuarioInvalidoException{
        return this.usuario.listarSugestao();
    }

    public void usuarioLogado(Usuario usuario) {
        this.usuario = usuario;
    }

    public void solicitarAmizade(int idSugestao) {
        this.usuario.solicitarAmizade(idSugestao);
    }

    public List<Usuario> listarSolicitacao() throws UsuarioInvalidoException {
        return this.usuario.listarSolicitacao();
    }

    public void aceitarAmizade(int idAceitar) {
        this.usuario.aceitarAmizade(idAceitar);
    }

    public void removerAmizade(int idAmizade) {
        this.usuario.removerAmizade(idAmizade);
    }

    
    /**
     * Envia o link de redefinição de senha para o usuário
     * @param destinatario O email do usuário que está redefinindo a senha
     * @throws MessagingException Caso ocorra erro no envio do email
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja encontrado
     * @throws NoSuchAlgorithmException Caso ocorra um erro na criação do hash
     * @throws UnsupportedEncodingException Caso ocorra um erro na criação do hash
     */
    public void recuperarSenha(String destinatario) throws MessagingException, 
            DadosUsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {                        
        RedefinicaoSenha redefinicao = new RedefinicaoSenha();
        
        this.usuario = Usuario.verificarExistenciaUsuario(destinatario);
        redefinicao.setUsuario(this.usuario);
        String senhaHash = Calendar.getInstance().getTime().toString() + "witc" + Arrays.toString(destinatario.getBytes());
        redefinicao.setHashRecuperacaoSenha(Usuario.criarHashSenha(senhaHash));
        
        redefinicao.EnviarEmailRecuperacao();
    }     
}
