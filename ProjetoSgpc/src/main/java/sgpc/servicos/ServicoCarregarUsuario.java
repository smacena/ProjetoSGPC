package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;
import sgpc.servicos.dao.DaoFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de objetos responsáveis por carregar informações de usuários
 * cadastrados no sistema de informações da aplicação.
 *
 */
public class ServicoCarregarUsuario extends ServicoCarga<Usuario> {
  
@Override
  public List<Usuario> carregarDados(String... criterios) {
    
    List<Usuario> usuarios = new ArrayList<Usuario>();
    
    if (criterios != null && criterios.length == 2) {
      Usuario usuario = new Usuario();
      UsuarioId id    = new UsuarioId(criterios[0], criterios[1]);
      
      usuario.setId(id);
      usuarios = DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
              getUsuarioDao().consultarUsuario(true,usuario.getId().getUsername(), 
              usuario.getId().getSenha());
      
/*    usuario.setUsername(criterios[0]);
      usuario.setSenha(criterios[1]);
      
      usuarios = DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
              getUsuarioDao().consultarUsuario(true,usuario.getUsername(), 
              usuario.getSenha());*/
    }
    return usuarios;
  }

@Override
public List<Usuario> carregarDadosUsuarios() {
    List<Usuario> usuarios = new ArrayList<Usuario>();
    usuarios = DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
            getUsuarioDao().consultarUsuarios();
    return usuarios;
}
}