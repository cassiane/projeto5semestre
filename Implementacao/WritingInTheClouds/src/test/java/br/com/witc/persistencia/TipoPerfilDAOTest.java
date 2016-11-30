/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.modelo.TipoPerfil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
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
public class TipoPerfilDAOTest {
    
    @InjectMocks
    TipoPerfilDAO instance = new TipoPerfilDAO();
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
    
    @Mock
    TipoPerfil tipoPerfil;
    
    @Mock
    Query query;
    
    public TipoPerfilDAOTest() {
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
     * Test of carregarTipoPerfilEscritor method, of class TipoPerfilDAO.
     */
    @Test
    public void testCarregarTipoPerfilEscritorOK() throws Exception {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery("from TipoPerfil where tipoPerfil like '%escritor%'")).thenReturn(query);
        Mockito.when((TipoPerfil) query.uniqueResult()).thenReturn(tipoPerfil);
        TipoPerfil expResult = tipoPerfil;
        TipoPerfil result = instance.carregarTipoPerfilEscritor();
        assertEquals(expResult, result);        
    }
    
    @Test(expected=TipoPerfilException.class)
    public void testCarregarTipoPerfilEscritorTipoPerfilException() throws Exception {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery("from TipoPerfil where tipoPerfil like '%escritor%'")).thenReturn(query);
        Mockito.when((TipoPerfil) query.uniqueResult()).thenThrow(TipoPerfilException.class);        
        instance.carregarTipoPerfilEscritor();        
    }
    
    @Test(expected=QuerySyntaxException.class)
    public void testCarregarTipoPerfilEscritorQuerySyntaxException() throws Exception {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Mockito.when(sessao.createQuery("from TipoPerfil where tipoPerfil like '%escritor%'")).thenReturn(query);
        Mockito.when((TipoPerfil) query.uniqueResult()).thenThrow(QuerySyntaxException.class);        
        instance.carregarTipoPerfilEscritor();        
    }
}
