/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.LivroException;
import br.com.witc.excessao.TipoTextoException;
import br.com.witc.modelo.ControladorLivro;
import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoStatus;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
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
    // Livro selecionado na Biblioteca Virtual
    private Livro livroSelecionado;

    private Usuario usuario;
    private Perfil perfilUsuario;
    private String textoLivro;
    private String tituloLivro;
    private HistoricoLivro historico;
    private TipoTexto tipoTexto;
    private List<Livro> livros;        
    private final ControladorLivro controlador;    
    private UploadedFile file;
    private static final String CAMINHO_CAPA_DEFAULT = "/resources/imagens/semCapa.png";
    private String campoPesquisa;
    private String valorPesquisa;
    private Map<String,List<Livro>> bibliotecaVirtual;
    private boolean disponivelEdicaoAmigo;

    private boolean livroFinalizado;
    private boolean disponivelRevisao;

    
    // Itens de pesquisa
    public static final String ITEM_PESQUISA_AUTOR = "autor";
    public static final String ITEM_PESQUISA_TITULO = "titulo";
    public static final String ITEM_PESQUISA_TIPO_TEXTO = "tipoTexto";

    public LivroBean() {
        this.controlador = new ControladorLivro();
        
        //usuario logado
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        AutenticarBean autenticarBean = (AutenticarBean) FacesContext.getCurrentInstance().getApplication()
                    .getELResolver().getValue(elContext, null, "autenticarBean");
        
        this.usuario = autenticarBean.usuarioLogado();                                        
        this.perfilUsuario = this.controlador.carregarPerfil(this.usuario);

        this.livros=this.controlador.listarLivrosPerfil(this.perfilUsuario);  
        this.disponivelRevisao=false;
    }

    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

