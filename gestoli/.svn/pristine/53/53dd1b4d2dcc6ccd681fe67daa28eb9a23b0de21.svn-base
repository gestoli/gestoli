package es.caib.gestoli.logic.model;

import java.util.HashSet;
import java.util.Set;

public class QualitatAPPCCEtapa {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nom;
	private Integer order;
	
	private Establiment establiment;
	
	private Set<QualitatAPPCCEtapaPerill> perills = new HashSet<QualitatAPPCCEtapaPerill>(0);

	
	public QualitatAPPCCEtapa() {
		super();
	}

	public QualitatAPPCCEtapa(Long id, String nom, Integer order, Establiment establiment, 
			Set<QualitatAPPCCEtapaPerill> perills){
		this.establiment = establiment;
		this.id = id;
		this.order = order;
		this.nom = nom;
		this.perills = perills;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Set<QualitatAPPCCEtapaPerill> getPerills() {
		return perills;
	}

	public void setPerills(Set<QualitatAPPCCEtapaPerill> perills) {
		this.perills = perills;
	}

	
}
