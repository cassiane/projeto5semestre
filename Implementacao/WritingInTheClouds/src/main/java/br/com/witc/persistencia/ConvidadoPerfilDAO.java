/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.ConvidadoPerfil;
import br.com.witc.modelo.Perfil;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author 10070124
 */
public class ConvidadoPerfilDAO {
    private final Session sessao;

    public ConvidadoPerfilDAO() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Registrar na tabela a solicitação de edição
     * @param convite dados da solicitação
     */
    public void salvar(ConvidadoPerfil convite) {
        sessao.save(convite);
    }
    
    /**
     * Acessa a tabela para carregar a lista de solicitação do usuario ativo
     * @param idPerfilConvidado perfil do usuario ativo
     * @return lista de solicitações
     */
    public List<ConvidadoPerfil> carregar(int idPerfilConvidado) {
        List<ConvidadoPerfil> listConvPerf = new ArrayList<ConvidadoPerfil>();
        //List<ConvidadoPerfil> listConvPerf = new ArrayList();
        Query select = sessao.createSQLQuery("CALL witc.proc_soliedicao(:idper)")
                //.addEntity(ConvidadoPerfil.class)
                .setParameter("idper", idPerfilConvidado);
        //listConvPerf = (List<ConvidadoPerfil>) sessao.createQuery("FROM ConvidadoPerfil WHERE idPerfilConvidado = :convidado").setInteger("convidado", idPerfilConvidado.getId()).list();
        
        //listConvPerf = sessao.createCriteria(ConvidadoPerfil.class).add(Restrictions.eq("idPerfilConvidado", idPerfilConvidado)).list();
        
//        SQLQuery sql = sessao.createSQLQuery("SELECT * FROM ConvidadoPerfil WHERE idPerfilConvidado = :con");
//        sql.setInteger("con", idPerfilConvidado.getId());
//        List<Object[]> resultado = sql.list();
//        for(Object[] ob : resultado) {
//            ConvidadoPerfil o = new ConvidadoPerfil();
//            o.getIdPerfil().setId(Integer.parseInt(ob[0].toString()));
//            o.getIdPerfilConvidado().setId((int) ob[1]);
//            o.getIdLivro().setId((int) ob[2]);
//            o.getDataSolicitacao().setTime(new Date(ob[3].toString()));
//            //for(Object o : ob) {
//                //if (o instanceof ConvidadoPerfil) {
//            listConvPerf.add(o);
//                    //break;
//                //}
//            //}
//        }
        
//        String sql = "FROM ConvidadoPerfil WHERE idPerfilConvidado = :con";
        //listConvPerf = sessao.createSQLQuery(sql).addEntity(ConvidadoPerfil.class).setInteger("con", idPerfilConvidado.getId()).list();
        
//        List<Object[]> resultado = sessao.createQuery(sql).setInteger("con", idPerfilConvidado.getId()).list();
//        for(Object[] obj : resultado) {
//            for(Object ob : obj) {
//                if (ob instanceof ConvidadoPerfil) {
//                    listConvPerf.add((ConvidadoPerfil) ob);
//                }
//            }
//        }
        
        List retorno = select.list();
        listConvPerf = (List<ConvidadoPerfil>) retorno;
        return listConvPerf;
    }
    
    /**
     * Remover da tabela a solicitação
     * @param convite registro a ser removido
     */
    public void remover(ConvidadoPerfil convite) {
        sessao.delete(convite);
        sessao.flush();
    }
}
