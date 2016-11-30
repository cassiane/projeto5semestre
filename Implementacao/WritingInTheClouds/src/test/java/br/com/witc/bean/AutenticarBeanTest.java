/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorAutenticacao;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.modelo.Usuario;
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
 * @author vanderson
 */
public class AutenticarBeanTest {        
    
    @InjectMocks
    AutenticarBean instance = new AutenticarBean();
    
    @Mock
    ControladorAutenticacao controlador;
    
    public AutenticarBeanTest() {
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
     * Test of getNumeroAmigosUsuarioLogado method, of class AutenticarBean.
     */
    @Test
    public void testGetNumeroAmigosUsuarioLogado() {
        System.out.println("getNumeroAmigosUsuarioLogado");
        Mockito.when(controlador.getNumeroAmigosUsuarioLogado())
                .thenReturn(5)
                .thenReturn(1)
                .thenReturn(0);
        assertEquals("5 amigos", instance.getNumeroAmigosUsuarioLogado());
        assertEquals("1 amigo", instance.getNumeroAmigosUsuarioLogado());
        assertEquals("0 amigos", instance.getNumeroAmigosUsuarioLogado());
        Mockito.verify(controlador, Mockito.times(3)).getNumeroAmigosUsuarioLogado();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
