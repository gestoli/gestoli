package es.caib.gestoli.logic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.VarietatOli;

public class AnaliticaAux implements java.io.Serializable {

	private Long id;

	private Traza traza;

	private VarietatOli varietatOli;

	private Establiment establiment;

	private Diposit diposit;

	private Date data;

	private String analisiSensorialNomLaboratori;

	private Date analisiSensorialDataRecepcio;

	private Date analisiSensorialDataTast;

	private Boolean analisiSensorialVarietatOliValid;

	private String analisiSensorialObservacions;

	private Double analisiFisicoQuimicAcidesa;

	private Boolean analisiFisicoQuimicAcidesaValid;

	private Double analisiFisicoQuimicIp;

	private Boolean analisiFisicoQuimicIpValid;

	private Double analisiFisicoQuimicK270;

	private Boolean analisiFisicoQuimicK270Valid;

	private Double analisiFisicoQuimicK232;

	private Boolean analisiFisicoQuimicK232Valid;

	private Double analisiFisicoQuimicHumitat;

	private Boolean analisiFisicoQuimicHumitatValid;

	private Boolean valid;

	private String observacions;

	private String analisiFisicoQuimicNomLaboratori;

	private Date analisiFisicoQuimicDataRecepcio;

	private Date analisiFisicoQuimicDataInici;

	private Date analisiFisicoQuimicDataFi;

	private Boolean desqualificatDiposit;
	
	private Integer idVarietatOli;
	

	public AnaliticaAux() {
	}

	public AnaliticaAux(Traza traza, VarietatOli varietatOli,
			Establiment establiment, Diposit diposit, Date data,
			String analisiSensorialNomLaboratori,
			Date analisiSensorialDataRecepcio, Date analisiSensorialDataTast,
			Boolean analisiSensorialVarietatOliValid,
			Double analisiFisicoQuimicAcidesa,
			Boolean analisiFisicoQuimicAcidesaValid,
			Double analisiFisicoQuimicIp, Boolean analisiFisicoQuimicIpValid,
			Double analisiFisicoQuimicK270,
			Boolean analisiFisicoQuimicK270Valid,
			Double analisiFisicoQuimicK232,
			Boolean analisiFisicoQuimicK232Valid,
			Double analisiFisicoQuimicHumitat,
			Boolean analisiFisicoQuimicHumitatValid, Boolean valid,
			String analisiFisicoQuimicNomLaboratori,
			Date analisiFisicoQuimicDataRecepcio,
			Date analisiFisicoQuimicDataInici, Date analisiFisicoQuimicDataFi,
			Boolean desqualificatDiposit) {
		this.traza = traza;
		this.varietatOli = varietatOli;
		this.establiment = establiment;
		this.diposit = diposit;
		this.data = data;
		this.analisiSensorialNomLaboratori = analisiSensorialNomLaboratori;
		this.analisiSensorialDataRecepcio = analisiSensorialDataRecepcio;
		this.analisiSensorialDataTast = analisiSensorialDataTast;
		this.analisiSensorialVarietatOliValid = analisiSensorialVarietatOliValid;
		this.analisiFisicoQuimicAcidesa = analisiFisicoQuimicAcidesa;
		this.analisiFisicoQuimicAcidesaValid = analisiFisicoQuimicAcidesaValid;
		this.analisiFisicoQuimicIp = analisiFisicoQuimicIp;
		this.analisiFisicoQuimicIpValid = analisiFisicoQuimicIpValid;
		this.analisiFisicoQuimicK270 = analisiFisicoQuimicK270;
		this.analisiFisicoQuimicK270Valid = analisiFisicoQuimicK270Valid;
		this.analisiFisicoQuimicK232 = analisiFisicoQuimicK232;
		this.analisiFisicoQuimicK232Valid = analisiFisicoQuimicK232Valid;
		this.analisiFisicoQuimicHumitat = analisiFisicoQuimicHumitat;
		this.analisiFisicoQuimicHumitatValid = analisiFisicoQuimicHumitatValid;
		this.valid = valid;
		this.analisiFisicoQuimicNomLaboratori = analisiFisicoQuimicNomLaboratori;
		this.analisiFisicoQuimicDataRecepcio = analisiFisicoQuimicDataRecepcio;
		this.analisiFisicoQuimicDataInici = analisiFisicoQuimicDataInici;
		this.analisiFisicoQuimicDataFi = analisiFisicoQuimicDataFi;
		this.desqualificatDiposit = desqualificatDiposit;
	}

