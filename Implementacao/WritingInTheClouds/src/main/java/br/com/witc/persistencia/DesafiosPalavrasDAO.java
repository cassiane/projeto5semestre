/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

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
     * 
     * @param listaPalavras
     * @param idDesafio
     * @param numeroPalavras 
     * @param idUsuario 
     * @param idDesafiante 
     */
    public void salvarDesafiosPalavras(List<String> listaPalavras, int idDesafio, int numeroPalavras, int idUsuario, int idDesafiante) {
        for(int i=0;i<listaPalavras.size();i++){
            String palavra = listaPalavras.get(i);
            sessao.createSQLQuery("INSERT INTO DesafiosPalavras(palavra,idDesafio) "
                + "VALUES(:palavra,:idDesafio);")
                .setString("palavra", palavra)
                .setInteger("idDesafio", idDesafio)
                .executeUpdate();
        }
        if(numeroPalavras > 0){
            sessao.createSQLQuery("INSERT INTO DesafiosUsuarios(idUsuario,idUsuarioDesafiante,idDesafio,numeroPalavras) "
                + "VALUES(:idUsuario,:idDesafiante,:idDesafio,:numeroPalavras);")
                .setInteger("idUsuario", idUsuario)
                .setInteger("idDesafiante", idDesafiante)
                .setInteger("idDesafio", idDesafio)
                .setInteger("numeroPalavras",numeroPalavras)
                .executeUpdate();
        }else{
            sessao.createSQLQuery("INSERT INTO DesafiosUsuarios(idUsuario,idUsuarioDesafiante,idDesafio,numeroPalavras) "
                + "VALUES(:idUsuario,:idDesafiante,:idDesafio);")
                .setInteger("idUsuario", idUsuario)
                .setInteger("idDesafiante", idDesafiante)
                .setInteger("idDesafio", idDesafio)
                .executeUpdate();
        }
        
    }
}
