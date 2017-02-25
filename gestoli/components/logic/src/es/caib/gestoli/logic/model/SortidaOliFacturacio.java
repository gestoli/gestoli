package es.caib.gestoli.logic.model;

import java.util.Date;


public class SortidaOliFacturacio implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long idImportacio;
	private String tipusSortida; // Lot-Diposit
	private String nomArxiu;
	private Date dataInsercio;

	private String pais;
	private String provincia;
	private String municipi;
	
	// Campos Lot
	private String lot;
	private String producte;
	private String zona;
	private String accioSortida; // v -> venda
	private String vendaData;
	private String vendaNumeroBotelles;
	private String vendaMotiu;
	private String vendaObservacions;
	private String vendaDestinatari;
	private String vendaNumeroDocument;
	private String vendaTipusDocument;
	
	private Boolean estat;
	private String error;
	private Long idSortida;
	
	public SortidaOliFacturacio() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdImportacio() {
		return idImportacio;
	}

	public void setIdImportacio(Long idImportacio) {
		this.idImportacio = idImportacio;
	}

	public String getNomArxiu() {
		return nomArxiu;
	}

	public void setNomArxiu(String nomArxiu) {
		this.nomArxiu = nomArxiu;
	}

	public Date getDataInsercio() {
		return dataInsercio;
	}

	public void setDataInsercio(Date dataInsercio) {
		this.dataInsercio = dataInsercio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipi() {
		return municipi;
	}

	public void setMunicipi(String municipi) {
		this.municipi = municipi;
	}

	public String getTipusSortida() {
		return tipusSortida;
	}

	public void setTipusSortida(String tipusSortida) {
		this.tipusSortida = tipusSortida;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getProducte() {
		return producte;
	}

	public void setProducte(String producte) {
		this.producte = producte;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getAccioSortida() {
		return accioSortida;
	}

	public void setAccioSortida(String accioSortida) {
		this.accioSortida = accioSortida;
	}

	public String getVendaData() {
		return vendaData;
	}

	public void setVendaData(String vendaData) {
		this.vendaData = vendaData;
	}

	public String getVendaNumeroBotelles() {
		return vendaNumeroBotelles;
	}

	public void setVendaNumeroBotelles(String vendaNumeroBotelles) {
		this.vendaNumeroBotelles = vendaNumeroBotelles;
	}

	public String getVendaMotiu() {
		return vendaMotiu;
	}

	public void setVendaMotiu(String vendaMotiu) {
		this.vendaMotiu = vendaMotiu;
	}

	public String getVendaObservacions() {
		return vendaObservacions;
	}

	public void setVendaObservacions(String vendaObservacions) {
		this.vendaObservacions = vendaObservacions;
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

	public Long getIdSortida() {
		return idSortida;
	}

	public void setIdSortida(Long idSortida) {
		this.idSortida = idSortida;
	}

	public Boolean getEstat() {
		return estat;
	}

	public void setEstat(Boolean estat) {
		this.estat = estat;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
