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
import br.com.witc.persistencia.HistoricoDAO;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
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
public class EditarBean {
   
  
    private Livro livro;
    private Usuario usuario;
    private Perfil perfilUsuario;
    private String tituloLivro = "";
    private String textoLivro="";
    private HistoricoLivro historico;
    private List<Livro> livros;
    LivroDAO dao;
    PerfilDAO daoPerfil;
 
    HistoricoDAO historicoDAO;
    
  // carrega o perfil padrao e a lista de livros logo após instanciar a classe
  
    public EditarBean(){ 
       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();
        this.historico = new HistoricoLivro();
       
      daoPerfil = new PerfilDAO();
       dao = new LivroDAO();
       historicoDAO = new HistoricoDAO();
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
        return this.perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    
    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getTextoLivro() {
        return textoLivro;
    }

    public void setTextoLivro(String textoLivro) {
        this.textoLivro = textoLivro;
    }
    
    public String salvarLivro(){
        perfilUsuario = daoPerfil.burcarPerfilUsuario(this.usuario);
        this.livro=new Livro();
        this.livro.setTexto(this.textoLivro);
        this.livro.setTitulo(this.tituloLivro);
        historico.setLivro(livro);
        historico.setPerfil(perfilUsuario);
        TipoStatus status = null;
        historico.setStatus(status);
        historico.setDataInicio(Calendar.getInstance());
    //    historico.setDataConclusao(Calendar.getInstance());
        dao.criarLivro(livro);
        historicoDAO.criarHistorico(historico);
        this.textoLivro="";
        return "biblioteca";
        
    }
    
   
     public String biblioteca(){
         return "biblioteca";
     }
  
    public List<Livro> getLivros() {
       return this.livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = dao.carregarTodosLivrosUsuario(perfilUsuario);
    }
    
    
}
