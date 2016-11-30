/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.HistoricoLivroDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class HistoricoLivroTest {
    @InjectMocks
    HistoricoLivro historicoLivro = new HistoricoLivro();
    @Mock
    HistoricoLivroDAO dao;
    @Mock
    Perfil perfil;
    @Mock
    Livro livro;
    @Mock
    TipoStatus tipoStatus;
    
    public HistoricoLivroTest() {
    }
    
    /**
     * Test of finalizarLivroUsuario method, of class HistoricoLivro.
     */
    @Test
    public void testFinalizarLivroUsuario() {
        System.out.println("finalizarLivroUsuario");
        Mockito.when(dao.carregarHistoricoLivroUsuario(livro, perfil)).thenReturn(historicoLivro);
        Mockito.when(tipoStatus.carregarTipoStatus(1)).thenReturn(tipoStatus);
       // historicoLivro.setDataConclusao(Calendar.getInstance());
       // historicoLivro.setStatus(tipoStatus.carregarTipoStatus(1));
        historicoLivro.finalizarLivroUsuario(livro, perfil);
        Mockito.verify(dao).salvarHistorico(historicoLivro);
        
    }
    
}
