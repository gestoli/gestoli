
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus ConsultaOliComercialitzatLlistat.
 * 
 * @author Carlos Pérez <cperez@at4.net>
 */
public class ModificarOliComercialitzatLlistatValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(ModificarOliComercialitzatLlistatCommand.class);
    }

    /**
     * Valida el objeto.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {

    	ValidationUtils.rejectIfEmpty(
                errors,
                "dataInici",
                "error.dataInici.buit",
                "El camp Data inici no pot estar buit");
    	ValidationUtils.rejectIfEmpty(
                errors,
                "dataFi",
                "error.dataFi.buit",
                "El camp Data fi no pot estar buit");    	

    }

}
