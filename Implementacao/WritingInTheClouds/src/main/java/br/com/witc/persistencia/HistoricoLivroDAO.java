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
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class HistoricoLivroDAO {
    Session sessao;

    public HistoricoLivroDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
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
        HistoricoLivro historico = (HistoricoLivro) sessao.createQuery("FROM HistoricoLivro WHERE idLivro=:idLivro AND idPerfil=:idPerfil")
                .setString("idLivro", String.valueOf(idLivro))
                .setString("idPerfil", String.valueOf(idPerfil))
                .uniqueResult();
        
        sessao.refresh(historico);
        return historico.getDataConclusao() != null;
    }
}
