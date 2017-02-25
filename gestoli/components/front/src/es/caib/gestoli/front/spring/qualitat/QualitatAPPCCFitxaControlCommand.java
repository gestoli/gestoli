package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControlHistoric;

public class QualitatAPPCCFitxaControlCommand extends QualitatAPPCCFitxaControlHistoric{

	private static final long serialVersionUID = 1L;
	
	private Long idResponsablePrevencio;
	private Long idResponsableVigilancia;
	private Long idResponsableCorreccio;
	private Long idFitxa;
	private Long idControl;


	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAPPCCFitxaControlHistoric.
     * @param form QualitatAPPCCFitxaControlHistoric
     */
    public void fromQualitatAPPCCFitxaControlHistoric(QualitatAPPCCFitxaControlHistoric form) {
    	setId(form.getId());
    	setMesuresPrevencio(form.getMesuresPrevencio());
    	setResponsablePrevencio(form.getResponsablePrevencio());
    	if (form.getResponsablePrevencio() != null){
    		setIdResponsablePrevencio(form.getResponsablePrevencio().getCodi());
    	}
    	setVigilancia(form.getVigilancia());
    	setResponsableVigilancia(form.getResponsableVigilancia());   	
    	if (form.getResponsableVigilancia() != null){
    		setIdResponsableVigilancia(form.getResponsableVigilancia().getCodi());
    	}
    	setRegistre(form.getRegistre());
    	setLimits(form.getLimits());
    	setFreqVigilancia(form.getFreqVigilancia());
    	setMesuresCorreccio(form.getMesuresCorreccio());
    	setResponsableCorreccio(form.getResponsableCorreccio());
    	if (form.getResponsableCorreccio() != null){
    		setIdResponsableCorreccio(form.getResponsableCorreccio().getCodi());
    	}
    	setFitxa(form.getFitxa());
    	if (form.getFitxa() != null){
    		setIdFitxa(form.getFitxa().getId());
    		if (form.getFitxa().getControl() != null){
    			setIdControl(form.getFitxa().getControl().getId());
    		}
    	}
    	setDataModificacio(form.getDataModificacio());
    }

	public Long getIdResponsablePrevencio() {
		return idResponsablePrevencio;
	}

	public void setIdResponsablePrevencio(Long idResponsablePrevencio) {
		this.idResponsablePrevencio = idResponsablePrevencio;
	}

	public Long getIdResponsableVigilancia() {
		return idResponsableVigilancia;
	}

	public void setIdResponsableVigilancia(Long idResponsableVigilancia) {
		this.idResponsableVigilancia = idResponsableVigilancia;
	}

	public Long getIdResponsableCorreccio() {
		return idResponsableCorreccio;
	}

	public void setIdResponsableCorreccio(Long idResponsableCorreccio) {
		this.idResponsableCorreccio = idResponsableCorreccio;
	}

	public Long getIdFitxa() {
		return idFitxa;
	}

	public void setIdFitxa(Long idFitxa) {
		this.idFitxa = idFitxa;
	}

	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}
    
    
}
