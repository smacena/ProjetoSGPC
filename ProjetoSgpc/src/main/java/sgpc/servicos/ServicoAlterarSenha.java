package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;

/**
 * Classe de objetos de servi�o respons�vel por atualizar a senha de um usu�rio.
 *
 */
public class ServicoAlterarSenha extends ServicoCadastro<Usuario> {

	public ServicoAlterarSenha(Usuario usuario) {
		super(usuario);
	}

	@Override
	public boolean cadastrar() {
		return false;
	}

	@Override
	public boolean alterar() {
		return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao()
				.atualizarUsuarioSenha(this.entidade);
	}

	@Override
	public boolean excluir() {
		return false;
	}

}
