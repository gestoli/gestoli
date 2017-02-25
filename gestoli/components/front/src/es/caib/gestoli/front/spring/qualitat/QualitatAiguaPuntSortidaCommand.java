package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAiguaPuntSortida;

public class QualitatAiguaPuntSortidaCommand extends QualitatAiguaPuntSortida{

	private static final long serialVersionUID = 1L;
	
	private Long idEstabliment;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlagues.
     * @param form QualitatControlPlagues
     */
    public void fromQualitatAiguaPuntSortida(QualitatAiguaPuntSortida form) {
    	setId(form.getId());
    	setUbicacio(form.getUbicacio());
    	setEstabliment(form.getEstabliment());
    	
    }
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	
}
