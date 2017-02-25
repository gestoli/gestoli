
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus ConsultaOliDisponibleLlistatCommand.
 * 
 * @author Carlos Pérez <cperez@at4.net>
 */
public class ConsultaAnaliticasSimplificatValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(ConsultaAnaliticasSimplificatCommand.class);
    }

    /**
     * Valida el objeto.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {

    }

}
