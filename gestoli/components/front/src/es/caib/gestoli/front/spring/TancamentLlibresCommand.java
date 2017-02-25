
package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.Date;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.model.Establiment;


/**
 * 
 */
public class TancamentLlibresCommand{
	private static final long serialVersionUID = 1L;
	private Boolean totsDiposits;
	private Boolean totsLots;
	private Collection DipositsSeleccionats;
	private Collection LotsSeleccionats;
	private Double[] litros;
	private Double[] litrosInicialsLots;
	private Double[] litrosActualsLots;
	private Double[] kilos;
	private Double[] kilosInicialsLots;
	private Double[] kilosActualsLots;
	private Long[] idPartidaOli;
	private Integer[] idCategoriaOli;	
	private Long[] idPartidaOliLots;
	private String[] nomCategoriaOli;
	private String[] nomCategoriaOliLots;
	private Double[] acidesa;
	private Double[] acidesaLots;
	private String[] observacions;
	private String[] observacionsLots;
	private Long[] dipositId;
	private Long[] lotId;
	private Long[] marcaId;
	private Long[] etiquetatgeId;
	private String[] numeroLot;
	private Integer[][] varietatsId;
	private BasicData[][] etiquetatges;
	private Double[] volum;
	private Long[] zonaId;
	private Integer[] numeroBotellesInicials;
	private Integer[] numeroBotellesActuals;
	private Boolean[] etiquetar;
	private Long idEstabliment;
	private Establiment establiment;
	private Date data;
	private String[] etiquetaLletra;
	private String[] etiquetaInici;
	private Double[] quantitat;
	private String[] etiquetaFi;
	private String[] etiquetaCapacitat;
	private Double[] litres;
	private Date[] dataConsum;

	public Boolean getTotsDiposits() {
		return totsDiposits;
	}
	public void setTotsDiposits(Boolean totsDiposits) {
		this.totsDiposits = totsDiposits;
	}

	public Boolean getTotsLots() {
		return totsLots;
	}
	public void setTotsLots(Boolean totsLots) {
		this.totsLots = totsLots;
	}
	
	public Collection getDipositsSeleccionats() {
		return DipositsSeleccionats;
	}
	public void setDipositsSeleccionats(Collection dipositsSeleccionats) {
		DipositsSeleccionats = dipositsSeleccionats;
	}
	
