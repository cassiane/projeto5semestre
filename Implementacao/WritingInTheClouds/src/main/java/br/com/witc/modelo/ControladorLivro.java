/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.bean.LivroBean;
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
     * Persiste um novo livro na BD
     * @param livro O livro a ser persistido
     * @param finalizado True, se o livro já foi finalizado pelo usuário ou false, caso contrário
     * @param perfil O perfil do usuário criador do livro
     */
    public void criarLivro(Livro livro, boolean finalizado, Perfil perfil){    
        if (finalizado) {
            HistoricoLivro historicoLivro = new HistoricoLivro();
            historicoLivro.finalizarLivroUsuario(livro, perfil);
            
            boolean terminado = true;
            for (HistoricoLivro hl : historicoLivro.listarHistoricoLivro(livro.getId())) {
                if (hl.getDataConclusao() == null) {
                    terminado = false;
                    break;
                }
            }
            
            if (terminado) {
                livro.setDisponivelBiblioteca(true);
            }
        }
        this.livro.criarLivro(livro);
    }
    
    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de pesquisa
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a Biblioteca Virtual esteja vazia     
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema    
     */
    public Map<String,List<Livro>> carregaBibliotecaVirtual() 
            throws BibliotecaVirtualVaziaException, TipoTextoException {        
        Map<String,List<Livro>> tmpMap = new HashMap();
                
        for (TipoTexto tp : getLstTipoTexto()) {
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
        List<Livro> tmpLivros;
        for (TipoTexto tp : tipoTexto.getLstTipoTexto()) {
            try {
                if ((campoPesquisa.equals(LivroBean.ITEM_PESQUISA_TIPO_TEXTO)) && 
                   (!tp.getTipoTexto().toUpperCase().equals(valorPesquisa.toUpperCase()))) {
                    tmpLivros = this.livro.listarLivrosPorTipoTexto(tp, campoPesquisa, valorPesquisa);
                    if ((tmpLivros != null) && (!tmpLivros.isEmpty())) {
                        tmpMap.put(tp.getTipoTexto(), tmpLivros);
                    }                
                }
            } catch (BibliotecaVirtualVaziaException ex) {}
        }
        
        if (tmpMap.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro encontrado com os critérios informados.");
        }
        return tmpMap;
    }
    
    /**
     * Verifica se o livro está disponível para edição do usuário logado
     * @param idLivro O Livro
     * @param idPerfil O id do perfil do usuário
     * @return True, caso o livro esteja disponível para edição e false, caso contrário
     */
    public boolean estaDisponivelEdicaoUsuario(int idLivro, int idPerfil) {        
        HistoricoLivro historicoLivro = new HistoricoLivro();
        return this.livro.estaDisponivelEdicaoUsuario(idLivro, idPerfil) &&
                !historicoLivro.estaFinalizadoUsuario(idLivro, idPerfil);
    }    
}