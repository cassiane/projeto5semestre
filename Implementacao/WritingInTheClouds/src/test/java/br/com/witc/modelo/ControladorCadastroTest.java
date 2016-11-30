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
 * @author Marcelo
 */
public class ControladorCadastroTest {
    
    @InjectMocks
    ControladorCadastro instance = new ControladorCadastro();
    
    @Mock
    Usuario usuario;
    
    @Mock
    PerfilDAO perfilDAO;
    
    @Mock
    Perfil perfil;
    
    public ControladorCadastroTest() {
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
     * Test of solicitarAmizade method, of class ControladorCadastro.
     */
    @Test
    public void testSolicitarAmizade() {        
        instance.solicitarAmizade(Mockito.anyInt());
        Mockito.verify(usuario, times(1)).solicitarAmizade(Mockito.anyInt());
    }   
        
    @Test
    public void testCriarPerfilPadrao() {        
        instance.setPerfil(perfil);
        instance.setPerfilDAO(perfilDAO);
        Mockito.when(usuario.getNome()).thenReturn("teste");                        
        instance.criarPerfilPadrao(usuario);
        Mockito.verify(perfilDAO, times(1)).salvarPerfil(perfil);
    }    
    
    @Test
    public void testSalvarTipoTextoUsuario() {
        instance.salvarTipoTextoUsuario(Mockito.anyList(), Mockito.anyInt());
        Mockito.verify(usuario, times(1)).salvarTipoTextoUsuario(Mockito.anyList(), Mockito.anyInt());
    }
}
