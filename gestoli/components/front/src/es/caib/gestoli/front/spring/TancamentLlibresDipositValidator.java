/**
 * DipositValidator.java
 *
 * Creada el 27 de novembre de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class TancamentLlibresDipositValidator implements Validator {
	private static Logger logger = Logger.getLogger(TancamentLlibresDipositValidator.class);

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
		return clazz.equals(DipositCommand.class);
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
				"idCategoriaOli",
				"error.categoriaOli.buit",
				"Ha de seleccionar una categoria d'oli");
		
		// OB 20091202  -  No ho posem perque ho miren als llibres físics
//		ValidationUtils.rejectIfEmpty(
//				errors,
//				"varietatsOlivaArray",
//				"error.varietatOliva.buit",
//				"El camp varietat olives seleccionades no pot estar buit");
//		
//		ValidationUtils.rejectIfEmpty(
//				errors,
//				"idVarietatOli",
//				"error.varietatOli.buit",
//				"Ha de seleccionar una varietat d'oli");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"acidesa",
				"error.acidesa.buit",
				"El camp acidesa no pot estar buit");
				
		try {
			
			// Comprobar que los litros no superan la cantidad del depósito
			DipositCommand dipCommand = (DipositCommand) obj;
			
//			if (dipCommand.getLitros().doubleValue() > dipCommand.getVolumActual().doubleValue()) {
//				errors.rejectValue("numeroBotellesInicials", "error.numeroBotelles.incorrecto" ,"No s'ha omplit cap botella per a l'elaboració del lot");
//			}
			
			if (dipCommand.getEstabliment().getUnitatMesura().equals("l")) {
				if (dipCommand.getLitros() != null && dipCommand.getLitros().doubleValue() < 0){
					errors.rejectValue("litros", "error.litros.negativo","El numero de litres introduits ha de ser major que 0");
				} else {
					ValidationUtils.rejectIfEmpty(
							errors,
							"litros",
							"error.litros.buit",
							"S'ha d'introduir la quantitat introduïda dins el dipòsit");
				}
			}else{
				if (dipCommand.getKilos() != null && dipCommand.getKilos().doubleValue() < 0){
					errors.rejectValue("kilos", "error.kilos.negativo","El numero de kilos introduits ha de ser major que 0");
				}else{
					ValidationUtils.rejectIfEmpty(
							errors,
							"kilos",
							"error.litros.buit",
							"S'ha d'introduir la quantitat introduïda dins el dipòsit");
				}
				
			}

			if (dipCommand.getCapacitat() != null) {
				
				if ((dipCommand.getLitros()!=null?dipCommand.getLitros().doubleValue():new Double(0).doubleValue()) > dipCommand.getCapacitat().doubleValue()) {
					Object[] valor = new Object[1];
					if (dipCommand.getEstabliment().getUnitatMesura().equals("l")) {
						valor[0] =  String.valueOf(dipCommand.getCapacitat().doubleValue());
						errors.rejectValue("litros", "error.litros.incorrecto", valor,"Els litres que es volen introduïr superen els que hi caben actualment: {0}");
					}else{
						valor[0] =  String.valueOf(dipCommand.getCapacitat().doubleValue() * 0.916);
						errors.rejectValue("kilos", "error.kilos.incorrecto", valor,"Els quilos que es volen introduïr superen els que hi caben actualment: {0}");
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
