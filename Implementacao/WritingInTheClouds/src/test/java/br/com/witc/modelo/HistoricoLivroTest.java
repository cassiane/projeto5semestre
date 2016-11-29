/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

/**
 *
 * @author cassiane.santos
 */
public class HistoricoLivroTest {
    @Mock 
    HistoricoLivro historico; 
    
    @Before
    public void setUp() {
        historico = Mockito.mock(HistoricoLivro.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvarHistorico method, of class HistoricoLivro.
     
    @Test
    public void testSalvarHistorico() {
        System.out.println("salvarHistorico");
        HistoricoLivro hist = null;
        HistoricoLivro instance = new HistoricoLivro();
        instance.salvarHistorico(hist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarHistoricoLivro method, of class HistoricoLivro.
     
    @Test
    public void testListarHistoricoLivro() {
        System.out.println("listarHistoricoLivro");
        int idLivro = 0;
        HistoricoLivro instance = new HistoricoLivro();
        List<HistoricoLivro> expResult = null;
        List<HistoricoLivro> result = instance.listarHistoricoLivro(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarHistoricoLivroUsuario method, of class HistoricoLivro.
     
    @Test
    public void testCarregarHistoricoLivroUsuario() {
        System.out.println("carregarHistoricoLivroUsuario");
        Livro livro = null;
        Perfil perfil = null;
        HistoricoLivro instance = new HistoricoLivro();
        HistoricoLivro expResult = null;
        HistoricoLivro result = instance.carregarHistoricoLivroUsuario(livro, perfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaFinalizadoUsuario method, of class HistoricoLivro.
     */
    @Test
    public void testEstaFinalizadoUsuario() {
        int idLivro = 1; 
        int idPerfil = 1;
        historico.estaFinalizadoUsuario(idLivro, idPerfil);
        Mockito.verify(historico,times(1)).estaFinalizadoUsuario(idLivro, idPerfil);
    }

    /**
     * Test of estaFinalizadoRevisaoUsuario method, of class HistoricoLivro.
     
    @Test
    public void testEstaFinalizadoRevisaoUsuario() {
        System.out.println("estaFinalizadoRevisaoUsuario");
        int idLivro = 0;
        int idPerfil = 0;
        HistoricoLivro instance = new HistoricoLivro();
        boolean expResult = false;
        boolean result = instance.estaFinalizadoRevisaoUsuario(idLivro, idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalizarLivroUsuario method, of class HistoricoLivro.
     
    @Test
    public void testFinalizarLivroUsuario() {
        System.out.println("finalizarLivroUsuario");
        Livro livro = null;
        Perfil perfil = null;
        HistoricoLivro instance = new HistoricoLivro();
        instance.finalizarLivroUsuario(livro, perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarHistoricoConvite method, of class HistoricoLivro.
     
    @Test
    public void testSalvarHistoricoConvite() {
        System.out.println("salvarHistoricoConvite");
        int perfil = 0;
        int livro = 0;
        HistoricoLivro instance = new HistoricoLivro();
        instance.salvarHistoricoConvite(perfil, livro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
