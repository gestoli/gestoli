package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class TipusEnvas implements java.io.Serializable {

	private Long id;

	private MaterialTipusEnvas materialTipusEnvas;

	private Color color;

	private Boolean actiu;

	private Double volum;

	private String observacions;

	public TipusEnvas() {
	}

	public TipusEnvas(MaterialTipusEnvas materialTipusEnvas, Color color,
			Boolean actiu, Double volum) {
		this.materialTipusEnvas = materialTipusEnvas;
		this.color = color;
		this.actiu = actiu;
		this.volum = volum;
	}

	public TipusEnvas(MaterialTipusEnvas materialTipusEnvas, Color color,
			Boolean actiu, Double volum, String observacions) {
		this.materialTipusEnvas = materialTipusEnvas;
		this.color = color;
		this.actiu = actiu;
		this.volum = volum;
		this.observacions = observacions;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialTipusEnvas getMaterialTipusEnvas() {
		return this.materialTipusEnvas;
	}

	public void setMaterialTipusEnvas(MaterialTipusEnvas materialTipusEnvas) {
		this.materialTipusEnvas = materialTipusEnvas;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean getActiu() {
		return this.actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public Double getVolum() {
		return this.volum;
	}

	public void setVolum(Double volum) {
		this.volum = volum;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve una descripcion de un tipo de envase
	 */
	public String getNomSelect() {
		String nomSelect = this.volum.toString() + " - "
				+ this.materialTipusEnvas.getNom() + " - "
				+ this.color.getNom();
		return nomSelect;
	}

	// end of extra code specified in the hbm.xml files

}
