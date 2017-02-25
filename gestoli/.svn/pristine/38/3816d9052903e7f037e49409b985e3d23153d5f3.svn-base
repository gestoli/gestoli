package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCControl;

public class QualitatAPPCCControlCommand  extends QualitatAPPCCControl{

	private static final long serialVersionUID = 1L;
	
	private Long idEtapa;
	private Long idPerill;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlaguesVerificacio.
     * @param form QualitatControlPlaguesVerificacio
     */
    public void fromQualitatAPPCCControl(QualitatAPPCCControl form) {
    	setId(form.getId());
    	setP1(form.getP1());
    	setP2(form.getP2());
    	setP3(form.getP3());
    	setP4(form.getP4());
    	setP5(form.getP5());
    	setPuntControl(form.getPuntControl());
    	setPerillControlat(form.getPerillControlat());
    	
    	setEtapa(form.getEtapa());
    	if (form.getEtapa() != null){
    		setIdEtapa(form.getEtapa().getId());
    	}
    	
    	setPerill(form.getPerill());
    	if (form.getPerill() != null){
    		setIdPerill(form.getPerill().getId());
    	}
    }

	public Long getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}

	public Long getIdPerill() {
		return idPerill;
	}

	public void setIdPerill(Long idPerill) {
		this.idPerill = idPerill;
	}
	
    

}
