package es.caib.gestoli.front.spring.qualitat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatProveidor;
import es.caib.gestoli.logic.model.QualitatProveidorRGSA;
import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatProveidorCommand extends QualitatProveidor {
	
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String direccio;
	private String telefon;
	private String personaContacte;
	private Set<QualitatSubministre> subministres = new HashSet<QualitatSubministre>(0);
	private Set<QualitatProveidorRGSA> RGSAs = new HashSet<QualitatProveidorRGSA>(0);
	private Long idEstabliment;
	private String observacions;
	 
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatProveidor(QualitatProveidor prov) {
    	setCodi(prov.getCodi());
    	setNom (prov.getNom());
    	setDireccio(prov.getDireccio());
    	setTelefon(prov.getTelefon());
    	setPersonaContacte(prov.getPersonaContacte());
    	setSubministres(prov.getSubministres());
    	setRGSAs(prov.getRGSAs());
    	setEstabliment(prov.getEstabliment());
    	setObservacions(prov.getObservacions());
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

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	


    
}
