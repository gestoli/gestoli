/**
 * CategoriaValidator.java
 *
 * Creada el 08 de maig de 2006, 12:17:13
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus Categoria.
 * 
 * @author Josep Gayà <josepg@limit.es>
 */
public class AgenciaOliSortidaOrujoValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(AgenciaOliSortidaOrujoCommand.class);
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
