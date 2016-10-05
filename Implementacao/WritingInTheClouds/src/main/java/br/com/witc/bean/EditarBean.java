/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorEdicao;
import br.com.witc.modelo.ConvidadoPerfil;
import br.com.witc.modelo.HistoricoLivros;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoStatus;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.HistoricoLivroDAO;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.TipoStatusDAO;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author 00026108
 */
@ManagedBean
@SessionScoped
public class EditarBean {

    private final ControladorEdicao controlador;
    private Livro livro, livroCarregado;
    private Usuario usuario;
    private Perfil perfilUsuario;
    private String textoLivro;
    private String tituloLivro;
    private HistoricoLivros historico;
    private List<Livro> livros;
    private PerfilDAO daoPerfil;
    private LivroDAO daoLivro;
    private HistoricoLivroDAO daoHistorico;
    // lista de amigos selecionados para solicitação
    private List<Perfil> amigoEditor;
    // lista de amigos editores
    private List<Perfil> listaAmigoEditor;
    // lista de solicitações para edição do usuario
    private List<ConvidadoPerfil> listaSolicitacaoEdicao;
    
    
    public EditarBean() {
        this.controlador = new ControladorEdicao();
        
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

    public HistoricoLivros getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoLivros historico) {
        this.historico = historico;
    }    
    public List<Livro> listarLivrosPerfil(){
        List<Livro> listaTemp = daoLivro.listarLivrosPerfil(this.perfilUsuario);
       
            return listaTemp;
        
    }
    
    public void salvarNovoLivro(){
        this.perfilUsuario = daoPerfil.carregarPerfil(this.usuario);
        TipoStatus st = new TipoStatus();
        TipoStatusDAO daoStatus = new TipoStatusDAO();
        st=daoStatus.carregarPerfil(1);
        this.livro = new Livro();
        this.livro.setTexto(textoLivro);
        
        if(tituloLivro.isEmpty()){
            tituloLivro="Novo Livro";
        }
        this.livro.setTitulo(tituloLivro);
        this.livro.setClassificacao("LIVRE");
        this.livro.setDisponivelBiblioteca(true);
        this.livro.setReportadoConteudoImproprio(false);
        this.livro.setQualificacao(0);
        daoLivro.criarLivro(livro);
        
        this.historico=new HistoricoLivros(); 
        this.historico.setPerfil(this.perfilUsuario);
        this.historico.setLivro(this.livro);
        this.historico.setStatus(st);
        this.historico.setDataInicio(this.getPegaDataAtual());
        daoHistorico.salvarHistorico(this.historico);
 
    }
    
    public void salvarLivro(){
        daoLivro.criarLivro(this.livroCarregado);
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

    public List<Perfil> getAmigoEditor() {
        return amigoEditor;
    }

    public void setAmigoEditor(List<Perfil> AmigoEditor) {
        this.amigoEditor = AmigoEditor;
    }

    public List<Perfil> getListaAmigoEditor() {
        return listaAmigoEditor;
    }

    public List<ConvidadoPerfil> getListaSolicitacaoEdicao() {
        this.listaSolicitacaoEdicao = this.controlador.carregarListaSolicitacaoEdicao(this.perfilUsuario);
        return listaSolicitacaoEdicao;
    }

    public void setListaSolicitacaoEdicao(List<ConvidadoPerfil> listaSolicitacaoEdicao) {
        this.listaSolicitacaoEdicao = listaSolicitacaoEdicao;
    }
    
    public StreamedContent carregarFoto(int idfoto) {
        byte[] foto = null;
        
        if (foto == null) {
            return new CadastrarBean().carregarFotoDefault();
        }
        
        InputStream is = new ByteArrayInputStream(foto);               
        StreamedContent image = new DefaultStreamedContent(is);        
        return image;
    }
    
    /**
     * Preencher a variavel com a lista de amigos editores
     */
    public void carregarListaAmigoEditor() {
        this.listaAmigoEditor = this.controlador.carregarListaAmigoEditor(this.perfilUsuario, this.livroCarregado.getId());
    }
    
    /**
     * Metodo para gerar a solicitação de edição
     */
    public void convidarAmigoEditor() {
        this.controlador.convidarAmigoEditor(this.perfilUsuario, this.amigoEditor, this.livroCarregado);
    }
    
    /**
     * Preencher a variavel com as solicitações de edição
     */
    public void carregarListaSolicitacaoEdicao() {
        this.listaSolicitacaoEdicao = this.controlador.carregarListaSolicitacaoEdicao(this.perfilUsuario);
    }
    
    /**
     * Metodo para aceitar a solicitação de edição
     * @param ediLivro livro a ser compartilhado para edição
     * @return a pagina para refresh
     */
    public String aceitarEdicao(ConvidadoPerfil ediLivro) {
        this.controlador.aceitarEdicao(ediLivro);
        //this.listaSolicitacaoEdicao = this.controlador.carregarListaSolicitacaoEdicao(this.perfilUsuario);
        return "biblioteca";
    }
    
    /**
     * Metodo para negar a solicitação de edição
     * @param ediLivro livro negado para compartilhamento de edição
     * @return a pagina para refresh
     */
    public String negarEdicao(ConvidadoPerfil ediLivro) {
        this.controlador.negarEdicao(ediLivro);
        this.carregarListaSolicitacaoEdicao();
        return "biblioteca";
    }
}
