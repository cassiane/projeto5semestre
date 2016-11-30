/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070142
 */
public class ControladorLivroTest {
    
    @InjectMocks
    ControladorLivro controlador = new ControladorLivro();
    
    @Mock
    Livro livro;    
    @Mock
    Perfil perfil;    
    @Mock
    HistoricoLivro historicoLivro;    
    @Mock
    ConvidadoPerfil convidadoPerfil;
    @Mock 
    Usuario usuario;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testEstaDisponivelEdicaoUsuario() {
        controlador.estaDisponivelEdicaoUsuario(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(controlador, times(1)).aceitarEdicao(perfil, livro);
    }

    @Test
    public void testCarregarListaSolicitacaoEdicao() {
        List<Perfil> edicao = new ArrayList<>();
        edicao.add(perfil);        
        controlador.carregarListaSolicitacaoEdicao(perfil);
        Mockito.verify(convidadoPerfil,times(1)).salvar();
    }
}
