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
 * @author Marcelo
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
     * Test of aceitarAmizade method, of class Usuario.
     */
    @Test
    public void testAceitarAmizade() {        
        instance.aceitarAmizade(1);
        Mockito.verify(usuarioDAO, times(1)).aceitarAmizade(instance.getId(), 1);
    }
    
    @Test
    public void testExcluirTodosTipoTextoUsuario() {
        instance.excluirTodosTipoTextoUsuario(instance.getId());
        Mockito.verify(usuarioDAO, times(1)).excluirTodosTipoTextoUsuario(instance.getId());
    }
}
