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
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author cassiane.santos
 */
@RunWith(MockitoJUnitRunner.class)
public class ControladorCadastroTest {
    
    
    @Mock    
    Usuario usuario;
    ControladorCadastro controlador;
    TipoPerfil tipoperfil;
    
    @Before
    public void setUp() {        
        usuario = Mockito.mock(Usuario.class);
        controlador = Mockito.mock(ControladorCadastro.class);
        tipoperfil = Mockito.mock(TipoPerfil.class);
    }
    
    @After
    public void tearDown() {
        usuario = null;
        controlador = null;
        tipoperfil = null;
    }

    /**
     * Test of cadastrarUsuario method, of class ControladorCadastro.
     * @throws java.lang.Exception
     */
    @Test
    public void testCadastrarUsuario() throws Exception {  
        controlador.cadastrarUsuario(usuario);        
        Mockito.verify(controlador,times(1)).cadastrarUsuario(usuario);
    }

    /**
     * Test of alterarUsuario method, of class ControladorCadastro.
     
    @Test
    public void testAlterarUsuario() throws Exception {
        System.out.println("alterarUsuario");
        Usuario usuario = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.alterarUsuario(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirUsuario method, of class ControladorCadastro.
     
    @Test
    public void testExcluirUsuario() throws Exception {
        System.out.println("excluirUsuario");
        Usuario usuario = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.excluirUsuario(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarAmigos method, of class ControladorCadastro.
     
    @Test
    public void testListarAmigos() {
        System.out.println("listarAmigos");
        ControladorCadastro instance = new ControladorCadastro();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarSugestao method, of class ControladorCadastro.
     
    @Test
    public void testListarSugestao() {
        System.out.println("listarSugestao");
        ControladorCadastro instance = new ControladorCadastro();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarSugestao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usuarioLogado method, of class ControladorCadastro.
     
    @Test
    public void testUsuarioLogado() {
        System.out.println("usuarioLogado");
        Usuario usuario = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.usuarioLogado(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solicitarAmizade method, of class ControladorCadastro.
     
    @Test
    public void testSolicitarAmizade() {
        System.out.println("solicitarAmizade");
        int idSugestao = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.solicitarAmizade(idSugestao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarSolicitacao method, of class ControladorCadastro.
     
    @Test
    public void testListarSolicitacao() {
        System.out.println("listarSolicitacao");
        ControladorCadastro instance = new ControladorCadastro();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarSolicitacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceitarAmizade method, of class ControladorCadastro.
     
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
     * Test of removerAmizade method, of class ControladorCadastro.
    **/
    @Test
    public void testRemoverAmizade() {
        controlador.removerAmizade(1);
        Mockito.verify(controlador,times(1)).removerAmizade(1);
    }

    /**
     * Test of removerTodasAmizades method, of class ControladorCadastro.
     * @throws java.lang.Exception     
    @Test
    public void testRemoverTodasAmizades() {
        System.out.println("removerTodasAmizades");
        int idUsuario = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.removerTodasAmizades(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviarConvite method, of class ControladorCadastro.
     
    @Test
    public void testEnviarConvite() throws Exception {
        System.out.println("enviarConvite");
        String o = "";
        String path = "";
        ControladorCadastro instance = new ControladorCadastro();
        instance.enviarConvite(o, path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarUsuarios method, of class ControladorCadastro.
     
    @Test
    public void testListarUsuarios() {
        System.out.println("listarUsuarios");
        ControladorCadastro instance = new ControladorCadastro();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarConvite method, of class ControladorCadastro.
     
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
     * Test of recuperarConta method, of class ControladorCadastro.
     
    @Test
    public void testRecuperarConta() throws Exception {
        System.out.println("recuperarConta");
        String destinatario = "";
        String path = "";
        ControladorCadastro instance = new ControladorCadastro();
        instance.recuperarConta(destinatario, path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of redefinirSenha method, of class ControladorCadastro.
     
    @Test
    public void testRedefinirSenha() throws Exception {
        System.out.println("redefinirSenha");
        String email = "";
        String hashCode = "";
        String novaSenha = "";
        ControladorCadastro instance = new ControladorCadastro();
        instance.redefinirSenha(email, hashCode, novaSenha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarPerfilPadrao method, of class ControladorCadastro.
     
    @Test
    public void testCriarPerfilPadrao() {
        System.out.println("criarPerfilPadrao");
        Usuario usuario = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.criarPerfilPadrao(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarTipoPerfil method, of class ControladorCadastro.
    **/ 
    @Test
    public void testCadastrarTipoPerfil() throws Exception {
        controlador.cadastrarTipoPerfil(tipoperfil);
        Mockito.verify(controlador,times(1)).cadastrarTipoPerfil(tipoperfil);
    }

