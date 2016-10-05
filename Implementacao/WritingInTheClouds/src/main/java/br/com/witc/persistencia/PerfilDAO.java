/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.modelo.Perfil;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author 00026108
 */
public class PerfilDAO {

    Session sessao;

    public PerfilDAO() {
        this.sessao = getSessionFactory().getCurrentSession();
    }

    public Perfil carregarPerfil(Usuario usuario) {

        Perfil p = (Perfil) sessao.createQuery("FROM Perfil WHERE idUsuario=:idUsuario ").setInteger("idUsuario", usuario.getId())
                .uniqueResult();

        if (p == null) {
            return null; // lançar excessao
        }
        return p;
    }

    public void salvarPerfil(Perfil perfil) {
        try {
            sessao.save(perfil);
        } catch (ConstraintViolationException e) {
            sessao.clear();
        }
    }

    /**
     * Acessar a tabela e buscar a lista de amigos
     *
     * @return lista de amigos editores
     */
    public List<Perfil> carregarListaAmigoEditor(int idUsuario, int idLivro) {
        List<Perfil> tmpPerfil = null;
        try {
            Query query = sessao.createSQLQuery("CALL witc.proc_edicao(:idusu, :idliv)")
                    .addEntity(Perfil.class)
                    .setParameter("idusu", idUsuario)
                    .setParameter("idliv", idLivro);
            tmpPerfil = query.list();
        } catch (Exception ex) {

        }
        return tmpPerfil;
    }

}
