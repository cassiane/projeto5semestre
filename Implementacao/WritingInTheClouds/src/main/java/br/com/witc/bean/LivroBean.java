/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoStatus;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.HistoricoLivroDAO;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.TipoStatusDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
    private List<Livro> livros;
    private PerfilDAO daoPerfil;
    private LivroDAO daoLivro;
    private HistoricoLivroDAO daoHistorico;
    private List<String> teste = new ArrayList();    
    private List<String> tipoTexto = new ArrayList();
    
    public LivroBean() {
        //usuario logado
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();        
        /*
        daoPerfil = new PerfilDAO();
        daoLivro = new LivroDAO();
        daoHistorico = new HistoricoLivroDAO();
        
        this.perfilUsuario = daoPerfil.carregarPerfil(this.usuario);
        this.livros=daoLivro.listarLivrosPerfil(this.perfilUsuario);        
        */
        
        tipoTexto.add("Teste1");
        tipoTexto.add("Teste2");
        tipoTexto.add("Teste3");
        
        for (int i=0; i<10; i++) {
            teste.add("Livro" + i);
        }
    }
    
    public List<String> getTeste() {
        return teste;
    }
    
    public List<String> getTipoTexto() {
        return tipoTexto;
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
        
        this.historico=new HistoricoLivro(); 
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

   
}
