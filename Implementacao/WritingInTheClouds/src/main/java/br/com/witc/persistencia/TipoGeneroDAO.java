/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TipoGeneroException;
import br.com.witc.modelo.TipoGenero;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author root
 */
public class TipoGeneroDAO {
    Session sessao;

    public TipoGeneroDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
        
    }
    
    public List<TipoGenero> getLstTipoGenero() throws TipoGeneroException {
         List<TipoGenero> lstTipoGenero = sessao.createCriteria(TipoGenero.class)
                .addOrder(Order.asc("tipoGenero"))
                .list();
        if (lstTipoGenero.isEmpty()) {
            throw new TipoGeneroException("Nenhum tipo de genero cadastrado no sistema");
        }
        return lstTipoGenero;
    }
    
}
