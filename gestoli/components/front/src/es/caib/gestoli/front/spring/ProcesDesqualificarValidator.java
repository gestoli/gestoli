/**
 * ProcesDesqualificarValidator.java
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

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesDesqualificarValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesDesqualificarValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesDesqualificarCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.data.buit",
				"El camp Data no pot estar buit");
		
		try {
			//COMPROVEM DATA EXECUCIO
			ProcesDesqualificarCommand procesDesqualificarCommand =(ProcesDesqualificarCommand)obj;
		
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(!oliInfraestructuraEjb.esDataDipositCorrecte(procesDesqualificarCommand.getData(), procesDesqualificarCommand.getDiposit().getId())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(procesDesqualificarCommand.getData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
