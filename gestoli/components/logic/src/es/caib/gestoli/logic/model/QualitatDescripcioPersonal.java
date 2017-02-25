package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class QualitatDescripcioPersonal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codi;
	private String nom;
	private String llinatges;
	private String dni;
	private Date dataNaixement;
	private String telefon;
	private String direccio;
	private String formacio;
	private String expLaboral;	
	private Date dataIncorporacio;
	private Date dataCarrec;
	private Set<QualitatFormacioEvaluacio> formacions = new HashSet<QualitatFormacioEvaluacio>(0);
	private Set<QualitatPlaFormacio> formacionsResponsable = new HashSet<QualitatPlaFormacio>(0);
	private Set<QualitatPlaFormacio> formacionsSupervisor = new HashSet<QualitatPlaFormacio>(0);
	private Set<QualitatPlaMantenimentControl> mantenimentsControlResponsable = new HashSet<QualitatPlaMantenimentControl>(0);
	
	private Set<Document> documents = new HashSet<Document>(0);

	private QualitatPuestoTreball carrec;
	
	private Establiment establiment;


	public QualitatDescripcioPersonal() {
		super();
	}

	public QualitatDescripcioPersonal(
			Establiment establiment, String nom, String llinatges,
			String dni, Date dataNaixement, String telefon, String direccio,
			String formacio, String expLaboral,
			Date dataIncorporacio, QualitatPuestoTreball carrec, Date dataCarrec, 
			Set<QualitatFormacioEvaluacio> formacions, 
			Set<QualitatPlaFormacio> formacionsResponsable, Set<QualitatPlaFormacio> formacionsSupervisor,
			Set<QualitatPlaMantenimentControl> mantenimentsControlResponsable,
			Set<Document> documents) {
		super();
		this.establiment = establiment;
		this.nom = nom;
		this.llinatges = llinatges;
		this.dni = dni;
		this.dataNaixement = dataNaixement;
		this.telefon = telefon;
		this.direccio = direccio;
		this.formacio = formacio;
		this.expLaboral = expLaboral;
		this.dataIncorporacio = dataIncorporacio;
		this.carrec = carrec;
		this.dataCarrec = dataCarrec;
		this.formacions = formacions;
		this.formacionsResponsable = formacionsResponsable;
		this.formacionsSupervisor = formacionsSupervisor;
		this.mantenimentsControlResponsable = mantenimentsControlResponsable;
		this.documents = documents;
	}



	public Long getCodi() {
		return codi;
	}

	public void setCodi(Long codi) {
		this.codi = codi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLlinatges() {
		return llinatges;
	}

	public void setLlinatges(String llinatges) {
		this.llinatges = llinatges;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getDataNaixement() {
		return dataNaixement;
	}

	public void setDataNaixement(Date dataNaixement) {
		this.dataNaixement = dataNaixement;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getDireccio() {
		return direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getFormacio() {
		return formacio;
	}

	public void setFormacio(String formacio) {
		this.formacio = formacio;
	}

	public String getExpLaboral() {
		return expLaboral;
	}

	public void setExpLaboral(String expLaboral) {
		this.expLaboral = expLaboral;
	}

	public Date getDataIncorporacio() {
		return dataIncorporacio;
	}

	public void setDataIncorporacio(Date dataIncorporacio) {
		this.dataIncorporacio = dataIncorporacio;
	}

	public QualitatPuestoTreball getCarrec() {
		return carrec;
	}

	public void setCarrec(QualitatPuestoTreball carrec) {
		this.carrec = carrec;
	}

	public Date getDataCarrec() {
		return dataCarrec;
	}

	public void setDataCarrec(Date dataCarrec) {
		this.dataCarrec = dataCarrec;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Set<QualitatFormacioEvaluacio> getFormacions() {
		return formacions;
	}

	public void setFormacions(Set<QualitatFormacioEvaluacio> formacions) {
		this.formacions = formacions;
	}

	public Set<QualitatPlaFormacio> getFormacionsResponsable() {
		return formacionsResponsable;
	}

	public void setFormacionsResponsable(
			Set<QualitatPlaFormacio> formacionsResponsable) {
		this.formacionsResponsable = formacionsResponsable;
	}

	public Set<QualitatPlaFormacio> getFormacionsSupervisor() {
		return formacionsSupervisor;
	}

	public void setFormacionsSupervisor(
			Set<QualitatPlaFormacio> formacionsSupervisor) {
		this.formacionsSupervisor = formacionsSupervisor;
	}
	
	public Set<QualitatPlaMantenimentControl> getMantenimentsControlResponsable() {
		return mantenimentsControlResponsable;
	}

	public void setMantenimentsControlResponsable(
			Set<QualitatPlaMantenimentControl> mantenimentsControlResponsable) {
		this.mantenimentsControlResponsable = mantenimentsControlResponsable;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}


	
}