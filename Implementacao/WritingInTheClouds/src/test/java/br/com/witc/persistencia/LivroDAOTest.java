/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Livro;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class LivroDAOTest {
    
    @InjectMocks
    LivroDAO dao = new LivroDAO();
    
    @Mock
    Livro livro;
    
    @Mock
    SessionFactory sessionFactory;
    
    @Mock
    Session sessao;
    
    @Mock
    Query query;
    
    public LivroDAOTest() {
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
     * Test of estaDisponivelEdicaoUsuario method, of class LivroDAO.
     */
    @Test
    public void testSalvarLivro() {
       dao.salvarLivro(livro);
       Mockito.verify(dao, times(1)).salvarLivro(livro);   
    }
}
