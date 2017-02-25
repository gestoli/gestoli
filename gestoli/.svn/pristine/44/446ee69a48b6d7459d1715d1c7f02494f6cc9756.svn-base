package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Javadoc de les clases Model de Gest Oli
 * 
 * @author Oriol Barnes Cruz (obarnes@at4.net)
 * 
 */
public class Marca implements java.io.Serializable {

	private Long id;
	private Boolean actiu;
	private String nom;
	private Boolean denominacioOrigen;
	private Boolean olivaTaula;
	private String observacions;
	private Set etiquetatges = new HashSet(0);
	private Set establiments = new HashSet(0);
	private Date dataAlta;
	private Date dataBaixa;

	public Marca() {
	}

	public Marca(Boolean actiu, String nom, Boolean denominacioOrigen) {
		this.actiu = actiu;
		this.nom = nom;
		this.denominacioOrigen = denominacioOrigen;
	}

	public Marca(Boolean actiu, String nom, Boolean denominacioOrigen,
			String observacions, Set etiquetatges, Set establiments) {
		this.actiu = actiu;
		this.nom = nom;
		this.denominacioOrigen = denominacioOrigen;
		this.observacions = observacions;
		this.etiquetatges = etiquetatges;
		this.establiments = establiments;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActiu() {
		return this.actiu;
	}
	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getDenominacioOrigen() {
		return this.denominacioOrigen;
	}
	public void setDenominacioOrigen(Boolean denominacioOrigen) {
		this.denominacioOrigen = denominacioOrigen;
	}

	public Boolean getOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(Boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}

	public String getObservacions() {
		return this.observacions;
	}
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getEtiquetatges() {
		return this.etiquetatges;
	}
	public void setEtiquetatges(Set etiquetatges) {
		this.etiquetatges = etiquetatges;
	}

	public Set getEstabliments() {
		return this.establiments;
	}
	public void setEstabliments(Set establiments) {
		this.establiments = establiments;
	}
	
	public Date getDataAlta() {
		return dataAlta;
	}
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}
	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
