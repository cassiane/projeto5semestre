/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.ConvidadoPerfil;
import org.hibernate.Session;
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
public class ConvidadoPerfilDAOTest {
    @Mock 
    ConvidadoPerfilDAO dao;
    ConvidadoPerfil convP;
    
    @Before
    public void setUp() {
        dao = Mockito.mock(ConvidadoPerfilDAO.class);
        convP = Mockito.mock(ConvidadoPerfil.class);
    }

    /**
     * Test of salvar method, of class ConvidadoPerfilDAO.
     */
    @Test
    public void testSalvar() {
        dao.salvar(convP);
        Mockito.verify(dao,times(1)).salvar(convP);
    }
    
}
