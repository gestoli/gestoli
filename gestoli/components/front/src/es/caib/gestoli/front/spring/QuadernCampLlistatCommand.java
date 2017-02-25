package es.caib.gestoli.front.spring;

import java.util.Date;

/**
 * Objeto que representa el formulario de consultas
 */
public class QuadernCampLlistatCommand {
	private Date dataInici;
	private Date dataFi;
	private Long idOlivicultor;
	
	
	public Date getDataInici() {
		return dataInici;
	}
	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}
	public Date getDataFi() {
		return dataFi;
	}
	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}
	public Long getIdOlivicultor() {
		return idOlivicultor;
	}
	public void setIdOlivicultor(Long idOlivicultor) {
		this.idOlivicultor = idOlivicultor;
	}
	
}
