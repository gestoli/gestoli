package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Etiquetatge implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private TipusEnvas tipusEnvas;

	private Marca marca;

	private Boolean actiu;

	private Long imatgeFrontal;

	private Long imatgePosterior;

	private String observacions;

	public Etiquetatge() {
	}

	public Etiquetatge(TipusEnvas tipusEnvas, Marca marca, Boolean actiu,
			Long imatgeFrontal, Long imatgePosterior) {
		this.tipusEnvas = tipusEnvas;
		this.marca = marca;
		this.actiu = actiu;
		this.imatgeFrontal = imatgeFrontal;
		this.imatgePosterior = imatgePosterior;
	}

	public Etiquetatge(TipusEnvas tipusEnvas, Marca marca, Boolean actiu,
			Long imatgeFrontal, Long imatgePosterior, String observacions) {
		this.tipusEnvas = tipusEnvas;
		this.marca = marca;
		this.actiu = actiu;
		this.imatgeFrontal = imatgeFrontal;
		this.imatgePosterior = imatgePosterior;
		this.observacions = observacions;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipusEnvas getTipusEnvas() {
		return this.tipusEnvas;
	}

	public void setTipusEnvas(TipusEnvas tipusEnvas) {
		this.tipusEnvas = tipusEnvas;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Boolean getActiu() {
		return this.actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public Long getImatgeFrontal() {
		return this.imatgeFrontal;
	}

	public void setImatgeFrontal(Long imatgeFrontal) {
		this.imatgeFrontal = imatgeFrontal;
	}

	public Long getImatgePosterior() {
		return this.imatgePosterior;
	}

	public void setImatgePosterior(Long imatgePosterior) {
		this.imatgePosterior = imatgePosterior;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	public String getNomEnvas() {
		if (this.tipusEnvas == null) return null;
		String nom= this.tipusEnvas.getNomSelect();
		
		if(this.observacions != null && !this.observacions.equals("")){
			nom += " (" + this.observacions + ")";
		}
		
		return nom;
	}

}
