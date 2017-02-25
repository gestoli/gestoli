
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus ConsultaOliElaboratLlistatCommand.
 * 
 * @author Carlos Pérez <cperez@at4.net>
 */
public class CercaTrazabilitatLlistatValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(CercaTrazabilitatLlistatCommand.class);
    }

    /**
     * Valida el objeto.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {

    	CercaTrazabilitatLlistatCommand command = (CercaTrazabilitatLlistatCommand)obj;
    	ValidationUtils.rejectIfEmpty(
                errors,
                "tipus",
                "error.tipus.buit",
                "El camp Tipus d'informació no pot estar buit");
    }

}
