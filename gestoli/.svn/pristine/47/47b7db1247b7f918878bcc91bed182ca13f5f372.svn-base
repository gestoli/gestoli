/**
 * ProcesLotValidator.java
 *
 * Creada el 6 de juliol de 2009
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
public class ProcesLotValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesLotValidator.class);

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
				"codiAssignat",
				"error.nomLot.buit",
				"El camp nom del lot no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"dataConsum",
				"error.dataConsum.buit",
				"El camp data consum del lot no pot estar buit");
		
		
		try {
			ProcesLotCommand plc = (ProcesLotCommand) obj;
			
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
			
			if (plc.getDiposit() != null) {
				Diposit diposit = null;
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposit = oliInfraestructuraEjb.dipositAmbId(plc.getDiposit().getId());
				
				String mesura = diposit.getEstabliment().getUnitatMesura().equals("l")?"litres":"kilos";
				String message = mesura.equals("litres")?"litros":mesura;
				ValidationUtils.rejectIfEmpty(
						errors,
						mesura+"Envassats",
						"error.quantitatAEnvassar.buit",
						"S'ha d'indicar la quantitat que es vol envassar");
				
				
				if (plc.getPerdua() != null && plc.getPerdua().booleanValue()) {
					ValidationUtils.rejectIfEmpty(
							errors,
							mesura+"Perduts",
							"error.quantitatPerduts.buit",
							"S'han d'indicar la quantitat que s'han perdut");
					
//					ValidationUtils.rejectIfEmpty(
//							errors,
//							"motiuPerdua",
//							"error.motiuPerdua.buit",
//							"S'ha d'indicar el motiu de la pèrdua");
					
					if(plc.getMotiuPerdua()== null || (plc.getMotiuPerdua()!= null && plc.getMotiuPerdua().equals(""))){
						errors.rejectValue("errorObservaciones", "error.motiuPerdua.buit" ,"S'ha d'indicar el motiu de la pèrdua");
					}
				}
				
				if (diposit.getVolumActual() != null) {
					Object[] valor = new Object[1];
					valor[0] =  String.valueOf(mesura.equals("litres")?diposit.getVolumActual().toString():numberDecimalFormat.format(diposit.getVolumActual().doubleValue() * 0.916));
					
					if (plc.getPerdua() != null && plc.getPerdua().booleanValue()) {
						if (plc.getLitresEnvassats() != null && plc.getLitresPerduts() != null) {
							if ((plc.getLitresEnvassats().doubleValue() + plc.getLitresPerduts().doubleValue()) > diposit.getVolumActual().doubleValue()) {
								errors.rejectValue(mesura+"Envassats", "error."+message+"Total.incorrecto", valor, "La suma de la quantitat perduda i la quantitat envassada supera la que hi ha actualment: {0}");
								errors.rejectValue(mesura+"Perduts", "error."+message+"Total.incorrecto", valor, "La suma de la quantitat perduda i la quantitat envassada supera la que hi ha actualment: {0}");
							}
						}
						
						if (plc.getLitresPerduts() != null) {
							if (plc.getLitresPerduts().doubleValue() > diposit.getVolumActual().doubleValue()) {
								errors.rejectValue(mesura+"Perduts", "error."+message+"Perdidos.incorrecto", valor, "Els "+mesura+" perduts superen els que hi ha actualment: {0}");
							}
						}
					}
					
					if (plc.getLitresEnvassats() != null) {
						if (plc.getLitresEnvassats().doubleValue() == 0) {
							errors.rejectValue(mesura+"Envassats", "error."+message+"Envassar.buit", valor, "S'ha d'indicar la quantitat d'oli per envassar");
						}
						if (plc.getLitresEnvassats().doubleValue() > diposit.getVolumActual().doubleValue()) {
							errors.rejectValue(mesura+"Envassats", "error."+message+"Envassar.incorrecto", valor, "Els "+mesura+" envassats superen els que hi ha actualment: {0}");
						}
					}
					
				}
			}
			
			if (plc.getNumeroBotellesInicials().intValue() < 1) {
				errors.rejectValue("numeroBotelles", "error.numeroBotelles.incorrecto" ,"No s'ha omplit cap botella per a l'elaboració del lot");
			}
			
			//COMPROVEM DATA EXECUCIO
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(!oliInfraestructuraEjb.esDataDipositCorrecte(plc.getData(), plc.getDiposit().getId())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(plc.getData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
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
