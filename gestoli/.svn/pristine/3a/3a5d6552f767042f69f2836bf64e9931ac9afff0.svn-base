package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatControlPlagues;

public class QualitatControlPlaguesCommand extends QualitatControlPlagues{

	private static final long serialVersionUID = 1L;
	
	private Long idEstabliment;
	private Long idResponsableIntern;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlagues.
     * @param form QualitatControlPlagues
     */
    public void fromQualitatControlPlagues(QualitatControlPlagues form) {
    	setId(form.getId());
    	setUbicacio(form.getUbicacio());
    	setElementControl(form.getElementControl());
    	setFrecuencia(form.getFrecuencia());
    	setIsResponsableIntern(form.getIsResponsableIntern());
    	setResponsableIntern(form.getResponsableIntern());
    	if (form.getResponsableIntern() != null){
    		setIdResponsableIntern(form.getResponsableIntern().getCodi());
    	}
    	setEmpresaExterna(form.getEmpresaExterna());
    	setIniciContracte(form.getIniciContracte());
    	setFiContracte(form.getFiContracte());
    	setPlagaObjectiu(form.getPlagaObjectiu());
    	setFrecSeguiment(form.getFrecSeguiment());
    	setEstabliment(form.getEstabliment());
    	
    }
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	
	public Long getIdResponsableIntern() {
		return idResponsableIntern;
	}
	public void setIdResponsableIntern(Long idResponsableIntern) {
		this.idResponsableIntern = idResponsableIntern;
	}
	
	
}
