/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.ConvidadoPerfil;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Query;
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
        sessao.save(convite);
    }
    
    /**
     * Acessa a tabela para carregar a lista de solicitação do usuario ativo
     * @param idPerfilConvidado perfil do usuario ativo
     * @return lista de solicitações
     */
    public List<ConvidadoPerfil> carregar(Perfil idPerfilConvidado) {
        List<ConvidadoPerfil> listConvPerf = new ArrayList<ConvidadoPerfil>();
        Query sql = sessao.createSQLQuery("SELECT * FROM ConvidadoPerfil WHERE idPerfilConvidado = :con").setParameter("con", idPerfilConvidado.getId());
        List<Object[]> resultado = sql.list();
        
        // Parar a verificação
        if (resultado.isEmpty()) {
            return null;
        }
        
        List<String> busca = new ArrayList<>();
        for(Object[] ob : resultado) {
            for(Object o : ob) {
                busca.add(o.toString());
            }
        }
        
        for(int i=0; i<busca.size(); i++) {
            int per = Integer.parseInt(busca.get(i++)),
                    percon = Integer.parseInt(busca.get(i++)),
                    liv = Integer.parseInt(busca.get(i++));
            ConvidadoPerfil carrega = new ConvidadoPerfil();
            PerfilDAO preenche = new PerfilDAO();
            LivroDAO preenche2 = new LivroDAO();
            carrega.setIdPerfil(preenche.carregaPerfilID(per));
            carrega.setIdPerfilConvidado(preenche.carregaPerfilID(percon));
            carrega.setIdLivro(preenche2.carregarHistoricoConvite(liv));
            String formata = busca.get(i);
            int dia = Integer.parseInt(formata.substring(8, 10));
            int mes = Integer.parseInt(formata.substring(5, 7));
            int ano = Integer.parseInt(formata.substring(0, 4));
            int hora = Integer.parseInt(formata.substring(11, 13));
            int minuto = Integer.parseInt(formata.substring(14, 16));
            int segundo = Integer.parseInt(formata.substring(17, 19));
            Calendar dat = Calendar.getInstance();
            dat.set(ano, mes, dia, hora, minuto, segundo);
            carrega.setDataSolicitacao(dat);
            listConvPerf.add(carrega);
        }
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
    
    public ConvidadoPerfil carregar(Perfil perfil, Livro livro) {
        ConvidadoPerfil carrega = new ConvidadoPerfil();
        carrega = (ConvidadoPerfil) sessao.createQuery("FROM ConvidadoPerfil"
                + " WHERE idPerfilConvidado=:percon"
                + " AND idLivro=:liv")
                .setInteger("percon", perfil.getId())
                .setInteger("liv", livro.getId())
                .uniqueResult();
        return carrega;
    }
}
