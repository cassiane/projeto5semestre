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
public class ControladorLivroTest {
    @InjectMocks
    ControladorLivro controlador = new ControladorLivro();
    @Mock
    Livro livro;
    @Mock
    ConvidadoPerfil convidado;
    @Mock
    Perfil perfil;
    
    public ControladorLivroTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of getLivro method, of class ControladorLivro.
     */
    @Test
    public void testGetLivro() {
        System.out.println("getLivro");
        ControladorLivro instance = new ControladorLivro();
        Livro expResult = null;
        Livro result = instance.getLivro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLivro method, of class ControladorLivro.
     */
    @Test
    public void testSetLivro() {
        System.out.println("setLivro");
        Livro livro = null;
        ControladorLivro instance = new ControladorLivro();
        instance.setLivro(livro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLstTipoTexto method, of class ControladorLivro.
     */
    @Test
    public void testGetLstTipoTexto() throws Exception {
        System.out.println("getLstTipoTexto");
        ControladorLivro instance = new ControladorLivro();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.getLstTipoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapaPorId method, of class ControladorLivro.
     */
    @Test
    public void testGetCapaPorId() throws Exception {
        System.out.println("getCapaPorId");
        int idLivro = 0;
        ControladorLivro instance = new ControladorLivro();
        byte[] expResult = null;
        byte[] result = instance.getCapaPorId(idLivro);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarLivro method, of class ControladorLivro.
     */
    @Test
    public void testSalvarLivro_Livro() {
        controlador.salvarLivro(livro);
        Mockito.verify(livro).salvarLivro(livro);   
    }

    /**
     * Test of salvarLivro method, of class ControladorLivro.
     */
    @Test
    public void testSalvarLivro_3args() {
        System.out.println("salvarLivro");
        Livro livro = null;
        boolean finalizado = false;
        Perfil perfil = null;
        ControladorLivro instance = new ControladorLivro();
        instance.salvarLivro(livro, finalizado, perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarLivro method, of class ControladorLivro.
     */
    @Test
    public void testCarregarLivro() throws Exception {
        System.out.println("carregarLivro");
        int idLivro = 0;
        ControladorLivro instance = new ControladorLivro();
        Livro expResult = null;
        Livro result = instance.carregarLivro(idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosPerfil method, of class ControladorLivro.
     */
    @Test
    public void testListarLivrosPerfil() {
        System.out.println("listarLivrosPerfil");
        Perfil perfil = null;
        ControladorLivro instance = new ControladorLivro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosPerfil(perfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarPerfil method, of class ControladorLivro.
     */
    @Test
    public void testCarregarPerfil() {
        System.out.println("carregarPerfil");
        Usuario usuario = null;
        ControladorLivro instance = new ControladorLivro();
        Perfil expResult = null;
        Perfil result = instance.carregarPerfil(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregaBibliotecaVirtual method, of class ControladorLivro.
     */
    @Test
    public void testCarregaBibliotecaVirtual() throws Exception {
        System.out.println("carregaBibliotecaVirtual");
        ControladorLivro instance = new ControladorLivro();
        Map<String, List<Livro>> expResult = null;
        Map<String, List<Livro>> result = instance.carregaBibliotecaVirtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenerosPreferidos method, of class ControladorLivro.
     */
    @Test
    public void testGetGenerosPreferidos() {
        System.out.println("getGenerosPreferidos");
        int idPerfil = 0;
        ControladorLivro instance = new ControladorLivro();
        Map<String, Float> expResult = null;
        Map<String, Float> result = instance.getGenerosPreferidos(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregaBibliotecaVirtualPesquisa method, of class ControladorLivro.
     */
    @Test
    public void testCarregaBibliotecaVirtualPesquisa() throws Exception {
        System.out.println("carregaBibliotecaVirtualPesquisa");
        String valorPesquisa = "";
        ControladorLivro instance = new ControladorLivro();
        Map<String, List<Livro>> expResult = null;
        Map<String, List<Livro>> result = instance.carregaBibliotecaVirtualPesquisa(valorPesquisa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaDisponivelEdicaoUsuario method, of class ControladorLivro.
     */
    @Test
    public void testEstaDisponivelEdicaoUsuario() {
        System.out.println("estaDisponivelEdicaoUsuario");
        int idLivro = 0;
        int idPerfil = 0;
        ControladorLivro instance = new ControladorLivro();
        boolean expResult = false;
        boolean result = instance.estaDisponivelEdicaoUsuario(idLivro, idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaDisponivelRevisaoUsuario method, of class ControladorLivro.
     */
    @Test
    public void testEstaDisponivelRevisaoUsuario() {
        System.out.println("estaDisponivelRevisaoUsuario");
        Mockito.when(livro.estaDisponivelRevisaoUsuario(1, 1)).thenReturn(true);
        boolean expResult = true;
        boolean result = controlador.estaDisponivelRevisaoUsuario(1, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of salvarHistorico method, of class ControladorLivro.
     */
    @Test
    public void testSalvarHistorico() {
        System.out.println("salvarHistorico");
        HistoricoLivro hist = null;
        ControladorLivro instance = new ControladorLivro();
        instance.salvarHistorico(hist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarTipoStatus method, of class ControladorLivro.
     */
    @Test
    public void testCarregarTipoStatus() {
        System.out.println("carregarTipoStatus");
        int id = 0;
        ControladorLivro instance = new ControladorLivro();
        TipoStatus expResult = null;
        TipoStatus result = instance.carregarTipoStatus(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaAmigoEditor method, of class ControladorLivro.
     */
    @Test
    public void testCarregarListaAmigoEditor() {
        System.out.println("carregarListaAmigoEditor");
        Perfil perfil = null;
        int idLivro = 0;
        ControladorLivro instance = new ControladorLivro();
        List<Perfil> expResult = null;
        List<Perfil> result = instance.carregarListaAmigoEditor(perfil, idLivro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convidarAmigoEditor method, of class ControladorLivro.
     */
    @Test
    public void testConvidarAmigoEditor() {
        System.out.println("convidarAmigoEditor");
        Perfil usuario = null;
        List<Perfil> convidado = null;
        Livro livro = null;
        ControladorLivro instance = new ControladorLivro();
        instance.convidarAmigoEditor(usuario, convidado, livro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarListaSolicitacaoEdicao method, of class ControladorLivro.
     */
    @Test
    public void testCarregarListaSolicitacaoEdicao() {
        System.out.println("carregarListaSolicitacaoEdicao");
        Perfil perfilUsuario = null;
        ControladorLivro instance = new ControladorLivro();
        List<ConvidadoPerfil> expResult = null;
        List<ConvidadoPerfil> result = instance.carregarListaSolicitacaoEdicao(perfilUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceitarEdicao method, of class ControladorLivro.
     */
    @Test
    public void testAceitarEdicao() {
        System.out.println("aceitarEdicao");
        convidado.aceitarEdicao(perfil, livro);
        Mockito.verify(this.convidado).aceitarEdicao(perfil, livro);
    }

    /**
     * Test of negarEdicao method, of class ControladorLivro.
     */
    @Test
    public void testNegarEdicao() {
        System.out.println("negarEdicao");
        ConvidadoPerfil editarLivro = null;
        ControladorLivro instance = new ControladorLivro();
        instance.negarEdicao(editarLivro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosPublicadosPerfil method, of class ControladorLivro.
     */
    @Test
    public void testListarLivrosPublicadosPerfil() {
        System.out.println("listarLivrosPublicadosPerfil");
        int idPerfil = 0;
        ControladorLivro instance = new ControladorLivro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosPublicadosPerfil(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLivrosRevisao method, of class ControladorLivro.
     */
    @Test
    public void testListarLivrosRevisao() {
        System.out.println("listarLivrosRevisao");
        ControladorLivro instance = new ControladorLivro();
        List<Livro> expResult = null;
        List<Livro> result = instance.listarLivrosRevisao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadEpub method, of class ControladorLivro.
     */
    @Test
    public void testDownloadEpub() throws Exception {
        System.out.println("downloadEpub");
        Livro livro = null;
        String pathEbub = "";
        ControladorLivro instance = new ControladorLivro();
        byte[] expResult = null;
        byte[] result = instance.downloadEpub(livro, pathEbub);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
