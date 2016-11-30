/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author vanderson
 */
public class HistoricoLivroTest {
    
    @InjectMocks
    HistoricoLivro instance = new HistoricoLivro();
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    HistoricoLivroDAO historicoLivroDAO;
    
    public HistoricoLivroTest() {
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
     * Test of carregarHistoricoLivroUsuario method, of class HistoricoLivro.
     */
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
    
}
