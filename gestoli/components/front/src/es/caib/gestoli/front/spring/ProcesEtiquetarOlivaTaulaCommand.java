/**
 * ProcesLotCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Lot;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class ProcesEtiquetarOlivaTaulaCommand extends Lot {
	
	private Long[] idEtiquetes;
	private Integer[] etiquetesInicials;
	private Integer[] etiquetesFinals;
	
	public ProcesEtiquetarOlivaTaulaCommand() {}

	public ProcesEtiquetarOlivaTaulaCommand(Lot lot){
		setPartidaOli(lot.getPartidaOli());
		setCodiAssignat(lot.getCodiAssignat());
		setData(lot.getData());
		setDataConsum(lot.getDataConsum());
		setEtiquetatge(lot.getEtiquetatge());
		setId(lot.getId());
		setVarietatOlivas(lot.getVarietatOlivas());
		setZona(lot.getZona());
	}

	public Long[] getIdEtiquetes() {
		return idEtiquetes;
	}

	public void setIdEtiquetes(Long[] idEtiquetes) {
		this.idEtiquetes = idEtiquetes;
	}

	public Integer[] getEtiquetesInicials() {
		return etiquetesInicials;
	}

	public void setEtiquetesInicials(Integer[] etiquetesInicials) {
		this.etiquetesInicials = etiquetesInicials;
	}

	public Integer[] getEtiquetesFinals() {
		return etiquetesFinals;
	}

	public void setEtiquetesFinals(Integer[] etiquetesFinals) {
		this.etiquetesFinals = etiquetesFinals;
	}
	


	
}
