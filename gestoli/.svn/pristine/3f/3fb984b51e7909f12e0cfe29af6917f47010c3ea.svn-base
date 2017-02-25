package es.caib.gestoli.logic.model;

// Generated 30-nov-2009 17:09:50 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Diposit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private PartidaOli partidaOli;
	private Zona zona;
	private Zona zonaOrigenTrasllat;
	private Establiment establiment;
	private MaterialDiposit materialDiposit;
	private Long idOriginal;
	private Boolean actiu;
	private String codiAssignat;
	private Boolean fictici;
	private Double capacitat;
	private Integer posicioX;
	private Integer posicioY;
	private Double volumActual;
	private Double acidesa;
	private String observacions;
	private Double volumTrasllat;
	private String codiOriginal;
	private Boolean precintat;
	private Boolean deEnvasadora;

	public Diposit() {
	}

	public Diposit(Zona zona, Establiment establiment, Boolean actiu,
			String codiAssignat, Boolean fictici) {
		this.zona = zona;
		this.establiment = establiment;
		this.actiu = actiu;
		this.codiAssignat = codiAssignat;
		this.fictici = fictici;
		this.precintat = false;
		this.zonaOrigenTrasllat = zona;
	}

	public Diposit(PartidaOli partidaOli, Zona zona,
			Establiment establiment, MaterialDiposit materialDiposit,
			Long idOriginal, Boolean actiu, String codiAssignat,
			Boolean fictici, Double capacitat, Integer posicioX,
			Integer posicioY, Double volumActual, Double acidesa,
			String observacions,
			String codiOriginal) {
		this.partidaOli = partidaOli;
		this.zona = zona;
		this.zonaOrigenTrasllat = zona;
		this.establiment = establiment;
		this.materialDiposit = materialDiposit;
		this.idOriginal = idOriginal;
		this.actiu = actiu;
		this.codiAssignat = codiAssignat;
		this.fictici = fictici;
		this.capacitat = capacitat;
		this.posicioX = posicioX;
		this.posicioY = posicioY;
		this.volumActual = volumActual;
		this.acidesa = acidesa;
		this.observacions = observacions;
		this.codiOriginal = codiOriginal;
		this.precintat = false;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the partidaOli
	 */
	public PartidaOli getPartidaOli() {
		return partidaOli;
	}

	/**
	 * @param partidaOli the partidaOli to set
	 */
	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
	}
	
	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Establiment getEstabliment() {
		return this.establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public MaterialDiposit getMaterialDiposit() {
		return this.materialDiposit;
	}

	public void setMaterialDiposit(MaterialDiposit materialDiposit) {
		this.materialDiposit = materialDiposit;
	}

	public Long getIdOriginal() {
		return this.idOriginal;
	}

	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public Boolean getActiu() {
		return this.actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public String getCodiAssignat() {
		return this.codiAssignat;
	}

	public void setCodiAssignat(String codiAssignat) {
		this.codiAssignat = codiAssignat;
	}

	public Boolean getFictici() {
		return this.fictici;
	}

	public void setFictici(Boolean fictici) {
		this.fictici = fictici;
	}

	public Double getCapacitat() {
		return this.capacitat;
	}

	public void setCapacitat(Double capacitat) {
		this.capacitat = capacitat;
	}

	public Integer getPosicioX() {
		return this.posicioX;
	}

	public void setPosicioX(Integer posicioX) {
		this.posicioX = posicioX;
	}

	public Integer getPosicioY() {
		return this.posicioY;
	}

	public void setPosicioY(Integer posicioY) {
		this.posicioY = posicioY;
	}

	public Double getVolumActual() {
		return this.volumActual;
	}

	public void setVolumActual(Double volumActual) {
		this.volumActual = volumActual;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Double getVolumTrasllat() {
		return volumTrasllat;
	}

	public void setVolumTrasllat(Double volumTrasllat) {
		this.volumTrasllat = volumTrasllat;
	}
	
	public String getCodiOriginal() {
		return codiOriginal;
	}

	public void setCodiOriginal(String codiOriginal) {
		this.codiOriginal = codiOriginal;
	}
	
	public Boolean getPrecintat() {
		
		if(precintat == null) return false;
		
		return precintat;
	}

	public void setPrecintat(Boolean precintat) {
		this.precintat = precintat;
	}

	public Zona getZonaOrigenTrasllat() {
		return zonaOrigenTrasllat;
	}

	public void setZonaOrigenTrasllat(Zona zonaOrigenTrasllat) {
		this.zonaOrigenTrasllat = zonaOrigenTrasllat;
	}
	
	public Boolean getDeEnvasadora() {
		return deEnvasadora;
	}

	public void setDeEnvasadora(Boolean deEnvasadora) {
		this.deEnvasadora = deEnvasadora;
	}
	
	// The following is extra code specified in the hbm.xml files
	/**
	 * Devuelve el tipo del icono de deposito
	 */
	public Long getTipo() {
		Long tipo = null;
		if (this.materialDiposit != null) {
			tipo = this.materialDiposit.getIcona();
		}
		return tipo;
	}

	/**
	 * Devuelve el aceite disponible en kilos
	 */
	public Double getDisponibleOliQuilos() {
		Double cantidad = null;
		if (this.getVolumActual() != null) {
			//PASAMOS A KG
			cantidad = Double.valueOf(String.valueOf((Double.valueOf("0.916")
					.doubleValue() * this.getVolumActual().doubleValue())));
		}
		
		/* Redondejam */
		if(cantidad != null)
			return Round(cantidad,2);
		else
			return null;
	}
	
	/**
	 * Devuelve el aceite disponible en kilos
	 */
	public Double getDisponibleTrasllatOliQuilos() {
		Double cantidad = null;
		if (this.getVolumTrasllat() != null) {
			//PASAMOS A KG
			cantidad = Double.valueOf(String.valueOf((Double.valueOf("0.916")
					.doubleValue() * this.getVolumTrasllat().doubleValue())));
		}
		
		/* Redondejam */
		if(cantidad != null)
			return Round(cantidad,2);
		else
			return null;
	}
	
	public static double Round(double Rval, int Rpl) {
		double p = (double)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  double tmp = Math.round(Rval);
		  return (double)tmp/p;
	}
	// end of extra code specified in the hbm.xml files

}
