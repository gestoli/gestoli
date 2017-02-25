package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Model de l'objecte de Històric d'Olivicultor.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class HistoricOlivicultor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Olivicultor olivicultor;
	private Usuari usuari;
	private Campanya campanya;
	private Long idOriginal;
	private String codiDO;
	private String codiDOExperimental;
	private String codiDOPOliva;
	private String nom;
	private String direccio;
	private Municipi poblacio;
	private String codiPostal;
	private String nif;
	private String compteBancari;
	private Boolean altaDO;
	private Boolean cartilla;
	private String email;
	private String telefon;
	private String fax;
	private String uidRfid;
	private Boolean subvencionat;
	private String observacions;
	private Date dataAlta;
	private Date dataBaixa;
	private Set fincas = new HashSet(0);
	private Set informacions = new HashSet(0);
	
	private Boolean esAltaBaixa;

	public HistoricOlivicultor() {
	}

	public HistoricOlivicultor(Olivicultor olivicultor, Usuari usuari, Campanya campanya,
			String nom, String direccio, Municipi poblacio, String codiPostal, String nif,
			String compteBancari, Boolean altaDO, Boolean cartilla, Boolean subvencionat) {
		this.olivicultor = olivicultor;
		this.usuari = usuari;
		this.campanya = campanya;
		this.nom = nom;
		this.direccio = direccio;
		this.poblacio = poblacio;
		this.codiPostal = codiPostal;
		this.nif = nif;
		this.compteBancari = compteBancari;
		this.altaDO = altaDO;
		this.cartilla = cartilla;
		this.subvencionat = subvencionat;
	}

	public HistoricOlivicultor(Olivicultor olivicultor, Usuari usuari, Campanya campanya,
			Long idOriginal, String codiDO, String codiDOExperimental, String codiDOPOliva, String nom,
			String direccio, Municipi poblacio, String codiPostal, String nif,
			String compteBancari, Boolean altaDO, Boolean cartilla, String email,
			String telefon, String fax, String uidRfid, Boolean subvencionat,
			String observacions, Date dataAlta, Date dataBaixa, Set fincas, Set informacions) {
		this.olivicultor = olivicultor;
		this.usuari = usuari;
		this.campanya = campanya;
		this.idOriginal = idOriginal;
		this.codiDO = codiDO;
		this.codiDOExperimental = codiDOExperimental;
		this.codiDOPOliva = codiDOPOliva;
		this.nom = nom;
		this.direccio = direccio;
		this.poblacio = poblacio;
		this.codiPostal = codiPostal;
		this.nif = nif;
		this.compteBancari = compteBancari;
		this.altaDO = altaDO;
		this.cartilla = cartilla;
		this.email = email;
		this.telefon = telefon;
		this.fax = fax;
		this.uidRfid = uidRfid;
		this.subvencionat = subvencionat;
		this.observacions = observacions;
		this.dataAlta = dataAlta;
		this.dataBaixa = dataBaixa;
		this.fincas = fincas;
		this.informacions = informacions;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Olivicultor getOlivicultor() {
		return olivicultor;
	}
	public void setOlivicultor(Olivicultor olivicultor) {
		this.olivicultor = olivicultor;
	}
	
	public Usuari getUsuari() {
		return this.usuari;
	}
	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

	public Campanya getCampanya() {
		return this.campanya;
	}
	public void setCampanya(Campanya campanya) {
		this.campanya = campanya;
	}

	public Long getIdOriginal() {
		return this.idOriginal;
	}
	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public String getCodiDO() {
		return this.codiDO;
	}
	public void setCodiDO(String codiDO) {
		this.codiDO = codiDO;
	}

	public String getCodiDOExperimental() {
		return this.codiDOExperimental;
	}
	public void setCodiDOExperimental(String codiDOExperimental) {
		this.codiDOExperimental = codiDOExperimental;
	}

	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDireccio() {
		return this.direccio;
	}
	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public Municipi getPoblacio() {
		return this.poblacio;
	}
	public void setPoblacio(Municipi poblacio) {
		this.poblacio = poblacio;
	}

	public String getCodiPostal() {
		return this.codiPostal;
	}
	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}

	public String getNif() {
		return this.nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getCompteBancari() {
		return this.compteBancari;
	}
	public void setCompteBancari(String compteBancari) {
		this.compteBancari = compteBancari;
	}

	public Boolean getAltaDO() {
		return this.altaDO;
	}
	public void setAltaDO(Boolean altaDO) {
		this.altaDO = altaDO;
	}

	public Boolean getCartilla() {
		return this.cartilla;
	}
	public void setCartilla(Boolean cartilla) {
		this.cartilla = cartilla;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return this.telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return this.fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getUidRfid() {
		return this.uidRfid;
	}
	public void setUidRfid(String uidRfid) {
		this.uidRfid = uidRfid;
	}

	public Boolean getSubvencionat() {
		return this.subvencionat;
	}
	public void setSubvencionat(Boolean subvencionat) {
		this.subvencionat = subvencionat;
	}

	public String getObservacions() {
		return this.observacions;
	}
	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}
	
	public Date getDataAlta() {
		return dataAlta;
	}
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}
	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	
	public Set getFincas() {
		return this.fincas;
	}
	public void setFincas(Set fincas) {
		this.fincas = fincas;
	}

	public Set getInformacions() {
		return this.informacions;
	}
	public void setInformacions(Set informacions) {
		this.informacions = informacions;
	}
	public Boolean getEsAltaBaixa() {
		return esAltaBaixa;
	}
	public void setEsAltaBaixa(Boolean esAltaBaixa) {
		this.esAltaBaixa = esAltaBaixa;
	}

	public String getCodiDOPOliva() {
		return codiDOPOliva;
	}

	public void setCodiDOPOliva(String codiDOPOliva) {
		this.codiDOPOliva = codiDOPOliva;
	}
}
