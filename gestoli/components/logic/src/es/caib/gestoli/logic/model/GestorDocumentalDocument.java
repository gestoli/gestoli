package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class GestorDocumentalDocument implements java.io.Serializable {

	private Integer id;

	private GestorDocumentalInformacio gestorDocumentalInformacio;

	private String titol;

	private Long arxiu;

	public GestorDocumentalDocument() {
	}

	public GestorDocumentalDocument(GestorDocumentalInformacio gestorDocumentalInformacio, String titol, Long arxiu) {
		this.gestorDocumentalInformacio = gestorDocumentalInformacio;
		this.titol = titol;
		this.arxiu = arxiu;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GestorDocumentalInformacio getGestorDocumentalInformacio() {
		return gestorDocumentalInformacio;
	}

	public void setGestorDocumentalInformacio(
			GestorDocumentalInformacio gestorDocumentalInformacio) {
		this.gestorDocumentalInformacio = gestorDocumentalInformacio;
	}

	public String getTitol() {
		return this.titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public Long getArxiu() {
		return this.arxiu;
	}

	public void setArxiu(Long arxiu) {
		this.arxiu = arxiu;
	}

}
