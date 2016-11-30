/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Marcelo
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
        
        Mockito.when(sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil")
                .setString("idLivro", "1")
                .setString("idPerfil", "1"))
                .thenReturn(query);
        /*
        Mockito.when(sessao.createQuery(Mockito.anyString()))                
                .thenReturn(query);
        */
        Mockito.when(query.uniqueResult()).thenReturn(historicoLivro);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(Mockito.anyObject());        
        boolean expResult = true;
        boolean result = instance.estaFinalizadoUsuario(1, 1);
        assertEquals(expResult, result);        
    }
}
