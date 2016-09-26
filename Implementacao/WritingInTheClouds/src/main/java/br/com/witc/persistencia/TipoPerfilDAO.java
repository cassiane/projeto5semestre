/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.TipoPerfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
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
    /**
     * Retorna um objeto tipo perfil com os dados daquele objeto do banco de dados
     * @param id
     * @return 
     */
    public TipoPerfil carregarTipoPerfil(int id){
       return  (TipoPerfil) sessao.load(TipoPerfil.class,id);
    }
    /**
     * 
     * @param aThis 
     */
    public void salvarTipoPerfil(TipoPerfil aThis) {       
        try{
            sessao.saveOrUpdate(aThis);
        }catch(ConstraintViolationException e){
             sessao.clear();
        }    
    }
    
    /**
     * Método usado para quando criar um usuário ele ter o perfil de escritor como padrão
     * @return 
     */
    public TipoPerfil carregarTipoPerfilEscritor(){
        String consulta ="select * from tipoperfil where tipoperfil.tipoperfil like '%escritor%'"; 
        return (TipoPerfil) sessao.createSQLQuery(consulta).uniqueResult();        
    }
    
    /**
     * Retorna uma lista de todos os campos da tabela tipo perfil
     * @return 
     */
    public List<TipoPerfil> listarTiposPerfil() {
        String consulta ="select * from tipoperfil"; 
        List<TipoPerfil> lista ;
        lista = (List<TipoPerfil>) sessao.createSQLQuery(consulta);
        return lista;
    }
}
