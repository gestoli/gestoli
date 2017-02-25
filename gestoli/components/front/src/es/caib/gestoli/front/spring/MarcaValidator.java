/**
 * MarcaValidator.java
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
public class MarcaValidator implements Validator {
	private static Logger logger = Logger.getLogger(MarcaValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;

	public void setOliInfraestructuraEjb(
			OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public MarcaValidator() {
	}

	/**
	 * Indica las clases que soporta este validador.
	 * 
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(MarcaCommand.class);
	}

	/**
	 * Valida el objeto.
	 * 
	 * @see Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		validateEtiquetageActivo((MarcaCommand) obj, errors);
	}

	/**
	 * Valida el objeto.
	 * 
	 * @see Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validateFirstPage(MarcaCommand marca, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nom", "error.nom.buit",
				"El camp nom no pot estar buit");

		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (oliInfraestructuraEjb
					.existeMarca(marca.getNom(), marca.getId())) {
				errors.rejectValue("nom", "error.marca.repetit",
						"El nom de la marca d'oli ja existeix ");
			}
		} catch (Exception ex) {
			logger.error("Error validant la marca. MarcaValidator", ex);
		}

	}

	/**
	 * Valida el objeto.
	 * 
	 * @see Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validateSecondPage(MarcaCommand marca, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "tipusEnvasIdEtiquetatge",
				"error.tipusEnvas.buit",
				"El camp Tipus d'envàs no pot estar buit");
		//		
		// if((marca.getImatgeImatgeFrontal().length == 0 &&
		// marca.getImatgeFrontal() == null && marca.getArxiucImatgeFrontal() ==
		// null) || (marca.getImatgeImatgePosterior().length == 0 &&
		// marca.getImatgePosterior() == null &&
		// marca.getArxiucImatgePosterior() == null) ){
		// // errors.rejectValue("imatgeImatgePosterior", "error.imatges.buit"
		// ,"Tant la Imatge frontal com la Imatge posterior no poden estar buides");
		// errors.rejectValue("errorImagenes", "error.imatges.buit"
		// ,"Tant la Imatge frontal com la Imatge posterior no poden estar buides");
		// }

		if (marca.getImatgeImatgeFrontal().length == 0
				&& marca.getImatgeFrontal() == null
				&& marca.getArxiucImatgeFrontal() == null) {
			errors
					.rejectValue("imatgeImatgeFrontal", "error.imatges.buit",
							"Tant la Imatge frontal com la Imatge posterior no poden estar buides");
		}
		// if(marca.getImatgeImatgePosterior().length == 0 &&
		// marca.getImatgePosterior() == null &&
		// marca.getArxiucImatgePosterior() == null ) {
		// errors.rejectValue("imatgeImatgePosterior", "error.imatges.buit"
		// ,"Tant la Imatge frontal com la Imatge posterior no poden estar buides");
		// }

	}

	public void validateEtiquetageActivo(MarcaCommand marca, Errors errors) {

		if (marca.getEtiquetatgesList() != null
				&& marca.getEtiquetatgesList().size() > 0) {
			boolean algunEtiquetatgeActivo = false;
			for (int i = 0; i < marca.getEtiquetatgesList().size(); i++) {
				EtiquetatgeCommand etiquetatgeCommand = (EtiquetatgeCommand) marca
						.getEtiquetatgesList().get(i);
				if (etiquetatgeCommand.getActiu().booleanValue()) {
					algunEtiquetatgeActivo = true;
					break;
				}
			}
			if (!algunEtiquetatgeActivo) {
				errors
						.rejectValue("nom", "error.etiquetatges.repetit",
								"La marca d'oli ha de tenir associat algún etiquetatge actiu");
			}
		} else {
			errors
					.rejectValue("nom", "error.etiquetatges.repetit",
							"La marca d'oli ha de tenir associat algún etiquetatge actiu");
		}
	}

	public void validateEstablecimientosEnvasadoresActivo(MarcaCommand marca,
			Errors errors, Long campanyaId,
			Integer tipusEstablimentTafonaEnvasadora,
			Integer tipusEstablimentEnvasadora) {
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Boolean existenEnvasadoresActivos = oliInfraestructuraEjb
					.getExistenEnvasadoresActivoByMarca(marca.getId(),
							campanyaId, tipusEstablimentTafonaEnvasadora,
							tipusEstablimentEnvasadora);
			if (existenEnvasadoresActivos.booleanValue()) {
				errors
						.rejectValue(
								"actiu",
								"error.actiu.actiu",
								"La marca d'oli ha de romandre activa ja que està associada a establiments amb envasadora");

			}
		} catch (Exception e) {
			logger.error("Error validant la marca. MarcaValidator", e);
		}

	}

	/**
	 * set the hibernate template.
	 * 
	 * @param hibernateTemplate
	 *            the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * get the hibernate template.
	 * 
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

}
