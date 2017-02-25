/**
 * GestionarDocumentCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;



import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Document;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class GestionarDocumentCommand extends Document {
	
	private byte[] fitxer;
	
	private Arxiu arxiuObject;
	
	public GestionarDocumentCommand(){
		
	}

	/**
     * Rellena los campos de este objeto con la información
     * del usuario.
     * @param u Usuari
     */
    public void fromDocument(Document d) {
    	setId(d.getId());
    	setArxiu(d.getArxiu());
    	setInformacio(d.getInformacio());
    	setPersonal(d.getPersonal());
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
