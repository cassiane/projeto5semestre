/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.excessao.BibliotecaVirtualVaziaException;
import br.com.witc.excessao.LivroException;
import br.com.witc.excessao.TipoTextoException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class ControladorLivro {

    private Livro livro;
    private HistoricoLivro historicoLivro;
    ConvidadoPerfil convidar;
    Perfil perfil;

    public ControladorLivro() {
        this.livro = new Livro();
        this.perfil = new Perfil();
        this.convidar = new ConvidadoPerfil(Calendar.getInstance());
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
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de
     * Textos cadastrados no sistema
     */
    public List<TipoTexto> getLstTipoTexto() throws TipoTextoException {
        TipoTexto tipoTexto = new TipoTexto();
        return tipoTexto.getLstTipoTexto();
    }

    /**
     * @param idLivro O id do livro
     * @return O array de byte que representa a imagem
     * @throws br.com.witc.excessao.LivroException Caso o livro não seja
     * encontrado
     */
    public byte[] getCapaPorId(int idLivro) throws LivroException {
        return this.livro.getCapaPorId(idLivro);
    }

    /**
     * Persiste um novo livro na BD
     *
     * @param livro O livro a ser persistido
     */
    public void salvarLivro(Livro livro) {
        this.livro.salvarLivro(livro);
    }

    /**
     * Persiste um novo livro na BD
     *
     * @param livro O livro a ser persistido
     * @param finalizado True, se o livro já foi finalizado pelo usuário ou
     * false, caso contrário
     * @param perfil O perfil do usuário criador do livro
     */
    public void salvarLivro(Livro livro, boolean finalizado, Perfil perfil) {
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
//        if(livro.isDisponivelRevisao()){
//            HistoricoLivro historicoLivro = new HistoricoLivro();
//            historicoLivro.setLivro(livro);
//            historicoLivro.setPerfil(perfil);
//        }
        this.livro.salvarLivro(livro);
    }

    /**
     * Carrega um livro
     *
     * @param idLivro O id do livro a ser carregado
     * @return Livro Um objeto livro
     * @throws br.com.witc.excessao.LivroException Caso o livro não seja
     * encontrado
     */
    public Livro carregarLivro(int idLivro) throws LivroException {
        return this.livro.carregarLivro(idLivro);
    }

    public List<Livro> listarLivrosPerfil(Perfil perfil) {
        return this.livro.listarLivrosPerfil(perfil);
    }

    public Perfil carregarPerfil(Usuario usuario) {        
        return perfil.carregarPerfil(usuario);
    }

    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de
     * pesquisa
     *
     * @return Um Map com os Livros da Biblioteca Virtual
     * @throws br.com.witc.excessao.BibliotecaVirtualVaziaException Caso a
     * Biblioteca Virtual esteja vazia
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de
     * Textos cadastrados no sistema
     */
    public Map<String, List<Livro>> carregaBibliotecaVirtual()
            throws BibliotecaVirtualVaziaException, TipoTextoException {
        Map<String, List<Livro>> tmpMap = new HashMap();

        for (TipoTexto tp : getLstTipoTexto()) {
            try {
                tmpMap.put(tp.getTipoTexto(), this.livro.listarLivrosPorTipoTexto(tp));
            } catch (BibliotecaVirtualVaziaException ex) {
            }
        }

        if (tmpMap.isEmpty()) {
            throw new BibliotecaVirtualVaziaException("Nenhum livro foi publicado até o momento.");
        }
        return tmpMap;
    }

    /**
     * @param idPerfil O id do perfil do usuário
     * @return Um map contendo o gênero e a avaliação
     */
    public Map<String, Float> getGenerosPreferidos(int idPerfil) {
        return this.livro.getGenerosPreferidos(idPerfil);
    }

    /**
     * Carrega os livros disponíveis na Biblioteca Virtual segundo critérios de
     * pesquisa
     *
     * @param valorPesquisa O valor a ser pesquisado
     * @return Um objeto Map, com os livros encontrados
     * @throws BibliotecaVirtualVaziaException Caso não sejam encontrados livros
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de
     * Textos cadastrados no sistema
     */
    public Map<String, List<Livro>> carregaBibliotecaVirtualPesquisa(String valorPesquisa)
            throws BibliotecaVirtualVaziaException, TipoTextoException {
        return this.livro.carregaBibliotecaVirtualPesquisa(valorPesquisa);
    }

    /**
     * Verifica se o livro está disponível para edição do usuário logado
     *
     * @param idLivro O Livro
     * @param idPerfil O id do perfil do usuário
     * @return True, caso o livro esteja disponível para edição e false, caso
     * contrário
     */
    public boolean estaDisponivelEdicaoUsuario(int idLivro, int idPerfil) {
        HistoricoLivro historicoLivro = new HistoricoLivro();
        return this.livro.estaDisponivelEdicaoUsuario(idLivro, idPerfil)
                && !historicoLivro.estaFinalizadoUsuario(idLivro, idPerfil);
    }

    public boolean estaDisponivelRevisaoUsuario(int idLivro, int idPerfil) {

        return this.livro.estaDisponivelRevisaoUsuario(idLivro, idPerfil);
    }

    public void salvarHistorico(HistoricoLivro hist) {
        this.historicoLivro.salvarHistorico(hist);
    }

    public TipoStatus carregarTipoStatus(int id) {
        TipoStatus tipoStatus = new TipoStatus();
        return tipoStatus.carregarTipoStatus(id);
    }

    /**
     * Buscar a lista de amigos editores
     *
     * @return a lista de amigos
     */
    public List<Perfil> carregarListaAmigoEditor(Perfil perfil, int idLivro) {
        return perfil.carregarListaAmigoEditor(idLivro);
    }

    /**
     * Metodo para preencher a solicitação de edição
     *
     * @param usuario perfil ativo do usuario
     * @param convidado perfil do usuario convidado
     * @param livro livro a ser compartilhado
     */
    public void convidarAmigoEditor(Perfil usuario, List<Perfil> convidado, Livro livro) {
        convidar.setIdPerfil(usuario);
        convidar.setIdLivro(livro);
        for (Perfil p : convidado) {
            convidar.setIdPerfilConvidado(p);
            convidar.salvar();
        }
    }

    /**
     * Buscar a lista de solicitações
     *
     * @param perfilUsuario perfil do usuario ativo
     * @return retorna a lista de solicitação de edição do usuario ativo
     */
    public List<ConvidadoPerfil> carregarListaSolicitacaoEdicao(Perfil perfilUsuario) {
        ConvidadoPerfil lista = new ConvidadoPerfil();
        lista.setIdPerfilConvidado(perfilUsuario);
        return lista.carregarlista();
    }

    /**
     * Metodo para aceitar a solicitação de edição
     *
     * @param editarLivro livro a ser compartilhado
     */
    //public void aceitarEdicao(ConvidadoPerfil editarLivro) {
    public void aceitarEdicao(Perfil perfil, Livro livro) {
        ConvidadoPerfil editar = new ConvidadoPerfil();
        editar.aceitarEdicao(perfil, livro);
    }

    /**
     * Metodo para negar a solicitação de edição
     * @param editarLivro livro negado para compartilhamento
     */
    public void negarEdicao(ConvidadoPerfil editarLivro) {
        editarLivro.negarEdicao();
    }

    /**
     * @param idPerfil O id do perfil do usuário
     * @return Uma lista de livros publicados pelo usuário
     */
    public List<Livro> listarLivrosPublicadosPerfil(int idPerfil) {
        return this.livro.listarLivrosPublicadosPerfil(idPerfil);
    }

    public List<Livro> listarLivrosRevisao() {
        return this.livro.listarLivrosRevisao();
    }

    public byte[] downloadEpub(Livro livro, String pathEbub) throws IOException {
        EPub epub = new EPub();
        epub.setPathEpub(pathEbub);
        epub.setLivro(livro);
        return epub.downloadEPub();
    }
}
