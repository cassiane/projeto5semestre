/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.excessao;

/**
 *
 * @author 10070129
 */
public class TipoGeneroException extends Exception {
    
    public TipoGeneroException() {
    }

    public TipoGeneroException(String message) {
        super(message);
    }

    public TipoGeneroException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipoGeneroException(Throwable cause) {
        super(cause);
    }

    public TipoGeneroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
