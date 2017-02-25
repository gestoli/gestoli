package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

public class DevolucioValidator implements Validator {

	private static Logger logger = Logger.getLogger(DevolucioValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
	public boolean supports(Class clazz) {
		return clazz.equals(DevolucioCommand.class);
	}

	public void validate(Object obj, Errors errors) {
		DevolucioCommand devolucioCommand = (DevolucioCommand)obj;
		try {
			if (devolucioCommand.getDevolucioBotelles() > devolucioCommand.getTotalBotelles()) {
				errors.rejectValue("devolucioBotelles", "error.botelles.maxim", "No es poden tornar més botelles de les que varen sortir");
			}
			
			Integer botelles = devolucioCommand.getDevolucioBotelles() + devolucioCommand.getBotellesTornades();
			if (botelles > devolucioCommand.getTotalBotelles()) {
				errors.rejectValue("devolucioBotelles", "error.botelles.maxim", "No es poden tornar més botelles de les que varen sortir");
			}
			ValidationUtils.rejectIfEmpty(
	                errors,
	                "data",
	                "error.data.buit",
	                "El camp Data no pot estar buit");
		} catch (Exception ex) {
			logger.error("Error validant les devolucions. ", ex);
		}
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
}
