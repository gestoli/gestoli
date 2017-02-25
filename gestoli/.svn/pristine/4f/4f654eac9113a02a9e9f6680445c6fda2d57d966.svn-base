package es.caib.gestoli.front.spring.qualitat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatPlaControlTransport;

public class QualitatPlaControlTransportCommand  {
	
	private static final long serialVersionUID = 1L;
	
	private Long vehicleId;
	private Long responsableVerificacioId;
	private Long establimentId;
	private Long id;
	private Boolean estibaCorrecta;
	private Boolean netejaCorrecta;
	private Date dataVerificacio;
	private Boolean satisfactori;
	private Set<QualitatNoConformitat> noConformitats = new HashSet<QualitatNoConformitat>(0);
	 
	

	/**
     * Rellena los campos de este objeto con la información
     * del QualitatDescripcioPersonal.
     * @param pers QualitatDescripcioPersonal
     */
    public void fromQualitatPlaControlTransport(QualitatPlaControlTransport pla) {
    	setId(pla.getId());
    	setDataVerificacio (pla.getDataVerificacio());
    	setEstibaCorrecta(pla.getEstibaCorrecta());
    	setNetejaCorrecta(pla.getNetejaCorrecta());
    	if (pla.getVehicle()!=null) {
    		setVehicleId(pla.getVehicle().getCodi());
    	}
    	if (pla.getResponsableVerificacio()!=null) {
    		setResponsableVerificacioId(pla.getResponsableVerificacio().getCodi());
    	}
    	setEstablimentId(pla.getEstabliment().getId());
    	setNoConformitats(pla.getNoConformitats());
    	if (pla.getEstibaCorrecta() && pla.getNetejaCorrecta()) 
    		setSatisfactori(pla.getEstibaCorrecta() && pla.getNetejaCorrecta());
    }

    
	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}


	public Long getResponsableVerificacioId() {
		return responsableVerificacioId;
	}

	public void setResponsableVerificacioId(Long responsableVerificacioId) {
		this.responsableVerificacioId = responsableVerificacioId;
	}

	public Long getEstablimentId() {
		return establimentId;
	}

	public void setEstablimentId(Long establimentId) {
		this.establimentId = establimentId;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getEstibaCorrecta() {
		return estibaCorrecta;
	}


	public void setEstibaCorrecta(Boolean estibaCorrecta) {
		this.estibaCorrecta = estibaCorrecta;
	}


	public Boolean getNetejaCorrecta() {
		return netejaCorrecta;
	}


	public void setNetejaCorrecta(Boolean netejaCorrecta) {
		this.netejaCorrecta = netejaCorrecta;
	}


	public Date getDataVerificacio() {
		return dataVerificacio;
	}


	public void setDataVerificacio(Date dataVerificacio) {
		this.dataVerificacio = dataVerificacio;
	}



	public Set<QualitatNoConformitat> getNoConformitats() {
		return noConformitats;
	}


	public void setNoConformitats(Set<QualitatNoConformitat> noConformitats) {
		this.noConformitats = noConformitats;
	}
	
	public Boolean getSatisfactori() {
		return satisfactori;
	}


	public void setSatisfactori(Boolean satisfactori) {
		this.satisfactori = satisfactori;
	}


}