/**
 * GestionarInformacioCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.GestorDocumentalInformacio;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class GestorDocumentalGestionarInformacioCommand extends GestorDocumentalInformacio {
	
	Integer idCategoria;
	Boolean informar;
	
	public GestorDocumentalGestionarInformacioCommand(){
		
	}

	/**
     * Rellena los campos de este objeto con la información
     * del usuario.
     * @param u Usuari
     */
    public void fromInformacio(GestorDocumentalInformacio i) {
    	setId(i.getId());
    	setGestorDocumentalCategoriaInformacio(i.getGestorDocumentalCategoriaInformacio());
    	setData(i.getData());
    	setDocuments(i.getDocuments());
    	setEstabliments(i.getEstabliments());
    	setUsuaris(i.getUsuaris());
    	setTexte(i.getTexte());
    	setTitol(i.getTitol());
    	setEstat(i.getEstat());
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
