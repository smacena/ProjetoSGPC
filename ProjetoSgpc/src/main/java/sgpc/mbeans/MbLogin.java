package sgpc.mbeans;

import sgpc.controller.ControladorAcesso;
import sgpc.domain.Usuario;
import sgpc.domain.UsuarioId;
import sgpc.servicos.ServicoAtivarUsuario;
import sgpc.servicos.ServicoCarregarUsuario;
import sgpc.servicos.ServicoControleSessao;
import sgpc.servicos.ServicoDesativarUsuario;
import sgpc.servicos.ServicoLogin;
import com.sun.istack.internal.logging.Logger;
import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Bean responsável por controlar operações de login, logout e controle de 
 * sessões de usuários.
 *
 */
@ManagedBean
@SessionScoped
public class MbLogin implements Serializable {

  private static final long serialVersionUID = 1L;
	
  private static final String LOGIN_SUCESSO = "login_sucesso";
  public static final String LOGIN_FALHA = "login_falha";
  public static final String SESSAO_INEXISTENTE = "sessao_invalida";
  private static final String OUTCOME_LOGOUT = "logout";
  public static final String USUARIO_SESSAO = "usuario";
  
  private Usuario usuario;
  private UsuarioId usuarioId;
  private ControladorAcesso controladorAcesso;
  private Usuario usuarioSessaoTipo;

  public MbLogin() {}
  
  @PostConstruct
  public void inicializar() {
    usuario = new Usuario();
    usuarioId = new UsuarioId();
    controladorAcesso = new ControladorAcesso();
    Logger.getLogger(MbLogin.class).log(Level.INFO, 
            ">>>>>>>>>>>>> Inicializando um bean de login.");
  }
  
  /**
   * Utilizado para tentativas de login no sistema, confrontando dados fornecidos
   * pelo usuário com registros de usuários cadastrados.
   * 
   * @return Outcome associado a fracasso ou sucesso na tentativa de login.
   */
	public String doLogin() {

		if (camposPreenchidos() && !isUsuarioLogado()) {
			if (new ServicoLogin(usuarioId).executar()) {

				// Descobrindo o tipo de usuário que está tentando acessar o
				// sistema.
				Usuario usuarioLogado = new ServicoCarregarUsuario()
						.carregarDados(usuarioId.getUsername(), usuarioId.getSenha()).get(0);
				usuarioLogado.setStatus(Usuario.ATIVO);

				HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				sessao.setAttribute(USUARIO_SESSAO, usuarioLogado);
				controladorAcesso.configurarAcesso();
				// pegar o tipo de usuário para apresentar na tela
				usuarioSessaoTipo = (Usuario) sessao.getAttribute(USUARIO_SESSAO);
				// Atualizando sistema de informações para informar que o
				// usuário está logado.
				new ServicoAtivarUsuario(usuarioLogado).alterar();
				return LOGIN_SUCESSO;
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Usuário não localizado, por favor verifique a senha e o usuário."));						
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Todos os campos devem ser preenchidos."));		
		}
		return LOGIN_FALHA;
	}

  /**
   * Utilizado para finalizar uma sessão de um usuário no sistema.
   * 
   * @return Outcome associado a fracasso ou sucesso na tentativa de logout.
   */
  public String doLogout() {
    FacesContext context = FacesContext.getCurrentInstance();
    
    HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      usuarioSessao.setStatus(Usuario.INATIVO);
      new ServicoDesativarUsuario(usuarioSessao).alterar();
    }
    
    context.getExternalContext().invalidateSession();
    return OUTCOME_LOGOUT;
  }

  /**
   * Utilizado para verificar se as credenciais necessárias para realização do 
   * login foram preenchidas.
   * 
   * @return  <code>true</code> em caso de dados preenchidos.
   *          <code>false</code> caso contrário.
   */
  private boolean camposPreenchidos() {
	    return (usuarioId != null && usuarioId.getUsername() != null
	            && !"".equals(usuarioId.getUsername()) && usuarioId.getSenha() != null
	            && !"".equals(usuarioId.getSenha()));	  
/*    return (usuario != null && usuario.getId().getUsername() != null
            && !"".equals(usuario.getId().getUsername()) && usuario.getId().getSenha() != null
            && !"".equals(usuario.getId().getSenha()));*/
  }
  
  /**
   * Método utilizado para verificar se um usuário tentando logar na aplicação
   * já não possui alguma sessão aberta em outro navegador ou outra aba. A 
   * aplicação está barrando múltiplos acessos simultâneos de um usuário.
   * 
   * @return <code>true</code> se já existir uma sessão ativa para o usuário.
   *         <code>false</code> caso contrário.
   */
  private boolean isUsuarioLogado() {
    return new ServicoControleSessao(usuarioId).executar();
  }
  
  /**
   * Limpa todos os dados da tela de login.
   */
  public void limparTela() {
    usuario = new Usuario();
    usuarioId = new UsuarioId();
    controladorAcesso = new ControladorAcesso();
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public ControladorAcesso getControladorAcesso() {
    return controladorAcesso;
  }
  
	public String tipoSessao() {

		/*
		 * if (usuarioSessaoTipo.getTipo() == usuario.getTipo_AdmB()) { return
		 * usuario.TIPO_ADMINISTRADOR; }else if (usuarioSessaoTipo.getTipo() ==
		 * usuario.getTipo_FuncB()) { return usuario.TIPO_FUNCIONARIO; }else if
		 * (usuarioSessaoTipo.getTipo() == usuario.getTipo_ConB()) { return
		 * usuario.TIPO_CONVIDADO; }else { return ""; }
		 */
		
/*		if (usuarioSessaoTipo.getTipoUsuario().getTipo().equals(usuario.getTipo_Adm())) {
			return usuario.getTipo_Adm();
		} else if (usuarioSessaoTipo.getTipoUsuario().getTipo().equals(usuario.getTipo_Func())) {
			return usuario.getTipo_Func();
		} else if (usuarioSessaoTipo.getTipoUsuario().getTipo().equals(usuario.getTipo_Con())) {
			return usuario.getTipo_Con();
		} else {
			return "";
		}*/
		
		return usuario.getTipo_Adm();
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioId usuarioId) {
		this.usuarioId = usuarioId;
	}

	
  
}
