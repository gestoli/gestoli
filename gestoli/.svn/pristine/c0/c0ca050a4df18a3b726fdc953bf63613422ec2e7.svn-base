package es.caib.gestoli.logic.model;

// Generated 03-dic-2009 17:52:36 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class SortidaBotaGranel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long idZona;
	private Traza traza;
	private String accion;
	private String vendaMotiu;
	private Date vendaData;
	private String vendaDestinatari;
	private String vendaTipusDocument;
	private String vendaNumeroDocument;
	private String vendaObservacions;
	private Date canviZonaData;
	private String canviZonaObservacions;
	private Boolean valid;
	private Double vendaKilos;
	private Pais pais;
	private Provincia provincia;
	private Municipi municipi;
	private Bota bota;
	private Establiment establiment;

	public SortidaBotaGranel() {
	}

	public SortidaBotaGranel(Traza traza, String accioSortida, Boolean valid) {
		this.traza = traza;
		this.accion = accioSortida;
		this.valid = valid;
	}

	public SortidaBotaGranel(Long zona, Traza traza, String accioSortida,
			String vendaMotiu, Date vendaData, String vendaDestinatari,
			String vendaTipusDocument, String vendaNumeroDocument,
			String vendaObservacions, Date canviZonaData,
			String canviZonaObservacions, Boolean valid,
			Double vendaKilos, Pais pais, Provincia provincia, Municipi municipi, Bota bota) {
		this.idZona = zona;
		this.traza = traza;
		this.accion = accioSortida;
		this.vendaMotiu = vendaMotiu;
		this.vendaData = vendaData;
		this.vendaDestinatari = vendaDestinatari;
		this.vendaTipusDocument = vendaTipusDocument;
		this.vendaNumeroDocument = vendaNumeroDocument;
		this.vendaObservacions = vendaObservacions;
		this.canviZonaData = canviZonaData;
		this.canviZonaObservacions = canviZonaObservacions;
		this.valid = valid;
		this.vendaKilos = vendaKilos;
		this.pais = pais;
		this.provincia = provincia;
		this.municipi = municipi;
		this.bota = bota;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
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

	public Double getVendaKilos() {
		return vendaKilos;
	}

	public void setVendaKilos(Double vendaKilos) {
		this.vendaKilos = vendaKilos;
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

	public Bota getBota() {
		return bota;
	}

	public void setBota(Bota bota) {
		this.bota = bota;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	// end of extra code specified in the hbm.xml files

}
