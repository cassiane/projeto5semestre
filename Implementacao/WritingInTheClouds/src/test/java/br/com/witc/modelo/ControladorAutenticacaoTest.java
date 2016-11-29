/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author cassiane.santos
 */
@RunWith(MockitoJUnitRunner.class)
public class ControladorAutenticacaoTest {
    @InjectMocks
    ControladorAutenticacao control = new ControladorAutenticacao();
    
    @Mock 
    Usuario usuario;
    Usuario outroUsuario;
    ControladorAutenticacao controlador; 
    
    @Before
    public void setUp() {
        usuario = Mockito.mock(Usuario.class);
        outroUsuario = Mockito.mock(Usuario.class);
        controlador = Mockito.mock(ControladorAutenticacao.class);
    }
    
    @After
    public void tearDown() {
        usuario = null; 
        controlador = null;
    }

    /**
     * Test of getNomeCompletoUsuario method, of class ControladorAutenticacao.    
    @Test
    public void testGetNomeCompletoUsuario() {
        System.out.println("getNomeCompletoUsuario");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        String expResult = "";
        String result = instance.getNomeCompletoUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroAmigosUsuarioLogado method, of class ControladorAutenticacao.
    **/
    @Test
    public void testGetNumeroAmigosUsuarioLogado() {
        System.out.println("getNumeroAmigosUsuarioLogado");
    }

    /**
     * Test of retornarPerfilPadraoUsuarioLogado method, of class ControladorAutenticacao.
     
    @Test
    public void testRetornarPerfilPadraoUsuarioLogado() {
        System.out.println("retornarPerfilPadraoUsuarioLogado");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.retornarPerfilPadraoUsuarioLogado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatusUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testGetStatusUsuario() {
        System.out.println("getStatusUsuario");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        String expResult = "";
        String result = instance.getStatusUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAmigoUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testSetAmigoUsuario_int() {
        System.out.println("setAmigoUsuario");
        int id = 0;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.setAmigoUsuario(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAmigoUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testSetAmigoUsuario_0args() {
        System.out.println("setAmigoUsuario");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.setAmigoUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAmigoUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testGetAmigoUsuario() {
        System.out.println("getAmigoUsuario");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        Usuario expResult = null;
        Usuario result = instance.getAmigoUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarStatusUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testAtualizarStatusUsuario() {
        System.out.println("atualizarStatusUsuario");
        int status = 0;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.atualizarStatusUsuario(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trocarPerfilUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testTrocarPerfilUsuario() {
        System.out.println("trocarPerfilUsuario");
        Perfil auxPerfil = null;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.trocarPerfilUsuario(auxPerfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPerfis method, of class ControladorAutenticacao.
     
    @Test
    public void testListarPerfis() {
        System.out.println("listarPerfis");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.listarPerfis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of perfilIgual method, of class ControladorAutenticacao.
     
    @Test
    public void testPerfilIgual() {
        System.out.println("perfilIgual");
        Perfil perfil = null;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        boolean expResult = false;
        boolean result = instance.perfilIgual(perfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarPerfilPorId method, of class ControladorAutenticacao.
     
    @Test
    public void testCarregarPerfilPorId() {
        System.out.println("carregarPerfilPorId");
        int idPerfil = 0;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfilPorId(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarPerfil method, of class ControladorAutenticacao.
     
    @Test
    public void testSalvarPerfil() {
        System.out.println("salvarPerfil");
        Perfil perfil = null;
        ControladorAutenticacao instance = new ControladorAutenticacao();
        instance.salvarPerfil(perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPerfisUsuario method, of class ControladorAutenticacao.
     
    @Test
    public void testListarPerfisUsuario() {
        System.out.println("listarPerfisUsuario");
        ControladorAutenticacao instance = new ControladorAutenticacao();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.listarPerfisUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
