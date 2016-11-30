/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.excessao;

/**
 *
 * @author 10070187
 */
public class DadosUsuarioInvalidoException extends Exception{
    public DadosUsuarioInvalidoException() {
    }

    public DadosUsuarioInvalidoException(String message) {
        super(message);
    }

    public DadosUsuarioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DadosUsuarioInvalidoException(Throwable cause) {
        super(cause);
    }

    public DadosUsuarioInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
