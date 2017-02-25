/**
 * DipositCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.AnaliticaParametreTipus;



/**
 * Objeto que representa el formulario de mantenimento
 * 
 * 
 */
public class AnaliticaParametreTipusCommand extends AnaliticaParametreTipus {
	
	private static final long serialVersionUID = 1465768940114210213L;


	/**
     * Rellena los campos de este objeto con la información
     * 
     * @param d Diposit
     */
    public void fromAnaliticaParametreTipus(AnaliticaParametreTipus a) {
    	setId(a.getId());
    	setNom(a.getNom());
    	setNomCast(a.getNomCast());
    	setOrdre(a.getOrdre());
    	setTipus(a.getTipus());
    	setTipusControl(a.getTipusControl());
    	setActiu(a.getActiu());
    }



}
