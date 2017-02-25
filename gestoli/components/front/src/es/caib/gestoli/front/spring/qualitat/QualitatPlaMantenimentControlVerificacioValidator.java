package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QualitatPlaMantenimentControlVerificacioValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentControlVerificacioValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatPlaMantenimentControlCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

}
