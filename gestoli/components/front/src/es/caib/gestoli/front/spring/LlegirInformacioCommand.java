/**
 * GestionarInformacioCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import es.caib.gestoli.logic.model.Informacio;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class LlegirInformacioCommand extends Informacio {
	
	Integer idCategoria;
	String nomCategoria;
	String dataFormat;
	Boolean llegit;
	
	public LlegirInformacioCommand(){
		
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
    	
    	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	this.dataFormat= sdf.format(i.getData());
    	
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

	public String getNomCategoria() {
		return nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public Boolean getLlegit() {
		return llegit;
	}

	public void setLlegit(Boolean llegit) {
		this.llegit = llegit;
	}


}
