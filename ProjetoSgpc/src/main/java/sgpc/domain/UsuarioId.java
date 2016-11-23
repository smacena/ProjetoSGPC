package sgpc.domain;
// Generated 23/11/2016 16:31:09 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsuarioId generated by hbm2java
 */
@Embeddable
public class UsuarioId implements java.io.Serializable {

	private String username;
	private String senha;

	public UsuarioId() {
	}

	public UsuarioId(String username, String senha) {
		this.username = username;
		this.senha = senha;
	}

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "senha", nullable = false, length = 20)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsuarioId))
			return false;
		UsuarioId castOther = (UsuarioId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this.getUsername() != null
				&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				&& ((this.getSenha() == castOther.getSenha()) || (this.getSenha() != null
						&& castOther.getSenha() != null && this.getSenha().equals(castOther.getSenha())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getSenha() == null ? 0 : this.getSenha().hashCode());
		return result;
	}

}
