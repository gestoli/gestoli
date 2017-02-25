package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GestioInfografiaValidator  implements Validator {
	private static Logger logger = Logger.getLogger(GestioInfografiaValidator.class);

	
	public boolean supports(Class clazz) {
		return clazz.equals(GestioInfografiaCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

	
}
