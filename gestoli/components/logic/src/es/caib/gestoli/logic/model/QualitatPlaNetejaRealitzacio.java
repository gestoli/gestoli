package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QualitatPlaNetejaRealitzacio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private QualitatPlaNeteja neteja;
	private Date dataRealitzacio;
	private Date dataAnterior;

	private QualitatDescripcioPersonal responsable;
	
	public QualitatPlaNetejaRealitzacio(){
		super();
	}
	
	public QualitatPlaNetejaRealitzacio(Long id, QualitatPlaNeteja neteja,
			Date dataRealitzacio, Date dataAnterior, String accioRealitzada, String altresAccions,
			Boolean isResponsableIntern, QualitatDescripcioPersonal responsable, String responsableExtern){
		this.id = id;
		this.neteja = neteja;
		this.dataRealitzacio = dataRealitzacio;
		this.dataAnterior = dataAnterior;
		this.responsable = responsable;
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

	public Date getDataRealitzacio() {
		return dataRealitzacio;
	}

	public void setDataRealitzacio(Date dataRealitzacio) {
		this.dataRealitzacio = dataRealitzacio;
	}

	public Date getDataAnterior() {
		return dataAnterior;
	}

	public void setDataAnterior(Date dataAnterior) {
		this.dataAnterior = dataAnterior;
	}

	public QualitatDescripcioPersonal getResponsable() {
		return responsable;
	}

	public void setResponsable(QualitatDescripcioPersonal responsable) {
		this.responsable = responsable;
	}
	
	

}
