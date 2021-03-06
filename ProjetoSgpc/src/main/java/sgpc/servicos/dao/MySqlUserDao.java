package sgpc.servicos.dao;

import sgpc.domain.TipoUsuario;
import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de objetos de acesso a dados relacionados a usu�rio, espec�fica para
 * bancos de dados MySQL.
 *
 */
public class MySqlUserDao implements UsuarioDao {

	public boolean salvarUsuario(Usuario usuario) {

		boolean adicionado = false;
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conn = fabrica.criarConexao();

		try {
			PreparedStatement ps = conn.prepareStatement(MySqlDaoFactory.lerPropriedade("SALVAR_USUARIO"));
			ps.setString(1, usuario.getId().getUsername());
			ps.setString(2, usuario.getId().getSenha());
			ps.setString(3, usuario.getEmail());		
			ps.setString(4, usuario.getTipoUsuario().getTipo());

			if (ps.executeUpdate() > 0) {
				adicionado = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
		}

		fabrica.fecharConexao(conn);
		return adicionado;
	}

  public boolean atualizarUsuario(Usuario usuario) {
    boolean atualizado = false;

    DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
    Connection conn = fabrica.criarConexao();

    try {
      PreparedStatement ps = conn.
              prepareStatement(MySqlDaoFactory.lerPropriedade("ATUALIZAR_STATUS_USUARIO"));
      ps.setByte(1, usuario.getStatus());
      ps.setString(2, usuario.getId().getUsername());

      if (ps.executeUpdate() > 0) {
        atualizado = true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    fabrica.fecharConexao(conn);
    return atualizado;
  }
  
  public boolean atualizarUsuarioSenha(Usuario usuario) {
	    boolean atualizado = false;

	    DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
	    Connection conn = fabrica.criarConexao();

	    try {
	      PreparedStatement ps = conn.
	              prepareStatement(MySqlDaoFactory.lerPropriedade("ATUALIZAR_STATUS_USUARIOSENHA"));
	      ps.setString(1, usuario.getId().getSenha()); /*senha*/
	      ps.setString(2, usuario.getId().getUsername());

	      if (ps.executeUpdate() > 0) {
	        atualizado = true;
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    fabrica.fecharConexao(conn);
	    return atualizado;
	  }  
  
  public boolean atualizarUsuarioCampo(Usuario usuario) {
	    boolean atualizado = false;
        
	    DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
	    Connection conn = fabrica.criarConexao();

	    try {
	      PreparedStatement ps = conn.
	              prepareStatement(MySqlDaoFactory.lerPropriedade("ATUALIZAR_STATUS_USUARIOCAMPOS"));
	      ps.setString(1, usuario.getEmail());
	      ps.setString(2, usuario.getTipoUsuario().getTipo());
	      ps.setString(3, usuario.getId().getUsername());
	      ps.setString(4, usuario.getId().getSenha());

	      if (ps.executeUpdate() > 0) {
	        atualizado = true;
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    fabrica.fecharConexao(conn);
	    return atualizado;
	  }    

  public boolean excluirUsuario(Usuario usuario) {
	    boolean deletado = false;

	    DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
	    Connection conn = fabrica.criarConexao();

	    try {
	      PreparedStatement ps = conn.
	              prepareStatement(MySqlDaoFactory.lerPropriedade("DELETAR_USUARIO"));
	      ps.setString(1, usuario.getId().getUsername());
	      ps.setString(2, usuario.getId().getSenha());

	      if (ps.executeUpdate() > 0) {
	    	  deletado = true;
	      }
	    } catch (SQLException ex) {
	      Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    fabrica.fecharConexao(conn);
	    return deletado;
  }

  public List<Usuario> consultarUsuario(boolean login, String... criterios) {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    if (login && criterios.length == 2) {
      DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
      Connection conn = fabrica.criarConexao();
      
	  TipoUsuario tipoUsuario = new TipoUsuario();  
	  UsuarioId id = new UsuarioId();	
	  
      try {

	        PreparedStatement ps = conn.prepareStatement(
	                MySqlDaoFactory.lerPropriedade(
	                		  "VERIFICAR_USUARIO_LOGIN"
	                		));
	        ps.setString(1, criterios[0]);
	        ps.setString(2, criterios[1]);	
	        ResultSet resultados = ps.executeQuery();
        
        if (resultados.next()) {
              
    	  id.setUsername(resultados.getString("username"));
    	  id.setSenha(resultados.getString("senha"));
    	  
    	  tipoUsuario.setTipo(resultados.getString("tipo"));
    	  
          usuarios.add(new Usuario(id,
                  tipoUsuario,
                  resultados.getString("email"),
                  resultados.getByte("status")));    	  
    	  
        }
      } catch (SQLException ex) {
        Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
      }
      fabrica.fecharConexao(conn);
    }
    return usuarios;
  }
  
	public List<Usuario> consultarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conn = fabrica.criarConexao();

	    
		try {

			PreparedStatement ps = conn.prepareStatement(MySqlDaoFactory.lerPropriedade(
					               "VERIFICAR_USUARIOS"));
			ResultSet resultados = ps.executeQuery();

			while (resultados.next()) { 
				 UsuarioId id = new UsuarioId();
				 id.setUsername(resultados.getString("username"));
		    	 id.setSenha(resultados.getString("senha"));
		    	 
				 TipoUsuario tipoUsuario = new TipoUsuario(); 		    	  
		    	 tipoUsuario.setTipo(resultados.getString("tipo"));
		    	  
		    	  
	             usuarios.add(new Usuario(id,
		                   tipoUsuario,
		                   resultados.getString("email"),
		                   resultados.getByte("status")));		

			}
		} catch (SQLException ex) {
			Logger.getLogger(MySqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		fabrica.fecharConexao(conn);

		return usuarios;
	}
  
}
