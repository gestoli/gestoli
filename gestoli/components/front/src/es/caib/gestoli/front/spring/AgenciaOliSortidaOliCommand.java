/**
 * ConsultaLlistatCommand.java
 */
package es.caib.gestoli.front.spring;

import java.util.Date;


/**
 * Objeto que representa el formulario de consultas
 * @author Oriol Barnes (obarnes@at4.net)
 */
public class AgenciaOliSortidaOliCommand {
	private Date dataInici;
	private Date dataFi;
	private Long idEstabliment;
	private String fromEstabliment;
//	private Collection categories;
//	private Long categoria;
//	private Integer consulta;
//	private Collection consultes;
//	private Long bodega;
	
	public String getFromEstabliment() {
		return fromEstabliment;
	}
	public void setFromEstabliment(String fromEstabliment) {
		this.fromEstabliment = fromEstabliment;
	}
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
	
//	public Long getCategoria() {
//		return categoria;
//	}
//	public void setCategoria(Long categoria) {
//		this.categoria = categoria;
//	}
//	public Collection getCategories() {
//		return categories;
//	}
//	public void setCategories(Collection categories) {
//		this.categories = categories;
//	}
//	public Integer getConsulta() {
//		return consulta;
//	}
//	public void setConsulta(Integer consulta) {
//		this.consulta = consulta;
//	}
//	public Collection getConsultes() {
//		return consultes;
//	}
//	public void setConsultes(Collection consultes) {
//		this.consultes = consultes;
//	}
//	public Long getBodega() {
//		return bodega;
//	}
//	public void setBodega(Long bodega) {
//		this.bodega = bodega;
//	}

}
