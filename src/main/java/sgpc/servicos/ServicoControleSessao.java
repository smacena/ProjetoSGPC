package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;
import java.util.List;

/**
 * Classe de objetos que representa um servi�o de verifica��o da atividade de 
 * um usu�rio no sistema, indicando se h� sess�o ativa para ele em um dado momento.
 * 
 */
public class ServicoControleSessao extends ServicoVerificador {

  private Usuario usuario;
  
  public ServicoControleSessao(Usuario usuario) {
    this.usuario = usuario;
  }
  
  @Override
  public boolean executar() {
    List<Usuario> usuarios = 
            DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
            getUsuarioDao().consultarUsuario(true, usuario.getUsername(), usuario.getSenha());
    
    return (usuarios.size() > 0 && usuarios.get(0).getStatus() == Usuario.ATIVO);
  }
}

