/**
 * ProcesPerduesValidator.java
 *
 * Creada el 2 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

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
public class ProcesPerduesValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesPerduesValidator.class);
	
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
		return clazz.equals(ProcesPerduesCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesPerduesCommand PerduesCommand = (ProcesPerduesCommand) obj;
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.data.buit",
				"El camp Data no pot estar buit");
		
		
		try {
			// Validación depósitos origen
				Diposit diposit = null;
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposit = oliInfraestructuraEjb.dipositAmbId(PerduesCommand.getDiposit().getId());

				if (diposit.getEstabliment().getUnitatMesura().equals("l")) {
					if (PerduesCommand.getLitros() != null && PerduesCommand.getLitros().doubleValue()<=0){
						errors.rejectValue("litros", "error.litros.negativo","El numero de litres introduits ha de ser positiu");
					}else{
						ValidationUtils.rejectIfEmpty(
								errors,
								"litros",
								"error.litrosPerdidos.buit",
								"S'ha d'introduir la quantitat peduda del dipòsit");
					}
				} else if (diposit.getEstabliment().getUnitatMesura().equals("k")) {
					if (PerduesCommand.getKilos() != null && PerduesCommand.getKilos().doubleValue()<=0){
						errors.rejectValue("kilos", "error.kilos.negativo","El numero de quilos introduits ha de ser positiu");
					}else{
						ValidationUtils.rejectIfEmpty(
								errors,
								"kilos",
								"error.litrosPerdidos.buit",
								"S'ha d'introduir la quantitat peduda del dipòsit");
					}
				}

				if (diposit.getVolumActual() != null) {
					if (PerduesCommand.getLitros() != null && PerduesCommand.getLitros().doubleValue() > diposit.getVolumActual().doubleValue()) {
						Object[] valor = new Object[1];
						
						if (diposit.getEstabliment().getUnitatMesura().equals("l")) {
							valor[0] =  String.valueOf(diposit.getVolumActual().doubleValue());
							errors.rejectValue("litros", "error.litrosPerdidos.incorrecto", valor,"Els litres perduts superen els que hi ha actualment: {0}");
						} else if (diposit.getEstabliment().getUnitatMesura().equals("k")) {
							valor[0] =  String.valueOf(diposit.getVolumActual().doubleValue() * 0.916);
							errors.rejectValue("kilos", "error.kilosPerdidos.incorrecto", valor,"Els kilos perduts superen els que hi ha actualment: {0}");
						}
					}

				}
				
				//COMPROVEM DATA EXECUCIO
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if(!oliInfraestructuraEjb.esDataDipositCorrecte(PerduesCommand.getData(), PerduesCommand.getDiposit().getId())){
					errors.rejectValue(
							"data",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
				}
				
				//Comprovació campanya
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
				if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(PerduesCommand.getData())){
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