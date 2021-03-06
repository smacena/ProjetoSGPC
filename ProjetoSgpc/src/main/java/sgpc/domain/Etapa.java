package sgpc.domain;
// Generated 23/11/2016 03:44:08 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Etapa generated by hbm2java
 */
@Entity
@Table(name = "etapa", catalog = "sgpc")
public class Etapa implements java.io.Serializable {

	private Integer idEtapa;
	private String descricao;
	private Set cronogramas = new HashSet(0);

	public Etapa() {
	}

	public Etapa(String descricao, Set cronogramas) {
		this.descricao = descricao;
		this.cronogramas = cronogramas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEtapa", unique = true, nullable = false)
	public Integer getIdEtapa() {
		return this.idEtapa;
	}

	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	@Column(name = "Descricao", length = 60)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etapa")
	public Set getCronogramas() {
		return this.cronogramas;
	}

	public void setCronogramas(Set cronogramas) {
		this.cronogramas = cronogramas;
	}

}
