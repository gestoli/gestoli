package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QualitatPlaFormacio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descripcio;
	private String continguts;
	private Boolean periodic;
	private String frecuencia; //tri->Trimestral / sem->Semestral / anu->Anual
	private Date dataPrevista;
	
	private Set<QualitatFormacioEvaluacio> personalAssistent = new HashSet(0);
	private Set<QualitatPuestoTreball> carrecAssistent = new HashSet(0);
	
	private Boolean isResponsableIntern; 	// True -> responsableIntern // False -> responsableExtern
	private QualitatDescripcioPersonal responsableIntern;
	private String responsableExtern; //15 caracteres
	
	private Integer duracio;
	private String duracioTipus; //h->Horas / d->Días / s->Semanas 
	
	private QualitatDescripcioPersonal supervisorFormacio;
	private String activitatSupervisio;
	private Date dataSupervisio;
	
	private Establiment establiment;


	public QualitatPlaFormacio() {
		super();
	}

	public QualitatPlaFormacio(
			Establiment establiment, String descripcio, String continguts, Boolean periodic,
			String frecuencia, Date dataPrevista, Set<QualitatFormacioEvaluacio> personalAssistent,
			Set<QualitatPuestoTreball> carrecAssistent,	Boolean isResponsableIntern, 
			QualitatDescripcioPersonal responsableIntern, String responsableExtern, Integer duracio,
			String duracioTipus, QualitatDescripcioPersonal supervisorFormacio, String activitatSupervisio,
			Date dataSupervisio) {
		super();
		this.establiment = establiment;
		this.descripcio = descripcio;
		this.continguts = continguts;
		this.periodic = periodic;
		this.frecuencia = frecuencia;
		this.dataPrevista = dataPrevista;
		this.personalAssistent = personalAssistent;
		this.carrecAssistent = carrecAssistent;
		this.isResponsableIntern = isResponsableIntern;
		this.responsableIntern = responsableIntern;
		this.responsableExtern = responsableExtern;
		this.duracio = duracio;
		this.duracioTipus = duracioTipus;
		this.supervisorFormacio = supervisorFormacio;
		this.activitatSupervisio = activitatSupervisio;
		this.dataSupervisio = dataSupervisio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getContinguts() {
		return continguts;
	}

	public void setContinguts(String continguts) {
		this.continguts = continguts;
	}

	public Boolean getPeriodic() {
		return periodic;
	}

	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Set<QualitatFormacioEvaluacio> getPersonalAssistent() {
		return personalAssistent;
	}

	public void setPersonalAssistent(
			Set<QualitatFormacioEvaluacio> personalAssistent) {
		this.personalAssistent = personalAssistent;
	}

	public Set<QualitatPuestoTreball> getCarrecAssistent() {
		return carrecAssistent;
	}

	public void setCarrecAssistent(Set<QualitatPuestoTreball> carrecAssistent) {
		this.carrecAssistent = carrecAssistent;
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

	public Integer getDuracio() {
		return duracio;
	}

	public void setDuracio(Integer duracio) {
		this.duracio = duracio;
	}

	public String getDuracioTipus() {
		return duracioTipus;
	}

	public void setDuracioTipus(String duracioTipus) {
		this.duracioTipus = duracioTipus;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public QualitatDescripcioPersonal getSupervisorFormacio() {
		return supervisorFormacio;
	}

	public void setSupervisorFormacio(QualitatDescripcioPersonal supervisorFormacio) {
		this.supervisorFormacio = supervisorFormacio;
	}

	public String getActivitatSupervisio() {
		return activitatSupervisio;
	}

	public void setActivitatSupervisio(String activitatSupervisio) {
		this.activitatSupervisio = activitatSupervisio;
	}

	public Date getDataSupervisio() {
		return dataSupervisio;
	}

	public void setDataSupervisio(Date dataSupervisio) {
		this.dataSupervisio = dataSupervisio;
	}


}
