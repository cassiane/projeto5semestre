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
public class BibliotecaVirtualVaziaException extends Exception {

    public BibliotecaVirtualVaziaException() {
    }

    public BibliotecaVirtualVaziaException(String message) {
        super(message);
    }

    public BibliotecaVirtualVaziaException(String message, Throwable cause) {
        super(message, cause);
    }

    public BibliotecaVirtualVaziaException(Throwable cause) {
        super(cause);
    }

    public BibliotecaVirtualVaziaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
