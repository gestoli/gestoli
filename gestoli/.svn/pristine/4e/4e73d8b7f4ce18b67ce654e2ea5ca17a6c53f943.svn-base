package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatProveidorRGSA;

public class QualitatProveidorRGSACommand extends QualitatProveidorRGSA {
	
	private static final long serialVersionUID = 1L;
	
	private Long proveidorId;

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatProveidorRGSA.
     * @param pers QualitatProveidorRGSA
     */
    public void fromQualitatProveidorRGSA(QualitatProveidorRGSA rgsa) {
    	setId(rgsa.getId());
    	setNom(rgsa.getNom());
    	setCaducitatRGSA(rgsa.getCaducitatRGSA());
    	if (rgsa.getProveidor()!=null){
    		setProveidorId(rgsa.getProveidor().getCodi());
    	}
    }

	public Long getProveidorId() {
		return proveidorId;
	}

	public void setProveidorId(Long proveidorId) {
		this.proveidorId = proveidorId;
	}
    
    
}