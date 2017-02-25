/**
 * GestionarDocumentCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;



import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.GestorDocumentalDocument;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class GestorDocumentalGestionarDocumentCommand extends GestorDocumentalDocument {
	
	private byte[] fitxer;
	
	private Arxiu arxiuObject;
	
	public GestorDocumentalGestionarDocumentCommand(){
		
	}

	/**
     * Rellena los campos de este objeto con la información
     * del usuario.
     * @param u Usuari
     */
    public void fromDocument(GestorDocumentalDocument d) {
    	setId(d.getId());
    	setArxiu(d.getArxiu());
    	setGestorDocumentalInformacio(d.getGestorDocumentalInformacio());
    	setTitol(d.getTitol());
    }

	/**
	 * @return the fitxer
	 */
	public byte[] getFitxer() {
		return fitxer;
	}

	/**
	 * @param fitxer the fitxer to set
	 */
	public void setFitxer(byte[] fitxer) {
		this.fitxer = fitxer;
	}

	/**
	 * @return the arxiuObject
	 */
	public Arxiu getArxiuObject() {
		return arxiuObject;
	}

	/**
	 * @param arxiuObject the arxiuObject to set
	 */
	public void setArxiuObject(Arxiu arxiuObject) {
		this.arxiuObject = arxiuObject;
	}
    
    
}
