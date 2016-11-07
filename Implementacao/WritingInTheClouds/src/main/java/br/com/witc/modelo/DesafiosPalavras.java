/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.DesafiosPalavrasDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author cassiane.santos
 */
@Entity
public class DesafiosPalavras implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String palavra;
    @ManyToOne
    @JoinColumn(name="idDesafio")
    private Desafios desafio;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }    
    
    public List<String> listarDesafiosPalavras() throws Exception{
        DesafiosPalavrasDAO dao = new DesafiosPalavrasDAO();
        return dao.listarDesafiosPalavras();
    }

    /**
     * @return the desafio
     */
    public Desafios getDesafio() {
        return desafio;
    }

    /**
     * @param desafio the desafio to set
     */
    public void setDesafio(Desafios desafio) {
        this.desafio = desafio;
    }
    
    /**
     * Salvar a lista de palavras do desafio e salva o desafio do usuário
     * @param listaPalavras
     * @param idDesafiante
     * @param idDesafio
     * @param idAmigo 
     */
    public void salvarDesafio(List<String> listaPalavras, int idDesafiante, int idDesafio, int idAmigo) {
        DesafiosPalavrasDAO dao = new DesafiosPalavrasDAO();
        dao.salvarDesafio(listaPalavras, idDesafiante, idDesafio, idAmigo);
    }
}
