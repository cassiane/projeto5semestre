/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;

/**
 *
 * @author vanderson
 */
public class ControladorMessenger {
    private Messenger controlmsn;

    public Messenger getControlmsn() {
        return controlmsn;
    }

    public void setMsn(Messenger msn) {
        this.controlmsn = msn;
    }

    public void enviaMsn(int send, int receive, String msn) {
        Messenger sendMsn = new Messenger();
        sendMsn.setIdSend(new Usuario().carregarAmigo(send));
        sendMsn.setIdReceive(new Usuario().carregarAmigo(receive));
        if (msn.length() > 0)
            sendMsn.setMsn(msn);
        sendMsn.enviarMsn();
    }
    
    public List<Messenger> listarMsn(int send, int receive) {
        return new Messenger().listarMsn(send, receive);
    }
}
