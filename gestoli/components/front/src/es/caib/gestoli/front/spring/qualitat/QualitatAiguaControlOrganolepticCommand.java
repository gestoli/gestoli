package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAiguaControlOrganoleptic;

public class QualitatAiguaControlOrganolepticCommand extends QualitatAiguaControlOrganoleptic {

	private static final long serialVersionUID = 1L;
	private Long responsableId;
	
	public void fromQualitatAiguaControlOrganoleptic(QualitatAiguaControlOrganoleptic form) {
    	setId(form.getId());
    	setFrequencia(form.getFrequencia());
    	setResponsable(form.getResponsable());
    	if (form.getResponsable()!=null) setResponsableId(form.getResponsable().getCodi());
    	setEstabliment(form.getEstabliment());
    }
	
	public Long getResponsableId() {
		return responsableId;
	}
	public void setResponsableId(Long responsableId) {
		this.responsableId = responsableId;
	}
	
	

}
