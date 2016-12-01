/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

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
public class ControladorAutenticacaoTest {
    @InjectMocks
    ControladorAutenticacao instance = new ControladorAutenticacao();
    
    @Mock
    Perfil perfil;
    
    public ControladorAutenticacaoTest() {
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
     * Test of carregarPerfilPorId method, of class ControladorAutenticacao.
     */
    @Test
    public void testCarregarPerfilPorId() {
        Mockito.when(perfil.carregarPerfilPorId(Mockito.anyInt())).thenReturn(perfil);        
        Perfil expResult = perfil;
        Perfil result = instance.carregarPerfilPorId(Mockito.anyInt());
        assertEquals(expResult, result);        
    }
}
