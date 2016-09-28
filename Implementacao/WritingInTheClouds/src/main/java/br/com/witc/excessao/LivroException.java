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
public class LivroException extends Exception {

    public LivroException() {
    }

    public LivroException(String message) {
        super(message);
    }

    public LivroException(String message, Throwable cause) {
        super(message, cause);
    }

    public LivroException(Throwable cause) {
        super(cause);
    }

    public LivroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
