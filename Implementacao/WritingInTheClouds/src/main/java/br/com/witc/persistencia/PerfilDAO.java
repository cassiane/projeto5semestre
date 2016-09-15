/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;

/**
 *
 * @author 00026108
 */
public class PerfilDAO {
    Session sessao;

    public PerfilDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }
    
    public Perfil carregarPerfil (Usuario usuario){
        
       Perfil p = (Perfil) sessao.createQuery("FROM Perfil WHERE idUsuario=:idUsuario ").setInteger("idUsuario",usuario.getId())               
                .uniqueResult();
                
        if (p==null){
        return null; // lançar excessao
    }
        return p;
    }
    
}
