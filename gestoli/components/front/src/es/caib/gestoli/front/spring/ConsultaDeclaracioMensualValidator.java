/**
 * UsuariValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ConsultaDeclaracioMensualValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ConsultaDeclaracioMensualValidator.class);
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ConsultaDeclaracioMensualCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ConsultaDeclaracioMensualCommand consultaDeclaracioMensualCommand = (ConsultaDeclaracioMensualCommand) obj;
		
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"dataInici",
				"error.dataInici.buit",
				"El camp Data inici no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"dataFi",
				"error.dataFi.buit",
				"El camp Data fi no pot estar buit");
				

	}

}
