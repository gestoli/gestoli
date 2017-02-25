/**
 * AnaliticaValidator.java
 *
 * Creada el 3 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.AnaliticaParametre;
import es.caib.gestoli.logic.model.AnaliticaParametreTipus;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class AnaliticaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(AnaliticaValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(AnaliticaCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		try{
			AnaliticaCommand analiticaCommand = (AnaliticaCommand)obj;
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"data",
					"error.data.buit",
					"El camp de la data no pot estar buit");
			
			//ANALISI SENSORIAL
			ValidationUtils.rejectIfEmpty(
					errors,
					"analisiSensorialNomLaboratori",
					"error.nomLaboratori.buit",
					"El camp del nom del laboratori no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"analisiSensorialDataRecepcio",
					"error.dataRecepcio.buit",
					"El camp Data recepcio no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"analisiSensorialDataTast",
					"error.dataTast.buit",
					"El camp Data tast no pot estar buit");
			
			
			//Paràmetres sensorial
			if(analiticaCommand.getIdVarietatOli() != null && analiticaCommand.getIdVarietatOli() != 0){
				Long[] idsSensorial = analiticaCommand.getIdAnaliticaParametreTipusSensorial();
				
				for(int i=0; i<idsSensorial.length; i++){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					List<AnaliticaParametre> analiticaParametre = (List<AnaliticaParametre>)oliInfraestructuraEjb.analiticaParametreByParametreTipusIVarietat(idsSensorial[i],analiticaCommand.getIdVarietatOli());
					
					for(AnaliticaParametre parametre: analiticaParametre){
						if(parametre.getObligatori()){
							ValidationUtils.rejectIfEmpty(
									errors,
									"analiticaParametreTipusSensorial["+i+"]",
									"error.buit",
									"El camp no pot estar buit");
						}
					}
				}
			}
			
			
			
			//ANALISI FISICOQUIMIC
			ValidationUtils.rejectIfEmpty(
			errors,
			"analisiFisicoQuimicNomLaboratori",
			"error.nomLaboratori.buit",
			"El camp del nom del laboratori no pot estar buit");
	
			ValidationUtils.rejectIfEmpty(
			errors,
			"analisiFisicoQuimicDataRecepcio",
			"error.dataRecepcio.buit",
			"El camp Data recepcio no pot estar buit");
	
			ValidationUtils.rejectIfEmpty(
			errors,
			"analisiFisicoQuimicDataInici",
			"error.dataInici.buit",
			"El camp Data inici no pot estar buit");
	
			ValidationUtils.rejectIfEmpty(
			errors,
			"analisiFisicoQuimicDataFi",
			"error.dataFi.buit",
			"El camp Data fi no pot estar buit");
			
			
			//Paràmetres fisico-químic
			if(analiticaCommand.getIdVarietatOli() != null && analiticaCommand.getIdVarietatOli() != 0){
				Long[] idsFisicoQuimic = analiticaCommand.getIdAnaliticaParametreTipusFisicoQuimic();
				
				for(int i=0; i<idsFisicoQuimic.length; i++){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					List<AnaliticaParametre> analiticaParametre = (List<AnaliticaParametre>)oliInfraestructuraEjb.analiticaParametreByParametreTipusIVarietat(idsFisicoQuimic[i],analiticaCommand.getIdVarietatOli());
					
					for(AnaliticaParametre parametre: analiticaParametre){
						if(parametre.getObligatori()){
							ValidationUtils.rejectIfEmpty(
									errors,
									"analiticaParametreTipusFisicoQuimic["+i+"]",
									"error.buit",
									"El camp no pot estar buit");
						}
					}
				}
			}
			
			//VARIETAT
			if( analiticaCommand.getIdVarietatOli() == null || 
				analiticaCommand.getIdVarietatOli() == 0){
				errors.rejectValue("nomVarietatOli", "error.varietat.desconeguda");
			}
			
			//PARTIDA
			if(analiticaCommand.getIdPartidaOli() == null){
				errors.rejectValue(
							"idPartidaOli",
							"error.partidaOli.buit",
							"El camp Partida d'oli no pot estar buit");
			} else {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if(!oliInfraestructuraEjb.partidaCorrecteAnalitica(analiticaCommand.getIdPartidaOli(),analiticaCommand.getIdDiposit())){
					errors.rejectValue(
							"idPartidaOli",
							"error.partidaOli.existeix",
							"La partida de aceite ya existe en otro deposito.");
				}
			}
			
			//COMPROVEM DATA EXECUCIO
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(!oliInfraestructuraEjb.esDataDipositCorrecte(analiticaCommand.getData(), analiticaCommand.getIdDiposit())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
		
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(analiticaCommand.getData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
		} catch(Exception e){
			e.printStackTrace();
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
