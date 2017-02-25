package es.caib.gestoli.front.spring;

public class ModificarDocumentSortidaCommand {

	private Long idSortida;
	private String tipusSortida;
	private String tipusDocument;
	private String document;
	private String infoSortida;
	
	public Long getIdSortida() {
		return idSortida;
	}
	public void setIdSortida(Long idSortida) {
		this.idSortida = idSortida;
	}
	public String getTipusSortida() {
		return tipusSortida;
	}
	public void setTipusSortida(String tipusSortida) {
		this.tipusSortida = tipusSortida;
	}
	public String getTipusDocument() {
		return tipusDocument;
	}
	public void setTipusDocument(String tipusDocument) {
		this.tipusDocument = tipusDocument;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getInfoSortida() {
		return infoSortida;
	}
	public void setInfoSortida(String infoSortida) {
		this.infoSortida = infoSortida;
	}
}
