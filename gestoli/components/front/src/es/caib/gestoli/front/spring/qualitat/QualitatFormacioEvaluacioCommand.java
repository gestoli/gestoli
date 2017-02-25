package es.caib.gestoli.front.spring.qualitat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;
import es.caib.gestoli.logic.model.VarietatOliva;

public class QualitatFormacioEvaluacioCommand extends QualitatPlaFormacio{
	
	private static final long serialVersionUID = 1L;
	
	QualitatDescripcioPersonal supervisor = new QualitatDescripcioPersonal();
	Long supervisorId;
	String activitatSupervisio;	
	QualitatFormacioEvaluacio[] evaluacions;
	
	public QualitatFormacioEvaluacioCommand(){ }
	
	public QualitatFormacioEvaluacioCommand(int longitud){
		evaluacions = new QualitatFormacioEvaluacio[longitud];
		for (int i=0; i<longitud; i++){
			evaluacions[i]=new QualitatFormacioEvaluacio();
		}
	}
	
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatPlaFormacio.
     * @param form QualitatPlaFormacio
     */
    public void fromQualitatPlaFormacio(QualitatPlaFormacio form, Collection evaluacions) {
    	setId(form.getId());
    	setSupervisorFormacio(form.getSupervisorFormacio());
    	if (form.getSupervisorFormacio() != null){
    			setSupervisorId(form.getSupervisorFormacio().getCodi());
    	}
    	setActivitatSupervisio(form.getActivitatSupervisio());
    	setDataSupervisio(form.getDataSupervisio());
    	
    	QualitatFormacioEvaluacio[] arrayEvaluacions = new QualitatFormacioEvaluacio[evaluacions.size()];
    	int i = 0;
    	for (Iterator it = evaluacions.iterator(); it.hasNext();){
    		QualitatFormacioEvaluacio evaluacio = (QualitatFormacioEvaluacio)it.next();
    		arrayEvaluacions[i] = evaluacio;
//    		QualitatNoConformitat algo = evaluacio.getNoConformitats().iterator().next();
    		++i;
    	}
    	
    	setEvaluacions(arrayEvaluacions);
    }

	public QualitatDescripcioPersonal getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(QualitatDescripcioPersonal supervisor) {
		this.supervisor = supervisor;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getActivitatSupervisio() {
		return activitatSupervisio;
	}

	public void setActivitatSupervisio(String activitatSupervisio) {
		this.activitatSupervisio = activitatSupervisio;
	}

	public QualitatFormacioEvaluacio[] getEvaluacions() {
		return evaluacions;
	}

	public void setEvaluacions(QualitatFormacioEvaluacio[] evaluacions) {
		this.evaluacions = evaluacions;
	}

    
}
