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
public class TimelineException extends Exception {

    public TimelineException() {
    }

    public TimelineException(String message) {
        super(message);
    }

    public TimelineException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimelineException(Throwable cause) {
        super(cause);
    }

    public TimelineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
