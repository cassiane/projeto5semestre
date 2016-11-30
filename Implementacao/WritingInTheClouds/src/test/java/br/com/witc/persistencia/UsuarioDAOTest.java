/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.Usuario;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author marcelo.lima
 */
public class UsuarioDAOTest {
    
    @InjectMocks
    UsuarioDAO instance = new UsuarioDAO();
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
            
    public UsuarioDAOTest() {
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
     * Test of ExcluirUsuario method, of class UsuarioDAO.
     */    
    @Test
    public void testExcluirUsuario() throws Exception {        
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);                                  
        instance.setSessao(sessao);
        Usuario usuario = new Usuario();        
        instance.ExcluirUsuario(usuario);        
        Mockito.verify(sessao, times(1)).saveOrUpdate(usuario);
    }        
}
