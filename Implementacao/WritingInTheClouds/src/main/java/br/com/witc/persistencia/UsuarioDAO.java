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
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import static br.com.witc.persistencia.HibernateUtil.getSessionFactory;
import java.util.ArrayList;
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
     * Exclui todos os registros de solicitação ou amizade na tabela
     * @param id Identificador do usuario
     */
    public void removerTodasAmizades(int id) {
        // Executa a exclusão caso o usuario estaja como usuario
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idUsuario = :usuario")
                .setParameter("usuario", id)                
                .executeUpdate();
        // Executa a exclusão caso o usuario esteja como amigo
        sessao.createSQLQuery("DELETE FROM Usuario_tem_Amigo "
                + "WHERE idAmigo = :usuario")
                .setParameter("usuario", id)
                .executeUpdate();
    }
    
    /**
     * Salva vários registros na tabela de ligação entre usuário e tipo de texto
     * para identificar os tipos de texto em que o usuário se identifica
     * @param tiposTextoUsuario lista de tipos de texto que foram selecionados
     * @param idUsuario 
     
    public void salvarTipoTextoUsuario(List <TipoTexto> tiposTextoUsuario, int idUsuario){
        for(int i=0;i<tiposTextoUsuario.size();i++){
            int tip = tiposTextoUsuario.get(i).getId();
            sessao.createSQLQuery("INSERT INTO usuario_tem_tipotexto(idUsuario,idTipoTexto) "
                + "VALUES(:usuario,:tipoTexto);")
                .setInteger("usuario", idUsuario)
                .setInteger("tipoTexto", tip)
                .executeUpdate();
        }              
    }
    
    /**
     * Retorna vários registros na tabela de ligação entre usuário e tipo de texto
     * para identificar os tipos de texto em que o usuário se identifica
     * @param idUsuario 
     * @return retorna uma lista de string de tipos de texto do usuario
     
    public List<TipoTexto> listarTipoTextoUsuario(int idUsuario){
        List resultado = sessao.createSQLQuery("SELECT tipo.id,tipo.tipotexto FROM usuario_tem_tipotexto u " +
        "inner join tipotexto tipo on u.idtipotexto = tipo.id " +
        "where u.idusuario = :usuario")
                .setParameter("usuario", idUsuario)
                .list();
        if (resultado.isEmpty()) {
            return null;
        }
        List<TipoTexto> tmpTipos = (List<TipoTexto>)resultado;
        return tmpTipos;        
    }
    
    /**
     * Exclui um registro na tabela de ligação entre usuário e tipo de texto
     * para identificar os tipos de texto em que o usuário se identifica
     * @param idUsuario
     * @param idTipoTexto 

    public void excluirTipoTextoUsuario(int idUsuario, int idTipoTexto){
        sessao.createSQLQuery("DELETE FROM usuario_tem_tipotexto"
                + "WHERE idUsuario = :usuario and idTipoTexto = :tipoTexto;")
                .setParameter("usuario", idUsuario)
                .setParameter("tipoTexto", idTipoTexto)
                .executeUpdate();
    }
    
    /**
     * Exclui todos os registros do tipo de texto com que o usuario se identifica
     * quando ele exclui sua conta 
     * @param idUsuario 

    public void excluirTodosTipoTextoUsuario(int idUsuario){
        sessao.createSQLQuery("DELETE FROM usuario_tem_tipotexto"
            + "WHERE idUsuario = :usuario")
            .setInteger("usuario", idUsuario)        
            .executeUpdate();
    }
    **/
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
        sessao.save(novoUsuario);
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

    /**
     * Buscar o amigo passado por parametro
     * @param id Codigo do amigo
     * @return O amigo
     */
    public Usuario carregarAmigo(int id) {
        return (Usuario) sessao.load(Usuario.class, id);
    }

    /**
     * Persistir a atualização do status do usuario passado por parametro
     * @param usuario Codigo do usuario
     * @param status Codigo do status (Enum do banco)
     */
    public void atualizarStatus(int usuario, int status) {
        sessao.createSQLQuery("UPDATE witc.Usuario SET status = :st WHERE id = :us")
                .setInteger("st", status)
                .setInteger("us", usuario)
                .executeUpdate();
        sessao.flush();
    }

    /**
     * Busca o status do usuario passado por parametro
     * @param usuario Codigo do usuario
     * @return O status do usuario
     */
    public String carregarStatus(int usuario) {
        return (String) sessao.createSQLQuery("SELECT status FROM witc.Usuario WHERE id = :us")
                .setInteger("us", usuario)
                .uniqueResult();
    }
}
