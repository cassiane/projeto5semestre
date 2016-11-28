/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorCadastro;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class CadastrarBeanTest {
    @InjectMocks
    CadastrarBean cad = new CadastrarBean();
    
    @Mock 
    CadastrarBean cadastrar; 
    ControladorCadastro controlador;
    
    @Before
    public void setUp() {
        cadastrar = new CadastrarBean(); 
        controlador = Mockito.mock(ControladorCadastro.class);
        cadastrar.setControlador(controlador);
    }
    
    /**
     * Test of aceitarAmizade method, of class CadastrarBean.
     */
    @Test
    public void testAceitarAmizade() {
        assertEquals(cadastrar, cad);
    }
    
    /**
     * Test of getFoto method, of class CadastrarBean.
    
    @Test
    public void testGetFoto() {
        System.out.println("getFoto");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFoto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoCapa method, of class CadastrarBean.
    
    @Test
    public void testGetFotoCapa() {
        System.out.println("getFotoCapa");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoCapa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAmigos method, of class CadastrarBean.
    
    @Test
    public void testGetAmigos() {
        System.out.println("getAmigos");
        CadastrarBean instance = new CadastrarBean();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTemAmigos method, of class CadastrarBean.
    
    @Test
    public void testIsTemAmigos() {
        System.out.println("isTemAmigos");
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.isTemAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSugestao method, of class CadastrarBean.
    
    @Test
    public void testGetSugestao() {
        System.out.println("getSugestao");
        CadastrarBean instance = new CadastrarBean();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getSugestao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTemSugestao method, of class CadastrarBean.
    
    @Test
    public void testIsTemSugestao() {
        System.out.println("isTemSugestao");
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.isTemSugestao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSolicitacao method, of class CadastrarBean.
    
    @Test
    public void testGetSolicitacao() {
        System.out.println("getSolicitacao");
        CadastrarBean instance = new CadastrarBean();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getSolicitacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTemSolicitacao method, of class CadastrarBean.
    
    @Test
    public void testIsTemSolicitacao() {
        System.out.println("isTemSolicitacao");
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.isTemSolicitacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsuarios method, of class CadastrarBean.
    
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");
        CadastrarBean instance = new CadastrarBean();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.getUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotos method, of class CadastrarBean.
    
    @Test
    public void testGetFotos() {
        System.out.println("getFotos");
        Usuario user = null;
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotos(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoAmigo method, of class CadastrarBean.
    
    @Test
    public void testGetFotoAmigo() {
        System.out.println("getFotoAmigo");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoAmigo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoSugestao method, of class CadastrarBean.
    
    @Test
    public void testGetFotoSugestao() {
        System.out.println("getFotoSugestao");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoSugestao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoSolicitacao method, of class CadastrarBean.
    
    @Test
    public void testGetFotoSolicitacao() {
        System.out.println("getFotoSolicitacao");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoSolicitacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoConvidar method, of class CadastrarBean.
    
    @Test
    public void testGetFotoConvidar() {
        System.out.println("getFotoConvidar");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoConvidar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoNotificacao method, of class CadastrarBean.
    
    @Test
    public void testGetFotoNotificacao() {
        System.out.println("getFotoNotificacao");
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.getFotoNotificacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnoAtual method, of class CadastrarBean.
     
    @Test
    public void testGetAnoAtual() {
        System.out.println("getAnoAtual");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.getAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnoInicial method, of class CadastrarBean.
     
    @Test
    public void testGetAnoInicial() {
        System.out.println("getAnoInicial");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.getAnoInicial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsuarioLogado method, of class CadastrarBean.
     
    @Test
    public void testSetUsuarioLogado() {
        System.out.println("setUsuarioLogado");
        CadastrarBean instance = new CadastrarBean();
        instance.setUsuarioLogado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataNascimento method, of class CadastrarBean.
     
    @Test
    public void testSetDataNascimento() throws Exception {
        System.out.println("setDataNascimento");
        CadastrarBean instance = new CadastrarBean();
        instance.setDataNascimento();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preencherDataNasc method, of class CadastrarBean.
     
    @Test
    public void testPreencherDataNasc() {
        System.out.println("preencherDataNasc");
        CadastrarBean instance = new CadastrarBean();
        instance.preencherDataNasc();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarDataNasc method, of class CadastrarBean.
     
    @Test
    public void testRetornarDataNasc() {
        System.out.println("retornarDataNasc");
        CadastrarBean instance = new CadastrarBean();
        instance.retornarDataNasc();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHistoriasDesafiosCarregado method, of class CadastrarBean.
     
    @Test
    public void testGetHistoriasDesafiosCarregado() {
        System.out.println("getHistoriasDesafiosCarregado");
        CadastrarBean instance = new CadastrarBean();
        HistoriasDesafios expResult = null;
        HistoriasDesafios result = instance.getHistoriasDesafiosCarregado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarUsuario method, of class CadastrarBean.
    
    @Test
    public void testCadastrarUsuario() {
        System.out.println("cadastrarUsuario");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.cadastrarUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarUsuario method, of class CadastrarBean.
    
    @Test
    public void testAlterarUsuario() {
        System.out.println("alterarUsuario");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.alterarUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirUsuario method, of class CadastrarBean.
     
    @Test
    public void testExcluirUsuario() {
        System.out.println("excluirUsuario");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.excluirUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recuperarConta method, of class CadastrarBean.
    
    @Test
    public void testRecuperarConta() {
        System.out.println("recuperarConta");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.recuperarConta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of redefinirSenha method, of class CadastrarBean.
     
    @Test
    public void testRedefinirSenha() {
        System.out.println("redefinirSenha");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.redefinirSenha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarAmigos method, of class CadastrarBean.
     
    @Test
    public void testListarAmigos() {
        System.out.println("listarAmigos");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.listarAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of completeEmail method, of class CadastrarBean.
     
    @Test
    public void testCompleteEmail() {
        System.out.println("completeEmail");
        String email = "";
        CadastrarBean instance = new CadastrarBean();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.completeEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solicitarAmizade method, of class CadastrarBean.
     
    @Test
    public void testSolicitarAmizade() {
        System.out.println("solicitarAmizade");
        int idSugestao = 0;
        CadastrarBean instance = new CadastrarBean();
        instance.solicitarAmizade(idSugestao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerAmizade method, of class CadastrarBean.
   
    @Test
    public void testRemoverAmizade() {
        System.out.println("removerAmizade");
        int idAmizade = 0;
        CadastrarBean instance = new CadastrarBean();
        instance.removerAmizade(idAmizade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerTodasAmizades method, of class CadastrarBean.
   
    @Test
    public void testRemoverTodasAmizades() {
        System.out.println("removerTodasAmizades");
        int idUsuario = 0;
        CadastrarBean instance = new CadastrarBean();
        instance.removerTodasAmizades(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviarConvite method, of class CadastrarBean.
    
    @Test
    public void testEnviarConvite() {
        System.out.println("enviarConvite");
        CadastrarBean instance = new CadastrarBean();
        instance.enviarConvite();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editarLivro method, of class CadastrarBean.
    
    @Test
    public void testEditarLivro() {
        System.out.println("editarLivro");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.editarLivro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converterFoto method, of class CadastrarBean.
     
    @Test
    public void testConverterFoto() {
        System.out.println("converterFoto");
        String pathFile = "";
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.converterFoto(pathFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarFotoDefault method, of class CadastrarBean.
     
    @Test
    public void testCarregarFotoDefault() {
        System.out.println("carregarFotoDefault");
        boolean capa = false;
        CadastrarBean instance = new CadastrarBean();
        StreamedContent expResult = null;
        StreamedContent result = instance.carregarFotoDefault(capa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarTipoPerfil method, of class CadastrarBean.
     
    @Test
    public void testCadastrarTipoPerfil() {
        System.out.println("cadastrarTipoPerfil");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.cadastrarTipoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of novoPerfil method, of class CadastrarBean.
   
    @Test
    public void testNovoPerfil() {
        System.out.println("novoPerfil");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.novoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTipoPerfil method, of class CadastrarBean.
    
    @Test
    public void testListarTipoPerfil() {
        System.out.println("listarTipoPerfil");
        CadastrarBean instance = new CadastrarBean();
        List<TipoPerfil> expResult = null;
        List<TipoPerfil> result = instance.listarTipoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTipoPerfilPossiveis method, of class CadastrarBean.
    
    @Test
    public void testListarTipoPerfilPossiveis() {
        System.out.println("listarTipoPerfilPossiveis");
        CadastrarBean instance = new CadastrarBean();
        List<TipoPerfil> expResult = null;
        List<TipoPerfil> result = instance.listarTipoPerfilPossiveis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editarPerfil method, of class CadastrarBean.
     
    @Test
    public void testEditarPerfil() {
        System.out.println("editarPerfil");
        int id = 0;
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.editarPerfil(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarTipoTexto method, of class CadastrarBean.
    
    @Test
    public void testCadastrarTipoTexto() {
        System.out.println("cadastrarTipoTexto");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.cadastrarTipoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of novoTipoTexto method, of class CadastrarBean.
     
    @Test
    public void testNovoTipoTexto() {
        System.out.println("novoTipoTexto");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.novoTipoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTipoTexto method, of class CadastrarBean.
    
    @Test
    public void testListarTipoTexto() throws Exception {
        System.out.println("listarTipoTexto");
        CadastrarBean instance = new CadastrarBean();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.listarTipoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaTipoTextoFiltrada method, of class CadastrarBean.
    
    @Test
    public void testListaTipoTextoFiltrada() throws Exception {
        System.out.println("listaTipoTextoFiltrada");
        String nomeTipoTexto = "";
        CadastrarBean instance = new CadastrarBean();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.listaTipoTextoFiltrada(nomeTipoTexto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaPalavrasDesafios method, of class CadastrarBean.
     
    @Test
    public void testListaPalavrasDesafios() throws Exception {
        System.out.println("listaPalavrasDesafios");
        String palavra = "";
        CadastrarBean instance = new CadastrarBean();
        List<String> expResult = null;
        List<String> result = instance.listaPalavrasDesafios(palavra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editarTipoTexto method, of class CadastrarBean.
     
    @Test
    public void testEditarTipoTexto() {
        System.out.println("editarTipoTexto");
        int id = 0;
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.editarTipoTexto(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedTiposTextoUsuario method, of class CadastrarBean.
     
    @Test
    public void testGetSelectedTiposTextoUsuario() {
        System.out.println("getSelectedTiposTextoUsuario");
        CadastrarBean instance = new CadastrarBean();
        List<String> expResult = null;
        List<String> result = instance.getSelectedTiposTextoUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedTiposTextoUsuario method, of class CadastrarBean.
   
    @Test
    public void testSetSelectedTiposTextoUsuario() {
        System.out.println("setSelectedTiposTextoUsuario");
        List<String> selectedTiposTextoUsuario = null;
        CadastrarBean instance = new CadastrarBean();
        instance.setSelectedTiposTextoUsuario(selectedTiposTextoUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarStatusUsuario method, of class CadastrarBean.
    
    @Test
    public void testAtualizarStatusUsuario() {
        System.out.println("atualizarStatusUsuario");
        int status = 0;
        CadastrarBean instance = new CadastrarBean();
        instance.atualizarStatusUsuario(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarNovoPerfil method, of class CadastrarBean.
    
    @Test
    public void testCriarNovoPerfil() {
        System.out.println("criarNovoPerfil");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.criarNovoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarPalavra method, of class CadastrarBean.
    
    @Test
    public void testSalvarPalavra() {
        System.out.println("salvarPalavra");
        CadastrarBean instance = new CadastrarBean();
        instance.salvarPalavra();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarDesafio method, of class CadastrarBean.
     
    @Test
    public void testSalvarDesafio() {
        System.out.println("salvarDesafio");
        int idAmigo = 0;
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.salvarDesafio(idAmigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarDesafiosUsuario method, of class CadastrarBean.
     
    @Test
    public void testListarDesafiosUsuario() {
        System.out.println("listarDesafiosUsuario");
        CadastrarBean instance = new CadastrarBean();
        instance.listarDesafiosUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHistoriasDesafios method, of class CadastrarBean.
     
    @Test
    public void testGetHistoriasDesafios() {
        System.out.println("getHistoriasDesafios");
        CadastrarBean instance = new CadastrarBean();
        HistoriasDesafios expResult = null;
        HistoriasDesafios result = instance.getHistoriasDesafios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHistoriasDesafios method, of class CadastrarBean.
     
    @Test
    public void testSetHistoriasDesafios() {
        System.out.println("setHistoriasDesafios");
        HistoriasDesafios historiasDesafios = null;
        CadastrarBean instance = new CadastrarBean();
        instance.setHistoriasDesafios(historiasDesafios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarHistoriaDesafio method, of class CadastrarBean.
    
    @Test
    public void testSalvarHistoriaDesafio() throws Exception {
        System.out.println("salvarHistoriaDesafio");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.salvarHistoriaDesafio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarPalavrasDoDesafio method, of class CadastrarBean.
    
    @Test
    public void testVerificarPalavrasDoDesafio() throws Exception {
        System.out.println("verificarPalavrasDoDesafio");
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.verificarPalavrasDoDesafio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarHistoriaNovamente method, of class CadastrarBean.
    
    @Test
    public void testSalvarHistoriaNovamente() {
        System.out.println("salvarHistoriaNovamente");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.salvarHistoriaNovamente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naoEstaAcordo method, of class CadastrarBean.
    
    @Test
    public void testNaoEstaAcordo() {
        System.out.println("naoEstaAcordo");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.naoEstaAcordo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of publicarDesafioBiblioteca method, of class CadastrarBean.
    
    @Test
    public void testPublicarDesafioBiblioteca() throws Exception {
        System.out.println("publicarDesafioBiblioteca");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.publicarDesafioBiblioteca();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desafioRating method, of class CadastrarBean.
    
    @Test
    public void testDesafioRating() {
        System.out.println("desafioRating");
        CadastrarBean instance = new CadastrarBean();
        String expResult = "";
        String result = instance.desafioRating();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPalavrasDoDesafio method, of class CadastrarBean.
    
    @Test
    public void testListarPalavrasDoDesafio() throws Exception {
        System.out.println("listarPalavrasDoDesafio");
        CadastrarBean instance = new CadastrarBean();
        List<String> expResult = null;
        List<String> result = instance.listarPalavrasDoDesafio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConclusaoDesafio method, of class CadastrarBean.
    
    @Test
    public void testIsConclusaoDesafio() {
        System.out.println("isConclusaoDesafio");
        Notificacoes notificacao = null;
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.isConclusaoDesafio(notificacao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNaoDeAcordo method, of class CadastrarBean.
    
    @Test
    public void testIsNaoDeAcordo() {
        System.out.println("isNaoDeAcordo");
        CadastrarBean instance = new CadastrarBean();
        boolean expResult = false;
        boolean result = instance.isNaoDeAcordo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desistir method, of class CadastrarBean.
    
    @Test
    public void testDesistir() {
        System.out.println("desistir");
        CadastrarBean instance = new CadastrarBean();
        instance.desistir();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
