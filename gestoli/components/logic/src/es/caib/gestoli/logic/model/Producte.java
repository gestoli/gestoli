package es.caib.gestoli.logic.model;


import java.util.HashSet;
import java.util.Set;

/**
 * Javadoc de les clases Model de Gest Oli
 * 
 * 
 */
public class Producte implements java.io.Serializable {

	private Long id;
	private Boolean actiu;
	private String nom;
	private Establiment establiment;
	private Set lots = new HashSet(0);

	public Producte() {
	}

	public Producte(Boolean actiu, String nom) {
		this.actiu = actiu;
		this.nom = nom;
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
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Establiment getEstabliment() {
		return establiment;
	}
	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Set getLots() {
		return lots;
	}
	public void setLots(Set lots) {
		this.lots = lots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actiu == null) ? 0 : actiu.hashCode());
		result = prime * result
				+ ((nom == null) ? 0 : nom.hashCode());
		result = prime * result
				+ ((establiment == null) ? 0 : establiment.hashCode());
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
		Producte other = (Producte) obj;
		if (actiu == null) {
			if (other.actiu != null)
				return false;
		} else if (!actiu.equals(other.actiu))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (establiment == null) {
			if (other.establiment != null)
				return false;
		} else if (!establiment.equals(other.establiment))
			return false;
		return true;
	}

}
