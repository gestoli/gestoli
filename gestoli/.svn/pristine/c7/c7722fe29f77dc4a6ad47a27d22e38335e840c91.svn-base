package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class InformeCampanya implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataAlta;
	private Boolean tipus; // "S"->Campanya - "N"->Any
	private String campanya;
	private String any;

	private Long document;
	//private Arxiu arxiucImatgePrincipal;
	//private byte[] imatgeImatgePrincipal;
	
	public InformeCampanya(){
		super();
	}
	
	public InformeCampanya(
			Long id, Date dataAlta, Boolean tipus, String campanya, String any, Long document){
		this.id = id;
		this.dataAlta = dataAlta;
		this.tipus = tipus;
		this.campanya = campanya;
		this.any = any;
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Boolean getTipus() {
		return tipus;
	}

	public void setTipus(Boolean tipus) {
		this.tipus = tipus;
	}

	public String getCampanya() {
		return campanya;
	}

	public void setCampanya(String campanya) {
		this.campanya = campanya;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

	public Long getDocument() {
		return document;
	}

	public void setDocument(Long document) {
		this.document = document;
	}

}
