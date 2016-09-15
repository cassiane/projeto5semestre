/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.LivroDAO;
import br.com.witc.persistencia.PerfilDAO;
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
    private String textoLivro;
    private List<Livro> livros;
    private PerfilDAO daoPerfil;
    private LivroDAO daoLivro;
    
    
    public EditarBean() {
        //usuario logado
          ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();
        daoPerfil = new PerfilDAO();
        daoLivro = new LivroDAO();
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
    
  
    public void salvarLivro(){
       // this.perfilUsuario = daoPerfil.carregarPerfil(this.usuario);
        this.livro = new Livro();
        this.livro.setTexto(textoLivro);
        this.livro.setTitulo("titulo do livro");
        this.livro.setClassificacao("LIVRE");
        this.livro.setDisponivelBiblioteca(true);
        this.livro.setReportadoConteudoImproprio(false);
        this.livro.setQualificacao(0);
        daoLivro.criarLivro(livro);
        
        
       
        //perfil usuario
        //livro
        //salva livro
        //historico
        //salvahistorico
    }
    
    public String biblioteca(){
        return "biblioteca";
    }  
    
    public String editor(){
        return "editarLivro";
    }

    public String getTextoLivro() {
        return textoLivro;
    }

    public void setTextoLivro(String textoLivro) {
        this.textoLivro = textoLivro;
    }

   
}
