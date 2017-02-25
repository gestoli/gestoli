/**
 * ZonaValidator.java
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
 * 
 */
public class ZonaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ZonaValidator.class);


	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ZonaCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ZonaCommand zona = (ZonaCommand) obj;

		if (zona.getImatgePlanol() == null && (zona.getFictici()== null || !zona.getFictici().booleanValue())){
			ValidationUtils.rejectIfEmpty(
					errors,
					"imatge",
					"error.plano.buit",
			"El camp Plano pot estar buit");
		}
		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
		"El camp Nom no pot estar buit");


		

	}

		
}
