/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author 10070187
 */
public class PerfilTest {
    @InjectMocks
    Perfil perfil = new Perfil();
    @Mock 
    PerfilDAO dao;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of criarPerfil method, of class Perfil.
     */
    @Test
    public void testCriarPerfil() {
        perfil.criarPerfil(perfil);
        Mockito.verify(dao,times(1)).salvarPerfil(perfil);
    }
}
