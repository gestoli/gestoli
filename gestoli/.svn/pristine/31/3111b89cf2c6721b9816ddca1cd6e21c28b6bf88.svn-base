/**
 * ProcesLotCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Iterator;

import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.VarietatOliva;


/**
 * Objeto que representa el formulario de mantenimento
 * de lotes.
 * 
 * 
 */
public class ProcesLotCommand extends Lot {
	
	Boolean perdua;
	Boolean etiquetar;
	Double kilosPerduts;
	Double kilosEnvassats;
	Long idMarca;
	Long idEtiquetatge;
	Long idZona;
	Integer idCategoriaOli;
	Long idPartidaOli;
	Double volum;
	String errorObservaciones;
	Integer[] varietatsArray;
	String nomCategoriaOli;
	String numeroLot;
	Boolean ferEtiquetat;
	Long idProducte;
	String nomProducte;
	String lotTap;
	
	public String getErrorObservaciones() {
		return errorObservaciones;
	}

	public void setErrorObservaciones(String errorObservaciones) {
		this.errorObservaciones = errorObservaciones;
	}

	public ProcesLotCommand() {}

	public ProcesLotCommand(Lot lot){
		setPartidaOli(lot.getPartidaOli());
		setCodiAssignat(lot.getCodiAssignat());
		setData(lot.getData());
		setDataConsum(lot.getDataConsum());
		setEtiquetatge(lot.getEtiquetatge());
		setId(lot.getId());
		setVarietatOlivas(lot.getVarietatOlivas());
		setZona(lot.getZona());
		setVolum(lot.getEtiquetatge().getTipusEnvas().getVolum());
		if (lot.getPartidaOli() != null) {
			setIdPartidaOli(lot.getPartidaOli().getId());
			setNomCategoriaOli(lot.getPartidaOli().getCategoriaOli().getNom());
		}
		setIdEtiquetatge(lot.getEtiquetatge().getId());
		setIdMarca(lot.getEtiquetatge().getMarca().getId());
		setIdZona(lot.getZona().getId());
		Integer[] varietatsIds = new Integer[lot.getVarietatOlivas().size()];
        int index = 0;
        for (Iterator it = lot.getVarietatOlivas().iterator(); it.hasNext();) {
        	varietatsIds[index++] = ((VarietatOliva)it.next()).getId();
        }
        setVarietatsArray(varietatsIds);
        setProducte(lot.getProducte());
        setLotTap(lot.getLotTap());
	}

	public Boolean getPerdua() {
		return perdua;
	}
	public void setPerdua(Boolean perdua) {
		this.perdua = perdua;
	}

	public Boolean getEtiquetar() {
		return etiquetar;
	}
	public void setEtiquetar(Boolean etiquetar) {
		this.etiquetar = etiquetar;
	}

	public Double getKilosPerduts() {
		return kilosPerduts;
	}
	public void setKilosPerduts(Double kilosPerduts) {
		this.kilosPerduts = kilosPerduts;
	}
	
	public Double getKilosEnvassats() {
		return kilosEnvassats;
	}
	public void setKilosEnvassats(Double kilosEnvassats) {
		this.kilosEnvassats = kilosEnvassats;
	}

	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Long getIdEtiquetatge() {
		return idEtiquetatge;
	}
	public void setIdEtiquetatge(Long idEtiquetatge) {
		this.idEtiquetatge = idEtiquetatge;
	}

	public Long getIdZona() {
		return idZona;
	}
	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Double getVolum() {
		return volum;
	}
	public void setVolum(Double volum) {
		this.volum = volum;
	}

	public Integer getIdCategoriaOli() {
		return idCategoriaOli;
	}
	public void setIdCategoriaOli(Integer idCategoriaOli) {
		this.idCategoriaOli = idCategoriaOli;
	}

	public Integer[] getVarietatsArray() {
		return varietatsArray;
	}
	public void setVarietatsArray(Integer[] varietatsArray) {
		this.varietatsArray = varietatsArray;
	}

	public Long getIdPartidaOli() {
		return idPartidaOli;
	}
	public void setIdPartidaOli(Long idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}

	public String getNomCategoriaOli() {
		return nomCategoriaOli;
	}
	public void setNomCategoriaOli(String nomCategoriaOli) {
		this.nomCategoriaOli = nomCategoriaOli;
	}

	public String getNumeroLot() {
		return numeroLot;
	}
	public void setNumeroLot(String numeroLot) {
		this.numeroLot = numeroLot;
	}

	public Boolean getFerEtiquetat() {
		return ferEtiquetat;
	}
	public void setFerEtiquetat(Boolean ferEtiquetat) {
		this.ferEtiquetat = ferEtiquetat;
	}
	public Long getIdProducte() {
		return idProducte;
	}
	public void setIdProducte(Long idProducte) {
		this.idProducte = idProducte;
	}
	public String getNomProducte() {
		return nomProducte;
	}
	public void setNomProducte(String nomProducte) {
		this.nomProducte = nomProducte;
	}

	public String getLotTap() {
		return lotTap;
	}

	public void setLotTap(String lotTap) {
		this.lotTap = lotTap;
	}
}
