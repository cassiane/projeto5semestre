/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.TipoPerfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class TipoPerfilDAO {
    Session sessao;

    public TipoPerfilDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }
    
    public TipoPerfil carregarTipoPerfil(int id){
       return  (TipoPerfil) sessao.load(TipoPerfil.class,id);
    }
    /**
     * 
     * @param aThis 
     */
    public void cadastrarTipoPerfil(TipoPerfil aThis) {       
        try{
            sessao.save(aThis);
        }catch(ConstraintViolationException e){
             sessao.clear();
        }    
    }
    
}
