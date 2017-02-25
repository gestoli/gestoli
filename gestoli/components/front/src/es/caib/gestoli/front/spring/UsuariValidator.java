/**
 * UsuariValidator.java
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

import es.caib.gestoli.logic.util.Constants;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Idioma;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class UsuariValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(UsuariValidator.class);
	
	private String rolDoControl;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;


	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(UsuariCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(
				errors,
				"usuari",
				"error.usuari.buit",
				"El camp usuari no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"contrasenya",
				"error.contrasenya.buit",
				"El camp contrasenya no pot estar buit");
				
//		ValidationUtils.rejectIfEmpty(
//				errors,
//				"grupsArray",
//				"error.grup.buit",
//				"El camp grup no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idiomaId",
				"error.idioma.buit",
				"El camp idioma no pot estar buit");


		UsuariCommand usuario = (UsuariCommand) obj;
		if (usuario.getIdiomaId() != null) {
			Idioma idioma = new Idioma();
			idioma.setId(usuario.getIdiomaId());
			usuario.setIdioma(idioma);
		}
		
 		// Validamos que si es rolDoControl tiene informado el email
//		boolean grupoControlador = true; 
//		if (usuario.getGrupsArray() != null && usuario.getGrupsArray().length > 0) {
//			for (int i = 0; i < usuario.getGrupsArray().length; i++) {
//				if (usuario.getGrupsArray()[i].equals(rolDoControl)) {
//					grupoControlador = false;
//					break;
//				}
//			}
//		}
		// Validamos formatos
		boolean esEmail = true;
		if (usuario.getEmail() != null && !usuario.getEmail().equals("")){
			esEmail = Util.isEmail(usuario.getEmail());			
		}

		
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (oliInfraestructuraEjb.existeNombre(usuario.getUsuari(), usuario.getId())){
				errors.rejectValue("usuari", "error.usuari.repetit", "El nom d'usuari ja existeix");
			}
			// Validamos formatos
			if (!esEmail) {
				errors.rejectValue("email", "error.email.incorrecto" ,"Escrigui el email correctament");
			}
			// Validamos que si es rolDoControl tiene informado el email
//			if (!grupoControlador && (usuario.getEmail() == null || usuario.getEmail().equals(""))) {
			if (usuario.getTipusUsuari().equals(Constants.DO_CONTROL) && (usuario.getEmail() == null || usuario.getEmail().equals(""))) {
				errors.rejectValue("email", "error.email.buit" ,"El camp email no pot estar buit");
			}

		} catch (Exception ex) {
			logger.error("Error validant l'usuari. UsuariValidator", ex);
		}

	}


	/**
	 * Injecció de la dependència rolDoControl
	 * @param rolDoControl La classe a injectar.
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
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