package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatNoConformitat;

public class QualitatNoConformitatCommand extends QualitatNoConformitat{

	private static final long serialVersionUID = 1L;
	
	private Long idResponsableDeteccio;
	private Long idControl;
	private Long idDepartament;
	private Long idEstabliment;
	
	
	public QualitatNoConformitatCommand(){ }
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatNoConformitat.
     * @param form QualitatNoConformitat
     */
    public void fromQualitatNoConformitat(QualitatNoConformitat form) {
    	setId(form.getId());
    	setDataNoConformitat(form.getDataNoConformitat());
    	
    	setResponsableDeteccio(form.getResponsableDeteccio());
    	if (form.getResponsableDeteccio() != null){
    		setIdResponsableDeteccio(form.getResponsableDeteccio().getCodi());
    	}
    	
    	setDescripcio(form.getDescripcio());
    	setCausa(form.getCausa());
    	
    	setAccions(form.getAccions());
    	
    	setDepartament(form.getDepartament());
    	if (form.getDepartament() != null){
    		setIdDepartament(form.getDepartament().getId());
    	}
    	
    	setControl(form.getControl());
    	if (form.getControl() != null){
    		setIdControl(form.getControl().getId());
    	}
    	
    	setEstabliment(form.getEstabliment());
    	if (form.getEstabliment() != null){
    		setIdEstabliment(form.getEstabliment().getId());
    	}
    	
    	setDataTancament(form.getDataTancament());
    
    }

	public Long getIdResponsableDeteccio() {
		return idResponsableDeteccio;
	}

	public void setIdResponsableDeteccio(Long idResponsableDeteccio) {
		this.idResponsableDeteccio = idResponsableDeteccio;
	}


	public Long getIdDepartament() {
		return idDepartament;
	}

	public void setIdDepartament(Long idDepartament) {
		this.idDepartament = idDepartament;
	}

	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    
    
}
