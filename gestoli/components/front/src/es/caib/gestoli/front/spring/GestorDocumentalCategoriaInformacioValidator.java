/**
 * DipositValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 */
public class GestorDocumentalCategoriaInformacioValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(GestorDocumentalCategoriaInformacioValidator.class);
	
	private OliQualitatEjb oliQualitatEjb;

	private HibernateTemplate hibernateTemplate;
	


	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(GestorDocumentalCategoriaInformacioCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
		"El camp Nom no pot estar buit");

		GestorDocumentalCategoriaInformacioCommand cat = (GestorDocumentalCategoriaInformacioCommand) obj;

		try {
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());

			if (cat.getNom()!=null && oliQualitatEjb.existeOtraCategoriaConNombre(cat.getNom(),cat.getId())){
				errors.rejectValue("nom", "error.nom.duplicat", "Ja existeix una categoria amb aquest nom");
			}
	
		} catch (RemoteException e) {
			logger.error("EXCEPTION", e);
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

	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}
	
}