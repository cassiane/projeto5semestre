/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.TipoTextoException;
import br.com.witc.modelo.ControladorLivro;
import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoStatus;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.HistoricoLivroDAO;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.TipoStatusDAO;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.event.PhaseId;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author 00026108
 */
@ManagedBean
@SessionScoped
public class LivroBean {

    private Livro livro, livroCarregado;
    private Usuario usuario;
    private Perfil perfilUsuario;
    private String textoLivro;
    private String tituloLivro;
    private HistoricoLivro historico;
    private TipoTexto tipoTexto;
    private List<Livro> livros;
    private PerfilDAO daoPerfil;
    private LivroDAO daoLivro;
    private HistoricoLivroDAO daoHistorico;        
    private final ControladorLivro controlador;    
    private UploadedFile file;
    private static final String CAMINHO_CAPA_DEFAULT = "/resources/imagens/semCapa.png";

    public LivroBean() {
        this.controlador = new ControladorLivro();
        
        //usuario logado
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();        
        
        daoPerfil = new PerfilDAO();
        daoLivro = new LivroDAO();
        daoHistorico = new HistoricoLivroDAO();
        
        this.perfilUsuario = daoPerfil.carregarPerfil(this.usuario);
        this.livros=daoLivro.listarLivrosPerfil(this.perfilUsuario);                
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
      public String getTextoLivro() {
        return textoLivro;
    }

    public void setTextoLivro(String textoLivro) {
        this.textoLivro = textoLivro;
    }

    public HistoricoLivro getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoLivro historico) {
        this.historico = historico;
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
     * @return Uma lista contendo os tipos de textos cadastrados no sistema
     */
    public List<TipoTexto> getLstTipoTexto() {
        List<TipoTexto> tmpLstTipoTexto = new ArrayList();
        try {
            tmpLstTipoTexto = this.controlador.getLstTipoTexto();
        } catch (TipoTextoException ex) {
            Logger.getLogger(LivroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmpLstTipoTexto;
    }
    
    public List<Livro> listarLivrosPerfil(){
        List<Livro> listaTemp = daoLivro.listarLivrosPerfil(this.perfilUsuario);
       
            return listaTemp;
        
    }
    
    public void salvarNovoLivro(){        
        try {
            this.perfilUsuario = daoPerfil.carregarPerfil(this.usuario);             
            TipoStatusDAO daoStatus = new TipoStatusDAO();
            TipoStatus st =daoStatus.carregarPerfil(1);
            this.livro = new Livro();
            
            this.livro.setCapa(getImgBytes());
            this.livro.setTipoTexto(tipoTexto);
            this.livro.setTexto(textoLivro);
            if(tituloLivro.isEmpty()){
                tituloLivro="Novo Livro";
            }
            this.livro.setTitulo(tituloLivro);
            this.livro.setClassificacao("LIVRE");
            this.livro.setDisponivelBiblioteca(false);
            this.livro.setReportadoConteudoImproprio(false);
            this.livro.setQualificacao(0);
            daoLivro.criarLivro(livro);
            
            this.historico=new HistoricoLivro();
            this.historico.setPerfil(this.perfilUsuario);
            this.historico.setLivro(this.livro);
            this.historico.setStatus(st);
            this.historico.setDataInicio(this.getPegaDataAtual());
            daoHistorico.salvarHistorico(this.historico);
        } catch (Exception ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Problemas ao carregar a capa!");
        }
 
    }
    
    public void salvarLivro(){
        try {
            this.livroCarregado.setCapa(getImgBytes());
            this.livroCarregado.setTipoTexto(tipoTexto);
            daoLivro.criarLivro(this.livroCarregado);
        } catch (Exception ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Problemas ao carregar a capa!");
        }
    }
    
    /**     
     * @return Um array de byte da imagem
     * @throws Exception Caso haja algum problema na conversão da imagem
     */
    private byte[] getImgBytes() throws Exception {
        if (this.file == null) {
            this.file.write(CAMINHO_CAPA_DEFAULT);
        }
        InputStream inputStream = this.file.getInputstream();
        return IOUtils.toByteArray(inputStream);                        
    }
    
    public  Calendar getPegaDataAtual(){
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
                return  calendar;
	
    }
    public String biblioteca(){
        return "biblioteca";
    }  
    
    public String editorNovoLivro(){
        this.livro=new Livro();
        this.textoLivro="";
        return "novoLivro";
    }

    public String editarLivro(int id){
        livroCarregado = new Livro();
        livroCarregado=daoLivro.carregarLivro(id);
        return "editarLivro";
    }

    public Livro getLivroCarregado() {
        return livroCarregado;
    }

    public void setLivroCarregado(Livro livroCarregado) {
        this.livroCarregado = livroCarregado;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public UploadedFile getFile() {
        return this.file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }    
    
    /**     
     * @return Um Map com os Livros da Biblioteca Virtual
     */
    public Map<String,List<Livro>> getBibliotecaVirtual() {
        try {
            return this.controlador.getBibliotecaVirtual();
        } catch (BibliotecaVirtualVaziaException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, ex.getMessage());
        }
        return new HashMap();
   }

    /**     
     * @return A capa do livro
     */
    public StreamedContent getCapa() {                                                                 
        FacesContext context = FacesContext.getCurrentInstance();        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
                
        Integer livroId = null;
        try {
            livroId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("livroId"));
        } catch(NumberFormatException ex) {}        
        
        /*
        Necessario tratamento para o caso de img null
        */
        //byte[] img = this.controlador.getCapaPorId(livroId);
        byte[] img = daoLivro.carregarLivro(livroId).getCapa();
        
        InputStream is = new ByteArrayInputStream(img);               
        StreamedContent capa = new DefaultStreamedContent(is);        
        return capa;
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