    /**
     * Test of listarTipoPerfil method, of class ControladorCadastro.
    
    @Test
    public void testListarTipoPerfil() {
        System.out.println("listarTipoPerfil");
        ControladorCadastro instance = new ControladorCadastro();
        List<TipoPerfil> expResult = null;
        List<TipoPerfil> result = instance.listarTipoPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTipoPerfilPossiveis method, of class ControladorCadastro.
     
    @Test
    public void testListarTipoPerfilPossiveis() {
        System.out.println("listarTipoPerfilPossiveis");
        int idUsuario = 0;
        ControladorCadastro instance = new ControladorCadastro();
        List<TipoPerfil> expResult = null;
        List<TipoPerfil> result = instance.listarTipoPerfilPossiveis(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarTipoTexto method, of class ControladorCadastro.
     
    @Test
    public void testCadastrarTipoTexto() throws Exception {
        System.out.println("cadastrarTipoTexto");
        TipoTexto tipoTexto = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.cadastrarTipoTexto(tipoTexto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarTipoTexto method, of class ControladorCadastro.
     
    @Test
    public void testCarregarTipoTexto() {
        System.out.println("carregarTipoTexto");
        int id = 0;
        ControladorCadastro instance = new ControladorCadastro();
        TipoTexto expResult = null;
        TipoTexto result = instance.carregarTipoTexto(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarTipoTextoPorNome method, of class ControladorCadastro.
     
    @Test
    public void testCarregarTipoTextoPorNome() {
        System.out.println("carregarTipoTextoPorNome");
        String nome = "";
        ControladorCadastro instance = new ControladorCadastro();
        TipoTexto expResult = null;
        TipoTexto result = instance.carregarTipoTextoPorNome(nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarTipoTextoUsuario method, of class ControladorCadastro.
     
    @Test
    public void testSalvarTipoTextoUsuario() {
        System.out.println("salvarTipoTextoUsuario");
        List<String> tiposTextoUsuario = null;
        int idUsuario = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.salvarTipoTextoUsuario(tiposTextoUsuario, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirTipoTextoUsuario method, of class ControladorCadastro.
     
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

    /**
     * Test of excluirTodosTipoTextoUsuario method, of class ControladorCadastro.
    **/ 
    @Test
    public void testExcluirTodosTipoTextoUsuario() {
       controlador.excluirTodosTipoTextoUsuario(usuario.getId());
       Mockito.verify(controlador,times(1)).excluirTodosTipoTextoUsuario(usuario.getId());
    }

