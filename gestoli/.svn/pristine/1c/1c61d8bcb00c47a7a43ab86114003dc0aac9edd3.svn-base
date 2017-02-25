package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Producte;

public class ProducteCommand extends Producte{

	private Long establimentId;
	
	/**
     * Omple els camps d'aquest objete amb la informació del producte.
     * @param p Producte
     */
    public void fromProducte(Producte p) {
    	this.setActiu(p.getActiu());
    	this.setEstabliment(p.getEstabliment());
    	this.setId(p.getId());
    	this.setNom(p.getNom());
    }

	public Long getEstablimentId() {
		return establimentId;
	}
	public void setEstablimentId(Long establimentId) {
		this.establimentId = establimentId;
	}
}
