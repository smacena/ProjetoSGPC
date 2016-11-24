package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;
import sgpc.servicos.dao.DaoFactory;
import java.util.List;

/**
 * Classe de objetos que representa um serviço de verificação da atividade de 
 * um usuário no sistema, indicando se há sessão ativa para ele em um dado momento.
 * 
 */
public class ServicoControleSessao extends ServicoVerificador {

  private UsuarioId usuarioId;
  
  public ServicoControleSessao(UsuarioId usuarioId) {
    this.usuarioId = usuarioId;
  }
  
  @Override
  public boolean executar() {
	List<Usuario> usuarios = 
	            DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
	            getUsuarioDao().consultarUsuario(true, usuarioId.getUsername(), usuarioId.getSenha());    
    return (usuarios.size() > 0 && usuarios.get(0).getStatus() == Usuario.ATIVO);
  }
}

