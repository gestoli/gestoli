package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Usuari implements java.io.Serializable {

	private Long id;
	private Idioma idioma;
	private Boolean actiu;
	private String usuari;
	private String contrasenya;
	private String email;
	private String observacions;
//	private Establiment establiment;
//	private Olivicultor olivicultor;
	
	private Set grups = new HashSet(0);
	private Set olivicultors = new HashSet(0);
	private Set establiments = new HashSet(0);

	public Usuari() {
	}

	public Usuari(Idioma idioma, Boolean actiu, String usuari,
			String contrasenya) {
		this.idioma = idioma;
		this.actiu = actiu;
		this.usuari = usuari;
		this.contrasenya = contrasenya;
	}

	public Usuari(Idioma idioma, Boolean actiu, String usuari,
			String contrasenya, String email, String observacions, Establiment establiment, 
			Olivicultor olivicultor, Set grups) {//, Set olivicultors, Set establiments) {
		this.idioma = idioma;
		this.actiu = actiu;
		this.usuari = usuari;
		this.contrasenya = contrasenya;
		this.email = email;
//		this.establiment = establiment;
//		this.olivicultor = olivicultor;
		this.observacions = observacions;
		this.grups = grups;
		this.olivicultors = olivicultors;
		this.establiments = establiments;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Idioma getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Boolean getActiu() {
		return this.actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public String getUsuari() {
		return this.usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getContrasenya() {
		return this.contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getGrups() {
		return this.grups;
	}

	public void setGrups(Set grups) {
		this.grups = grups;
	}

	public Set getOlivicultors() {
		return this.olivicultors;
	}

	public void setOlivicultors(Set olivicultors) {
		this.olivicultors = olivicultors;
	}

//	public Olivicultor getOlivicultor() {
//		return olivicultor;
//	}
//
//	public void setOlivicultor(Olivicultor olivicultor) {
//		this.olivicultor = olivicultor;
//	}

	public Set getEstabliments() {
		return this.establiments;
	}

	public void setEstabliments(Set establiments) {
		this.establiments = establiments;
	}
	
//	public Establiment getEstabliment() {
//		return establiment;
//	}
//
//	public void setEstabliment(Establiment establiment) {
//		this.establiment = establiment;
//	}
	
	// The following is extra code specified in the hbm.xml files

	public Set getSortedGrups() {
		return (new TreeSet(this.grups));
	}

	// end of extra code specified in the hbm.xml files

}
