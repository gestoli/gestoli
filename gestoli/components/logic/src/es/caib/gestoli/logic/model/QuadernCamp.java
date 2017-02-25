package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QuadernCamp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private Plantacio plantacio;
	private String tractament;
	private String materiaActiva;
	private String marca;
	private String dosi;
	private String litresBrou;
	private String terminiSeguretat;
	private String observacions;	
	
	private Olivicultor olivicultor;
	
	public QuadernCamp() {
		super();
	}
	
	public QuadernCamp(
			Long id, Date data, Plantacio plantacio, String tractament,
			String materiaActiva, String marca, String dosi, String litresBrou,
			String terminiSeguretat, String observaciones, Olivicultor olivicultor){
		this.id = id;
		this.data = data;
		this.plantacio = plantacio;
		this.tractament = tractament;
		this.materiaActiva = materiaActiva;
		this.marca = marca;
		this.dosi = dosi;
		this.litresBrou = litresBrou;
		this.terminiSeguretat = terminiSeguretat;
		this.observacions = observaciones;
		this.olivicultor = olivicultor;
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

	public Plantacio getPlantacio() {
		return plantacio;
	}

	public void setPlantacio(Plantacio plantacio) {
		this.plantacio = plantacio;
	}

	public String getTractament() {
		return tractament;
	}

	public void setTractament(String tractament) {
		this.tractament = tractament;
	}

	public String getMateriaActiva() {
		return materiaActiva;
	}

	public void setMateriaActiva(String materiaActiva) {
		this.materiaActiva = materiaActiva;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDosi() {
		return dosi;
	}

	public void setDosi(String dosi) {
		this.dosi = dosi;
	}

	public String getLitresBrou() {
		return litresBrou;
	}

	public void setLitresBrou(String litresBrou) {
		this.litresBrou = litresBrou;
	}

	public String getTerminiSeguretat() {
		return terminiSeguretat;
	}

	public void setTerminiSeguretat(String terminiSeguretat) {
		this.terminiSeguretat = terminiSeguretat;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Olivicultor getOlivicultor() {
		return olivicultor;
	}

	public void setOlivicultor(Olivicultor olivicultor) {
		this.olivicultor = olivicultor;
	}
	
}
