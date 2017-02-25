package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * Validador pels camps associats amb el formulari de sortides d'orujo.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class SortidaOrujoValidator implements Validator {

	private static Logger logger = Logger.getLogger(SortidaOrujoValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
	public boolean supports(Class clazz) {
		return clazz.equals(SortidaOrujoCommand.class);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.data.buit",
				"El camp Data no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"kilos",
				"error.kilos.buit",
				"El camp Kilos no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"albara",
				"error.albara.buit",
				"El camp Albarà no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"desti",
				"error.desti.buit",
				"El camp Destí no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"document",
				"error.cif.buit",
				"El camp CIF no pot estar buit");
	}

	/**
	 * Injecció de la dependència oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
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
