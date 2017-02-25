package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;


public class QualitatSubministre implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codi;
	private String tipusSubministre;
	private Date homologacio;
	private Date deshomologacio;
	private String observacions;
	private QualitatProveidor proveidor;
	
	
	
	
	public QualitatSubministre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public QualitatSubministre(Long codi, String tipusSubministre,
			Date homologacio, Date deshomologacio, String observacions,
			QualitatProveidor proveidor) {
		super();
		this.codi = codi;
		this.tipusSubministre = tipusSubministre;
		this.homologacio = homologacio;
		this.deshomologacio = deshomologacio;
		this.observacions = observacions;
		this.proveidor = proveidor;
	}


	public Long getCodi() {
		return codi;
	}


	public void setCodi(Long codi) {
		this.codi = codi;
	}


	public String getTipusSubministre() {
		return tipusSubministre;
	}


	public void setTipusSubministre(String tipusSubministre) {
		this.tipusSubministre = tipusSubministre;
	}


	public Date getHomologacio() {
		return homologacio;
	}


	public void setHomologacio(Date homologacio) {
		this.homologacio = homologacio;
	}


	public Date getDeshomologacio() {
		return deshomologacio;
	}


	public void setDeshomologacio(Date deshomologacio) {
		this.deshomologacio = deshomologacio;
	}


	public String getObservacions() {
		return observacions;
	}


	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}


	public QualitatProveidor getProveidor() {
		return proveidor;
	}


	public void setProveidor(QualitatProveidor proveidor) {
		this.proveidor = proveidor;
	}

}