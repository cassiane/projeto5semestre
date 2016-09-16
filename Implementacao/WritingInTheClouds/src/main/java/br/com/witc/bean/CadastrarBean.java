/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;


import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
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
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
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
    private String emailRecuperacaoSenha;
    private String senhaRedefinicao;
    private String hashRedefinicao;
    private String diaNascimento;
    private String mesNascimento;
    private String anoNascimento;
    private List<Usuario> amigos;
    private List<Usuario> sugestao;
    private List<Usuario> solicitacao;
    private Usuario convidarEmail;
    
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
     * @return the senhaRedefinicao
     */
    public String getSenhaRedefinicao() {
        return senhaRedefinicao;
    }

    /**
     * @param senhaRedefinicao the senhaRedefinicao to set
     */
    public void setSenhaRedefinicao(String senhaRedefinicao) {
        this.senhaRedefinicao = senhaRedefinicao;
    }    
    
    /**
     * @return the hashRedefinicao
     */
    public String getHashRedefinicao() {
        return hashRedefinicao;
    }

    /**
     * @param hashRedefinicao the hashRedefinicao to set
     */
    public void setHashRedefinicao(String hashRedefinicao) {
        this.hashRedefinicao = hashRedefinicao;
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
     * Seta o usuario deste bean com o usuario logado no sistema
     */
    public void setUsuarioLogado() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();
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
        List<Usuario> filteredUsuario = new ArrayList<>();
         
        for (int i = 0; i < this.sugestao.size(); i++) {
                //Theme skin = allThemes.get(i);
            Usuario pesquisando = this.sugestao.get(i);
                //if(skin.getName().toLowerCase().startsWith(query)) {
            if (pesquisando.getEmail().toLowerCase().startsWith(email)) {
                //filteredThemes.add(skin);
                filteredUsuario.add(pesquisando);
            }
        }
         
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
    /**.
     * Seta a data de nascimento
     * @throws ParseException 
     */
    public void setDataNascimento() throws ParseException {         
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");            
        String data = getDiaNascimento()+"/"+getMesNascimento()+"/"+getAnoNascimento();
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(data));

        this.usuario.setDataAniversario(c);            

    }
    
    /**
     * Seta a data de nascimento nas variaveis locais 
     */
    public void preencherDataNasc() {         
        try {
            this.diaNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getDay());
            this.mesNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getMonth());
            this.anoNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getYear());
        } catch (Exception e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }
        
    /**
     * Retorna a data de nascimento
     */    
    public void retornarDataNasc() {
        this.preencherDataNasc();        
    }
    
    /**
     * Cadastra um usuario no sistema
     * @return Uma string contendo a próxima página a ser enviada para o usuário    
     */
    public String cadastrarUsuario() {
        // Setar a data de nascimento no usuario
        try {
            if (!this.usuario.getEmail().equals(this.emailVerificado)) {
                throw new DadosUsuarioInvalidoException("Os emails informados não coincidem!");
            }
            setDataNascimento();                
            this.controlador.cadastrarUsuario(usuario);    
            this.controlador.criarPerfilPadrao(usuario);
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
            autenticarBean.setUsuario(this.usuario);            
            return "timeline";
        }catch(ParseException ex){
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Data de Nascimento inválida.");          
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash da senha!");            
        } catch(DadosUsuarioInvalidoException ex) {            
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());            
        } catch (UsuarioInvalidoException ex) {
            // Apaga os dados do formulario
            this.usuario = new Usuario();
            this.diaNascimento = null;
            this.mesNascimento = null;
            this.anoNascimento = null;
            this.emailVerificado = null;
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());  
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
    
    /**
     * Envia o link de redefinição de senha para o usuário
     * @return A próxima página a ser visualizada pelo usuário
     */
    public String recuperarConta() {
        try {
            this.controlador.recuperarConta(this.emailRecuperacaoSenha);
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, "Um email com instruções para redefinir sua senha foi enviado.");
            return "resultadoOper";
        } catch(DadosUsuarioInvalidoException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, e.getMessage());
        } catch(EmailException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Não foi possível enviar o email para redefinição de senha!");
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash para redefinição de senha!");                                
        } 
        return null;
    }
    
    /**
     * Redefine a senha do usuário
     * @return A próxima página a ser visualizada pelo usuário
     */
    public String redefinirSenha() {
        if (!this.usuario.getSenha().equals(this.senhaRedefinicao)) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "As senhas informadas não coicidem!");
            return null;
        }
        
        if (this.hashRedefinicao.length() != 64) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Link de redefinição inválido!");
            return null;
        }
               
        try {
            this.controlador.redefinirSenha(this.emailRecuperacaoSenha, this.hashRedefinicao, this.senhaRedefinicao);
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso");
            return "index.xhtml";
        } catch (DadosUsuarioInvalidoException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash para redefinição de senha!");
        } catch (LinkRecuperacaoInvalidoException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
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