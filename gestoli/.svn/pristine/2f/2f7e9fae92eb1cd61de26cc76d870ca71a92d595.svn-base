/**
 * AnaliticaValidator.java
 *
 * Creada el 3 de juliol de 2009
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
 */
public class AutocontrolValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(AutocontrolValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(AutocontrolCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		try{
			AutocontrolCommand autocontrolCommand = (AutocontrolCommand)obj;
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"data",
					"error.data.buit",
					"El camp de la data no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"hora",
					"error.hora.buit",
					"El camp de la data no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"responsable",
					"error.responsable.buit",
					"El camp de la data no pot estar buit");
			
			
			
		} catch(Exception e){
			e.printStackTrace();
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
