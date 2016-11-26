/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LoginInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import br.com.witc.persistencia.UsuarioDAO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.*;
import org.apache.commons.mail.EmailException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author marcelo.lima
 */
@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataAniversario;
    private String genero;
    private byte[] foto;
    private byte[] fotoCapa;
    private String senha;
    private String status;
    private boolean ativo; 
    @OneToMany(cascade=CascadeType.ALL)
           @JoinTable(name="Usuario_tem_TipoTexto",
                     joinColumns={@JoinColumn(name="idUsuario",  
                      referencedColumnName="id")},  
                     inverseJoinColumns={@JoinColumn(name="idTipoTexto",   
                      referencedColumnName="id")})  
    private List<TipoTexto> tipostextos;
    @OneToMany(cascade=CascadeType.ALL)
           @JoinTable(name="Revisor_tem_TipoTexto",
                     joinColumns={@JoinColumn(name="idUsuario",  
                      referencedColumnName="id")},  
                     inverseJoinColumns={@JoinColumn(name="idTipoTexto",   
                      referencedColumnName="id")})  
    private List<TipoTexto> tipostextosRevisor;
    private static final String CAMINHO_FOTO_DEFAULT = "/resources/imagens/semFoto.png";
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dataAniversario
     */
    public Calendar getDataAniversario() {
        return dataAniversario;
    }

    /**
     * @param dataAniversario the dataAniversario to set
     */
    public void setDataAniversario(Calendar dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }
    
    /**
     * Converte uma imagem para apresentar em um componente p:graphicImage     
     * @return Um objeto StreamedContent
     */
    public StreamedContent carregarFotoDefault() {  
        
        File imgFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(CAMINHO_FOTO_DEFAULT));            
        
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
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
        /**
     * @return the fotoCapa
     */
    public byte[] getFotoCapa() {
        return fotoCapa;
    }

    /**
     * @param fotoCapa the fotoCapa to set
     */
    public void setFotoCapa(byte[] fotoCapa) {
        this.fotoCapa = fotoCapa;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return 
     */
    public boolean isAtivo() {
        return ativo;
    }
    
    /**
     * @param ativo 
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }    
    
    /**     
     * @return O nome do autor em formato ABNT
     */
    public String getNomeABNT() {
        String tmpNome = this.nome.split(" ")[0];
        String nomeABNT;
        if (tmpNome.length() > 1) {
            nomeABNT = tmpNome.substring(0, 1).toUpperCase() + tmpNome.substring(1).toLowerCase();
        } else {
            nomeABNT = tmpNome.toUpperCase();
        }
        return this.getSobrenome().split(" ")[this.getSobrenome().split(" ").length - 1].toUpperCase() + ", "  +
                nomeABNT;
    }    
    
    /**
     * Autentica um usuário no sistema
     *
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso os dados informados sejam inválidos
     * @throws java.security.NoSuchAlgorithmException Caso o algorítimo SHA-256
     * não seja localizado
     * @throws java.io.UnsupportedEncodingException Caso haja erro de
     * codificação
     */
    public static Usuario efetuarLogin(String email, String senha)
            throws LoginInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String hashSenha = criarHashSenha(senha);
        return usuarioDAO.efetuarLogin(email, hashSenha);
    }

    /**
     * Cria O hash da senha do usuário utilizando o algorítimo SHA-256
     *
     * @param senha A string que servira de base para criacao do hash
     * @return O hash criar
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja
     * localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     */
    public static String criarHashSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(senha.getBytes("UTF-8"));
        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Persiste usuario no banco
     *
     * @throws DadosUsuarioInvalidoException Caso o usuário já esteja cadastrado
     * no sistema
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja
     * localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     * @throws br.com.witc.excessao.UsuarioInvalidoException Caso usuário já
     * exista no BD
     */
    public void cadastrarUsuario() throws DadosUsuarioInvalidoException,
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException {
        UsuarioDAO dao = new UsuarioDAO();
        setSenha(criarHashSenha(this.senha));
        dao.salvarUsuario(this);
    }
    /**
     * Dado um usuário do sistema
     * quando selecionar excluir conta
     * então deve apagar a conta do usuário
     * @throws UsuarioInvalidoException 
     */
    public void excluirUsuario() throws UsuarioInvalidoException{
        UsuarioDAO dao = new UsuarioDAO();
        dao.ExcluirUsuario(this);
    }
    /**
     * altera os dados do usuário
     * @throws DadosUsuarioInvalidoException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws UsuarioInvalidoException 
     */
    public void alterarUsuario() throws DadosUsuarioInvalidoException, 
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException{
       UsuarioDAO dao = new UsuarioDAO();        
       dao.salvarUsuario(this);
    }
    /**
     * Acessar o dao para buscar os amigos
     *
     * @return Lista de amigos
     */
    public List<Usuario> listarAmigos() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarAmigos(this.getId());
    }

    /**
     * Acessa o dao para buscar as sugestões
     *
     * @return Lista de sugestão de amigos
     */
    public List<Usuario> listarSugestao() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarSugestao(this.getId());
    }

    /**
     * Acessar o dao para buscar os usuarios
     *
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarUsuarios();
    }

    /**
     * Acessar o dao para registrar uma solicitação de amizade
     *
     * @param idSugestao Identificador do amigo a ser solicitado
     */
    public void solicitarAmizade(int idSugestao) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.solicitarAmizade(this.getId(), idSugestao);
    }

    /**
     * Acessar o dao para buscar a lista de solicitações de amizade do usuario
     *
     * @return Lista de solicitação de amizade
     */
    public List<Usuario> listarSolicitacao() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarSolicitacao(this.getId());
    }

    /**
     * Acessar o dao para registrar o aceite de amizade
     *
     * @param idAceitar Identificador do solicitante da amizade
     */
    void aceitarAmizade(int idAceitar) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.aceitarAmizade(this.getId(), idAceitar);
    }

    /**
     * Acessar o dao para remover a amizade solicitada ou realizada
     *
     * @param idAmizade Identificador do solicitante ou amigo
     */
    void removerAmizade(int idAmizade) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.removerAmizade(this.getId(), idAmizade);
    }
    
    /**
     * Acessar o dao para remover as amizades do usuário que está apagando a conta
     *
     * @param idAmizade Identificador do solicitante ou amigo
     */
    void removerTodasAmizades(int idUsuario) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.removerTodasAmizades(idUsuario);
    }
    
    /**
     * Método para salvar os tipos de texto em que o usuário se identifica
     * @param tiposTextoUsuario lista dos tipos de textos
     * @param idUsuario 
     */    
    void salvarTipoTextoUsuario(List <String> tiposTextoUsuario, int idUsuario){
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvarTipoTextoUsuario(tiposTextoUsuario,idUsuario);
    }
    
    /**
     * Exclui um registro de um tipo de texto em que o usuario nao se identifica mais
     * @param idUsuario
     * @param idTipoTexto 
     */
    void excluirTipoTextoUsuario(int idUsuario, int idTipoTexto){
        UsuarioDAO dao = new UsuarioDAO();
        dao.excluirTipoTextoUsuario(idUsuario, idTipoTexto);
    }
    
    /**
     * Exclui todos os registros de tipo de texto em que o usuario se identifica 
     * quando este apaga sua conta 
     * @param idUsuario 
     */
    void excluirTodosTipoTextoUsuario(int idUsuario){
        UsuarioDAO dao = new UsuarioDAO();
        dao.excluirTodosTipoTextoUsuario(idUsuario);
    }

    @Override
    public String toString() {
        return this.email;
    }

    /**
     * Verifica a existência do usuário no BD
     *
     * @param Email O email do usuário pesquisado
     * @return Um objeto Usuario contendo o usuário pesquisado
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja localizado
     * na base de dados
     */
    public static Usuario verificarExistenciaUsuario(String Email) throws DadosUsuarioInvalidoException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.verificarExistenciaUsuario(Email);
    }

    /**
     * Consiste os dados informados
     *
     * @throws DadosUsuarioInvalidoException Quando um ou mais dados estiverem
     * incorretos
     */
    public void consistirDados() throws DadosUsuarioInvalidoException {
        String regexNome = "^[a-zA-Zà-úÀ-Ú ]*$";
        if (!this.nome.trim().matches(regexNome)) {
            throw new DadosUsuarioInvalidoException("O nome informado é inválido!");
        }

        if (!this.sobrenome.trim().matches(regexNome)) {
            throw new DadosUsuarioInvalidoException("O sobrenome informado é inválido!");
        }

        String regexEmail = "^\\w+([-\\.]\\w+)*@\\w+([-.]\\w+)+$";
        if (!this.email.matches(regexEmail)) {
            throw new DadosUsuarioInvalidoException("O e-mail informado é inválido!");
        }
    }

    void enviarConviteEmail(String destinatario, String path) throws EmailException {
        UsuarioDAO dao = new UsuarioDAO();
        //registrar o envio do convite
        dao.registrarConvite(this.id, destinatario);
        //preencher a mensagem para novos usuarios
        Mensagem msn = new Mensagem();
        msn.setDestino(destinatario);
        msn.setTitulo("Solicitação de Amizade");
        msn.setMensagem("Você ainda não conhece o Escrita Colaborativa?\n\n"
                + "Venha logo conhecer, o usuario \b" + this.getNome()
                + " " + this.getSobrenome() + "\b está convidando você"
                + " para participar, para isso acesse o link "
                + path + " e faça logo o seu cadastro.");
        //enviar email para novos usuarios
        EmailUtils.enviaEmail(msn);
    }

    void verificarConvite(String email) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.verificarConvite(email);
    }

    /**
     * Acessa a persistencia para carregar o amigo passado no parametro
     * @param id Codigo do amigo
     * @return O amigo
     */
    public Usuario carregarAmigo(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.carregarAmigo(id);
    }

    /**
     * Acessa a persistencia para atualizar o status do usuario
     * @param status Codigo do status (Enum do banco)
     */
    public void atualizarStatus(int status) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizarStatus(this.getId(), status);
        this.setStatus(dao.carregarStatus(this.getId()));
    }

    /**
     * @return the tipostextos
     */
    public List<TipoTexto> getTipostextos() {
        return tipostextos;
    }

    /**
     * @param tipostextos the tipostextos to set
     */
    public void setTipostextos(List<TipoTexto> tipostextos) {
        this.tipostextos = tipostextos;
    }

    /**
     * @return the tipostextosRevisor
     */
    public List<TipoTexto> getTipostextosRevisor() {
        return tipostextosRevisor;
    }

    /**
     * @param tipostextosRevisor the tipostextosRevisor to set
     */
    public void setTipostextosRevisor(List<TipoTexto> tipostextosRevisor) {
        this.tipostextosRevisor = tipostextosRevisor;
    }
}
