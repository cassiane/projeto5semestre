/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070187
 */
public class HistoricoLivroTest {
    
    @InjectMocks
    HistoricoLivro historico = new HistoricoLivro();
    @Mock
    HistoricoLivro historicoLivro;
    @Mock
    HistoricoLivroDAO historicoLivroDAO;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testSalvarHistorico() {        
        historico.salvarHistorico(historicoLivro);
        Mockito.verify(historicoLivroDAO, times(1)).salvarHistorico(historicoLivro);
    }

    /**
     * Test of salvarHistoricoConvite method, of class HistoricoLivro.
     */
    @Test
    public void testSalvarHistoricoConvite() {        
        historico.salvarHistoricoConvite(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(historicoLivroDAO, times(1)).salvarHistoricoConvite(Mockito.anyInt(), Mockito.anyInt());
    }
    
    @Test
    public void testEstaFinalizadoUsuario() {
        historico.estaFinalizadoUsuario(Mockito.anyInt(),Mockito.anyInt());
        Mockito.verify(historicoLivroDAO,times(1)).estaFinalizadoUsuario(Mockito.anyInt(),Mockito.anyInt());
    }
}
