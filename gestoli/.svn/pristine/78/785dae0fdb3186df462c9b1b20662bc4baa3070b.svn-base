package es.caib.gestoli.logic.model;

import java.io.Serializable;

public class QualitatPlaManteniment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private QualitatDescripcioEquip equip;
	private String accions;
	private String frecuencia;

	private Boolean isResponsableIntern; 	// True -> responsableIntern // False -> responsableExtern
	private QualitatDescripcioPersonal responsableIntern;
	private String responsableExtern; //15 caracteres

	private String verificacioFrecuencia;
	private QualitatDescripcioPersonal verificacioResponsable;
	private Boolean actiu;
	
	public QualitatPlaManteniment() {
		super();
	}
	
	public QualitatPlaManteniment(Long id, QualitatDescripcioEquip equip,
			String accions, String frecuencia, Boolean isResponsableIntern,
			QualitatDescripcioPersonal responsableIntern, String responsableExtern,
			String verificacioFrecuencia, QualitatDescripcioPersonal verificacioResponsable,
			Boolean actiu) {
		this.id = id;
		this.equip = equip;
		this.accions = accions;
		this.frecuencia = frecuencia;
		this.isResponsableIntern = isResponsableIntern;
		this.responsableIntern = responsableIntern;
		this.responsableExtern = responsableExtern;
		this.verificacioFrecuencia = verificacioFrecuencia;
		this.verificacioResponsable = verificacioResponsable;
		this.actiu = actiu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualitatDescripcioEquip getEquip() {
		return equip;
	}

	public void setEquip(QualitatDescripcioEquip equip) {
		this.equip = equip;
	}

	public String getAccions() {
		return accions;
	}

	public void setAccions(String accions) {
		this.accions = accions;
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

	public String getResponsableExtern() {
		return responsableExtern;
	}

	public void setResponsableExtern(String responsableExtern) {
		this.responsableExtern = responsableExtern;
	}

	public String getVerificacioFrecuencia() {
		return verificacioFrecuencia;
	}

	public void setVerificacioFrecuencia(String verificacioFrecuencia) {
		this.verificacioFrecuencia = verificacioFrecuencia;
	}

	public QualitatDescripcioPersonal getVerificacioResponsable() {
		return verificacioResponsable;
	}

	public void setVerificacioResponsable(
			QualitatDescripcioPersonal verificacioResponsable) {
		this.verificacioResponsable = verificacioResponsable;
	}

	public Boolean getActiu() {
		return actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}
	
	
}
