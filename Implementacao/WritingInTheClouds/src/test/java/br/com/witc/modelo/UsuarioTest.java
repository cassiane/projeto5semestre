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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author marcio
 */
public class UsuarioTest {
    @InjectMocks
    Usuario instance = new Usuario();
    
    @Mock
    UsuarioDAO usuarioDAO;
    
    public UsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Usuario.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Usuario instance = new Usuario();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Usuario.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Usuario instance = new Usuario();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Usuario.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Usuario.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Usuario instance = new Usuario();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSobrenome method, of class Usuario.
     */
    @Test
    public void testGetSobrenome() {
        System.out.println("getSobrenome");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getSobrenome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSobrenome method, of class Usuario.
     */
    @Test
    public void testSetSobrenome() {
        System.out.println("setSobrenome");
        String sobrenome = "";
        Usuario instance = new Usuario();
        instance.setSobrenome(sobrenome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Usuario.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Usuario.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Usuario instance = new Usuario();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataAniversario method, of class Usuario.
     */
    @Test
    public void testGetDataAniversario() {
        System.out.println("getDataAniversario");
        Usuario instance = new Usuario();
        Calendar expResult = null;
        Calendar result = instance.getDataAniversario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataAniversario method, of class Usuario.
     */
    @Test
    public void testSetDataAniversario() {
        System.out.println("setDataAniversario");
        Calendar dataAniversario = null;
        Usuario instance = new Usuario();
        instance.setDataAniversario(dataAniversario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenero method, of class Usuario.
     */
    @Test
    public void testGetGenero() {
        System.out.println("getGenero");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getGenero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenero method, of class Usuario.
     */
    @Test
    public void testSetGenero() {
        System.out.println("setGenero");
        String genero = "";
        Usuario instance = new Usuario();
        instance.setGenero(genero);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFoto method, of class Usuario.
     */
    @Test
    public void testGetFoto() {
        System.out.println("getFoto");
        Usuario instance = new Usuario();
        byte[] expResult = null;
        byte[] result = instance.getFoto();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carregarFotoDefault method, of class Usuario.
     */
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
     * Test of setFoto method, of class Usuario.
     */
    @Test
    public void testSetFoto() {
        System.out.println("setFoto");
        byte[] foto = null;
        Usuario instance = new Usuario();
        instance.setFoto(foto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFotoCapa method, of class Usuario.
     */
    @Test
    public void testGetFotoCapa() {
        System.out.println("getFotoCapa");
        Usuario instance = new Usuario();
        byte[] expResult = null;
        byte[] result = instance.getFotoCapa();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFotoCapa method, of class Usuario.
     */
    @Test
    public void testSetFotoCapa() {
        System.out.println("setFotoCapa");
        byte[] fotoCapa = null;
        Usuario instance = new Usuario();
        instance.setFotoCapa(fotoCapa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSenha method, of class Usuario.
     */
    @Test
    public void testGetSenha() {
        System.out.println("getSenha");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getSenha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSenha method, of class Usuario.
     */
    @Test
    public void testSetSenha() {
        System.out.println("setSenha");
        String senha = "";
        Usuario instance = new Usuario();
        instance.setSenha(senha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Usuario.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Usuario.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        Usuario instance = new Usuario();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAtivo method, of class Usuario.
     */
    @Test
    public void testIsAtivo() {
        System.out.println("isAtivo");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.isAtivo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAtivo method, of class Usuario.
     */
    @Test
    public void testSetAtivo() {
        System.out.println("setAtivo");
        boolean ativo = false;
        Usuario instance = new Usuario();
        instance.setAtivo(ativo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNomeABNT method, of class Usuario.
     */
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
     */
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
     */
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
     */
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
        System.out.println("excluirUsuario");
        Usuario instance = new Usuario();
        instance.excluirUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarUsuario method, of class Usuario.
     */
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
     */
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
     */
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
     */
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
     */
    @Test
    public void testSolicitarAmizade() {
        instance.setId(1);
        instance.solicitarAmizade(2);
        Mockito.verify(usuarioDAO,times(1)).solicitarAmizade(instance.getId(), 2);
    }
    /**
     * Test of listarSolicitacao method, of class Usuario.
     */
    @Test
    public void testListarSolicitacao() {
        System.out.println("listarSolicitacao");
        
    }

    /**
     * Test of aceitarAmizade method, of class Usuario.
     */
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
     */
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
     */
    @Test
    public void testRemoverTodasAmizades() {
        System.out.println("removerTodasAmizades");
        int idUsuario = 0;
        Usuario instance = new Usuario();
        instance.removerTodasAmizades(idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarTipoTextoUsuario method, of class Usuario.
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
    @Test
    public void testAtualizarStatus() {
        System.out.println("atualizarStatus");
        int status = 0;
        Usuario instance = new Usuario();
        instance.atualizarStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipostextos method, of class Usuario.
     */
    @Test
    public void testGetTipostextos() {
        System.out.println("getTipostextos");
        Usuario instance = new Usuario();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.getTipostextos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipostextos method, of class Usuario.
     */
    @Test
    public void testSetTipostextos() {
        System.out.println("setTipostextos");
        List<TipoTexto> tipostextos = null;
        Usuario instance = new Usuario();
        instance.setTipostextos(tipostextos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipostextosRevisor method, of class Usuario.
     */
    @Test
    public void testGetTipostextosRevisor() {
        System.out.println("getTipostextosRevisor");
        Usuario instance = new Usuario();
        List<TipoTexto> expResult = null;
        List<TipoTexto> result = instance.getTipostextosRevisor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipostextosRevisor method, of class Usuario.
     */
    @Test
    public void testSetTipostextosRevisor() {
        System.out.println("setTipostextosRevisor");
        List<TipoTexto> tipostextosRevisor = null;
        Usuario instance = new Usuario();
        instance.setTipostextosRevisor(tipostextosRevisor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
