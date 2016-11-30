/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.LinkRecuperacaoInvalidoException;
import br.com.witc.modelo.RecuperarConta;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author marcelo.lima
 */
public class RecuperarContaDAO {
    private final Session sessao;

    public RecuperarContaDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        this.sessao = sessionFactory.openSession();
    }

    /**
     * Persiste os dados da redefinição no BD
     * @param recuperar O objeto RecuperarConta a ser persistido
     */
    public void salvar(RecuperarConta recuperar) {
        inutilizaLink(recuperar);
        
        sessao.saveOrUpdate(recuperar);
    }
    
    /**
     * Verifica se o link acessado é válido
     * @param recuperar O objeto RecuperarConta contendo o usuário e o hash
     * @return Um objeto RecuperarConta
     * @throws LinkRecuperacaoInvalidoException Caso o link seja inválido 
     */
    public RecuperarConta verificarLink(RecuperarConta recuperar) throws LinkRecuperacaoInvalidoException {
        RecuperarConta tmpRecuperar = 
                (RecuperarConta) sessao.createQuery("FROM RecuperarConta WHERE "
                        + "idUsuario=:idUsuario AND "
                        + "hashRecuperacaoSenha=:hash AND "
                        + "dataUtilizacao IS NULL AND "
                        + "inutilizado = 'false'")
                .setInteger("idUsuario", recuperar.getUsuario().getId())
                .setString("hash", recuperar.getHashRecuperacaoSenha())
                .uniqueResult();
        
        if (tmpRecuperar == null) {
            throw new LinkRecuperacaoInvalidoException("Link de recuperação inválido!");
        }                        
        
        return tmpRecuperar;
    }
    
    /**
     * Verifica se o usuário tem links ainda não utilizados. Caso afirmativo, inutiliza-os
     * @param recuperar O objeto RecuperarConta
     */
    public void inutilizaLink(RecuperarConta recuperar) {
        List<RecuperarConta> lstRecuperar = 
                sessao.createQuery("FROM RecuperarConta WHERE "
                        + "idUsuario=:idUsuario AND "
                        + "hashRecuperacaoSenha!=:hash AND "
                        + "dataUtilizacao IS NULL AND "
                        + "inutilizado = 'false'")
                .setInteger("idUsuario", recuperar.getUsuario().getId())
                .setString("hash", recuperar.getHashRecuperacaoSenha())
                .list();
                
        // Inutiliza links nao utilizados
        for (RecuperarConta rec : lstRecuperar) {
            rec.setInutilizado(true);
            sessao.saveOrUpdate(rec);
        }
    }
}
