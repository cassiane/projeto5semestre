/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.ControladorCadastro;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;


/**
 *
 * @author marcio
 */

public class CadastrarBeanTest {
    
    @InjectMocks
    CadastrarBean cadastrar = new CadastrarBean();  
    
    @Mock 
    ControladorCadastro controlador;
    
     @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        
    }
     /**
     * Test of removerTodasAmizades method, of class CadastrarBean.
     */
    @Test
    public void testRemoverTodasAmizades() {
        System.out.println("removerTodasAmizades");
        cadastrar.removerTodasAmizades(1);
        Mockito.verify(controlador,times(1)).removerTodasAmizades(1);
    }

}
