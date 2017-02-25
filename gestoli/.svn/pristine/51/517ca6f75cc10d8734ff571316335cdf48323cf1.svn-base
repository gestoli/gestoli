/**
 * ProcesElaboracioOlivaValidator.java
 *
 * Creada el 19 de juny de 2009
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
import es.caib.gestoli.logic.model.PartidaFonoll;

/**
 * Validador para los campos asociados con el formulario de creaciÃ³n/ediciÃ³n.
 */
public class ProcesElaboracioOlivaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesElaboracioOlivaValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
//	private String desqualificat;
//    private String pendentQualificar;
    
	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesElaboracioOlivaCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesElaboracioOlivaCommand elaboracioOlivaCommand = (ProcesElaboracioOlivaCommand) obj;
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.dataElaboracio.buit",
				"El camp Data elaboracio no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idBota",
				"error.salmorra.buit",
				"El camp identificador no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"concentracioSalmorra",
				"error.salmorra.buit",
				"El camp salmorra no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"lotSal",
				"error.lotSal.buit",
				"El camp lot sal no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"kgOliva",
				"error.lotSal.buit",
				"El camp kg d'oliva no pot estar buit");
		
		if (elaboracioOlivaCommand.getKgBota() > elaboracioOlivaCommand.getKgMaxim()) {
			Object[] valor = new Object[1];
			valor[0] = elaboracioOlivaCommand.getKgMaxim();
			errors.rejectValue(
					"kgOliva", 
					"error.partidaOliva.insuficient", 
					valor,
					"Aquestes partides només disposen de {0} Kg d'oliva");
		}
		
		if (elaboracioOlivaCommand.getTipusOlivaTaula()==1) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"gFonoll",
					"error.lotSal.buit",
					"El camp quantitat de fonoll no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"gPebre",
					"error.lotSal.buit",
					"El camp quantitat de pebre no pot estar buit");
			ValidationUtils.rejectIfEmpty(
					errors,
					"lotPebre",
					"error.lotSal.buit",
					"El camp lot pebre no pot estar buit");
			if (elaboracioOlivaCommand.getFonollPropi()==true) {
				ValidationUtils.rejectIfEmpty(
						errors,
						"partidaFonollId",
						"error.lotSal.buit",
						"El camp partida fonoll no pot estar buit");
				try {
					oliInfraestructuraEjb.setHibernateTemplate(hibernateTemplate);
					PartidaFonoll pf = oliInfraestructuraEjb.partidaFonollAmbId(elaboracioOlivaCommand.getPartidaFonollId());
					if (pf.getKgRestants()*1000<elaboracioOlivaCommand.getgFonoll()) {
						Object[] valor = new Object[1];
						valor[0] = pf.getKgRestants()*1000;
						errors.rejectValue(
								"partidaFonollId", 
								"error.partidaFonoll.insuficient", 
								valor,
								"Aquesta partida només disposa de {0}g de fonoll");
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			} else {
				ValidationUtils.rejectIfEmpty(
						errors,
						"lotFonoll",
						"error.lotSal.buit",
						"El camp lot fonoll no pot estar buit");
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