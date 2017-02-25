package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatAPPCC;
import es.caib.gestoli.logic.model.QualitatPuestoTreball;

public class QualitatAPPCCCommand extends QualitatAPPCC {
	
	private static final long serialVersionUID = 1L;

	private Long[] carrecsArray;
	private Long idEstabliment;
	
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatAPPCC.
     * @param form QualitatAPPCC
     */
    public void fromQualitatAPPCC(QualitatAPPCC form) {
    	setId(form.getId());
    	
    	Long[] carrecIds = new Long[form.getCarrecs().size()];
        int index = 0;
        for (QualitatPuestoTreball carrec : form.getCarrecs()) {
        	carrecIds[index++] = carrec.getId();
        }
        setCarrecsArray(carrecIds);
    	
        setProductes(form.getProductes());
        setEstabliment(form.getEstabliment());
    }

	public Long[] getCarrecsArray() {
		return carrecsArray;
	}

	public void setCarrecsArray(Long[] carrecsArray) {
		this.carrecsArray = carrecsArray;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    	
}
