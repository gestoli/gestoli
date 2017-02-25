package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPlaNetejaVerificacio;

public class QualitatPlaNetejaVerificacioCommand extends QualitatPlaNetejaVerificacio{
	
	private static final long serialVersionUID = 1L;

	private Long netejaId;
	private Long responsableId;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacioVerificacio.
     * @param form QualitatPlaNetejaVerificacio
     */
    public void fromQualitatPlaNetejaVerificacio(QualitatPlaNetejaVerificacio form) {
    	setId(form.getId());
    	setNeteja(form.getNeteja());
    	if (form.getNeteja() != null){
    		setNetejaId(form.getNeteja().getCodi());
    	}
    	setDataAnterior(form.getDataAnterior());
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setResponsableId(form.getResponsableVerificacio().getCodi());
    	}  
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    }

    
	public Long getNetejaId() {
		return netejaId;
	}

	public void setNetejaId(Long netejaId) {
		this.netejaId = netejaId;
	}

	public Long getResponsableId() {
		return responsableId;
	}

	public void setResponsableId(Long responsableInternId) {
		this.responsableId = responsableInternId;
	}
    

}
