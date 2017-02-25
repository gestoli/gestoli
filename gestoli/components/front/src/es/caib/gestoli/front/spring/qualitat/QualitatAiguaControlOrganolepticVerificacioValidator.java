package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QualitatAiguaControlOrganolepticVerificacioValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatAiguaControlOrganolepticVerificacioValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatAiguaControlOrganolepticVerificacioCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

}
