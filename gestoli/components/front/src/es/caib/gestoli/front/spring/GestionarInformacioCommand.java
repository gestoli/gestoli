/**
 * GestionarInformacioCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.text.SimpleDateFormat;
import java.util.Date;

import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Usuari;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class GestionarInformacioCommand extends Informacio {
	
	Integer idCategoria;
	Boolean informar;
	
	public GestionarInformacioCommand(){
		
	}

	/**
     * Rellena los campos de este objeto con la información
     * del usuario.
     * @param u Usuari
     */
    public void fromInformacio(Informacio i) {
    	setId(i.getId());
    	setCategoriaInformacio(i.getCategoriaInformacio());
    	setData(i.getData());
    	setDocuments(i.getDocuments());
    	setEstabliments(i.getEstabliments());
    	setOlivicultors(i.getOlivicultors());
    	setTexte(i.getTexte());
    	setTitol(i.getTitol());
    }
	
	/**
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the informar
	 */
	public Boolean getInformar() {
		return informar;
	}

	/**
	 * @param informar the informar to set
	 */
	public void setInformar(Boolean informar) {
		this.informar = informar;
	}


}
