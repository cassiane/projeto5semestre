/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author root
 */
public class ControladorEdicao {
    Livro livro;

    /**
     * Buscar a lista de amigos editores
     * @return a lista de amigos
     */
    public List<Perfil> carregarListaAmigoEditor(Perfil perfil) {
        return perfil.carregarListaAmigoEditor();
    }

    /**
     * Metodo para preencher a solicitação de edição
     * @param usuario perfil ativo do usuario
     * @param convidado perfil do usuario convidado
     * @param livro livro a ser compartilhado
     */
    public void convidarAmigoEditor(Perfil usuario, List<Perfil> convidado, Livro livro) {
        ConvidadoPerfil convidar = new ConvidadoPerfil(Calendar.getInstance());
        convidar.setIdPerfil(usuario);
        convidar.setIdLivro(livro);
        for (Perfil p : convidado) {
            convidar.setIdPerfilConvidado(p);
            convidar.salvar();
        }
    }
    
    /**
     * Buscar a lista de solicitações
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
     * @param editarLivro livro a ser compartilhado
     */
    public void aceitarEdicao(ConvidadoPerfil editarLivro) {
        editarLivro.aceitarEdicao();
    }
    
    /**
     * Metodo para negar a solicitação de edição
     * @param editarLivro livro negado para compartilhamento
     */
    public void negarEdicao(ConvidadoPerfil editarLivro) {
        editarLivro.negarEdicao();
    }
    
}
