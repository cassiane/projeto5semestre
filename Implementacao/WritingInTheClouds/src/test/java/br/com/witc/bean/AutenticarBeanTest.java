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
 * @author 10070133
 */
public class AutenticarBeanTest {        
    
    public AutenticarBeanTest() {
    }
    
    @InjectMocks
    AutenticarBean instance = new AutenticarBean();
    
    @Mock
    ControladorAutenticacao controlador;
    
    @Mock
    Perfil perfil;
    
    @Mock
    Usuario usuario;
    
    @Mock
    TipoPerfil tipoPerfil;
    
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
     * Test of isPerfilEditor method, of class AutenticarBean.
     */
    @Test
    public void testIsPerfilEditor() {                                
        Mockito.when(controlador.getUsuario()).thenReturn(usuario); 
        Mockito.when(perfil.carregarPerfil(usuario)).thenReturn(perfil);                
        Mockito.when(perfil.getTipoPerfil()).thenReturn(tipoPerfil);        
        Mockito.when(tipoPerfil.getId()).thenReturn(1);
        boolean expResult = true;
        boolean result = instance.isPerfilEditor();
        assertEquals(expResult, result);
    }
}
