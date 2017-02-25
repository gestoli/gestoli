package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCC;

public class QualitatAPPCCVerificacioCommand extends QualitatAPPCC {
	
	private static final long serialVersionUID = 1L;

	private Long idResponsable;
	private Long idEstabliment;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAPPCC.
     * @param form QualitatAPPCC
     */
    public void fromQualitatAPPCC(QualitatAPPCC form) {
    	setId(form.getId());
    	
    	setNom(form.getNom());
    	setDataComprobacio(form.getDataComprobacio());
    	setCorrecte(form.getCorrecte());
    	setResponsable(form.getResponsable());
    	if (form.getResponsable() != null){
    		setIdResponsable(form.getResponsable().getCodi());
    	}

        setEstabliment(form.getEstabliment());
    }

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Long getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Long idResponsable) {
		this.idResponsable = idResponsable;
	}
	
}
