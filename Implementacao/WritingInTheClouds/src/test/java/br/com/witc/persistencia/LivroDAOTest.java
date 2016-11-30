/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoTexto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.hibernate.Session;
import org.mockito.InjectMocks;

/**
 *
 * @author 10070187
 */


public class LivroDAOTest {
    @InjectMocks
    LivroDAO dao = new LivroDAO();
    
    @Mock 
    Session sessao;
    
    @Before
    public void setUp() {
        dao = Mockito.mock(LivroDAO.class);
        sessao = Mockito.mock(Session.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listarLivrosRevisao method, of class LivroDAO.
     */
    @Test
    public void testListarLivrosRevisao() {        
        
    }
    
}
