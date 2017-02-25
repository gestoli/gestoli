package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QualitatDescripcioPersonalValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatDescripcioPersonalValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatDescripcioPersonalCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}

}