	public AnaliticaAux(Traza traza, VarietatOli varietatOli,
			Establiment establiment, Diposit diposit, Date data,
			String analisiSensorialNomLaboratori,
			Date analisiSensorialDataRecepcio, Date analisiSensorialDataTast,
			Boolean analisiSensorialVarietatOliValid,
			String analisiSensorialObservacions,
			Double analisiFisicoQuimicAcidesa,
			Boolean analisiFisicoQuimicAcidesaValid,
			Double analisiFisicoQuimicIp, Boolean analisiFisicoQuimicIpValid,
			Double analisiFisicoQuimicK270,
			Boolean analisiFisicoQuimicK270Valid,
			Double analisiFisicoQuimicK232,
			Boolean analisiFisicoQuimicK232Valid,
			Double analisiFisicoQuimicHumitat,
			Boolean analisiFisicoQuimicHumitatValid, Boolean valid,
			String observacions, String analisiFisicoQuimicNomLaboratori,
			Date analisiFisicoQuimicDataRecepcio,
			Date analisiFisicoQuimicDataInici, Date analisiFisicoQuimicDataFi,
			Boolean desqualificatDiposit) {
		this.traza = traza;
		this.varietatOli = varietatOli;
		this.establiment = establiment;
		this.diposit = diposit;
		this.data = data;
		this.analisiSensorialNomLaboratori = analisiSensorialNomLaboratori;
		this.analisiSensorialDataRecepcio = analisiSensorialDataRecepcio;
		this.analisiSensorialDataTast = analisiSensorialDataTast;
		this.analisiSensorialVarietatOliValid = analisiSensorialVarietatOliValid;
		this.analisiSensorialObservacions = analisiSensorialObservacions;
		this.analisiFisicoQuimicAcidesa = analisiFisicoQuimicAcidesa;
		this.analisiFisicoQuimicAcidesaValid = analisiFisicoQuimicAcidesaValid;
		this.analisiFisicoQuimicIp = analisiFisicoQuimicIp;
		this.analisiFisicoQuimicIpValid = analisiFisicoQuimicIpValid;
		this.analisiFisicoQuimicK270 = analisiFisicoQuimicK270;
		this.analisiFisicoQuimicK270Valid = analisiFisicoQuimicK270Valid;
		this.analisiFisicoQuimicK232 = analisiFisicoQuimicK232;
		this.analisiFisicoQuimicK232Valid = analisiFisicoQuimicK232Valid;
		this.analisiFisicoQuimicHumitat = analisiFisicoQuimicHumitat;
		this.analisiFisicoQuimicHumitatValid = analisiFisicoQuimicHumitatValid;
		this.valid = valid;
		this.observacions = observacions;
		this.analisiFisicoQuimicNomLaboratori = analisiFisicoQuimicNomLaboratori;
		this.analisiFisicoQuimicDataRecepcio = analisiFisicoQuimicDataRecepcio;
		this.analisiFisicoQuimicDataInici = analisiFisicoQuimicDataInici;
		this.analisiFisicoQuimicDataFi = analisiFisicoQuimicDataFi;
		this.desqualificatDiposit = desqualificatDiposit;
	}

//	public AnaliticaAux(AnaliticaCommand command) {
//		this.analisiSensorialDataRecepcio = command.getAnalisiSensorialDataRecepcio();
//		this.analisiSensorialDataTast = command.getAnalisiSensorialDataTast();
//		this.analisiSensorialNomLaboratori = command.getAnalisiSensorialNomLaboratori();
//		this.analisiSensorialObservacions = command.getAnalisiSensorialObservacions();
//		this.idVarietatOli = command.getIdVarietatOli();
//		
//		this.analisiFisicoQuimicAcidesa = command.getAnalisiFisicoQuimicAcidesa();
//		this.analisiFisicoQuimicDataFi = command.getAnalisiFisicoQuimicDataFi();
//		this.analisiFisicoQuimicDataInici = command.getAnalisiFisicoQuimicDataInici();
//		this.analisiFisicoQuimicDataRecepcio = command.getAnalisiFisicoQuimicDataRecepcio();
//		this.analisiFisicoQuimicHumitat = command.getAnalisiFisicoQuimicHumitat();
//		this.analisiFisicoQuimicIp = command.getAnalisiFisicoQuimicIp();
//		this.analisiFisicoQuimicK232 = command.getAnalisiFisicoQuimicK232();
//		this.analisiFisicoQuimicK270 = command.getAnalisiFisicoQuimicK270();
//		this.analisiFisicoQuimicNomLaboratori = command.getAnalisiFisicoQuimicNomLaboratori();
//		
//		this.valid = command.getValid();
//		this.observacions = command.getObservacions();
//		
//		this.data = command.getData();
//		this.diposit = command.getDiposit();
//		this.establiment = command.getEstabliment();
//		
//	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public VarietatOli getVarietatOli() {
		return this.varietatOli;
	}

	public void setVarietatOli(VarietatOli varietatOli) {
		this.varietatOli = varietatOli;
	}

	public Establiment getEstabliment() {
		return this.establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Diposit getDiposit() {
		return this.diposit;
	}

	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAnalisiSensorialNomLaboratori() {
		return this.analisiSensorialNomLaboratori;
	}

	public void setAnalisiSensorialNomLaboratori(
			String analisiSensorialNomLaboratori) {
		this.analisiSensorialNomLaboratori = analisiSensorialNomLaboratori;
	}

	public Date getAnalisiSensorialDataRecepcio() {
		return this.analisiSensorialDataRecepcio;
	}

	public void setAnalisiSensorialDataRecepcio(
			Date analisiSensorialDataRecepcio) {
		this.analisiSensorialDataRecepcio = analisiSensorialDataRecepcio;
	}

	public Date getAnalisiSensorialDataTast() {
		return this.analisiSensorialDataTast;
	}

	public void setAnalisiSensorialDataTast(Date analisiSensorialDataTast) {
		this.analisiSensorialDataTast = analisiSensorialDataTast;
	}

	public Boolean getAnalisiSensorialVarietatOliValid() {
		return this.analisiSensorialVarietatOliValid;
	}

	public void setAnalisiSensorialVarietatOliValid(
			Boolean analisiSensorialVarietatOliValid) {
		this.analisiSensorialVarietatOliValid = analisiSensorialVarietatOliValid;
	}

	public String getAnalisiSensorialObservacions() {
		return this.analisiSensorialObservacions;
	}

	public void setAnalisiSensorialObservacions(
			String analisiSensorialObservacions) {
		this.analisiSensorialObservacions = analisiSensorialObservacions;
	}

	public Double getAnalisiFisicoQuimicAcidesa() {
		return this.analisiFisicoQuimicAcidesa;
	}

	public void setAnalisiFisicoQuimicAcidesa(Double analisiFisicoQuimicAcidesa) {
		this.analisiFisicoQuimicAcidesa = analisiFisicoQuimicAcidesa;
	}

	public Boolean getAnalisiFisicoQuimicAcidesaValid() {
		return this.analisiFisicoQuimicAcidesaValid;
	}

	public void setAnalisiFisicoQuimicAcidesaValid(
			Boolean analisiFisicoQuimicAcidesaValid) {
		this.analisiFisicoQuimicAcidesaValid = analisiFisicoQuimicAcidesaValid;
	}

	public Double getAnalisiFisicoQuimicIp() {
		return this.analisiFisicoQuimicIp;
	}

	public void setAnalisiFisicoQuimicIp(Double analisiFisicoQuimicIp) {
		this.analisiFisicoQuimicIp = analisiFisicoQuimicIp;
	}

	public Boolean getAnalisiFisicoQuimicIpValid() {
		return this.analisiFisicoQuimicIpValid;
	}

	public void setAnalisiFisicoQuimicIpValid(Boolean analisiFisicoQuimicIpValid) {
		this.analisiFisicoQuimicIpValid = analisiFisicoQuimicIpValid;
	}

	public Double getAnalisiFisicoQuimicK270() {
		return this.analisiFisicoQuimicK270;
	}

	public void setAnalisiFisicoQuimicK270(Double analisiFisicoQuimicK270) {
		this.analisiFisicoQuimicK270 = analisiFisicoQuimicK270;
	}

	public Boolean getAnalisiFisicoQuimicK270Valid() {
		return this.analisiFisicoQuimicK270Valid;
	}

	public void setAnalisiFisicoQuimicK270Valid(
			Boolean analisiFisicoQuimicK270Valid) {
		this.analisiFisicoQuimicK270Valid = analisiFisicoQuimicK270Valid;
	}

	public Double getAnalisiFisicoQuimicK232() {
		return this.analisiFisicoQuimicK232;
	}

	public void setAnalisiFisicoQuimicK232(Double analisiFisicoQuimicK232) {
		this.analisiFisicoQuimicK232 = analisiFisicoQuimicK232;
	}

	public Boolean getAnalisiFisicoQuimicK232Valid() {
		return this.analisiFisicoQuimicK232Valid;
	}

	public void setAnalisiFisicoQuimicK232Valid(
			Boolean analisiFisicoQuimicK232Valid) {
		this.analisiFisicoQuimicK232Valid = analisiFisicoQuimicK232Valid;
	}

	public Double getAnalisiFisicoQuimicHumitat() {
		return this.analisiFisicoQuimicHumitat;
	}

	public void setAnalisiFisicoQuimicHumitat(Double analisiFisicoQuimicHumitat) {
		this.analisiFisicoQuimicHumitat = analisiFisicoQuimicHumitat;
	}

	public Boolean getAnalisiFisicoQuimicHumitatValid() {
		return this.analisiFisicoQuimicHumitatValid;
	}

	public void setAnalisiFisicoQuimicHumitatValid(
			Boolean analisiFisicoQuimicHumitatValid) {
		this.analisiFisicoQuimicHumitatValid = analisiFisicoQuimicHumitatValid;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getAnalisiFisicoQuimicNomLaboratori() {
		return this.analisiFisicoQuimicNomLaboratori;
	}

	public void setAnalisiFisicoQuimicNomLaboratori(
			String analisiFisicoQuimicNomLaboratori) {
		this.analisiFisicoQuimicNomLaboratori = analisiFisicoQuimicNomLaboratori;
	}

	public Date getAnalisiFisicoQuimicDataRecepcio() {
		return this.analisiFisicoQuimicDataRecepcio;
	}

	public void setAnalisiFisicoQuimicDataRecepcio(
			Date analisiFisicoQuimicDataRecepcio) {
		this.analisiFisicoQuimicDataRecepcio = analisiFisicoQuimicDataRecepcio;
	}

	public Date getAnalisiFisicoQuimicDataInici() {
		return this.analisiFisicoQuimicDataInici;
	}

	public void setAnalisiFisicoQuimicDataInici(
			Date analisiFisicoQuimicDataInici) {
		this.analisiFisicoQuimicDataInici = analisiFisicoQuimicDataInici;
	}

	public Date getAnalisiFisicoQuimicDataFi() {
		return this.analisiFisicoQuimicDataFi;
	}

	public void setAnalisiFisicoQuimicDataFi(Date analisiFisicoQuimicDataFi) {
		this.analisiFisicoQuimicDataFi = analisiFisicoQuimicDataFi;
	}

	public Boolean getDesqualificatDiposit() {
		return this.desqualificatDiposit;
	}

	public void setDesqualificatDiposit(Boolean desqualificatDiposit) {
		this.desqualificatDiposit = desqualificatDiposit;
	}

	public Integer getIdVarietatOli() {
		return idVarietatOli;
	}

	public void setIdVarietatOli(Integer idVarietatOli) {
		this.idVarietatOli = idVarietatOli;
	}


	
	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	// end of extra code specified in the hbm.xml files

}
