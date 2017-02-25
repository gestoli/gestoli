package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPlaNetejaRealitzacio;

public class QualitatPlaNetejaRealitzacioCommand extends QualitatPlaNetejaRealitzacio{
	
	private static final long serialVersionUID = 1L;

	private Long netejaId;
	private Long responsableId;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacioRealitzacio.
     * @param form QualitatPlaNetejaRealitzacio
     */
    public void fromQualitatPlaNetejaRealitzacio(QualitatPlaNetejaRealitzacio form) {
    	setId(form.getId());
    	setNeteja(form.getNeteja());
    	if (form.getNeteja() != null){
    		setNetejaId(form.getNeteja().getCodi());
    	}
    	setDataAnterior(form.getDataAnterior());
    	setDataRealitzacio(form.getDataRealitzacio());
    	setResponsable(form.getResponsable());
    	if (form.getResponsable() != null){
    		setResponsableId(form.getResponsable().getCodi());
    	}    	
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
