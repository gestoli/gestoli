package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPuestoTreball;

public class QualitatPuestoTreballCommand extends QualitatPuestoTreball {
	
	private static final long serialVersionUID = 1L;
	
	String carrec;
	String funcions;
	String formacio;
	String experiencia;
	Long idEstabliment;
	Long idCarrecSuperior;
	 
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPuestoTreball.
     * @param pers QualitatPuestoTreball
     */
    public void fromQualitatPuestoTreball(QualitatPuestoTreball pers) {
    	setId(pers.getId());
    	setNivellJerarquic(pers.getNivellJerarquic());
    	setCarrec(pers.getCarrec());
    	setCarrecSuperior(pers.getCarrecSuperior());
    	if (pers.getCarrecSuperior() != null){
    		setIdCarrecSuperior(pers.getCarrecSuperior().getId());
    	}
    	setFuncions(pers.getFuncions());
    	setFormacio(pers.getFormacio());
    	setExperiencia(pers.getExperiencia());
    	setEstabliment(pers.getEstabliment());
    	if (pers.getEstabliment() != null){
    		setIdEstabliment(pers.getEstabliment().getId());
    	}
    	setPersonal(pers.getPersonal());
    	setCarrecsInferiors(pers.getCarrecsInferiors());
    }

	public String getCarrec() {
		return carrec;
	}

	public void setCarrec(String carrec) {
		this.carrec = carrec;
	}

	public String getFuncions() {
		return funcions;
	}

	public void setFuncions(String funcions) {
		this.funcions = funcions;
	}

	public String getFormacio() {
		return formacio;
	}

	public void setFormacio(String formacio) {
		this.formacio = formacio;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Long getIdCarrecSuperior() {
		return idCarrecSuperior;
	}

	public void setIdCarrecSuperior(Long idCarrecSuperior) {
		this.idCarrecSuperior = idCarrecSuperior;
	}
    
    
}
