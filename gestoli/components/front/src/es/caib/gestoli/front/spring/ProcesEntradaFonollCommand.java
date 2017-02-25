/**
 * ProcesEntradaOlivaCommand.java
 */
package es.caib.gestoli.front.spring; 

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;



/**
 * Objecte que representa un formulari web d'entrada
 * de oliva.
 * 
 * @author cperez <cperez@at4.net>
 */
public class ProcesEntradaFonollCommand {

	private Date dataExecucio;
	private String hora;	
	private Long trazaId;
	private Long establimentId;
	private String codi;
	private Long municipiId;
	private String poligon;
	private String parcela;
	private String titular;
	private Double kgInici;
	public Date getDataExecucio() {
		return dataExecucio;
	}
	public void setDataExecucio(Date dataExecucio) {
		this.dataExecucio = dataExecucio;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Long getTrazaId() {
		return trazaId;
	}
	public void setTrazaId(Long trazaId) {
		this.trazaId = trazaId;
	}
	public Long getEstablimentId() {
		return establimentId;
	}
	public void setEstablimentId(Long establimentId) {
		this.establimentId = establimentId;
	}
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	public Long getMunicipiId() {
		return municipiId;
	}
	public void setMunicipiId(Long municipiId) {
		this.municipiId = municipiId;
	}
	public String getPoligon() {
		return poligon;
	}
	public void setPoligon(String poligon) {
		this.poligon = poligon;
	}
	public String getParcela() {
		return parcela;
	}
	public void setParcela(String parcela) {
		this.parcela = parcela;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public Double getKgInici() {
		return kgInici;
	}
	public void setKgInici(Double kgInici) {
		this.kgInici = kgInici;
	}
	
	
	
	
}
