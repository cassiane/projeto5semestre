/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorCadastro;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


/**
 *
 * @author marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class CadastrarBeanTest {
    
    //@InjectMocks
    CadastrarBean cadastrar;  
    
    @Mock 
    ControladorCadastro controlador;
    
    @Mock
    AutenticarBean autenticar;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cadastrar = new CadastrarBean();
        
    }
     /**
     * Test of removerTodasAmizades method, of class CadastrarBean.
     */
    @Test
    public void testRemoverTodasAmizades() {
        System.out.println("removerTodasAmizades");
        int idUsuario = 1;
        Mockito.verify(this.controlador).removerTodasAmizades(idUsuario);

    }

}
