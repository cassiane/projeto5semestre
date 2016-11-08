/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.DesafiosPalavras;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author cassiane.santos
 */
public class DesafiosPalavrasDAO {
    Session sessao;

    public DesafiosPalavrasDAO() {
        this.sessao=getSessionFactory().getCurrentSession();
    }
    
    /**
     * Método para retornar a lista de todas as palavras cadastradas no sistema
     * @return retornar lista de palavras
     * @throws Exception 
     */
    public List<String> listarDesafiosPalavras() throws Exception {
        String consulta = "select palavras from DesafiosPalavras";
        List<String> lstPalavras;
        lstPalavras = sessao.createSQLQuery(consulta).list();
        if (lstPalavras.isEmpty()) {
            throw new Exception("Nenhuma palavra foi cadastrada no sistema");
        }
        return lstPalavras;
    }
    
    /**
     * Salvar o desafio e a lista de palavras
     * @param listaPalavras
     * @param idDesafio 
     */
    public void salvarDesafio(List<String> listaPalavras, int idDesafio) {
        listaPalavras.forEach((palavra) -> {            
            sessao.createSQLQuery("insert into desafiospalavras(palavra, idDesafio) values (:palavra,:idDesafio)")
                    .setString("palavra", palavra)
                    .setInteger("idDesafio",idDesafio)
                    .executeUpdate();
        });
    }
}
