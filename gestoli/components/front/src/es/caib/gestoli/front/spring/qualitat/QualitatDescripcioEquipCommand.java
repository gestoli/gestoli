package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatDescripcioEquip;

/**
 * Objecte que representa el formulari de manteniment d'equips.
 * 
 * @author Jaume Morey Riera <jaumem@limit.es>
 */
public class QualitatDescripcioEquipCommand extends QualitatDescripcioEquip {

	private static final long serialVersionUID = 1L;

	private Long idEstabliment;
	private Long idUbicacio;

	/**
	 * Omple els camps d'aquest objecte amb la informació de l'equip.
	 * 
	 * @param qualitatDescripcioEquip QualitatDescripcioEquip
	 */
	public void fromQualitatDescripcioEquip(QualitatDescripcioEquip qualitatDescripcioEquip) {
		setCodi(qualitatDescripcioEquip.getCodi());
		setCodiUsuari(qualitatDescripcioEquip.getCodiUsuari());
		setNom(qualitatDescripcioEquip.getNom());
		setMarca(qualitatDescripcioEquip.getMarca());
		setUbicacio(qualitatDescripcioEquip.getUbicacio());
		if (qualitatDescripcioEquip.getUbicacio() != null){
			setIdUbicacio(qualitatDescripcioEquip.getUbicacio().getId());
		}
		setDataCompra(qualitatDescripcioEquip.getDataCompra());
		setNumSerie(qualitatDescripcioEquip.getNumSerie());
		setEstabliment(qualitatDescripcioEquip.getEstabliment());
		setEsVehicle (qualitatDescripcioEquip.getEsVehicle());
	}


	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Long getIdUbicacio() {
		return idUbicacio;
	}

	public void setIdUbicacio(Long idUbicacio) {
		this.idUbicacio = idUbicacio;
	}

}
