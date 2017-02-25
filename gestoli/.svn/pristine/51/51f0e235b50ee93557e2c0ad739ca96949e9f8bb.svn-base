/**
 * OlivicultorValidator.java
 *
 * 
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Usuari;


/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 * 
 */
public class EtiquetesOlivicultorsValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(EtiquetesOlivicultorsValidator.class);
	

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String campanyaSessionKey;

	private HibernateTemplate hibernateTemplate;
	
	
	public void setOliInfraestructuraEjb (OliInfraestructuraEjb oliInfraestructuraEjb){
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	



	/**
	 * Indica las clases que soporta este validador.
	 *
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(EtiquetesOlivicultorsCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		OlivicultorCommand olivicultor = (OlivicultorCommand) obj;

		try {
		}catch (Exception ex) {
			logger.error("Error validando al olivicultor. OlivicultorValidator", ex);

		}

	}
	
	 /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
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