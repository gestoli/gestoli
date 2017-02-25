package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPlaMantenimentControl;

public class QualitatPlaMantenimentControlCommand extends QualitatPlaMantenimentControl{
	
	private static final long serialVersionUID = 1L;

	private Long idManteniment;
	private Long idResponsableIntern;
	private Long idResponsableVerificacio;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacioControl.
     * @param form QualitatPlaMantenimentControl
     */
    public void fromQualitatPlaMantenimentControl(QualitatPlaMantenimentControl form) {
    	setId(form.getId());
    	setManteniment(form.getManteniment());
    	if (form.getManteniment() != null){
    		setIdManteniment(form.getManteniment().getId());
    	}
    	setAccioRealitzada(form.getAccioRealitzada());
    	setAltresAccions(form.getAltresAccions());
    	setDataAnterior(form.getDataAnterior());
    	setDataRealitzacio(form.getDataRealitzacio());
    	setIsResponsableIntern(form.getIsResponsableIntern());
    	setResponsableIntern(form.getResponsableIntern());
    	if (form.getResponsableIntern() != null){
    		setIdResponsableIntern(form.getResponsableIntern().getCodi());
    	}
    	setResponsableExtern(form.getResponsableExtern());
    	
    	// Control
    	setObjectiu(form.getObjectiu());
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setIdResponsableVerificacio(form.getResponsableVerificacio().getCodi());
    	}
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    	
    }

	public Long getIdManteniment() {
		return idManteniment;
	}

	public void setIdManteniment(Long idManteniment) {
		this.idManteniment = idManteniment;
	}

	public Long getIdResponsableIntern() {
		return idResponsableIntern;
	}

	public void setIdResponsableIntern(Long idResponsableIntern) {
		this.idResponsableIntern = idResponsableIntern;
	}

	public Long getIdResponsableVerificacio() {
		return idResponsableVerificacio;
	}

	public void setIdResponsableVerificacio(Long idResponsableVerificacio) {
		this.idResponsableVerificacio = idResponsableVerificacio;
	}


}
