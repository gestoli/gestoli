/**
 * LotValidator.java
 *
 * Creada el 27 de novembre de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.util.Constants;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class TancamentLlibresLotValidator implements Validator {
	private static Logger logger = Logger.getLogger(TancamentLlibresLotValidator.class);

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
		return clazz.equals(ProcesLotCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.data.buit",
				"El camp Data no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idMarca",
				"error.marcasArray.buit",
				"El camp marca no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idEtiquetatge",
				"error.etiquetatgeLot.buit",
				"El camp etiquetatge no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idZona",
				"error.zona.buit",
				"El camp zona no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idPartidaOli",
				"error.partidaOli.buit",
				"Ha de seleccionar una partida d'oli");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"codiAssignat",
				"error.nomLot.buit",
				"El camp nom del lot no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"varietatsArray",
				"error.varietatOliva.buit",
				"El camp varietat olives seleccionades no pot estar buit");
		
		try {
			ProcesLotCommand plc = (ProcesLotCommand) obj;
			if (plc.getId() != null) {
				ValidationUtils.rejectIfEmpty(
						errors,
						"numeroBotellesInicials",
						"error.numeroBotelles.incorrecto",
						"No s'ha omplit cap botella per a l'elaboració del lot");
				
				ValidationUtils.rejectIfEmpty(
						errors,
						"acidesa",
						"error.acidesa.buit",
						"El camp acidesa no pot estar buit");

				if (plc.getEtiquetar() != null && plc.getEtiquetar().booleanValue()) {
					ValidationUtils.rejectIfEmpty(
							errors,
							"numeroEtiquetaInicial",
							"error.numeroEtiquetaInicial.buit",
					"El camp rang inicial no pot estar buit");

					ValidationUtils.rejectIfEmpty(
							errors,
							"numeroEtiquetaFinal",
							"error.numeroEtiquetaFinal.buit",
					"El camp rang final no pot estar buit");
							

				}

				if (plc.getNumeroBotellesInicials() != null && plc.getNumeroBotellesInicials().intValue() < 1) {
					errors.rejectValue("numeroBotellesInicials", "error.numeroBotelles.negativo" ,"El numero de botelles inicials introduïdes ha de ser major que 0");
				}
			}
			
			PartidaOli partidaOli = null;
			if (plc.getIdPartidaOli() != null){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				partidaOli = oliInfraestructuraEjb.getPartidaOliById(plc.getIdPartidaOli());
			}
			
			if (plc.getVarietatsArray() != null && plc.getVarietatsArray().length > 0){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				for(Integer var :  plc.getVarietatsArray()){
					VarietatOliva varietat = oliInfraestructuraEjb.variedadAmbId(var);
					if ((varietat.getExperimental()!= null && varietat.getExperimental().booleanValue()) || varietat.getNom().equals("Experimental")){
						if (partidaOli != null && partidaOli.getCategoriaOli().getId() != Constants.CATEGORIA_NO_DO){
							errors.rejectValue("idPartidaOli", "error.partidaOli.varietats.experimentals" ,"Si es seleccionen varietats experimentals, la partida d'oli ha de ser NO DO");
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
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
}
