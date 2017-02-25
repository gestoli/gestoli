/**
 * ConsultaGeneralCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Oriol Barnes (obarnes@at4.net)
 */
public class ConsultaGeneralCommand {
	private Date dataInici;
	private Date dataFi;
	private Long temporadaId;
	private String buscar;

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
	public Long getTemporadaId() {
		return temporadaId;
	}
	public void setTemporadaId(Long temporadaId) {
		this.temporadaId = temporadaId;
	}
	public String getBuscar() {
		return buscar;
	}
	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}
	

}
