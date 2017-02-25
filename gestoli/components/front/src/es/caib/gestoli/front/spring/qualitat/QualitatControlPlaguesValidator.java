package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QualitatControlPlaguesValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatControlPlaguesValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatControlPlaguesCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

}
