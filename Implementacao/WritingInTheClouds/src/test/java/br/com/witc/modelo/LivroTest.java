/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import br.com.witc.modelo.HistoricoLivro;
import java.util.ArrayList;
import org.junit.Assert;

/**
 *
 * @author cassiane.santos
 */
public class LivroTest {
    
    @Mock 
    HistoricoLivro historico;
    
    @Before
    public void setUp() {
        historico = Mockito.mock(HistoricoLivro.class);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of setTitulo method, of class Livro.
     
    @Test
    public void testSetTitulo() throws Exception {
        System.out.println("setTitulo");
        String titulo = "";
        Livro instance = new Livro();
        instance.setTitulo(titulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getCapaPorId method, of class Livro.
     
    @Test
    public void testGetCapaPorId() throws Exception {
        System.out.println("getCapaPorId");
        int idLivro = 0;
        Livro instance = new Livro();
        byte[] expResult = null;
        byte[] result = instance.getCapaPorId(idLivro);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomeAutoresABNT method, of class Livro.
     
    @Test
    public void testGetNomeAutoresABNT() {
        System.out.println("getNomeAutoresABNT");
        int idLivro = 0;
        Livro instance = new Livro();
        String expResult = "";
        String result = instance.getNomeAutoresABNT(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLstNomesCompletosAutores method, of class Livro.
     */
    @Test
    public void testGetLstNomesCompletosAutores() {
        List<HistoricoLivro> lstHistorico;
        List<HistoricoLivro> hist;
        
        lstHistorico = new ArrayList<>();
        lstHistorico.add(historico);
        
        hist = new ArrayList<>();
        hist.add(historico);
        
        String[][] arrNomes = new String[lstHistorico.size()][2];
        int index = 0;
        for (HistoricoLivro histor : lstHistorico) {
            arrNomes[index][0] = histor.getNomeUsuario();
            arrNomes[index][1] = histor.getSobrenomeUsuario();
            index++;
        }
        String[][] arrNomeEsp = new String[hist.size()][2];
        int index2 = 0;
        for (HistoricoLivro historico2 : lstHistorico) {
            arrNomes[index2][0] = historico2.getNomeUsuario();
            arrNomes[index2][1] = historico2.getSobrenomeUsuario();
            index2++;
        }
        assertArrayEquals(arrNomeEsp,arrNomes);
    }

    /**
     * Test of carregarLivro method, of class Livro.
     
    @Test
    public void testCarregarLivro() throws Exception {
        System.out.println("carregarLivro");
        int idLivro = 0;
        Livro instance = new Livro();
        Livro expResult = null;
        Livro result = instance.carregarLivro(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosPerfil method, of class Livro.
     
    @Test
    public void testListarLivrosPerfil() {
        System.out.println("listarLivrosPerfil");
        Perfil perfil = null;
        Livro instance = new Livro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosPerfil(perfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarLivro method, of class Livro.
     
    @Test
    public void testSalvarLivro() {
        System.out.println("salvarLivro");
        Livro livro = null;
        Livro instance = new Livro();
        instance.salvarLivro(livro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosPorTipoTexto method, of class Livro.
     
    @Test
    public void testListarLivrosPorTipoTexto() throws Exception {
        System.out.println("listarLivrosPorTipoTexto");
        TipoTexto tp = null;
        Livro instance = new Livro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosPorTipoTexto(tp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregaBibliotecaVirtualPesquisa method, of class Livro.
     
    @Test
    public void testCarregaBibliotecaVirtualPesquisa() throws Exception {
        System.out.println("carregaBibliotecaVirtualPesquisa");
        String valorPesquisa = "";
        Livro instance = new Livro();
        Map<String, List<Livro>> expResult = null;
        Map<String, List<Livro>> result = instance.carregaBibliotecaVirtualPesquisa(valorPesquisa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenerosPreferidos method, of class Livro.
     
    @Test
    public void testGetGenerosPreferidos() {
        System.out.println("getGenerosPreferidos");
        int idPerfil = 0;
        Livro instance = new Livro();
        Map<String, Float> expResult = null;
        Map<String, Float> result = instance.getGenerosPreferidos(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaDisponivelEdicaoUsuario method, of class Livro.
     
    @Test
    public void testEstaDisponivelEdicaoUsuario() {
        System.out.println("estaDisponivelEdicaoUsuario");
        int idLivro = 0;
        int idPerfil = 0;
        Livro instance = new Livro();
        boolean expResult = false;
        boolean result = instance.estaDisponivelEdicaoUsuario(idLivro, idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaDisponivelRevisaoUsuario method, of class Livro.
     
    @Test
    public void testEstaDisponivelRevisaoUsuario() {
        System.out.println("estaDisponivelRevisaoUsuario");
        int idLivro = 0;
        int idPerfil = 0;
        Livro instance = new Livro();
        boolean expResult = false;
        boolean result = instance.estaDisponivelRevisaoUsuario(idLivro, idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosPublicadosPerfil method, of class Livro.
     
    @Test
    public void testListarLivrosPublicadosPerfil() {
        System.out.println("listarLivrosPublicadosPerfil");
        int idPerfil = 0;
        Livro instance = new Livro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosPublicadosPerfil(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosRevisao method, of class Livro.
     
    @Test
    public void testListarLivrosRevisao() {
        System.out.println("listarLivrosRevisao");
        Livro instance = new Livro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosRevisao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Livro.
     
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Livro instance = new Livro();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Livro.
     
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Livro instance = new Livro();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
