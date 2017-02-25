package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QualitatAPPCCVerificacio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nom;
	private Date dataComprobacio;
	private Boolean correcte;
	private QualitatDescripcioPersonal responsable;
	
	private Establiment establiment;
	
	public QualitatAPPCCVerificacio() {
		super();
	}
	
	public QualitatAPPCCVerificacio(Establiment establiment, String nom, Date dataComprobacio,
			Boolean correcte, QualitatDescripcioPersonal responsable) {
		super();
		this.establiment = establiment;
		this.nom = nom;
		this.dataComprobacio = dataComprobacio;
		this.correcte = correcte;
		this.responsable = responsable;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDataComprobacio() {
		return dataComprobacio;
	}

	public void setDataComprobacio(Date dataComprobacio) {
		this.dataComprobacio = dataComprobacio;
	}

	public Boolean getCorrecte() {
		return correcte;
	}

	public void setCorrecte(Boolean correcte) {
		this.correcte = correcte;
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
