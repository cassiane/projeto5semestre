/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Erick
 */
public class TipoPerfilTest {
    
    
    @InjectMocks
    TipoPerfil instance = new TipoPerfil();
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;   
            
    @Mock
    Usuario usuario;
    
    public TipoPerfilTest() {
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

    @Test
    public void testCadastraTipoPerfil(){
    Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
    Usuario user = new Usuario();
    instance.cadastrarTipoPerfil();
    Mockito.verify(instance,times(1)).cadastrarTipoPerfil();
    } 
}
