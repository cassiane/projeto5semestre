/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoricoLivro;
import br.com.witc.modelo.Livro;
import br.com.witc.modelo.Perfil;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class HistoricoLivroDAO {
    Session sessao;
    HistoricoLivro historico;
    SessionFactory sessionFactory;

    public HistoricoLivroDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
    }
    
     public void salvarHistorico(HistoricoLivro hist){
         
          try {
           sessao.saveOrUpdate(hist);
           
            } catch (ConstraintViolationException e) {
          
                sessao.clear();
             
            } 
        }
     
    /**     
     * @param idLivro O id do livro pesquisado
     * @return Uma lista com os registros daquele livro no BD
     */ 
    public List<HistoricoLivro> listarHistoricoLivro(int idLivro) {
        return sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro")
                .setString("idLivro", String.valueOf(idLivro))
                .list();
    }
    
    /**
     * Carrega o histórico de um determinado livro e de um determinado perfil
     * @param livro O livro buscado
     * @param perfil O perfil do usuário
     * @return Um objeto HistoricoLivro
     */
    public HistoricoLivro carregarHistoricoLivroUsuario(Livro livro, Perfil perfil) {
        return (HistoricoLivro) sessao.createCriteria(HistoricoLivro.class)
                .add(Restrictions.eq("livro", livro))
                .add(Restrictions.eq("perfil", perfil))
                .uniqueResult();
    }
    
    /**
     * Verifica se o livro já foi finalizado pelo usuário
     * @param idLivro O id do livro buscado
     * @param idPerfil O id do perfil do usuário
     * @return Um objeto HistoricoLivro
     */
    public boolean estaFinalizadoUsuario(int idLivro, int idPerfil) {
        historico = (HistoricoLivro) sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil")
                .setString("idLivro", String.valueOf(idLivro))
                .setString("idPerfil", String.valueOf(idPerfil))
//                .setInteger("idStatus", 1)
                .uniqueResult();
        
       sessao.refresh(historico);
        return historico.getDataConclusao() != null;
    }
    public boolean estaFinalizadoRevisaoUsuario(int idLivro, int idPerfil) {
        HistoricoLivro historico = (HistoricoLivro) sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil AND idTipoStatus=:idStatus")
                .setString("idLivro", String.valueOf(idLivro))
                .setString("idPerfil", String.valueOf(idPerfil))
                .setInteger("idStatus", 2)
                .uniqueResult();
        
       sessao.refresh(historico);
        return historico.getDataConclusao() != null;
    }

    public void salvarHistoricoConvite(int perfil, int livro) {
        sessao.createQuery("CALL witc.proc_historico_convite(:per, :liv)")
                .setParameter("per", perfil)
                .setParameter("liv", livro)
                .executeUpdate();
    }
}