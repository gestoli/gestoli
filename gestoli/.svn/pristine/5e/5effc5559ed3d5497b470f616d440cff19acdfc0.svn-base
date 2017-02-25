package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class EntradaLot implements java.io.Serializable {

	private Long id;
	private Zona zona;
	private Traza traza;
	private Lot lot;
	private Date data;
	private Double acidesa;
	private String dipositProcedencia;
	private String numerosContraetiquetes;
	private Boolean valid;
	private String observacions;
	private Boolean esDevolucio;
	private Integer botelles;
	
	public EntradaLot() {
	}

	public EntradaLot(Zona zona, Traza traza, Lot lot, Date data,
			Double acidesa, Integer botelles, Boolean valid) {
		this.zona = zona;
		this.traza = traza;
		this.lot = lot;
		this.data = data;
		this.acidesa = acidesa;
		this.valid = valid;
		this.botelles = botelles;
	}

	public EntradaLot(Zona zona, Traza traza, Lot lot, Date data,
			Double acidesa, String dipositProcedencia,
			String numerosContraetiquetes, Integer botelles, Boolean valid) {
		this.zona = zona;
		this.traza = traza;
		this.lot = lot;
		this.data = data;
		this.acidesa = acidesa;
		this.dipositProcedencia = dipositProcedencia;
		this.numerosContraetiquetes = numerosContraetiquetes;
		this.valid = valid;
		this.botelles = botelles;
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

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public String getDipositProcedencia() {
		return this.dipositProcedencia;
	}

	public void setDipositProcedencia(String dipositProcedencia) {
		this.dipositProcedencia = dipositProcedencia;
	}

	public String getNumerosContraetiquetes() {
		return this.numerosContraetiquetes;
	}

	public void setNumerosContraetiquetes(String numerosContraetiquetes) {
		this.numerosContraetiquetes = numerosContraetiquetes;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Boolean getEsDevolucio() {
		return esDevolucio;
	}

	public void setEsDevolucio(Boolean esDevolucio) {
		this.esDevolucio = esDevolucio;
	}

	public Integer getBotelles() {
		return botelles;
	}

	public void setBotelles(Integer botelles) {
		this.botelles = botelles;
	}
	
	public Double getLitres(){
		if (this.botelles != null)
			return this.botelles * this.lot.getEtiquetatge().getTipusEnvas().getVolum();
		return 0.0;
	}
}
