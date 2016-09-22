/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.TipoTextoException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class ControladorLivro {
    private Livro livro;

    public ControladorLivro() {
        this.livro = new Livro();
    }    
    
    /**
     * @return the livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    /**
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia     
     */
    public Map<String,List<Livro>> getBibliotecaVirtual() throws BibliotecaVirtualVaziaException {
        return this.livro.getBibliotecaVirtual();
    }
    
    /**     
     * @return Uma lista contendo os tipos de textos cadastrados no sistema
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema
     */
    public List<TipoTexto> getLstTipoTexto() throws TipoTextoException {
        TipoTexto tipoTexto = new TipoTexto();
        return tipoTexto.getLstTipoTexto();
    }    
    
    /**     
     * @param idLivro O id do livro
     * @return O array de byte que representa a imagem
     */
    public byte[] getCapaPorId(int idLivro) {
        return this.livro.getCapaPorId(idLivro);
    }
}
