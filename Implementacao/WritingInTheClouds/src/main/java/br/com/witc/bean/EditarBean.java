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
import java.util.List;
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
    private Livro livro;
    private Usuario usuario;
    private Perfil perfilUsuario;
    private String tituloLivro = "";
    private String textoLivro="";
    private List<Livro> livros;
    LivroDAO dao;
    
    public EditarBean() {
       
      this.livro=new Livro();
       dao = new LivroDAO();
     //  this.livros = dao.carregarTodosLivrosUsuario(controlador.getUsuario());
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
        this.textoLivro="";
        return "biblioteca";
    }
     public String biblioteca(){
         return "biblioteca";
     }
    public void listarLivrosUsuario(){
       
       this.livros = dao.carregarTodosLivrosUsuario(this.usuario);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
