/**
 * ProcesSortidaOlivaTaulaCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.util.Date;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Lot;


/**
 * Objeto que representa el formulario de mantenimento
 * de salida de lotes.
 * 
 * 
 */
public class ProcesSortidaOlivaTaulaCommand { //extends SortidaLot {
	
	private Long idZona;
	private String accion;
	private Double litros;
	private Double kilos;
	private Diposit diposit;
	private Lot[] lot;
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
	private Integer[] vendaNumeroBotelles;
	private Date canviZonaData;
	private String canviZonaObservacions;
	private Boolean desqualificar;
	
	public ProcesSortidaOlivaTaulaCommand(){
		
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

	public Double getLitros() {
		return litros;
	}

	public void setLitros(Double litros) {
		this.litros = litros;
	}

	public Double getKilos() {
		return kilos;
	}

	public void setKilos(Double kilos) {
		this.kilos = kilos;
	}

	public Diposit getDiposit() {
		return diposit;
	}

	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
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

	public Lot[] getLot() {
		return lot;
	}

	public void setLot(Lot[] lot) {
		this.lot = lot;
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

	public Integer[] getVendaNumeroBotelles() {
		return vendaNumeroBotelles;
	}

	public void setVendaNumeroBotelles(Integer[] vendaNumeroBotelles) {
		this.vendaNumeroBotelles = vendaNumeroBotelles;
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
}
