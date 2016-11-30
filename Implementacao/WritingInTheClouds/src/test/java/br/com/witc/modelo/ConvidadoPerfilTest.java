/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.ConvidadoPerfilDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

/**
 *
 * @author 10070187
 */
public class ConvidadoPerfilTest {
    
    @Mock 
    ConvidadoPerfilDAO dao;
    ConvidadoPerfil convidado;
    Perfil perfil;
    Livro livro;
    
    @Before
    public void setUp() {
        dao = Mockito.mock(ConvidadoPerfilDAO.class);
        convidado = Mockito.mock(ConvidadoPerfil.class);
        livro = Mockito.mock(Livro.class);
        perfil = Mockito.mock(Perfil.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of remover method, of class ConvidadoPerfil.
     */
    @Test
    public void testRemover() {
       dao.carregar(perfil, livro);
       Mockito.verify(dao,times(1)).carregar(perfil, livro);
    }
}
