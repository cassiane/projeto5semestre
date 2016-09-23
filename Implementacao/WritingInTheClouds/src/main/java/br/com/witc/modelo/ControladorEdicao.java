/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;

/**
 *
 * @author root
 */
public class ControladorEdicao {
    Livro livro;

    public List<Usuario> carregarListaAmigoEditor() {
        Perfil perfil = new Perfil();
        return perfil.carregarListaAmigoEditor();
    }
    
}
