package sgpc.domain;
// Generated 23/11/2016 03:44:08 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Cronograma generated by hbm2java
 */
@Entity
@Table(name = "cronograma", catalog = "sgpc")
public class Cronograma implements java.io.Serializable {

	private int idCronograma;
	private Dadosconsolidados dadosconsolidados;
	private Etapa etapa;
	private Status status;
	private Tmp tmp;
	private Date dtIni;
	private Date dtFim;
	private Integer qtdDiasFim;
	private Date dtFinalizado;

	public Cronograma() {
	}

	public Cronograma(int idCronograma, Dadosconsolidados dadosconsolidados, Etapa etapa, Status status, Tmp tmp) {
		this.idCronograma = idCronograma;
		this.dadosconsolidados = dadosconsolidados;
		this.etapa = etapa;
		this.status = status;
		this.tmp = tmp;
	}

	public Cronograma(int idCronograma, Dadosconsolidados dadosconsolidados, Etapa etapa, Status status, Tmp tmp,
			Date dtIni, Date dtFim, Integer qtdDiasFim, Date dtFinalizado) {
		this.idCronograma = idCronograma;
		this.dadosconsolidados = dadosconsolidados;
		this.etapa = etapa;
		this.status = status;
		this.tmp = tmp;
		this.dtIni = dtIni;
		this.dtFim = dtFim;
		this.qtdDiasFim = qtdDiasFim;
		this.dtFinalizado = dtFinalizado;
	}

	@Id

	@Column(name = "idCronograma", unique = true, nullable = false)
	public int getIdCronograma() {
		return this.idCronograma;
	}

	public void setIdCronograma(int idCronograma) {
		this.idCronograma = idCronograma;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numProcesso", nullable = false)
	public Dadosconsolidados getDadosconsolidados() {
		return this.dadosconsolidados;
	}

	public void setDadosconsolidados(Dadosconsolidados dadosconsolidados) {
		this.dadosconsolidados = dadosconsolidados;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEtapa", nullable = false)
	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idStatus", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTMP", nullable = false)
	public Tmp getTmp() {
		return this.tmp;
	}

	public void setTmp(Tmp tmp) {
		this.tmp = tmp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DtIni", length = 10)
	public Date getDtIni() {
		return this.dtIni;
	}

	public void setDtIni(Date dtIni) {
		this.dtIni = dtIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DtFim", length = 10)
	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	@Column(name = "QtdDiasFim")
	public Integer getQtdDiasFim() {
		return this.qtdDiasFim;
	}

	public void setQtdDiasFim(Integer qtdDiasFim) {
		this.qtdDiasFim = qtdDiasFim;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DtFinalizado", length = 10)
	public Date getDtFinalizado() {
		return this.dtFinalizado;
	}

	public void setDtFinalizado(Date dtFinalizado) {
		this.dtFinalizado = dtFinalizado;
	}

}
