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
public class AnaliticaParametreValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(AnaliticaParametreValidator.class);
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(AnaliticaParametreCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"operador",
				"error.analiticaParametre.operador.buit",
				"El camp Operador no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"valor",
				"error.analiticaParametre.valor.buit",
				"El camp Valor no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idVarietatOli",
				"error.analiticaParametre.varietatOli.buit",
				"El camp Varietat no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idAnaliticaParametreTipus",
				"error.analiticaParametre.analiticaParametreTipus.buit",
				"El camp Paràmetre de control no pot estar buit");
		
	}

}
