package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Grup implements Comparable {

	private String id;

	private String nom;

	private String observacions;

	private Set usuaris = new HashSet(0);

	public Grup() {
	}

	public Grup(String id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Grup(String id, String nom, String observacions, Set usuaris) {
		this.id = id;
		this.nom = nom;
		this.observacions = observacions;
		this.usuaris = usuaris;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public Set getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(Set usuaris) {
		this.usuaris = usuaris;
	}

	// The following is extra code specified in the hbm.xml files

	/**
	 * Comparator
	 */
	public int compareTo(Object g) {
		Grup grup = (Grup) g;
		return this.nom.compareTo(grup.getNom());
	}

	// end of extra code specified in the hbm.xml files

}
