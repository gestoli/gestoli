package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 		
 */
public class HistoricPlantacio implements java.io.Serializable {

	private Long id;
	private HistoricFinca finca;
	private Long idOriginal;
	private Boolean actiu;
	private Municipi municipi;
	private String poligon;
	private String parcela;
	private String coordX;
	private String coordY;
	private String catastre;
	private Boolean regadiu;
	private Integer anyPlantacio;
	private String observacions;
	private Boolean deBaixa = false;
	private String nomComplet;
	private Set descomposicioPlantacios = new HashSet(0);

	public HistoricPlantacio() {
	}

	public HistoricPlantacio(HistoricFinca finca, Boolean actiu, Municipi municipi,
			String poligon, String parcela, String coordX, String coordY, Boolean regadiu,
			Integer anyPlantacio) {
		this.finca = finca;
		this.actiu = actiu;
		this.municipi = municipi;
		this.poligon = poligon;
		this.parcela = parcela;
		this.coordX = coordX;
		this.coordY = coordY;
		this.regadiu = regadiu;
		this.anyPlantacio = anyPlantacio;
		
	}

	public HistoricPlantacio(HistoricFinca finca, Long idOriginal, Boolean actiu,
			Municipi municipi, String poligon, String parcela, String coordX, String coordY, 
			Boolean regadiu, Integer anyPlantacio, String observacions, Boolean deBaixa,
			Set descomposicioPlantacios) {
		this.finca = finca;
		this.idOriginal = idOriginal;
		this.actiu = actiu;
		this.municipi = municipi;
		this.poligon = poligon;
		this.parcela = parcela;
		this.coordX = coordX;
		this.coordY = coordY;
		this.regadiu = regadiu;
		this.anyPlantacio = anyPlantacio;
		this.observacions = observacions;
		this.deBaixa = deBaixa;
		this.descomposicioPlantacios = descomposicioPlantacios;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public HistoricFinca getFinca() {
		return this.finca;
	}
	public void setFinca(HistoricFinca finca) {
		this.finca = finca;
	}

	public Long getIdOriginal() {
		return this.idOriginal;
	}
	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public Boolean getActiu() {
		return this.actiu;
	}
	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public Municipi getMunicipi() {
		return this.municipi;
	}
	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
	}

	public String getPoligon() {
		return this.poligon;
	}
	public void setPoligon(String poligon) {
		this.poligon = poligon;
	}

	public String getParcela() {
		return this.parcela;
	}
	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public Boolean getRegadiu() {
		return this.regadiu;
	}
	public void setRegadiu(Boolean regadiu) {
		this.regadiu = regadiu;
	}

	public Integer getAnyPlantacio() {
		return this.anyPlantacio;
	}
	public void setAnyPlantacio(Integer anyPlantacio) {
		this.anyPlantacio = anyPlantacio;
	}

	public String getObservacions() {
		return this.observacions;
	}
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getDescomposicioPlantacios() {
		return this.descomposicioPlantacios;
	}
	public void setDescomposicioPlantacios(Set descomposicioPlantacios) {
		this.descomposicioPlantacios = descomposicioPlantacios;
	}

	public Boolean getDeBaixa() {
		return deBaixa;
	}
	public void setDeBaixa(Boolean deBaixa) {
		this.deBaixa = deBaixa;
	}

	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getCoordX() {
		return coordX;
	}
	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}
	public String getCoordY() {
		return coordY;
	}
	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}
	public String getCatastre() {
		return catastre;
	}
	public void setCatastre(String catastre) {
		this.catastre = catastre;
	}
}
