package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QualitatPlaNetejaVerificacioValidator  implements Validator {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaVerificacioValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatPlaNetejaVerificacioCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

}
