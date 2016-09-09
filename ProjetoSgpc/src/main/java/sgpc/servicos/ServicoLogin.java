package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;

/**
 * Serviço para autenticação de usuário.
 * 
 */
public class ServicoLogin extends ServicoVerificador {
  
  private Usuario usuario;
  
  public ServicoLogin(Usuario usuario) {
    this.usuario = usuario;
  }
  @Override
  public boolean executar() {
    return (DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
            getUsuarioDao().consultarUsuario(true, usuario.getUsername(), 
            usuario.getSenha()).size() > 0);
  }
}
