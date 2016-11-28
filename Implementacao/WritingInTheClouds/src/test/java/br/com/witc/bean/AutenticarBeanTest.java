/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorAutenticacao;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author cassiane.santos
 */
@RunWith(MockitoJUnitRunner.class)
public class AutenticarBeanTest {
    @InjectMocks
    AutenticarBean autenticar = new AutenticarBean();
    
    @Mock 
    ControladorAutenticacao controlador;
    @Mock 
    private AutenticarBean aut;
    @Mock 
    Perfil perfil;
    @Mock 
    Perfil perfil2;    
    @Mock 
    Usuario usuario;

    @Before
    public void setup(){
        aut = new AutenticarBean();
        controlador = Mockito.mock(ControladorAutenticacao.class);
        aut.setControlador(controlador);
    }
    
    /**
     * Test of isPerfis method, of class AutenticarBean.
     * Teste válido se retornar false porque 
     * forçamos o usuário a ter somente um perfil
     */
    @Test
    public void testIsPerfisFalse() {        
        List<Perfil> lista = new ArrayList<>();
        lista.add(perfil);        
        Mockito.when(controlador.listarPerfis()).thenReturn(lista);        
        assertEquals(aut.isPerfis(),false);        
    }
    
    /**
     * Test of isPerfis method, of class AutenticarBean.
    */
    @Test
    public void testIsPerfisVerdadeiro() {
        List<Perfil> lista = new ArrayList<Perfil>();
        lista.add(perfil);
        lista.add(perfil2);
        Mockito.when(controlador.listarPerfis()).thenReturn(lista); 
        assertEquals(aut.isPerfis(),true);
    }
    
    /**
     * Test of efetuarLogin method, of class AutenticarBean.
    */
    @Test
    public void testEfetuarLogin() {
        assertNotNull(aut.efetuarLogin());
    }

    /**
     * Test of setAmigoUsuario method, of class AutenticarBean.
     
    @Test
    public void testSetAmigoUsuario_int() {
        System.out.println("setAmigoUsuario");
        int id = 0;
        AutenticarBean instance = new AutenticarBean();
        instance.setAmigoUsuario(id);        
    }

    /**
     * Test of isAmigo method, of class AutenticarBean.
     
    @Test
    public void testIsAmigo() {
        System.out.println("isAmigo");
        AutenticarBean instance = new AutenticarBean();
        boolean expResult = false;
        boolean result = instance.isAmigo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdAmigoUsuario method, of class AutenticarBean.
     
    @Test
    public void testGetIdAmigoUsuario() {
        System.out.println("getIdAmigoUsuario");
        AutenticarBean instance = new AutenticarBean();
        int expResult = 0;
        int result = instance.getIdAmigoUsuario();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPerfil method, of class AutenticarBean.
     
    @Test
    public void testGetIdPerfil() {
        System.out.println("getIdPerfil");
        AutenticarBean instance = new AutenticarBean();
        int expResult = 0;
        int result = instance.getIdPerfil();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvaliacaoPerfil method, of class AutenticarBean.
     
    @Test
    public void testGetAvaliacaoPerfil() {
        System.out.println("getAvaliacaoPerfil");
        AutenticarBean instance = new AutenticarBean();
        float expResult = 0.0F;
        float result = instance.getAvaliacaoPerfil();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of listarPerfisAmigos method, of class AutenticarBean.
     
    @Test
    public void testListarPerfisAmigos() {
        System.out.println("listarPerfisAmigos");
        AutenticarBean instance = new AutenticarBean();
        instance.listarPerfisAmigos();
    }

    /**
     * Test of listarPerfisUsuarioLogado method, of class AutenticarBean.
     
    @Test
    public void testListarPerfisUsuarioLogado() {
        System.out.println("listarPerfisUsuarioLogado");
        AutenticarBean instance = new AutenticarBean();
        instance.listarPerfisUsuarioLogado();
    }

    /**
     * Test of atualizarStatusUsuario method, of class AutenticarBean.
     
    @Test
    public void testAtualizarStatusUsuario() {
        System.out.println("atualizarStatusUsuario");
        int status = 0;
        AutenticarBean instance = new AutenticarBean();
        instance.atualizarStatusUsuario(status);
    }

    /**
     * Test of trocarPerfilUsuario method, of class AutenticarBean.
     
    @Test
    public void testTrocarPerfilUsuario() {
        System.out.println("trocarPerfilUsuario");
        Perfil auxPerfil = null;
        AutenticarBean instance = new AutenticarBean();
        instance.trocarPerfilUsuario(auxPerfil);
    }

    /**
     * Test of perfilIgual method, of class AutenticarBean.
     
    @Test
    public void testPerfilIgual() {
        System.out.println("perfilIgual");
        Perfil perfil = null;
        AutenticarBean instance = new AutenticarBean();
        boolean expResult = false;
        boolean result = instance.perfilIgual(perfil);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPerfilEditor method, of class AutenticarBean.
     
    @Test
    public void testIsPerfilEditor() {
        System.out.println("isPerfilEditor");
        AutenticarBean instance = new AutenticarBean();
        boolean expResult = false;
        boolean result = instance.isPerfilEditor();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPerfilRevisor method, of class AutenticarBean.
     
    @Test
    public void testIsPerfilRevisor() {
        System.out.println("isPerfilRevisor");
        AutenticarBean instance = new AutenticarBean();
        boolean expResult = false;
        boolean result = instance.isPerfilRevisor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumeroAmigosUsuarioLogado method, of class AutenticarBean.
     
    @Test
    public void testGetNumeroAmigosUsuarioLogado() {
        System.out.println("getNumeroAmigosUsuarioLogado");
        AutenticarBean instance = new AutenticarBean();
        String expResult = "";
        String result = instance.getNumeroAmigosUsuarioLogado();
        assertEquals(expResult, result);
    }

    /**
     * Test of usuarioLogado method, of class AutenticarBean.
     
    @Test
    public void testUsuarioLogado() {
        System.out.println("usuarioLogado");
        AutenticarBean instance = new AutenticarBean();
        Usuario expResult = null;
        Usuario result = instance.usuarioLogado();
        assertEquals(expResult, result);
    }

    /**
     * Test of verificarAdministrador method, of class AutenticarBean.
     
    @Test
    public void testVerificarAdministrador() {
        System.out.println("verificarAdministrador");
        AutenticarBean instance = new AutenticarBean();
        boolean expResult = false;
        boolean result = instance.verificarAdministrador();
        assertEquals(expResult, result);
    }

    /**
     * Test of efetuarLogoff method, of class AutenticarBean.
     
    @Test
    public void testEfetuarLogoff() {
        System.out.println("efetuarLogoff");
        AutenticarBean instance = new AutenticarBean();
        String expResult = "";
        String result = instance.efetuarLogoff();
        assertEquals(expResult, result);
    }

    /**
     * Test of userRating method, of class AutenticarBean.
     
    @Test
    public void testUserRating() {
        System.out.println("userRating");
        AutenticarBean instance = new AutenticarBean();
        instance.userRating();
    }

    /**
     * Test of setarPerfilUsuario method, of class AutenticarBean.
     
    @Test
    public void testSetarPerfilUsuario() {
        System.out.println("setarPerfilUsuario");
        AutenticarBean instance = new AutenticarBean();
        instance.setarPerfilUsuario();
    }
    **/
    
}
