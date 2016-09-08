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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
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
    private Usuario convidarEmail;
    
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
    
    public List<Usuario> getAmigos() {
        this.amigos = this.controlador.listarAmigos();
        return this.amigos;
    }
    
    public boolean isTemAmigos() {
        return !(this.amigos == null || this.amigos.isEmpty());
    }

    public List<Usuario> getSugestao() {
        this.sugestao = this.controlador.listarSugestao();
        return this.sugestao;
    }
    
    public boolean isTemSugestao() {
        return !(this.sugestao == null || this.sugestao.isEmpty());
    }
    
    public List<Usuario> getSolicitacao() {
        this.solicitacao = this.controlador.listarSolicitacao();
        return solicitacao;
    }

    public boolean isTemSolicitacao() {
        return !(this.solicitacao == null || this.solicitacao.isEmpty());
    }

    public Usuario getConvidarEmail() {
        return convidarEmail;
    }

    public void setConvidarEmail(Usuario convidarEmail) {
        this.convidarEmail = convidarEmail;
    }

    public List<Usuario> completeEmail(String email) {
        //List<Theme> allThemes = service.getThemes();
        //List<Theme> filteredThemes = new ArrayList<Theme>();
        List<Usuario> filteredUsuario = new ArrayList<Usuario>();
         
        //for (int i = 0; i < allThemes.size(); i++) {
            //Theme skin = allThemes.get(i);
            //if(skin.getName().toLowerCase().startsWith(query)) {
                //filteredThemes.add(skin);
            //}
        //}
         
        //return filteredThemes;
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
        String data = getDiaNascimento()+"/"+getMesNascimento()+"/"+getAnoNascimento();
        Calendar c = Calendar.getInstance();;
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
            enviarMensagem(SEVERITY_ERROR, e.getMessage());            
        }
        return null;
    }

    public String listarAmigos() {
        this.controlador.usuarioLogado(this.usuario);
        return "listarAmigos";
    }
    
    public void solicitarAmizade(int idSugestao) {
        this.controlador.solicitarAmizade(idSugestao);
    }
    
    public void aceitarAmizade(int idAceitar) {
        this.controlador.aceitarAmizade(idAceitar);
    }
    
    public void removerAmizade(int idAmizade) {
        this.controlador.removerAmizade(idAmizade);
    }
    
    public String editarLivro(){
        return "editarLivro";
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
