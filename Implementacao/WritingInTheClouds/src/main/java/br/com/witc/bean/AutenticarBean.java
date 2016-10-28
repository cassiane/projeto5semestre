/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.LoginInvalidoException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.witc.modelo.ControladorAutenticacao;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 *
 * @author marcelo.lima
 */
@ManagedBean
@SessionScoped
public class AutenticarBean {
    private final ControladorAutenticacao controlador;
    private String email;
    private String senha;
    private List<Perfil> perfisUsuario;

    public AutenticarBean() {
        this.controlador = new ControladorAutenticacao();              
    }    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return perfisUsuario
     */
    public List<Perfil> getPerfisUsuario() {
        return perfisUsuario;
    }

    /**
     * @param perfisUsuario the perfisUsuario to set
     */
    public void setPerfisUsuario(List<Perfil> perfisUsuario) {
        this.perfisUsuario = perfisUsuario;
    }

    /**
     * Verifica se o usuario possui mais de um perfil
     * @return Verdadeiro se tiver mais de um perfil
     */
    public boolean isPerfis() {
        this.perfisUsuario = this.controlador.listarPerfis();
        if (this.perfisUsuario.size() > 1) {
            return true;
        }
        return false;
    }
    
    /**     
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.controlador.setUsuario(usuario);
    }
    
    /**     
     * @return O email do usuário logado no sistema
     */
    public String getNomeCompletoUsuario() {
        return this.controlador.getNomeCompletoUsuario();
    }
    
    /**
     * @return O status do usuário logado no sistema
     */
    public String getStatusUsuario() {
        return this.controlador.getStatusUsuario();
    }
    
    /**
     * Acessa o controle para setar o amigo
     * @param id Codigo do amigo
     */
    public void setAmigoUsuario(int id) {
        this.controlador.setAmigoUsuario(id);
    }
    
    /**
     * Acessa o controle para zerar o amigo
     */
    public void setAmigoUsuario() {
        this.controlador.setAmigoUsuario();
    }
    
    /**
     * Acessa o controle para verificar se é para mostrar o usuario ou o amigo
     * @return False se for amigo
     */
    public boolean isAmigo() {
        return this.controlador.getAmigoUsuario().getId() == 0;
    }

    /**
     * Retornando o id do usuário amigo
     * @return Codigo do amigo
     */
    public int getIdAmigoUsuario() {
        if (this.isAmigo()) {
            return 0;
        } else {
            return this.controlador.getAmigoUsuario().getId();
        }
    }
    
    /**     
     * @return O id do perfil
     */
    public int getIdPerfil() {
        /*
        Aqui devemos fazer o tratamento para verificar qual pagina estamos visitando.
        Poderia, por exemplo, ser a pagina de um amigo, ou a propria pagina do usuario.        
        */
        this.perfisUsuario = this.controlador.listarPerfis();
        return this.perfisUsuario.get(0).getId();
    }
    
    /**     
     * @return A avaliacao do perfil
     */
    public float getAvaliacaoPerfil() {
        /*
        Aqui devemos fazer o tratamento para verificar qual pagina estamos visitando.
        Poderia, por exemplo, ser a pagina de um amigo, ou a propria pagina do usuario.        
        */
        this.perfisUsuario = this.controlador.listarPerfis();
        return this.perfisUsuario.get(0).getAvaliacao();
    }
    
    /**
     * Acessa o controle para atualizar o status do usuario
     * @param status Codigo do status (Enum do banco)
     */
    public void atualizarStatusUsuario(int status) {
        this.controlador.atualizarStatusUsuario(status);
    }
    
    /**
     * Acessa o controle para realizar a troca de perfil
     */
    public void trocarPerfilUsuario(Perfil auxPerfil) {
        if (auxPerfil != null) {
            this.controlador.trocarPerfilUsuario(auxPerfil);
            // Recarregar o perfil logado
            this.controlador.retornarPerfilUsuarioLogado();
        }
    }

