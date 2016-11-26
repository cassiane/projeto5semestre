/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.TimelineException;
import br.com.witc.modelo.ControladorPublicacao;
import br.com.witc.modelo.Publicacao;
import br.com.witc.modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author marcelo.lima
 */
@ManagedBean
@SessionScoped
public class PublicacaoBean {
    private final ControladorPublicacao controlador;        
    private List<Publicacao> lstTimeline;
    private String mensagemPublicacao;
    private Usuario usuario;
    
    public PublicacaoBean() {
        this.controlador = new ControladorPublicacao();
    }
    
    /**     
     * @return Um map contendo as fotos dos amigos convertidas para StreamedContent e suas publicações     
     */
    public List<Publicacao> getPublicacoesAmigos() {
        return this.lstTimeline;        
    }    
    
    /**
     * @return the mensagemPublicacao
     */
    public String getMensagemPublicacao() {
        return mensagemPublicacao;
    }

    /**
     * @param mensagemPublicacao the mensagemPublicacao to set
     */
    public void setMensagemPublicacao(String mensagemPublicacao) {
        this.mensagemPublicacao = mensagemPublicacao;
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
     * @param data A data a ser formatada
     * @return Uma string no formato dd-MM-yy às HH:mm
     */
    public String getDataPublicacao(Calendar data) {
        SimpleDateFormat frmt = new SimpleDateFormat("dd-MM-yy 'às' HH'h'mm");
        return frmt.format(data.getTime());
    }
    
    /**
     * Carrega a lista de publicação dos amigos
     * @param idPerfil O id do perfil visualizado atualmente
     */
    public void carregarPublicacoesAmigos(int idPerfil) {
        try {
            this.lstTimeline = this.controlador.getPublicacoesAmigos(idPerfil);
        } catch (TimelineException ex) {}
    }
    
    public StreamedContent getFotoAmigo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        
        int posicao = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("posicao"));
        byte[] foto = this.lstTimeline.get(posicao).getAmigo().getFoto();
        
        if (foto == null) {
            return carregarFotoDefault();
        }
        
        InputStream is = new ByteArrayInputStream(foto);
        StreamedContent image = new DefaultStreamedContent(is);        
        return image;
    }
    
    /**
     * Converte uma imagem para apresentar em um componente p:graphicImage     
     * @return Um objeto StreamedContent
     */
    public StreamedContent carregarFotoDefault() {        
        File imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(CadastrarBean.CAMINHO_FOTO_DEFAULT));            
        
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
    
    public void salvarMensagemPublicacao() {
        this.controlador.setUsuario(this.usuario);
        this.controlador.salvarMensagemPublicacao(this.mensagemPublicacao);        
        this.mensagemPublicacao = null;
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
