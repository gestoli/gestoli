package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class RendimentVarietatValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(RendimentVarietatValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Indica las clases que soporta este validador.
	 *
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(RendimentVarietatCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(
				errors,
				"tipusRendimentCampanya",
				"error.tipusRendiment.buit",
				"Ha de triar un tipus de rendiment per les varietats");

		RendimentVarietatCommand rendimentVarietatCommand = (RendimentVarietatCommand)obj;
		try {
			for (int i = 0; i < rendimentVarietatCommand.getRendiments().length; i++) {
				if (rendimentVarietatCommand.getRendiments()[i].getRendiment() == null) {
					errors.rejectValue(
							"rendiments[" + i + "].rendiment",
							"error.rendiment.buit",
							"El rendiment no pot ser buit");
				}
			}
		} catch (Exception ex) {
			logger.error("Error validant els rendiments. ", ex);
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