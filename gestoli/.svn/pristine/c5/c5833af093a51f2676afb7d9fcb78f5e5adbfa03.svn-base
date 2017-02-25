/**
 * CategoriaValidator.java
 *
 * Creada el 08 de maig de 2006, 12:17:13
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Valida els camps del <i>command</i> de tipus Categoria.
 * 
 * @author Josep Gayà <josepg@limit.es>
 */
public class ConsultaLlibreOlivaTaulaValidator implements Validator {


    /**
     * Indica las clases que soporta este validador.
     *
     * @see Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return clazz.equals(ConsultaLlibreOlivaTaulaCommand.class);
    }

    /**
     * Valida el objeto.
     *
     * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {

    	ConsultaLlibreOlivaTaulaCommand consultaLlibreEntradaOlivaTaulaCommand = (ConsultaLlibreOlivaTaulaCommand) obj;
		
		if ( consultaLlibreEntradaOlivaTaulaCommand.getIdCampanya()== null && (consultaLlibreEntradaOlivaTaulaCommand.getDataFi() == null || consultaLlibreEntradaOlivaTaulaCommand.getDataInici() == null)){
			errors.rejectValue("buscar", "error.opcion.buit" ,"Debe elegir una opcion de busqueda");
		}	
    	
    	
    }

}
