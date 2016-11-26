package sgpc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;
import sgpc.servicos.ServicoAlterarSenha;



/**
 * Bean responsável por alterar a senha de um usuário previamente cadastrado.
 *
 */
@ManagedBean
@SessionScoped
public class MbAlterarSenha implements Serializable {

	private Usuario usuario;
	private UsuarioId usuarioId;
	private Usuario usuarioSessao;
	private String senhaAntiga;
	private String confirmaSenha;
	List<Usuario> senhaAnt;

	public MbAlterarSenha() {
	}

	@PostConstruct
	public void inicializar() {
	    HttpSession sessao = (HttpSession) 
	            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	    usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
	    senhaAnt = new ArrayList<Usuario>();
	    
		usuario   = new Usuario();
		usuarioId = new UsuarioId();
	}
		
  public void AlterarSenha(){
	  if (VerificarSenha()) {
		  usuarioId.setUsername(usuarioSessao.getId().getUsername());
		  usuario.setId(usuarioId);
		  usuario.setStatus(usuarioSessao.getStatus());
		  
		  if(new ServicoAlterarSenha(usuario).alterar()){
		        FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(null, "Senha atualizada com sucesso." ));
		        limparTela();
		  }else {
		        FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro ao alterar senha." )); 			
		}
	}else {
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Por favor, verifique os dados digitados." )); 	
	}	 
	    
  }
  
	public boolean VerificarSenha() {
		try {
			senhaAnt = new ServicoAlterarSenha(usuario).consultarUsuarioSenha(usuarioSessao.getId().getUsername(),
					senhaAntiga);
			if (senhaAnt.get(0).getId().getSenha().equals(senhaAntiga) && usuarioId.getSenha().equals(confirmaSenha)
					&& confirmaSenha.length() > 0 && usuarioId.getSenha().length() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
  
  /**
   * Limpa todos os dados da tela.
   */
  public void limparTela() {
    usuario = new Usuario();
    usuarioId    = new UsuarioId();
	senhaAntiga  = "";
	confirmaSenha= "";
  }
  
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioId usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
