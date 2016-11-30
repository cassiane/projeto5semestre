/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author marcio
 */

@RunWith(MockitoJUnitRunner.class)
public class ControladorCadastroTest {
    
        @Mock
        ControladorCadastro controlador;    
        @Mock
        Usuario usuario;
        @Mock
        TipoTexto tipoTexto;
        @Mock
        TipoTexto tipoTexto1;

    
    public ControladorCadastroTest() {
    }
    
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }
  

    /**
     * Test of excluirUsuario method, of class ControladorCadastro.
     * @throws java.lang.Exception
     */
    @Test
    public void testExcluirUsuario() throws Exception {
       controlador.excluirUsuario(usuario);
        Mockito.verify(usuario).excluirUsuario();
       // Mockito.verify(controlador).excluirUsuario(usuario);
       // controlador.excluirUsuario(usuario);
    }

    @Test
    public void testEnviarConvite() throws Exception {
        
    }


    /**
     * Test of carregarTipoTexto method, of class ControladorCadastro.
     */
    @Test
    public void testCarregarTipoTexto() {
        Mockito.when(tipoTexto.carregarTipoTexto(1)).thenReturn(tipoTexto1);
        TipoTexto expResult = tipoTexto1;
        TipoTexto result = this.tipoTexto.carregarTipoTexto(1);
        assertEquals(expResult,result);
    }
    
}
