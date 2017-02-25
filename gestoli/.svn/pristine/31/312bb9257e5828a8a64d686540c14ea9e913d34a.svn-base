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
public class OlivicultorValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(OlivicultorValidator.class);
	

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
		return clazz.equals(OlivicultorCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		

		ValidationUtils.rejectIfEmpty(
				errors,
				"nomUsuari",
				"error.usuari.buit",
				"El camp usuari no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"contrasenya",
				"error.contrasenya.buit",
				"El camp contrasenya no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idiomaId",
				"error.idioma.buit",
				"El camp idioma no pot estar buit");
		
		// L'usuari es crea al mateix moment que l'olivicultor.
		// Per tant no fa falta que ja tengui un id d'usuari 
//		ValidationUtils.rejectIfEmpty(
//				errors,
//				"usuariId",
//				"error.usuari.buit",
//				"El camp usuari no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
				"El camp nom no pot estar buit");	
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"nif",
				"error.nif.buit",
				"El camp nif no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"direccio",
				"error.direccio.buit",
				"El camp direccio no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"municipiId",
				"error.poblacio.buit",
				"El camp poblacio no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"codiPostal",
				"error.codiPostal.buit",
				"El camp codiPostal no pot estar buit");
		
		/*ValidationUtils.rejectIfEmpty(
				errors,
				"compteBancari",
				"error.compteBancari.buit",
				"El camp compteBancari no pot estar buit");*/
		
				
		
		OlivicultorCommand olivicultor = (OlivicultorCommand) obj;

		boolean esEmail = false;
		if(olivicultor.getEmail()!= null && !olivicultor.getEmail().equalsIgnoreCase("")){
			esEmail = Util.isEmail(olivicultor.getEmail());			
		}
		boolean telefono = (olivicultor.getTelefon()!= null && Util.isNumerico(olivicultor.getTelefon()));
		boolean cpostal = (olivicultor.getCodiPostal()!= null && Util.isNumerico(olivicultor.getCodiPostal()));
		boolean fax = (olivicultor.getFax()!= null && Util.isNumerico(olivicultor.getFax()));
		boolean nif = (olivicultor.getNif()!= null && Util.isNif(olivicultor.getNif()));
		boolean compteBancaria = (olivicultor.getCompteBancari()!= null && !olivicultor.getCompteBancari().equals(""))?Util.isNumerico(olivicultor.getCompteBancari()):true;

		
		if (olivicultor.getUsuariId() != null) {
			Usuari usuari = new Usuari();
			usuari.setId(olivicultor.getUsuariId());
			olivicultor.setUsuari(usuari);
		}
		
		
		
		try {

			// Validamos que el usuario no  ha sido asignado a ningun otro olivicultor
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (olivicultor.getUsuariId()!= null && oliInfraestructuraEjb.usuarioAsignadoOlivicultor(olivicultor.getCampanyaId(), olivicultor.getUsuariId(),olivicultor.getId())){
				errors.rejectValue("usuariId", "error.usuari.repetitEstabliment" ,"L'usuari ja ha estat assignat a un altre olivicultor");
			}
			// Validamos que el nombre del olivicultor y su nif no existen ya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (olivicultor.getNif()!= null && olivicultor.getNom()!= null && oliInfraestructuraEjb.existeNifName(olivicultor.getCampanyaId(),olivicultor.getNif(), olivicultor.getNom(),olivicultor.getId())){
				errors.rejectValue("nif", "error.olivicultor.repetit" ,"El nif del olivicultor ja existeix");
			}
			
			if (!esEmail && olivicultor.getEmail()!= null && !olivicultor.getEmail().equalsIgnoreCase("")){
				errors.rejectValue("email", "error.email.incorrecto" ,"Escrigui el email correctament");
			}
			if (!telefono && olivicultor.getTelefon()!= null && !olivicultor.getTelefon().equalsIgnoreCase("")){
				errors.rejectValue("telefon", "error.telefon.incorrecto" ,"Escrigui el telèfon correctament");
			}
			if (!fax && olivicultor.getFax()!= null && !olivicultor.getFax().equalsIgnoreCase("")){
				errors.rejectValue("fax", "error.fax.incorrecto" ,"Escrigui el fax correctament");
			}
			if (!cpostal){
				errors.rejectValue("codiPostal", "error.codiPostal.incorrecto" ,"Escrigui el codi postal correctament");
			}
//			if (!nif){
//				errors.rejectValue("nif", "error.nifSolicitant.incorrecto" ,"Escrigui el nif correctament");
//			}
			if (!compteBancaria){
				errors.rejectValue("compteBancari", "error.compteBancari.incorrecto" ,"Escrigui el compte bancari correctament");
			}
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