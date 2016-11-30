/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class HistoricoLivroTest {
   @InjectMocks
    HistoricoLivro instance = new HistoricoLivro();
    
    @Mock
    HistoricoLivro historicoLivro;
    
    @Mock
    HistoricoLivroDAO historicoLivroDAO;
    
    @Mock
    TipoStatus tipoStatus;
    
    @Mock
    Livro livro;
    
    @Mock
    Perfil perfil;
    
    public HistoricoLivroTest() {
    }
    
    /**
     * Test of finalizarLivroUsuario method, of class HistoricoLivro.
     */
    @Test
    public void testFinalizarLivroUsuario() {
        System.out.println("finalizarLivroUsuario");
        Mockito.when(historicoLivroDAO.carregarHistoricoLivroUsuario(livro, perfil)).thenReturn(historicoLivro);
        Mockito.when(tipoStatus.carregarTipoStatus(1)).thenReturn(tipoStatus);
        historicoLivro.setDataConclusao(Calendar.getInstance());
        historicoLivro.setStatus(tipoStatus.carregarTipoStatus(1));
        instance.finalizarLivroUsuario(livro, perfil);
        Mockito.verify(historicoLivroDAO, times(1)).salvarHistorico(historicoLivro);
        
    }
    
}
