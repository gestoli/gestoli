package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAiguaControlOrganolepticVerificacio;

public class QualitatAiguaControlOrganolepticVerificacioCommand extends QualitatAiguaControlOrganolepticVerificacio{

	private static final long serialVersionUID = 1L;
	
	private Long idControl;
	private Long idResponsableVerificacio;
	private Long idPuntMostreig;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAiguaControlOrganolepticVerificacio.
     * @param form QualitatAiguaControlOrganolepticVerificacio
     */
    public void fromQualitatAiguaControlOrganolepticVerificacio(QualitatAiguaControlOrganolepticVerificacio form) {
    	setId(form.getId());
    	setControlOrganoleptic(form.getControlOrganoleptic());
    	if (form.getControlOrganoleptic() != null){
    		setIdControlOrganoleptic(form.getControlOrganoleptic().getId());
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
    	setOlor(form.getOlor());
    	setColor(form.getColor());
    	setSabor(form.getSabor());
    	setTerbolesa(form.getTerbolesa());
    	setResultat(form.getResultat());
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    }

	public Long getIdControlOrganoleptic() {
		return idControl;
	}

	public void setIdControlOrganoleptic(Long idControl) {
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