    /**
     * Verificar se o perfil passado eh nulo ou igual ao logado
     * @param perfil a ser verificado
     * @return verdadeiro se for o mesmo perfil
     */
    public boolean perfilIgual(Perfil perfil) {
        if (perfil != null) {
            return this.controlador.perfilIgual(perfil);
        } else {
            return false;
        }
    }

    /**
     * Verifica se o perfil logado é editor
     * @return Verdadeiro se Editor
     */
    public boolean isPerfilEditor() {
        Perfil editor = new Perfil();
        editor = editor.carregarPerfil(this.usuarioLogado());
        return editor.getTipoPerfil().getId() == 1;
    }

    /**
     * Verifica se o perfil logado é revisor
     * @return Verdadeiro se Editor
     */
    public boolean isPerfilRevisor() {
        Perfil revisor = new Perfil();
        revisor = revisor.carregarPerfil(this.usuarioLogado());
        return revisor.getTipoPerfil().getId() == 2;
    }
    
    /**     
     * @return A quantidade de amigos do usuário logado no sistema
     */
    public String getNumeroAmigosUsuarioLogado() {
        int numAmigos = this.controlador.getNumeroAmigosUsuarioLogado();
        if (numAmigos == 1) {
            return "1 amigo";
        }
        return String.valueOf(numAmigos) + " amigos";
    }        
    
    /**
     * Unica forma que achei para buscar o usuario
     * @return Retorna o usuario logado
     */
    public Usuario usuarioLogado() {
        return this.controlador.getUsuario();
    }
    
    /**
     * Autentica um usuário no sistema
     * @return A página a ser visualizada pelo usuário após o login     
     */
    public String efetuarLogin() {        
        try {
            this.controlador.efetuarLogin(this.email, this.senha);
            this.controlador.retornarPerfilUsuarioLogado();
            return "timeline";
        } catch(LoginInvalidoException e) {
            enviarMensagem(SEVERITY_ERROR, e.getMessage());            
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(SEVERITY_ERROR, "Problemas na geração do hash da senha!");     
        }
        return "index";
    }   
    
    /**
     * Verifica se o usuário logado é administrador 
     * @return 
     */
    public boolean verificarAdministrador(){
        try {
            return this.controlador.getTipoPerfil().toLowerCase().contains("admin");        
        } catch(NullPointerException ex) {
            // cadastro de usuario
            return false;
        }
    }
    
    /**
     * Realiza o logout do usuário
     * @return A próxima página a ser visualizada pelo usuário após o logout
     */
    public String efetuarLogoff() {
        getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";        
    }    
    
    /**
     * Recebe o id e a nota dada pelo usuário ao livro.  
     */
    public void userRating() {        
        try {
            String[] avaliacao = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("rating").split("-");            
            int idPerfil = Integer.parseInt(avaliacao[0]);
            float rating = Float.parseFloat(avaliacao[1]);
                    
            Perfil tmpPerfil = this.controlador.carregarPerfilPorId(idPerfil);
            
            int qtdAvaliacoes = tmpPerfil.getQtdAvaliacoes() + 1;
            float somaAvaliacoes = tmpPerfil.getSomaAvaliacoes() + rating;
            float novaAvaliacao = somaAvaliacoes / qtdAvaliacoes;
            
            tmpPerfil.setAvaliacao(novaAvaliacao);
            tmpPerfil.setQtdAvaliacoes(qtdAvaliacoes);
            tmpPerfil.setSomaAvaliacoes(somaAvaliacoes);
            
            this.controlador.salvarPerfil(tmpPerfil);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | 
                NullPointerException | PatternSyntaxException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Erro ao qualificar o usuário. Seu voto não foi computado!");
        }        
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
    public void setarPerfilUsuario(){
        this.controlador.retornarPerfilUsuarioLogado();
    }

}
    
