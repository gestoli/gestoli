package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;


public class QualitatDescripcioEquip implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codi;
	private String codiUsuari;
	private String nom;
	private Zona ubicacio;
	private String marca;
	private Date dataCompra;
	private String numSerie;
	private Establiment establiment;
	private Boolean esVehicle;
	
	public QualitatDescripcioEquip() {
		super();
	}

	public QualitatDescripcioEquip(String codiUsuari, String nom,
			 Zona ubicacio, String marca, Date dataCompra, String numSerie,
			Establiment establiment, Boolean esVehicle) {
		super();
		this.codiUsuari = codiUsuari;
		this.numSerie = numSerie;
		this.nom = nom;
		this.ubicacio = ubicacio;
		this.marca = marca;
		this.dataCompra = dataCompra;
		this.establiment = establiment;
		this.esVehicle = esVehicle;
	}

	public String getCodiUsuari() {
		return codiUsuari;
	}

	public void setCodiUsuari(String codiUsuari) {
		this.codiUsuari = codiUsuari;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public Long getCodi() {
		return codi;
	}

	public void setCodi(Long codi) {
		this.codi = codi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Zona getUbicacio() {
		return ubicacio;
	}

	public void setUbicacio(Zona ubicacio) {
		this.ubicacio = ubicacio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Boolean getEsVehicle() {
		return esVehicle;
	}

	public void setEsVehicle(Boolean esVehicle) {
		this.esVehicle = esVehicle;
	}
	
}
	
	
