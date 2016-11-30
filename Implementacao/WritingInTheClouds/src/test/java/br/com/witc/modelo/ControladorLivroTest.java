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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author vanderson
 */
public class ControladorLivroTest {
    
    @InjectMocks
    ControladorLivro instance = new ControladorLivro();
    
    @Mock
    Livro livro;
    
    @Mock
    Perfil perfil;
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    ConvidadoPerfil convidadoPerfil;
    
    public ControladorLivroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of carregarLivro method, of class ControladorLivro.
     */
    @Test
    public void testCarregarLivro() throws Exception {
        System.out.println("carregarLivro");
        int idLivro = 0;
        ControladorLivro instance = new ControladorLivro();
        Livro expResult = null;
        Livro result = instance.carregarLivro(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaAmigoEditor method, of class ControladorLivro.
     */
    @Test
    public void testCarregarListaAmigoEditor() {
        System.out.println("carregarListaAmigoEditor");
        Perfil perfil = null;
        int idLivro = 0;
        ControladorLivro instance = new ControladorLivro();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.carregarListaAmigoEditor(perfil, idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
