package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatNoConformitatAccio;

public class QualitatNoConformitatAccioCommand extends QualitatNoConformitatAccio{

	private static final long serialVersionUID = 1L;
	
	private Long idResponsableAccio;
	private Long idNoConformitat;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatNoConformitat.
     * @param form QualitatNoConformitat
     */
    public void fromQualitatNoConformitatAccio(QualitatNoConformitatAccio form) {
    	setId(form.getId());
    	setCodi(form.getCodi());
    	
    	setResponsableAccio(form.getResponsableAccio());
    	if (form.getResponsableAccio() != null){
    		setIdResponsableAccio(form.getResponsableAccio().getCodi());
    	}
    	
    	setAccio(form.getAccio());
    	setDataFiPrevista(form.getDataFiPrevista());
    	setDataTancament(form.getDataTancament());
    	
    	setNoConformitat(form.getNoConformitat());
    	if (form.getNoConformitat() != null){
    		setIdNoConformitat(form.getNoConformitat().getId());
    	}
    	
    }
    
	public Long getIdResponsableAccio() {
		return idResponsableAccio;
	}

	public void setIdResponsableAccio(Long idResponsableAccio) {
		this.idResponsableAccio = idResponsableAccio;
	}

	public Long getIdNoConformitat() {
		return idNoConformitat;
	}

	public void setIdNoConformitat(Long idNoConformitat) {
		this.idNoConformitat = idNoConformitat;
	}

	
	
}
