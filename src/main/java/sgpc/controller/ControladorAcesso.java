package sgpc.controller;

import sgpc.domain.Usuario;
import sgpc.mbeans.MbLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Controlador utilizado especificamente para verificação de permissões de 
 * acesso a funcionalidades oferecidas pela aplicação.
 *
 */
public class ControladorAcesso {
  
  private boolean permissaoAdministrador;
  private boolean permissaoFuncionario;
  private boolean permissaoComum;

  public boolean isPermissaoAdministrador() {    
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
    } else {
      permissaoAdministrador = false;
    }
    return permissaoAdministrador;
  }

  public boolean isPermissaoFuncionario() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
        permissaoFuncionario  = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
      }
    } else {
      permissaoFuncionario = false;
    }
    return permissaoFuncionario;
  }

  public boolean isPermissaoComum() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
      permissaoFuncionario  = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
      
      if (permissaoAdministrador || permissaoFuncionario) {
        permissaoComum = true;
      } else {
        permissaoComum  = (usuarioSessao.getTipo() == Usuario.CONVIDADO);
      }
    } else {
      permissaoComum = false;
    }
    return permissaoComum;
  }
  
  /**
   * Método utilizado para configurar o perfil de acesso do usuário logado às
   * funcionalidades da aplicação.
   */
  public void configurarAcesso() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      
      Logger.getLogger("ControladorAcesso").log(Level.INFO, 
              ">>>>>>>>>>>>>> Usuário da sessão tem tipo {0}", usuarioSessao.getTipo());
      
      permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
        permissaoFuncionario  = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
        permissaoComum  = (usuarioSessao.getTipo() == Usuario.CONVIDADO);
      }
    }
  }
}

