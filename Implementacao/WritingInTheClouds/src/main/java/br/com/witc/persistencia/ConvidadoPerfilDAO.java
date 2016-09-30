/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.ConvidadoPerfil;
import br.com.witc.modelo.Perfil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author 10070124
 */
public class ConvidadoPerfilDAO {
    private final Session sessao;

    public ConvidadoPerfilDAO() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void salvar(ConvidadoPerfil convite) {
        sessao.saveOrUpdate(convite);
    }
    
    public List<ConvidadoPerfil> carregar(Perfil idPerfilConvidado) {
        List<ConvidadoPerfil> listConvPerf = new ArrayList<ConvidadoPerfil>();
        listConvPerf = (List<ConvidadoPerfil>) sessao.createQuery("FROM ConvidadoPerfil WHERE idPerfilConvidado = :convidado").setInteger("convidado", idPerfilConvidado.getId()).list();
        return listConvPerf;
    }
    
    public void remover(ConvidadoPerfil convite) {
        sessao.delete(convite);
    }
}
