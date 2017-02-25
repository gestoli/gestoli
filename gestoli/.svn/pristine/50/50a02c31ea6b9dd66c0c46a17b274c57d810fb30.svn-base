package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAiguaControlAnaliticVerificacio;

public class QualitatAiguaControlAnaliticVerificacioCommand extends QualitatAiguaControlAnaliticVerificacio{

	private static final long serialVersionUID = 1L;
	
	private Long idControl;
	private Long idResponsableVerificacio;
	private Long idPuntMostreig;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAiguaControlAnaliticVerificacio.
     * @param form QualitatAiguaControlAnaliticVerificacio
     */
    public void fromQualitatAiguaControlAnaliticVerificacio(QualitatAiguaControlAnaliticVerificacio form) {
    	setId(form.getId());
    	setControlAnalitic(form.getControlAnalitic());
    	if (form.getControlAnalitic() != null){
    		setIdControlAnalitic(form.getControlAnalitic().getId());
    	}
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setIdResponsableVerificacio(form.getResponsableVerificacio().getCodi());
    	}
    	setPuntMostreig(form.getPuntMostreig());
    	if (form.getPuntMostreig() != null){
    		setIdPuntMostreig(form.getPuntMostreig().getId());
    	}
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    	setDataAnalisi(form.getDataAnalisi());
    }

	public Long getIdControlAnalitic() {
		return idControl;
	}

	public void setIdControlAnalitic(Long idControl) {
		this.idControl = idControl;
	}

	public Long getIdResponsableVerificacio() {
		return idResponsableVerificacio;
	}

	public void setIdResponsableVerificacio(Long idResponsableVerificacio) {
		this.idResponsableVerificacio = idResponsableVerificacio;
	}

	public Long getIdPuntMostreig() {
		return idPuntMostreig;
	}

	public void setIdPuntMostreig(Long idPuntMostreig) {
		this.idPuntMostreig = idPuntMostreig;
	}
    
    
}
