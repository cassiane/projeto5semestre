/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import br.com.witc.persistencia.PerfilDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Marcelo
 */
public class ControladorCadastroTest {
    
    @InjectMocks
    ControladorCadastro controlador = new ControladorCadastro();
    
    @Mock
    Usuario usuario;    
    @Mock
    PerfilDAO perfilDAO;    
    @Mock
    Perfil perfil;
    @Mock 
    TipoPerfil tipoPerfil;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testSolicitarAmizade() {        
        controlador.solicitarAmizade(Mockito.anyInt());
        Mockito.verify(usuario, times(1)).solicitarAmizade(Mockito.anyInt());
    } 
    
    @Test
    public void testSalvarTipoTextoUsuario() {
        controlador.salvarTipoTextoUsuario(Mockito.anyList(), Mockito.anyInt());
        Mockito.verify(usuario, times(1)).salvarTipoTextoUsuario(Mockito.anyList(), Mockito.anyInt());
    }
    
    @Test
    public void testCadastrarUsuario() throws Exception {  
        controlador.cadastrarUsuario(usuario);        
        Mockito.verify(usuario,times(1)).cadastrarUsuario();
    }
	
    @Test
    public void testRemoverAmizade() {
        controlador.removerAmizade(1);
        Mockito.verify(usuario,times(1)).removerAmizade(1);
    }
	
    @Test
    public void testCadastrarTipoPerfil() throws Exception {
        controlador.cadastrarTipoPerfil(tipoPerfil);
        Mockito.verify(tipoPerfil,times(1)).cadastrarTipoPerfil();
    }
	
    @Test
    public void testExcluirTodosTipoTextoUsuario() {
       controlador.excluirTodosTipoTextoUsuario(Mockito.anyInt());
       Mockito.verify(usuario,times(1)).excluirTodosTipoTextoUsuario(Mockito.anyInt());
    }
}
