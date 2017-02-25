package es.caib.gestoli.logic.model;

// Generated 30-nov-2009 17:09:50 by Hibernate Tools 3.2.0.b9

import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class EntradaDiposit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Establiment establiment;
	private Traza traza;
	private Diposit diposit;
	private CategoriaOli categoriaOli;
	private CategoriaOli categoriaOriginalPartida; // S'utilitza per si es canvia la categoria al fer un trasbals
	private PartidaOli partidaOli;
	private Elaboracio elaboracio;
	private Date data;
	private Double litres;
	private Double acidesa;
	private String observacions;
	private Boolean valid;
	private Olivicultor olivicultor;

	public EntradaDiposit() {
	}

	public EntradaDiposit(Establiment establiment, Traza traza,
			Diposit diposit, PartidaOli partidaOli, Date data,
			Double litres, Boolean valid) {
		this.establiment = establiment;
		this.traza = traza;
		this.diposit = diposit;
		this.categoriaOli = (partidaOli != null)?partidaOli.getCategoriaOli():null;
		this.partidaOli = partidaOli;
		this.data = data;
		this.litres = litres;
		this.valid = valid;
	}

	public EntradaDiposit(Establiment establiment, Traza traza,
			Diposit diposit, PartidaOli partidaOli, Elaboracio elaboracio,
			Date data, Double litres, Double acidesa, String observacions,
			Boolean valid) {
		this.establiment = establiment;
		this.traza = traza;
		this.diposit = diposit;
		this.categoriaOli = (partidaOli != null)?partidaOli.getCategoriaOli():null;
		this.partidaOli = partidaOli;
		this.elaboracio = elaboracio;
		this.data = data;
		this.litres = litres;
		this.acidesa = acidesa;
		this.observacions = observacions;
		this.valid = valid;
	}
	
	public EntradaDiposit(Establiment establiment, Traza traza,
			Diposit diposit, PartidaOli partidaOli, Elaboracio elaboracio,
			Date data, Double litres, Double acidesa, String observacions,
			Boolean valid, CategoriaOli categoriaOli) {
		this.establiment = establiment;
		this.traza = traza;
		this.diposit = diposit;
		this.categoriaOli = categoriaOli;
		this.partidaOli = partidaOli;
		this.elaboracio = elaboracio;
		this.data = data;
		this.litres = litres;
		this.acidesa = acidesa;
		this.observacions = observacions;
		this.valid = valid;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Establiment getEstabliment() {
		return this.establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Diposit getDiposit() {
		return this.diposit;
	}

	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}

	public CategoriaOli getCategoriaOli() {
		return this.categoriaOli;
	}

	public void setCategoriaOli(CategoriaOli categoriaOli) {
		this.categoriaOli = categoriaOli;
	}

	public CategoriaOli getCategoriaOriginalPartida() {
		return categoriaOriginalPartida;
	}

	public void setCategoriaOriginalPartida(CategoriaOli categoriaOriginalPartida) {
		this.categoriaOriginalPartida = categoriaOriginalPartida;
	}

	/**
	 * @return the partidaOli
	 */
	public PartidaOli getPartidaOli() {
		return partidaOli;
	}

	/**
	 * @param partidaOli the partidaOli to set
	 */
	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
		
//		if(partidaOli != null)
//			this.categoriaOli = partidaOli.getCategoriaOli();
//		else
//			this.categoriaOli = null;
		
	}

	public Elaboracio getElaboracio() {
		return this.elaboracio;
	}

	public void setElaboracio(Elaboracio elaboracio) {
		this.elaboracio = elaboracio;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getLitres() {
		return this.litres;
	}

	public void setLitres(Double litres) {
		this.litres = litres;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Olivicultor getOlivicultor() {
		return olivicultor;
	}

	public void setOlivicultor(Olivicultor olivicultor) {
		this.olivicultor = olivicultor;
	}

}
