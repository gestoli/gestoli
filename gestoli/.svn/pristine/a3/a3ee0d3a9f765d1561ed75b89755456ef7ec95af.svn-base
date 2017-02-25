package es.caib.gestoli.logic.model;

// Generated 03-dic-2009 17:52:36 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class SortidaLot implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Zona zona;
	private Lot lot;
	private Traza traza;
	private String accioSortida;
	private String vendaMotiu;
	private Date vendaData;
	private String vendaDestinatari;
	private String vendaTipusDocument;
	private String vendaNumeroDocument;
	private String vendaObservacions;
	private Date canviZonaData;
	private String canviZonaObservacions;
	private Boolean valid;
	private Integer vendaNumeroBotelles;
	private String observacions;
	private Pais pais;
	private Provincia provincia;
	private Municipi municipi;
	private Integer botellesDevolucio;
	private Boolean olivaDO;

	public SortidaLot() {
	}

	public SortidaLot(Lot lot, Traza traza, String accioSortida, Boolean valid) {
		this.lot = lot;
		this.traza = traza;
		this.accioSortida = accioSortida;
		this.valid = valid;
	}

	public SortidaLot(Zona zona, Lot lot, Traza traza, String accioSortida,
			String vendaMotiu, Date vendaData, String vendaDestinatari,
			String vendaTipusDocument, String vendaNumeroDocument,
			String vendaObservacions, Date canviZonaData,
			String canviZonaObservacions, Boolean valid,
			Integer vendaNumeroBotelles,
			Pais pais, Provincia provincia, Municipi municipi, Integer botellesDevolucio) {
		this.zona = zona;
		this.lot = lot;
		this.traza = traza;
		this.accioSortida = accioSortida;
		this.vendaMotiu = vendaMotiu;
		this.vendaData = vendaData;
		this.vendaDestinatari = vendaDestinatari;
		this.vendaTipusDocument = vendaTipusDocument;
		this.vendaNumeroDocument = vendaNumeroDocument;
		this.vendaObservacions = vendaObservacions;
		this.canviZonaData = canviZonaData;
		this.canviZonaObservacions = canviZonaObservacions;
		this.valid = valid;
		this.vendaNumeroBotelles = vendaNumeroBotelles;
		this.pais = pais;
		this.provincia = provincia;
		this.municipi = municipi;
		this.botellesDevolucio = botellesDevolucio;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public String getAccioSortida() {
		return this.accioSortida;
	}

	public void setAccioSortida(String accioSortida) {
		this.accioSortida = accioSortida;
	}

	public String getVendaMotiu() {
		return this.vendaMotiu;
	}

	public void setVendaMotiu(String vendaMotiu) {
		this.vendaMotiu = vendaMotiu;
	}

	public Date getVendaData() {
		return this.vendaData;
	}

	public void setVendaData(Date vendaData) {
		this.vendaData = vendaData;
	}

	public String getVendaDestinatari() {
		return this.vendaDestinatari;
	}

	public void setVendaDestinatari(String vendaDestinatari) {
		this.vendaDestinatari = vendaDestinatari;
	}

	public String getVendaTipusDocument() {
		return this.vendaTipusDocument;
	}

	public void setVendaTipusDocument(String vendaTipusDocument) {
		this.vendaTipusDocument = vendaTipusDocument;
	}

	public String getVendaNumeroDocument() {
		return this.vendaNumeroDocument;
	}

	public void setVendaNumeroDocument(String vendaNumeroDocument) {
		this.vendaNumeroDocument = vendaNumeroDocument;
	}

	public String getVendaObservacions() {
		return this.vendaObservacions;
	}

	public void setVendaObservacions(String vendaObservacions) {
		this.vendaObservacions = vendaObservacions;
	}

	public Date getCanviZonaData() {
		return this.canviZonaData;
	}

	public void setCanviZonaData(Date canviZonaData) {
		this.canviZonaData = canviZonaData;
	}

	public String getCanviZonaObservacions() {
		return this.canviZonaObservacions;
	}

	public void setCanviZonaObservacions(String canviZonaObservacions) {
		this.canviZonaObservacions = canviZonaObservacions;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Integer getVendaNumeroBotelles() {
		return this.vendaNumeroBotelles;
	}

	public void setVendaNumeroBotelles(Integer vendaNumeroBotelles) {
		this.vendaNumeroBotelles = vendaNumeroBotelles;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve una fecha de venta formateada
	 */
	public String getVendaDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = "";
		if (this.vendaData != null) {
			fechaFormateada = sdf.format(this.vendaData);
		}
		return fechaFormateada;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Municipi getMunicipi() {
		return municipi;
	}

	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
	}

	public Integer getBotellesDevolucio() {
		return botellesDevolucio;
	}

	public void setBotellesDevolucio(Integer botellesDevolucio) {
		this.botellesDevolucio = botellesDevolucio;
	}
	
	public Boolean getOlivaDO() {
		return olivaDO;
	}

	public void setOlivaDO(Boolean olivaDO) {
		this.olivaDO = olivaDO;
	}

	public String getDestinacio_es(){
		String resultat = "";
		
		if(this.pais != null && this.pais.getIso() != null && !this.pais.getIso().equals(Pais.ESPANYA_ISO)){
			resultat += this.pais.getNomcas();
		} else {
			if(this.getProvincia() != null){
				resultat += this.getProvincia().getNom();
			}
			
			if(this.getMunicipi() != null){
				if(this.getProvincia() != null){
					resultat += ", ";
				}
				resultat += this.getMunicipi().getNom();
			}
		}
		
		return resultat;
	}
	
	public String getDestinacio_ca(){
		String resultat = "";
		
		if(this.pais != null && this.pais.getIso() != null && !this.pais.getIso().equals(Pais.ESPANYA_ISO)){
			resultat += this.pais.getNomcat();
		} else {
			if(this.getProvincia() != null){
				resultat += this.getProvincia().getNom();
			}
			
			if(this.getMunicipi() != null){
				if(this.getProvincia() != null){
					resultat += ", ";
				}
				resultat += this.getMunicipi().getNom();
			}
		}
		
		return resultat;
	}
	
	public Double getVendaLitres(){
		if (this.vendaNumeroBotelles != null)
			return this.vendaNumeroBotelles * this.lot.getEtiquetatge().getTipusEnvas().getVolum();
		return 0.0;
	}
	
	public Double getVendaLitresAmbDevolucio(){
		if (this.vendaNumeroBotelles != null)
			return (this.getVendaBotelles()) * this.lot.getEtiquetatge().getTipusEnvas().getVolum();
		return 0.0;
	}
	
	public Integer getVendaBotelles(){
		if (this.botellesDevolucio != null)
			return this.vendaNumeroBotelles - this.botellesDevolucio;
		return this.vendaNumeroBotelles;
	}

	// end of extra code specified in the hbm.xml files

}
