/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
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
 * @author vanderson
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
     * Test of aceitarAmizade method, of class ControladorCadastro.
     */
    @Test
    public void testAceitarAmizade() {
        System.out.println("aceitarAmizade");
        int idAceitar = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.aceitarAmizade(idAceitar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarConvite method, of class ControladorCadastro.
     */
    @Test
    public void testVerificarConvite() {
        System.out.println("verificarConvite");
        String email = "";
        ControladorCadastro instance = new ControladorCadastro();
        instance.verificarConvite(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirTipoTextoUsuario method, of class ControladorCadastro.
     */
    @Test
    public void testExcluirTipoTextoUsuario() {
        System.out.println("excluirTipoTextoUsuario");
        int idUsuario = 0;
        int idTipoTexto = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.excluirTipoTextoUsuario(idUsuario, idTipoTexto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
