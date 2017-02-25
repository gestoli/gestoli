package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class PartidaFonoll implements Comparable {

	private Long id;

	private Establiment establiment;
	
	private Traza traza;

	private ElaboracioOliva elaboracioOlivaTaula;

	private Date data;

	private String hora;

	private Integer numeroEntrada;
	
	private String codi;

	private String titular;
	
	private Double kgInici;
	
	private Double kgRestants;
	
	private Municipi municipi;
	
	private String poligon;
	
	private String parcela;

	private Boolean valid;
	
	private String numeroDocument;


	public PartidaFonoll() {
	}

	public PartidaFonoll(Establiment establiment, Traza traza, Date data, String hora, 
			Integer numeroEntrada, String titular, Double kgInici, Double kgRestants, Municipi municipi,
			String poligon, String parcela, Boolean valid) {
		this.establiment = establiment;
		this.traza = traza;
		this.data = data;
		this.hora = hora;
		this.numeroEntrada = numeroEntrada;
		this.titular = titular;
		this.kgInici = kgInici;
		this.kgRestants = kgRestants;
		this.municipi = municipi;
		this.poligon = poligon;
		this.parcela = parcela;
		this.valid = valid;
	}

	public PartidaFonoll(Establiment establiment, Traza traza, ElaboracioOliva elaboracioOliva, Date data, String hora,
			Integer numeroEntrada, String titular, Double kgInici, Double kgRestants, Municipi municipi,
			String poligon, String parcela, Boolean valid) {
		this.establiment = establiment;
		this.traza = traza;
		this.elaboracioOlivaTaula = elaboracioOliva;
		this.data = data;
		this.hora = hora;
		this.numeroEntrada = numeroEntrada;
		this.titular = titular;
		this.kgInici = kgInici;
		this.kgRestants = kgRestants;
		this.municipi = municipi;
		this.poligon = poligon;
		this.parcela = parcela;
		this.valid = valid;
	}
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Double getKgInici() {
		return kgInici;
	}

	public void setKgInici(Double kgInici) {
		this.kgInici = kgInici;
	}

	public Double getKgRestants() {
		return kgRestants;
	}

	public void setKgRestants(Double kgRestants) {
		this.kgRestants = kgRestants;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}


	public ElaboracioOliva getElaboracioOlivaTaula() {
		return elaboracioOlivaTaula;
	}

	public void setElaboracioOlivaTaula(ElaboracioOliva elaboracioOlivaTaula) {
		this.elaboracioOlivaTaula = elaboracioOlivaTaula;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getNumeroEntrada() {
		return this.numeroEntrada;
	}

	public void setNumeroEntrada(Integer numeroEntrada) {
		this.numeroEntrada = numeroEntrada;
	}

	
	
	public String getCodi() {
		return codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Municipi getMunicipi() {
		return municipi;
	}

	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
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

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}



	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	/**
	 * Devuelve un nombre de partida
	 */
	public String getCodiAssignat() {
		return ("P(" + this.getDataFormat() + " " + hora + " " + " NUM: " + numeroEntrada + ")");
	}

	/**
	 * Devuelve un descripcion de partida
	 */
	public String getDescPartida() {
		return "(" + this.getDataFormat() + " " + hora + " " + " NUM: " + numeroEntrada + ")";
	}


	/**
	 * Comparator
	 */
	public int compareTo(Object po) {
		PartidaFonoll partidaOliva = (PartidaFonoll) po;
		if (this.data.compareTo(partidaOliva.getData()) == 0) {
			return this.numeroEntrada
					.compareTo(partidaOliva.getNumeroEntrada());
		} else {
			return this.data.compareTo(partidaOliva.getData());
		}
	}
	
	public String getNumeroDocument() {
		String[] lletres = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"};
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		numeroDocument = dateFormat.format(this.getData()) + lletres[this.getNumeroEntrada()-1];
		return numeroDocument;
	}

	// end of extra code specified in the hbm.xml files

}
