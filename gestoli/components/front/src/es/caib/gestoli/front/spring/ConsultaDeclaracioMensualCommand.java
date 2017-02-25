/**
 * ConsultaLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Oriol Barnes (obarnes@at4.net)
 */
public class ConsultaDeclaracioMensualCommand {
	private Date dataInici;
	private Date dataFi;

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



}