	public Collection getLotsSeleccionats() {
		return LotsSeleccionats;
	}
	public void setLotsSeleccionats(Collection lotsSeleccionats) {
		LotsSeleccionats = lotsSeleccionats;
	}
	public Double[] getLitros() {
		return litros;
	}
	public void setLitros(Double[] litros) {
		this.litros = litros;
	}
	public Double[] getKilos() {
		return kilos;
	}
	public void setKilos(Double[] kilos) {
		this.kilos = kilos;
	}
	public Long[] getIdPartidaOli() {
		return idPartidaOli;
	}
	public void setIdPartidaOli(Long[] idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}
	public String[] getNomCategoriaOli() {
		return nomCategoriaOli;
	}
	public void setNomCategoriaOli(String[] nomCategoriaOli) {
		this.nomCategoriaOli = nomCategoriaOli;
	}
	public Double[] getAcidesa() {
		return acidesa;
	}
	public void setAcidesa(Double[] acidesa) {
		this.acidesa = acidesa;
	}
	public String[] getObservacions() {
		return observacions;
	}
	public void setObservacions(String[] observacions) {
		this.observacions = observacions;
	}
	public Long[] getDipositId() {
		return dipositId;
	}
	public void setDipositId(Long[] dipositId) {
		this.dipositId = dipositId;
	}
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	public Establiment getEstabliment() {
		return establiment;
	}
	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Long[] getIdPartidaOliLots() {
		return idPartidaOliLots;
	}
	public void setIdPartidaOliLots(Long[] idPartidaOliLots) {
		this.idPartidaOliLots = idPartidaOliLots;
	}
	public String[] getNomCategoriaOliLots() {
		return nomCategoriaOliLots;
	}
	public void setNomCategoriaOliLots(String[] nomCategoriaOliLots) {
		this.nomCategoriaOliLots = nomCategoriaOliLots;
	}
	public Double[] getAcidesaLots() {
		return acidesaLots;
	}
	public void setAcidesaLots(Double[] acidesaLots) {
		this.acidesaLots = acidesaLots;
	}
	public String[] getObservacionsLots() {
		return observacionsLots;
	}
	public void setObservacionsLots(String[] observacionsLots) {
		this.observacionsLots = observacionsLots;
	}
	public Long[] getLotId() {
		return lotId;
	}
	public void setLotId(Long[] lotId) {
		this.lotId = lotId;
	}
	public Long[] getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(Long[] marcaId) {
		this.marcaId = marcaId;
	}
	public Long[] getEtiquetatgeId() {
		return etiquetatgeId;
	}
	public void setEtiquetatgeId(Long[] etiquetatgeId) {
		this.etiquetatgeId = etiquetatgeId;
	}
	public String[] getNumeroLot() {
		return numeroLot;
	}
	public void setNumeroLot(String[] numeroLot) {
		this.numeroLot = numeroLot;
	}
	public Integer[][] getVarietatsId() {
		return varietatsId;
	}
	public void setVarietatsId(Integer[][] varietatsId) {
		this.varietatsId = varietatsId;
	}
	public Long[] getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long[] zonaId) {
		this.zonaId = zonaId;
	}
	public Integer[] getNumeroBotellesInicials() {
		return numeroBotellesInicials;
	}
	public void setNumeroBotellesInicials(Integer[] numeroBotellesInicials) {
		this.numeroBotellesInicials = numeroBotellesInicials;
	}
	public Integer[] getNumeroBotellesActuals() {
		return numeroBotellesActuals;
	}
	public void setNumeroBotellesActuals(Integer[] numeroBotellesActuals) {
		this.numeroBotellesActuals = numeroBotellesActuals;
	}
	public BasicData[][] getEtiquetatges() {
		return etiquetatges;
	}
	public void setEtiquetatges(BasicData[][] etiquetatges) {
		this.etiquetatges = etiquetatges;
	}
	public Double[] getLitrosInicialsLots() {
		return litrosInicialsLots;
	}
	public void setLitrosInicialsLots(Double[] litrosInicialsLots) {
		this.litrosInicialsLots = litrosInicialsLots;
	}
	public Double[] getLitrosActualsLots() {
		return litrosActualsLots;
	}
	public void setLitrosActualsLots(Double[] litrosActualsLots) {
		this.litrosActualsLots = litrosActualsLots;
	}
	public Double[] getKilosInicialsLots() {
		return kilosInicialsLots;
	}
	public void setKilosInicialsLots(Double[] kilosInicialsLots) {
		this.kilosInicialsLots = kilosInicialsLots;
	}
	public Double[] getKilosActualsLots() {
		return kilosActualsLots;
	}
	public void setKilosActualsLots(Double[] kilosActualsLots) {
		this.kilosActualsLots = kilosActualsLots;
	}
	public Double[] getVolum() {
		return volum;
	}
	public void setVolum(Double[] volum) {
		this.volum = volum;
	}
	public Integer[] getIdCategoriaOli() {
		return idCategoriaOli;
	}
	public void setIdCategoriaOli(Integer[] idCategoriaOli) {
		this.idCategoriaOli = idCategoriaOli;
	}
	public Boolean[] getEtiquetar() {
		return etiquetar;
	}
	public void setEtiquetar(Boolean[] etiquetar) {
		this.etiquetar = etiquetar;
	}
	public String[] getEtiquetaLletra() {
		return etiquetaLletra;
	}
	public void setEtiquetaLletra(String[] etiquetaLletra) {
		this.etiquetaLletra = etiquetaLletra;
	}
	public String[] getEtiquetaInici() {
		return etiquetaInici;
	}
	public void setEtiquetaInici(String[] etiquetaInici) {
		this.etiquetaInici = etiquetaInici;
	}
	public Double[] getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(Double[] quantitat) {
		this.quantitat = quantitat;
	}
	public String[] getEtiquetaFi() {
		return etiquetaFi;
	}
	public void setEtiquetaFi(String[] etiquetaFi) {
		this.etiquetaFi = etiquetaFi;
	}
	public String[] getEtiquetaCapacitat() {
		return etiquetaCapacitat;
	}
	public void setEtiquetaCapacitat(String[] etiquetaCapacitat) {
		this.etiquetaCapacitat = etiquetaCapacitat;
	}
	public Double[] getLitres() {
		return litres;
	}
	public void setLitres(Double[] litres) {
		this.litres = litres;
	}
	public Date[] getDataConsum() {
		return dataConsum;
	}
	public void setDataConsum(Date[] dataConsum) {
		this.dataConsum = dataConsum;
	}
}
