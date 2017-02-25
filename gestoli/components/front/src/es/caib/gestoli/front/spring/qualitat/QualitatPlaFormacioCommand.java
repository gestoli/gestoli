package es.caib.gestoli.front.spring.qualitat;

import java.util.Iterator;

import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class QualitatPlaFormacioCommand extends QualitatPlaFormacio {
	
	private static final long serialVersionUID = 1L;

	Boolean periodic;
	String frecuencia;
	Integer duracio;
	String duracioTipus;
	Long[] personalArray;
	Long responsableInternId;
	Long idEstabliment;
	 
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacio.
     * @param form QualitatPlaFormacio
     */
    public void fromQualitatPlaFormacio(QualitatPlaFormacio form) {
    	setId(form.getId());
    	setDescripcio(form.getDescripcio());
    	setContinguts(form.getContinguts());
    	setPeriodic(form.getPeriodic());
    	setFrecuencia(form.getFrecuencia());
    	setDataPrevista(form.getDataPrevista());
    	setIsResponsableIntern(form.getIsResponsableIntern());
    	setResponsableIntern(form.getResponsableIntern());
    	if (form.getResponsableIntern() != null){
    		setResponsableInternId(form.getResponsableIntern().getCodi());
    	}
    	setResponsableExtern(form.getResponsableExtern());
    	setDuracio(form.getDuracio());
    	setDuracioTipus(form.getDuracioTipus());
    	setEstabliment(form.getEstabliment());
    	
    	Long[] personalIds = new Long[form.getPersonalAssistent().size()];
        int index = 0;
        for (Iterator it = form.getPersonalAssistent().iterator(); it.hasNext();) {
        	personalIds[index++] = ((QualitatFormacioEvaluacio)it.next()).getTreballador().getCodi();
        }
        setPersonalArray(personalIds);
    }

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Boolean getPeriodic() {
		return periodic;
	}

	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Integer getDuracio() {
		return duracio;
	}

	public void setDuracio(Integer duracio) {
		this.duracio = duracio;
	}

	public String getDuracioTipus() {
		return duracioTipus;
	}

	public void setDuracioTipus(String duracioTipus) {
		this.duracioTipus = duracioTipus;
	}

	public Long[] getPersonalArray() {
		return personalArray;
	}

	public void setPersonalArray(Long[] personalArray) {
		this.personalArray = personalArray;
	}

	public Long getResponsableInternId() {
		return responsableInternId;
	}

	public void setResponsableInternId(Long responsableInternId) {
		this.responsableInternId = responsableInternId;
	}
    
    
}
