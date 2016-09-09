package sgpc.servicos.dao;

import sgpc.domain.Usuario;
import java.util.List;

/**
 * Padr�o que define como um objeto de acesso a usu�rios deve se comportar.
 *
 */
public interface UsuarioDao {
  
  static final String CAMPO_USERNAME = "username";
  static final String CAMPO_EMAIL = "email";
  static final String CAMPO_SENHA = "senha";
  
  boolean salvarUsuario(Usuario usuario);
  boolean atualizarUsuario(Usuario usuario);
  boolean atualizarUsuarioSenha(Usuario usuario);
  boolean atualizarUsuarioCampo(Usuario usuario);
  boolean excluirUsuario(Usuario usuario);
  List<Usuario> consultarUsuario(boolean login, String ... criterios);
  List<Usuario> consultarUsuarios();  
}

