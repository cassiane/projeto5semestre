/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;


import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.mail.MessagingException;
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
    private String emailRecuperacaoSenha;
    private String diaNascimento;
    private String mesNascimento;
    private String anoNascimento;
    private List<Usuario> amigos;
    private List<Usuario> sugestao;
    private List<Usuario> solicitacao;
    
    public CadastrarBean() {
        this.controlador = new ControladorCadastro();

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
     * @return the emailRecuperacaoSenha
     */
    public String getEmailRecuperacaoSenha() {
        return emailRecuperacaoSenha;
    }

    /**
     * @param emailRecuperacaoSenha the emailRecuperacaoSenha to set
     */
    public void setEmailRecuperacaoSenha(String emailRecuperacaoSenha) {
        this.emailRecuperacaoSenha = emailRecuperacaoSenha;
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
    
    public List<Usuario> getAmigos() throws UsuarioInvalidoException {
        //System.out.println("GetAmigos: " + this.amigos.size());
        return this.amigos;
    }
    
    private void setAmigos() throws UsuarioInvalidoException {
        this.amigos = null;
        try {
            this.amigos = this.controlador.listarAmigos();
        } catch (UsuarioInvalidoException | NullPointerException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public boolean isTemAmigos() {
        return this.amigos.isEmpty() || this.amigos == null;
    }

    public List<Usuario> getSugestao() throws UsuarioInvalidoException{
        //System.out.println("GetSugestao: " + this.sugestao.size());
        return this.sugestao;
    }
    
    private void setSugestao() throws UsuarioInvalidoException {
        this.sugestao = null;
        try {
            this.sugestao = this.controlador.listarSugestao();
        } catch (UsuarioInvalidoException | NullPointerException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public boolean isTemSugestao() {
        return this.sugestao.isEmpty() || this.sugestao == null;
    }
    
    public List<Usuario> getSolicitacao() {
        return solicitacao;
    }

    private void setSolicitacao() throws UsuarioInvalidoException{
        this.solicitacao = null;
        try {
            this.solicitacao = this.controlador.listarSolicitacao();
        } catch (NullPointerException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public boolean isTemSolicitacao() {
        return this.solicitacao.isEmpty() || this.solicitacao == null;
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
        String data = getDiaNascimento()+"/"+getMesNascimento()+"/"+getAnoNascimento();
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(data));

        this.usuario.setDataAniversario(c);            

    }
    
    /**
     * Cadastra um usuario no sistema
     * @return Uma string contendo a próxima página a ser enviada para o usuário

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
        }catch(ParseException ex){
            enviarMensagem(SEVERITY_ERROR, "Data de Nascimento inválida.");          
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(SEVERITY_ERROR, "Problemas na geração do hash da senha!");            
        } catch(UsuarioInvalidoException e) {
            // Apaga os dados do formulario
            this.usuario = null;
            enviarMensagem(SEVERITY_ERROR, e.getMessage());            
        }
        return null;
    }

    public String listarAmigos() {
        this.controlador.usuarioLogado(this.usuario);
        try {
            this.setSolicitacao();
            this.setAmigos();
            this.setSugestao();
        } catch (UsuarioInvalidoException | IllegalArgumentException | NullPointerException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
        return "listarAmigos";
    }
    
    public void solicitarAmizade(int idSugestao) {
        this.controlador.solicitarAmizade(idSugestao);
        try {
            //this.controlador.usuarioLogado(this.usuario);
            this.setSugestao();
        } catch (UsuarioInvalidoException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public void aceitarAmizade(int idAceitar) {
        this.controlador.aceitarAmizade(idAceitar);
        try {
            this.setSolicitacao();
        } catch (UsuarioInvalidoException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public void removerAmizade(int idAmizade) {
        this.controlador.removerAmizade(idAmizade);
        try {
            this.setSolicitacao();
        } catch (UsuarioInvalidoException ex) {
            enviarMensagem(SEVERITY_ERROR, ex.getMessage());
        }
    }
    
    public String recuperarSenha() {
        try {
            this.controlador.recuperarSenha(this.emailRecuperacaoSenha);
            return "paginaOkEnvio";
        } catch(DadosUsuarioInvalidoException e) {
            enviarMensagem(SEVERITY_ERROR, e.getMessage());
        } catch(MessagingException e) {
            enviarMensagem(SEVERITY_ERROR, "Não foi possível enviar o email para redefinição de senha!");
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(SEVERITY_ERROR, "Problemas na geração do hash para redefinição de senha!");                                
        }
        return null;
    }
    
    /**
     * Envia à viewer uma mensagem com o status da operação
     * @param sev A severidade da mensagem
     * @param msg A mensagem a ser apresentada
     */
    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = getCurrentInstance();        
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
}
