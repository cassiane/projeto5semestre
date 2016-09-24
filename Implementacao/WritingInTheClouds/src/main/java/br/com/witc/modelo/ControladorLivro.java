/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.TipoTextoException;
import java.util.HashMap;
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
    
    /**
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia     
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema    
     */
    public Map<String,List<Livro>> carregaBibliotecaVirtual() 
            throws BibliotecaVirtualVaziaException, TipoTextoException {        
        Map<String,List<Livro>> tmpMap = new HashMap();
        
        TipoTexto tipoTexto = new TipoTexto();
        for (TipoTexto tp : tipoTexto.getLstTipoTexto()) {
            try {
                tmpMap.put(tp.getTipoTexto(), this.livro.listarLivrosPorTipoTexto(tp));
            } catch (BibliotecaVirtualVaziaException ex) {}
        }
        
        if (tmpMap.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        return tmpMap;
    }
    
    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de pesquisa
     * @param campoPesquisa O campo a ser pesquisado
     * @param valorPesquisa O valor a ser pesquisado
     * @return Um objeto Map, com os livros encontrados
     * @throws BibliotecaVirtualVaziaException Caso não sejam encontrados livros
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema
     */
    public Map<String, List<Livro>> carregaBibliotecaVirtualPesquisa(String campoPesquisa, String valorPesquisa) 
            throws BibliotecaVirtualVaziaException, TipoTextoException {
        Map<String,List<Livro>> tmpMap = new HashMap();
        
        TipoTexto tipoTexto = new TipoTexto();
        for (TipoTexto tp : tipoTexto.getLstTipoTexto()) {
            try {
                tmpMap.put(tp.getTipoTexto(), this.livro.listarLivrosPorTipoTexto(tp, campoPesquisa, valorPesquisa));
            } catch (BibliotecaVirtualVaziaException ex) {}
        }
        
        if (tmpMap.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        return tmpMap;
    }
}
