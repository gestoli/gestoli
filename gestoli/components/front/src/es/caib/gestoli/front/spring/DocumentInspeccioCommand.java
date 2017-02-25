package es.caib.gestoli.front.spring;



import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.DocumentInspeccio;


public class DocumentInspeccioCommand extends DocumentInspeccio {
	
	private static final long serialVersionUID = 1L;

	private byte[] fitxer;
	private Arxiu arxiuObject;
	
	private Long idOlivicultor;
	private Long idEstabliment;
	
	public DocumentInspeccioCommand(){
		
	}

	/**
     * Rellena los campos de este objeto con la información
     * del DocumentInspeccio.
     * @param form DocumentInspeccio
     */
    public void fromDocumentInspeccio(DocumentInspeccio form) {
    	setId(form.getId());
    	setArxiu(form.getArxiu());
    	setData(form.getData());
    	setTipus(form.getTipus());
    	setOlivicultor(form.getOlivicultor());
    	if (form.getOlivicultor() != null){
    		setIdOlivicultor(form.getOlivicultor().getId());
    	}
    	setEstabliment(form.getEstabliment());
    	if (form.getEstabliment() != null){
    		setIdEstabliment(form.getEstabliment().getId());
    	}
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

	public Long getIdOlivicultor() {
		return idOlivicultor;
	}

	public void setIdOlivicultor(Long idOlivicultor) {
		this.idOlivicultor = idOlivicultor;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
    
    
}
