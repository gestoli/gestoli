package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.VarietatOliva;;

/**
 * Objecte que representa el formulari de manteniment de varietats experimentals.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class VarietatsExperimentalsCommand extends VarietatOliva {

	private static final long serialVersionUID = 1L;
	
	private Double rendiment;
	private String tipusRendimentCampanya;
	
	/**
	 * Rellena los campos de este objeto con la información del finca.
	 * 
	 * @param f Finca
	 */
	public void fromVarietatOlivaExperimental(VarietatOliva v) {
		setId(v.getId());
		setNom(v.getNom());
		setAutoritzada(v.getAutoritzada());
		setIcona(v.getIcona());
		setColor(v.getColor());
		setObservacions(v.getObservacions());
		setDescomposicioPlantacios(v.getDescomposicioPlantacios());
		setLots(v.getLots());
		setNomVarietat(v.getNomVarietat());
		setExperimental(v.getExperimental());
		setActiu(v.getActiu());
	}

	public Double getRendiment() {
		return rendiment;
	}
	public void setRendiment(Double rendiment) {
		this.rendiment = rendiment;
	}

	public String getTipusRendimentCampanya() {
		return tipusRendimentCampanya;
	}
	public void setTipusRendimentCampanya(String tipusRendimentCampanya) {
		this.tipusRendimentCampanya = tipusRendimentCampanya;
	}
}
