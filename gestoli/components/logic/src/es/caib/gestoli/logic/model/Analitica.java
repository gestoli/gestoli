package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Analitica implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date data;
	private Double litresAnalitzats;
	private String usuari;
	private VarietatOli varietatOli;
	private PartidaOli partidaOli;
	private Traza traza;
	private Diposit diposit;
	private Establiment establiment;


	private String analisiSensorialNomLaboratori;
	private Date analisiSensorialDataRecepcio;
	private Date analisiSensorialDataTast;
	private String analisiSensorialObservacions;
	

	private String analisiFisicoQuimicNomLaboratori;
	private Date analisiFisicoQuimicDataRecepcio;
	private Date analisiFisicoQuimicDataInici;
	private Date analisiFisicoQuimicDataFi;
	private Boolean analisiFisicoQuimicValid;
	private String analisiFisicoQuimicObservacions;
	private Double analisiFisicoQuimicAcidesa;
	
	private VarietatOli varietatOli1;
	private VarietatOli varietatOli2;
	
	private Set analiticaValor = new HashSet(0);
	
	
	public Analitica() {
	}

	public Analitica(Date data, Double litresAnalitzats, String usuari,
			VarietatOli varietatOli, PartidaOli partidaOli, Traza traza,
			Diposit diposit, Establiment establiment,
			String analisiSensorialNomLaboratori,
			Date analisiSensorialDataRecepcio, Date analisiSensorialDataTast,
			String analisiSensorialObservacions,
			String analisiFisicoQuimicNomLaboratori,
			Date analisiFisicoQuimicDataRecepcio,
			Date analisiFisicoQuimicDataInici, Date analisiFisicoQuimicDataFi,
			Boolean analisiFisicoQuimicValid,
			String analisiFisicoQuimicObservacions,
			Double analisiFisicoQuimicAcidesa,
			VarietatOli varietatOli1,
			VarietatOli varietatOli2) {
		super();
		this.data = data;
		this.litresAnalitzats = litresAnalitzats;
		this.usuari = usuari;
		this.varietatOli = varietatOli;
		this.partidaOli = partidaOli;
		this.traza = traza;
		this.diposit = diposit;
		this.establiment = establiment;
		this.analisiSensorialNomLaboratori = analisiSensorialNomLaboratori;
		this.analisiSensorialDataRecepcio = analisiSensorialDataRecepcio;
		this.analisiSensorialDataTast = analisiSensorialDataTast;
		this.analisiSensorialObservacions = analisiSensorialObservacions;
		this.analisiFisicoQuimicNomLaboratori = analisiFisicoQuimicNomLaboratori;
		this.analisiFisicoQuimicDataRecepcio = analisiFisicoQuimicDataRecepcio;
		this.analisiFisicoQuimicDataInici = analisiFisicoQuimicDataInici;
		this.analisiFisicoQuimicDataFi = analisiFisicoQuimicDataFi;
		this.analisiFisicoQuimicValid = analisiFisicoQuimicValid;
		this.analisiFisicoQuimicObservacions = analisiFisicoQuimicObservacions;
		this.analisiFisicoQuimicAcidesa = analisiFisicoQuimicAcidesa;
		this.varietatOli1 = varietatOli1;
		this.varietatOli2 = varietatOli2;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Double getLitresAnalitzats() {
		return litresAnalitzats;
	}


	public void setLitresAnalitzats(Double litresAnalitzats) {
		this.litresAnalitzats = litresAnalitzats;
	}


	public String getUsuari() {
		return usuari;
	}


	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}


	public VarietatOli getVarietatOli() {
		return varietatOli;
	}


	public void setVarietatOli(VarietatOli varietatOli) {
		this.varietatOli = varietatOli;
	}


	public PartidaOli getPartidaOli() {
		return partidaOli;
	}


	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
	}


	public String getAnalisiSensorialNomLaboratori() {
		return analisiSensorialNomLaboratori;
	}


	public void setAnalisiSensorialNomLaboratori(
			String analisiSensorialNomLaboratori) {
		this.analisiSensorialNomLaboratori = analisiSensorialNomLaboratori;
	}


	public Date getAnalisiSensorialDataRecepcio() {
		return analisiSensorialDataRecepcio;
	}


	public void setAnalisiSensorialDataRecepcio(Date analisiSensorialDataRecepcio) {
		this.analisiSensorialDataRecepcio = analisiSensorialDataRecepcio;
	}


	public Date getAnalisiSensorialDataTast() {
		return analisiSensorialDataTast;
	}


	public void setAnalisiSensorialDataTast(Date analisiSensorialDataTast) {
		this.analisiSensorialDataTast = analisiSensorialDataTast;
	}


	public String getAnalisiSensorialObservacions() {
		return analisiSensorialObservacions;
	}


	public void setAnalisiSensorialObservacions(String analisiSensorialObservacions) {
		this.analisiSensorialObservacions = analisiSensorialObservacions;
	}


	public String getAnalisiFisicoQuimicNomLaboratori() {
		return analisiFisicoQuimicNomLaboratori;
	}


	public void setAnalisiFisicoQuimicNomLaboratori(
			String analisiFisicoQuimicNomLaboratori) {
		this.analisiFisicoQuimicNomLaboratori = analisiFisicoQuimicNomLaboratori;
	}


	public Date getAnalisiFisicoQuimicDataRecepcio() {
		return analisiFisicoQuimicDataRecepcio;
	}


	public void setAnalisiFisicoQuimicDataRecepcio(
			Date analisiFisicoQuimicDataRecepcio) {
		this.analisiFisicoQuimicDataRecepcio = analisiFisicoQuimicDataRecepcio;
	}


	public Date getAnalisiFisicoQuimicDataInici() {
		return analisiFisicoQuimicDataInici;
	}


	public void setAnalisiFisicoQuimicDataInici(Date analisiFisicoQuimicDataInici) {
		this.analisiFisicoQuimicDataInici = analisiFisicoQuimicDataInici;
	}


	public Date getAnalisiFisicoQuimicDataFi() {
		return analisiFisicoQuimicDataFi;
	}


	public void setAnalisiFisicoQuimicDataFi(Date analisiFisicoQuimicDataFi) {
		this.analisiFisicoQuimicDataFi = analisiFisicoQuimicDataFi;
	}


	public Boolean getAnalisiFisicoQuimicValid() {
		return analisiFisicoQuimicValid;
	}


	public void setAnalisiFisicoQuimicValid(Boolean analisiFisicoQuimicValid) {
		this.analisiFisicoQuimicValid = analisiFisicoQuimicValid;
	}


	public String getAnalisiFisicoQuimicObservacions() {
		return analisiFisicoQuimicObservacions;
	}


	public void setAnalisiFisicoQuimicObservacions(String analisiFisicoQuimicObservacions) {
		this.analisiFisicoQuimicObservacions = analisiFisicoQuimicObservacions;
	}

	public Double getAnalisiFisicoQuimicAcidesa() {
		return analisiFisicoQuimicAcidesa;
	}

	public void setAnalisiFisicoQuimicAcidesa(Double analisiFisicoQuimicAcidesa) {
		this.analisiFisicoQuimicAcidesa = analisiFisicoQuimicAcidesa;
	}

	public Set getAnaliticaValor() {
		return analiticaValor;
	}


	public void setAnaliticaValor(Set analiticaValor) {
		this.analiticaValor = analiticaValor;
	}


	public Traza getTraza() {
		return traza;
	}


	public void setTraza(Traza traza) {
		this.traza = traza;
	}


	public Diposit getDiposit() {
		return diposit;
	}

	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}



	public Establiment getEstabliment() {
		return establiment;
	}
	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public VarietatOli getVarietatOli1() {
		return varietatOli1;
	}

	public void setVarietatOli1(VarietatOli varietatOli1) {
		this.varietatOli1 = varietatOli1;
	}
	
	public VarietatOli getVarietatOli2() {
		return varietatOli2;
	}

	public void setVarietatOli2(VarietatOli varietatOli2) {
		this.varietatOli2 = varietatOli2;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	/**
	 * Comparator
	 */
	public int compareTo(Object a) {
		Analitica analitica = (Analitica) a;
		return this.data.compareTo(analitica.getData());
	}

}
