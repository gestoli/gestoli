/**
 * OlivicultorCommand.java
 *
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Olivicultor;

/**
 * Objeto que representa el formulario de olivicultor.
 * 
 * 
 */
public class OlivicultorCommand extends Olivicultor {

	private static final long serialVersionUID = 1148613628614982021L;

	private Long usuariId;
	private Long campanyaId;
	private String idiomaId;
	private String contrasenya;
	private String contrasenyaEncriptada;
	private String nomUsuari;
	private Boolean actiu;
	private Long municipiId;
	private Boolean teCodiDo;
	private Boolean teCodiDoExperimental;
	private Boolean teCodiDopOliva;
	private String cerca;

	public Long getCampanyaId() {
		return campanyaId;
	}

	public void setCampanyaId(Long campanyaId) {
		this.campanyaId = campanyaId;
	}

	public Long getMunicipiId() {
		return municipiId;
	}

	public void setMunicipiId(Long municipiId) {
		this.municipiId = municipiId;
	}

	/**
	 * Rellena los campos de este objeto con la información del olivicultor.
	 * 
	 * @param ol
	 *            Olivicultor
	 */
	public void fromOlivicultor(Olivicultor ol) {
		setAltaDO(ol.getAltaDO());
		setCartilla(ol.getCartilla());
		setCodiDO(ol.getCodiDO());
		setCodiDOExperimental(ol.getCodiDOExperimental());
		setCodiDOPOliva(ol.getCodiDOPOliva());
		setCodiPostal(ol.getCodiPostal());
		setCompteBancari(ol.getCompteBancari());
		setDireccio(ol.getDireccio());
		setEmail(ol.getEmail());
		setFax(ol.getFax());
		setFincas(ol.getFincas());
		setId(ol.getId());
		setNif(ol.getNif());
		setNom(ol.getNom());
		setObservacions(ol.getObservacions());
		setPoblacio(ol.getPoblacio());
		setTelefon(ol.getTelefon());
		setUsuari(ol.getUsuari());
		setCampanya(ol.getCampanya());
		setIdOriginal(ol.getIdOriginal());
		setUidRfid(ol.getUidRfid());
		setSubvencionat(ol.getSubvencionat());
		if (ol.getUsuari() != null) {
			setIdiomaId(ol.getUsuari().getIdioma().getId());
			setContrasenya(ol.getUsuari().getContrasenya());
			setContrasenyaEncriptada(ol.getUsuari().getContrasenya());
			setActiu(ol.getUsuari().getActiu());
			setNomUsuari(ol.getUsuari().getUsuari());
			setUsuariId(ol.getUsuari().getId());
		}
		setMunicipiId((ol.getPoblacio()!=null)?ol.getPoblacio().getId():null);
		setTeCodiDo(ol.getCodiDO() != null);
		setTeCodiDoExperimental(ol.getCodiDOExperimental() != null);
		setTeCodiDopOliva(ol.getCodiDOPOliva()!=null);
		
		setDataBaixa(ol.getDataBaixa());
	}

	/**
	 * Para imprimir bien el registro
	 */
	public String toString() {
		return "Olivicultor(" + "olivicultor: " + getNom() + ")";
	}

	public Long getUsuariId() {
		return usuariId;
	}

	public void setUsuariId(Long usuariId) {
		this.usuariId = usuariId;
	}

	public String getIdiomaId() {
		return idiomaId;
	}

	public void setIdiomaId(String idiomaId) {
		this.idiomaId = idiomaId;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getContrasenyaEncriptada() {
		return contrasenyaEncriptada;
	}

	public void setContrasenyaEncriptada(String contrasenyaEncriptada) {
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public Boolean getActiu() {
		return actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}
	
	public Boolean getTeCodiDo() {
		return teCodiDo;
	}
	public void setTeCodiDo(Boolean teCodiDo) {
		this.teCodiDo = teCodiDo;
	}

	public Boolean getTeCodiDoExperimental() {
		return teCodiDoExperimental;
	}
	public void setTeCodiDoExperimental(Boolean teCodiDoExperimental) {
		this.teCodiDoExperimental = teCodiDoExperimental;
	}

	public Boolean getTeCodiDopOliva() {
		return teCodiDopOliva;
	}

	public void setTeCodiDopOliva(Boolean teCodiDopOliva) {
		this.teCodiDopOliva = teCodiDopOliva;
	}

	public String getCerca() {
		return cerca;
	}

	public void setCerca(String cerca) {
		this.cerca = cerca;
	}
}
