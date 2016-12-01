/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.LoginInvalidoException;
import br.com.witc.modelo.ControladorAutenticacao;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070187
 */
public class AutenticarBeanTest {        
    
    public AutenticarBeanTest() {
    }
    
    @InjectMocks
    AutenticarBean instance = new AutenticarBean();
    
    @Mock
    AutenticarBean autenticar;
    
    @Mock
    ControladorAutenticacao controlador;
    
    @Mock
    Perfil perfil;
    
    @Mock
    Usuario usuario;
    
    @Mock
    TipoPerfil tipoPerfil;     
    
    @Mock 
    UsuarioDAO dao;
    
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
    public void testIsPerfilEditorTrue() {                                
        Mockito.when(controlador.getUsuario()).thenReturn(usuario);             
        Mockito.when(controlador.carregarPerfil(usuario)).thenReturn(perfil);
        Mockito.when(perfil.getTipoPerfil()).thenReturn(tipoPerfil);        
        Mockito.when(tipoPerfil.getId()).thenReturn(1);
        boolean expResult = true;
        boolean result = instance.isPerfilEditor();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsPerfilEditorFalse() {                                
        Mockito.when(controlador.getUsuario()).thenReturn(usuario);             
        Mockito.when(controlador.carregarPerfil(usuario)).thenReturn(perfil);
        Mockito.when(perfil.getTipoPerfil()).thenReturn(tipoPerfil);        
        Mockito.when(tipoPerfil.getId()).thenReturn(0);
        boolean expResult = false;
        boolean result = instance.isPerfilEditor();
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testeEfetuarLogin() throws LoginInvalidoException, NoSuchAlgorithmException, UnsupportedEncodingException{        
        instance.setEmail("email");
        instance.setSenha("senha");
        instance.efetuarLogin();
        Mockito.verify(controlador,times(1)).efetuarLogin(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(controlador,times(1)).retornarPerfilPadraoUsuarioLogado();
        Mockito.verify(controlador,times(1)).atualizarStatusUsuario(Mockito.anyInt());
    }
}
