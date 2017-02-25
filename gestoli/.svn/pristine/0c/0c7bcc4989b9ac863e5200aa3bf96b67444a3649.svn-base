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
public class ConsultaGeneralValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ConsultaGeneralValidator.class);
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ConsultaGeneralCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ConsultaGeneralCommand consultaGeneralCommand = (ConsultaGeneralCommand) obj;
		
		/*
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
		*/
		if ( consultaGeneralCommand.getTemporadaId()!= null && (consultaGeneralCommand.getDataFi()!= null || consultaGeneralCommand.getDataInici()!= null)){
			errors.rejectValue("buscar", "error.opcion.unica" ,"No puede elegir las dos opciones de busqueda");
		}
		if ( consultaGeneralCommand.getTemporadaId()== null && consultaGeneralCommand.getDataFi() == null && consultaGeneralCommand.getDataInici() == null){
			errors.rejectValue("buscar", "error.opcion.buit" ,"Debe elegir una opcion de busqueda");
		}		

	}

}
