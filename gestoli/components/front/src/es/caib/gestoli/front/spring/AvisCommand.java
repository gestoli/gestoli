package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Avis;

public class AvisCommand extends Avis{

	private static final long serialVersionUID = 1L;

	Long idEstabliment;
	
	/**
     * Rellena los campos de este objeto con la información
     * del Avis.
     * @param form Avis
     */
    public void fromAvis(Avis form) {
    	setId(form.getId());
    	setTipus(form.getTipus());
    	setDescripcio(form.getDescripcio());
    	setFrecuencia(form.getFrecuencia());
    	setDataSeguent(form.getDataSeguent());    	
    	setEstabliment(form.getEstabliment());
    }

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    	
}
