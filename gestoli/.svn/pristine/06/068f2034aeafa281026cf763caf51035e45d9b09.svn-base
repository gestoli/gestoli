
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus ConsultaOliDisponibleLlistatCommand.
 * 
 * @author Carlos Pérez <cperez@at4.net>
 */
public class ConsultaOliDisponibleLlistatValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(ConsultaOliDisponibleLlistatCommand.class);
    }

    /**
     * Valida el objeto.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {

    	
    	ValidationUtils.rejectIfEmpty(
                errors,
                "categoriaOli",
                "error.categoriaOli.buit",
                "Ha de seleccionar una categoria d'oli");
    	ValidationUtils.rejectIfEmpty(
                errors,
                "mesura",
                "error.mesura.buit",
                "Ha de seleccionar una mesura");

    }

}
