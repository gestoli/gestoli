package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Idioma implements java.io.Serializable {

	private String id;

	private Boolean actiu;

	private String nom;

	private String observacions;

	public Idioma() {
	}

	public Idioma(String id, Boolean actiu, String nom) {
		this.id = id;
		this.actiu = actiu;
		this.nom = nom;
	}

	public Idioma(String id, Boolean actiu, String nom, String observacions) {
		this.id = id;
		this.actiu = actiu;
		this.nom = nom;
		this.observacions = observacions;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

}
