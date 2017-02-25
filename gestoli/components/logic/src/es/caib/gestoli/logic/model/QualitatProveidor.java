package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class QualitatProveidor implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long codi;
	private String nom;
	private String direccio;
	private String telefon;
	private String personaContacte;
	private Set<QualitatSubministre> subministres = new HashSet<QualitatSubministre>(0);
	private Set<QualitatProveidorRGSA> RGSAs = new HashSet<QualitatProveidorRGSA>(0);
	private String observacions;
	private Establiment establiment;
	
	
	public QualitatProveidor() {
		super();
	}
	
	public QualitatProveidor(Long codi, String nom, String direccio,
			String telefon, String personaContacte, Long numRGSA, Date caducitatRGSA, String observacions,
			Establiment establiment) {
		super();
		this.codi = codi;
		this.nom = nom;
		this.direccio = direccio;
		this.telefon = telefon;
		this.personaContacte = personaContacte;
		this.observacions = observacions;
		this.establiment = establiment;
	}


	public Long getCodi() {
		return codi;
	}


	public void setCodi(Long codi) {
		this.codi = codi;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDireccio() {
		return direccio;
	}


	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public String getPersonaContacte() {
		return personaContacte;
	}


	public void setPersonaContacte(String personaContacte) {
		this.personaContacte = personaContacte;
	}




	public Set<QualitatSubministre> getSubministres() {
		return subministres;
	}


	public void setSubministres(Set<QualitatSubministre> subministres) {
		this.subministres = subministres;
	}

	public Set<QualitatProveidorRGSA> getRGSAs() {
		return RGSAs;
	}

	public void setRGSAs(Set<QualitatProveidorRGSA> rGSAs) {
		RGSAs = rGSAs;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	
	
	
	
}
	
	
