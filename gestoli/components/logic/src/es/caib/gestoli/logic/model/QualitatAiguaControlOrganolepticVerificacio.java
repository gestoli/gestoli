package es.caib.gestoli.logic.model;

import java.util.Date;

public class QualitatAiguaControlOrganolepticVerificacio extends QualitatControl {

	private static final long serialVersionUID = 1L;
	private QualitatAiguaPuntSortida puntMostreig;
	private QualitatAiguaControlOrganoleptic controlOrganoleptic;
	
	private Double resultat;
	//CONTROL ORGANOLEPTIC
	private Boolean olor;
	private Boolean color;
	private Boolean sabor;
	private Boolean terbolesa;
	private String olorString;
	private String colorString;
	private String saborString;
	private String terbolesaString;
		
	
	public QualitatAiguaControlOrganolepticVerificacio(){
		super();
	}
	
	
	public QualitatAiguaControlOrganolepticVerificacio(Long id,
			Date dataRealitzacio, QualitatDescripcioPersonal responsableVerificacio,
			Boolean satisfactori, QualitatAiguaControlOrganoleptic controlOrganoleptic,
			QualitatAiguaPuntSortida puntMostreig, Boolean olor, Boolean color,
			Boolean sabor, Boolean terbolesa, Double resultat){
		this.id = id;
		this.dataVerificacio = dataRealitzacio;
		this.responsableVerificacio = responsableVerificacio;
		this.satisfactori = satisfactori;
		this.controlOrganoleptic = controlOrganoleptic;
		this.puntMostreig = puntMostreig;
		this.olor = olor;
		this.color = color;
		this.sabor = sabor;
		this.terbolesa = terbolesa;
		this.resultat = resultat;
	}

	public QualitatAiguaControlOrganoleptic getControlOrganoleptic() {
		return controlOrganoleptic;
	}
	public void setControlOrganoleptic(QualitatAiguaControlOrganoleptic controlOrganoleptic) {
		this.controlOrganoleptic = controlOrganoleptic;
	}


	public QualitatAiguaPuntSortida getPuntMostreig() {
		return puntMostreig;
	}


	public void setPuntMostreig(QualitatAiguaPuntSortida puntMostreig) {
		this.puntMostreig = puntMostreig;
	}


	public Double getResultat() {
		return resultat;
	}


	public void setResultat(Double resultat) {
		this.resultat = resultat;
	}


	public Boolean getOlor() {
		return olor;
	}


	public void setOlor(Boolean olor) {
		this.olor = olor;
	}


	public Boolean getColor() {
		return color;
	}


	public void setColor(Boolean color) {
		this.color = color;
	}


	public Boolean getSabor() {
		return sabor;
	}


	public void setSabor(Boolean sabor) {
		this.sabor = sabor;
	}


	public Boolean getTerbolesa() {
		return terbolesa;
	}


	public void setTerbolesa(Boolean terbolesa) {
		this.terbolesa = terbolesa;
	}


	public String getOlorString() {
		return olorString;
	}


	public void setOlorString(String olorString) {
		this.olorString = olorString;
	}


	public String getColorString() {
		return colorString;
	}


	public void setColorString(String colorString) {
		this.colorString = colorString;
	}


	public String getSaborString() {
		return saborString;
	}


	public void setSaborString(String saborString) {
		this.saborString = saborString;
	}


	public String getTerbolesaString() {
		return terbolesaString;
	}


	public void setTerbolesaString(String terbolesaString) {
		this.terbolesaString = terbolesaString;
	}
	
}