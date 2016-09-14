/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author 00026108
 */
@Entity
@Table(name="perfil_tem_livro")
public class PerfilTemLivro implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name="idPerfil")
    private Perfil Perfil;
    @OneToOne
    @JoinColumn(name="idLivro")
    private Livro Livro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return Perfil;
    }

    public void setPerfil(Perfil Perfil) {
        this.Perfil = Perfil;
    }

    public Livro getLivro() {
        return Livro;
    }

    public void setLivro(Livro Livro) {
        this.Livro = Livro;
    }
}