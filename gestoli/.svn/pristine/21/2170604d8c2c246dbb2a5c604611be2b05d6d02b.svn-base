/**
 * EtiquetatgeCommand.java
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Etiquetatge;


/**
 * Objeto que representa el formulario de mantenimento
 * de usuarios.
 */
public class EtiquetatgeCommand extends Etiquetatge {
	private static final long serialVersionUID = 3660614058015788632L;
	private Long tipusEnvasId;
		
		
	private Arxiu arxiucImatgeFrontal;
	private byte[] imatgeImatgeFrontal;
	private Arxiu arxiucImatgePosterior;
	private byte[] imatgeImatgePosterior;
	
	private Integer etiPosition;
	
	private String nom;
	

	public Integer getEtiPosition() {
		return etiPosition;
	}

	public void setEtiPosition(Integer etiPosition) {
		this.etiPosition = etiPosition;
	}

	
	/**
     * Rellena los campos de este objeto con la información
     * del Etiquetatge.
     * 
     * @param et Etiquetatge
     */
    public void fromEtiquetatge(Etiquetatge et) {
    	setId(et.getId());
    	setActiu(et.getActiu());
    	setImatgeFrontal(et.getImatgeFrontal());
    	setImatgePosterior(et.getImatgePosterior());
    	setObservacions(et.getObservacions());
    	setMarca(et.getMarca());
    	setTipusEnvas(et.getTipusEnvas());
    }

    /**
     * Para imprimir bien el registro
     */
    public String toString() {
    	return "Etiquetatge(" +
    			"Etiquetatge: " + getTipusEnvas().getVolum() + ")";
    }

	

	public Long getTipusEnvasId() {
		return tipusEnvasId;
	}

	public void setTipusEnvasId(Long tipusEnvasId) {
		this.tipusEnvasId = tipusEnvasId;
	}

	

	public Arxiu getArxiucImatgeFrontal() {
		return arxiucImatgeFrontal;
	}

	public void setArxiucImatgeFrontal(Arxiu arxiucImatgeFrontal) {
		this.arxiucImatgeFrontal = arxiucImatgeFrontal;
	}

	
	public byte[] getImatgeImatgeFrontal() {
		return imatgeImatgeFrontal;
	}

	public void setImatgeImatgeFrontal(byte[] imatgeImatgeFrontal) {
		this.imatgeImatgeFrontal = imatgeImatgeFrontal;
	}

	public Arxiu getArxiucImatgePosterior() {
		return arxiucImatgePosterior;
	}

	public void setArxiucImatgePosterior(Arxiu arxiucImatgePosterior) {
		this.arxiucImatgePosterior = arxiucImatgePosterior;
	}

	public byte[] getImatgeImatgePosterior() {
		return imatgeImatgePosterior;
	}

	public void setImatgeImatgePosterior(byte[] imatgeImatgePosterior) {
		this.imatgeImatgePosterior = imatgeImatgePosterior;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	

}
