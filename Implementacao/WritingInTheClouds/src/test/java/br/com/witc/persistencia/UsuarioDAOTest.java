/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Usuario;
import java.util.List;
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
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author vanderson
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioDAOTest {
    
    @InjectMocks
    UsuarioDAO instance = new UsuarioDAO();
    
    @Mock
    Session sessao;
    
    @Mock
    SQLQuery sqlQuery;
    
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
     * Test of solicitarAmizade method, of class UsuarioDAO.
     */
    @Test
    public void testSolicitarAmizade() {
        System.out.println("solicitarAmizade");
        int id = 1;
        int idSugestao = 2;
        Mockito.when(sessao.createSQLQuery(Mockito.any())).thenReturn(sqlQuery);
        Mockito.when(sqlQuery.executeUpdate()).thenReturn(1);
        instance.solicitarAmizade(id, idSugestao);
        Mockito.verify(sessao, Mockito.times(1)).createSQLQuery(Mockito.anyString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
