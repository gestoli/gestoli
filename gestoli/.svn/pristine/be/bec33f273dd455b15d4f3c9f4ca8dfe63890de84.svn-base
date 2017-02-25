package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QualitatNoConformitat implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataNoConformitat;
	private QualitatDescripcioPersonal responsableDeteccio;
	private String descripcio;
	private String causa;
	private Date dataTancament;
	
	private Set<QualitatNoConformitatAccio> accions = new HashSet<QualitatNoConformitatAccio>(0);
	
	private QualitatDepartament departament;
	private QualitatControl control;
	private Establiment establiment;
	
	public QualitatNoConformitat() {
		super();
	}
	
	public QualitatNoConformitat(Long id, Date dataNoConformitat, QualitatDescripcioPersonal responsableDeteccio,
			String descripcio, String causa, Date dataTancament, Set<QualitatNoConformitatAccio> accions, 
			QualitatDepartament departament, QualitatControl control, Establiment establiment){
		this.id = id;
		this.dataNoConformitat = dataNoConformitat;
		this.responsableDeteccio = responsableDeteccio;
		this.descripcio = descripcio;
		this.causa = causa;
		this.accions = accions;
		this.departament = departament;
		this.control = control;
		this.establiment = establiment;
		this.dataTancament = dataTancament;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataNoConformitat() {
		return dataNoConformitat;
	}

	public void setDataNoConformitat(Date dataNoConformitat) {
		this.dataNoConformitat = dataNoConformitat;
	}

	public QualitatDescripcioPersonal getResponsableDeteccio() {
		return responsableDeteccio;
	}

	public void setResponsableDeteccio(
			QualitatDescripcioPersonal responsableDeteccio) {
		this.responsableDeteccio = responsableDeteccio;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	public Date getDataTancament() {
		return dataTancament;
	}

	public void setDataTancament(Date dataTancament) {
		this.dataTancament = dataTancament;
	}

	public Set<QualitatNoConformitatAccio> getAccions() {
		return accions;
	}

	public void setAccions(Set<QualitatNoConformitatAccio> accions) {
		this.accions = accions;
	}
	
	public QualitatDepartament getDepartament() {
		return departament;
	}

	public void setDepartament(QualitatDepartament departament) {
		this.departament = departament;
	}

	public QualitatControl getControl() {
		return control;
	}

	public void setControl(QualitatControl control) {
		this.control = control;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	
	
}
