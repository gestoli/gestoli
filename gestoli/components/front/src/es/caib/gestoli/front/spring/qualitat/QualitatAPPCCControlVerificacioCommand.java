package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCControl;

public class QualitatAPPCCControlVerificacioCommand extends QualitatAPPCCControl{

	private static final long serialVersionUID = 1L;
	
	private Long idResponsableVerificacio;
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlaguesVerificacio.
     * @param form QualitatControlPlaguesVerificacio
     */
    public void fromQualitatAPPCCControl(QualitatAPPCCControl form) {
    	setId(form.getId());
    	setObjectiu(form.getObjectiu());
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setIdResponsableVerificacio(form.getResponsableVerificacio().getCodi());
    	}
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    }
    
	public Long getIdResponsableVerificacio() {
		return idResponsableVerificacio;
	}
	
	public void setIdResponsableVerificacio(Long idResponsableVerificacio) {
		this.idResponsableVerificacio = idResponsableVerificacio;
	}
    
    

}
