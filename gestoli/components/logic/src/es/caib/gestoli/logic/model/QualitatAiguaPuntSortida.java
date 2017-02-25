package es.caib.gestoli.logic.model;

import java.io.Serializable;

public class QualitatAiguaPuntSortida implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String ubicacio;
	
	private Establiment establiment;

	public QualitatAiguaPuntSortida() {
		super();
	}

	public QualitatAiguaPuntSortida(Long id, String ubicacio, Establiment establiment) {
		this.ubicacio = ubicacio;
		this.establiment = establiment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUbicacio() {
		return ubicacio;
	}

	public void setUbicacio(String ubicacio) {
		this.ubicacio = ubicacio;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
}
