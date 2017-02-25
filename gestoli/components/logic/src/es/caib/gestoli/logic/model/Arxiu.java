package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:50:43 by Hibernate Tools 3.2.0.b9

import java.text.DecimalFormat;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Arxiu implements java.io.Serializable {

	private Long id;

	private String nom;

	private String mime;

	private Integer tamany;

	private Integer width;

	private Integer height;

	private byte[] binari;

	public Arxiu() {
	}

	public Arxiu(String nom, String mime, Integer tamany, byte[] binari) {
		this.nom = nom;
		this.mime = mime;
		this.tamany = tamany;
		this.binari = binari;
	}

	public Arxiu(String nom, String mime, Integer tamany, Integer width,
			Integer height, byte[] binari) {
		this.nom = nom;
		this.mime = mime;
		this.tamany = tamany;
		this.width = width;
		this.height = height;
		this.binari = binari;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMime() {
		return this.mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public Integer getTamany() {
		return this.tamany;
	}

	public void setTamany(Integer tamany) {
		this.tamany = tamany;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public byte[] getBinari() {
		return this.binari;
	}

	public void setBinari(byte[] binari) {
		this.binari = binari;
	}

	// The following is extra code specified in the hbm.xml files

	/**
	 * Method 'getNormalizeSize'
	 * @return
	 */
	public String getNormalizeSize() {
		double miles = 1024.0;
		double pes = tamany.doubleValue();
		String normalizeSize = "";
		if (pes != 0) {
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			if (pes < miles) {
				normalizeSize = df.format(pes) + " Bytes";
			} else {
				pes = tamany.doubleValue() / miles;
				normalizeSize = df.format(pes) + " Kb";
				if (pes > miles) {
					pes = pes / miles;
					normalizeSize = df.format(pes) + " Mb";
				}
			}
		}
		return normalizeSize;
	}

	// end of extra code specified in the hbm.xml files

}