/**
     * @return the livroSelecionado
     */
    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    /**
     * @param livroSelecionado the livroSelecionado to set
     */
    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
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
            enviarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
        return tmpLstTipoTexto;
    }
    
    public List<Livro> listarLivrosPerfil(){
        List<Livro> listaTemp = this.controlador.listarLivrosPerfil(this.perfilUsuario);       
        return listaTemp;        
    }   
    
     public List<Livro> listarLivrosRevisao(){
         TipoStatus status = this.controlador.carregarTipoStatus(2);
        List<Livro> listaTemp = this.controlador.listarLivrosStatus(status);       
        return listaTemp;        
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
        return "metadadosLivro";
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
     * @return the campoPesquisa
     */
    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    /**
     * @param campoPesquisa the campoPesquisa to set
     */
    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

    /**
     * @return the valorPesquisa
     */
    public String getValorPesquisa() {
        return valorPesquisa;
    }

    /**
     * @param valorPesquisa the valorPesquisa to set
     */
    public void setValorPesquisa(String valorPesquisa) {
        this.valorPesquisa = valorPesquisa;
    }    
    
    /**     
     * @return Um Map com os Livros da Biblioteca Virtual
     */
    public Map<String,List<Livro>> getBibliotecaVirtual() {                
        return this.bibliotecaVirtual;
   }
    
    public String salvarNovoLivro(){        
        try {            
            this.perfilUsuario = this.controlador.carregarPerfil(this.usuario);                         
            TipoStatus st = this.controlador.carregarTipoStatus(1);
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
            this.livro.setAvaliacao(0f);
            if (this.disponivelEdicaoAmigo) {
                this.livro.setBookLock(0);
            } else {
                this.livro.setBookLock(this.perfilUsuario.getId());
            }
            this.controlador.salvarLivro(this.livro, this.livroFinalizado, this.perfilUsuario);            
            
            this.historico=new HistoricoLivro();
            this.historico.setPerfil(this.perfilUsuario);
            this.historico.setLivro(this.livro);
            this.historico.setStatus(st);
            this.historico.setDataInicio(this.getPegaDataAtual());

            this.controlador.salvarHistorico(this.historico);
            this.tituloLivro="";
            if(this.disponivelRevisao){
              HistoricoLivro hist=new HistoricoLivro();
              TipoStatus stTemp = this.controlador.carregarTipoStatus(2);
              hist.setPerfil(this.perfilUsuario);
              hist.setLivro(this.livro);
              hist.setStatus(stTemp);
              hist.setDataInicio(this.getPegaDataAtual());
              this.controlador.salvarHistorico(hist);    
            }
            if (this.disponivelEdicaoAmigo) {
              

                return "biblioteca.xhtml?faces-redirect=true";                
            }            
        } catch (LivroException ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        } catch (Exception ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Não foi possível salvar! Problemas ao carregar a capa.");
        }
        return null; 
    }
    
    public String salvarLivro(){
        try {                     
            if ((this.livroFinalizado) || (this.disponivelEdicaoAmigo)) {
                this.livroCarregado.setBookLock(0);                
            }
            
            this.livroCarregado.setCapa(getImgBytes());
            //this.livroCarregado.setTipoTexto(tipoTexto);            
            this.controlador.salvarLivro(livroCarregado, this.livroFinalizado, this.perfilUsuario);                        
            
            if ((this.livroFinalizado) || (this.disponivelEdicaoAmigo)) {
                this.livroFinalizado = false;
                this.disponivelEdicaoAmigo = false;
                return "biblioteca.xhtml?faces-redirect=true";                
            }
        } catch (Exception ex) {
            enviarMensagem(FacesMessage.SEVERITY_ERROR, "Não foi possível salvar! Problemas ao carregar a capa.");
        }
        return null;
    }        

    /**     
     * @return Um array de byte da imagem
     * @throws Exception Caso haja algum problema na conversão da imagem
     */
    private byte[] getImgBytes() throws Exception {
        if ((this.file == null) || (this.file.getFileName().isEmpty())) {            
            File imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(CAMINHO_CAPA_DEFAULT));            
        
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
        InputStream inputStream = this.file.getInputstream();
        return IOUtils.toByteArray(inputStream);                        
    }    
    
    /**     
     * @return Os autores do livro selecionado
     */
    public String getAutoresLivroSelecionado() {
        if (this.livroSelecionado != null) {
            return this.livroSelecionado.getAutores(this.livroSelecionado.getId());
        }
        return null;
    }
    
    /**     
     * @return O texto do livro selecionado
     */
    public String getTextoLivroSelecionado() {
        if (this.livroSelecionado != null) {
            return this.livroSelecionado.getTexto();
        }
        return null;
    }
    
/**     
     * @return O título do livro selecionado
     */
    public String getTituloLivroSelecionado() {
        if (this.livroSelecionado != null) {
            return this.livroSelecionado.getTitulo();
        }
        return null;
    }    
    
    /**
     * @return the disponivelEdicaoAmigo
     */
    public boolean isDisponivelEdicaoAmigo() {
        return disponivelEdicaoAmigo;
    }

    /**
     * @param disponivelEdicaoAmigo the disponivelEdicaoAmigo to set
     */
    public void setDisponivelEdicaoAmigo(boolean disponivelEdicaoAmigo) {
        this.disponivelEdicaoAmigo = disponivelEdicaoAmigo;
    }    
    
    /**
     * @return the livroFinalizado
     */
    public boolean isLivroFinalizado() {
        return livroFinalizado;
    }

    /**
     * @param livroFinalizado the livroFinalizado to set
     */
    public void setLivroFinalizado(boolean livroFinalizado) {
        this.livroFinalizado = livroFinalizado;        
    }    
    
    /**
     * @return the ITEM_PESQUISA_AUTOR
     */
    public String getITEM_PESQUISA_AUTOR() {
        return ITEM_PESQUISA_AUTOR;
    }
    
    /**
     * @return the ITEM_PESQUISA_TITULO
     */
    public String getITEM_PESQUISA_TITULO() {
        return ITEM_PESQUISA_TITULO;
    }
    
    /**
     * @return the ITEM_PESQUISA_TIPO_TEXTO
     */
    public String getITEM_PESQUISA_TIPO_TEXTO() {
        return ITEM_PESQUISA_TIPO_TEXTO;
    }
    
    /**     
     * @return A capa do livro em formato compatível com p:graphicImage
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
        String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");       
        
        List<Livro> lstLivros = this.bibliotecaVirtual.get(key);
        Livro livroCapa = new Livro();
        livroCapa.setId(livroId);        
        
        byte[] img = lstLivros.get(lstLivros.indexOf(livroCapa)).getCapa();        
        InputStream is = new ByteArrayInputStream(img);               
        StreamedContent capa = new DefaultStreamedContent(is);        
        return capa;        
    }  
    
    /**
     * Carrega todos os livros disponíveis na Biblioteca Virtual
     * @return A próxima página a se visualizada pelo usuário
     */
    public String carregaBibliotecaVirtualCompleta() {    
        try {            
            this.bibliotecaVirtual = this.controlador.carregaBibliotecaVirtual();
        } catch (BibliotecaVirtualVaziaException | TipoTextoException ex) {
            this.bibliotecaVirtual = null;
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }                   
        
        this.campoPesquisa = null;
        this.valorPesquisa = null;
        return "bibliotecaVirtual";
    }
    
    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de pesquisa
     * @return A próxima página a se visualizada pelo usuário
     */
    public String carregaBibliotecaVirtualPesquisa() {
        try {            
            this.bibliotecaVirtual = this.controlador.carregaBibliotecaVirtualPesquisa(campoPesquisa, valorPesquisa);
        } catch (BibliotecaVirtualVaziaException | TipoTextoException ex) {
            this.bibliotecaVirtual = null;
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }  
        return null;
    }        
    
    /**
     * Verifica se o livro está disponível para edição do usuário logado
     * @param idLivro O id do Livro     
     * @return True, caso o livro esteja disponível para edição e false, caso contrário
     */
    public boolean estaDisponivelEdicaoUsuario(int idLivro) {
        return this.controlador.estaDisponivelEdicaoUsuario(idLivro, this.perfilUsuario.getId());
    }    
    
    /**
     * Verifica se o livro está disponível para edição do usuário logado     
     * @return True, caso o livro esteja disponível para edição e false, caso contrário
     */
    public boolean estaDisponivelEdicaoUsuario() {
        return estaDisponivelEdicaoUsuario(this.livroCarregado.getId());
    }        
    
    /**
     * Atualiza a disponibilidade para edição do amigo, caso o check box de finalizaçã seja alterado
     */
    public void onLivroFinalizadoStatusChange() {
        if (this.livroFinalizado) {
            this.disponivelEdicaoAmigo = true;        
        }
    }
    
    /**
     * Seta o bookLock do livro para o usuário que pressionou o botão de editar, e também verifica
     * se o livro já não está com lock setado.
     * @param idLivro O id do livro a ter o bookLock setado
     * @return A próxima página a ser visualizada pelo usuário
     */
    public String atualizarLockLivro(int idLivro) {
        try {
            this.disponivelEdicaoAmigo = false;
            this.livroFinalizado = false;
            this.livroCarregado = this.controlador.carregarLivro(idLivro);
            if ((this.livroCarregado.getBookLock() != 0) && 
                (this.livroCarregado.getBookLock() != this.perfilUsuario.getId())){
                enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, 
                        "O livro já está sendo editado por outro usuário!");
                return null;
            
            } else if (this.livroCarregado.getBookLock() != this.perfilUsuario.getId()) {
                this.livroCarregado.setBookLock(this.perfilUsuario.getId());            
                this.controlador.salvarLivro(livroCarregado, livroFinalizado, perfilUsuario);
            }                                    
            return "editarLivro";
        } catch(LivroException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
        return null;
    }
    
    /**
     * Recebe o id e a nota dada pelo usuário ao livro. Esse método é chamado em witc.js     
     */
    public void bookRating() {        
        try {
            String[] avaliacao = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("bookRating").split("-");
            String key = avaliacao[0];
            int idLivro = Integer.parseInt(avaliacao[1]);
            float rating = Float.parseFloat(avaliacao[2]);
                    
            Livro tmpLivro = new Livro();
            tmpLivro.setId(idLivro);
            int indexLivro = this.bibliotecaVirtual.get(key).indexOf(tmpLivro);
            tmpLivro = this.bibliotecaVirtual.get(key).get(indexLivro);
            
            int qtdAvaliacoes = tmpLivro.getQtdAvaliacoes() + 1;
            float somaAvaliacoes = tmpLivro.getSomaAvaliacoes() + rating;
            float novaAvaliacao = somaAvaliacoes / qtdAvaliacoes;
            
            tmpLivro.setAvaliacao(novaAvaliacao);
            tmpLivro.setQtdAvaliacoes(qtdAvaliacoes);
            tmpLivro.setSomaAvaliacoes(somaAvaliacoes);
            
            this.controlador.salvarLivro(tmpLivro);                        
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | 
                NullPointerException | PatternSyntaxException ex) {
            enviarMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, "Erro ao qualificar o livro. Seu voto não foi computado!");
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

    public boolean isDisponivelRevisao() {
        return disponivelRevisao;
    }

    public void setDisponivelRevisao(boolean disponivelRevisao) {
        this.disponivelRevisao = disponivelRevisao;
    }
    
 
}
