/**
 * ProcesAcceptarOliGranelValidator.java
 *
 * Creada el 2 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesAcceptarOliGranelValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesAcceptarOliGranelValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesAcceptarOliGranelCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ProcesAcceptarOliGranelCommand acceptarOliGranelCommand = (ProcesAcceptarOliGranelCommand) obj;
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.dataPrevista.buit",
				"El camp Data prevista no pot estar buit");
		try {
			Diposit dipDesti = oliInfraestructuraEjb.dipositAmbId(acceptarOliGranelCommand.getDipositDesti().getId());
			Double volumActualDesti = dipDesti.getVolumActual();
			
			if(volumActualDesti == null)
				volumActualDesti = 0.0;
			
			Double volumTrasllat = new Double(0);
			
			for(Diposit d: acceptarOliGranelCommand.getDipositsOrigen()){
				Diposit dAux = oliInfraestructuraEjb.dipositAmbId(d.getId());
				volumTrasllat += dAux.getVolumTrasllat();
			}
			
			if( (volumActualDesti + volumTrasllat) > dipDesti.getCapacitat()){
				Object[] valor = new Object[1];
				if (dipDesti.getEstabliment().getUnitatMesura().equals("l")) {
					valor[0] =  String.valueOf(dipDesti.getCapacitat());
					errors.reject("error.litros.incorrecto", valor,"Els litres que es volen introduïr superen els que hi caben actualment: {0}");
				} else if (dipDesti.getEstabliment().getUnitatMesura().equals("k")) {
					valor[0] =  String.valueOf(dipDesti.getCapacitat() * 0.916);
					errors.reject("error.kilos.incorrecto", valor,"Els kilos que es volen introduïr superen els que hi caben actualment: {0}");
				}
			}
			
			//COMPROVEM DATA EXECUCIO
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(!oliInfraestructuraEjb.esDataDipositCorrecte(acceptarOliGranelCommand.getData(), dipDesti.getId())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(acceptarOliGranelCommand.getData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	
	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}

	
}