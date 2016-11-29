/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author vanderson
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({HibernateUtil.class})
public class PerfilDAOTest {

    @Mock
    public PerfilDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of carregarPerfilPadrao method, of class PerfilDAO.
     */
    @Test
    public void testCarregarPerfilPadrao() {
        System.out.println("carregarPerfilPadrao");
        Usuario usuario = null;
        PerfilDAO instance = new PerfilDAO();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfilPadrao(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarPerfilPorId method, of class PerfilDAO.
     */
    @Test
    public void testCarregarPerfilPorId() {
        System.out.println("carregarPerfilPorId");
        int idPerfil = 0;
        PerfilDAO instance = new PerfilDAO();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfilPorId(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarPerfil method, of class PerfilDAO.
     */
    @Test
    public void testSalvarPerfil() {
        System.out.println("salvarPerfil");
        Perfil perfil = null;
        PerfilDAO instance = new PerfilDAO();
        instance.salvarPerfil(perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaAmigoEditor method, of class PerfilDAO.
     */
    @Test
    public void testCarregarListaAmigoEditor() {
        System.out.println("carregarListaAmigoEditor");
        int idUsuario = 0;
        int idLivro = 0;
        PerfilDAO instance = new PerfilDAO();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.carregarListaAmigoEditor(idUsuario, idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregaPerfilID method, of class PerfilDAO.
     */
    @Test
    public void testCarregaPerfilID() {
        System.out.println("carregaPerfilID");
        int id = 0;
        PerfilDAO instance = new PerfilDAO();
        Perfil expResult = null;
        Perfil result = instance.carregaPerfilID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaPerfilUsuario method, of class PerfilDAO.
     */
    @Test
    public void testCarregarListaPerfilUsuario() {
        System.out.println("carregarListaPerfilUsuario");
        Usuario usuario = null;
        PerfilDAO instance = new PerfilDAO();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.carregarListaPerfilUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarUsuarioPorId method, of class PerfilDAO.
     */
    @Test
    public void testCarregarUsuarioPorId() {
        System.out.println("carregarUsuarioPorId");
        int idPerfil = 0;
        PerfilDAO instance = new PerfilDAO();
        Usuario expResult = null;
        Usuario result = instance.carregarUsuarioPorId(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
