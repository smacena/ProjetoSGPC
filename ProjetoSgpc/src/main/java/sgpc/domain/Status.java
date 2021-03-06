package sgpc.domain;
// Generated 23/11/2016 03:44:08 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Status generated by hbm2java
 */
@Entity
@Table(name = "status", catalog = "sgpc")
public class Status implements java.io.Serializable {

	private int idStatus;
	private String descricao;
	private Set cronogramas = new HashSet(0);
	private Set dadosconsolidadoses = new HashSet(0);

	public Status() {
	}

	public Status(int idStatus) {
		this.idStatus = idStatus;
	}

	public Status(int idStatus, String descricao, Set cronogramas, Set dadosconsolidadoses) {
		this.idStatus = idStatus;
		this.descricao = descricao;
		this.cronogramas = cronogramas;
		this.dadosconsolidadoses = dadosconsolidadoses;
	}

	@Id

	@Column(name = "idStatus", unique = true, nullable = false)
	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	@Column(name = "Descricao", length = 60)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set getCronogramas() {
		return this.cronogramas;
	}

	public void setCronogramas(Set cronogramas) {
		this.cronogramas = cronogramas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set getDadosconsolidadoses() {
		return this.dadosconsolidadoses;
	}

	public void setDadosconsolidadoses(Set dadosconsolidadoses) {
		this.dadosconsolidadoses = dadosconsolidadoses;
	}

}
