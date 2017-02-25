package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QualitatControlPlagues implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String ubicacio;
	private String elementControl;  //50 caracteres
	private String frecuencia;
	private Boolean isResponsableIntern; 	// True -> responsableIntern // False -> empresaExterna
	private QualitatDescripcioPersonal responsableIntern;
	
	// Datos empresa externa
	private String empresaExterna; //50 caracteres
	private Date iniciContracte;
	private Date fiContracte;
	private String plagaObjectiu;
	private String frecSeguiment;

	private Establiment establiment;
	
	public QualitatControlPlagues() {
		super();
	}

	
	public QualitatControlPlagues(Long id, String ubicacio, String elementControl, String frecuencia,
			Establiment establiment, Boolean isResponsableIntern, QualitatDescripcioPersonal responsableIntern, 
			String empresaExterna, Date iniciContracte, Date fiContracte,
			String plagaObjectiu, String frecSeguiment){
		this.establiment = establiment;
		this.id = id;
		this.ubicacio = ubicacio;
		this.elementControl = elementControl;
		this.frecuencia = frecuencia;
		this.isResponsableIntern = isResponsableIntern;
		this.responsableIntern = responsableIntern;
		this.empresaExterna = empresaExterna;  
		this.iniciContracte = iniciContracte;
		this.fiContracte = fiContracte;
		this.plagaObjectiu = plagaObjectiu;
		this.frecSeguiment = frecSeguiment;
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


	public String getElementControl() {
		return elementControl;
	}


	public void setElementControl(String elementControl) {
		this.elementControl = elementControl;
	}


	public String getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


	public Boolean getIsResponsableIntern() {
		return isResponsableIntern;
	}


	public void setIsResponsableIntern(Boolean isResponsableIntern) {
		this.isResponsableIntern = isResponsableIntern;
	}


	public QualitatDescripcioPersonal getResponsableIntern() {
		return responsableIntern;
	}


	public void setResponsableIntern(QualitatDescripcioPersonal responsableIntern) {
		this.responsableIntern = responsableIntern;
	}


	public String getEmpresaExterna() {
		return empresaExterna;
	}


	public void setEmpresaExterna(String empresaExterna) {
		this.empresaExterna = empresaExterna;
	}


	public Date getIniciContracte() {
		return iniciContracte;
	}


	public void setIniciContracte(Date iniciContracte) {
		this.iniciContracte = iniciContracte;
	}


	public Date getFiContracte() {
		return fiContracte;
	}


	public void setFiContracte(Date fiContracte) {
		this.fiContracte = fiContracte;
	}


	public String getPlagaObjectiu() {
		return plagaObjectiu;
	}


	public void setPlagaObjectiu(String plagaObjectiu) {
		this.plagaObjectiu = plagaObjectiu;
	}


	public String getFrecSeguiment() {
		return frecSeguiment;
	}


	public void setFrecSeguiment(String frecSeguiment) {
		this.frecSeguiment = frecSeguiment;
	}


	public Establiment getEstabliment() {
		return establiment;
	}


	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	
	
}