    /**
     * Test of listarTipoTexto method, of class ControladorCadastro.
     
    @Test
    public void testListarTipoTexto() throws Exception {
        System.out.println("listarTipoTexto");
        ControladorCadastro instance = new ControladorCadastro();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.listarTipoTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarDesafiosPalavras method, of class ControladorCadastro.
     
    @Test
    public void testListarDesafiosPalavras() throws Exception {
        System.out.println("listarDesafiosPalavras");
        ControladorCadastro instance = new ControladorCadastro();
        List<String> expResult = null;
        List<String> result = instance.listarDesafiosPalavras();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPalavrasDoDesafio method, of class ControladorCadastro.
     
    @Test
    public void testListarPalavrasDoDesafio() throws Exception {
        System.out.println("listarPalavrasDoDesafio");
        int idDesafio = 0;
        ControladorCadastro instance = new ControladorCadastro();
        List<String> expResult = null;
        List<String> result = instance.listarPalavrasDoDesafio(idDesafio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarPerfilUsuario method, of class ControladorCadastro.
     
    @Test
    public void testCriarPerfilUsuario() {
        System.out.println("criarPerfilUsuario");
        int idTipo = 0;
        Usuario usuario = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.criarPerfilUsuario(idTipo, usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarDesafio method, of class ControladorCadastro.
     
    @Test
    public void testSalvarDesafio() {
        System.out.println("salvarDesafio");
        List<String> listaPalavras = null;
        int idDesafio = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.salvarDesafio(listaPalavras, idDesafio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarDesafiosUsuarios method, of class ControladorCadastro.
     
    @Test
    public void testSalvarDesafiosUsuarios() {
        System.out.println("salvarDesafiosUsuarios");
        DesafiosUsuarios des = null;
        ControladorCadastro instance = new ControladorCadastro();
        int expResult = 0;
        int result = instance.salvarDesafiosUsuarios(des);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarDesafiosUsuarios method, of class ControladorCadastro.
     
    @Test
    public void testListarDesafiosUsuarios() {
        System.out.println("listarDesafiosUsuarios");
        int idUsuario = 0;
        ControladorCadastro instance = new ControladorCadastro();
        List<DesafiosUsuarios> expResult = null;
        List<DesafiosUsuarios> result = instance.listarDesafiosUsuarios(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarHistoriaDesafio method, of class ControladorCadastro.
     
    @Test
    public void testSalvarHistoriaDesafio() {
        System.out.println("salvarHistoriaDesafio");
        HistoriasDesafios historiasDesafios = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.salvarHistoriaDesafio(historiasDesafios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarDesafiosUsuarios method, of class ControladorCadastro.
     
    @Test
    public void testCarregarDesafiosUsuarios() {
        System.out.println("carregarDesafiosUsuarios");
        int idDesafiosUsuarios = 0;
        ControladorCadastro instance = new ControladorCadastro();
        DesafiosUsuarios expResult = null;
        DesafiosUsuarios result = instance.carregarDesafiosUsuarios(idDesafiosUsuarios);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarNotificacao method, of class ControladorCadastro.
     
    @Test
    public void testSalvarNotificacao() {
        System.out.println("salvarNotificacao");
        Notificacoes notificacao = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.salvarNotificacao(notificacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarNotificacoes method, of class ControladorCadastro.
     
    @Test
    public void testListarNotificacoes() {
        System.out.println("listarNotificacoes");
        int idUsuario = 0;
        ControladorCadastro instance = new ControladorCadastro();
        List<Notificacoes> expResult = null;
        List<Notificacoes> result = instance.listarNotificacoes(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirNotificacao method, of class ControladorCadastro.
     
    @Test
    public void testExcluirNotificacao() {
        System.out.println("excluirNotificacao");
        int idDesafiosUsuarios = 0;
        ControladorCadastro instance = new ControladorCadastro();
        instance.excluirNotificacao(idDesafiosUsuarios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarHistoriasDesafios method, of class ControladorCadastro.
     
    @Test
    public void testCarregarHistoriasDesafios() {
        System.out.println("carregarHistoriasDesafios");
        DesafiosUsuarios desafiosUsuarios = null;
        ControladorCadastro instance = new ControladorCadastro();
        HistoriasDesafios expResult = null;
        HistoriasDesafios result = instance.carregarHistoriasDesafios(desafiosUsuarios);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarHistoriasDesafiosPorId method, of class ControladorCadastro.
     
    @Test
    public void testCarregarHistoriasDesafiosPorId() {
        System.out.println("carregarHistoriasDesafiosPorId");
        int idHistoriasDesafios = 0;
        ControladorCadastro instance = new ControladorCadastro();
        HistoriasDesafios expResult = null;
        HistoriasDesafios result = instance.carregarHistoriasDesafiosPorId(idHistoriasDesafios);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarDesafioBiblioteca method, of class ControladorCadastro.
     
    @Test
    public void testSalvarDesafioBiblioteca() {
        System.out.println("salvarDesafioBiblioteca");
        Livro livro = null;
        Perfil perfil = null;
        ControladorCadastro instance = new ControladorCadastro();
        instance.salvarDesafioBiblioteca(livro, perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarUsuarioPorIdPerfil method, of class ControladorCadastro.
     
    @Test
    public void testCarregarUsuarioPorIdPerfil() {
        System.out.println("carregarUsuarioPorIdPerfil");
        int idPerfil = 0;
        ControladorCadastro instance = new ControladorCadastro();
        Usuario expResult = null;
        Usuario result = instance.carregarUsuarioPorIdPerfil(idPerfil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
