/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
public class HistoricoLivroDAOTest {
    
    @InjectMocks
    HistoricoLivroDAO instance = new HistoricoLivroDAO();
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    Livro livro;
    
    @Mock
    Calendar calendar;
    
    public HistoricoLivroDAOTest() {
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
     * Test of estaFinalizadoRevisaoUsuario method, of class HistoricoLivroDAO.
     */
    @Test
    public void testEstaFinalizadoRevisaoUsuario() {
        System.out.println("estaFinalizadoRevisaoUsuario");
        Mockito.when(sessao.createQuery(Mockito.any())).thenReturn(query);
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setInteger(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
        Mockito.when((HistoricoLivro) query.uniqueResult()).thenReturn(historicoLivro);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(null);
        Mockito.when(livro.getId()).thenReturn(1);
        boolean expResult = false;
        boolean result = instance.estaFinalizadoRevisaoUsuario(livro.getId(), Mockito.anyInt());
        assertEquals(expResult, result);
    }
    
    /**
     * Test of estaFinalizadoRevisaoUsuario method, of class HistoricoLivroDAO.
     */
    @Test
    public void testEstaFinalizadoRevisaoUsuario_True() {
        System.out.println("estaFinalizadoRevisaoUsuario_True");
        Mockito.when(sessao.createQuery(Mockito.any())).thenReturn(query);
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setInteger(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
        Mockito.when((HistoricoLivro) query.uniqueResult()).thenReturn(historicoLivro);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(calendar);
        Mockito.when(livro.getId()).thenReturn(1);
        boolean expResult = true;
        boolean result = instance.estaFinalizadoRevisaoUsuario(livro.getId(), Mockito.anyInt());
        assertEquals(expResult, result);
    }
}
