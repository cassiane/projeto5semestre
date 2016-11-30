/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

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

/**
 *
 * @author 10070142
 */
public class ControladorAutenticacaoTest {
    @InjectMocks
    ControladorAutenticacao instance = new ControladorAutenticacao();
    
    @Mock
    Perfil perfil;
    
    public ControladorAutenticacaoTest() {
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
     * Test of carregarPerfilPorId method, of class ControladorAutenticacao.
     */
    @Test
    public void testAtualizaStatusUsuario() {
        Mockito.when(perfil.carregarPerfilPorId(Mockito.anyInt()));        
        Perfil expResult = perfil;
        instance.atualizarStatusUsuario(Mockito.anyInt());
        Mockito.verify(instance,times(1)).atualizarStatusUsuario(Mockito.anyInt());        
    }
}
