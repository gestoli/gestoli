/**
 * TaxaValidator.java
 *
 * Creada el 13 de maig de 2009
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
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class CampanyaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(CampanyaValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;


	/**
	 * Indica las clases que soporta este validador.
	 *
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(CampanyaCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
				"El camp Nom no pot estar buit");

		CampanyaCommand campanya = (CampanyaCommand)obj;
		try {
			if (campanya.getNom() != null && !campanya.getNom().equals("")) {
				// Validacion de que no exista otra campaña con el mismo nombre
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if (oliInfraestructuraEjb.existeCampanyaMismoNombre(campanya.getNom())) {
					errors.rejectValue("nom", "error.campanya.nomRepetit" ,"Ja existeix una campanya amb el mateix nom");
				}
			}
		} catch (Exception ex) {
			logger.error("Error validando nueva campaña. ", ex);
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