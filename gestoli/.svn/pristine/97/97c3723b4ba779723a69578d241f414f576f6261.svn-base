/**
 * OlivicultorCommand.java
 *
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;

import es.caib.gestoli.logic.model.Olivicultor;

/**
 * Objeto que representa el formulario de olivicultor.
 * 
 * 
 */
public class EtiquetesOlivicultorsCommand extends Olivicultor {

	private static final long serialVersionUID = 1148613628614982021L;

	private Collection olivicultorsSeleccionats;
	private Boolean totsOlivicultors;
	
	/**
	 * Rellena los campos de este objeto con la información del olivicultor.
	 * 
	 * @param ol
	 *            Olivicultor
	 */
	public void fromEtiquetesOlivicultors(Olivicultor ol) {
		setCodiPostal(ol.getCodiPostal());
		setDireccio(ol.getDireccio());
		setNom(ol.getNom());
		setPoblacio(ol.getPoblacio());
	}

	public Collection getOlivicultorsSeleccionats() {
		return olivicultorsSeleccionats;
	}

	public void setOlivicultorsSeleccionats(Collection olivicultorsSeleccionats) {
		this.olivicultorsSeleccionats = olivicultorsSeleccionats;
	}

	public Boolean getTotsOlivicultors() {
		return totsOlivicultors;
	}

	public void setTotsOlivicultors(Boolean totsOlivicultors) {
		this.totsOlivicultors = totsOlivicultors;
	}
}
