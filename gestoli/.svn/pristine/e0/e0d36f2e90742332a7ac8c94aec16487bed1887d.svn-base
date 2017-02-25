/**
 * AnaliticaValidator.java
 *
 * Creada el 3 de juliol de 2009
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
public class AnaliticaParametreTipusValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(AnaliticaParametreTipusValidator.class);
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(AnaliticaParametreTipusCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.analiticaParametreTipus.nom.buit",
				"El camp del Nom no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"ordre",
				"error.analiticaParametreTipus.ordre.buit",
				"El camp Ordre no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"tipus",
				"error.analiticaParametreTipus.tipus.buit",
				"El camp Tipus no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"tipusControl",
				"error.analiticaParametreTipus.tipusControl.buit",
				"El camp Tipus de control no pot estar buit");
	}

}
