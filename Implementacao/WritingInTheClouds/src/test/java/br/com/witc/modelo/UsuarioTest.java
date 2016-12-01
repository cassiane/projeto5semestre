/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.UsuarioDAO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author vanderson
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioTest {
    
    @InjectMocks
    Usuario instance = new Usuario();
    
    @Mock
    UsuarioDAO usuarioDAO;
    
    @Mock
    Session sessao;
    
    @Mock
    SQLQuery sqlQuery;
    
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
//    @Test
//    public void testCadastrarUsuario() throws Exception {
//        System.out.println("cadastrarUsuario");
//        //PowerMockito.mockStatic(Usuario.class);
//        
//        instance.cadastrarUsuario();
//    }

    /**
     * Test of removerAmizade method, of class Usuario.
     */
    @Test
    public void testRemoverAmizade() {
        System.out.println("removerAmizade");
        instance.setId(1);
        instance.removerAmizade(2);
        Mockito.verify(usuarioDAO, Mockito.times(1)).removerAmizade(Mockito.anyInt(), Mockito.anyInt());
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
    }
    
}
