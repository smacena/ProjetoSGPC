package sgpc.servicos;

import sgpc.domain.UsuarioId;
import sgpc.servicos.dao.DaoFactory;

/**
 * Servi�o para autentica��o de usu�rio.
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
