/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorMessenger;
import br.com.witc.modelo.Messenger;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author vanderson
 */
@ManagedBean
@RequestScoped
public class MessengerBean {
    private final ControladorMessenger controlador;
    @ManagedProperty(value = "#{param.idsend}")
    private int idSend;
    @ManagedProperty(value = "#{param.idreceive}")
    private int idReceive;
    @ManagedProperty(value = "#{param.messengermsn}")
    private String msn;
    private boolean seen;
    private Calendar dateSend;

    public int getIdSend() {
        return idSend;
    }

    public void setIdSend(int idSend) {
        this.idSend = idSend;
    }

    public int getIdReceive() {
        return idReceive;
    }

    public void setIdReceive(int idReceive) {
        this.idReceive = idReceive;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Calendar getDateSend() {
        return dateSend;
    }

    public MessengerBean() {
        this.controlador = new ControladorMessenger();
    }

    public void enviarMsn() {
        if (((this.idSend > 0) && (this.idReceive > 0)) && ((!this.msn.isEmpty())))
            this.controlador.enviaMsn(this.idSend, this.idReceive, this.msn);
    }

    public List<Messenger> listarMsn(int idsend, int idreceive) {
        return this.controlador.listarMsn(idsend, idreceive);
    }
}
