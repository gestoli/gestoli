package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCProducte;

public class QualitatAPPCCProducteCommand extends QualitatAPPCCProducte{

	private static final long serialVersionUID = 1L;
	
	private Long idAPPCC;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAPPCCProducte.
     * @param form QualitatAPPCCProducte
     */
    public void fromQualitatAPPCCProducte(QualitatAPPCCProducte form) {
    	setId(form.getId());
    	setDescripcio(form.getDescripcio());
    	setUs(form.getUs());
    	
    	setAppcc(form.getAppcc());
    	if (form.getAppcc() != null){
    		setIdAPPCC(form.getAppcc().getId());
    	}
    }
	
	public Long getIdAPPCC() {
		return idAPPCC;
	}

	public void setIdAPPCC(Long idAPPCC) {
		this.idAPPCC = idAPPCC;
	}
	
}
