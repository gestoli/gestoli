/**
 * TaxaValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class TaxaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(TaxaValidator.class);
	

	/**
	 * Indica las clases que soporta este validador.
	 *
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(TaxaCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {


		ValidationUtils.rejectIfEmpty(
				errors,
				"taxaPlantacioMajorIgual75",
				"error.taxamayor.buit",
				"El camp Quota plantació amb 75 o més anys no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"taxaPlantacioMenor75",
				"error.taxamenor.buit",
				"El camp Quota plantació amb menys de 75 anys no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"taxaVolumEnvasar",
				"error.volumenTaxa.buit",
				"El camp Quota volum a envasar no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"taxaContraEtiqueta",
				"error.etiqueta.buit",
				"El camp Quota contraetiqueta no pot estar buit");


		TaxaCommand taxa = (TaxaCommand) obj;
		

		boolean taxaMayorNumerico = (taxa.getTaxaPlantacioMajorIgual75()!= null && Pattern.matches("^(.*\\d+.*)$", taxa.getTaxaPlantacioMajorIgual75().toString()));
		boolean taxaMenorNumerico = (taxa.getTaxaPlantacioMenor75()!= null && Pattern.matches("^(.*\\d+.*)$", taxa.getTaxaPlantacioMenor75().toString()));
		boolean volumenNumerico = (taxa.getTaxaVolumEnvasar()!= null && Pattern.matches("^(.*\\d+.*)$", taxa.getTaxaVolumEnvasar().toString()));
		boolean etiquetaNumerico = (taxa.getTaxaContraEtiqueta()!= null && Pattern.matches("^(.*\\d+.*)$", taxa.getTaxaContraEtiqueta().toString()));
		
		boolean taxaMayorPositivo = (taxa.getTaxaPlantacioMajorIgual75()!= null && taxa.getTaxaPlantacioMajorIgual75().intValue() >= 0 );
		boolean taxaMenorPositivo = (taxa.getTaxaPlantacioMenor75()!= null && taxa.getTaxaPlantacioMenor75().intValue() >= 0);
		boolean volumenPositivo = (taxa.getTaxaVolumEnvasar()!= null && taxa.getTaxaVolumEnvasar().intValue() >= 0);
		boolean etiquetaPositivo = (taxa.getTaxaContraEtiqueta()!= null && taxa.getTaxaContraEtiqueta().intValue() >= 0);

		try {
			if (!taxaMayorNumerico){
				errors.rejectValue("taxaPlantacioMajorIgual75", "error.taxamayor.incorrecto" ,"Escrigui la Quota plantació amb 75 o més anys correctament");
			}
			
			if (!taxaMenorNumerico){
				errors.rejectValue("taxaPlantacioMenor75", "error.taxamenor.incorrecto" ,"Escrigui la Quota plantació amb menys de 75 anys correctament");
			}
			
			if (!volumenNumerico){
				errors.rejectValue("taxaVolumEnvasar", "error.volumenTaxa.incorrecto" ,"Escrigui la Quota volum a envasar correctament");
			}
			
			if (!etiquetaNumerico){
				errors.rejectValue("taxaContraEtiqueta", "error.etiqueta.incorrecto" ,"Escrigui la Quota contraetiqueta correctament");
			}
			
			if (!taxaMayorPositivo){
				errors.rejectValue("taxaPlantacioMajorIgual75", "error.positivo" ,"El valor ha de ser positiu");
			}
			
			if (!taxaMenorPositivo){
				errors.rejectValue("taxaPlantacioMenor75", "error.positivo" ,"El valor ha de ser positiu");
			}
			
			if (!volumenPositivo){
				errors.rejectValue("taxaVolumEnvasar", "error.positivo" ,"El valor ha de ser positiu");
			}
			
			if (!etiquetaPositivo){
				errors.rejectValue("taxaContraEtiqueta", "error.positivo" ,"El valor ha de ser positiu");
			}
			
		} catch (Exception ex) {
			logger.error("Error validando la taxa. TaxaValidator", ex);
		}


	}

}
