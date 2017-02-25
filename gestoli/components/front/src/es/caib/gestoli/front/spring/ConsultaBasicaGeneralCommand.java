/**
 * 
 */
package es.caib.gestoli.front.spring;



/**
 * Objecte que representa un formulari web d'una consulta general.
 * 
 */
public class ConsultaBasicaGeneralCommand {

	private String partidaNom;
	private String lotNom;
	private String zona;
	
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
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}



	private String partidaNomC;
	private String lotNomC;
	private String zonaC;

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
	public String getZonaC() {
		return zonaC;
	}
	public void setZonaC(String zonaC) {
		this.zonaC = zonaC;
	}

}
