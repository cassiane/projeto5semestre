/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.ConvidadoPerfil;
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
 * @author 10070142
 */
public class ConvidadoPerfilDAOTest {
    @InjectMocks 
    ConvidadoPerfilDAO dao = new ConvidadoPerfilDAO();
    
    @Mock 
    ConvidadoPerfil convP;
    @Mock
    SessionFactory sessionFactory;    
    @Mock
    Session sessao; 
    @Mock
    ConvidadoPerfilDAO d;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCarregar() {
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        ConvidadoPerfil p = new ConvidadoPerfil();
        d.carregar(Mockito.any());
        Mockito.verify(d,times(1)).salvar(Mockito.any());
    }
    
}
