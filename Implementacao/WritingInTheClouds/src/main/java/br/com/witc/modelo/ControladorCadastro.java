/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.excessao.TipoTextoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.TipoPerfilDAO;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author marcelo.lima
 */
public class ControladorCadastro {

    private Usuario usuario;
    private TipoPerfil tipoPerfil; 
    private final TipoPerfilDAO tipoDAO;
    private final Perfil perfil;  
    private final PerfilDAO perfilDAO; 
    private final TipoTexto tipoTexto;

    public ControladorCadastro() {
        this.usuario = new Usuario();
        this.tipoPerfil = new TipoPerfil();
        this.tipoDAO = new TipoPerfilDAO();
        try {  
            this.tipoPerfil = tipoDAO.carregarTipoPerfilEscritor();
        } catch (TipoPerfilException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
        this.perfil    = new Perfil();
        this.perfilDAO = new PerfilDAO();
        this.tipoTexto = new TipoTexto();
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
     * altera um usuário no sistema
     * 
     * @param usuario
     * @throws DadosUsuarioInvalidoException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws UsuarioInvalidoException 
     */
    public void alterarUsuario(Usuario usuario) throws DadosUsuarioInvalidoException, 
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException {
        
        usuario.consistirDados();
        usuario.alterarUsuario();
    }
    /**
     * Dado um usuário do sistema
     * quando selecionar excluir conta
     * então o sistema deve excluir a conta do usuário
     * e retornar á pagina inicial
     * @param usuario
     * @throws DadosUsuarioInvalidoException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws UsuarioInvalidoException 
     */
    public void excluirUsuario(Usuario usuario) throws DadosUsuarioInvalidoException, 
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException {
        usuario.excluirUsuario();
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
     * Método para remover todas as amizades do usuário que apagou a conta
     * @param idUsuario id do usuário que está apagando a conta
     */
    public void removerTodasAmizades(int idUsuario) {
        this.usuario.removerTodasAmizades(idUsuario);
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
                //envia o email do destinatario para o metodo enviar o convite
                this.usuario.enviarConviteEmail(o.toLowerCase(), path);
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

    public void verificarConvite(String email) {
        usuario.verificarConvite(email);
    }
    
    /**
     * Envia o link de redefinição de senha para o usuário
     * @param destinatario O email do usuário que está redefinindo a senha
     * @throws EmailException Caso ocorra erro no envio do email
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja encontrado
     * @throws NoSuchAlgorithmException Caso ocorra um erro na criação do hash
     * @throws UnsupportedEncodingException Caso ocorra um erro na criação do hash
     */
    public void recuperarConta(String destinatario, String path) throws EmailException, 
            DadosUsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {                        
        RecuperarConta recuperar = new RecuperarConta();
        
        this.usuario = Usuario.verificarExistenciaUsuario(destinatario);
        recuperar.setUsuario(this.usuario);
        String senhaHash = Calendar.getInstance().getTime().toString() + "witc" + Arrays.toString(destinatario.getBytes());
        recuperar.setHashRecuperacaoSenha(Usuario.criarHashSenha(senhaHash));                
        recuperar.EnviarEmailRecuperacao(path);        
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
    
    /** 
     * @param usuario 
     */
    public void criarPerfilPadrao(Usuario usuario){
        this.perfil.setUsuario(usuario);
        this.perfil.setPseudonimo(usuario.getNome());
        this.perfil.setTipoPerfil(this.tipoPerfil);
        //SB24 Setar perfil padrão
        this.perfil.setPerfilPadrao(true);
        perfilDAO.salvarPerfil(this.perfil);
    }

    /**
     * @param tipoPerfil 
     * @throws TipoPerfilException 
     */
    public void cadastrarTipoPerfil(TipoPerfil tipoPerfil) throws TipoPerfilException{
        tipoPerfil.cadastrarTipoPerfil(); 
    }
    
    /**
     * Retorna a lista de tipo de perfil
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfil() {
        return this.tipoPerfil.listarTipoPerfil();
    }
    
    /**
     * Retorna a lista de tipo de perfil em que o usuário não possui
     * @param idUsuario
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfilPossiveis(int idUsuario) {
        return this.tipoPerfil.listarTipoPerfilPossiveis(idUsuario);
    }
    
    /**
     * Cadastra um tipo de texto
     * @param tipoTexto
     * @throws TipoTextoException 
     */
    public void cadastrarTipoTexto(TipoTexto tipoTexto) throws TipoTextoException {
        this.tipoTexto.salvarTipoTexto(tipoTexto);
    }
    
    /**
     * Retorna os dados de um tipo de texto
     * @param id
     * @return 
     */
    public TipoTexto carregarTipoTexto(int id) {
        return this.tipoTexto.carregarTipoTexto(id);
    }
    
    /**
     * Método para salvar os tipos de textos ao usuário
     * para este se identificar com vários tipos de texto
     * @param tiposTextoUsuario lista dos tipos de textos 
     * @param idUsuario usuario 
     */
    public void salvarTipoTextoUsuario(List <String> tiposTextoUsuario, int idUsuario){
        this.usuario.salvarTipoTextoUsuario(tiposTextoUsuario, idUsuario);
    }
    
    /**
     * Método que exclui um registro de um tipo de texto em que um usuario nao 
     * se identifica mais
     * @param idUsuario
     * @param idTipoTexto  
     */
    public void excluirTipoTextoUsuario(int idUsuario, int idTipoTexto){
        this.usuario.excluirTipoTextoUsuario(idUsuario, idTipoTexto);        
    }
    
    /**
     * Método para excluir todos os tipos de texto da ligação com usuario quando 
     * este excluir sua conta
     * @param idUsuario 
     */
    public void excluirTodosTipoTextoUsuario(int idUsuario){
        this.usuario.excluirTodosTipoTextoUsuario(idUsuario);
    }
    
    /**
     * Lista os tipos de texto
     * @return 
     * @throws br.com.witc.excessao.TipoTextoException 
     */
    public List<TipoTexto> listarTipoTexto() throws TipoTextoException {
        return this.tipoTexto.getLstTipoTexto();
    }
    
    /**
     * Envia à viewer uma mensagem com o status da operação
     *
     * @param sev A severidade da mensagem
     * @param msg A mensagem a ser apresentada
     */
    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    } 
    
    /**
     * Cria um novo perfil para o usuário
     * @param idTipo id Perfil escolhido pelo usuário
     * @param usuario id usuário logado
     */
    public void criarPerfilUsuario(int idTipo, Usuario usuario) {
        Perfil newPerfil = new Perfil();
        for (TipoPerfil tipo : this.listarTipoPerfil()) {
            if (idTipo == tipo.getId()) {
                newPerfil.setTipoPerfil(tipo);
                break;
            }
        }
        newPerfil.setPerfilPadrao(true);
        newPerfil.setPseudonimo(usuario.getNome());

        newPerfil.setAvaliacao(0f);

        newPerfil.setUsuario(usuario);
        Perfil oldPerfil = this.perfil.carregarPerfil(usuario);
        this.perfil.desativarPerfil(oldPerfil);
        this.perfil.criarPerfil(newPerfil);
    }
}
