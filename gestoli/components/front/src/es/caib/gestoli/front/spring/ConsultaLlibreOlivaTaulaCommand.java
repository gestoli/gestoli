package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 */
public class ConsultaLlibreOlivaTaulaCommand {
	private Date dataInici;
	private Date dataFi;
	private Long idCampanya;
	private Long idEstabliment;
	private String fromEstabliment;
	
	public String getFromEstabliment() {
		return fromEstabliment;
	}
	public void setFromEstabliment(String fromEstabliment) {
		this.fromEstabliment = fromEstabliment;
	}
	public Date getDataFi() {
		return dataFi;
	}
	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}
	public Date getDataInici() {
		return dataInici;
	}
	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}
	public Long getIdCampanya() {
		return idCampanya;
	}
	public void setIdCampanya(Long idCampanya) {
		this.idCampanya = idCampanya;
	}
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
}
