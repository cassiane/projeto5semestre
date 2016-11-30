/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class ControladorLivroTest {
    @InjectMocks
    ControladorLivro controlador = new ControladorLivro();
    @Mock
    Livro livro;
    @Mock
    ConvidadoPerfil convidado;
    @Mock
    Perfil perfil;
    
    public ControladorLivroTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of salvarLivro method, of class ControladorLivro.
     */
    @Test
    public void testSalvarLivro_Livro() {
        controlador.salvarLivro(livro);
        Mockito.verify(livro).salvarLivro(livro);   
    }

    /**
     * Test of estaDisponivelRevisaoUsuario method, of class ControladorLivro.
     */
    @Test
    public void testEstaDisponivelRevisaoUsuario() {
        System.out.println("estaDisponivelRevisaoUsuario");
        Mockito.when(livro.estaDisponivelRevisaoUsuario(1, 1)).thenReturn(true);
        boolean expResult = true;
        boolean result = controlador.estaDisponivelRevisaoUsuario(1, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of aceitarEdicao method, of class ControladorLivro.
     */
    @Test
    public void testAceitarEdicao() {
        System.out.println("aceitarEdicao");
        convidado.aceitarEdicao(perfil, livro);
        Mockito.verify(this.convidado).aceitarEdicao(perfil, livro);
    }

}
