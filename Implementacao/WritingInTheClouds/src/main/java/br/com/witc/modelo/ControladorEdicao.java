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

    public List<Perfil> carregarListaAmigoEditor() {
        Perfil perfil = new Perfil();
        return perfil.carregarListaAmigoEditor();
    }

    public void convidarAmigoEditor(Perfil usuario, List<Perfil> convidado, Livro livro) {
        ConvidadoPerfil convidar = new ConvidadoPerfil(Calendar.getInstance());
        convidar.setIdPerfil(usuario);
        convidar.setIdLivro(livro);
        for (Perfil p : convidado) {
            convidar.setIdPerfilConvidado(p);
            convidar.salvar();
        }
    }
    
}
