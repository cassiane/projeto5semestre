/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoTexto;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
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
import org.mockito.MockitoAnnotations;

/**
 *
 * @author marcelo.lima
 */
public class LivroDAOTest {
    
    @InjectMocks
    LivroDAO instance = new LivroDAO();
    
    @Mock
    Livro livro;
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
    public LivroDAOTest() {
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
     * Test of estaDisponivelEdicaoUsuario method, of class LivroDAO.
     */
    @Test
    public void testEstaDisponivelEdicaoUsuario_1ParOK() {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery(Mockito.anyString()))                
                .thenReturn(query);        
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when((Livro) query.uniqueResult()).thenReturn(livro);
        Mockito.when(livro.getBookLock()).thenReturn(1);        
        boolean expResult = true;
        boolean result = instance.estaDisponivelEdicaoUsuario(livro.getId(),1);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testEstaDisponivelEdicaoUsuario_2ParOK() {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery(Mockito.anyString()))                
                .thenReturn(query);        
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when((Livro) query.uniqueResult()).thenReturn(livro);
        Mockito.when(livro.getBookLock()).thenReturn(0);        
        boolean expResult = true;
        boolean result = instance.estaDisponivelEdicaoUsuario(livro.getId(), 1);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testEstaDisponivelEdicaoUsuario_2ParNOK() {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery(Mockito.anyString()))                
                .thenReturn(query);        
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when((Livro) query.uniqueResult()).thenReturn(livro);
        Mockito.when(livro.getBookLock()).thenReturn(3);        
        boolean expResult = false;
        boolean result = instance.estaDisponivelEdicaoUsuario(livro.getId(), 1);
        assertEquals(expResult, result);        
    }
}
