/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.LivroDAO;
import java.util.ArrayList;
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
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070187
 */
public class LivroTest {
    
    @InjectMocks
    Livro instance = new Livro();
    
    @Mock
    LivroDAO livroDAO;
    @Mock 
    HistoricoLivro historicoLivro;
    
    public LivroTest() {
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
     * Test of estaDisponivelEdicaoUsuario method, of class Livro.
     */
    @Test
    public void testEstaDisponivelEdicaoUsuarioTrue() {        
        Mockito.when(livroDAO.estaDisponivelEdicaoUsuario(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Boolean.TRUE);
        boolean expResult = true;
        boolean result = instance.estaDisponivelEdicaoUsuario(Mockito.anyInt(), Mockito.anyInt());
        assertEquals(expResult, result);        
    }       
    
    @Test
    public void testEstaDisponivelEdicaoUsuarioFalse() {        
        Mockito.when(livroDAO.estaDisponivelEdicaoUsuario(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Boolean.FALSE);
        boolean expResult = false;
        boolean result = instance.estaDisponivelEdicaoUsuario(Mockito.anyInt(), Mockito.anyInt());
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testGetLstNomesCompletosAutores() {  
        List<HistoricoLivro> lstHistorico = new ArrayList<>();
        lstHistorico.add(historicoLivro);
        Mockito.when(historicoLivro.listarHistoricoLivro(Mockito.anyInt())).thenReturn(lstHistorico);
        Mockito.when(historicoLivro.getNomeUsuario()).thenReturn("CASSIANE");
        Mockito.when(historicoLivro.getSobrenomeUsuario()).thenReturn("SANTOS");
        String[][] arrNomesEsp = new String[1][2]; 
        arrNomesEsp[0][0] = "CASSIANE";
        arrNomesEsp[0][1] = "SANTOS";
        
        assertArrayEquals(arrNomesEsp, instance.getLstNomesCompletosAutores());        
    }
}
