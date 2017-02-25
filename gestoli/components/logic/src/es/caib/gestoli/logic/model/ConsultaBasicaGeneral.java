/**
 * 
 */
package es.caib.gestoli.logic.model;




/**
 * Objecte que representa un formulari web d'una consulta general.
 * 
 */
public class ConsultaBasicaGeneral implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String partidaNom;
	private String lotNom;
	private String zona;
	private String partidaNomC;
	private String lotNomC;
	private String zonaC;
	

	public ConsultaBasicaGeneral(String partidaNom, String lotNom, String zona,
			String partidaNomC, String lotNomC, String zonaC) {
		super();
		this.partidaNom = partidaNom;
		this.lotNom = lotNom;
		this.zona = zona;
		this.partidaNomC = partidaNomC;
		this.lotNomC = lotNomC;
		this.zonaC = zonaC;
	}


	public ConsultaBasicaGeneral() {
	}
	
	
	public String getPartidaNom() {
		return partidaNom;
	}
	public void setPartidaNom(String partidaNom) {
		this.partidaNom = partidaNom;
	}


	public String getLotNom() {
		return lotNom;
	}


	public void setLotNom(String lotNom) {
		this.lotNom = lotNom;
	}


	public String getPartidaNomC() {
		return partidaNomC;
	}


	public void setPartidaNomC(String partidaNomC) {
		this.partidaNomC = partidaNomC;
	}


	public String getLotNomC() {
		return lotNomC;
	}


	public void setLotNomC(String lotNomC) {
		this.lotNomC = lotNomC;
	}


	public String getZona() {
		return zona;
	}


	public void setZona(String zona) {
		this.zona = zona;
	}


	public String getZonaC() {
		return zonaC;
	}


	public void setZonaC(String zonaC) {
		this.zonaC = zonaC;
	}
	
	
}
