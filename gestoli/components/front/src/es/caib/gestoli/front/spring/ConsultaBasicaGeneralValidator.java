package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validador de ConsultaGeneralCommand
 * 
 */
@SuppressWarnings("unchecked")
public class ConsultaBasicaGeneralValidator implements Validator {

	/**
     * Indica las classes que suporta aquest validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
	public boolean supports(Class clazz) {
	    return clazz.equals(ConsultaBasicaGeneralCommand.class);
	}

	/**
     * Valida l'objecte.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
	
	
	
	
	
	public void validate(Object obj, Errors errors) {
			if(((ConsultaBasicaGeneralCommand)obj).getPartidaNomC()!=null){
				if(((ConsultaBasicaGeneralCommand)obj).getPartidaNom()==null){
					ValidationUtils.rejectIfEmpty(
							errors,
							"partidaNom",
							"error.consulta.general.partida",
							"Heu de seleccionar al menys un camp per al llistat");
				}
			}
	}

}
