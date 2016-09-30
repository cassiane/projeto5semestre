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
    
    /**
     * Registrar na tabela a solicitação de edição
     * @param convite dados da solicitação
     */
    public void salvar(ConvidadoPerfil convite) {
        sessao.saveOrUpdate(convite);
    }
    
    /**
     * Acessa a tabela para carregar a lista de solicitação do usuario ativo
     * @param idPerfilConvidado perfil do usuario ativo
     * @return lista de solicitações
     */
    public List<ConvidadoPerfil> carregar(Perfil idPerfilConvidado) {
        List<ConvidadoPerfil> listConvPerf = new ArrayList<ConvidadoPerfil>();
        listConvPerf = (List<ConvidadoPerfil>) sessao.createQuery("FROM ConvidadoPerfil WHERE idPerfilConvidado = :convidado").setInteger("convidado", idPerfilConvidado.getId()).list();
        return listConvPerf;
    }
    
    /**
     * Remover da tabela a solicitação
     * @param convite registro a ser removido
     */
    public void remover(ConvidadoPerfil convite) {
        sessao.delete(convite);
    }
}
