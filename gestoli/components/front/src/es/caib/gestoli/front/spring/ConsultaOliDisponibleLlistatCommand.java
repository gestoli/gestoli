/**
 * ConsultaOliDisponibleLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Carlos Perez (cperez@at4.net)
 */
public class ConsultaOliDisponibleLlistatCommand {
	
	private Long idEstabliment;
	private String mesura;
	private String categoriaOli;
	private String fromEstabliment;
	private Date data;

	
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	public String getMesura() {
		return mesura;
	}
	public void setMesura(String mesura) {
		this.mesura = mesura;
	}
	public String getCategoriaOli() {
		return categoriaOli;
	}
	public void setCategoriaOli(String categoriaOli) {
		this.categoriaOli = categoriaOli;
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
