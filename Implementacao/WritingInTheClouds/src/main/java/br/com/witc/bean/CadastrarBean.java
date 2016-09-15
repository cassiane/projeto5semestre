/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author marcelo.lima
 */
@ManagedBean
@SessionScoped
public class CadastrarBean {

    private final ControladorCadastro controlador;
    private Usuario usuario;
    private String emailVerificado;
    private String diaNascimento;
    private String mesNascimento;
    private String anoNascimento;
    private List<Usuario> amigos;
    private List<Usuario> sugestao;
    private List<Usuario> solicitacao;
    private List<Usuario> usuarios;
    private String convidarEmail;

    public CadastrarBean() {
        this.controlador = new ControladorCadastro(null);

        this.usuario = new Usuario();
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
     * @return the emailVerificado
     */
    public String getEmailVerificado() {
        return emailVerificado;
    }

    /**
     * @param emailVerificado the emailVerificado to set
     */
    public void setEmailVerificado(String emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    /**
     * @return the diaNascimento
     */
    public String getDiaNascimento() {
        return diaNascimento;
    }

    /**
     * @param diaNascimento the diaNascimento to set
     */
    public void setDiaNascimento(String diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    /**
     * @return the mesNascimento
     */
    public String getMesNascimento() {
        return mesNascimento;
    }

    /**
     * @param mesNascimento the mesNascimento to set
     */
    public void setMesNascimento(String mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    /**
     * @return the anoNascimento
     */
    public String getAnoNascimento() {
        return anoNascimento;
    }

    /**
     * @param anoNascimento the anoNascimento to set
     */
    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public StreamedContent getFoto(Usuario usufoto) {
        return this.controlador.getAmigosFoto(usufoto);
    }

    /**
     * Busca e atualiza a lista de amigos
     * @return Lista de amigos
     */
    public List<Usuario> getAmigos() {
        this.amigos = this.controlador.listarAmigos();
        return this.amigos;
    }

    /**
     * Verifica se o usuario possui amigos
     * @return Se tem amigos
     */
    public boolean isTemAmigos() {
        return !(this.amigos == null || this.amigos.isEmpty());
    }

    /**
     * Busca e atualiza a lista de sugestão
     * @return Lista de sugestão
     */
    public List<Usuario> getSugestao() {
        this.sugestao = this.controlador.listarSugestao();
        return this.sugestao;
    }

    /**
     * Verifica se o sistema possui sugestão de amigos
     * @return Se tem sugestão de amigos
     */
    public boolean isTemSugestao() {
        return !(this.sugestao == null || this.sugestao.isEmpty());
    }

    /**
     * Busca e atualiza a lista de solicitação
     * @return Lista de convites recebidos
     */
    public List<Usuario> getSolicitacao() {
        this.solicitacao = this.controlador.listarSolicitacao();
        return solicitacao;
    }

    /**
     * Metodo para verificar se existe solicitação
     * @return Se tem solicitação
     */
    public boolean isTemSolicitacao() {
        return !(this.solicitacao == null || this.solicitacao.isEmpty());
    }

    /**
     * Busca a lista de usuarios do sistema
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @return Email do convidado para ser amigo
     */
    public String getConvidarEmail() {
        return convidarEmail;
    }

    /**
     * @param convidarEmail Email de quem vai ser solicitado como amigo
     */
    public void setConvidarEmail(String convidarEmail) {
        this.convidarEmail = convidarEmail;
    }

    /**
     * Metodo utilizado no autocomplete para completar automaticamente o email
     * @param email Digitado no autocomplete
     * @return Lista de usuarios com o possivel email
     */
    public List<Usuario> completeEmail(String email) {
        List<Usuario> filteredUsuario = new ArrayList<Usuario>();
        for (Usuario pesquisando : this.listarUsuarios()) {
            // Verificar se o email digitado é parecido com os cadastrados no sistema
            if (pesquisando.getEmail().toLowerCase().startsWith(email.toLowerCase())) {
                // Adiciona os email se forem parecidos
                filteredUsuario.add(pesquisando);
            }
        }
        // Verifica se possui usuario adicionado para retorno
        if (filteredUsuario.size() == 0 || filteredUsuario.isEmpty()) {
            // Cria um novo usuario vazio e seta o email com o digitado pelo usuario
            Usuario filuser = new Usuario();
            filuser.setEmail(email);
            filteredUsuario.add(filuser);
        }
        return filteredUsuario;
    }

    /**
     * @return the anoAtual
     */
    public String getAnoAtual() {
        Calendar now = Calendar.getInstance();
        return String.valueOf(now.get(Calendar.YEAR));
    }

    /**
     * @return the anoInicial
     */
    public String getAnoInicial() {
        int anoAtual = Integer.parseInt(this.getAnoAtual());
        return String.valueOf(anoAtual - 80);

    }

    public void setDataNascimento() throws ParseException {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String data = getDiaNascimento() + "/" + getMesNascimento() + "/" + getAnoNascimento();
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(data));

        this.usuario.setDataAniversario(c);

    }

    /**
     * Cadastra um usuario no sistema
     *
     * @return Uma string contendo a próxima página a ser enviada para o usuário
     *
     * @throws br.com.witc.excessao.UsuarioInvalidoException
     */
    public String cadastrarUsuario() throws UsuarioInvalidoException {
        // Setar a data de nascimento no usuario
        try {
            if (!this.usuario.getEmail().equals(this.emailVerificado)) {
                throw new UsuarioInvalidoException("Os emails informados não coicidem!");
            }
            setDataNascimento();
            this.controlador.cadastrarUsuario(usuario);
            return "timeline";
        } catch (ParseException ex) {
            enviarMensagem(SEVERITY_ERROR, "Data de Nascimento inválida.");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(SEVERITY_ERROR, "Problemas na geração do hash da senha!");
        } catch (UsuarioInvalidoException e) {
            enviarMensagem(SEVERITY_ERROR, e.getMessage());
        }
        return null;
    }

    /**
     * Prepara as variaveis para a view de amigos
     * @return Pagina de manutenção de amigos
     */
    public String listarAmigos() {
        this.controlador.usuarioLogado(this.usuario);
        this.usuarios = this.listarUsuarios();
        return "listarAmigos";
    }

    /**
     * Metodo para solicitar a amizade para outro usuario
     * @param idSugestao Identificação do usuario que deseja-se tornar amigo
     */
    public void solicitarAmizade(int idSugestao) {
        this.controlador.solicitarAmizade(idSugestao);
    }

    /**
     * Metodo para confirmar a amizade de dois usuarios
     * @param idAceitar Identificador do solicitante da amizade
     */
    public void aceitarAmizade(int idAceitar) {
        this.controlador.aceitarAmizade(idAceitar);
    }

    /**
     * Metodo para negar a amizade do usuario
     * @param idAmizade Identificador do solicitante da amizade
     */
    public void removerAmizade(int idAmizade) {
        this.controlador.removerAmizade(idAmizade);
    }

    public String editarLivro() {
        return "editarLivro";
    }

    /**
     * Metodo para capiturar o email e a url para enviar o convite
     */
    public void enviarConvite() {
        try {
            // Capitura a url do sistema
            String path = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
            // Altera a variavel para excluir o restante da url
            path = path.replaceFirst("/faces(.*)", "");
            // Envia o email digitado e a URL para o metodo que envia o email
            this.controlador.enviarConvite(this.getConvidarEmail(), path);
        } catch (EmailException e) {
            enviarMensagem(SEVERITY_ERROR, "Erro ao enviar o convite, tente novamente!");
        }
    }

    /**
     * Metodo para carregar os usuarios do sistema
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> listarUsuarios() {
        return this.controlador.listarUsuarios();
    }
    
    /**
     * Envia à viewer uma mensagem com o status da operação
     *
     * @param sev A severidade da mensagem
     * @param msg A mensagem a ser apresentada
     */
    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
}
