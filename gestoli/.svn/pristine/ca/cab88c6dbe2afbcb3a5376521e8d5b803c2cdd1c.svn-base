package es.caib.gestoli.logic.model;

import java.util.Date;

public class QualitatAiguaControlAnaliticVerificacio extends QualitatControl {

	private static final long serialVersionUID = 1L;
	private QualitatAiguaPuntSortida puntMostreig;
	private QualitatAiguaControlAnalitic controlAnalitic;
	private Date dataAnalisi;
	private String satisfactoriString;
	
	public QualitatAiguaControlAnaliticVerificacio(){
		super();
	}
	
	
	public QualitatAiguaControlAnaliticVerificacio(Long id,
			Date dataRealitzacio, QualitatDescripcioPersonal responsableVerificacio, Date dataAnalisi,
			Boolean satisfactori, QualitatAiguaControlAnalitic controlAnalitic, QualitatAiguaPuntSortida puntMostreig){
		this.id = id;
		this.dataVerificacio = dataRealitzacio;
		this.dataAnalisi = dataAnalisi;
		this.responsableVerificacio = responsableVerificacio;
		this.satisfactori = satisfactori;
		this.controlAnalitic = controlAnalitic;
		this.puntMostreig = puntMostreig;
	}

	public QualitatAiguaControlAnalitic getControlAnalitic() {
		return controlAnalitic;
	}
	public void setControlAnalitic(QualitatAiguaControlAnalitic controlAnalitic) {
		this.controlAnalitic = controlAnalitic;
	}


	public QualitatAiguaPuntSortida getPuntMostreig() {
		return puntMostreig;
	}


	public void setPuntMostreig(QualitatAiguaPuntSortida puntMostreig) {
		this.puntMostreig = puntMostreig;
	}


	public Date getDataAnalisi() {
		return dataAnalisi;
	}


	public void setDataAnalisi(Date dataAnalisi) {
		this.dataAnalisi = dataAnalisi;
	}


	public String getSatisfactoriString() {
		return satisfactoriString;
	}


	public void setSatisfactoriString(String satisfactoriString) {
		this.satisfactoriString = satisfactoriString;
	}
	
	
	
}
