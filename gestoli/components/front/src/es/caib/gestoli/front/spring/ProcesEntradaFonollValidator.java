package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;

/**
* Validador para los campos asociados con el formulario de entrada d'Fonoll.
*/
public class ProcesEntradaFonollValidator implements Validator {

	private static Logger logger = Logger.getLogger(ProcesEntradaFonollValidator.class);

	private OliProcessosEjb oliProcessosEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;


	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesEntradaFonollCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesEntradaFonollCommand procesEntradaFonollCommand = (ProcesEntradaFonollCommand)obj;

		ValidationUtils.rejectIfEmpty(
				errors,
				"dataExecucio",
				"error.dataExecucio.buit",
				"El camp Data entrada no pot estar buit");

		ValidationUtils.rejectIfEmpty(
				errors,
				"hora",
				"error.hora.buit",
				"El camp Hora entrada no pot estar buit");

		ValidationUtils.rejectIfEmpty(
				errors,
				"kgInici",
				"error.quantitat.buit",
				"El camp Quantitat total de fonoll no pot estar buit");


		if ((procesEntradaFonollCommand.getKgInici() != null) && (procesEntradaFonollCommand.getKgInici().doubleValue() < 0)) {
			errors.rejectValue("quantitat", "error.quantitat.buit" ,"El camp Quantitat total d'Fonoll ha de ser major que zero");
		}

		boolean esHora = true;
		if ((procesEntradaFonollCommand.getHora() != null) && (!procesEntradaFonollCommand.getHora().equalsIgnoreCase(""))) {
			esHora = Util.isHora(procesEntradaFonollCommand.getHora());
		}
		if (!esHora) {
			errors.rejectValue("hora", "error.hora.incorrecto" ,"El format del camp no és el correcte");
		}

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
	
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
}