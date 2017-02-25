package es.caib.gestoli.logic.model;

import java.util.HashSet;
import java.util.Set;

public class QualitatAPPCCFitxaControl {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private QualitatAPPCCControl control;
	private Set<QualitatAPPCCFitxaControlHistoric> historicFitxa = new HashSet<QualitatAPPCCFitxaControlHistoric>(0);
	
	
	public QualitatAPPCCFitxaControl(){
		super();
	}
	
	public QualitatAPPCCFitxaControl(Long id, QualitatAPPCCControl control,
			Set<QualitatAPPCCFitxaControlHistoric> historicFitxa){
		this.id = id;
		this.control = control;
		this.historicFitxa = historicFitxa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualitatAPPCCControl getControl() {
		return control;
	}

	public void setControl(QualitatAPPCCControl control) {
		this.control = control;
	}

	public Set<QualitatAPPCCFitxaControlHistoric> getHistoricFitxa() {
		return historicFitxa;
	}

	public void setHistoricFitxa(
			Set<QualitatAPPCCFitxaControlHistoric> historicFitxa) {
		this.historicFitxa = historicFitxa;
	}

	
}
