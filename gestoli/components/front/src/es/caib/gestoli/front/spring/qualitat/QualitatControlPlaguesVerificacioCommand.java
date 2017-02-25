package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatControlPlaguesVerificacio;

public class QualitatControlPlaguesVerificacioCommand extends QualitatControlPlaguesVerificacio{

	private static final long serialVersionUID = 1L;
	
	private Long idPlaga;
	private Long idResponsableVerificacio;
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatControlPlaguesVerificacio.
     * @param form QualitatControlPlaguesVerificacio
     */
    public void fromQualitatControlPlaguesVerificacio(QualitatControlPlaguesVerificacio form) {
    	setId(form.getId());
    	setControlPlaga(form.getControlPlaga());
    	if (form.getControlPlaga() != null){
    		setIdPlaga(form.getControlPlaga().getId());
    	}
    	setObjectiu(form.getObjectiu());
    	setDataVerificacio(form.getDataVerificacio());
    	setResponsableVerificacio(form.getResponsableVerificacio());
    	if (form.getResponsableVerificacio() != null){
    		setIdResponsableVerificacio(form.getResponsableVerificacio().getCodi());
    	}
    	setSatisfactori(form.getSatisfactori());
    	setNoConformitats(form.getNoConformitats());
    }

	public Long getIdPlaga() {
		return idPlaga;
	}

	public void setIdPlaga(Long idPlaga) {
		this.idPlaga = idPlaga;
	}

	public Long getIdResponsableVerificacio() {
		return idResponsableVerificacio;
	}

	public void setIdResponsableVerificacio(Long idResponsableVerificacio) {
		this.idResponsableVerificacio = idResponsableVerificacio;
	}
    
    
}
