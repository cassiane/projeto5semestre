/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import java.util.Calendar;
import java.util.List;
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
 * @author Marcelo
 */
public class HistoricoLivroTest {
    
    @InjectMocks
    HistoricoLivro instance = new HistoricoLivro();
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    HistoricoLivroDAO historicoLivroDAO;
    
    public HistoricoLivroTest() {
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
     * Test of salvarHistorico method, of class HistoricoLivro.
     */
    @Test
    public void testSalvarHistorico() {        
        instance.salvarHistorico(historicoLivro);
        Mockito.verify(historicoLivroDAO, times(1)).salvarHistorico(historicoLivro);
    }

    /**
     * Test of salvarHistoricoConvite method, of class HistoricoLivro.
     */
    @Test
    public void testSalvarHistoricoConvite() {        
        instance.salvarHistoricoConvite(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(historicoLivroDAO, times(1)).salvarHistoricoConvite(Mockito.anyInt(), Mockito.anyInt());
    }
    
}
