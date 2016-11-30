/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.TipoPerfilException;
import br.com.witc.modelo.TipoPerfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.hql.internal.ast.QuerySyntaxException;

/**
 *
 * @author 00026108
 */
public class TipoPerfilDAO {
    Session sessao;

    public TipoPerfilDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
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
     * @throws br.com.witc.excessao.TipoPerfilException 
     */
    public TipoPerfil carregarTipoPerfilEscritor() throws TipoPerfilException, QuerySyntaxException{
        try{
            TipoPerfil tipoPerfilTmp = (TipoPerfil) sessao.createQuery("from TipoPerfil where tipoPerfil like '%escritor%'")
                    .uniqueResult();
            if (tipoPerfilTmp == null) {
                throw new TipoPerfilException("Não existe um perfil de escritor.");            
            }else{
                return tipoPerfilTmp;
            }
        }catch(QuerySyntaxException ex){
            throw new QuerySyntaxException(ex.getMessage());
        }
    }        
    
    
    /**
     * Retorna uma lista de todos os campos da tabela tipo perfil
     * @return 
     */
    public List<TipoPerfil> listarTiposPerfil() {  
        List<TipoPerfil> lista;
        lista = (List<TipoPerfil>) sessao.createQuery("from TipoPerfil").list();        
        return lista;
    }
    
    /**
     * Retorna uma lista de todos os campos da tabela tipo perfil que o usuário
     * pode se cadastrar
     * @param idUsuario
     * @return 
     */
    public List<TipoPerfil> listarTiposPerfilPossiveis(int idUsuario) {  
        List<TipoPerfil> lista;        
        String consulta = "Select t1.* from TipoPerfil t1 " +
            "where t1.id not in (select t1.id from TipoPerfil t1 " +
            "left join Perfil t2 on t1.id = t2.idTipoPerfil " +
            "where t2.idUsuario = :idUsuario)";
        lista = (List<TipoPerfil>) sessao.createSQLQuery(consulta).addEntity("TipoPerfil",TipoPerfil.class)
                .setInteger("idUsuario", idUsuario)
                .list();          
        return lista;
    }
    
}
