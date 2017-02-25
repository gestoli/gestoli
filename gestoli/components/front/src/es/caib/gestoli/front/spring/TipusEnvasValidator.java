/**
 * TipusEnvas.java
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
import es.caib.gestoli.logic.model.Color;
import es.caib.gestoli.logic.model.MaterialTipusEnvas;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class TipusEnvasValidator implements Validator {

	private static Logger logger = Logger.getLogger(TipusEnvasValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(TipusEnvasCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(
				errors,
				"volum",
				"error.volum.buit",
				"El camp volum no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"materialTipusEnvasId",
				"error.material.buit",
				"El camp material no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"colorId",
				"error.color.buit",
				"El camp color no pot estar buit");
		

		TipusEnvasCommand tipusEnvas = (TipusEnvasCommand)obj;
		if (tipusEnvas.getMaterialTipusEnvasId() != null) {
			MaterialTipusEnvas materialTipusEnvas = new MaterialTipusEnvas();
			materialTipusEnvas.setId(Integer.valueOf(tipusEnvas.getMaterialTipusEnvasId().toString()));
			tipusEnvas.setMaterialTipusEnvas(materialTipusEnvas);
		}
		if (tipusEnvas.getColorId() != null) {
			Color color = new Color();
			color.setId(Integer.valueOf(tipusEnvas.getColorId().toString()));
			tipusEnvas.setColor(color);
		}

		try {
			// Validacion de que no exista otro tipo de envase igual
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (oliInfraestructuraEjb.existeTipusEnvas(tipusEnvas.getMaterialTipusEnvasId(), tipusEnvas.getColorId(), tipusEnvas.getVolum(), tipusEnvas.getId())) {
				errors.rejectValue("volum", "error.envase.repetit" ,"El tipus d'envàs ja existeix");
				errors.rejectValue("materialTipusEnvasId", "error.envase.repetit" ,"El tipus d'envàs ja existeix");
				errors.rejectValue("colorId", "error.envase.repetit" ,"El tipus d'envàs ja existeix");
			}
			// Validacion de que si se modifica de activo a no activo, no debe tener ningún etiquetaje ACTIVO asociado
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (tipusEnvas.getActiu() == null && oliInfraestructuraEjb.existenEtiquetatgesActiusEnTipusEnvas(tipusEnvas.getId())) {
				errors.rejectValue("actiu", "error.envase.etiquetatgesActiusAssociats" ,"El tipus d'envàs no es pot desactivar ja que té etiquetatges actius associats");
			}
			if (tipusEnvas.getVolum() != null && tipusEnvas.getVolum().doubleValue() <= 0 ) {
				errors.rejectValue("volum", "error.envase.volum" ,"El volum ha de ser major que 0");
			}
		} catch (Exception ex) {
			logger.error("Error validando tipo envase. TipusEnvas", ex);
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