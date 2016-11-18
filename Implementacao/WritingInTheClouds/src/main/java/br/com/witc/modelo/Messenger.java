/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.MessengerDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author vanderson
 */
@Entity
public class Messenger {

    @Id
    @GeneratedValue
    private int idMessenger;
    @ManyToOne
    @JoinColumn(name = "idSend")
    private Usuario idSend;
    @ManyToOne
    @JoinColumn(name = "idReceive")
    private Usuario idReceive;
    private String msn;
    private byte[] msnFoto;
    private boolean seen;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar dateSend;

    public int getIdMessenger() {
        return idMessenger;
    }

    public void setIdMessenger(int idMessenger) {
        this.idMessenger = idMessenger;
    }

    public Usuario getIdSend() {
        return idSend;
    }

    public void setIdSend(Usuario idSend) {
        this.idSend = idSend;
    }

    public Usuario getIdReceive() {
        return idReceive;
    }

    public void setIdReceive(Usuario idReceive) {
        this.idReceive = idReceive;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public byte[] getMsnFoto() {
        return msnFoto;
    }

    public void setMsnFoto(byte[] msnFoto) {
        this.msnFoto = msnFoto;
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

    public void setDateSend(Calendar dateSend) {
        this.dateSend = dateSend;
    }
    
    public void enviarMsn() {
        if (this.dateSend == null) {
            this.dateSend = Calendar.getInstance();
        }
        MessengerDAO dao = new MessengerDAO();
        dao.enviarMsn(this);
    }
    
    public List<Messenger> listarMsn(int send, int receive) {
        MessengerDAO dao = new MessengerDAO();
        List<Messenger> lstmsn = dao.listarMsn(send, receive);
        return lstmsn;
    }
}
