package sgpc.servicos;

import sgpc.domain.Usuario;
import sgpc.servicos.dao.DaoFactory;

public class ServicoMantemUsuario extends ServicoCadastro<Usuario>{
	
	public ServicoMantemUsuario(Usuario usuario) {
		super(usuario);
	}

	@Override
	public boolean cadastrar() {
		return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao()
				.salvarUsuario(this.entidade);
	}

	@Override
	public boolean alterar() {
		return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao()
				.atualizarUsuarioCampo(this.entidade);
	}

	@Override
	public boolean excluir() {
		return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao()
				.excluirUsuario(this.entidade);
	}
}
