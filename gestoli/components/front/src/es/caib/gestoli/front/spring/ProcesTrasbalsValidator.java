/**
 * ProcesTrasbalsValidator.java
 *
 * Creada el 2 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.PartidaOliva;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesTrasbalsValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesTrasbalsValidator.class);
	
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
		return clazz.equals(ProcesTrasbalsCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesTrasbalsCommand trasbalsCommand = (ProcesTrasbalsCommand) obj;
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.dataElaboracio.buit",
				"El camp Data elaboracio no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"acidesa",
				"error.acidesa.buit",
				"El camp acidesa no pot estar buit");
		
		try {
			// Validación depósitos origen
			Diposit diposit = null;
			for (int i = 0; i < trasbalsCommand.getDipositsOrigen().length; i++) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposit = oliInfraestructuraEjb.dipositAmbId(trasbalsCommand.getDipositsOrigen()[i].getId());

				if (diposit.getEstabliment().getUnitatMesura().equals("l")) {
					if (trasbalsCommand.getLitros()[i].doubleValue()<=0){
						errors.rejectValue("litros["+i+"]", "error.litresTrasbalsNegatiu","El numero de litres introduits ha de ser major que 0");
					}else{
						ValidationUtils.rejectIfEmpty(
								errors,
								"litros["+i+"]",
								"error.litrosExtreure.buit",
								"S'ha d'introduir la quantitat retirada del dipòsit");
					}
				} else if (diposit.getEstabliment().getUnitatMesura().equals("k")) {
					if (trasbalsCommand.getKilos()[i].doubleValue()<=0){
						errors.rejectValue("kilos["+i+"]", "error.kilosTrasbalsNegatiu","El numero de kilos introduits ha de ser major que 0");
					}else{
						ValidationUtils.rejectIfEmpty(
								errors,
								"kilos["+i+"]",
								"error.litrosExtreure.buit",
								"S'ha d'introduir la quantitat retirada del dipòsit");
					}
				}

				if (diposit.getVolumActual() != null) {
					if (trasbalsCommand.getLitros()[i] != null && trasbalsCommand.getLitros()[i].doubleValue() > diposit.getVolumActual().doubleValue()) {
						Object[] valor = new Object[1];
						
						if (diposit.getEstabliment().getUnitatMesura().equals("l")) {
							valor[0] =  String.valueOf(diposit.getVolumActual().doubleValue());
							errors.rejectValue("litros["+i+"]", "error.litrosExtreure.incorrecto", valor,"Els litres que es volen extreure superen els que hi ha actualment: {0}");
						} else if (diposit.getEstabliment().getUnitatMesura().equals("k")) {
							valor[0] =  String.valueOf(diposit.getVolumActual().doubleValue() * 0.916);
							errors.rejectValue("kilos["+i+"]", "error.kilosExtreure.incorrecto", valor,"Els kilos que es volen extreure superen els que hi ha actualment: {0}");
						}
					}

				}
			}
			
			// Validación depósitos destino
			if (diposit.getEstabliment().getUnitatMesura().equals("l")) {
				ValidationUtils.rejectIfEmpty(
						errors,
						"litrosAfegits",
						"error.litros.buit",
						"S'ha d'introduir la quantitat introduïda dins el dipòsit");
			} else if (diposit.getEstabliment().getUnitatMesura().equals("k")) {
				ValidationUtils.rejectIfEmpty(
						errors,
						"kilosAfegits",
						"error.litros.buit",
						"S'ha d'introduir la quantitat introduïda dins el dipòsit");
			}
			if (trasbalsCommand.getLitrosAfegits() != null && trasbalsCommand.getLitrosAfegits().doubleValue() > 0) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit dipositDesti = oliInfraestructuraEjb.dipositAmbId(trasbalsCommand.getDipositDesti().getId());
				
				double capacitatActual = dipositDesti.getCapacitat().doubleValue() - (dipositDesti.getVolumActual()!=null?dipositDesti.getVolumActual().doubleValue():new Double(0).doubleValue());
				
				if (trasbalsCommand.getLitrosAfegits().doubleValue() > capacitatActual) {
					Object[] valor = new Object[1];
					if (dipositDesti.getEstabliment().getUnitatMesura().equals("l")) {
						valor[0] =  String.valueOf(capacitatActual);
						errors.rejectValue("litrosAfegits", "error.litros.incorrecto", valor,"Els litres que es volen introduïr superen els que hi caben actualment: {0}");
					} else if (dipositDesti.getEstabliment().getUnitatMesura().equals("k")) {
						valor[0] =  String.valueOf(capacitatActual * 0.916);
						errors.rejectValue("kilosAfegits", "error.kilos.incorrecto", valor,"Els kilos que es volen introduïr superen els que hi caben actualment: {0}");
					}
				}
			}
			
			//COMPROVEM DATA EXECUCIO
			//Diposit desti
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataDipositCorrecte(trasbalsCommand.getData(), trasbalsCommand.getDipositDesti().getId())){
					errors.rejectValue(
							"data",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
			}
			
			//Diposits origen
			for(int i=0; i<trasbalsCommand.getDipositsOrigen().length; i++){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
				if(!oliInfraestructuraEjb.esDataDipositCorrecte(trasbalsCommand.getData(), trasbalsCommand.getDipositsOrigen()[i].getId())){
					errors.rejectValue(
							"data",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
				}
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(trasbalsCommand.getData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
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