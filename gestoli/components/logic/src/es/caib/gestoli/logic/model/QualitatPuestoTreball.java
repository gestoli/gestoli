package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class QualitatPuestoTreball implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer nivellJerarquic;
	private String carrec;
	private QualitatPuestoTreball carrecSuperior;
	private String funcions;
	private String formacio;
	private String experiencia;
	
	private Establiment establiment;
	
	private Set<QualitatDescripcioPersonal> personal = new HashSet<QualitatDescripcioPersonal>(0);
	private Set<QualitatPuestoTreball> carrecsInferiors = new HashSet<QualitatPuestoTreball>(0);
	
	public QualitatPuestoTreball() {
		super();
	}

	public QualitatPuestoTreball(
			Establiment establiment, String carrec, Integer nivellJerarquic,
			QualitatPuestoTreball carrecSuperior, String funcions, 
			String formacio, String experiencia, Set<QualitatDescripcioPersonal> personal,
			Set<QualitatPuestoTreball> carrecsInferiors) {
		super();
		this.establiment = establiment;
		this.carrec = carrec;
		this.nivellJerarquic = nivellJerarquic;
		this.carrecSuperior = carrecSuperior;
		this.funcions = funcions;
		this.formacio = formacio;
		this.experiencia = experiencia;
		this.personal = personal;
		this.carrecsInferiors = carrecsInferiors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarrec() {
		return carrec;
	}

	public void setCarrec(String carrec) {
		this.carrec = carrec;
	}

	public Integer getNivellJerarquic() {
		return nivellJerarquic;
	}

	public void setNivellJerarquic(Integer nivellJerarquic) {
		this.nivellJerarquic = nivellJerarquic;
	}

	public QualitatPuestoTreball getCarrecSuperior() {
		return carrecSuperior;
	}

	public void setCarrecSuperior(QualitatPuestoTreball carrecSuperior) {
		this.carrecSuperior = carrecSuperior;
	}

	public String getFuncions() {
		return funcions;
	}

	public void setFuncions(String funcions) {
		this.funcions = funcions;
	}

	public String getFormacio() {
		return formacio;
	}

	public void setFormacio(String formacio) {
		this.formacio = formacio;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	public Set<QualitatDescripcioPersonal> getPersonal() {
		return personal;
	}

	public void setPersonal(Set<QualitatDescripcioPersonal> personal) {
		this.personal = personal;
	}
	
	public Set<QualitatPuestoTreball> getCarrecsInferiors() {
		return carrecsInferiors;
	}

	public void setCarrecsInferiors(Set<QualitatPuestoTreball> carrecsInferiors) {
		this.carrecsInferiors = carrecsInferiors;
	}

}
