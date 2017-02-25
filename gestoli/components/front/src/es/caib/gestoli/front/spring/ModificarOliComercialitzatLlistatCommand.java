/**
 * ModificarOliComercialitzatLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Carlos Perez (cperez@at4.net)
 */
public class ModificarOliComercialitzatLlistatCommand {
	private Date dataInici;
	private Date dataFi;
	private Long idEstabliment;
	private String fromEstabliment;
	private String document;
	private String motiuVenda;
	
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
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	public String getFromEstabliment() {
		return fromEstabliment;
	}
	public void setFromEstabliment(String fromEstabliment) {
		this.fromEstabliment = fromEstabliment;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getMotiuVenda() {
		return motiuVenda;
	}
	public void setMotiuVenda(String motiuVenda) {
		this.motiuVenda = motiuVenda;
	}
}
