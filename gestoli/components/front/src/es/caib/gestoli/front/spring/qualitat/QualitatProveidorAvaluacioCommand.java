package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatProveidorAvaluacio;

public class QualitatProveidorAvaluacioCommand extends QualitatProveidorAvaluacio{

	private static final long serialVersionUID = 1L;
	
	private Long idProveidor;
	private Long idResponsableVerificacio;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatProveidorAvaluacio.
     * @param form QualitatProveidorAvaluacio
     */
    public void fromQualitatProveidorAvaluacio(QualitatProveidorAvaluacio form) {
    	setId(form.getId());
    	setProveidor(form.getProveidor());
    	if (form.getProveidor() != null){
    		setIdProveidor(form.getProveidor().getCodi());
    	}
    	setValoracio(form.getValoracio());
    	setDataRehomologacio(form.getDataRehomologacio());
    	setDataDeshomologacio(form.getDataDeshomologacio());
    	setProximaAvaluacio(form.getProximaAvaluacio());
    	setObjectiu(form.getObjectiu());
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setIdResponsableVerificacio(form.getResponsableVerificacio().getCodi());
    	}
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    	setObservacions(form.getObservacions());
    }

	public Long getIdProveidor() {
		return idProveidor;
	}

	public void setIdProveidor(Long idProveidor) {
		this.idProveidor = idProveidor;
	}

	public Long getIdResponsableVerificacio() {
		return idResponsableVerificacio;
	}

	public void setIdResponsableVerificacio(Long idResponsableVerificacio) {
		this.idResponsableVerificacio = idResponsableVerificacio;
	}
 
    
}
