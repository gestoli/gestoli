/**
 * DipositCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.CategoriaInformacio;
import es.caib.gestoli.logic.model.Diposit;



/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class CategoriaInformacioCommand extends CategoriaInformacio {
	
	private static final long serialVersionUID = 1465768940114210213L;

	/**
     * Rellena los campos de este objeto con la información
     */
    public void fromCategoriaInformacio(CategoriaInformacio i) {
    	setId(i.getId());
    	setNom(i.getNom());
    	setDescripcio(i.getDescripcio());
    }

}