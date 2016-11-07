/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.DesafiosUsuarios;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author cassiane.santos
 */
public class DesafiosUsuariosDAO {
    Session sessao;
    
    public DesafiosUsuariosDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
    }
    
    
    public List<DesafiosUsuarios> listarDesafiosUsuarios(int idUsuario) {  
        return sessao.createQuery("from DesafiosUsuarios where idUsuario = :idUsuario")                
                .setInteger("idUsuario", idUsuario)
                .list();
    }
    
    public int salvarDesafioUsuario(DesafiosUsuarios desafio){
        sessao.saveOrUpdate(desafio);
        return (int) sessao.getIdentifier(desafio);
    }
}
