/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070187
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
    public void testSalvarLivro_3argsNaoFinalizado() {                
        controlador.salvarLivro(livro, false, perfil);        
        Mockito.verify(livro, times(1)).salvarLivro(livro);
    }
    
    @Test
    public void testSalvarHistorico() {        
        controlador.salvarHistorico(historicoLivro);
        Mockito.verify(historicoLivro, times(1)).salvarHistorico(historicoLivro);
    }
    
    @Test
    public void testNegarEdicao() {        
        controlador.negarEdicao(convidadoPerfil);
        Mockito.verify(convidadoPerfil, times(1)).negarEdicao();
    }
    
    @Test
    public void testCarregarPerfil() {
       Mockito.when(perfil.carregarPerfil(Mockito.any())).thenReturn(perfil);   
       assertEquals(controlador.carregarPerfil(usuario),perfil);
    }
	
    @Test
    public void testConvidarAmigoEditor() {
        List<Perfil> convidado = new ArrayList<>();
        convidado.add(perfil);        
        controlador.convidarAmigoEditor(perfil, convidado, livro);
        Mockito.verify(convidadoPerfil,times(1)).salvar();
    }
}
