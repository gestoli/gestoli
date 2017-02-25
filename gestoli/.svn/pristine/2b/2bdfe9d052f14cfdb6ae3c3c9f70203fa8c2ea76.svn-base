/**
 * TaxaCommand.java
 *
 */
package es.caib.gestoli.front.spring;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import es.caib.gestoli.logic.model.Campanya;


/**
 * 
 */
public class CampanyaCommand extends Campanya {
	private static final long serialVersionUID = 3461078905819773874L;
	private Boolean generarPrefactures;
	
	
	/**
     * Rellena los campos de este objeto con la información
     * @param campanya
     */
	public void fromCampanya(Campanya campanya) {
    	setId(campanya.getId());
    	setNom(campanya.getNom());
    	setObservacions(campanya.getObservacions());
    }

	/**
	 * Rellena el nombre de la campaña con un nombre por defecto, por ejemplo "2008-2009"
	 */
	public void setNomPerDefecte() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int anyActual = cal.get(Calendar.YEAR);
		cal.add(Calendar.YEAR, 1);
		int anySeguent = cal.get(Calendar.YEAR);
		setNom(anyActual + "/" + anySeguent);
	}

	
	/**
	 * Metodo 'getGenerarPrefactures'
	 * @return el generarPrefactures
	 */
	public Boolean getGenerarPrefactures() {
		return generarPrefactures;
	}

	/**
	 * Metodo 'setGenerarPrefactures'
	 * @param generarPrefactures el generarPrefactures a modificar
	 */
	public void setGenerarPrefactures(Boolean generarPrefactures) {
		this.generarPrefactures = generarPrefactures;
	}


}
