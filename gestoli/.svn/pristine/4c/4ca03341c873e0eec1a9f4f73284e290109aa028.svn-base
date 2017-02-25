package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.InformeCampanya;

public class InformeCampanyaCommand extends InformeCampanya {
	private static final long serialVersionUID = -3454772861320876579L;
	
	private Arxiu arxiuDocument;
	private byte[] imatgeDocument;
	private Long document;
	
	private String errorImagenes;
	
	/**
	 * Rellena los campos de este objeto con la información
	 * de la InformacioUtil
	 * 
	 * @param m InformacioUtil
	 */
	public void fromInformeCampanya(InformeCampanya m) {
		setId(m.getId());
		setDataAlta(m.getDataAlta());
		setTipus(m.getTipus());
		setCampanya(m.getCampanya());
		setAny(m.getAny());
	}		
	
	public Arxiu getArxiuDocument() {
		return arxiuDocument;
	}
	public void setArxiuDocument(Arxiu arxiuDocument) {
		this.arxiuDocument = arxiuDocument;
	}

	public byte[] getImatgeDocument() {
		return imatgeDocument;
	}
	public void setImatgeDocument(byte[] imatgeDocument) {
		this.imatgeDocument = imatgeDocument;
	}

	public Long getDocument() {
		return document;
	}
	public void setDocument(Long document) {
		this.document = document;
	}

	public String getErrorImagenes() {
		return errorImagenes;
	}
	public void setErrorImagenes(String errorImagenes) {
		this.errorImagenes = errorImagenes;
	}

	
}
