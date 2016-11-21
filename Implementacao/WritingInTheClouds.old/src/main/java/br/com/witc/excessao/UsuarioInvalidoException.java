/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.excessao;

/**
 *
 * @author marcelo.lima
 */
public class UsuarioInvalidoException extends Exception {

    public UsuarioInvalidoException() {
    }

    public UsuarioInvalidoException(String message) {
        super(message);
    }

    public UsuarioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioInvalidoException(Throwable cause) {
        super(cause);
    }

    public UsuarioInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
