/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorCadastro;
import br.com.witc.modelo.Desafios;
import br.com.witc.modelo.DesafiosPalavras;
import br.com.witc.modelo.DesafiosUsuarios;
import br.com.witc.modelo.HistoriasDesafios;
import br.com.witc.modelo.Notificacoes;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.PerfilDAO;
import br.com.witc.persistencia.UsuarioDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author vanderson
 */
@RunWith(MockitoJUnitRunner.class)
public class CadastrarBeanTest {
    
    @InjectMocks
    CadastrarBean instance = new CadastrarBean();
    
    @Mock
    ControladorCadastro controlador;
    
    @Mock
    Usuario usuario;
    
    @Mock
    UsuarioDAO usuarioDAO;
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
    public CadastrarBeanTest() {
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
     * Test of solicitarAmizade method, of class CadastrarBean.
     */
    @Test
    public void testSolicitarAmizade() {
        System.out.println("solicitarAmizade");
        Mockito.when(query.executeUpdate()).thenReturn(1);
        Mockito.when(usuario.getId()).thenReturn(Mockito.anyInt());
        //usuario.setId(1);
        controlador.usuarioLogado(usuario);
        instance.solicitarAmizade(2);
        Mockito.verify(controlador, Mockito.times(1)).solicitarAmizade(2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
