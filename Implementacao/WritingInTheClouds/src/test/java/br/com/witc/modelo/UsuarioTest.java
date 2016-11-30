/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.UsuarioDAO;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author 10070187
 */
public class UsuarioTest {
    @Mock 
    Usuario usuario;
    UsuarioDAO dao;
    @Before
    public void setUp() {
        usuario = Mockito.mock(Usuario.class);
        dao = Mockito.mock(UsuarioDAO.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of carregarFotoDefault method, of class Usuario.
     
    @Test
    public void testCarregarFotoDefault() {
        System.out.println("carregarFotoDefault");
        Usuario instance = new Usuario();
        StreamedContent expResult = null;
        StreamedContent result = instance.carregarFotoDefault();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomeABNT method, of class Usuario.
     
    @Test
    public void testGetNomeABNT() {
        System.out.println("getNomeABNT");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNomeABNT();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of efetuarLogin method, of class Usuario.
     
    @Test
    public void testEfetuarLogin() throws Exception {
        System.out.println("efetuarLogin");
        String email = "";
        String senha = "";
        Usuario expResult = null;
        Usuario result = Usuario.efetuarLogin(email, senha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarHashSenha method, of class Usuario.
     
    @Test
    public void testCriarHashSenha() throws Exception {
        System.out.println("criarHashSenha");
        String senha = "";
        String expResult = "";
        String result = Usuario.criarHashSenha(senha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cadastrarUsuario method, of class Usuario.
     
    @Test
    public void testCadastrarUsuario() throws Exception {
        System.out.println("cadastrarUsuario");
        Usuario instance = new Usuario();
        instance.cadastrarUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirUsuario method, of class Usuario.
     */
    @Test
    public void testExcluirUsuario() throws Exception {        
        usuario.excluirUsuario();
        Mockito.verify(usuario,times(1)).excluirUsuario();
    }

    /**
     * Test of alterarUsuario method, of class Usuario.
     
    @Test
    public void testAlterarUsuario() throws Exception {
        System.out.println("alterarUsuario");
        Usuario instance = new Usuario();
        instance.alterarUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarAmigos method, of class Usuario.
     
    @Test
    public void testListarAmigos() {
        System.out.println("listarAmigos");
        Usuario instance = new Usuario();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarSugestao method, of class Usuario.
     
    @Test
    public void testListarSugestao() {
        System.out.println("listarSugestao");
        Usuario instance = new Usuario();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarSugestao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarUsuarios method, of class Usuario.
     
    @Test
    public void testListarUsuarios() {
        System.out.println("listarUsuarios");
        Usuario instance = new Usuario();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of solicitarAmizade method, of class Usuario.
     
    @Test
    public void testSolicitarAmizade() {
        System.out.println("solicitarAmizade");
        int idSugestao = 0;
        Usuario instance = new Usuario();
        instance.solicitarAmizade(idSugestao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarSolicitacao method, of class Usuario.
     
    @Test
    public void testListarSolicitacao() {
        System.out.println("listarSolicitacao");
        Usuario instance = new Usuario();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.listarSolicitacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceitarAmizade method, of class Usuario.
     
    @Test
    public void testAceitarAmizade() {
        System.out.println("aceitarAmizade");
        int idAceitar = 0;
        Usuario instance = new Usuario();
        instance.aceitarAmizade(idAceitar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerAmizade method, of class Usuario.
     
    @Test
    public void testRemoverAmizade() {
        System.out.println("removerAmizade");
        int idAmizade = 0;
        Usuario instance = new Usuario();
        instance.removerAmizade(idAmizade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerTodasAmizades method, of class Usuario.
    **/ 
    @Test
    public void testRemoverTodasAmizades() {
        usuario.removerTodasAmizades(1);
        Mockito.verify(usuario,times(1)).removerTodasAmizades(1);
    }

    /**
     * Test of salvarTipoTextoUsuario method, of class Usuario.
     
    @Test
    public void testSalvarTipoTextoUsuario() {
        System.out.println("salvarTipoTextoUsuario");
        List<String> tiposTextoUsuario = null;
        int idUsuario = 0;
        Usuario instance = new Usuario();
        instance.salvarTipoTextoUsuario(tiposTextoUsuario, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirTipoTextoUsuario method, of class Usuario.
    
    @Test
    public void testExcluirTipoTextoUsuario() {
        System.out.println("excluirTipoTextoUsuario");
        int idUsuario = 0;
        int idTipoTexto = 0;
        Usuario instance = new Usuario();
        instance.excluirTipoTextoUsuario(idUsuario, idTipoTexto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirTodosTipoTextoUsuario method, of class Usuario.
     
    @Test
    public void testExcluirTodosTipoTextoUsuario() {
        System.out.println("excluirTodosTipoTextoUsuario");
        int idUsuario = 0;
        Usuario instance = new Usuario();
        instance.excluirTodosTipoTextoUsuario(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Usuario.
     
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarExistenciaUsuario method, of class Usuario.
     
    @Test
    public void testVerificarExistenciaUsuario() throws Exception {
        System.out.println("verificarExistenciaUsuario");
        String Email = "";
        Usuario expResult = null;
        Usuario result = Usuario.verificarExistenciaUsuario(Email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consistirDados method, of class Usuario.
     
    @Test
    public void testConsistirDados() throws Exception {
        System.out.println("consistirDados");
        Usuario instance = new Usuario();
        instance.consistirDados();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviarConviteEmail method, of class Usuario.
     
    @Test
    public void testEnviarConviteEmail() throws Exception {
        System.out.println("enviarConviteEmail");
        String destinatario = "";
        String path = "";
        Usuario instance = new Usuario();
        instance.enviarConviteEmail(destinatario, path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarConvite method, of class Usuario.
     
    @Test
    public void testVerificarConvite() {
        System.out.println("verificarConvite");
        String email = "";
        Usuario instance = new Usuario();
        instance.verificarConvite(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarAmigo method, of class Usuario.
     
    @Test
    public void testCarregarAmigo() {
        System.out.println("carregarAmigo");
        int id = 0;
        Usuario instance = new Usuario();
        Usuario expResult = null;
        Usuario result = instance.carregarAmigo(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atualizarStatus method, of class Usuario.
     
    @Test
    public void testAtualizarStatus() {
        System.out.println("atualizarStatus");
        int status = 0;
        Usuario instance = new Usuario();
        instance.atualizarStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    **/
}
