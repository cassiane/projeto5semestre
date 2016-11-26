/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoPerfil;
import br.com.witc.modelo.Usuario;
import br.com.witc.persistencia.PerfilDAO;
import java.util.List;
import javax.persistence.Id;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Erick
 */
public class AutenticarBeanTest {
    
    public AutenticarBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of trocarPerfilUsuario method, of class AutenticarBean.
     * @throws java.lang.Exception
     */
    @Test (expected = Exception.class)
    public void testTrocarPerfilUsuario() throws Exception {
        try {
        // Crio um mock para a minha interface de acesso ao perfil
        PerfilDAO perfilDao = mock(PerfilDAO.class);
        // especifico o comportamento do mock
        Perfil perfil = new Perfil();
        perfil.setTipoPerfil(any());
        
        // Aqui utilizo o Mockito para para retornar o perfil acima quando o método "pegarPerfil" pega o id do tipo perfil
        doReturn(perfil).when(perfilDao).carregaPerfilID(isNull());
        
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("cause"));
            throw e;
        }
    }
}
