package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QualitatPlaNetejaVerificacio extends QualitatControl {

	private static final long serialVersionUID = 1L;

	private Long id;
	private QualitatPlaNeteja neteja;
	private Date dataAnterior;
	private String satisfactoriString;

	
	public QualitatPlaNetejaVerificacio(){
		super();
	}
	
	public QualitatPlaNetejaVerificacio(Long id, QualitatPlaNeteja neteja,
			Date dataVerificacio, Date dataAnterior, QualitatDescripcioPersonal responsableVerificacio, 
			Boolean satisfactori){
		this.id = id;
		this.neteja = neteja;
		this.dataVerificacio = dataVerificacio;
		this.dataAnterior = dataAnterior;
		this.responsableVerificacio = responsableVerificacio;
		this.satisfactori = satisfactori;
	}

	
	public Boolean getSatisfactori() {
		return satisfactori;
	}

	public void setSatisfactori(Boolean satisfactori) {
		this.satisfactori = satisfactori;
	}

	public Date getDataVerificacio() {
		return dataVerificacio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualitatPlaNeteja getNeteja() {
		return neteja;
	}

	public void setNeteja(QualitatPlaNeteja neteja) {
		this.neteja = neteja;
	}


	public void setDataVerificacio(Date dataVerificacio) {
		this.dataVerificacio = dataVerificacio;
	}

	public Date getDataAnterior() {
		return dataAnterior;
	}

	public void setDataAnterior(Date dataAnterior) {
		this.dataAnterior = dataAnterior;
	}

	public QualitatDescripcioPersonal getResponsableVerificacio() {
		return responsableVerificacio;
	}

	public void setResponsableVerificacio(QualitatDescripcioPersonal responsableVerificacio) {
		this.responsableVerificacio = responsableVerificacio;
	}

	public String getSatisfactoriString() {
		return satisfactoriString;
	}

	public void setSatisfactoriString(String satisfactoriString) {
		this.satisfactoriString = satisfactoriString;
	}
	
	

}
