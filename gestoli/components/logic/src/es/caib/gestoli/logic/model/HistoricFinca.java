package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class HistoricFinca implements java.io.Serializable {

	private Long id;
	private HistoricOlivicultor olivicultor;
	private Long idOriginal;
	private Boolean actiu;
	private String nom;
	private String observacions;
	private Boolean deBaixa;
	private Set plantacios = new HashSet(0);

	public HistoricFinca() {
	}

	public HistoricFinca(HistoricOlivicultor olivicultor, Boolean actiu, String nom) {
		this.olivicultor = olivicultor;
		this.actiu = actiu;
		this.nom = nom;
	}

	public HistoricFinca(HistoricOlivicultor olivicultor, Long idOriginal, Boolean actiu,
			String nom, String observacions, Boolean deBaixa, Set plantacios) {
		this.olivicultor = olivicultor;
		this.idOriginal = idOriginal;
		this.actiu = actiu;
		this.nom = nom;
		this.observacions = observacions;
		this.deBaixa = deBaixa;
		this.plantacios = plantacios;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public HistoricOlivicultor getOlivicultor() {
		return this.olivicultor;
	}
	public void setOlivicultor(HistoricOlivicultor olivicultor) {
		this.olivicultor = olivicultor;
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

	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObservacions() {
		return this.observacions;
	}
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getPlantacios() {
		return this.plantacios;
	}
	public void setPlantacios(Set plantacios) {
		this.plantacios = plantacios;
	}

	public Boolean getDeBaixa() {
		return deBaixa;
	}
	public void setDeBaixa(Boolean deBaixa) {
		this.deBaixa = deBaixa;
	}

}
