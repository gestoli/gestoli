package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public abstract class QualitatControl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected QualitatDepartament departament;
	protected String objectiu;
	protected Date dataVerificacio;
	protected QualitatDescripcioPersonal responsableVerificacio;
	protected Boolean satisfactori; // True -> satisfactio, False -> insatisfactorio

	protected Set<QualitatNoConformitat> noConformitats = new HashSet<QualitatNoConformitat>(0);

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public QualitatDepartament getDepartament() {
		return departament;
	}
	public void setDepartament(QualitatDepartament departament) {
		this.departament = departament;
	}

	public String getObjectiu() {
		return objectiu;
	}
	public void setObjectiu(String objectiu) {
		this.objectiu = objectiu;
	}
	
	public Date getDataVerificacio() {
		return dataVerificacio;
	}
	public void setDataVerificacio(Date dataVerificacio) {
		this.dataVerificacio = dataVerificacio;
	}
	
	public QualitatDescripcioPersonal getResponsableVerificacio() {
		return responsableVerificacio;
	}
	public void setResponsableVerificacio(
			QualitatDescripcioPersonal responsableVerificacio) {
		this.responsableVerificacio = responsableVerificacio;
	}
	
	public Boolean getSatisfactori() {
		return satisfactori;
	}
	public void setSatisfactori(Boolean satisfactori) {
		this.satisfactori = satisfactori;
	}
	
	public Set<QualitatNoConformitat> getNoConformitats() {
		return noConformitats;
	}
	public void setNoConformitats(Set<QualitatNoConformitat> noConformitats) {
		this.noConformitats = noConformitats;
	}
	
	
	
}
