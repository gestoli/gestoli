/**
 * EstablimentCommand.java
 *
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de mantenimento
 * de establecimientos.
 * 
 * @author Carlos Pérez (cperez@at4.net)
 */
public class EdicioProcessosCommand {
	private static final long serialVersionUID = 3660614058015788633L;
	private Long trazaId;
	private Date dataMax;
	private Date dataMin;
	private Date dataNova;
	private Date dataExecucio;
	private int tipus;
	
	public Long getTrazaId() {
		return trazaId;
	}
	public void setTrazaId(Long trazaId) {
		this.trazaId = trazaId;
	}
	public Date getDataMax() {
		return dataMax;
	}
	public void setDataMax(Date dataMax) {
		this.dataMax = dataMax;
	}
	public Date getDataMin() {
		return dataMin;
	}
	public void setDataMin(Date dataMin) {
		this.dataMin = dataMin;
	}
	public Date getDataNova() {
		return dataNova;
	}
	public void setDataNova(Date dataNova) {
		this.dataNova = dataNova;
	}
	public Date getDataExecucio() {
		return dataExecucio;
	}
	public void setDataExecucio(Date dataExecucio) {
		this.dataExecucio = dataExecucio;
	}
	public int getTipus() {
		return tipus;
	}
	public void setTipus(int tipus) {
		this.tipus = tipus;
	}
}
