package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;

/**
 * Classe de objetos de servi�o respons�vel por atualizar a informa��o de
 * (in)atividade de um usu�rio.
 *
 */
public class ServicoAtivarUsuario extends ServicoCadastro<Usuario> {

	public ServicoAtivarUsuario(Usuario usuario) {
		super(usuario);
	}

	@Override
	public boolean cadastrar() {
		return false;
	}

	@Override
	public boolean alterar() {
		return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao()
				.atualizarUsuario(this.entidade);
	}

	@Override
	public boolean excluir() {
		return false;
	}

}
