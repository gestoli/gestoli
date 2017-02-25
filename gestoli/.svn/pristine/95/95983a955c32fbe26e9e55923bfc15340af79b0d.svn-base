package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QualitatAPPCC implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Set<QualitatPuestoTreball> carrecs = new HashSet(0);
	private Set<QualitatAPPCCProducte> productes = new HashSet(0);
	
	// Verificacio
	private String nom;
	private Date dataComprobacio;
	private Boolean correcte;
	private QualitatDescripcioPersonal responsable;
	
	
	private Establiment establiment;
	
	public QualitatAPPCC(){
		super();
	}
	
	public QualitatAPPCC(
			Set<QualitatPuestoTreball> carrecs,	Set<QualitatAPPCCProducte> productes, 
			String nom, Date dataComprobacio, Boolean correcte, QualitatDescripcioPersonal responsable,
			Establiment establiment){
		super();
		this.establiment = establiment;
		this.carrecs = carrecs;
		this.productes = productes;		
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

	public Set<QualitatPuestoTreball> getCarrecs() {
		return carrecs;
	}

	public void setCarrecs(Set<QualitatPuestoTreball> carrecs) {
		this.carrecs = carrecs;
	}

	public Set<QualitatAPPCCProducte> getProductes() {
		return productes;
	}

	public void setProductes(Set<QualitatAPPCCProducte> productes) {
		this.productes = productes;
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
