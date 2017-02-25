/**
 * ZonaValidator.java
 *
 * Creada el 25 de marÃƒÆ’Ã‚Â§ de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * Validador para los campos asociados con el formulario de creaciÃƒÆ’Ã‚Â³n/ediciÃƒÆ’Ã‚Â³n.
 * 
 */
public class ProducteValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProducteValidator.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProducteCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ProducteCommand producte = (ProducteCommand) obj;

		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
		"El camp Nom no pot estar buit");

		// Comprovar que el nom no esta repetit en l'establiment
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (oliInfraestructuraEjb.existeixProducteEstabliment(producte.getId(), producte.getNom(), producte.getEstablimentId())){
				errors.rejectValue("nom", "error.producte.nom.repetit", "El nom del producte ja existeix");
			}
		} catch (Exception ex) {
			logger.error("Error validant el porducte. ProducteValidator", ex);
		}
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
}
