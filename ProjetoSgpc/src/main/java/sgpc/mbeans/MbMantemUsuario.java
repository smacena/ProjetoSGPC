package sgpc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sgpc.domain.TipoUsuario;
import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;
import sgpc.servicos.ServicoCarregarUsuario;
import sgpc.servicos.ServicoMantemUsuario;

import javax.faces.application.FacesMessage;



/**
 * Bean responsável por cadastrar um novo usuário, alterar, excluir e 
 * vizualizar usuários cadastrados.
 */
@ManagedBean
@SessionScoped
public class MbMantemUsuario implements Serializable {


	private static final long serialVersionUID = 1L;
	private String confirmaSenha;
	private Boolean modoEdicao;
	private Usuario usuario;
	private UsuarioId usuarioId;
	private TipoUsuario tipoUsuario;
	private List<Usuario> usuarios;  
	
	public MbMantemUsuario() {	
	}

	@PostConstruct
	public void inicializar() {
		usuario     = new Usuario();		
		usuarioId   = new UsuarioId();
		tipoUsuario = new TipoUsuario();
		
		modoEdicao = false;
	
		usuarios = new ArrayList<Usuario>();
		carregaUsuario();
	}

	public void cadastrar() {
		if (validarEmail(usuario.getEmail())) {
			if (modoEdicao) {
				usuario.setTipoUsuario(tipoUsuario);
				if (new ServicoMantemUsuario(usuario).alterar()) {
					carregaUsuario();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Alterado com sucesso."));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao alterar dados do usuário."));
				}
				modoEdicao = false;
				limpar();
			} else {
				if (validarCampos()) {
					usuario.setId(usuarioId);
					usuario.setTipoUsuario(tipoUsuario);
					if (new ServicoMantemUsuario(usuario).cadastrar()) {
						carregaUsuario();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Info", "Usuário cadastrado com sucesso!"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao cadastrar usuário."));
					}
					limpar();
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Info", "Todos os campos devem ser preenchidos e a senha deve ser confirmada exatamente."));
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Email inválido."));
		}
	}

	public void limpar() {
		usuario     = new Usuario();
		usuarioId   = new UsuarioId();
		tipoUsuario = new TipoUsuario();
		
		modoEdicao  = false;
	}

	public void editar() {
		modoEdicao = true;
	}

	public void excluir(Usuario usuario) {
		if (new ServicoMantemUsuario(usuario).excluir()) {
			carregaUsuario();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao deletar usuário."));
		}
	}

	public void carregaUsuario() {
		usuarios = new ServicoCarregarUsuario().carregarDadosUsuarios();
	}

	public boolean validarCampos() {
		if (usuario.getEmail().length() > 0 && usuarioId.getUsername().length() > 0
				&& usuarioId.getSenha().equals(confirmaSenha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validarEmail(String email)
    {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Boolean getModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(Boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioId usuarioId) {
		this.usuarioId = usuarioId;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


}
