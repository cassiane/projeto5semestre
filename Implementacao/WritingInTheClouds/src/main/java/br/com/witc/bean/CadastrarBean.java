/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.excessao.LivroException;
import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.excessao.TipoTextoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.Desafios;
import br.com.witc.modelo.DesafiosPalavras;
import br.com.witc.modelo.DesafiosUsuarios;
import br.com.witc.modelo.HistoriasDesafios;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Notificacoes;
import br.com.witc.modelo.Perfil;
import java.io.InputStream;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.TipoPerfilDAO;
import br.com.witc.persistencia.TipoTextoDAO;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import java.io.*;
import java.util.regex.PatternSyntaxException;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

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
    private List<Usuario> usuarios;
    private List<String> selectedTiposTextoUsuario;
    private String tipotextoTag;
    private String convidarEmail;
    private StreamedContent imagemEnviada = new DefaultStreamedContent();
    private String imagemTemporaria;
    private CroppedImage croppedImage;
    private boolean exibeBotao = true;
    private UploadedFile file;
    private UploadedFile wallpaperFile;
    private TipoPerfil tipoPerfil;
    private TipoPerfilDAO tipoPerfildao;
    public TipoTexto tipoTexto;
    public TipoTextoDAO tipoTextoDAO;    
    List<TipoTexto> tiposTexto = new ArrayList<>();
    private int tipoPerfilNovo;
    private final Desafios desafio;
    private DesafiosPalavras desafioPalavras;
    private List<String> listaPalavras;
    String palavra;
    private HistoriasDesafios historiasDesafios;
    private List<String> palavrasDoDesafio;
    private List<Notificacoes> listaNotificacoes;
    private final Notificacoes notificacao;
    DesafiosUsuarios desafiosUsuarios;
    private HistoriasDesafios historiasDesafiosCarregado; 
    public int idDesafiosUsuarios;
    AutenticarBean autenticarBean;
            
    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
    
    

    public static final String CAMINHO_FOTO_DEFAULT = "/resources/imagens/semFoto.png";
    public static final String CAMINHO_FOTO_CAPA_DEFAULT = "/resources/imagens/semWallpaper.png";
    
    public CadastrarBean() {
        this.listaPalavras = new ArrayList<>();
        this.controlador = new ControladorCadastro();
        this.usuario = new Usuario();
        this.tipoPerfil = new TipoPerfil();
        this.tipoPerfildao = new TipoPerfilDAO();
        this.tipoTexto = new TipoTexto();
        this.tipoTextoDAO = new TipoTextoDAO();
        this.selectedTiposTextoUsuario = new ArrayList<>();
        this.desafioPalavras = new DesafiosPalavras();
        this.desafio = new Desafios();
        this.historiasDesafios = new HistoriasDesafios();
        this.listaNotificacoes = new ArrayList<Notificacoes>();
        this.notificacao = new Notificacoes();        
        this.desafiosUsuarios = new DesafiosUsuarios();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "autenticarBean");
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
     * @param anoNascimento the anoNascimento to set
     */
    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    
    /**
     * @return 
     */
    public StreamedContent getImagemEnviada() {
        return imagemEnviada;
    }
    /**
     * @param imagemEnviada 
     */
    public void setImagemEnviada(StreamedContent imagemEnviada) {
        this.imagemEnviada = imagemEnviada;
    }
    /**
     * @return 
     */
    public String getImagemTemporaria() {
        return imagemTemporaria;
    }
    /**
     * @param imagemTemporaria 
     */
    public void setImagemTemporaria(String imagemTemporaria) {
        this.imagemTemporaria = imagemTemporaria;
    }
    /**
     * @return 
     */
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }
    /**
     * @param croppedImage 
     */
    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
    /**
     * @return 
     */
    public boolean isExibeBotao() {
        return exibeBotao;
    }
    /**
     * @param exibeBotao 
     */
    public void setExibeBotao(boolean exibeBotao) {
        this.exibeBotao = exibeBotao;
    }
    
    public UploadedFile getFile() {
        return this.file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    /**
     * @return the wallpaperFile
     */
    public UploadedFile getWallpaperFile() {
        return wallpaperFile;
    }

    /**
     * @param wallpaperFile the wallpaperFile to set
     */
    public void setWallpaperFile(UploadedFile wallpaperFile) {
        this.wallpaperFile = wallpaperFile;
    }
    
    public StreamedContent getFoto() {                 
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        
        if (this.usuario.getFoto() == null) {
            return carregarFotoDefault(false);
        }        
        InputStream is = new ByteArrayInputStream(this.usuario.getFoto());
        StreamedContent image = new DefaultStreamedContent(is);        
        return image;
    }
    
    public StreamedContent getFotoCapa() {                         
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        
        int idPerfil = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPerfil"));
        byte[] capa = this.controlador.carregarUsuarioPorIdPerfil(idPerfil).getFotoCapa();
        
        if (capa == null) {
            return carregarFotoDefault(true);
        }        
        InputStream is = new ByteArrayInputStream(capa);
        StreamedContent image = new DefaultStreamedContent(is);        
        return image;
    }
    
    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }
    
    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public TipoPerfilDAO getTipoPerfildao() {
        return tipoPerfildao;
    }

    public void setTipoPerfildao(TipoPerfilDAO tipoPerfildao) {
        this.tipoPerfildao = tipoPerfildao;
    }
    
    /**
     * @return the tipoTexto
     */
    public TipoTexto getTipoTexto() {
        return tipoTexto;
    }

    /**
     * @param tipoTexto the tipoTexto to set
     */
    public void setTipoTexto(TipoTexto tipoTexto) {
        this.tipoTexto = tipoTexto;
    }

    /**
     * @return the tipoTextoDAO
     */
    public TipoTextoDAO getTipoTextoDAO() {
        return tipoTextoDAO;
    }

    /**
     * @param tipoTextoDAO the tipoTextoDAO to set
     */
    public void setTipoTextoDAO(TipoTextoDAO tipoTextoDAO) {
        this.tipoTextoDAO = tipoTextoDAO;
    }
           
    /**
     * Busca e atualiza a lista de amigos
     *
     * @return Lista de amigos
     */
    public List<Usuario> getAmigos() {
        this.amigos = this.controlador.listarAmigos();
        return this.amigos;
    }

    /**
     * Verifica se o usuario possui amigos
     *
     * @return Se tem amigos
     */
    public boolean isTemAmigos() {
        return !(this.amigos == null || this.amigos.isEmpty());
    }

    /**
     * Busca e atualiza a lista de sugestão
     *
     * @return Lista de sugestão
     */
    public List<Usuario> getSugestao() {
        this.sugestao = this.controlador.listarSugestao();
        return this.sugestao;
    }

    /**
     * Verifica se o sistema possui sugestão de amigos
     *
     * @return Se tem sugestão de amigos
     */
    public boolean isTemSugestao() {
        return !(this.sugestao == null || this.sugestao.isEmpty());
    }

    /**
     * Busca e atualiza a lista de solicitação
     *
     * @return Lista de convites recebidos
     */
    public List<Usuario> getSolicitacao() {
        this.solicitacao = this.controlador.listarSolicitacao();
        return solicitacao;
    }

    /**
     * Metodo para verificar se existe solicitação
     *
     * @return Se tem solicitação
     */
    public boolean isTemSolicitacao() {
        return !(this.solicitacao == null || this.solicitacao.isEmpty());
    }
    
    /**
     * Busca a lista de usuarios do sistema
     *
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> getUsuarios() {
        this.usuarios = this.controlador.listarUsuarios();
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
     * @return the tipoPerfilNovo
     */
    public int getTipoPerfilNovo() {
        return tipoPerfilNovo;
    }

    /**
     * @param tipoPerfilNovo the tipoPerfilNovo to set
     */
    public void setTipoPerfilNovo(int tipoPerfilNovo) {
        this.tipoPerfilNovo = tipoPerfilNovo;
    }
    
    /**
     * @return the desafio
     */
    public Desafios getDesafio() {
        return desafio;
    }
    
    /**
     * @return the desafioPalavras
     */
    public DesafiosPalavras getDesafioPalavras() {
        return desafioPalavras;
    }

    /**
     * @param desafioPalavras the desafioPalavras to set
     */
    public void setDesafioPalavras(DesafiosPalavras desafioPalavras) {
        this.desafioPalavras = desafioPalavras;
    }

    /**
     * @return the listaPalavras
     */
    public List<String> getListaPalavras() {
        return listaPalavras;
    }

    /**
     * @param listaPalavras the listaPalavras to set
     */
    public void setListaPalavras(List<String> listaPalavras) {
        this.listaPalavras = listaPalavras;
    }
    
    /**
     * @return the palavrasDoDesafio
     * @throws java.lang.Exception
     */
    public List<String> getPalavrasDoDesafio() throws Exception {
        return listarPalavrasDoDesafio();
    }

    /**
     * @param palavrasDoDesafio the palavrasDoDesafio to set
     */
    public void setPalavrasDoDesafio(List<String> palavrasDoDesafio) {
        this.palavrasDoDesafio = palavrasDoDesafio;
    }
    
    /**
     * @return the listaNotificacoes
     */
    public List<Notificacoes> getListaNotificacoes() {
        return listaNotificacoes = this.controlador.listarNotificacoes(this.usuario.getId());
    }

    /**
     * @param listaNotificacoes the listaNotificacoes to set
     */
    public void setListaNotificacoes(List<Notificacoes> listaNotificacoes) {
        this.listaNotificacoes = listaNotificacoes;
    }
    
    /**
     * @return the idDesafiosUsuarios
     */
    public int getIdDesafiosUsuarios() {
        return idDesafiosUsuarios;
    }

    /**
     * @param idDesafiosUsuarios the idDesafiosUsuarios to set
     */
    public void setIdDesafiosUsuarios(int idDesafiosUsuarios) {
        this.idDesafiosUsuarios = idDesafiosUsuarios;
    }
    
    public StreamedContent getFotos(Usuario user) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        
        try {
            if (user.getFoto() == null) {
                return carregarFotoDefault(false);
            }
            InputStream is = new ByteArrayInputStream(user.getFoto());
            StreamedContent image = new DefaultStreamedContent(is);
            return image;
        } catch(NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
    }
    
    public StreamedContent getFotoAmigo() {        
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        
        int idfoto = 0;
        try {
            idfoto = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userfoto"));
        } catch (NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
        
        if (this.autenticarBean.getPerfilSelecionadoAmigo() != null && 
            idfoto == this.autenticarBean.getPerfilSelecionadoAmigo().getUsuario().getId()) {
            return this.getFotos(this.autenticarBean.getPerfilSelecionadoAmigo().getUsuario());
        }                
        
        Usuario usu = new Usuario();
        for (Usuario us : this.amigos) {
            if (us.getId() == idfoto) {
                usu = us;
                break;
            }
        }
        
        return this.getFotos(usu);
    }
    
    public StreamedContent getFotoSugestao() {
        int idfoto = 0;
        try {
            idfoto = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userfoto"));
        } catch (NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
        Usuario usu = new Usuario();
        for (Usuario us : this.sugestao) {
            if (us.getId() == idfoto) {
                usu = us;
                break;
            }
        }
        return this.getFotos(usu);
    }
    
    public StreamedContent getFotoSolicitacao() {
        int idfoto = 0;
        try {
            idfoto = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userfoto"));
        } catch (NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
        Usuario usu = new Usuario();
        for (Usuario us : this.solicitacao) {
            if (us.getId() == idfoto) {
                usu = us;
                break;
            }
        }
        return this.getFotos(usu);
    }
    
    public StreamedContent getFotoConvidar() {
        int idfoto = 0;
        try {
            idfoto = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userfoto"));
        } catch (NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
        Usuario usu = new Usuario();
        for (Usuario us : this.usuarios) {
            if (us.getId() == idfoto) {
                usu = us;
                break;
            }
        }
        return this.getFotos(usu);
    }
    
    /**
     * 
     * @return 
     */
    public StreamedContent getFotoNotificacao() {
        int idfoto = 0;
        try {
            idfoto = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userfoto"));
        } catch (NumberFormatException ex) {
            return carregarFotoDefault(false);
        }
        Usuario usu = new Usuario();
        for (Notificacoes not : this.listaNotificacoes) {
            if(not.getRemetente().getId() == idfoto){
                usu = not.getRemetente();
                break;
            }
        }
        return this.getFotos(usu);
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

    /**
     * Seta o usuario deste bean com o usuario logado no sistema
     */
    public void setUsuarioLogado() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "autenticarBean");

        this.usuario = autenticarBean.usuarioLogado();
    }

    public void setDataNascimento() throws ParseException {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String data = getDiaNascimento() + "/" + getMesNascimento() + "/" + getAnoNascimento();        
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(data));
        this.usuario.setDataAniversario(c);
        
    }

    /**
     * Seta a data de nascimento nas variaveis locais
     */
    public void preencherDataNasc() {
        try {
            Calendar c; 
            c = this.usuario.getDataAniversario();             
            this.diaNascimento = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
            this.mesNascimento = Integer.toString(c.get(Calendar.MONTH)+1); 
            this.anoNascimento = Integer.toString(c.get(Calendar.YEAR));
            /*this.diaNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getDate());
            this.mesNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getMonth());
            this.anoNascimento = Integer.toString(this.usuario.getDataAniversario().getTime().getYear());*/
        } catch (Exception e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    /**
     * Retorna a data de nascimento
     */
    public void retornarDataNasc() {
        // Passei aqui e quiz atualizar o status
        this.atualizarStatusUsuario(1);
        this.preencherDataNasc();
    }
    
    /**
     * @return the historiasDesafiosCarregado
     */
    public HistoriasDesafios getHistoriasDesafiosCarregado() {
        //desafiosUsuarios.setId(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("desafiosUsuarios")));
        desafiosUsuarios.setId(this.idDesafiosUsuarios);
        return historiasDesafiosCarregado = this.controlador.carregarHistoriasDesafios(desafiosUsuarios);
    }
    
    /**
     * Usado no padrão interno para mostrar a mensagem sem registros
     * quando o usuário nao tiver notificações
     * @return 
     */
    public boolean listaVazia(){
        return this.listaNotificacoes.isEmpty();
    }
    
    /**
     * Cadastra um usuario no sistema
     *
     * @return Uma string contendo a próxima página a ser enviada para o usuário
     */
    public String cadastrarUsuario() {
        // Setar a data de nascimento no usuario
        try {
            if (!this.usuario.getEmail().equals(this.emailVerificado)) {
                throw new DadosUsuarioInvalidoException("Os emails informados não coincidem!");
            }

            setDataNascimento();
            this.usuario.setStatus("Pensando");
            this.usuario.setAtivo(true);
            this.controlador.cadastrarUsuario(this.usuario);
            setDataNascimento();                  
            this.controlador.criarPerfilPadrao(this.usuario);

            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
            autenticarBean.setUsuario(this.usuario);
            autenticarBean.setarPerfilUsuario();
            // Verifica se o novo usuario ja recebeu alguma solicitação de amizade
            this.controlador.verificarConvite(this.usuario.getEmail());
            return "timeline";
        } catch (ParseException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Data de Nascimento inválida.");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash da senha!");
        } catch (DadosUsuarioInvalidoException ex) {
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
    
    /**
     * Altera o usuário não utlizado o método de cadastro para 
     * não prejudicar a validação do email 
     * @return 
     */
    public String alterarUsuario() {
        InputStream inputStream = null;
        byte[] imgBytes = null;
        // Setar a data de nascimento no usuario
        try {            
            if ((this.file != null) && (!this.file.getFileName().isEmpty()))  {
                inputStream = this.file.getInputstream();                
                imgBytes = IOUtils.toByteArray(inputStream);
                this.usuario.setFoto(imgBytes);
            }                        
            
            if ((this.wallpaperFile != null) && (!this.wallpaperFile.getFileName().isEmpty()))  {
                inputStream = this.wallpaperFile.getInputstream();                
                imgBytes = IOUtils.toByteArray(inputStream);
                this.usuario.setFotoCapa(imgBytes);
            } 
            
            setDataNascimento();
            this.controlador.alterarUsuario(this.usuario);
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
            autenticarBean.setUsuario(this.usuario);
            // Verifica se o novo usuario ja recebeu alguma solicitação de amizade
            this.controlador.verificarConvite(this.usuario.getEmail());            
            return "timeline";
        } catch (ParseException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Data de Nascimento inválida.");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash da senha!");
        } catch (DadosUsuarioInvalidoException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (UsuarioInvalidoException ex) {
            // Apaga os dados do formulario
            this.usuario = new Usuario();
            this.diaNascimento = null;
            this.mesNascimento = null;
            this.anoNascimento = null;
            this.emailVerificado = null;
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (IOException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Problemas ao carregar a foto!");
        }
        return null;
    }
    public String excluirUsuario(){                
        try {
            this.usuario.setStatus("");
            this.usuario.setAtivo(false);
            this.controlador.excluirUsuario(this.usuario); 
            removerTodasAmizades(this.usuario.getId());            
        } catch (DadosUsuarioInvalidoException | NoSuchAlgorithmException | UnsupportedEncodingException | UsuarioInvalidoException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
        this.usuario = new Usuario();
        this.diaNascimento = null;
        this.mesNascimento = null;
        this.anoNascimento = null;
        this.emailVerificado = null;  
        getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";   
    }
    /**
     * Envia o link de redefinição de senha para o usuário
     *
     * @return A próxima página a ser visualizada pelo usuário
     */
    public String recuperarConta() {
        try {
            // Capitura a url do sistema
            String path = ((HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest()).getRequestURL().toString();
            // Altera a variavel para excluir o restante da url
            path = path.replaceFirst("/faces(.*)", "");
            this.controlador.recuperarConta(this.emailRecuperacaoSenha, path);
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, "Um email com instruções para redefinir sua senha foi enviado.");
            return "resultadoOper";
        } catch (DadosUsuarioInvalidoException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, e.getMessage());
        } catch (EmailException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Não foi possível enviar o email para redefinição de senha!");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash para redefinição de senha!");
        }
        return null;
    }

    /**
     * Redefine a senha do usuário
     *
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
        } catch (DadosUsuarioInvalidoException | LinkRecuperacaoInvalidoException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Problemas na geração do hash para redefinição de senha!");
        }

        return null;
    }

    /**
     * Prepara as variaveis para a view de amigos
     *
     * @return Pagina de manutenção de amigos
     */
    public String listarAmigos() {        
        int idPerfil;
        try {
            idPerfil = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPerfil"));
        } catch(NumberFormatException ex) {
            idPerfil = 0;
        }     
        
        if (idPerfil == 0) {
            this.controlador.usuarioLogado(this.usuario);
        } else {
            this.controlador.usuarioLogado(this.controlador.carregarUsuarioPorIdPerfil(idPerfil));
        }
        this.usuarios = this.listarUsuarios();
        return "listarAmigos";
    }

    /**
     * Metodo para carregar os usuarios do sistema
     *
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> listarUsuarios() {
        return this.controlador.listarUsuarios();
    }

    /**
     * Metodo utilizado no autocomplete para completar automaticamente o email
     *
     * @param email Digitado no autocomplete
     * @return Lista de usuarios com o possivel email
     */
    public List<Usuario> completeEmail(String email) {
        List<Usuario> filteredUsuario = new ArrayList<>();
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
     * Metodo para solicitar a amizade para outro usuario
     *
     * @param idSugestao Identificação do usuario que deseja-se tornar amigo
     */
    public void solicitarAmizade(int idSugestao) {
        // Status do usuario
        this.atualizarStatusUsuario(1);
        this.controlador.solicitarAmizade(idSugestao);
    }

    /**
     * Metodo para confirmar a amizade de dois usuarios
     *
     * @param idAceitar Identificador do solicitante da amizade
     */
    public void aceitarAmizade(int idAceitar) {
        // Status do usuario
        this.atualizarStatusUsuario(1);
        this.controlador.aceitarAmizade(idAceitar);
    }

    /**
     * Metodo para negar a amizade do usuario
     *
     * @param idAmizade Identificador do solicitante da amizade
     */
    public void removerAmizade(int idAmizade) {
        // Status do usuario
        this.atualizarStatusUsuario(1);
        this.controlador.removerAmizade(idAmizade);
    }
    
    /**
     * Exclui todas as amizades do usuário que está apagando a conta
     *
     * @param idUsuario id do usuario que esta apagando a conta
     */
    public void removerTodasAmizades(int idUsuario) {
        this.controlador.removerTodasAmizades(idUsuario);
    }

    /**
     * Metodo para capiturar o email e a url para enviar o convite
     */
    public void enviarConvite() {
        // Status do usuario
        this.atualizarStatusUsuario(1);
        try {
            // Capitura o email para convidar
            String email = this.getConvidarEmail();
            // Limpa o campo para a tela
            this.convidarEmail = null;
            // Capitura a url do sistema
            String path = ((HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest()).getRequestURL().toString();
            // Altera a variavel para excluir o restante da url
            path = path.replaceFirst("/faces(.*)", "");
            // Envia o email digitado e a URL para o metodo que envia o email
            this.controlador.enviarConvite(email, path);
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Email enviado com sucesso para "
                    + email + "!");
        } catch (EmailException e) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao enviar o convite, tente novamente mais tarde!");
        }
    }

    public String editarLivro() {
        return "editarLivro";
    }
    
    /**
     * Converte uma imagem para apresentar em um componente p:graphicImage     
     * @param pathFile O caminho do arquivo
     * @return Um objeto StreamedContent
     */
    public StreamedContent converterFoto(String pathFile) {        
        // Cria um objeto File com a foto do cliente        
        File imgFile = new File(pathFile);
        
        // Converte o arquivo em um array de bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] fotoCliente = null;
        try {            
            BufferedImage imagem = ImageIO.read(imgFile);
            ImageIO.write(imagem, "PNG", bos);
            bos.flush();  
            fotoCliente = bos.toByteArray();                
        } catch (IOException e) {            
        }        
        
        try {
            return new DefaultStreamedContent(new ByteArrayInputStream(fotoCliente));
        } catch(NullPointerException e) {
            // Nao foi possivel localizar nenhuma foto ...
            return new DefaultStreamedContent();
        }        
    }        
    
    /**
     * Converte uma imagem para apresentar em um componente p:graphicImage     
     * @return Um objeto StreamedContent
     */
    public StreamedContent carregarFotoDefault(boolean capa) {        
        File imgFile;
        if (!capa) {
            imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(CAMINHO_FOTO_DEFAULT));            
        } else {
            imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(CAMINHO_FOTO_CAPA_DEFAULT));
        }
        
        // Converte o arquivo em um array de bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] fotoCliente = null;
        try {            
            BufferedImage imagem = ImageIO.read(imgFile);
            ImageIO.write(imagem, "PNG", bos);
            bos.flush();  
            fotoCliente = bos.toByteArray();                
        } catch (IOException e) {            
        }        
        
        try {
            return new DefaultStreamedContent(new ByteArrayInputStream(fotoCliente));
        } catch(NullPointerException e) {
            // Nao foi possivel localizar nenhuma foto ...
            return new DefaultStreamedContent();
        }        
    }    
    
    /**
     * Cadastra um novo perfil
     * @return 
     */
    public String cadastrarTipoPerfil(){   
        try {
            this.controlador.cadastrarTipoPerfil(tipoPerfil);
            return "tiposPerfis"; 
        } catch (TipoPerfilException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR,"Não foi possível cadastrar este perfil - "+ex.getMessage());
        }
        return "timeline"; 
    }
    
    /**
     * Chamada para a tela de criação de um novo perfil 
     * @return 
     */
    public String novoPerfil(){
        this.tipoPerfil = new TipoPerfil();
        return "novoTipoPerfil"; 
    }
    
    /**
     * Retorna uma lista de perfis
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfil(){   
        return this.controlador.listarTipoPerfil();
    }
    
    /**
     * Retorna uma lista de perfis que o usuário não tem
     * @return 
     */
    public List<TipoPerfil> listarTipoPerfilPossiveis(){   
        return this.controlador.listarTipoPerfilPossiveis(this.usuario.getId());
    }
    
    /**
     * Retorna a tela de edição do perfil selecionado na lista
     * @param id
     * @return 
     */
    public String editarPerfil(int id){      
        this.tipoPerfil = this.tipoPerfildao.carregarTipoPerfil(id);
        return "novoTipoPerfil";
    }
    /**
     * Cadastra um novo tipo de texto
     * @return 
     */
    public String cadastrarTipoTexto(){   
        try {
            this.controlador.cadastrarTipoTexto(tipoTexto);
            return "tiposTexto"; 
        } catch (TipoTextoException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR,"Não foi possível cadastrar este tipo de texto. - "+ex.getMessage());
        }
        return "timeline"; 
    }
    
    /**
     * Chamada para a tela de criação de um novo tipo de texto
     * @return 
     */
    public String novoTipoTexto(){
        this.tipoTexto = new TipoTexto();
        return "novoTipoTexto"; 
    }
    /**
     * Retorna uma lista de tipos de texto
     * @return 
     * @throws br.com.witc.excessao.TipoTextoException 
     */
    public List<TipoTexto> listarTipoTexto() throws TipoTextoException{        
        return this.controlador.listarTipoTexto();
    }
    
    /**
     * Método para retornar uma lista filtrada para as tags de tipo de texto
     * quando o usuário seleciona os tipos de texto que ele se identifica
     * @param nomeTipoTexto
     * @return retorna uma lista de tipos de texto que comecem com aquele parametro de letras
     * @throws TipoTextoException 
     */
    public List<TipoTexto> listaTipoTextoFiltrada(String nomeTipoTexto) throws TipoTextoException {        
        List<TipoTexto> filteredTiposTexto;
        filteredTiposTexto = new ArrayList<>();
        for (TipoTexto tipo : this.listarTipoTexto()) {
            if(tipo.getTipoTexto().toLowerCase().startsWith(nomeTipoTexto.toLowerCase())){
                filteredTiposTexto.add(tipo);
            }
        }         
        return filteredTiposTexto;
    }
    
    /**
     * Método para retornar uma lista filtrada para as tags de tipo de texto
     * quando o usuário seleciona os tipos de texto que ele se identifica
     * @param palavra
     * @return retorna uma lista de tipos de texto que comecem com aquele parametro de letras 
     * @throws java.lang.Exception 
     */
    public List<String> listaPalavrasDesafios(String palavra) throws Exception {        
        List<String> filteredDesafiosPalavras;
        filteredDesafiosPalavras = new ArrayList<>();
        for(int i=0;i<=this.listarDesafiosPalavras().size();i++){
            if(this.listarDesafiosPalavras().get(i).toLowerCase().startsWith(palavra.toLowerCase())){
                filteredDesafiosPalavras.add(this.listarDesafiosPalavras().get(i));
            }
        }         
        return filteredDesafiosPalavras;
    }
    
    /**
     * Retorna a tela de edição do perfil selecionado na lista
     * @param id
     * @return 
     */
    public String editarTipoTexto(int id){ 
        tipoTexto = this.controlador.carregarTipoTexto(id);
        return "novoTipoTexto";
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
     * @return the selectedTiposTextoUsuario
     */
    public List<String> getSelectedTiposTextoUsuario() {
        return selectedTiposTextoUsuario;
    }

    /**
     * @param selectedTiposTextoUsuario the selectedTiposTextoUsuario to set
     */
    public void setSelectedTiposTextoUsuario(List<String> selectedTiposTextoUsuario) {
        this.selectedTiposTextoUsuario = selectedTiposTextoUsuario;
    }
    
    public void atualizarStatusUsuario(int status) {
        //Atualizar Status do Usuario
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "autenticarBean");
        autenticarBean.atualizarStatusUsuario(status);
    }
    
    /**
     * Cria um novo perfil para o usuário
     * @return 
     */
    public String criarNovoPerfil() {
        this.controlador.criarPerfilUsuario(getTipoPerfilNovo(), this.usuario);
        return "editarConta";
    }
    
    /**
     * Método para retornar a lista de todas as palavras cadastradas no sistema
     * @return List de string
     * @throws Exception 
     */
    private List<String> listarDesafiosPalavras() throws Exception {
        return this.controlador.listarDesafiosPalavras();
    }
    
    /**
     * Salva a palavra digitada na lista de palavras do desafio
     */
    public void salvarPalavra(){
        if(getListaPalavras() == null){
            listaPalavras = new ArrayList<>();
        }
        getListaPalavras().add(palavra);   
        this.palavra = "";
    }
    
    /**
     * Salva o desafio na tabela de desafios
     * Salva as palavras do desafio na tabela de palavras 
     * envia notificacao para o usuario que existe um desafio
     * salva na tabela de desafios do usuario um novo registro
     * @param idAmigo
     * @return 
     */
    public String salvarDesafio(int idAmigo){  
        DesafiosUsuarios desUsuario = new DesafiosUsuarios();
        desafio.setId(1);
        desUsuario.setDesafio(desafio);
        desUsuario.setUsuario(this.getUsuario().carregarAmigo(idAmigo));        
        desUsuario.setUsuarioDesafiante(usuario);
        int idDesafio = this.controlador.salvarDesafiosUsuarios(desUsuario);
        this.controlador.salvarDesafio(listaPalavras, idDesafio);
        this.notificacao.setDesafio(desUsuario);    
        this.notificacao.setDestinatario(this.getUsuario().carregarAmigo(idAmigo));
        this.notificacao.setRemetente(this.getUsuario());
        this.notificacao.setTexto("Desafiou você a escrever com as palavras que ele escolheu!");
        this.controlador.salvarNotificacao(this.notificacao);
        return "timeline.xhtml?faces-redirect=true";   
    }
    
    /**
     * Lista os desafios em que o usuário foi selecionado para fazer 
     */
    public void listarDesafiosUsuario(){
        this.controlador.listarDesafiosUsuarios(this.usuario.getId());
    }

    /**
     * @return the historiasDesafios
     */
    public HistoriasDesafios getHistoriasDesafios() {        
        return this.historiasDesafios;
    }

    /**
     * @param historiasDesafios the historiasDesafios to set
     */
    public void setHistoriasDesafios(HistoriasDesafios historiasDesafios) {
        this.historiasDesafios = historiasDesafios;
    }
    
    /**
     * Salva a história do desafio
     * @return retorna a pagina timeline
     * @throws java.lang.Exception
     */
    public String salvarHistoriaDesafio() throws Exception{
        if(this.verificarPalavrasDoDesafio()){
            this.historiasDesafios.setTipoTexto(this.controlador.carregarTipoTextoPorNome("DESAFIO"));
            this.historiasDesafios.setDisponivelBiblioteca(false);
            this.historiasDesafios.setReportadoConteudoImproprio(false);
            this.historiasDesafios.setClassificacao("LIVRE");
            this.historiasDesafios.setAvaliacao(0f);          
            this.desafiosUsuarios = this.controlador.carregarDesafiosUsuarios(this.historiasDesafios.getDesafiosUsuarios().getId());
            this.historiasDesafios.setDesafiosUsuarios(desafiosUsuarios);

            this.controlador.salvarHistoriaDesafio(this.historiasDesafios);

            this.controlador.excluirNotificacao(this.historiasDesafios.getDesafiosUsuarios().getId());
            this.notificacao.setDesafio(this.historiasDesafios.getDesafiosUsuarios());
            this.notificacao.setDestinatario(this.historiasDesafios.getDesafiosUsuarios().getUsuarioDesafiante());
            this.notificacao.setRemetente(this.historiasDesafios.getDesafiosUsuarios().getUsuario());
            this.notificacao.setTexto("concluiu seu desafio !");
            this.controlador.salvarNotificacao(this.notificacao);
            return "timeline.xhtml?faces-redirect=true";        
        }else{
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Você não escreveu o desafio com as palavras solicitadas.");
            return null;
        }
    }
    
    /**
     * Verifica se a história do desafio contém as palavras
     * escolhidas pelo desfiante 
     * @return 
     * @throws java.lang.Exception 
     */
    public boolean verificarPalavrasDoDesafio() throws Exception{
        for(String s:this.listarPalavrasDoDesafio()){
            return this.historiasDesafios.getTexto().toLowerCase().contains(s.toLowerCase());
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public String salvarHistoriaNovamente(){
        this.controlador.salvarHistoriaDesafio(this.historiasDesafiosCarregado);         
        this.controlador.excluirNotificacao(this.historiasDesafiosCarregado.getDesafiosUsuarios().getId());        
        this.notificacao.setDesafio(this.historiasDesafiosCarregado.getDesafiosUsuarios());
        this.notificacao.setRemetente(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
        this.notificacao.setDestinatario(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuarioDesafiante());
        this.notificacao.setTexto("tentou novamente o desafio !");
        this.controlador.salvarNotificacao(this.notificacao);
        return "timeline.xhtml?faces-redirect=true";
    }
    
    /**
     * Informa ao usuário que não está de acordo e salva a avaliação da historia
     * @return 
     */
    public String naoEstaAcordo(){
        this.controlador.salvarHistoriaDesafio(this.historiasDesafiosCarregado);  
        this.controlador.excluirNotificacao(this.historiasDesafiosCarregado.getDesafiosUsuarios().getId());        
        this.notificacao.setDesafio(this.historiasDesafiosCarregado.getDesafiosUsuarios());
        this.notificacao.setDestinatario(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
        this.notificacao.setRemetente(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuarioDesafiante());
        this.notificacao.setTexto("Informou que seu desafio não está de acordo !");
        this.controlador.salvarNotificacao(this.notificacao);
        return "timeline.xhtml?faces-redirect=true";
    }
    
    /**
     * Salva a história do desafio
     * @return retorna a pagina timeline
     * @throws br.com.witc.excessao.LivroException
     */
    public String publicarDesafioBiblioteca() throws LivroException, Exception{
        this.historiasDesafiosCarregado.setDisponivelBiblioteca(true);        
        this.controlador.salvarHistoriaDesafio(this.historiasDesafiosCarregado);
        Livro livro = this.criarLivroDoDesafio();
        Perfil perfil = Perfil.retornarPerfilPadraoUsuarioLogado(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
        this.controlador.salvarDesafioBiblioteca(livro,perfil);
        this.controlador.excluirNotificacao(this.historiasDesafiosCarregado.getDesafiosUsuarios().getId());        
        this.notificacao.setDesafio(this.historiasDesafiosCarregado.getDesafiosUsuarios());
        this.notificacao.setDestinatario(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
        this.notificacao.setRemetente(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuarioDesafiante());
        this.notificacao.setTexto("publicou seu desafio na biblioteca !");
        this.controlador.salvarNotificacao(this.notificacao);
        return "timeline.xhtml?faces-redirect=true";
    }
    
    /**
     * @return 
     * Recebe o id e a nota dada pelo usuário ao livro.  
     */
    public String desafioRating() {        
        try {
            String[] avaliacao = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("rating").split("-");              
            int idHistoriasDesafios = Integer.parseInt(avaliacao[0]);
            float rating = Float.parseFloat(avaliacao[1]);           
            
            HistoriasDesafios tmpHistoria;
            tmpHistoria = this.controlador.carregarHistoriasDesafiosPorId(idHistoriasDesafios);           
            int qtdAvaliacoesDesafio = tmpHistoria.getQtdAvaliacoes() + 1;
            float somaAvaliacoesDesafio = tmpHistoria.getSomaAvaliacoes() + rating;
            float novaAvaliacaoDesafio = somaAvaliacoesDesafio / qtdAvaliacoesDesafio;
            
            historiasDesafiosCarregado.setAvaliacao(novaAvaliacaoDesafio);
            historiasDesafiosCarregado.setQtdAvaliacoes(qtdAvaliacoesDesafio);
            historiasDesafiosCarregado.setSomaAvaliacoes(somaAvaliacoesDesafio); 
            
            this.controlador.salvarHistoriaDesafio(historiasDesafiosCarregado);
            
            //incrementa a avaliação do usuário também
            //ControladorAutenticacao controlAut = new ControladorAutenticacao();
            Perfil tmpPerfil = Perfil.retornarPerfilPadraoUsuarioLogado(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
            
            int qtdAvaliacoes = tmpPerfil.getQtdAvaliacoes() + 1;
            float somaAvaliacoes = tmpPerfil.getSomaAvaliacoes() + rating;
            float novaAvaliacao = somaAvaliacoes / qtdAvaliacoes;
            
            tmpPerfil.setAvaliacao(novaAvaliacao);
            tmpPerfil.setSomaAvaliacoes(somaAvaliacoes);
            tmpPerfil.setQtdAvaliacoes(qtdAvaliacoes);
            
            autenticarBean.controlador.salvarPerfil(tmpPerfil);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | 
                NullPointerException | PatternSyntaxException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Erro ao qualificar o usuário. Seu voto não foi computado!");
        }      
        return "editarDesafioConcluido.xhtml?faces-redirect=true";
    }
    
    /**
     * Listar as palavras do desafio em que o usuário está escrevendo
     * @return 
     * @throws java.lang.Exception 
     */
    public List<String> listarPalavrasDoDesafio() throws Exception{
        return this.controlador.listarPalavrasDoDesafio(this.historiasDesafios.getDesafiosUsuarios().getId());
    }
    
    
    /**
     * Método para redirecionar para outra página 
     * quando for uma notificação de conclusão de desafio
     * @param notificacao
     * @return true se contém a palavra conclui no texto da notificação
     */
    public boolean isConclusaoDesafio(Notificacoes notificacao){        
        return notificacao.getTexto().contains("concluiu") || notificacao.getTexto().contains("não") || notificacao.getTexto().contains("novamente");
    }
    
    /**
     * Método para redirecionar para outra página 
     * quando for uma notificação de um desafio não aceito
     * @return true se contém a palavra conclui no texto da notificação
     */
    public boolean isNaoDeAcordo(){     
        for(Notificacoes notif:listaNotificacoes){
            if(notif.getDesafio().getId() == this.historiasDesafiosCarregado.getDesafiosUsuarios().getId()){
                if(notif.getTexto().contains("acordo")){
                    return true;
                }
                if(notif.getTexto().contains("concluiu")){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Quando o usuário desiste de tentar fazer o desafio
     */
    public void desistir(){
        this.notificacao.setDesafio(this.historiasDesafiosCarregado.getDesafiosUsuarios());
        this.notificacao.setDestinatario(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuarioDesafiante());
        this.notificacao.setRemetente(this.historiasDesafiosCarregado.getDesafiosUsuarios().getUsuario());
        this.notificacao.setTexto("Desistiu de seu desafio !");
        this.controlador.salvarNotificacao(this.notificacao);
    }
    
    /**
     * Cria um novo livro a partir dos dados de um desafio 
     * @return
     * @throws LivroException 
     */
    private Livro criarLivroDoDesafio() throws LivroException, Exception {
        Livro livro = new Livro();   
        livro.setCapa(getImgBytes());
        livro.setAvaliacao(this.historiasDesafiosCarregado.getAvaliacao());
        livro.setBookLock(0);
        livro.setClassificacao("LIVRE");
        livro.setDisponivelBiblioteca(true);
        livro.setDisponivelRevisao(true);        
        livro.setNroPaginas(0);
        livro.setReportadoConteudoImproprio(false);
        livro.setQtdAvaliacoes(this.historiasDesafiosCarregado.getQtdAvaliacoes());
        livro.setRevisao(0);
        livro.setTexto(this.historiasDesafiosCarregado.getTexto());
        livro.setTipoGenero(null);
        this.tipoTexto = tipoTexto.carregarTipoTextoPorNome("DESAFIO");
        livro.setTipoTexto(tipoTexto);
        livro.setTitulo("Desafio entre palavras");
        return livro;
    }
    
    /**
     * @return Um array de byte da imagem
     * @throws Exception Caso haja algum problema na conversão da imagem
     */
    private byte[] getImgBytes() throws Exception {
        File imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/imagens/semCapa.png"));
        if (imgFile.exists()) {
            // Converte o arquivo em um array de bytes
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage imagem = ImageIO.read(imgFile);
            ImageIO.write(imagem, "PNG", bos);
            bos.flush();
            return bos.toByteArray();
        } else {
            throw new Exception();
        }
    }
}
