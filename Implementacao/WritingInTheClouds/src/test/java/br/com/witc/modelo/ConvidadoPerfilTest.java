/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.ConvidadoPerfilDAO;
import java.util.Calendar;
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
 * @author cassiane.santos
 */
public class ConvidadoPerfilTest {
    
    @Mock 
    ConvidadoPerfilDAO dao;
    ConvidadoPerfil convidado;
    Perfil perfil;
    Livro livro;
    
    @Before
    public void setUp() {
        dao = Mockito.mock(ConvidadoPerfilDAO.class);
        convidado = Mockito.mock(ConvidadoPerfil.class);
        livro = Mockito.mock(Livro.class);
        perfil = Mockito.mock(Perfil.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class ConvidadoPerfil.
     
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        ConvidadoPerfil instance = new ConvidadoPerfil();
        instance.salvar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remover method, of class ConvidadoPerfil.
     */
    @Test
    public void testRemover() {
       dao.carregar(perfil, livro);
       Mockito.verify(dao,times(1)).carregar(perfil, livro);
    }

    /**
     * Test of carregarlista method, of class ConvidadoPerfil.
     
    @Test
    public void testCarregarlista() {
        System.out.println("carregarlista");
        ConvidadoPerfil instance = new ConvidadoPerfil();
        List<ConvidadoPerfil> expResult = null;
        List<ConvidadoPerfil> result = instance.carregarlista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceitarEdicao method, of class ConvidadoPerfil.
     
    @Test
    public void testAceitarEdicao() {
        System.out.println("aceitarEdicao");
        Perfil perfil = null;
        Livro livro = null;
        ConvidadoPerfil instance = new ConvidadoPerfil();
        instance.aceitarEdicao(perfil, livro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of negarEdicao method, of class ConvidadoPerfil.
     
    @Test
    public void testNegarEdicao() {
        System.out.println("negarEdicao");
        ConvidadoPerfil instance = new ConvidadoPerfil();
        instance.negarEdicao();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
