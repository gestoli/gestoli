package es.caib.gestoli.logic.model;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class DescomposicioPlantacio implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Plantacio plantacio;
	private VarietatOliva varietatOliva;
	private Long idOriginal;
	private Integer numeroOliveres;
	private Double superficie;
	private Double maxProduccio;
	private String observacions;
	private Double produccioRestant;

	public DescomposicioPlantacio() {
	}

	public DescomposicioPlantacio(Plantacio plantacio, VarietatOliva varietatOliva,
			Integer numeroOliveres, Double superficie, Double maxProduccio) {
		this.plantacio = plantacio;
		this.varietatOliva = varietatOliva;
		this.numeroOliveres = numeroOliveres;
		this.superficie = superficie;
		this.maxProduccio = maxProduccio;
	}

	public DescomposicioPlantacio(Plantacio plantacio, VarietatOliva varietatOliva,
			Long idOriginal, Integer numeroOliveres, Double superficie, Double maxProduccio,
			String observacions, Double produccioRestant) {
		this.plantacio = plantacio;
		this.varietatOliva = varietatOliva;
		this.idOriginal = idOriginal;
		this.numeroOliveres = numeroOliveres;
		this.superficie = superficie;
		this.maxProduccio = maxProduccio;
		this.observacions = observacions;
		this.produccioRestant = produccioRestant;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Plantacio getPlantacio() {
		return this.plantacio;
	}
	public void setPlantacio(Plantacio plantacio) {
		this.plantacio = plantacio;
	}

	public VarietatOliva getVarietatOliva() {
		return this.varietatOliva;
	}
	public void setVarietatOliva(VarietatOliva varietatOliva) {
		this.varietatOliva = varietatOliva;
	}

	public Long getIdOriginal() {
		return this.idOriginal;
	}
	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public Integer getNumeroOliveres() {
		return this.numeroOliveres;
	}
	public void setNumeroOliveres(Integer numeroOliveres) {
		this.numeroOliveres = numeroOliveres;
	}

	public Double getSuperficie() {
		return this.superficie;
	}
	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public Double getMaxProduccio() {
		return this.maxProduccio;
	}
	public void setMaxProduccio(Double maxProduccio) {
		this.maxProduccio = maxProduccio;
	}

	public String getObservacions() {
		return this.observacions;
	}
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Double getProduccioRestant() {
		return produccioRestant;
	}

	public void setProduccioRestant(Double produccioRestant) {
		this.produccioRestant = produccioRestant;
	}
}
