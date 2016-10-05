package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;

/**
 * Classe de objetos que representa um servi�o de desativa��o de um usu�rio,
 * indicando que n�o h� mais sess�o ativa para ele no sistema a partir daquele
 * momento, o que o libera para abrir nova sess�o futuramente.
 *
 */
public class ServicoDesativarUsuario extends ServicoCadastro<Usuario> {

	public ServicoDesativarUsuario(Usuario usuario) {
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