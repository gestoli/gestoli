/**
 * FincasValidator.java
 *
 * Creada el 25 de març de 2009
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
 */
public class FincasValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(FincasValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;



	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(FincasCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		FincasCommand command = (FincasCommand)obj;
		if ((command.getFincaBaixaId() == null) && ((command.getNom() == null) || (command.getNom().equals("")))) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"nom",
					"error.nom.buit",
			"El camp nom no pot estar buit");
		}

		FincasCommand finca = (FincasCommand) obj;

		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (oliInfraestructuraEjb.existeNombreFinca(finca.getNom(), finca.getIdOlivicultor(),finca.getId())){
				errors.rejectValue("nom", "error.nomFinca.repetit", "El nom de la finca ja existeix");
			}
		} catch (Exception ex) {
			logger.error("Error validant l'usuari. UsuariValidator", ex);
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
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	
}
