package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPlaManteniment;

public class QualitatPlaMantenimentCommand extends QualitatPlaManteniment{
	
	private static final long serialVersionUID = 1L;

	private Long equipId;
	private Long responsableInternId;
	private Long verificacioResponsableId;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacio.
     * @param form QualitatPlaManteniment
     */
    public void fromQualitatPlaManteniment(QualitatPlaManteniment form) {
    	setId(form.getId());
    	setEquip(form.getEquip());
    	if (form.getEquip()!=null){
    		setEquipId(form.getEquip().getCodi());
    	}
    	setAccions(form.getAccions());
    	setFrecuencia(form.getFrecuencia());
    	setIsResponsableIntern(form.getIsResponsableIntern());
    	setResponsableIntern(form.getResponsableIntern());
    	if (form.getResponsableIntern() != null){
    		setResponsableInternId(form.getResponsableIntern().getCodi());
    	}
    	setResponsableExtern(form.getResponsableExtern());
    	setVerificacioFrecuencia(form.getVerificacioFrecuencia());
    	setVerificacioResponsable(form.getVerificacioResponsable());
    	if (form.getVerificacioResponsable()!=null){
    		setVerificacioResponsableId(form.getVerificacioResponsable().getCodi());
    	}
    	setActiu(form.getActiu());
    }

	public Long getEquipId() {
		return equipId;
	}

	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}

	public Long getResponsableInternId() {
		return responsableInternId;
	}

	public void setResponsableInternId(Long responsableInternId) {
		this.responsableInternId = responsableInternId;
	}

	public Long getVerificacioResponsableId() {
		return verificacioResponsableId;
	}

	public void setVerificacioResponsableId(Long verificacioResponsableId) {
		this.verificacioResponsableId = verificacioResponsableId;
	}
    
    
	
}
