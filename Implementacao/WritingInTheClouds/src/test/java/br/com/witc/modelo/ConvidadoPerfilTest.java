/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.ConvidadoPerfilDAO;
import br.com.witc.persistencia.PerfilDAO;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class ConvidadoPerfilTest {
    
    @InjectMocks
    ConvidadoPerfil convidado = new ConvidadoPerfil();
    
    @Mock
    ConvidadoPerfilDAO dao;
    
    public ConvidadoPerfilTest() {
    }
    
    @Test
    public void testNegarEdicao() {
        System.out.println("negarEdicao");
        convidado.negarEdicao();
       Mockito.verify(dao).remover(convidado);
    }
    
}
