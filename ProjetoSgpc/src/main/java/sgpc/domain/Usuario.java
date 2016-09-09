package sgpc.domain;

import java.io.Serializable;

/**
 * Representação de um usuário do sistema em memória.
 *
 */
public class Usuario implements Serializable {
  
  public static final String TIPO_ADMINISTRADOR = "ADMINISTRADOR";
  public static final String TIPO_FUNCIONARIO = "FUNCIONARIO";
  public static final String TIPO_CONVIDADO = "CONVIDADO";
  
  public static final byte ADMINISTRADOR = 0;
  public static final byte FUNCIONARIO = 1;
  public static final byte CONVIDADO = 2;
  
  public static final byte INATIVO = 0;
  public static final byte ATIVO = 1;
  
  private String username = "";
  private String email = "";
  private String senha = "";
  private byte tipo;
  private byte status;
  

public Usuario() {}
  
  public Usuario (String username, String email, String senha, byte tipo) {
    this.username = username;
    this.email = email;
    this.senha = senha;
    this.tipo = tipo;
  }
  
  public Usuario (String username, String email, String senha, byte tipo, byte status) {
    this.username = username;
    this.email = email;
    this.senha = senha;
    this.tipo = tipo;
    this.status = status;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public byte getTipo() {
    return tipo;
  }
  
  public String getTipo_Adm() {
	    return TIPO_ADMINISTRADOR;
	  }
  
  public String getTipo_Func() {
	    return TIPO_FUNCIONARIO;
	  }
  
  public String getTipo_Con() {
	    return TIPO_CONVIDADO;
	  }

  public byte getTipo_AdmB() {
	    return ADMINISTRADOR;
	  }

public byte getTipo_FuncB() {
	    return FUNCIONARIO;
	  }

public byte getTipo_ConB() {
	    return CONVIDADO;
	  }  
  
  public void setTipo(byte tipo) {
    this.tipo = tipo;
  }

  public byte getStatus() {
    return status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }
  
}
