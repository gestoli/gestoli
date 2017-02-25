package es.caib.gestoli.logic.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.VarietatOli;

public class ElaboracioAux implements java.io.Serializable {

	private Long id;
//	private VarietatOli varietatOli;
	private CategoriaOli categoriaOli;
	private Traza traza;
	private Date data;
	private Integer numeroElaboracio;
	private String responsable;
	private Double acidesa;
	private Double temperatura;
	private String talcMarcaComercial;
	private String talcLot;
	private Double talcQuantitat;
	private Double litres;
	private String observacions;
	private Set partidaOlivas = new HashSet(0);
//	private Integer idVarietatOli;
	private Integer idCategoriaOli;
	private PartidaOliva[] partides;
	private Diposit[] diposits;
	private Double[] litros;
	private Double[] kilos;
	private Long idPartidaOli;
	
	private Double[] litrosRetirats;
	private Long[] idOlivicultors;
	
	public ElaboracioAux() {
	}

	public ElaboracioAux(CategoriaOli categoriaOli, Traza traza, Date data,
			Integer numeroElaboracio, String responsable, Double acidesa,
			Double temperatura, String talcMarcaComercial, String talcLot,
			Double talcQuantitat, Double litres) {
		this.categoriaOli = categoriaOli;
		this.traza = traza;
		this.data = data;
		this.numeroElaboracio = numeroElaboracio;
		this.responsable = responsable;
		this.acidesa = acidesa;
		this.temperatura = temperatura;
		this.talcMarcaComercial = talcMarcaComercial;
		this.talcLot = talcLot;
		this.talcQuantitat = talcQuantitat;
		this.litres = litres;
	}

	public ElaboracioAux(CategoriaOli categoriaOli, Traza traza, Date data,
			Integer numeroElaboracio, String responsable, Double acidesa,
			Double temperatura, String talcMarcaComercial, String talcLot,
			Double talcQuantitat, Double litres, String observacions,
			Set partidaOlivas) {
		this.categoriaOli = categoriaOli;
		this.traza = traza;
		this.data = data;
		this.numeroElaboracio = numeroElaboracio;
		this.responsable = responsable;
		this.acidesa = acidesa;
		this.temperatura = temperatura;
		this.talcMarcaComercial = talcMarcaComercial;
		this.talcLot = talcLot;
		this.talcQuantitat = talcQuantitat;
		this.litres = litres;
		this.observacions = observacions;
		this.partidaOlivas = partidaOlivas;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriaOli getCategoriaOli() {
		return categoriaOli;
	}

	public void setCategoriaOli(CategoriaOli categoriaOli) {
		this.categoriaOli = categoriaOli;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getNumeroElaboracio() {
		return this.numeroElaboracio;
	}

	public void setNumeroElaboracio(Integer numeroElaboracio) {
		this.numeroElaboracio = numeroElaboracio;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public Double getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getTalcMarcaComercial() {
		return this.talcMarcaComercial;
	}

	public void setTalcMarcaComercial(String talcMarcaComercial) {
		this.talcMarcaComercial = talcMarcaComercial;
	}

	public String getTalcLot() {
		return this.talcLot;
	}

	public void setTalcLot(String talcLot) {
		this.talcLot = talcLot;
	}

	public Double getTalcQuantitat() {
		return this.talcQuantitat;
	}

	public void setTalcQuantitat(Double talcQuantitat) {
		this.talcQuantitat = talcQuantitat;
	}

	public Double getLitres() {
		return this.litres;
	}

	public void setLitres(Double litres) {
		this.litres = litres;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Set getPartidaOlivas() {
		return this.partidaOlivas;
	}

	public void setPartidaOlivas(Set partidaOlivas) {
		this.partidaOlivas = partidaOlivas;
	}
	
	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}
	public String getDataFormatCurt() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		return sdf.format(this.data);
	}
	
	public Double[] getLitros() {
		return litros;
	}

	public void setLitros(Double[] litros) {
		this.litros = litros;
	}

	public Double[] getLitrosRetirats() {
		return litrosRetirats;
	}

	public void setLitrosRetirats(Double[] litrosRetirats) {
		this.litrosRetirats = litrosRetirats;
	}

	public Long[] getIdOlivicultors() {
		return idOlivicultors;
	}

	public void setIdOlivicultors(Long[] idOlivicultors) {
		this.idOlivicultors = idOlivicultors;
	}

//	public Integer getIdVarietatOli() {
//		return idVarietatOli;
//	}
//
//	public void setIdVarietatOli(Integer idVarietatOli) {
//		this.idVarietatOli = idVarietatOli;
//	}

	public Integer getIdCategoriaOli() {
		return idCategoriaOli;
	}

	public void setIdCategoriaOli(Integer idCategoriaOli) {
		this.idCategoriaOli = idCategoriaOli;
	}

	public Double[] getKilos() {
		return kilos;
	}

	public void setKilos(Double[] kilos) {
		this.kilos = kilos;
	}

	public Diposit[] getDiposits() {
		return diposits;
	}

	public void setDiposits(Diposit[] diposits) {
		this.diposits = diposits;
	}

	public PartidaOliva[] getPartides() {
		return partides;
	}

	public void setPartides(PartidaOliva[] partides) {
		this.partides = partides;
	}

	public Long getIdPartidaOli() {
		return idPartidaOli;
	}

	public void setIdPartidaOli(Long idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}
	
}
