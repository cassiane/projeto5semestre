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
 * @author 10070142
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
    public void testEstaFinalizadoRevisaoUsuario() {
        historico.estaFinalizadoRevisaoUsuario(Mockito.anyInt(),Mockito.anyInt());
        Mockito.verify(historicoLivroDAO,times(1)).estaFinalizadoRevisaoUsuario(Mockito.anyInt(),Mockito.anyInt());
    }
}
