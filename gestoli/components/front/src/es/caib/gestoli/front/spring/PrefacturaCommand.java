
package es.caib.gestoli.front.spring;

import java.util.Collection;

import es.caib.gestoli.logic.model.Olivicultor;


/**
 * 
 */
public class PrefacturaCommand extends Olivicultor {
	private static final long serialVersionUID = 3461078905819773874L;
	private Boolean totsOlivicultorsPrefactura;
	private Collection olivicultorsSeleccionats;
	

	public Collection getOlivicultorsSeleccionats() {
		return olivicultorsSeleccionats;
	}
	public void setOlivicultorsSeleccionats(Collection olivicultorsSeleccionats) {
		this.olivicultorsSeleccionats = olivicultorsSeleccionats;
	}
	public Boolean getTotsOlivicultorsPrefactura() {
		return totsOlivicultorsPrefactura;
	}
	public void setTotsOlivicultorsPrefactura(Boolean totsOlivicultors) {
		this.totsOlivicultorsPrefactura = totsOlivicultors;
	}

}
