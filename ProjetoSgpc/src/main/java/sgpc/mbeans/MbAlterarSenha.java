package sgpc.mbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;

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

	public MbAlterarSenha() {
	}

	@PostConstruct
	public void inicializar() {
	    HttpSession sessao = (HttpSession) 
	            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	    usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
	    
		usuario   = new Usuario();
	}
		
  public void AlterarSenha(){
	  if (VerificarSenha()) {
		  /* usuario.setUsername(usuarioSessao.getUsername());
		   * usuario.setStatus(usuarioSessao.getStatus());*/
		  usuarioId = new UsuarioId();
		  
		  usuarioId.setUsername(usuarioSessao.getId().getUsername());
		  usuario.setStatus(usuarioSessao.getStatus());
		  if(new ServicoAlterarSenha(usuario).alterar()){
			    usuario = new Usuario();
		        FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(null, "Senha atualizada com sucesso!" ));
		  }else {
		        FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro ao alterar senha." )); 			
		}
	}else {
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Por favor, verifique os dados digitados." )); 	
	}	 
	    
  }
  
  public boolean VerificarSenha(){
	  if (usuarioSessao.getId().getSenha().equals(getSenhaAntiga()) && 
		  usuario.getId().getSenha().equals(getConfirmaSenha())	&& 
		  getConfirmaSenha().length() > 0 &&
		  getUsuario().getId().getSenha().length() > 0
		  ) {
		return true;
	} else {
		return false;
	}
  }
  
  /**
   * Limpa todos os dados da tela.
   */
  public void limparTela() {
    this.usuario = new Usuario();
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
	
}
