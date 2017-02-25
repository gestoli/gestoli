package es.caib.gestoli.logic.model;

import java.io.Serializable;

public class QualitatAiguaControlOrganoleptic implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String frequencia;

	private QualitatDescripcioPersonal responsable;
	
	private Establiment establiment;

	
	public QualitatAiguaControlOrganoleptic() {
		super();
	}
	
	public QualitatAiguaControlOrganoleptic(Long id, String frequencia,
			QualitatDescripcioPersonal responsable, Establiment establiment) {
		this.id = id;
		this.frequencia = frequencia;
		this.responsable = responsable;
		this.establiment = establiment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public QualitatDescripcioPersonal getResponsable() {
		return responsable;
	}

	public void setResponsable(QualitatDescripcioPersonal responsable) {
		this.responsable = responsable;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
}
