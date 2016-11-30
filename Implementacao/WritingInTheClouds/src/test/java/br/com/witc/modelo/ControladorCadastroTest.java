/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class ControladorCadastroTest {
    
    @InjectMocks
    ControladorCadastro controlador = new ControladorCadastro();
    
    @Mock
    Usuario usuario;    
    @Mock
    PerfilDAO perfilDAO;    
    @Mock
    Perfil perfil;
    @Mock 
    TipoPerfil tipoPerfil;
    @Mock 
    TipoTexto tipoTexto;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test of alterarUsuario method, of class ControladorCadastro.
     */
    @Test
    public void testAlterarUsuario() throws Exception {
       controlador.alterarUsuario(usuario);
       Mockito.verify(usuario,times(1)).alterarUsuario();
    }

    /**
     * Test of removerTodasAmizades method, of class ControladorCadastro.
     */
    @Test
    public void testRemoverTodasAmizades() {
        controlador.removerTodasAmizades(1);
        Mockito.verify(usuario,times(1)).removerTodasAmizades(1);
    }

    /**
     * Test of cadastrarTipoTexto method, of class ControladorCadastro.
     */
    @Test
    public void testCadastrarTipoTexto() throws Exception {
        controlador.cadastrarTipoTexto(tipoTexto);
        Mockito.verify(tipoTexto,times(1)).salvarTipoTexto(tipoTexto);
    }

    /**
     * Test of criarPerfilUsuario method, of class ControladorCadastro.
     */
    @Test
    public void testCriarPerfilUsuario() {
        controlador.criarPerfilUsuario(Mockito.anyInt(),usuario);
        Mockito.verify(tipoPerfil,times(1)).cadastrarTipoPerfil();
    }   
}
