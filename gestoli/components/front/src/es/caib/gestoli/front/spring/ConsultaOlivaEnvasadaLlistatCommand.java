/**
 * ConsultaOliElaboratLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Carlos Perez (cperez@at4.net)
 */
public class ConsultaOlivaEnvasadaLlistatCommand {
	private Date dataInici;
	private Date dataFi;
	private Long idEstabliment;
	private String fromEstabliment;

	
	public String getFromEstabliment() {
		return fromEstabliment;
	}
	public void setFromEstabliment(String fromEstabliment) {
		this.fromEstabliment = fromEstabliment;
	}
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
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
}
