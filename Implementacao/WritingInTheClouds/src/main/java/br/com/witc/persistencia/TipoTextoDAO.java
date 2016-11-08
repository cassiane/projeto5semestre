/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TipoTextoException;
import br.com.witc.modelo.TipoTexto;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author root
 */
public class TipoTextoDAO {
    Session sessao;

    public TipoTextoDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
    }
    
    /**     
     * @return Uma lista contendo os tipos de textos cadastrados no sistema
     * @throws br.com.witc.excessao.TipoTextoException Caso não haja Tipos de Textos cadastrados no sistema
     */
    public List<TipoTexto> getLstTipoTexto() throws TipoTextoException {
        List<TipoTexto> lstTipoTexto = sessao.createCriteria(TipoTexto.class)
                .addOrder(Order.asc("tipoTexto"))
                .list();
        if (lstTipoTexto.isEmpty()) {
            throw new TipoTextoException("Nenhum tipo de texto cadastrado no sistema");
        }
        return lstTipoTexto;
    }
    
    /**
     * Retorna um objeto tipo texto com os dados daquele objeto do banco de dados
     * @param id
     * @return 
     */
    public TipoTexto carregarTipoTexto(int id){
       return (TipoTexto)  sessao.createQuery("from TipoTexto where id = :id")
               .setInteger("id", id)
               .uniqueResult();
    }
    
    /**
     * 
     * @param aThis 
     */
    public void salvarTipoTexto(TipoTexto aThis) {       
        try{
            sessao.saveOrUpdate(aThis);
        }catch(ConstraintViolationException e){
             sessao.clear();
        }    
    }
    
    /**
     * Retorna uma lista de todos os campos da tabela tipo perfil
     * @return 
     */
    public List<TipoTexto> listarTiposTexto() {  
        List<TipoTexto> lista;
        lista = (List<TipoTexto>) sessao.createQuery("from TipoTexto").list();        
        return lista;
    }
}
