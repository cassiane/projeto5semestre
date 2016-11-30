/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.UsuarioDAO;
import java.util.Calendar;
import java.util.List;
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
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vanderson
 */
public class UsuarioTest {
    
    @InjectMocks
    Usuario instance = new Usuario();
    
    @Mock
    UsuarioDAO usuarioDAO;
    
    public UsuarioTest() {
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
     * Test of cadastrarUsuario method, of class Usuario.
     */
    @Test
    public void testCadastrarUsuario() throws Exception {
        System.out.println("cadastrarUsuario");
        Usuario instance = new Usuario();
        instance.cadastrarUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerAmizade method, of class Usuario.
     */
    @Test
    public void testRemoverAmizade() {
        System.out.println("removerAmizade");
        int idAmizade = 0;
        Usuario instance = new Usuario();
        instance.removerAmizade(idAmizade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarExistenciaUsuario method, of class Usuario.
     */
    @Test
    public void testVerificarExistenciaUsuario() throws Exception {
        System.out.println("verificarExistenciaUsuario");
        String Email = "";
        Usuario expResult = null;
        Usuario result = Usuario.verificarExistenciaUsuario(Email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
