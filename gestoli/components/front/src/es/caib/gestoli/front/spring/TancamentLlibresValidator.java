/**
 * PrefacturaValidator.java
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
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.util.Constants;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 */
public class TancamentLlibresValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(TancamentLlibresLotValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(TancamentLlibresCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
	
		TancamentLlibresCommand tc = (TancamentLlibresCommand) obj;
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.data.buit",
				"El camp Data no pot estar buit");
	
		// DIPOSITS
		int index = 0;
		Double[] quantitat = tc.getLitros();
		
		for (Long diposit : tc.getDipositId()){
			
			// La quantitat és obligatòria
			ValidationUtils.rejectIfEmpty(
					errors,
					"litros[" + index + "]",
					"error.tancament.litros.buit",
					"El camp litros no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"kilos[" + index + "]",
					"error.tancament.kilos.buit",
					"El camp quilos no pot estar buit");
			
			if (quantitat != null && quantitat[index] != null) {
				if (quantitat[index] > 0) {
				
					ValidationUtils.rejectIfEmpty(
							errors,
							"acidesa[" + index + "]",
							"error.tancament.acidesa.buit",
							"El camp acidesa no pot estar buit");
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"idPartidaOli[" + index + "]",
							"error.tancament.partidaOli.buit",
							"El camp partida d'oli no pot estar buit");
				} else if(quantitat[index] < 0) {
					errors.rejectValue(
							"litros[" + index + "]",
							"error.tancament.litros.negatiu",
							"El camp litros no pot ser negatiu");
				}
				
			}
			index++;
		}
		
		// LOTS
		index = 0;
		Integer[] botellesInicials = tc.getNumeroBotellesInicials();
		Integer[] botellesActuals = tc.getNumeroBotellesActuals();
		Boolean[] etiquetar = tc.getEtiquetar();
		Long[] partidaOliId = tc.getIdPartidaOliLots();
		Integer[][] varietats = tc.getVarietatsId();
		
		for (Long lot : tc.getLotId()){
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"marcaId[" + index + "]",
					"error.tancament.marca.buit",
					"El camp marca d'oli no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"etiquetatgeId[" + index + "]",
					"error.tancament.etiquetatge.buit",
					"El camp etiquetatge no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"varietatsId[" + index + "]",
					"error.tancament.varietats.buit",
					"El camp varietats d'oliva no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"idPartidaOliLots[" + index + "]",
					"error.tancament.partidaOli.buit",
					"El camp partida d'oli no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"zonaId[" + index + "]",
					"error.tancament.zona.buit",
					"El camp zona no pot estar buit");

			ValidationUtils.rejectIfEmpty(
					errors,
					"numeroBotellesInicials[" + index + "]",
					"error.tancament.numeroBotelles.buit",
					"El camp numeroBotelles no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"numeroBotellesActuals[" + index + "]",
					"error.tancament.numeroBotelles.buit",
					"El nombre de botelles no pot estar buit");
			
			if (botellesInicials != null && botellesInicials[index] != null){
				if (botellesInicials[index] < 0){
					errors.rejectValue(
							"numeroBotellesInicials[" + index + "]", 
							"error.tancament.numeroBotelles.negatiu" ,
							"El nombre de botelles no pot ser negatiu");
				} else if (botellesInicials[index] == 0){
					errors.rejectValue(
							"numeroBotellesInicials[" + index + "]", 
							"error.tancament.numeroBotelles.zero" ,
							"No s'ha omplit cap botella");
				} else {
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"acidesaLots[" + index + "]",
							"error.tancament.acidesa.buit",
							"El camp acidesa no pot estar buit");
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"dataConsum[" + index + "]",
							"error.dataConsum.buit",
							"El camp data consum del lot no pot estar buit");
					
					if (botellesActuals != null){
						if (botellesActuals[index] < 0){
							errors.rejectValue(
									"numeroBotellesActuals[" + index + "]", 
									"error.tancament.numeroBotelles.negatiu" ,
									"El nombre de botelles no pot ser negatiu");
						}
						if ((botellesInicials[index] - botellesActuals[index]) < 0){
							errors.rejectValue(
									"numeroBotellesActuals[" + index + "]", 
									"error.tancament.numeroBotelles.final.gran" ,
									"El nombre de botelles actuals no pot ser superior a de botelles inicials");
						}
					}

				}

				try {			
				
					PartidaOli partidaOli = null;
					if (partidaOliId[index] != null){
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						partidaOli = oliInfraestructuraEjb.getPartidaOliById(partidaOliId[index]);
					}
					
					if (varietats[index] != null && varietats[index].length > 0){
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						for(Integer var :  varietats[index]){
							VarietatOliva varietat = oliInfraestructuraEjb.variedadAmbId(var);
							if ((varietat.getExperimental()!= null && varietat.getExperimental().booleanValue()) || varietat.getNom().equals("Experimental")){
								if (partidaOli != null && partidaOli.getCategoriaOli().getId() != Constants.CATEGORIA_NO_DO){
									errors.rejectValue(
											"idPartidaOliLots[" + index + "]", 
											"error.partidaOli.varietats.experimentals" ,
											"Si es seleccionen varietats experimentals, la partida d'oli ha de ser NO DO");
									break;
								}
							}
						}
					}
				
				} catch (Exception e) {
					logger.error("EXCEPTION", e);
				}
			}
			
			index++;
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
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
}
