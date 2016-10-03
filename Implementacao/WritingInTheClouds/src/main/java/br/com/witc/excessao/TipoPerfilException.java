/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.excessao;

/**
 *
 * @author Cassiane 
 */
public class TipoPerfilException extends Exception {
    public TipoPerfilException() {
    }

    public TipoPerfilException(String message) {
        super(message);
    }

    public TipoPerfilException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipoPerfilException(Throwable cause) {
        super(cause);
    }

    public TipoPerfilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
