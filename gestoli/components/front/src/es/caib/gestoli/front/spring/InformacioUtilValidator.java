package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InformacioUtilValidator  implements Validator {
	private static Logger logger = Logger.getLogger(InformacioUtilValidator.class);

	
	public boolean supports(Class clazz) {
		return clazz.equals(InformacioUtilCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

	
}
