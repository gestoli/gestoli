package es.caib.gestoli.logic.model;

import java.io.Serializable;

public class QualitatAPPCCProducte implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descripcio;
	private String us;
	
	private QualitatAPPCC appcc;
	
	public QualitatAPPCCProducte(){
		super();
	}
	
	public QualitatAPPCCProducte(String descripcio, String us, QualitatAPPCC appcc){
		super();
		this.descripcio = descripcio;
		this.us = us;
		this.appcc = appcc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public QualitatAPPCC getAppcc() {
		return appcc;
	}

	public void setAppcc(QualitatAPPCC appcc) {
		this.appcc = appcc;
	}

	
}
