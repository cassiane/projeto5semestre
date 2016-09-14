/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorAutenticacao;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.PerfilTemLivroDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 00026108
 */
@ManagedBean
@SessionScoped
public class EditarBean {
    private ControladorAutenticacao controlador;
    private AutenticarBean autenticar=null;
    private Livro livro;
    private Usuario usuario;
    private Perfil perfilUsuario;
    private String tituloLivro = "";
    private String textoLivro="";
    private List<Livro> livros;
    LivroDAO dao;
    PerfilDAO daoPerfil;
    PerfilTemLivroDAO daoPerfilTemLivro;
    
  // carrega o perfil padrao e a lista de livros logo após instanciar a classe
  
    public EditarBean(){     
       this.livro=new Livro();
      daoPerfil = new PerfilDAO();
       dao = new LivroDAO();
       
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
    public Usuario usuarioLogado(){
        return autenticar.usuarioLogado();
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = controlador.getUsuario();
    }

    public Perfil getPerfilUsuario() {
        return daoPerfil.buscaPerfilPadrao(usuario.getId());
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = daoPerfil.buscaPerfilPadrao(usuario.getId());
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
        
        this.livro.setTexto(this.textoLivro);
        this.livro.setTitulo(this.tituloLivro);
       
        dao.criarLivro(livro);
        daoPerfilTemLivro.salvarPerfilLivro(perfilUsuario, livro);
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
