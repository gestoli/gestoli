/**
 * DipositValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.MaterialDiposit;
import es.caib.gestoli.logic.model.Zona;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 */
public class EdicioProcessosValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(EdicioProcessosValidator.class);
	

	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(EdicioProcessosCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		EdicioProcessosCommand command = (EdicioProcessosCommand) obj;
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"dataNova",
				"error.data.buit",
				"El camp Data no pot estar buit");
		
		if (command.getDataNova() != null){
			if(command.getDataMax() != null && command.getDataMax().before(command.getDataNova())){ 
				errors.rejectValue(
					"dataNova",
					"error.data.maxima",
					"La nova data ha de ser menor o igual que la data màxima");
			}
			if(command.getDataMin() != null && command.getDataMin().after(command.getDataNova())){ 
				errors.rejectValue(
					"dataNova",
					"error.data.minima",
					"La nova data ha de ser major o igual que la data mínima");
			}
		}
				
	}
}
