/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.LoginInvalidoException;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import org.hibernate.Session;

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
     * @param email O email do usuário
     * @param senha A senha do usuário
     * @return Um objeto Usuario
     * @throws LoginInvalidoException Caso o usuário e/ou a senha sejam inválidos
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
}
