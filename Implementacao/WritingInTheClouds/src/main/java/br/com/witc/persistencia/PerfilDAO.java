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
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class PerfilDAO {
    Session sessao;

    public PerfilDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }
    //cria novo perfil
    public void criarPerfil(Perfil perfil){
        
           try {
           sessao.saveOrUpdate(perfil);
        } catch (ConstraintViolationException e) {
          
                sessao.clear();    
            } 
    }
    // busca Perfil padrao do usuario
    public Perfil buscaPerfil(int id){
        return (Perfil) sessao.load(Perfil.class, id);
         
    }
    
    public Perfil burcarPerfilUsuario (Usuario usuario){
       return (Perfil) sessao.createQuery("FROM Perfil WHERE idUsuario=:idUsuario").setInteger("idUsuario",usuario.getId()).uniqueResult();
    }
}
