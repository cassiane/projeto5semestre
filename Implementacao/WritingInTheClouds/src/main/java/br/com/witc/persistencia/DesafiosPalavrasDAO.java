/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.DesafiosPalavras;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author cassiane.santos
 */
public class DesafiosPalavrasDAO {
    Session sessao;

    public DesafiosPalavrasDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
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
     * Método para retornar a lista das palavras do desafio em que o usuário
     * está escrevendo
     * @param idDesafio id do desafio do usuário
     * @return retornar lista de palavras
     * @throws Exception 
     */
    public List<String> listarPalavrasDoDesafio(int idDesafio) throws Exception {
        List<String> lstPalavras = new ArrayList<>();
        lstPalavras = sessao.createSQLQuery("select palavra from DesafiosPalavras where idDesafio = :idDesafio")
                .setInteger("idDesafio",idDesafio)
                .list();
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
