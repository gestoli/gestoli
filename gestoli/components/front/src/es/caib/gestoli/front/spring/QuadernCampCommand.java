package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.QuadernCamp;

public class QuadernCampCommand extends QuadernCamp{

	private static final long serialVersionUID = 1L;

	Long idPlantacio;
	Long idOlivicultor;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QuadernCamp.
     * @param form QuadernCamp
     */
    public void fromQuadernCamp(QuadernCamp form) {
    	setId(form.getId());
    	setData(form.getData());
    	setPlantacio(form.getPlantacio());
    	if (form.getPlantacio() != null){
    		setIdPlantacio(form.getPlantacio().getId());
    	}
    	setTractament(form.getTractament());
    	setMateriaActiva(form.getMateriaActiva());
    	setMarca(form.getMarca());
    	setDosi(form.getDosi());
    	setLitresBrou(form.getLitresBrou());
    	setTerminiSeguretat(form.getTerminiSeguretat());
    	setObservacions(form.getObservacions());
    	setOlivicultor(form.getOlivicultor());
    	if (form.getOlivicultor() != null){
    		setIdOlivicultor(form.getOlivicultor().getId());
    	}
    }

	public Long getIdPlantacio() {
		return idPlantacio;
	}

	public void setIdPlantacio(Long idPlantacio) {
		this.idPlantacio = idPlantacio;
	}

	public Long getIdOlivicultor() {
		return idOlivicultor;
	}

	public void setIdOlivicultor(Long idOlivicultor) {
		this.idOlivicultor = idOlivicultor;
	}

}
