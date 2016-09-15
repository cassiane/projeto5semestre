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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    private String senha;

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
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    /**
     * Autentica um usuário no sistema
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso os dados informados sejam inválidos
     * @throws java.security.NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado
     * @throws java.io.UnsupportedEncodingException Caso haja erro de codificação
     */
    public static Usuario efetuarLogin(String email, String senha) 
            throws LoginInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String hashSenha = criarHashSenha(senha);
        return usuarioDAO.efetuarLogin(email, hashSenha);
    }

    /**
     * Cria O hash da senha do usuário utilizando o algorítimo SHA-256
     * @param senha A string que servira de base para criacao do hash
     * @return O hash criar
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     */
    public static String criarHashSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");        

        md.update(senha.getBytes("UTF-8")); 
        byte[] digest = md.digest();
        
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    /**
     * Persiste usuario no banco     
     * @throws DadosUsuarioInvalidoException Caso o usuário já esteja cadastrado no sistema
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     * @throws br.com.witc.excessao.UsuarioInvalidoException Caso usuário já exista no BD
     */
    public void cadastrarUsuario() throws DadosUsuarioInvalidoException, 
            NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioInvalidoException{
       UsuarioDAO dao = new UsuarioDAO();
       setSenha(criarHashSenha(this.senha));
       dao.salvarUsuario(this);
    }

    /**
     * Acessar o dao para buscar os amigos
     * @return Lista de amigos
     */
    public List<Usuario> listarAmigos() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarAmigos(this.getId());
    }

    /**
     * Acessa o dao para buscar as sugestões
     * @return Lista de sugestão de amigos
     */
    public List<Usuario> listarSugestao() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarSugestao(this.getId());
    }
    
    /**
     * Acessar o dao para buscar os usuarios
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarUsuarios();
    }

    /**
     * Acessar o dao para registrar uma solicitação de amizade
     * @param idSugestao Identificador do amigo a ser solicitado
     */
    public void solicitarAmizade(int idSugestao) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.solicitarAmizade(this.getId(), idSugestao);
    }

    /**
     * Acessar o dao para buscar a lista de solicitações de amizade do usuario
     * @return Lista de solicitação de amizade
     */
    public List<Usuario> listarSolicitacao() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarSolicitacao(this.getId());
    }

    /**
     * Acessar o dao para registrar o aceite de amizade
     * @param idAceitar Identificador do solicitante da amizade
     */
    void aceitarAmizade(int idAceitar) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.aceitarAmizade(this.getId(), idAceitar);
    }

    /**
     * Acessar o dao para remover a amizade solicitada ou realizada
     * @param idAmizade Identificador do solicitante ou amigo
     */
    void removerAmizade(int idAmizade) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.removerAmizade(this.getId(), idAmizade);
    }

    @Override
    public String toString() {
        return this.email;
    }
    
    /**
     * Verifica a existência do usuário no BD
     * @param Email O email do usuário pesquisado
     * @return Um objeto Usuario contendo o usuário pesquisado
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja localizado na base de dados
     */
    public static Usuario verificarExistenciaUsuario(String Email) throws DadosUsuarioInvalidoException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.verificarExistenciaUsuario(Email);
    }       
    
    /**
     * Consiste os dados informados     
     * @throws DadosUsuarioInvalidoException Quando um ou mais dados estiverem incorretos
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
}
