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
     * Autentica um usuario no sistema
     * @param email O email do usu�rio
     * @param senha A senha do usu�rio
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso os dados informados sejam inv�lidos
     */
    public static Usuario efetuarLogin(String email, String senha) throws LoginInvalidoException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.efetuarLogin(email, senha);
    }
}
