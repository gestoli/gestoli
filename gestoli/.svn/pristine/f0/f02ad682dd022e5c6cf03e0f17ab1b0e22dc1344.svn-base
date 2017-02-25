package es.caib.gestoli.logic.model;

import java.util.Date;


public class QualitatFormacioEvaluacio extends QualitatControl {
	
	private static final long serialVersionUID = 1L;

	private QualitatPlaFormacio formacio;
	private QualitatDescripcioPersonal treballador;
	
	public QualitatFormacioEvaluacio() {
		super();
	}

	public QualitatFormacioEvaluacio(
			Long id, String objectiu, Date dataRevisio, 
			QualitatDescripcioPersonal responsableVerificacio,
			Boolean efectiva, QualitatPlaFormacio formacio, 
			QualitatDescripcioPersonal treballador) {
		this.id = id;
		this.treballador = treballador;
		this.objectiu = objectiu;
		this.dataVerificacio = dataRevisio;
		this.responsableVerificacio = responsableVerificacio;
		this.satisfactori = efectiva;
		this.formacio = formacio;
	}

	
	public QualitatPlaFormacio getFormacio() {
		return formacio;
	}

	public void setFormacio(QualitatPlaFormacio formacio) {
		this.formacio = formacio;
	}

	public QualitatDescripcioPersonal getTreballador() {
		return treballador;
	}

	public void setTreballador(QualitatDescripcioPersonal treballador) {
		this.treballador = treballador;
	}
	
}
