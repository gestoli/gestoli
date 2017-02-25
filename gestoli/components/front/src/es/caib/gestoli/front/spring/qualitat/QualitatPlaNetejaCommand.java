package es.caib.gestoli.front.spring.qualitat;

import es.caib.gestoli.logic.model.QualitatPlaNeteja;

public class QualitatPlaNetejaCommand  {
	
	private static final long serialVersionUID = 1L;
	
	private Long codi;
	private Boolean esGeneral;
	private String accio;
	private String elementPlanta;
	private Long equipId;
	private Long producteId;
	private String dosis;
	private String frequencia;
	private Long responsableId;
	private Long respVerificacioId;
	private String periodicitatVerificacio;
	private Long establimentId;
	 
	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatPlaNeteja(QualitatPlaNeteja pla) {
    	setCodi(pla.getCodi());
    	setAccio(pla.getAccio());
    	setEsGeneral (pla.getEsGeneral());
    	setElementPlanta(pla.getElementPlanta());
    	if (pla.getEquip()!=null) {
    		setEquipId(pla.getEquip().getCodi());
    	}
    	if (pla.getProducte()!=null) {
    		setProducteId(pla.getProducte().getCodi());
    	}
    	setDosis(pla.getDosis());
    	setFrequencia(pla.getFrequencia());
    	if (pla.getResponsable()!=null) {
    		setResponsableId(pla.getResponsable().getCodi());
    	}
    	if (pla.getRespVerificacio()!=null) {
    		setRespVerificacioId(pla.getRespVerificacio().getCodi());
    	}
    	setPeriodicitatVerificacio(pla.getPeriodicitatVerificacio());
    	setEstablimentId(pla.getEstabliment().getId());
    }

	public Long getEquipId() {
		return equipId;
	}

	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}

	public Long getProducteId() {
		return producteId;
	}

	public void setProducteId(Long producteId) {
		this.producteId = producteId;
	}

	public Long getResponsableId() {
		return responsableId;
	}

	public void setResponsableId(Long responsableId) {
		this.responsableId = responsableId;
	}

	public Long getRespVerificacioId() {
		return respVerificacioId;
	}

	public void setRespVerificacioId(Long respVerificacioId) {
		this.respVerificacioId = respVerificacioId;
	}

	public Long getEstablimentId() {
		return establimentId;
	}

	public void setEstablimentId(Long establimentId) {
		this.establimentId = establimentId;
	}

	public Long getCodi() {
		return codi;
	}

	public void setCodi(Long codi) {
		this.codi = codi;
	}

	public Boolean getEsGeneral() {
		return esGeneral;
	}

	public void setEsGeneral(Boolean esGeneral) {
		this.esGeneral = esGeneral;
	}

	public String getAccio() {
		return accio;
	}

	public void setAccio(String accio) {
		this.accio = accio;
	}

	public String getElementPlanta() {
		return elementPlanta;
	}

	public void setElementPlanta(String elementPlanta) {
		this.elementPlanta = elementPlanta;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public String getPeriodicitatVerificacio() {
		return periodicitatVerificacio;
	}

	public void setPeriodicitatVerificacio(String periodicitatVerificacio) {
		this.periodicitatVerificacio = periodicitatVerificacio;
	}

	


}