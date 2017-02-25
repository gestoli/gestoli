package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Informacio implements java.io.Serializable {

	private Integer id;

	private CategoriaInformacio categoriaInformacio;

	private Date data;

	private String titol;

	private String texte;

	private Set documents = new HashSet(0);

	private Set establiments = new HashSet(0);

	private Set olivicultors = new HashSet(0);

	public Informacio() {
	}

	public Informacio(CategoriaInformacio categoriaInformacio, Date data,
			String titol) {
		this.categoriaInformacio = categoriaInformacio;
		this.data = data;
		this.titol = titol;
	}

	public Informacio(CategoriaInformacio categoriaInformacio, Date data,
			String titol, String texte, Set documents, Set establiments,
			Set olivicultors) {
		this.categoriaInformacio = categoriaInformacio;
		this.data = data;
		this.titol = titol;
		this.texte = texte;
		this.documents = documents;
		this.establiments = establiments;
		this.olivicultors = olivicultors;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoriaInformacio getCategoriaInformacio() {
		return this.categoriaInformacio;
	}

	public void setCategoriaInformacio(CategoriaInformacio categoriaInformacio) {
		this.categoriaInformacio = categoriaInformacio;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitol() {
		return this.titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getTexte() {
		return this.texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Set getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set documents) {
		this.documents = documents;
	}

	public Set getEstabliments() {
		return this.establiments;
	}

	public void setEstabliments(Set establiments) {
		this.establiments = establiments;
	}

	public Set getOlivicultors() {
		return this.olivicultors;
	}

	public void setOlivicultors(Set olivicultors) {
		this.olivicultors = olivicultors;
	}

	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve true si tiene documentos
	 */
	public boolean getTeDocuments() {
		return documents.size() > 0;
	}

	// end of extra code specified in the hbm.xml files

}
