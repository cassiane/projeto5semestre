/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.HistoricoLivro;
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
}
