/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
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
public class UsuarioDAOTest {
    
    @Mock
    UsuarioDAO dao;
    @Mock
    SessionFactory sessionFactory;    
    @Mock
    Session sessao;    
    @Mock
    SQLQuery query;    
    @Mock
    Usuario usuario;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExcluirUsuario() throws Exception {        
        Mockito.when(sessionFactory.openSession()).thenReturn(sessao);
        Usuario user = new Usuario();
        dao.efetuarLogin(Mockito.any(),Mockito.any());
        Mockito.verify(dao,times(1)).efetuarLogin(Mockito.any(),Mockito.any());
    }
}
