/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanderson
 */
interface Dependency {
    List<Integer> retrieveAllStats();
}

public class SystemUnderPerfilDAOTest {

    private Dependency dependency;
    
    public SystemUnderPerfilDAOTest() {
    }

}
