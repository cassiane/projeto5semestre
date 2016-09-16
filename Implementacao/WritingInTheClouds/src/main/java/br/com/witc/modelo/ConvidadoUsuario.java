/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author cstads
 */
@Entity
public class ConvidadoUsuario implements Serializable {
    @Id
    private int idUsuario;
    private String emailConvidado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataSolicitacao;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailConvidado() {
        return emailConvidado;
    }

    public void setEmailConvidado(String emailConvidado) {
        this.emailConvidado = emailConvidado;
    }

    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public ConvidadoUsuario() {
    }

    public ConvidadoUsuario(int idUsuario, String emailConvidado) {
        this.idUsuario = idUsuario;
        this.emailConvidado = emailConvidado;
        this.dataSolicitacao = Calendar.getInstance();
    }
}
