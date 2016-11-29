/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanderson
 */
public class PerfilTest {
    
    public PerfilTest() {
    }

    /**
     * Test of getId method, of class Perfil.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Perfil instance = new Perfil();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Perfil.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Perfil instance = new Perfil();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvaliacao method, of class Perfil.
     */
    @Test
    public void testGetAvaliacao() {
        System.out.println("getAvaliacao");
        Perfil instance = new Perfil();
        float expResult = 0.0F;
        float result = instance.getAvaliacao();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvaliacao method, of class Perfil.
     */
    @Test
    public void testSetAvaliacao() {
        System.out.println("setAvaliacao");
        float avaliacao = 0.0F;
        Perfil instance = new Perfil();
        instance.setAvaliacao(avaliacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQtdAvaliacoes method, of class Perfil.
     */
    @Test
    public void testGetQtdAvaliacoes() {
        System.out.println("getQtdAvaliacoes");
        Perfil instance = new Perfil();
        int expResult = 0;
        int result = instance.getQtdAvaliacoes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQtdAvaliacoes method, of class Perfil.
     */
    @Test
    public void testSetQtdAvaliacoes() {
        System.out.println("setQtdAvaliacoes");
        int qtdAvaliacoes = 0;
        Perfil instance = new Perfil();
        instance.setQtdAvaliacoes(qtdAvaliacoes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSomaAvaliacoes method, of class Perfil.
     */
    @Test
    public void testGetSomaAvaliacoes() {
        System.out.println("getSomaAvaliacoes");
        Perfil instance = new Perfil();
        float expResult = 0.0F;
        float result = instance.getSomaAvaliacoes();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSomaAvaliacoes method, of class Perfil.
     */
    @Test
    public void testSetSomaAvaliacoes() {
        System.out.println("setSomaAvaliacoes");
        float somaAvaliacoes = 0.0F;
        Perfil instance = new Perfil();
        instance.setSomaAvaliacoes(somaAvaliacoes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPseudonimo method, of class Perfil.
     */
    @Test
    public void testGetPseudonimo() {
        System.out.println("getPseudonimo");
        Perfil instance = new Perfil();
        String expResult = "";
        String result = instance.getPseudonimo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPseudonimo method, of class Perfil.
     */
    @Test
    public void testSetPseudonimo() {
        System.out.println("setPseudonimo");
        String pseudonimo = "";
        Perfil instance = new Perfil();
        instance.setPseudonimo(pseudonimo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuario method, of class Perfil.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Perfil instance = new Perfil();
        Usuario expResult = null;
        Usuario result = instance.getUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuario method, of class Perfil.
     */
    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        Usuario usuario = null;
        Perfil instance = new Perfil();
        instance.setUsuario(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipoPerfil method, of class Perfil.
     */
    @Test
    public void testGetTipoPerfil() {
        System.out.println("getTipoPerfil");
        Perfil instance = new Perfil();
        TipoPerfil expResult = null;
        TipoPerfil result = instance.getTipoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipoPerfil method, of class Perfil.
     */
    @Test
    public void testSetTipoPerfil() {
        System.out.println("setTipoPerfil");
        TipoPerfil tipoPerfil = null;
        Perfil instance = new Perfil();
        instance.setTipoPerfil(tipoPerfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPerfilPadrao method, of class Perfil.
     */
    @Test
    public void testIsPerfilPadrao() {
        System.out.println("isPerfilPadrao");
        Perfil instance = new Perfil();
        boolean expResult = false;
        boolean result = instance.isPerfilPadrao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPerfilPadrao method, of class Perfil.
     */
    @Test
    public void testSetPerfilPadrao() {
        System.out.println("setPerfilPadrao");
        boolean perfilPadrao = false;
        Perfil instance = new Perfil();
        instance.setPerfilPadrao(perfilPadrao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarPerfilPadraoUsuarioLogado method, of class Perfil.
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
        System.out.println("criarPerfil");
        Perfil newPerfil = null;
        Perfil instance = new Perfil();
        instance.criarPerfil(newPerfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPerfisUsuario method, of class Perfil.
     */
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
     */
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
    
}
