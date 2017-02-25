/**
 * ProcesSortidaOlivaTaulaCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.util.Date;

import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Establiment;


/**
 * Objeto que representa el formulario de mantenimento
 * de salida d'oliva crua a granel.
 * 
 * 
 */

public class ProcesSortidaOlivaTaulaBotaGranelCommand {
	
	private Long idZona;
	private String accion;
	private Double kilos;
	private Bota bota;
	private String tipusSortida;
	private Long paisId;
	private Long provinciaId;
	private Long municipiId;
	private Date vendaData;
	private String vendaDestinatari;
	private String vendaNumeroDocument;
	private String vendaTipusDocument;
	private String vendaObservacions;
	private String vendaMotiu;
	private Date canviZonaData;
	private String canviZonaObservacions;
	private Boolean desqualificar;
	private Long IdPartidaOliva;
	private Long idBota;
	private Establiment establiment;
	private Long idEstabliment;
	
	
	public ProcesSortidaOlivaTaulaBotaGranelCommand(){
		
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Double getKilos() {
		return kilos;
	}

	public void setKilos(Double kilos) {
		this.kilos = kilos;
	}

	public Bota getBota() {
		return bota;
	}

	public void setBota(Bota bota) {
		this.bota = bota;
	}

	public String getTipusSortida() {
		return tipusSortida;
	}

	public void setTipusSortida(String tipusSortida) {
		this.tipusSortida = tipusSortida;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

	public Long getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Long provinciaId) {
		this.provinciaId = provinciaId;
	}

	public Long getMunicipiId() {
		return municipiId;
	}

	public void setMunicipiId(Long municipiId) {
		this.municipiId = municipiId;
	}

	public Date getVendaData() {
		return vendaData;
	}

	public void setVendaData(Date vendaData) {
		this.vendaData = vendaData;
	}

	public String getVendaDestinatari() {
		return vendaDestinatari;
	}

	public void setVendaDestinatari(String vendaDestinatari) {
		this.vendaDestinatari = vendaDestinatari;
	}

	public String getVendaNumeroDocument() {
		return vendaNumeroDocument;
	}

	public void setVendaNumeroDocument(String vendaNumeroDocument) {
		this.vendaNumeroDocument = vendaNumeroDocument;
	}

	public String getVendaTipusDocument() {
		return vendaTipusDocument;
	}

	public void setVendaTipusDocument(String vendaTipusDocument) {
		this.vendaTipusDocument = vendaTipusDocument;
	}

	public String getVendaObservacions() {
		return vendaObservacions;
	}

	public void setVendaObservacions(String vendaObservacions) {
		this.vendaObservacions = vendaObservacions;
	}

	public String getVendaMotiu() {
		return vendaMotiu;
	}

	public void setVendaMotiu(String vendaMotiu) {
		this.vendaMotiu = vendaMotiu;
	}

	public Date getCanviZonaData() {
		return canviZonaData;
	}

	public void setCanviZonaData(Date canviZonaData) {
		this.canviZonaData = canviZonaData;
	}

	public String getCanviZonaObservacions() {
		return canviZonaObservacions;
	}

	public void setCanviZonaObservacions(String canviZonaObservacions) {
		this.canviZonaObservacions = canviZonaObservacions;
	}

	public Boolean getDesqualificar() {
		return desqualificar;
	}

	public void setDesqualificar(Boolean desqualificar) {
		this.desqualificar = desqualificar;
	}

	public Long getIdPartidaOliva() {
		return IdPartidaOliva;
	}

	public void setIdPartidaOliva(Long idPartidaOliva) {
		IdPartidaOliva = idPartidaOliva;
	}

	public Long getIdBota() {
		return idBota;
	}

	public void setIdBota(Long idBota) {
		this.idBota = idBota;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	
}
