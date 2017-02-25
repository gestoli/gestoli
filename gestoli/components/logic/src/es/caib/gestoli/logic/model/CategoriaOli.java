package es.caib.gestoli.logic.model;

import java.util.HashSet;
import java.util.Set;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class CategoriaOli implements java.io.Serializable {

	private Integer id;

	private String nom;

	private String observacions;
	
	private Set partidesOli = new HashSet(0);

	public CategoriaOli() {
	}

	public CategoriaOli(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public CategoriaOli(Integer id, String nom, String observacions) {
		this.id = id;
		this.nom = nom;
		this.observacions = observacions;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set getPartidesOli() {
		return partidesOli;
	}

	public void setPartidesOli(Set partidesOli) {
		this.partidesOli = partidesOli;
	}

}
