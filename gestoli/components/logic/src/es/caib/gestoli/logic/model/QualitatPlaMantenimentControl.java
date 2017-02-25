package es.caib.gestoli.logic.model;

import java.util.Date;

public class QualitatPlaMantenimentControl  extends QualitatControl {

	private static final long serialVersionUID = 1L;
	private QualitatPlaManteniment manteniment;
	private Date dataRealitzacio;
	private Date dataAnterior;
	private String accioRealitzada;
	private String altresAccions;

	private Boolean isResponsableIntern; 	// True -> responsableIntern // False -> responsableExtern
	private QualitatDescripcioPersonal responsableIntern;
	private String responsableExtern; //15 caracteres

	public QualitatPlaMantenimentControl(){
		super();
	}
	
	public QualitatPlaMantenimentControl(Long id, QualitatPlaManteniment manteniment,
			Date dataRealitzacio, Date dataAnterior, String accioRealitzada, String altresAccions,
			Boolean isResponsableIntern, QualitatDescripcioPersonal responsableIntern, String responsableExtern,
			String objectiuVerificacio, Date dataVerificacio, QualitatDescripcioPersonal responsableVerificacio,
			Boolean satisfactori){
		this.id = id;
		this.manteniment = manteniment;
		this.dataRealitzacio = dataRealitzacio;
		this.dataAnterior = dataAnterior;
		this.accioRealitzada = accioRealitzada;
		this.altresAccions = altresAccions;
		this.isResponsableIntern = isResponsableIntern;
		this.responsableIntern = responsableIntern;
		this.responsableExtern = responsableExtern;		
		
		this.objectiu = objectiuVerificacio;
		this.dataVerificacio = dataVerificacio;
		this.responsableVerificacio = responsableVerificacio;
		this.satisfactori = satisfactori;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualitatPlaManteniment getManteniment() {
		return manteniment;
	}

	public void setManteniment(QualitatPlaManteniment manteniment) {
		this.manteniment = manteniment;
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

	public String getAccioRealitzada() {
		return accioRealitzada;
	}

	public void setAccioRealitzada(String accioRealitzada) {
		this.accioRealitzada = accioRealitzada;
	}

	public String getAltresAccions() {
		return altresAccions;
	}

	public void setAltresAccions(String altresAccions) {
		this.altresAccions = altresAccions;
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
	

}
