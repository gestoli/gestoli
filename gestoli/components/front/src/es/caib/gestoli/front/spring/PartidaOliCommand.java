package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.PartidaOli;

public class PartidaOliCommand extends PartidaOli{

	private Integer categoriaId;
	private Long establimentId;
	
	/**
     * Rellena los campos de este objeto con la información
     * del zona.
     * @param z Zona
     */
    public void fromPartidaOli(PartidaOli p) {
    	this.setEsActiu(p.getEsActiu());
    	this.setEstabliment(p.getEstabliment());
    	this.setId(p.getId());
    	this.setNom(p.getNom());
    	this.setCategoriaOli(p.getCategoriaOli());
    	this.setCategoriaId(p.getCategoriaOli().getId());
    	this.setEstablimentId(p.getEstabliment().getId());
    	this.setEsVisualitza(p.getEsVisualitza());
    	this.setOlivicultorEsPropietari(p.getOlivicultorEsPropietari());
    	this.setEsEcologic(p.getEsEcologic());
    }

	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Long getEstablimentId() {
		return establimentId;
	}
	public void setEstablimentId(Long establimentId) {
		this.establimentId = establimentId;
	}
}
