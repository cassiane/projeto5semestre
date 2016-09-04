/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.LoginInvalidoException;
<<<<<<< refs/remotes/origin/master
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
//import javax.validation.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
=======
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
>>>>>>> HEAD~1

/**
 *
 * @author marcelo.lima
 */
public class UsuarioDAO {
<<<<<<< refs/remotes/origin/master

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
=======
    private final Session sessao;
    
    public UsuarioDAO() {
        sessao = getSessionFactory().getCurrentSession();
    }
    
    /**
     * Verifica se existe usuário e senha informados
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso o usuário e/ou a senha sejam inválidos
>>>>>>> HEAD~1
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
<<<<<<< refs/remotes/origin/master

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
        List<Usuario> tmpAmigos = sessao.createQuery("FROM Usuario WHERE id<>:idUsuario ORDER BY nome")
                .setInteger("idUsuario", idUsuario)
                .list();
        System.out.println("usuario:: " + idUsuario);
        if (tmpAmigos.isEmpty()) {
            throw new UsuarioInvalidoException("Você não possui amigos ainda, faça amizades logo!");
        }
        return tmpAmigos;
    }
=======
>>>>>>> HEAD~1
}
