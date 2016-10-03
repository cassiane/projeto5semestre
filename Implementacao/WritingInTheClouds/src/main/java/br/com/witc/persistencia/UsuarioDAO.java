/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.persistencia;

import br.com.witc.excessao.DadosUsuarioInvalidoException;
import br.com.witc.excessao.LoginInvalidoException;
import br.com.witc.excessao.UsuarioInvalidoException;
import br.com.witc.modelo.ConvidadoUsuario;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.List;
import org.hibernate.Query;
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
        }else{
            if(tmpUsuario.isAtivo() == false){
                throw new LoginInvalidoException("Usuário inativo!");
            }else{
                return tmpUsuario;
            }
        }
    }

    /**
     *
     * @param usuario
     * @throws UsuarioInvalidoException Caso usuário já exista no BD
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
    /**
     * Mérodo para excluir o usuário
     * @param usuario
     * @throws UsuarioInvalidoException 
     */
    public void ExcluirUsuario(Usuario usuario) throws UsuarioInvalidoException{
        try{
            sessao.saveOrUpdate(usuario);           
        }catch (ConstraintViolationException e) {
            throw new UsuarioInvalidoException("Não foi possivel excluir sua conta - "+e.getMessage());            
        }
    }

    /**
     * Consulta a procedure e busca uma lista de amigos
     * @param idUsuario Identificador do usuario
     * @return Lista de amigos
     */
    public List<Usuario> listarAmigos(int idUsuario) {
        Query query = sessao.createSQLQuery("CALL witc.proc_amigo(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        if (resultado.isEmpty()) {
            return null;
        }
        List<Usuario> tmpAmigos = (List<Usuario>)resultado;
        return tmpAmigos;
    }

    /**
     * Consulta a procedure para saber se tem sugestão de amizade
     * @param idUsuario Identificador do usuario
     * @return Lista de sugestão de amigos
     */
    public List<Usuario> listarSugestao(int idUsuario) {
        Query query = sessao.createSQLQuery("CALL witc.proc_sugestao(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        //System.out.println("sugestao:: " + idUsuario + " - " + tmpAmigos.size());
        if (resultado.isEmpty()) {
            //throw new UsuarioInvalidoException("No momento não temos sugestão de amizade!");
            return null;
        }
        List<Usuario> tmpAmigos = (List<Usuario>)resultado;
        return tmpAmigos;
    }

    /**
     * Registrar no sistema a intenção de amizade
     * @param id Identificador do usuario
     * @param idSugestao Identificador do amigo para solicitação
     */
    public void solicitarAmizade(int id, int idSugestao) {
        sessao.createSQLQuery("INSERT INTO Usuario_tem_Amigo (idUsuario, idAmigo, dataSolicitacao) VALUES (:usuario, :amigo, CURRENT_DATE())").setInteger("usuario", id).setInteger("amigo", idSugestao).executeUpdate();
    }

    /**
     * Consulta uma procedure para verificar possiveis solicitações de amizade
     * @param idUsuario Identificação do usuario
     * @return Lista de solicitação de amizade
     */
    public List<Usuario> listarSolicitacao(int idUsuario) {
        Query query = sessao.createSQLQuery("CALL witc.proc_solicitacao(:idusu)")
                .addEntity(Usuario.class)
                .setParameter("idusu", idUsuario);
        List resultado = query.list();
        if (resultado.isEmpty()) {
            return null;
        }
        List<Usuario> tmpUsuarios = (List<Usuario>)resultado;
        return tmpUsuarios;
    }

    /**
     * Atualizar o registro da solicitação de amizade
     * @param id Identificador do usuario
     * @param idAceitar Identificador do solicitante
     */
    public void aceitarAmizade(int id, int idAceitar) {
        sessao.createSQLQuery("UPDATE Usuario_tem_Amigo "
                + "SET dataAceitacao = CURRENT_DATE(), amigoStatus = TRUE "
                + "WHERE idUsuario = :amigo AND idAmigo = :usuario")
                .setInteger("usuario", id)
                .setInteger("amigo", idAceitar)
                .executeUpdate();
    }

    /**
     * Exclui o registro de solicitação ou amizade na tabela
     * @param id Identificador do usuario
     * @param idAmizade Identificador do amigo
     */
    public void removerAmizade(int id, int idAmizade) {
        // Executa a exclusão caso o usuario estaja como usuario
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idUsuario = :usuario AND idAmigo = :amigo")
                .setParameter("usuario", id)
                .setParameter("amigo", idAmizade)
                .executeUpdate();
        // Executa a exclusão caso o usuario esteja como amigo
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idUsuario = :amigo AND idAmigo = :usuario")
                .setParameter("usuario", id)
                .setParameter("amigo", idAmizade)
                .executeUpdate();
    }

    /**
     * Realiza a consulta na tabela usuario
     * @return Lista de usuarios do sistema
     */
    public List<Usuario> listarUsuarios() {
        return sessao.createQuery("FROM Usuario").list();
    }
    
    /**
     * Verifica a existência do usuário no BD
     * @param email O email do usuário pesquisado
     * @return Um objeto Usuario
     * @throws DadosUsuarioInvalidoException Caso o usuário não seja localizado na base de dados
     */
    public Usuario verificarExistenciaUsuario(String email) throws DadosUsuarioInvalidoException {
        Usuario usuario = (Usuario) sessao.createQuery("FROM Usuario WHERE email=:email")
                .setParameter("email", email)
                .uniqueResult();        
        
        if (usuario == null) {
            throw new DadosUsuarioInvalidoException("Usuário não encontrado na base de dados!");
        }
        return usuario;
    }

    public void registrarConvite(int id, String destinatario) {
        ConvidadoUsuario novoUsuario = new ConvidadoUsuario(id, destinatario);
        sessao.saveOrUpdate(novoUsuario);
    }

    public void verificarConvite(String email) {
        try {
            sessao.createSQLQuery("CALL witc.proc_convite(:idemail)")
                    .setString("idemail", email)
                    .executeUpdate();
        } catch (Exception ex) {
            // usuario não recebeu nenhuma solicitação
        }
    }
}
