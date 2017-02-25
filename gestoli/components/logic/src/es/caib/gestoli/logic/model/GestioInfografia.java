package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class GestioInfografia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataAlta;
	private String nom;
	private String nomEs;
	private String descripcio;
	private String descripcioEs;
	
	private Long arxiu;
	private Long imatge;
	
	public GestioInfografia(){
		super();
	}
	
	public GestioInfografia(Long id, Date dataAlta, String nom, String descripcio, String nomEs, String descripcioEs, Long arxiu, Long imatge){
		this.id = id;
		this.dataAlta = dataAlta;
		this.nom = nom;
		this.descripcio = descripcio;
		this.nomEs = nomEs;
		this.descripcioEs = descripcioEs;
		this.arxiu = arxiu;
		this.imatge = imatge;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getNomEs() {
		return nomEs;
	}

	public void setNomEs(String nomEs) {
		this.nomEs = nomEs;
	}

	public String getDescripcioEs() {
		return descripcioEs;
	}

	public void setDescripcioEs(String descripcioEs) {
		this.descripcioEs = descripcioEs;
	}

	public Long getArxiu() {
		return arxiu;
	}

	public void setArxiu(Long arxiu) {
		this.arxiu = arxiu;
	}

	public Long getImatge() {
		return imatge;
	}

	public void setImatge(Long imatge) {
		this.imatge = imatge;
	}
	
}
