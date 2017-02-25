package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.DescomposicioPlantacio;

/**
 * Objeto que representa el formulario de mantenimento de descomposiciones.
 */
public class DescomposicioPlantacioCommand extends DescomposicioPlantacio {
		
	private static final long serialVersionUID = 7556985843867285706L;
	
	private Long idPlantacio;
	private Long[] idDescompPlantacion;
	private String[] idVariedadOliva;
	private Integer[] numOlivos;
	private Double[] superf;
	private Double[] prodMaxima;
	private Long[] idVarietatExperimental;
	private String[] tipusRendiment;
	private Double[] rendiment;

	/**
     * Rellena los campos de este objeto con la información de la descomposicio plantacio.
     * @param p DescomposicioPlantacio
     */
    public void fromDescomposicioPlantacio(DescomposicioPlantacio p) {
    }

	public Long[] getIdDescompPlantacion() {
		return idDescompPlantacion;
	}
	public void setIdDescompPlantacion(Long[] idDescompPlantacion) {
		this.idDescompPlantacion = idDescompPlantacion;
	}

	public Long getIdPlantacio() {
		return idPlantacio;
	}
	public void setIdPlantacio(Long idPlantacio) {
		this.idPlantacio = idPlantacio;
	}

	public String[] getIdVariedadOliva() {
		return idVariedadOliva;
	}
	public void setIdVariedadOliva(String[] idVariedadOliva) {
		this.idVariedadOliva = idVariedadOliva;
	}

	public Integer[] getNumOlivos() {
		return numOlivos;
	}
	public void setNumOlivos(Integer[] numOlivos) {
		this.numOlivos = numOlivos;
	}

	public Double[] getProdMaxima() {
		return prodMaxima;
	}
	public void setProdMaxima(Double[] prodMaxima) {
		this.prodMaxima = prodMaxima;
	}

	public Double[] getSuperf(){
		return superf;
	}
	public void setSuperf(Double[] superficie) {
		this.superf = superficie;
	}

	public Long[] getIdVarietatExperimental() {
		return idVarietatExperimental;
	}
	public void setIdVarietatExperimental(Long[] idVarietatExperimental) {
		this.idVarietatExperimental = idVarietatExperimental;
	}

	public String[] getTipusRendiment() {
		return tipusRendiment;
	}
	public void setTipusRendiment(String[] tipusRendiment) {
		this.tipusRendiment = tipusRendiment;
	}

	public Double[] getRendiment() {
		return rendiment;
	}
	public void setRendiment(Double[] rendiment) {
		this.rendiment = rendiment;
	}
}
