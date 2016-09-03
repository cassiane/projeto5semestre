/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.LoginInvalidoException;
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
import static javax.persistence.TemporalType.DATE;

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
    @Temporal(DATE)
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
     * @return O hash criar
     * @throws NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado
     * @throws UnsupportedEncodingException Caso haja erro de codificação
     */
    private static String criarHashSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
}
