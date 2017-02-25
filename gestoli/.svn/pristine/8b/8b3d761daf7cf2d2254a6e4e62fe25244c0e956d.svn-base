package es.caib.gestoli.front.spring.qualitat;

import java.util.Date;

import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatDescripcioPersonalCommand extends QualitatDescripcioPersonal {
	
	private static final long serialVersionUID = 1L;
	
	String nom;
	String llinatges;
	Date dataIncorporacio;
	Long idCarrec;
	Date dataCarrec;
	Long idEstabliment;
	 
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatDescripcioPersonal(QualitatDescripcioPersonal pers) {
    	setCodi(pers.getCodi());
    	setNom(pers.getNom());
    	setLlinatges(pers.getLlinatges());
    	setDataIncorporacio(pers.getDataIncorporacio());
    	setCarrec(pers.getCarrec());
    	setDataCarrec(pers.getDataCarrec());
    	setEstabliment(pers.getEstabliment());
    	setIdCarrec(pers.getCarrec().getId());
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLlinatges() {
		return llinatges;
	}

	public void setLlinatges(String llinatges) {
		this.llinatges = llinatges;
	}

	public Date getDataIncorporacio() {
		return dataIncorporacio;
	}

	public void setDataIncorporacio(Date dataIncorporacio) {
		this.dataIncorporacio = dataIncorporacio;
	}

	public Long getIdCarrec() {
		return idCarrec;
	}

	public void setIdCarrec(Long idCarrec) {
		this.idCarrec = idCarrec;
	}

	public Date getDataCarrec() {
		return dataCarrec;
	}

	public void setDataCarrec(Date dataCarrec) {
		this.dataCarrec = dataCarrec;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    
    
}
