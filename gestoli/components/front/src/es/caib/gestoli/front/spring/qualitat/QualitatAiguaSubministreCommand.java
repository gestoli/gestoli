package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAiguaSubministre;

public class QualitatAiguaSubministreCommand extends QualitatAiguaSubministre {

	private static final long serialVersionUID = 1L;
	private Long responsableId;
	
	public void fromQualitatAiguaSubministre(QualitatAiguaSubministre form) {
    	setId(form.getId());
    	setNetejaInstalacio(form.getNetejaInstalacio());
    	setHigienePersonal(form.getHigienePersonal());
    	setNetejaRoba(form.getNetejaRoba());
    	setElaboracioProductes(form.getElaboracioProductes());
    	setAltresUsos(form.getAltresUsos());
    	setXarxaPublica(form.getXarxaPublica());
    	setAbastamentPropi(form.getAbastamentPropi());
    	setCloracio(form.getCloracio());
    	setOzonitzacio(form.getOzonitzacio());
    	setFiltracio(form.getFiltracio());
    	setDescalcificacio(form.getDescalcificacio());
    	setAltresTractaments(form.getAltresTractaments());
    	setResponsablePla(form.getResponsablePla());
    	if (form.getResponsablePla() != null){
    		setResponsableId(form.getResponsablePla().getCodi());
    	}
    	setEstabliment(form.getEstabliment());
    }
	
	public Long getResponsableId() {
		return responsableId;
	}
	public void setResponsableId(Long responsableId) {
		this.responsableId = responsableId;
	}
	
	

}
