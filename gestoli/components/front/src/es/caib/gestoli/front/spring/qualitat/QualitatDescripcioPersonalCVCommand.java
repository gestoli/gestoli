package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatDescripcioPersonalCVCommand  extends QualitatDescripcioPersonal {
	
	private static final long serialVersionUID = 1L;
	
	Long idEstabliment;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatDescripcioPersonalCV(QualitatDescripcioPersonal pers) {
    	setCodi(pers.getCodi());
    	setNom(pers.getNom());
    	setLlinatges(pers.getLlinatges());
    	setDni(pers.getDni());
    	setDataNaixement(pers.getDataNaixement());
    	setTelefon(pers.getTelefon());
    	setDireccio(pers.getDireccio());
    	setFormacio(pers.getFormacio());
    	setExpLaboral(pers.getExpLaboral());
    	setEstabliment(pers.getEstabliment());
    }


	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    
	
}
