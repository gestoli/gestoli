
package es.caib.gestoli.front.spring;

import java.util.Date;

public class InformeProduccioCommand {
	
	private Long idEstabliment;
	private Date dataInici;
	private Long idCampanya;
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
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
	
}
