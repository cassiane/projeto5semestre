/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.LoginInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Query;
//import javax.validation.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;



/**
 *
 * @author marcelo.lima
 */
public class UsuarioDAO {


    private final Session sessao;

    public UsuarioDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }

    /**
     * Verifica se existe usuário e senha informados
     *
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso o usuário e/ou a senha sejam
     * inválidos
     */
    public Usuario efetuarLogin(String email, String senha) throws LoginInvalidoException {
        Usuario tmpUsuario = (Usuario) sessao.createQuery("FROM Usuario WHERE email=:email AND senha=:senha")
                .setString("email", email)
                .setString("senha", senha)
                .uniqueResult();
        if (tmpUsuario == null) {
            throw new LoginInvalidoException("Email ou senha incorretos!");
        }
        return tmpUsuario;
    }


    /**
     *
     * @param usuario
     * @throws UsuarioInvalidoException
     */
    public void salvarUsuario(Usuario usuario) throws UsuarioInvalidoException {
        try {
            sessao.saveOrUpdate(usuario);
        } catch (ConstraintViolationException e) {
            if (e.getSQLException().getMessage().contains("email")) {
                sessao.clear();
                throw new UsuarioInvalidoException("Email já está sendo utilizado realize o login ou clique em esqueceu a senha.");
            } else {

            }
        }
    }

    public List<Usuario> listarAmigos(int idUsuario) throws UsuarioInvalidoException {
        Query query = sessao.createSQLQuery("CALL witc.proc_amigo(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        //System.out.println("amigo:: " + idUsuario);
        if (resultado.isEmpty()) {
            throw new UsuarioInvalidoException("Você não possui amigos ainda, faça logo uma amizade!");
        }
        List<Usuario> tmpAmigos = (List<Usuario>)resultado;
        return tmpAmigos;
    }

    public List<Usuario> listarSugestao(int idUsuario) throws UsuarioInvalidoException{
        Query query = sessao.createSQLQuery("CALL witc.proc_sugestao(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        //System.out.println("sugestao:: " + idUsuario + " - " + tmpAmigos.size());
        if (resultado.isEmpty()) {
            throw new UsuarioInvalidoException("No momento não temos sugestão de amizade!");
        }
        List<Usuario> tmpAmigos = (List<Usuario>)resultado;
        return tmpAmigos;
    }

    public void solicitarAmizade(int id, int idSugestao) {
        sessao.createSQLQuery("INSERT INTO Usuario_tem_Amigo (idUsuario, idAmigo, dataSolicitacao) VALUES (:usuario, :amigo, CURRENT_DATE())").setInteger("usuario", id).setInteger("amigo", idSugestao).executeUpdate();
    }

    public List<Usuario> listarSolicitacao(int idUsuario) throws UsuarioInvalidoException {
        Query query = sessao.createSQLQuery("CALL witc.proc_solicitacao(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        if (resultado.isEmpty()) {
            throw new UsuarioInvalidoException("Você não possui solicitações!");
        }
        List<Usuario> tmpUsuarios = (List<Usuario>)resultado;
        return tmpUsuarios;
    }

    public void aceitarAmizade(int id, int idAceitar) {
        sessao.createSQLQuery("UPDATE Usuario_tem_Amigo "
                + "SET dataAceitacao = CURRENT_DATE(), amigoStatus = TRUE "
                + "WHERE idUsuario = :amigo AND idAmigo = :usuario")
                .setInteger("usuario", id)
                .setInteger("amigo", idAceitar)
                .executeUpdate();
    }

    public void removerAmizade(int id, int idAmizade) {
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idUsuario = :usuario AND idAmigo = :amigo")
                .setParameter("usuario", id)
                .setParameter("amigo", idAmizade)
                .executeUpdate();
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idUsuario = :amigo AND idAmigo = :usuario")
                .setParameter("usuario", id)
                .setParameter("amigo", idAmizade)
                .executeUpdate();
    }
}
