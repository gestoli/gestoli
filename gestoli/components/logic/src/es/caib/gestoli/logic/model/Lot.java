package es.caib.gestoli.logic.model;

// Generated 30-nov-2009 17:09:50 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Lot implements Comparable {

	private Long id;
	private Zona zona;
	private Etiquetatge etiquetatge;
	private PartidaOli partidaOli;
	private Diposit diposit;
	private Date data;
	private String codiAssignat;
	private Double acidesa;
	private Double litresPerduts;
	private String motiuPerdua;
	private Integer numeroBotellesInicials;
	private Double litresEnvassats;
	private Integer posicioX;
	private Integer posicioY;
	private String observacions;
	private Boolean valid;
	private Integer numeroBotellesActuals;
	private Boolean creatTancament;
	private String numeroLot;
	private Date dataConsum;
	private Producte producte;
	private Bota bota;
	private Boolean olivaTaula;
	private Double kgOlivaTaula;
	private Double totalOliAfegit;
	private Integer tipusOlivaTaula;
	private String lotOliAfegit;
	private Boolean olivaDO;
	private Date datafi;
	private String lotTap;

	private Set sortidaDiposits = new HashSet(0);
	private Set varietatOlivas = new HashSet(0);
	private Set sortidaLots = new HashSet(0);
	private Set entradaLots = new HashSet(0);
	private Set etiquetesLots = new HashSet(0);

	public Lot() {
	}

	public Lot(Zona zona, Etiquetatge etiquetatge, CategoriaOli categoriaOli,
			Date data, String codiAssignat, Double acidesa,
			Integer numeroBotellesInicials, Double litresEnvassats,
			Integer posicioX, Integer posicioY, Boolean valid,
			Integer numeroBotellesActuals, Boolean creatTancament, Producte producte, String lotTap) {
		this.zona = zona;
		this.etiquetatge = etiquetatge;
		this.partidaOli = partidaOli;
		this.data = data;
		this.codiAssignat = codiAssignat;
		this.acidesa = acidesa;
		this.numeroBotellesInicials = numeroBotellesInicials;
		this.litresEnvassats = litresEnvassats;
		this.posicioX = posicioX;
		this.posicioY = posicioY;
		this.valid = valid;
		this.numeroBotellesActuals = numeroBotellesActuals;
		this.creatTancament = creatTancament;
		this.producte = producte;
		this.lotTap = lotTap;
	}

	public Lot(Zona zona, Etiquetatge etiquetatge, CategoriaOli categoriaOli,
			Diposit diposit, Date data, String codiAssignat, Double acidesa,
			Double litresPerduts, String motiuPerdua,
			Integer numeroBotellesInicials, Double litresEnvassats,
			Integer posicioX, Integer posicioY, String observacions,
			Boolean valid, Integer numeroBotellesActuals,
			Boolean creatTancament, Set sortidaDiposits, Set varietatOlivas,
			Set sortidaLots, Set entradaLots, Set etiquetesLots, Producte producte, String lotTap) {
		this.zona = zona;
		this.etiquetatge = etiquetatge;
		this.partidaOli = partidaOli;
		this.diposit = diposit;
		this.data = data;
		this.codiAssignat = codiAssignat;
		this.acidesa = acidesa;
		this.litresPerduts = litresPerduts;
		this.motiuPerdua = motiuPerdua;
		this.numeroBotellesInicials = numeroBotellesInicials;
		this.litresEnvassats = litresEnvassats;
		this.posicioX = posicioX;
		this.posicioY = posicioY;
		this.observacions = observacions;
		this.valid = valid;
		this.numeroBotellesActuals = numeroBotellesActuals;
		this.creatTancament = creatTancament;
		this.sortidaDiposits = sortidaDiposits;
		this.varietatOlivas = varietatOlivas;
		this.sortidaLots = sortidaLots;
		this.entradaLots = entradaLots;
		this.etiquetesLots = etiquetesLots;
		this.producte = producte;
		this.lotTap = lotTap;
	}
	
	public Lot(Zona zona, Etiquetatge etiquetatge, PartidaOli partidaOli,
			Bota bota, Date data, String codiAssignat, Double acidesa,
			Integer numeroBotellesInicials, Double litresEnvassats,
			Integer posicioX, Integer posicioY, String observacions,
			Boolean valid, Integer numeroBotellesActuals,
			Boolean creatTancament, Set sortidaDiposits, Set varietatOlivas,
			Set sortidaLots, Set entradaLots, Set etiquetesLots, Producte producte, String lotTap) {
		this.zona = zona;
		this.etiquetatge = etiquetatge;
		this.partidaOli = partidaOli;
		this.bota = bota;
		this.data = data;
		this.codiAssignat = codiAssignat;
		this.acidesa = 0.0;
		this.numeroBotellesInicials = numeroBotellesInicials;
		this.litresEnvassats = litresEnvassats;
		this.posicioX = posicioX;
		this.posicioY = posicioY;
		this.observacions = observacions;
		this.valid = valid;
		this.numeroBotellesActuals = numeroBotellesActuals;
		this.creatTancament = creatTancament;
		this.varietatOlivas = varietatOlivas;
		this.sortidaLots = sortidaLots;
		this.entradaLots = entradaLots;
		this.etiquetesLots = etiquetesLots;
		this.producte = producte;
		this.lotTap = lotTap;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Etiquetatge getEtiquetatge() {
		return this.etiquetatge;
	}

	public void setEtiquetatge(Etiquetatge etiquetatge) {
		this.etiquetatge = etiquetatge;
	}

	/**
	 * @return the partidaOli
	 */
	public PartidaOli getPartidaOli() {
		return partidaOli;
	}

	/**
	 * @param partidaOli the partidaOli to set
	 */
	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
	}

	public Diposit getDiposit() {
		return this.diposit;
	}

	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCodiAssignat() {
		return this.codiAssignat;
	}

	public void setCodiAssignat(String codiAssignat) {
		this.codiAssignat = codiAssignat;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public Double getLitresPerduts() {
		return this.litresPerduts;
	}

	public void setLitresPerduts(Double litresPerduts) {
		this.litresPerduts = litresPerduts;
	}

	public String getMotiuPerdua() {
		return this.motiuPerdua;
	}

	public void setMotiuPerdua(String motiuPerdua) {
		this.motiuPerdua = motiuPerdua;
	}

	public Integer getNumeroBotellesInicials() {
		return this.numeroBotellesInicials;
	}

	public void setNumeroBotellesInicials(Integer numeroBotellesInicials) {
		this.numeroBotellesInicials = numeroBotellesInicials;
	}

	public Double getLitresEnvassats() {
		return this.litresEnvassats;
	}

	public void setLitresEnvassats(Double litresEnvassats) {
		this.litresEnvassats = litresEnvassats;
	}
	
	public Double getKilosEnvassats() {
		return this.litresEnvassats * 0.916;
	}

	public Integer getPosicioX() {
		return this.posicioX;
	}

	public void setPosicioX(Integer posicioX) {
		this.posicioX = posicioX;
	}

	public Integer getPosicioY() {
		return this.posicioY;
	}

	public void setPosicioY(Integer posicioY) {
		this.posicioY = posicioY;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Integer getNumeroBotellesActuals() {
		return this.numeroBotellesActuals;
	}

	public void setNumeroBotellesActuals(Integer numeroBotellesActuals) {
		this.numeroBotellesActuals = numeroBotellesActuals;
	}

	public Boolean getCreatTancament() {
		return this.creatTancament;
	}

	public void setCreatTancament(Boolean creatTancament) {
		this.creatTancament = creatTancament;
	}

	public String getNumeroLot() {
		return numeroLot;
	}

	public void setNumeroLot(String numeroLot) {
		this.numeroLot = numeroLot;
	}

	public Set getSortidaDiposits() {
		return this.sortidaDiposits;
	}

	public void setSortidaDiposits(Set sortidaDiposits) {
		this.sortidaDiposits = sortidaDiposits;
	}

	public Set getVarietatOlivas() {
		return this.varietatOlivas;
	}

	public void setVarietatOlivas(Set varietatOlivas) {
		this.varietatOlivas = varietatOlivas;
	}

	public Set getSortidaLots() {
		return this.sortidaLots;
	}

	public void setSortidaLots(Set sortidaLots) {
		this.sortidaLots = sortidaLots;
	}

	public Set getEntradaLots() {
		return this.entradaLots;
	}

	public void setEntradaLots(Set entradaLots) {
		this.entradaLots = entradaLots;
	}

	public Set getEtiquetesLots() {
		return etiquetesLots;
	}

	public void setEtiquetesLots(Set etiquetesLots) {
		this.etiquetesLots = etiquetesLots;
	}

	public Date getDataConsum() {
		return dataConsum;
	}

	public void setDataConsum(Date dataConsum) {
		this.dataConsum = dataConsum;
	}
	
	public Producte getProducte() {
		return producte;
	}

	public void setProducte(Producte producte) {
		this.producte = producte;
	}
	
	
	// The following is extra code specified in the hbm.xml files

	public String getLotTap() {
		return lotTap;
	}

	public void setLotTap(String lotTap) {
		this.lotTap = lotTap;
	}

	public Bota getBota() {
		return bota;
	}

	public void setBota(Bota bota) {
		this.bota = bota;
	}

	public Boolean getOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(Boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}

	public Double getKgOlivaTaula() {
		return kgOlivaTaula;
	}

	public void setKgOlivaTaula(Double kgOlivaTaula) {
		this.kgOlivaTaula = kgOlivaTaula;
	}

	public Double getTotalOliAfegit() {
		return totalOliAfegit;
	}

	public void setTotalOliAfegit(Double totalOliAfegit) {
		this.totalOliAfegit = totalOliAfegit;
	}

	public String getLotOliAfegit() {
		return lotOliAfegit;
	}

	public void setLotOliAfegit(String lotOliAfegit) {
		this.lotOliAfegit = lotOliAfegit;
	}

	public Integer getTipusOlivaTaula() {
		return tipusOlivaTaula;
	}

	public void setTipusOlivaTaula(Integer tipusOlivaTaula) {
		this.tipusOlivaTaula = tipusOlivaTaula;
	}

	public Boolean getOlivaDO() {
		return olivaDO;
	}

	public void setOlivaDO(Boolean olivaDO) {
		this.olivaDO = olivaDO;
	}

	
	public Date getDatafi() {
		return datafi;
	}

	public void setDatafi(Date datafi) {
		this.datafi = datafi;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.data != null)
			return sdf.format(this.data);
		else
			return null;
	}

	public String getDataConsumFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.dataConsum != null)
			return sdf.format(this.dataConsum);
		else
			return null;
	}
	
	/**
	 * Devuelve el tipo del icono de lote
	 */
	public Long getTipo() {
		Long tipo = Long.valueOf("5"); // por defecto botella cristal transparente
		if (this.olivaTaula != null && this.olivaTaula == true) {
			if (this.tipusOlivaTaula != null) {
				tipo = new Long(this.tipusOlivaTaula + 106);
			}
		} else if (this.etiquetatge != null
				&& this.etiquetatge.getTipusEnvas() != null
				&& this.etiquetatge.getTipusEnvas().getMaterialTipusEnvas() != null
				&& this.etiquetatge.getTipusEnvas().getMaterialTipusEnvas()
						.getIcona() != null) {
			tipo = this.etiquetatge.getTipusEnvas().getMaterialTipusEnvas()
					.getIcona();
		}
		return tipo;
	}

	/**
	 * Devuelve el aceite disponible en kilos
	 */
	public Double getDisponibleOliQuilos() {
		Double cantidad = null;
		if (this.getLitresEnvassats() != null) {
			//PASAMOS A KG
			cantidad = Double.valueOf(String.valueOf((Double.valueOf("0.916")
					.doubleValue() * this.getLitresEnvassats().doubleValue())));
		}
		return cantidad;
	}
	
	public Double getOliQuilosPerduts() {
		Double cantidad = null;
		if (this.getLitresPerduts() != null) {
			//PASAMOS A KG
			cantidad = Double.valueOf(String.valueOf((Double.valueOf("0.916")
					.doubleValue() * this.getLitresPerduts().doubleValue())));
		}
		return cantidad;
	}
	
	public Double getTotalSortides() {
		Double total = 0.0;
		for (Iterator it = this.getSortidaLots().iterator(); it.hasNext();){
			SortidaLot slo = (SortidaLot)it.next();
			if (slo != null && slo.getValid()){
				total += slo.getVendaLitres();
			}
		}
		return total;
	}

	/**
	 * Comparator
	 */
	public int compareTo(Object l) {
		Lot lote = (Lot) l;
		return this.data.compareTo(lote.getData());
	}

	// end of extra code specified in the hbm.xml files

}
