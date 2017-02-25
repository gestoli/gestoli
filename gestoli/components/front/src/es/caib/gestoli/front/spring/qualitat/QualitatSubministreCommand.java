package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatSubministreCommand extends QualitatSubministre {
	
	private static final long serialVersionUID = 1L;
	

	private Long proveidorId;
	private Long idEstabliment;
	
	 
	public Long getIdEstabliment() {
		return idEstabliment;
	}


	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}


	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatSubministre(QualitatSubministre subm) {
    	setCodi(subm.getCodi());
    	setTipusSubministre(subm.getTipusSubministre());
    	setHomologacio(subm.getHomologacio());
    	setDeshomologacio(subm.getDeshomologacio());
    	setObservacions(subm.getObservacions());
    	setProveidor(subm.getProveidor());
    	if (subm.getProveidor()!=null){
    		setProveidorId(subm.getProveidor().getCodi());
    	}
    }


	public Long getProveidorId() {
		return proveidorId;
	}

	public void setProveidorId(Long proveidorId) {
		this.proveidorId = proveidorId;
	}
    
    
}