package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Zona implements java.io.Serializable {

	private Long id;

	private Establiment establiment;

	private Long idOriginal;

	private Boolean actiu;

	private String nom;

	private Boolean fictici;

	private Long imatgePlanol;

	private Boolean defecte;

	private String observacions;
	
	private Boolean defecteTrasllat;

	private Set lots = new HashSet(0);

	private Set diposits = new HashSet(0);

	public Zona() {
	}

	public Zona(Establiment establiment, Boolean actiu, String nom,
			Boolean fictici, Boolean defecte, Boolean defecteTrasllat) {
		this.establiment = establiment;
		this.actiu = actiu;
		this.nom = nom;
		this.fictici = fictici;
		this.defecte = defecte;
		this.defecteTrasllat = defecteTrasllat;
	}

	public Zona(Establiment establiment, Long idOriginal, Boolean actiu,
			String nom, Boolean fictici, Long imatgePlanol, Boolean defecte,
			String observacions, Set lots, Set diposits, Boolean defecteTrasllat) {
		this.establiment = establiment;
		this.idOriginal = idOriginal;
		this.actiu = actiu;
		this.nom = nom;
		this.fictici = fictici;
		this.imatgePlanol = imatgePlanol;
		this.defecte = defecte;
		this.observacions = observacions;
		this.lots = lots;
		this.diposits = diposits;
		this.defecteTrasllat = defecteTrasllat;
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

	public Boolean getFictici() {
		return this.fictici;
	}

	public void setFictici(Boolean fictici) {
		this.fictici = fictici;
	}

	public Long getImatgePlanol() {
		return this.imatgePlanol;
	}

	public void setImatgePlanol(Long imatgePlanol) {
		this.imatgePlanol = imatgePlanol;
	}

	public Boolean getDefecte() {
		return this.defecte;
	}

	public void setDefecte(Boolean defecte) {
		this.defecte = defecte;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getLots() {
		return this.lots;
	}

	public void setLots(Set lots) {
		this.lots = lots;
	}

	public Set getDiposits() {
		return this.diposits;
	}

	public void setDiposits(Set diposits) {
		this.diposits = diposits;
	}

	public Boolean getDefecteTrasllat() {
		return defecteTrasllat;
	}

	public void setDefecteTrasllat(Boolean defecteTrasllat) {
		this.defecteTrasllat = defecteTrasllat;
	}

	
	
}
