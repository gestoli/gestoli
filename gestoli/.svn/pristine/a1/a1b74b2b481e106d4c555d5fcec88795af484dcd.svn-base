package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Javadoc de les clases Model de Gest Oli
 * 
 * @author Oriol Barnes Cruz (obarnes@at4.net)
 * 
 */
public class Olivicultor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Usuari usuari;
	private Campanya campanya;
	private Long idOriginal;
	private String codiDO;
	private String codiDOExperimental;
	private String codiDOPOliva;
	private Integer codiDO2;
	private Integer codiDOExperimental2;
	private Integer codiDOPOliva2;
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
	private Date dataBaixa;
	private Set fincas = new HashSet(0);
	private Set informacios = new HashSet(0);

	public Olivicultor() {
	}

	public Olivicultor(Usuari usuari, Campanya campanya, String nom,
			String direccio, Municipi poblacio, String codiPostal, String nif,
			String compteBancari, Boolean altaDO, Boolean cartilla,
			Boolean subvencionat) {
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

	public Olivicultor(Usuari usuari, Campanya campanya, Long idOriginal,
			String codiDO, String codiDOExperimental, String codiDOPOliva, String nom,
			String direccio, Municipi poblacio, String codiPostal, String nif,
			String compteBancari, Boolean altaDO, Boolean cartilla,
			String email, String telefon, String fax, String uidRfid,
			Boolean subvencionat, String observacions, Date dataBaixa,
			Set fincas, Set informacios) {
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
		this.dataBaixa = dataBaixa;
		this.fincas = fincas;
		this.informacios = informacios;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public String getCodiDOPOliva() {
		return codiDOPOliva;
	}

	public void setCodiDOPOliva(String codiDOPOliva) {
		this.codiDOPOliva = codiDOPOliva;
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
		if (this.codiPostal == null || this.codiPostal.equals("")){
			return this.codiPostal;
		} else {
			Integer cp = Integer.parseInt(this.codiPostal);
			NumberFormat nf = new DecimalFormat("00000");
			return nf.format(cp);
		}
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

	public Set getInformacios() {
		return this.informacios;
	}
	public void setInformacios(Set informacios) {
		this.informacios = informacios;
	}
	
	
	public Integer getCodiDO2() {
		try{
			return Integer.parseInt(this.codiDO);
		} catch(Exception e){
			return null;
		}
	}

	public Integer getCodiDOExperimental2() {
		try{
			return Integer.parseInt(this.codiDOExperimental);
		} catch(Exception e){
			return null;
		}
	}
	
	public Integer getCodiDOPOliva2() {
		try{
			return Integer.parseInt(this.codiDOPOliva);
		} catch(Exception e){
			return null;
		}
	}

	public String getMunicipisExplotacio(){
		List<String> municipis = new ArrayList<String>();
		Collection finques = this.getFincas();
		
		for(Iterator it=finques.iterator(); it.hasNext();){
			Finca f = (Finca)it.next();
			
			Collection plantacions = f.getPlantacios();
			
			for(Iterator it2=plantacions.iterator(); it2.hasNext();){
				Plantacio p = (Plantacio)it2.next();
				
				if(!municipis.contains(p.getMunicipi().getNom())){
					municipis.add(p.getMunicipi().getNom());
				}
			}
		}
		
		String strmunicipis = "";
		
		for(String municipi: municipis){
			strmunicipis += municipi + " ";
		}
		
		return strmunicipis;
	}

}
