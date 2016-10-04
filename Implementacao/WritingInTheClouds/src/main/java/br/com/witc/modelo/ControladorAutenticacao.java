/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.LoginInvalidoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author marcelo.lima
 */
public class ControladorAutenticacao {
    private Usuario usuario;
    private Perfil perfil;
    
    public ControladorAutenticacao() {
        this.usuario = new Usuario();
    }        

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return O usuário logado no sistema
     */
    public Usuario getUsuario() {
        return usuario;
    }

    
    /**     
     * @return O nome do usuário logado no sistema
     */
    public String getNomeUsuario() {
        return this.usuario.getNome().split(" ")[0];
    }
    /**
     * @return 
     */
    public String getTipoPerfil() {
        return perfil.getTipoPerfil().getTipoPerfil();
    }
    
    /**
     * @param perfil 
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    /**
     * Autentica um usuário no sistema
     * @param email O email do usuário
     * @param senha A senha do usuário     
     * @throws br.com.witc.excessao.LoginInvalidoException  Se o login for invalido            
     * @throws java.security.NoSuchAlgorithmException Caso o algorítimo SHA-256 não seja localizado          
     * @throws java.io.UnsupportedEncodingException Caso haja erro de codificação          
     */
    public void efetuarLogin(String email, String senha) 
            throws LoginInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {                
        this.setUsuario(Usuario.efetuarLogin(email, senha));
    }
    public void retornarPerfilUsuarioLogado(){
       this.setPerfil(Perfil.retornarPerfilUsuarioLogado(this.getUsuario()));
    }
}
