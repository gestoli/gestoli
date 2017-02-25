/**
 * ProcesEnvasatOlivaValidator.java
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
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Diposit;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesEnvasatOlivaValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesEnvasatOlivaValidator.class);

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
		return clazz.equals(ProcesEnvasatOlivaCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesEnvasatOlivaCommand envasatOlivaCommand = (ProcesEnvasatOlivaCommand) obj;
		
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
		
		if (envasatOlivaCommand.getpH1() == null) 
			ValidationUtils.rejectIfEmpty(
					errors,
					"pH1",
					"error.lotSal.buit",
					"El camp comprovació pH no pot estar buit");
		else if (envasatOlivaCommand.getpH1() > 4.3) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"quantitatAcidCitric",
					"error.lotSal.buit",
					"El camp 'quantitat d'àcid cítric' no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"lotAcidCitric",
					"error.lotSal.buit",
					"El camp 'lot àcid cítric' no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"pH2",
					"error.lotSal.buit",
					"El camp 'comprovació final de pH' no pot estar buit");
			if (envasatOlivaCommand.getpH2() == null) 
				ValidationUtils.rejectIfEmpty(
						errors,
						"pH2",
						"error.lotSal.buit",
						"El camp comprovació pH no pot estar buit");
			else if (envasatOlivaCommand.getpH2()>4.3) {
				 errors.rejectValue("pH2", "error.lotSal.buit");
			}
			
		}
		
		try {
			ProcesEnvasatOlivaCommand plc = (ProcesEnvasatOlivaCommand) obj;
			
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
			
			Bota bota = null;
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			bota = oliInfraestructuraEjb.botaAmbId(plc.getBota().getId());
			
			if (plc.getKgOlivaTaula() > bota.getKgOlivaRestant())
				errors.rejectValue("kgOlivaTaula", "error.lotSal.buit", "Els kg envassats superen els que hi ha actualment: {0}");
					
			if (plc.getDiposit() != null) {
				Diposit diposit = null;
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposit = oliInfraestructuraEjb.dipositAmbId(plc.getDiposit().getId());
				
				if (diposit!= null) {
					if (plc.getTotalOliAfegit()>diposit.getVolumActual())
						errors.rejectValue("totalOliAfegit", "error.lotSal.buit", "Els litres d'oli afegits superen els que hi ha actualment: {0}");
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
