/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.mail.EmailException;
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
     * @throws br.com.witc.excessao.DadosUsuarioInvalidoException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     * @throws br.com.witc.excessao.UsuarioInvalidoException Caso usuário já exista no BD
     */
    public void cadastrarUsuario(Usuario usuario) throws DadosUsuarioInvalidoException, 
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException {
        
        usuario.consistirDados();
        usuario.cadastrarUsuario();        
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

    
    /**
     * Envia o link de redefinição de senha para o usuário
     * @param destinatario O email do usuário que está redefinindo a senha
     * @throws EmailException Caso ocorra erro no envio do email
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja encontrado
     * @throws NoSuchAlgorithmException Caso ocorra um erro na criação do hash
     * @throws UnsupportedEncodingException Caso ocorra um erro na criação do hash
     */
    public void recuperarConta(String destinatario) throws EmailException, 
            DadosUsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {                        
        RecuperarConta recuperar = new RecuperarConta();
        
        this.usuario = Usuario.verificarExistenciaUsuario(destinatario);
        recuperar.setUsuario(this.usuario);
        String senhaHash = Calendar.getInstance().getTime().toString() + "witc" + Arrays.toString(destinatario.getBytes());
        recuperar.setHashRecuperacaoSenha(Usuario.criarHashSenha(senhaHash));                
        recuperar.EnviarEmailRecuperacao();        
    }             
    
    /**
     * Redefine a senha do usuário
     * @param email O email do usuário
     * @param hashCode O código hash do link da página de redefinição
     * @param novaSenha A nova senha do usuário
     * @throws DadosUsuarioInvalidoException Caso o usuário não esteja cadastrado no sistema
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     * @throws LinkRecuperacaoInvalidoException Caso o link seja inválido     
     */
    public void redefinirSenha(String email, String hashCode, String novaSenha) 
            throws DadosUsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException, 
            LinkRecuperacaoInvalidoException {
        this.usuario = Usuario.verificarExistenciaUsuario(email);
        
        RecuperarConta recuperar = new RecuperarConta();
        recuperar.setUsuario(this.usuario);
        recuperar.setHashRecuperacaoSenha(hashCode);
        recuperar = recuperar.verificarLink();
        
        this.usuario.setSenha(novaSenha);
        
        try {
            this.usuario.cadastrarUsuario();
        } catch (UsuarioInvalidoException ex) { // Essa excessao, no cadastro do usuario, eh lancada quando o usuario jah estah cadastrado no sistema
            // Essa excecao nao eh lancada, pois eh necessario que o usuario tenha
            // cadastro para redefinir a senha
        }
        
        recuperar.setDataUtilizacao(Calendar.getInstance());
        recuperar.salvar();
    }
}
