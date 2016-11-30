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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author vanderson
 */
public class HistoricoLivroDAOTest {
    
    @InjectMocks
    HistoricoLivroDAO instance = new HistoricoLivroDAO();
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
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
     * Test of estaFinalizadoUsuario method, of class HistoricoLivroDAO.
     */    
    @Test
    public void testEstaFinalizadoUsuarioTrue() {                
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
                          
        Mockito.when(sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil"))
                    .thenReturn(query);                        
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);                                   
        
        Mockito.when((HistoricoLivro) query.uniqueResult()).thenReturn(historicoLivro);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(Calendar.getInstance());        
        boolean expResult = true;
        boolean result = instance.estaFinalizadoUsuario(2, 1);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testEstaFinalizadoUsuarioFalse() {                
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
                          
        Mockito.when(sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil"))
                    .thenReturn(query);                        
        Mockito.when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);                                   
        
        Mockito.when((HistoricoLivro) query.uniqueResult()).thenReturn(historicoLivro);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(null);        
        boolean expResult = false;
        boolean result = instance.estaFinalizadoUsuario(2, 1);
        assertEquals(expResult, result);        
    }
    
    /**
     * Test of estaFinalizadoRevisaoUsuario method, of class HistoricoLivroDAO.
     */
    @Test
    public void testEstaFinalizadoRevisaoUsuario() {
        System.out.println("estaFinalizadoRevisaoUsuario");
        int idLivro = 0;
        int idPerfil = 0;
        HistoricoLivroDAO instance = new HistoricoLivroDAO();
        boolean expResult = false;
        boolean result = instance.estaFinalizadoRevisaoUsuario(idLivro, idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
