package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;

public class QualitatAPPCCEtapaPerillCommand extends QualitatAPPCCEtapaPerill{

	private static final long serialVersionUID = 1L;
	
	private Long idEtapa;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlaguesVerificacio.
     * @param form QualitatControlPlaguesVerificacio
     */
    public void fromQualitatAPPCCEtapaPerill(QualitatAPPCCEtapaPerill form) {
    	setId(form.getId());
    	setEtapa(form.getEtapa());
    	if (form.getEtapa() != null){
    		setIdEtapa(form.getEtapa().getId());
    	}
    	setTipus(form.getTipus());
    	setDetall(form.getDetall());
    	setCausa(form.getCausa());
    	setPrevencio(form.getPrevencio());
    	setProbabilitat(form.getProbabilitat());
    	setGravetat(form.getGravetat());
    }

	public Long getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}
    

}
