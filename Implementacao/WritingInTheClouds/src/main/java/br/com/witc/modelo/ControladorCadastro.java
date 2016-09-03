/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.UsuarioInvalidoException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author marcelo.lima
 */
public class ControladorCadastro {
    private Usuario usuario;

    public ControladorCadastro(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    /**
     * Cadastra um usuário no sistemap
     * @param usuario O usuário a ser cadastrado no sistema
     * @return Uma string contendo a próxima página a ser enviada para o usuário
     * @throws br.com.witc.excessao.UsuarioInvalidoException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public String cadastrarUsuario(Usuario usuario) throws UsuarioInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException {
        usuario.CadastrarUsuario(usuario);        
        return "timeline";
    }   
    
}
