/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.LoginInvalidoException;

/**
 *
 * @author marcelo.lima
 */
public class ControladorAutenticacao {
    private Usuario usuario;

    public ControladorAutenticacao() {
        this.usuario = new Usuario();
    }        
    
    /**     
     * @return O nome do usu�rio logado no sistema
     */
    public String getNomeUsuario() {
        return this.usuario.getNome();
    }
    
    /**
     * Autentica um usu�rio no sistema
     * @param email O email do usu�rio
     * @param senha A senha do usu�rio
     * @return A p�gina a ser visualizada pelo usu�rio ap�s o login              
     * @throws br.com.witc.excessao.LoginInvalidoException  Se o login for invalido            
     */
    public String efetuarLogin(String email, String senha) throws LoginInvalidoException {                
        this.usuario = Usuario.efetuarLogin(email, senha);
        
        if (this.usuario != null)
            return "timeline";
        return null;
    }
}
