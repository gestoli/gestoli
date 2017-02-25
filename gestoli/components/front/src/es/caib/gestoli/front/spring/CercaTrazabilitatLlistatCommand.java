/**
 * ConsultaOliElaboratLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;

/**
 * Objeto que representa el formulario de consultas
 * @author Carlos Perez (cperez@at4.net)
 */
public class CercaTrazabilitatLlistatCommand {
	private Long idEstabliment;
	private Long tipus;
	private String fromEstabliment;
	private Date data;
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	public Long getTipus() {
		return tipus;
	}
	public void setTipus(Long tipus) {
		this.tipus = tipus;
	}
	public String getFromEstabliment() {
		return fromEstabliment;
	}
	public void setFromEstabliment(String fromEstabliment) {
		this.fromEstabliment = fromEstabliment;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
