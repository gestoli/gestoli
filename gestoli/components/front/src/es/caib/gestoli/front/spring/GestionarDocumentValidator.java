/**
 * GestionarDocumentValidator.java
 *
 * Creada el 28 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class GestionarDocumentValidator implements Validator {
	
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(GestionarDocumentCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(
				errors,
				"titol",
				"error.titol.buit",
				"El camp titol no pot estar buit");
		
		GestionarDocumentCommand command = (GestionarDocumentCommand) obj;
		
		if (command.getArxiu() == null && (command.getFitxer() == null || (command.getFitxer() != null && command.getFitxer().length == 0))) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"fitxer",
					"error.fitxer.buit",
					"Ha de seleccionar un fixer");
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
}
