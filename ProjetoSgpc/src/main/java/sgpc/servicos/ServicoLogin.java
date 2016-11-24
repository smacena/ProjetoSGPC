package sgpc.servicos;

import sgpc.domain.UsuarioId;
import sgpc.servicos.dao.DaoFactory;

/**
 * Serviço para autenticação de usuário.
 * 
 */
public class ServicoLogin extends ServicoVerificador {
  
  private UsuarioId usuarioId;
  
  public ServicoLogin(UsuarioId usuarioId) {
    this.usuarioId = usuarioId;
  }
  @Override
  public boolean executar() {
	    return (DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
	            getUsuarioDao().consultarUsuario(true, usuarioId.getUsername(), 
	            usuarioId.getSenha()).size() > 0);	  
  }
}
