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
 * @author 10070142
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
    
    @Test
    public void testTrocarPerfilUsuario(){
        Mockito.when(controlador.getUsuario()).thenReturn(usuario);             
        Mockito.when(controlador.carregarPerfil(usuario)).thenReturn(perfil);
        Mockito.when(perfil.getTipoPerfil()).thenReturn(tipoPerfil);        
        Mockito.when(tipoPerfil.getId()).thenReturn(0);
        boolean expResult = false;
        boolean result = instance.isPerfilEditor();
        assertEquals(expResult, result);
    }
}
