package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Establiment implements Comparable {

	private Long id;

	private Campanya campanya;

//	private Usuari usuari;

	private TipusEstabliment tipusEstabliment;

	private Solicitant solicitant;

	private Long idOriginal;

	private String codiRB;

	private String codiRC;
	
	private String codiRT;

	private Boolean actiu;

	private String nom;

	private String cif;
	
	private String nomResponsable;
	
	private String cifResponsable;

	private String direccio;

	private Municipi poblacio;

	private String codiPostal;

	private String email;

	private String telefon;

	private String fax;

	private Integer numeroTreballadors;

	private Double superficie;

	private String unitatMesura;

	private Integer temperaturaMaximaPasta;

	private Double capacitatProduccio;

	private Boolean envassamentManual;

	private Boolean etiquetatManual;

	private String observacions;
	
	private Date dataBaixa;
	
	private Set<Marca> marcas = new HashSet<Marca>(0);

	private Set<Informacio> informacios = new HashSet<Informacio>(0);

	private Set<Diposit> diposits = new HashSet<Diposit>(0);

	private Set<Zona> zonas = new HashSet<Zona>(0);
	
	private Set<Usuari> usuaris = new HashSet<Usuari>(0);
	
	private Set<PartidaOli> partidesOli = new HashSet<PartidaOli>(0);
	
	private Set<Producte> productes = new HashSet<Producte>(0);

	private QualitatDescripcioInstalacio descripcio;
	
	private Set<QualitatDescripcioEquip> equips = new HashSet<QualitatDescripcioEquip>(0);
	
	private Set<QualitatDescripcioPersonal> personal = new HashSet<QualitatDescripcioPersonal>(0);
	
	private Set<QualitatPuestoTreball> puestos = new HashSet<QualitatPuestoTreball>(0);
	
	private Set<QualitatProveidor> proveidors = new HashSet<QualitatProveidor>(0);
	
	private Set<QualitatSubministre> subministres = new HashSet<QualitatSubministre>(0);
	
	private Set<QualitatPlaNeteja> plansNeteja = new HashSet<QualitatPlaNeteja>(0);
	
	private QualitatAiguaSubministre descripcioSubministreAigua;
	
	private QualitatAiguaControlOrganoleptic aiguaControlOrganoleptic;
	
	private QualitatAiguaControlAnalitic aiguaControlAnalitic;
	
	private Boolean olivaTaula;
	
	public Establiment() {
	}

	public Establiment(Campanya campanya, //Usuari usuari,
			TipusEstabliment tipusEstabliment, Solicitant solicitant,
			Boolean actiu, String nom, String cif, String email,
			String unitatMesura) {
		this.campanya = campanya;
		//this.usuari = usuari;
		this.tipusEstabliment = tipusEstabliment;
		this.solicitant = solicitant;
		this.actiu = actiu;
		this.nom = nom;
		this.cif = cif;
		this.email = email;
		this.unitatMesura = unitatMesura;
	}

	public Establiment(Campanya campanya, Usuari usuari,
			TipusEstabliment tipusEstabliment, Solicitant solicitant,
			Long idOriginal, String codiRB, String codiRC, String codiRT, Boolean actiu,
			String nom, String cif, String direccio, Municipi poblacio,
			String codiPostal, String email, String telefon, String fax,
			Integer numeroTreballadors, Double superficie, String unitatMesura,
			Integer temperaturaMaximaPasta, Double capacitatProduccio,
			Boolean envassamentManual, Boolean etiquetatManual,
			String observacions, Set<Marca> marcas, Set<Informacio> informacios, Set<Diposit> diposits,
			Set<Zona> zonas, Set<Usuari> usuaris, Set<Producte> productes) {
		this.campanya = campanya;
		this.tipusEstabliment = tipusEstabliment;
		this.solicitant = solicitant;
		this.idOriginal = idOriginal;
		this.codiRB = codiRB;
		this.codiRC = codiRC;
		this.codiRT = codiRT;
		this.actiu = actiu;
		this.nom = nom;
		this.cif = cif;
		this.direccio = direccio;
		this.poblacio = poblacio;
		this.codiPostal = codiPostal;
		this.email = email;
		this.telefon = telefon;
		this.fax = fax;
		this.numeroTreballadors = numeroTreballadors;
		this.superficie = superficie;
		this.unitatMesura = unitatMesura;
		this.temperaturaMaximaPasta = temperaturaMaximaPasta;
		this.capacitatProduccio = capacitatProduccio;
		this.envassamentManual = envassamentManual;
		this.etiquetatManual = etiquetatManual;
		this.observacions = observacions;
		this.marcas = marcas;
		this.informacios = informacios;
		this.diposits = diposits;
		this.zonas = zonas;
		this.usuaris = usuaris;
		this.productes = productes;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Campanya getCampanya() {
		return this.campanya;
	}

	public void setCampanya(Campanya campanya) {
		this.campanya = campanya;
	}

	public TipusEstabliment getTipusEstabliment() {
		return this.tipusEstabliment;
	}

	public void setTipusEstabliment(TipusEstabliment tipusEstabliment) {
		this.tipusEstabliment = tipusEstabliment;
	}

	public Solicitant getSolicitant() {
		return this.solicitant;
	}

	public void setSolicitant(Solicitant solicitant) {
		this.solicitant = solicitant;
	}

	public Long getIdOriginal() {
		return this.idOriginal;
	}

	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public String getCodiRB() {
		return this.codiRB;
	}

	public void setCodiRB(String codiRB) {
		this.codiRB = codiRB;
	}

	public String getCodiRC() {
		return this.codiRC;
	}

	public void setCodiRC(String codiRC) {
		this.codiRC = codiRC;
	}

	public Boolean getActiu() {
		return this.actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	public String getCifResponsable() {
		return cifResponsable;
	}

	public void setCifResponsable(String cifResponsable) {
		this.cifResponsable = cifResponsable;
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

	public Integer getNumeroTreballadors() {
		return this.numeroTreballadors;
	}

	public void setNumeroTreballadors(Integer numeroTreballadors) {
		this.numeroTreballadors = numeroTreballadors;
	}

	public Double getSuperficie() {
		return this.superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public String getUnitatMesura() {
		return this.unitatMesura;
	}

	public void setUnitatMesura(String unitatMesura) {
		this.unitatMesura = unitatMesura;
	}

	public Integer getTemperaturaMaximaPasta() {
		return this.temperaturaMaximaPasta;
	}

	public void setTemperaturaMaximaPasta(Integer temperaturaMaximaPasta) {
		this.temperaturaMaximaPasta = temperaturaMaximaPasta;
	}

	public Double getCapacitatProduccio() {
		return this.capacitatProduccio;
	}

	public void setCapacitatProduccio(Double capacitatProduccio) {
		this.capacitatProduccio = capacitatProduccio;
	}

	public Boolean getEnvassamentManual() {
		return this.envassamentManual;
	}

	public void setEnvassamentManual(Boolean envassamentManual) {
		this.envassamentManual = envassamentManual;
	}

	public Boolean getEtiquetatManual() {
		return this.etiquetatManual;
	}

	public void setEtiquetatManual(Boolean etiquetatManual) {
		this.etiquetatManual = etiquetatManual;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	
	public Set<Marca> getMarcas() {
		return this.marcas;
	}

	public void setMarcas(Set<Marca> marcas) {
		this.marcas = marcas;
	}

	public Set<Informacio> getInformacios() {
		return this.informacios;
	}

	public void setInformacios(Set<Informacio> informacios) {
		this.informacios = informacios;
	}

	public Set<Diposit> getDiposits() {
		return this.diposits;
	}

	public void setDiposits(Set<Diposit> diposits) {
		this.diposits = diposits;
	}

	public Set<Zona> getZonas() {
		return this.zonas;
	}

	public void setZonas(Set<Zona> zonas) {
		this.zonas = zonas;
	}
	
	public Set<Usuari> getUsuaris() {
		return usuaris;
	}

	public void setUsuaris(Set<Usuari> usuaris) {
		this.usuaris = usuaris;
	}
	
	public Set<PartidaOli> getPartidesOli() {
		return partidesOli;
	}

	public void setPartidesOli(Set<PartidaOli> partidesOli) {
		this.partidesOli = partidesOli;
	}

	public QualitatDescripcioInstalacio getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(QualitatDescripcioInstalacio descripcio) {
		this.descripcio = descripcio;
	}
	
	
	public Set<QualitatDescripcioEquip> getEquips() {
		return equips;
	}

	public void setEquips(Set<QualitatDescripcioEquip> equips) {
		this.equips = equips;
	}

	
	public Set<QualitatDescripcioPersonal> getPersonal() {
		return personal;
	}

	public void setPersonal(Set<QualitatDescripcioPersonal> personal) {
		this.personal = personal;
	}

	public Set<QualitatPuestoTreball> getPuestos() {
		return puestos;
	}

	public void setPuestos(Set<QualitatPuestoTreball> puestos) {
		this.puestos = puestos;
	}

	public Set<QualitatProveidor> getProveidors() {
		return proveidors;
	}

	public void setProveidors(Set<QualitatProveidor> proveidors) {
		this.proveidors = proveidors;
	}

	public Set<QualitatPlaNeteja> getPlansNeteja() {
		return plansNeteja;
	}

	public void setPlansNeteja(Set<QualitatPlaNeteja> plansNeteja) {
		this.plansNeteja = plansNeteja;
	}

	public Set<QualitatSubministre> getSubministres() {
		return subministres;
	}

	public void setSubministres(Set<QualitatSubministre> subministres) {
		this.subministres = subministres;
	}

	public QualitatAiguaSubministre getDescripcioSubministreAigua() {
		return descripcioSubministreAigua;
	}

	public void setDescripcioSubministreAigua(
			QualitatAiguaSubministre descripcioSubministreAigua) {
		this.descripcioSubministreAigua = descripcioSubministreAigua;
	}

	public QualitatAiguaControlOrganoleptic getAiguaControlOrganoleptic() {
		return aiguaControlOrganoleptic;
	}

	public void setAiguaControlOrganoleptic(
			QualitatAiguaControlOrganoleptic aiguaControlOrganoleptic) {
		this.aiguaControlOrganoleptic = aiguaControlOrganoleptic;
	}

	public QualitatAiguaControlAnalitic getAiguaControlAnalitic() {
		return aiguaControlAnalitic;
	}

	public void setAiguaControlAnalitic(
			QualitatAiguaControlAnalitic aiguaControlAnalitic) {
		this.aiguaControlAnalitic = aiguaControlAnalitic;
	}

	public Set<Producte> getProductes() {
		return productes;
	}

	public void setProductes(Set<Producte> productes) {
		this.productes = productes;
	}

	public Boolean getOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(Boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}

	public String getCodiRT() {
		return codiRT;
	}

	public void setCodiRT(String codiRT) {
		this.codiRT = codiRT;
	}

	// The following is extra code specified in the hbm.xml files
	/**
	 * Comparator
	 */
	public int compareTo(Object e) {
		Establiment establecimiento = (Establiment) e;
		return this.nom.compareTo(establecimiento.getNom());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanya == null) ? 0 : campanya.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime
				* result
				+ ((tipusEstabliment == null) ? 0 : tipusEstabliment.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Establiment other = (Establiment) obj;
		if (campanya == null) {
			if (other.campanya != null)
				return false;
		} else if (!campanya.equals(other.campanya))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (tipusEstabliment == null) {
			if (other.tipusEstabliment != null)
				return false;
		} else if (!tipusEstabliment.equals(other.tipusEstabliment))
			return false;
		return true;
	}

	// end of extra code specified in the hbm.xml files

}
