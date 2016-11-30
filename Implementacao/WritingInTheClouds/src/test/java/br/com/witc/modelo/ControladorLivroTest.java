/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Marcelo
 */
public class ControladorLivroTest {
    
    @InjectMocks
    ControladorLivro instance = new ControladorLivro();
        
    @Mock
    Livro livro;
    
    @Mock
    Perfil perfil;  
    
    @Mock
    HistoricoLivro historicoLivro;            
    
    @Mock
    ConvidadoPerfil convidadoPerfil;            
    
    public ControladorLivroTest() {
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
     * Test of salvarLivro method, of class ControladorLivro.
     */    
    @Test
    public void testSalvarLivro_3argsNaoFinalizado() {                
        instance.salvarLivro(livro, Boolean.FALSE, perfil);        
        Mockito.verify(livro, times(1)).salvarLivro(livro);
    }
    
    @Test
    public void testSalvarLivro_3argsFinalizadoTerminado() {                     
        Mockito.when(livro.getId()).thenReturn(0);        
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(Calendar.getInstance());
        instance.salvarLivro(livro, Boolean.TRUE, perfil);        
        Mockito.verify(historicoLivro, times(1)).finalizarLivroUsuario(livro,perfil);
        Mockito.verify(livro, times(1)).salvarLivro(livro);
    }    
            
    @Test
    public void testSalvarLivro_3argsFinalizadoNaoTerminado() {        
        List<HistoricoLivro> lstHl = new ArrayList();
        lstHl.add(historicoLivro);        
        Mockito.when(livro.getId()).thenReturn(0);        
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(Calendar.getInstance());
        Mockito.when(historicoLivro.listarHistoricoLivro(livro.getId())).thenReturn(lstHl);
        Mockito.when(historicoLivro.getDataConclusao()).thenReturn(null);        
        instance.salvarLivro(livro, Boolean.TRUE, perfil);        
        Mockito.verify(historicoLivro, times(1)).finalizarLivroUsuario(livro,perfil);
        Mockito.verify(livro, times(1)).salvarLivro(livro);
    }     
    
    /**
     * Test of salvarHistorico method, of class ControladorLivro.
     */   
    @Test
    public void testSalvarHistorico() {        
        instance.salvarHistorico(historicoLivro);
        Mockito.verify(historicoLivro, times(1)).salvarHistorico(historicoLivro);
    }    

    /**
     * Test of negarEdicao method, of class ControladorLivro.
     */    
    @Test
    public void testNegarEdicao() {        
        instance.negarEdicao(convidadoPerfil);
        Mockito.verify(convidadoPerfil, times(1)).negarEdicao();
    }    
}
