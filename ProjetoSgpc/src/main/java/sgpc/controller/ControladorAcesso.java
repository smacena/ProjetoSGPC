package sgpc.controller;

import sgpc.domain.Usuario;
import sgpc.mbeans.MbLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Controlador utilizado especificamente para verifica��o de permiss�es de 
 * acesso a funcionalidades oferecidas pela aplica��o.
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
    	permissaoAdministrador  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_ADMINISTRADOR));
      /*permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);*/
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
    	permissaoAdministrador  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_ADMINISTRADOR));
      /*permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);*/
    	      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
    	  permissaoFuncionario  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_FUNCIONARIO));
        /*permissaoFuncionario  = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);*/
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
        permissaoAdministrador  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_ADMINISTRADOR));
        permissaoFuncionario    = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_FUNCIONARIO));
 
 /*   permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
      permissaoFuncionario    = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);*/
      
      if (permissaoAdministrador || permissaoFuncionario) {
        permissaoComum = true;
      } else {
    	  permissaoComum  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_CONVIDADO));
        /*permissaoComum  = (usuarioSessao.getTipo() == Usuario.CONVIDADO);*/
      }
    } else {
      permissaoComum = false;
    }
    return permissaoComum;
  }
  
  /**
   * M�todo utilizado para configurar o perfil de acesso do usu�rio logado �s
   * funcionalidades da aplica��o.
   */
  public void configurarAcesso() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
    
    if (usuarioSessao != null) {
      
/*      Logger.getLogger("ControladorAcesso").log(Level.INFO, 
              ">>>>>>>>>>>>>> Usu�rio da sess�o tem tipo {0}", usuarioSessao.getTipo());
        permissaoAdministrador  = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);      */
    	
      Logger.getLogger("ControladorAcesso").log(Level.INFO, 
                ">>>>>>>>>>>>>> Usu�rio da sess�o tem tipo {0}", usuarioSessao.getTipoUsuario().getTipo());      
      permissaoAdministrador  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_ADMINISTRADOR));
      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
          permissaoFuncionario    = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_FUNCIONARIO));  
          permissaoComum  = (usuarioSessao.getTipoUsuario().getTipo().equals(Usuario.TIPO_CONVIDADO));
          
/*      permissaoFuncionario  = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
        permissaoComum  = (usuarioSessao.getTipo() == Usuario.CONVIDADO);*/
      }
    }
  }
}

