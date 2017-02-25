/**
 * EstablimentCommand.java
 *
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Establiment;


/**
 * Objeto que representa el formulario de mantenimento
 * de establecimientos.
 * 
 * @author Carlos Pérez (cperez@at4.net)
 */
public class EstablimentCommand extends Establiment {
	private static final long serialVersionUID = 3660614058015788632L;
	private Long[] marcasArray;
	private Long tipusEstablimentId;
//	private Long usuariId;
	private String nombreSolicitant;
	private String nifSolicitant;
	private String perfilSolicitant;
	private String telefonSolicitant;
	private Long campanyaId;
	private String cambioEstadoActivo;
	private Long municipiId;
	private String nomDipositFictici;
	

	/**
     * Rellena los campos de este objeto con la información
     * del establecimiento.
     * 
     * @param es Establiment
     */
    public void fromEstabliment(Establiment es) {
    	
    	setActiu(es.getActiu());
    	setCapacitatProduccio(es.getCapacitatProduccio());
    	setCif(es.getCif());
    	setCodiPostal(es.getCodiPostal());
    	setDireccio(es.getDireccio());
    	setEmail(es.getEmail());
    	setEnvassamentManual(es.getEnvassamentManual());
    	setEtiquetatManual(es.getEtiquetatManual());
    	setFax(es.getFax());
    	setId(es.getId());
    	setMarcas(es.getMarcas());
    	setNom(es.getNom());
    	setNumeroTreballadors(es.getNumeroTreballadors());
    	setObservacions(es.getObservacions());
    	setPoblacio(es.getPoblacio());
    	setSolicitant(es.getSolicitant());
    	setSuperficie(es.getSuperficie());
    	setTelefon(es.getTelefon());
    	setTemperaturaMaximaPasta(es.getTemperaturaMaximaPasta());
    	setTipusEstabliment(es.getTipusEstabliment());
    	setUnitatMesura(es.getUnitatMesura());
    	//setUsuari(es.getUsuari());    	
    	setNombreSolicitant(es.getSolicitant().getNom());
    	setNifSolicitant(es.getSolicitant().getNif());
    	setPerfilSolicitant(es.getSolicitant().getPerfil());
    	setTelefonSolicitant(es.getSolicitant().getTelefon());
    	setCampanya(es.getCampanya());
    	setIdOriginal(es.getIdOriginal());
    	setCodiRB(es.getCodiRB());
    	setCodiRC(es.getCodiRC());
    	setCodiRT(es.getCodiRT());
    	setMunicipiId((es.getPoblacio()!=null)?es.getPoblacio().getId():null);
    	
    	setDataBaixa(es.getDataBaixa());
    	
    	setNomResponsable(es.getNomResponsable());
    	setCifResponsable(es.getCifResponsable());
    	
    	setOlivaTaula(es.getOlivaTaula());
    }

//    /**
//     * Para imprimir bien el registro
//     */
//    public String toString() {
//    	return "Usuari(" +
//    			"usuari: " + getUsuari() + ")";
//    }

    
	public Long getTipusEstablimentId() {
		return tipusEstablimentId;
	}

	public void setTipusEstablimentId(Long tipusEstablimentId) {
		this.tipusEstablimentId = tipusEstablimentId;
	}

//	public Long getUsuariId() {
//		return usuariId;
//	}
//
//	public void setUsuariId(Long usuariId) {
//		this.usuariId = usuariId;
//	}


	public String getNifSolicitant() {
		return nifSolicitant;
	}

	public void setNifSolicitant(String nifSolicitant) {
		this.nifSolicitant = nifSolicitant;
	}

	public String getNombreSolicitant() {
		return nombreSolicitant;
	}

	public void setNombreSolicitant(String nombreSolicitant) {
		this.nombreSolicitant = nombreSolicitant;
	}

	public String getPerfilSolicitant() {
		return perfilSolicitant;
	}

	public void setPerfilSolicitant(String perfilSolicitant) {
		this.perfilSolicitant = perfilSolicitant;
	}

	public String getTelefonSolicitant() {
		return telefonSolicitant;
	}

	public void setTelefonSolicitant(String telefonSolicitant) {
		this.telefonSolicitant = telefonSolicitant;
	}

	public Long[] getMarcasArray() {
		return marcasArray;
	}

	public void setMarcasArray(Long[] marcasArray) {
		this.marcasArray = marcasArray;
	}

	public Long getCampanyaId() {
		return campanyaId;
	}

	public void setCampanyaId(Long campanyaId) {
		this.campanyaId = campanyaId;
	}

	public String getCambioEstadoActivo() {
		return cambioEstadoActivo;
	}

	public void setCambioEstadoActivo(String cambioEstadoActivo) {
		this.cambioEstadoActivo = cambioEstadoActivo;
	}
	
	public Long getMunicipiId() {
		return municipiId;
	}
	public void setMunicipiId(Long municipiId) {
		this.municipiId = municipiId;
	}

	public String getNomDipositFictici() {
		return nomDipositFictici;
	}

	public void setNomDipositFictici(String nomDipositFictici) {
		this.nomDipositFictici = nomDipositFictici;
	}
	
	
	
}
