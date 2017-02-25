/**
 * ProcesMoureOliValidator.java
 *
 * Creada el 2 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;

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
public class ProcesMoureOliValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesMoureOliValidator.class);
	
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
		return clazz.equals(ProcesMoureOliCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ProcesMoureOliCommand moureOliCommand = (ProcesMoureOliCommand) obj;
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.dataPrevista.buit",
				"El camp Data prevista no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"establimentDesti",
				"error.establimentDesti.buit",
				"El camp establiment no pot estar buit");
		
		/* Limit - Joan */
		Double[] kilos = moureOliCommand.getKilos();
		Double[] litres = moureOliCommand.getLitros();
		Diposit[] diposits = moureOliCommand.getDipositsOrigen();
		
		for(int i=0; i<diposits.length; i++){
			
			Diposit diposit;
			try {
				diposit = oliInfraestructuraEjb.dipositAmbId(diposits[i].getId());
			
				ValidationUtils.rejectIfEmptyOrWhitespace(
						errors,
						"kilos["+i+"]",
						"error.kilos.buit",
						"El camp kilos no pot estar buit");
				if(kilos[i] != null && kilos[i] <= 0){
					errors.rejectValue(
							"kilos["+i+"]",
							"error.kilos.negativo",
							"El camp kilos no pot ser negatiu");
				}
				if(kilos[i] != null && diposit != null && diposit.getDisponibleOliQuilos() != null && kilos[i] > diposit.getDisponibleOliQuilos()){
					errors.rejectValue(
							"kilos["+i+"]",
							"error.kilos.incorrecto",
							new Object[]{diposit.getDisponibleOliQuilos()},
							"El camp kilos supera els kilos disponibles: {0}");
				}
				
				ValidationUtils.rejectIfEmptyOrWhitespace(
						errors,
						"litros["+i+"]",
						"error.litros.buit",
						"El camp litres no pot estar buit");
				if(litres[i] != null && litres[i] <= 0){
					errors.rejectValue(
							"litros["+i+"]",
							"error.litros.negativo",
							"El camp litres no pot ser negatiu");
				}
				if(litres[i] != null && diposit != null && diposit.getVolumActual() != null && litres[i] > diposit.getVolumActual()){
					errors.rejectValue(
							"litros["+i+"]",
							"error.litros.incorrecto",
							new Object[]{diposit.getVolumActual()},
							"El camp litres supera els litres disponibles: {0}");
				}
			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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