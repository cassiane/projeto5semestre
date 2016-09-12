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
public class LinkRecuperacaoInvalidoException extends Exception {

    public LinkRecuperacaoInvalidoException() {
    }

    public LinkRecuperacaoInvalidoException(String message) {
        super(message);
    }

    public LinkRecuperacaoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkRecuperacaoInvalidoException(Throwable cause) {
        super(cause);
    }

    public LinkRecuperacaoInvalidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
