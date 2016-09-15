/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.UsuarioInvalidoException;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
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

    /**
     * Metodo do controlador para buscar a lista de sugestão de amigos
     * @return Lista de sugestão de amigos
     */
    public List<Usuario> listarSugestao() {
        return this.usuario.listarSugestao();
    }

    /**
     * Seta o usuario recebido no controlador
     * @param usuario 
     */
    public void usuarioLogado(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo do controlador para realizar a solicitação de amizade
     * @param idSugestao Identificador do amigo para solicitar
     */
    public void solicitarAmizade(int idSugestao) {
        this.usuario.solicitarAmizade(idSugestao);
    }

    /**
     * Metodo do controlador para buscar a lista de solicitações de amizade do usuario
     * @return Lista de solicitações de amizade
     */
    public List<Usuario> listarSolicitacao() {
        return this.usuario.listarSolicitacao();
    }

    /**
     * Metodo do controlador para aceitar a solicitação de amizade
     * @param idAceitar Identificador do solicitante da amizade
     */
    public void aceitarAmizade(int idAceitar) {
        this.usuario.aceitarAmizade(idAceitar);
    }

    /**
     * Metodo do controlador para remover uma amizade ou solicitação de amizade
     * @param idAmizade Identificador do solicitante da amizade
     */
    public void removerAmizade(int idAmizade) {
        this.usuario.removerAmizade(idAmizade);
    }

    /**
     * Metodo do controlador para enviar a solicitação de amizade
     * @param o Email para enviar o convite
     * @param path URL do sistema
     * @throws EmailException Falha ao enviar email
     */
    public void enviarConvite(String o, String path) throws EmailException {
        // Verifica se a variavel não está vazia
        if (!o.isEmpty()) {
            Usuario ami = new Usuario();
            for(Usuario ver : this.listarUsuarios()) {
                // Verifica se o email digitado ja está cadastrado no sistema
                if (ver.getEmail().toLowerCase().equals(o.toLowerCase())) {
                    // Seta a variavel temporaria com o usuario do email cadastrado
                    ami = ver;
                }
            }
            // Verifica se o usuario temporario possui identificador
            if (ami.getId() > 0) {
                //solicitar amizade
                this.solicitarAmizade(ami.getId());
            } else {
                //preencher a mensagem para novos usuarios
                Mensagem msn = new Mensagem();
                msn.setDestino(o.toLowerCase());
                msn.setTitulo("Solicitação de Amizade");
                msn.setMensagem("Você ainda não conhece o Escrita Colaborativa?\n\n"
                        + "Venha logo conhecer, o usuario \b" + this.usuario.getNome()
                        + " " + this.usuario.getSobrenome() + "\b está convidando você"
                        + " para participar, para isso acesse o link " + path + " e faça"
                        + " logo o seu cadastro.");
                //enviar email para novos usuarios
                EmailUtils.enviaEmail(msn);
            }
        }
    }
    
    /**
     * Metodo do controlador para buscar todos os usuarios do sistema
     * @return Lista de usuarios
     */
    public List<Usuario> listarUsuarios() {
        return usuario.listarUsuarios();
    }
}
