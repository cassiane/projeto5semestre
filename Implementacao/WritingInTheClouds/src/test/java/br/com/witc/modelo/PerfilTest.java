/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

/**
 *
 * @author 10070187
 */
public class PerfilTest {
    
    @Mock 
    Perfil perfil;
    
    @Before
    public void setUp() {
        perfil = Mockito.mock(Perfil.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of retornarPerfilPadraoUsuarioLogado method, of class Perfil.
     
    @Test
    public void testRetornarPerfilPadraoUsuarioLogado() {
        System.out.println("retornarPerfilPadraoUsuarioLogado");
        Usuario usuario = null;
        Perfil expResult = null;
        Perfil result = Perfil.retornarPerfilPadraoUsuarioLogado(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomeUsuarioABNT method, of class Perfil.
     
    @Test
    public void testGetNomeUsuarioABNT() {
        System.out.println("getNomeUsuarioABNT");
        Perfil instance = new Perfil();
        String expResult = "";
        String result = instance.getNomeUsuarioABNT();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomeUsuario method, of class Perfil.
     
    @Test
    public void testGetNomeUsuario() {
        System.out.println("getNomeUsuario");
        Perfil instance = new Perfil();
        String expResult = "";
        String result = instance.getNomeUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSobrenomeUsuario method, of class Perfil.
     
    @Test
    public void testGetSobrenomeUsuario() {
        System.out.println("getSobrenomeUsuario");
        Perfil instance = new Perfil();
        String expResult = "";
        String result = instance.getSobrenomeUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarioPorIdPerfil method, of class Perfil.
     
    @Test
    public void testGetUsuarioPorIdPerfil() {
        System.out.println("getUsuarioPorIdPerfil");
        int idPerfil = 0;
        Perfil instance = new Perfil();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioPorIdPerfil(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarPerfil method, of class Perfil.
     
    @Test
    public void testCarregarPerfil() {
        System.out.println("carregarPerfil");
        Usuario usuario = null;
        Perfil instance = new Perfil();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfil(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaAmigoEditor method, of class Perfil.
     
    @Test
    public void testCarregarListaAmigoEditor() {
        System.out.println("carregarListaAmigoEditor");
        int idLivro = 0;
        Perfil instance = new Perfil();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.carregarListaAmigoEditor(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desativarPerfil method, of class Perfil.
     
    @Test
    public void testDesativarPerfil() {
        System.out.println("desativarPerfil");
        Perfil perfil = null;
        Perfil instance = new Perfil();
        instance.desativarPerfil(perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarPerfil method, of class Perfil.
     */
    @Test
    public void testCriarPerfil() {
        perfil.criarPerfil(perfil);
        Mockito.verify(perfil,times(1)).criarPerfil(perfil);
    }

    /**
     * Test of listarPerfisUsuario method, of class Perfil.
     
    @Test
    public void testListarPerfisUsuario() {
        System.out.println("listarPerfisUsuario");
        Usuario usuario = null;
        Perfil instance = new Perfil();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.listarPerfisUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarPerfilPorId method, of class Perfil.
     
    @Test
    public void testCarregarPerfilPorId() {
        System.out.println("carregarPerfilPorId");
        int idPerfil = 0;
        Perfil instance = new Perfil();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfilPorId(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
