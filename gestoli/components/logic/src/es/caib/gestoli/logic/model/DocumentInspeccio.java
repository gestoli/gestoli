package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentInspeccio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String tipus;
	private Date data;
	
	private Olivicultor olivicultor;
	private Establiment establiment;

	private Long arxiu;

	public DocumentInspeccio() {
		super();
	}

	public DocumentInspeccio(String tipus, Date data, Olivicultor olivicultor, Establiment establiment, Long arxiu) {
		this.tipus = tipus;
		this.data = data;
		this.olivicultor = olivicultor;
		this.establiment = establiment;
		this.arxiu = arxiu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Olivicultor getOlivicultor() {
		return olivicultor;
	}

	public void setOlivicultor(Olivicultor olivicultor) {
		this.olivicultor = olivicultor;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Long getArxiu() {
		return this.arxiu;
	}

	public void setArxiu(Long arxiu) {
		this.arxiu = arxiu;
	}

}
