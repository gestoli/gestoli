package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class QualitatAPPCCProducteValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatAPPCCProducteValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatAPPCCProducteCommand.class);
	}
	
	public void validate(Object obj, Errors errors) {
	}


}