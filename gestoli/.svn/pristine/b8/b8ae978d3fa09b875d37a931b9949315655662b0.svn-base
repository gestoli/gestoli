package es.caib.gestoli.logic.model;

// Generated 10-dic-2009 16:00:09 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Jaume Morey Riera (jaumem@limit.es)
 * 		
 */
public class SortidaBota implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Establiment establiment;
	private Bota botaBySboCodbor;
	private Traza traza;
	private Lot lot;
	private Integer tipusOlivaTaula;
	private Date data;
	private Double kgOliva;
	private String desti;
	private Double pH1;
	private Double pH2;
	private Double quantitatAcidCitric;
	private String lotAcidCitric;
	private String observacions;
	private Boolean valid;
	
	public SortidaBota() {
	}

	public SortidaBota(Establiment establiment, Bota botaBySboCodbor,
			Traza traza, Double kgOliva, Boolean valid) {
		this.establiment = establiment;
		this.botaBySboCodbor = botaBySboCodbor;
		this.traza = traza;
		this.tipusOlivaTaula = (botaBySboCodbor != null)?botaBySboCodbor.getTipusOlivaTaula():null;
		this.kgOliva = kgOliva;
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

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}


	public Bota getBotaBySboCodbor() {
		return botaBySboCodbor;
	}

	public void setBotaBySboCodbor(Bota botaBySboCodbor) {
		this.botaBySboCodbor = botaBySboCodbor;
	}

	public Integer getTipusOlivaTaula() {
		return tipusOlivaTaula;
	}

	public void setTipusOlivaTaula(Integer tipusOlivaTaula) {
		this.tipusOlivaTaula = tipusOlivaTaula;
	}

	public Double getKgOliva() {
		return kgOliva;
	}

	public void setKgOliva(Double kgOliva) {
		this.kgOliva = kgOliva;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDesti() {
		return this.desti;
	}

	public void setDesti(String desti) {
		this.desti = desti;
	}

	public Double getpH1() {
		return pH1;
	}

	public void setpH1(Double pH1) {
		this.pH1 = pH1;
	}

	public Double getpH2() {
		return pH2;
	}

	public void setpH2(Double pH2) {
		this.pH2 = pH2;
	}

	public Double getQuantitatAcidCitric() {
		return quantitatAcidCitric;
	}

	public void setQuantitatAcidCitric(Double quantitatAcidCitric) {
		this.quantitatAcidCitric = quantitatAcidCitric;
	}

	public String getLotAcidCitric() {
		return lotAcidCitric;
	}

	public void setLotAcidCitric(String lotAcidCitric) {
		this.lotAcidCitric = lotAcidCitric;
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


	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = "";
		if (this.data != null) {
			fechaFormateada = sdf.format(this.data);
		}
		return fechaFormateada;
	}


	// end of extra code specified in the hbm.xml files

}
