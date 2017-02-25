package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCPlaVerificacio;

public class QualitatAPPCCPlaVerificacioCommand extends QualitatAPPCCPlaVerificacio{

	private static final long serialVersionUID = 1L;
	
	private Long idResponsable;
	private Long idEstabliment;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlagues.
     * @param form QualitatControlPlagues
     */
    public void fromQualitatAPPCCPlaVerificacio(QualitatAPPCCPlaVerificacio form) {
    	setId(form.getId());
    	setResponsable(form.getResponsable());
    	if (form.getResponsable() != null){
    		setIdResponsable(form.getResponsable().getCodi());
    	}
    	setDataRealitzacio(form.getDataRealitzacio());
		setP1(form.getP1());
		setP11(form.getP11());
		setP12(form.getP12());
		setP12_comments(form.getP12_comments());
		setP13(form.getP13());
		setP13_comments(form.getP13_comments());
		setP14(form.getP14());
		setP15(form.getP15());
		setP2(form.getP2());
		setP21(form.getP21());
		setP22(form.getP22());
		setP22_comments(form.getP22_comments());
		setP31(form.getP31());
		setP31_comments(form.getP31_comments());
		setP32(form.getP32());
		setP321(form.getP321());
		setP321_comments(form.getP321_comments());
		setP33(form.getP33());
		setP331(form.getP331());
		setP34(form.getP34());
		setP341(form.getP341());
		setP35(form.getP35());
		setP351(form.getP351());
		setP4(form.getP4());
		setP4_comments(form.getP4_comments());
		setP5(form.getP5());
		setP5_comments(form.getP5_comments());
		setP6(form.getP6());
		setEstabliment(form.getEstabliment());
    }

	
	public Long getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Long idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    
}
