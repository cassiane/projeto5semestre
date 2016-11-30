/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.TipoStatus;
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
public class TipoStatusDAOTest {
    
    @InjectMocks
    TipoStatusDAO instance = new TipoStatusDAO();
    
    @Mock
    TipoStatus tipoStatus;
    
    @Mock
    Session sessao;
    
    public TipoStatusDAOTest() {
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
     * Test of salvar method, of class TipoStatusDAO.
     */
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        
        instance.salvar(tipoStatus);
        Mockito.verify(sessao, Mockito.times(1)).saveOrUpdate(tipoStatus);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
