/**
 * 
 */
package es.caib.gestoli.logic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.AutocontrolOliva;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.ElaboracioOliva;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaBota;
import es.caib.gestoli.logic.model.VarietatOli;
import es.caib.gestoli.logic.model.VarietatOliva;

/**
 * @author siona
 *
 */
public class TrazabilitatResumida {

	private Integer tipus;
	private PartidaOli partidaOli;
	private VarietatOli tipusOli;
	private Double acidesa;
	@SuppressWarnings("rawtypes")
	private Map varietats;
	@SuppressWarnings("rawtypes")
	private Map varietatPlantacio;
	@SuppressWarnings("rawtypes")
	private Map municipis;
	private Double quantitatTotal;
	private Double quantitatTotalCribada;
	private Double litresTotal;
	private List<Elaboracio> elaboracions;
	private List<ElaboracioOliva> elaboracionsOliva;
	private Establiment envasadora;
	private List<EntradaLot> lotsEmbotellat;
	private List<SortidaBota> sortidesBota;
	private Double rendiment;
	private Establiment establiment;
	private Analitica analitica;
	private AutocontrolOliva autocontrolOliva;
	@SuppressWarnings("rawtypes")
	private Set plantacions;
	private Lot lot;
	private Diposit diposit;
	private Bota bota;
	private String etiquetaLletra;
	private Boolean olivaTaula;
	private Integer tipusOliva;
	@SuppressWarnings("rawtypes")
	private Map oliAfegit;
	private Integer categoria;
	private String lotOliAfegit;
	private Double volumDiposit;
	private List<Diposit> dipositsAmbPartidaId;
	
	@SuppressWarnings("rawtypes")
	public TrazabilitatResumida(){
		this.tipus = Constants.TIPUS_TRAZABILITAT_RESUMIDA_PARTIDA;
		this.partidaOli = null;
		this.tipusOli = null;
		this.acidesa = 0.0;
		this.varietats = new HashMap();
		this.varietatPlantacio = new HashMap();
		this.municipis = new HashMap();
		this.quantitatTotal = 0.0;
		this.quantitatTotalCribada = null;
		this.litresTotal = 0.0;
		this.elaboracions = new ArrayList<Elaboracio>();
		this.elaboracionsOliva = new ArrayList<ElaboracioOliva>();
		this.envasadora = null;
		this.lotsEmbotellat = new ArrayList<EntradaLot>();
		this.sortidesBota = new ArrayList<SortidaBota>();
		this.rendiment = 0.0;
		this.establiment = null;
		this.analitica = null;
		this.plantacions = new HashSet();
		this.lot = null;
		this.diposit = null;
		this.bota = null;
		this.etiquetaLletra = null;
		this.olivaTaula = null;
		this.autocontrolOliva = autocontrolOliva;
		this.tipusOliva = tipusOliva;
		this.oliAfegit = new HashMap();
		this.categoria = null;
		this.lotOliAfegit = null;
		this.volumDiposit = null;
		this.dipositsAmbPartidaId = new ArrayList<Diposit>();
	}
	
	public void setResum(DescomposicioPartidaOliva descomposicioPartida){
		setResum(descomposicioPartida, null);
	}
	public void setResum(DescomposicioPartidaOliva descomposicioPartida, Double percent){
		DescomposicioPlantacio descomposicioPlantacio = descomposicioPartida.getDescomposicioPlantacio();
		Double quantitat = descomposicioPartida.getKilos();
		if (percent != null) {
			quantitat = Math.round(quantitat * percent) / 100.0;
		}
		PartidaOliva entrada = descomposicioPartida.getPartidaOliva();
		
		// A varietats guardam parells (Varietat - Quantitat)
		if (this.varietats.containsKey(descomposicioPlantacio.getVarietatOliva())){
			this.varietats.put(descomposicioPlantacio.getVarietatOliva(), (Double)this.varietats.get(descomposicioPlantacio.getVarietatOliva()) + quantitat);
			
			// A varietatPlantació guardam parells (Varietat - parells[DescomposicioPartida - Quantitat]) 
			Map plantacio = (Map)this.varietatPlantacio.get(descomposicioPlantacio.getVarietatOliva());
			if (plantacio.containsKey(descomposicioPartida)){ //descomposicioPlantacio.getPlantacio())){
//				plantacio.put(descomposicioPlantacio.getPlantacio(), (Double)plantacio.get(descomposicioPlantacio.getPlantacio()) + quantitat);
				plantacio.put(descomposicioPartida, (Double)plantacio.get(descomposicioPartida) + quantitat);
			} else {
//				plantacio.put(descomposicioPlantacio.getPlantacio(), quantitat);
				plantacio.put(descomposicioPartida, quantitat);
			}
			this.varietatPlantacio.put(descomposicioPlantacio.getVarietatOliva(), plantacio);
		} else {
			this.varietats.put(descomposicioPlantacio.getVarietatOliva(), quantitat);
			
			Map plantacio = new HashMap();
//			plantacio.put(descomposicioPlantacio.getPlantacio(), quantitat);
			plantacio.put(descomposicioPartida, quantitat);
			this.varietatPlantacio.put(descomposicioPlantacio.getVarietatOliva(), plantacio);
		}
		
		// A municipis guardam parells (Municipi - Llista de Plantacions)
		if (this.municipis.containsKey(descomposicioPlantacio.getPlantacio().getMunicipi())){
			List plantacio = (List)this.municipis.get(descomposicioPlantacio.getPlantacio().getMunicipi());
			if (!plantacio.contains(descomposicioPlantacio.getPlantacio())){
				plantacio.add(descomposicioPlantacio.getPlantacio());
				this.municipis.put(descomposicioPlantacio.getPlantacio().getMunicipi(), plantacio);
			}
		} else {
			List plantacio = new ArrayList();
			plantacio.add(descomposicioPlantacio.getPlantacio());
			this.municipis.put(descomposicioPlantacio.getPlantacio().getMunicipi(), plantacio);
		}
		this.plantacions.add(descomposicioPlantacio.getPlantacio());
		this.quantitatTotal += quantitat;
	}
	
	// Mostram la partida qualificada, mentres no es demostri el contrari
	public boolean isQualificada(){
		if (this.partidaOli != null && !this.partidaOli.getEsQualificada()) return false;
		else return true;
	}
	public boolean isEmpty(){
		return (this.quantitatTotal == null || this.quantitatTotal <= 0.0);
	}
	
	@SuppressWarnings("rawtypes")
	public Map getVarietats(){
		return this.varietats;
	}
	@SuppressWarnings("rawtypes")
	public Map getMunicipis(){
		return this.municipis;
	}
	@SuppressWarnings("rawtypes")
	public String getNomMunicipis(){
		String noms = "";
		for(Iterator itMunicipis = this.municipis.entrySet().iterator(); itMunicipis.hasNext();){
			Map.Entry parell = (Map.Entry)itMunicipis.next();
			noms += ((Municipi)parell.getKey()).getNom() + ", ";
		}
		if(noms.length() > 1) noms = noms.substring(0, noms.length() - 2);
		return noms;
	}
	@SuppressWarnings("rawtypes")
	public Object[][] getMunicipisPoblacionsArray(){
		if (this.municipis.size() > 0){
			Object[][] munpla = new Object[this.municipis.size()][2];
			int i = 0;
			for(Iterator itMunicipis = this.municipis.entrySet().iterator(); itMunicipis.hasNext();){
				Map.Entry parell = (Map.Entry)itMunicipis.next();
				munpla[i][0] = ((Municipi)parell.getKey()).getNom();
				munpla[i][1] = (List)parell.getValue();
				i++;
			}
			return munpla;
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public Map getVarietatsPlantacio(){
		return this.varietatPlantacio;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getPercentatgesVarietats(){
		Map percentatges = new HashMap();
		
		if (this.quantitatTotal > 0.0){
			for(Iterator itVarietats = this.varietats.entrySet().iterator(); itVarietats.hasNext();){
				Map.Entry parell = (Map.Entry)itVarietats.next();
				percentatges.put(parell.getKey(), (Double)parell.getValue()*100/this.quantitatTotal);
			}
		}
		return percentatges;
	}
	@SuppressWarnings("rawtypes")
	public Object[][] getPercentatgesVarietatsArray(){
		if (this.quantitatTotal > 0.0){
			Object[][] varper = new Object[this.varietats.size()][2];
			int i = 0;
			for(Iterator itVarietats = this.varietats.entrySet().iterator(); itVarietats.hasNext();){
				Map.Entry parell = (Map.Entry)itVarietats.next();
				varper[i][0] = parell.getKey();
				varper[i][1] = (Double)parell.getValue()*100/this.quantitatTotal;
				i++;
			}
			return varper;
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public Object[][] getVarietatsPlantacioArray(){
		Object[][] varpla = new Object[this.varietatPlantacio.size()][3];
		int i = 0;
		for(Iterator itVarietats = this.varietatPlantacio.entrySet().iterator(); itVarietats.hasNext();){
			Map.Entry parell = (Map.Entry)itVarietats.next();
			Double quantitat = 0.0;
			varpla[i][0] = parell.getKey();
			
			Map plantacio = (Map)parell.getValue();
			Object[][] plants = new Object[plantacio.size()][2];
			int j = 0;
			for(Iterator itplants = plantacio.entrySet().iterator(); itplants.hasNext();){
				Map.Entry parell2 = (Map.Entry)itplants.next();
				plants[j][0] = parell2.getKey();
				plants[j][1] = (Double)parell2.getValue();
				quantitat += (Double)plants[j][1];
				j++;
			}
			
			varpla[i][1] = quantitat;
			varpla[i][2] = plants;
			i++;
		}
		return varpla;
	}
	
	public Double getQuantitatVarietat(VarietatOliva var){
		if (this.varietats.containsKey(var)){
			return (Double)this.varietats.get(var);
		}
		return 0.0;
	}
	
	public Object[] getArbre(){
		Object[] arbre = new Object[30];
		
		arbre[Constants.TRAZABILITAT_RESUMIDA_ESTABLIMENT] = this.getEstabliment();
		arbre[Constants.TRAZABILITAT_RESUMIDA_CATEGORIA_OLI] = this.getCategoria(); 
		arbre[Constants.TRAZABILITAT_RESUMIDA_TIPUS_OLI] = this.tipusOli;
		arbre[Constants.TRAZABILITAT_RESUMIDA_ACIDESA] = this.acidesa;
		arbre[Constants.TRAZABILITAT_RESUMIDA_ANALITICA] = this.analitica;
		arbre[Constants.TRAZABILITAT_RESUMIDA_PERCENTATGE_VARIETATS] = this.getPercentatgesVarietatsArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_QUANTITAT_TOTAL] = this.quantitatTotal;
		arbre[Constants.TRAZABILITAT_RESUMIDA_QUANTITAT_TOTAL_CRIBADA] = this.quantitatTotalCribada;
		arbre[Constants.TRAZABILITAT_RESUMIDA_VARIETAT_PLATACIONS] = this.getVarietatsPlantacioArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_LITRES_TOTAL] = this.litresTotal;
		arbre[Constants.TRAZABILITAT_RESUMIDA_RENDIMENT] = this.rendiment;
		arbre[Constants.TRAZABILITAT_RESUMIDA_MUNICIPIS] = this.getMunicipisPoblacionsArray(); //this.getNomMunicipis();
		arbre[Constants.TRAZABILITAT_RESUMIDA_PLANTACIONS] = this.plantacions.toArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_ELABORACIONS] = this.getElaboracionsArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_ELABORACIONS_OLIVA] = this.getElaboracionsOlivaArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_ESTABLIMENT_EMBOTELLAT] = this.getEmbotellatsArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_SORTIDA_BOTA] = this.getSortidesBotaArray();
		arbre[Constants.TRAZABILITAT_RESUMIDA_LOT] = this.lot;
		arbre[Constants.TRAZABILITAT_RESUMIDA_DIPOSIT] = this.diposit;
		arbre[Constants.TRAZABILITAT_RESUMIDA_BOTA] = this.bota;
		arbre[Constants.TRAZABILITAT_RESUMIDA_TIPUS] = this.tipus;
		arbre[Constants.TRAZABILITAT_RESUMIDA_ETIQUETALLETRA] = this.etiquetaLletra;
		arbre[Constants.TRAZABILITAT_RESUMIDA_OLIVA_TAULA] = this.olivaTaula;
		arbre[Constants.TRAZABILITAT_RESUMIDA_AUTOCONTROL] = this.autocontrolOliva;
		arbre[Constants.TRAZABILITAT_RESUMIDA_TIPUS_OLIVA] = this.tipusOliva;
		arbre[Constants.TRAZABILITAT_RESUMIDA_OLI_AFEGIT] = this.oliAfegit;
		arbre[Constants.TRAZABILITAT_RESUMIDA_LOT_OLI_AFEGIT] = this.lotOliAfegit;
		arbre[Constants.TRAZABILITAT_RESUMIDA_PARTIDA_OLI] = this.partidaOli; 
		arbre[Constants.TRAZABILITAT_RESUMIDA_VOLUM_DIPOSIT] = this.volumDiposit;
		arbre[Constants.TRAZABILITAT_RESUMIDA_DIPOSITS_AMB_PARTIDAID] = this.dipositsAmbPartidaId;
		return arbre;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[] getElaboracionsArray(){
		Collections.sort(
				this.elaboracions,
				new Comparator() {
					public int compare(Object obj1, Object obj2) {
						Elaboracio el1 = (Elaboracio)obj1;
						Elaboracio el2 = (Elaboracio)obj2;
						
						Long e1 = el1.getPartidaOli().getEstabliment().getId();
						Long e2 = el2.getPartidaOli().getEstabliment().getId();
						
						if(e1.compareTo(e2) == 0) {
							Date d1 = el1.getData();
							Date d2 = el2.getData();
							
							if(d1.compareTo(d2) == 0){
								Long t1 = el1.getTraza().getId();
								Long t2 = el2.getTraza().getId();
								
								return t1.compareTo(t2);
							} else {
								return d1.compareTo(d2);
							}
							
						} else {
							return e1.compareTo(e2);
						}
					}
				});
		return this.elaboracions.toArray();
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getElaboracionsOlivaArray(){
		Collections.sort(
				this.elaboracionsOliva,
				new Comparator() {
					public int compare(Object obj1, Object obj2) {
						ElaboracioOliva el1 = (ElaboracioOliva)obj1;
						ElaboracioOliva el2 = (ElaboracioOliva)obj2;
						
						Long e1 = el1.getBota().getEstabliment().getId();
						Long e2 = el2.getBota().getEstabliment().getId();
						
						if(e1.compareTo(e2) == 0) {
							Date d1 = el1.getData();
							Date d2 = el2.getData();
							
							if(d1.compareTo(d2) == 0){
								Long t1 = el1.getTraza().getId();
								Long t2 = el2.getTraza().getId();
								
								return t1.compareTo(t2);
							} else {
								return d1.compareTo(d2);
							}
							
						} else {
							return e1.compareTo(e2);
						}
					}
				});
		return this.elaboracionsOliva.toArray();
	}
	
	public Object[] getEmbotellatsArray(){
		Collections.sort(
				this.lotsEmbotellat,
				new Comparator() {
					public int compare(Object obj1, Object obj2) {
						EntradaLot el1 = (EntradaLot)obj1;
						EntradaLot el2 = (EntradaLot)obj2;
						
						Long e1 = el1.getZona().getEstabliment().getId();
						Long e2 = el2.getZona().getEstabliment().getId();
						
						if(e1.compareTo(e2) == 0) {
							Date d1 = el1.getData();
							Date d2 = el2.getData();
							
							if(d1.compareTo(d2) == 0){
								Long t1 = el1.getTraza().getId();
								Long t2 = el2.getTraza().getId();
								
								return t1.compareTo(t2);
							} else {
								return d1.compareTo(d2);
							}
							
						} else {
							return e1.compareTo(e2);
						}
					}
				});
		return this.lotsEmbotellat.toArray();
	}
	
	public Object[] getSortidesBotaArray(){
		Collections.sort(
				this.sortidesBota,
				new Comparator() {
					public int compare(Object obj1, Object obj2) {
						SortidaBota sb1 = (SortidaBota)obj1;
						SortidaBota sb2 = (SortidaBota)obj2;
						
						Long e1 = sb1.getEstabliment().getId();
						Long e2 = sb2.getEstabliment().getId();
						
						if(e1.compareTo(e2) == 0) {
							Date d1 = sb1.getData();
							Date d2 = sb2.getData();
							
							if(d1.compareTo(d2) == 0){
								Long t1 = sb1.getTraza().getId();
								Long t2 = sb2.getTraza().getId();
								
								return t1.compareTo(t2);
							} else {
								return d1.compareTo(d2);
							}
							
						} else {
							return e1.compareTo(e2);
						}
					}
				});
		return this.sortidesBota.toArray();
	}
	
	public PartidaOli getPartidaOli(){
		return this.partidaOli;
	}
	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
		this.establiment = partidaOli.getEstabliment();
	}
	public Integer getCategoria(){
		return (this.partidaOli != null ? this.partidaOli.getCategoriaOli().getId() : this.categoria);
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public VarietatOli getTipusOli(){
		return this.tipusOli;
	}
	public void setTipusOli(VarietatOli tipusOli) {
		this.tipusOli = tipusOli;
	}
	public Double getAcidesa(){
		return this.acidesa;
	}
	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}
	public List<Elaboracio> getElaboracions() {
		return elaboracions;
	}
	public void setElaboracions(List<Elaboracio> elaboracions) {
		this.elaboracions = elaboracions;
		this.litresTotal = 0.0;
		for (Elaboracio elaboracio: elaboracions){
			this.litresTotal += elaboracio.getLitres();
		}
	}
	public List<ElaboracioOliva> getElaboracionsOliva() {
		return elaboracionsOliva;
	}
	public void setElaboracionsOliva(List<ElaboracioOliva> elaboracionsOliva) {
		this.elaboracionsOliva = elaboracionsOliva;
		this.litresTotal = 0.0;
		for (ElaboracioOliva elaboracio: elaboracionsOliva){
			this.litresTotal += elaboracio.getBota().getKgOliva();
		}
	}
	public void setLitresTotal(Double litresTotal) {
		this.litresTotal = litresTotal;
	}
	public List<EntradaLot> getLotsEmbotellat() {
		return lotsEmbotellat;
	}
	public void setQuantitatTotal(Double quantitatTotal) {
		this.quantitatTotal = quantitatTotal;
	}
	public void setQuantitatTotalCribada(Double quantitatTotalCribada) {
		this.quantitatTotalCribada = quantitatTotalCribada;
	}
	public void setLotsEmbotellat(List<EntradaLot> lotsEmbotellat) {
		this.lotsEmbotellat = lotsEmbotellat;
	}
	public void setSortidesBota(List<SortidaBota> sortidesBota) {
		this.sortidesBota = sortidesBota;
	}
	public Establiment getEnvasadora(){
		return this.envasadora;
	}
	public void setEnvasadora(Establiment envasadora) {
		this.envasadora = envasadora;
	}
	public List<EntradaLot> getEmbotellats(){
		return this.lotsEmbotellat;
	}
	public List<SortidaBota> getSortidesBota() {
		return sortidesBota;
	}
	public Double getRendiment() {
		return rendiment;
	}
	public void setRendiment(Double rendiment) {
		this.rendiment = rendiment;
	}
	public Establiment getEstabliment() {
		return establiment;
	}
	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	public Analitica getAnalitica() {
		return analitica;
	}
	public void setAnalitica(Analitica analitica) {
		this.analitica = analitica;
		if (analitica != null){
			this.tipusOli = analitica.getVarietatOli();
		}
	}
	public AutocontrolOliva getAutocontrolOliva() {
		return autocontrolOliva;
	}
	public void setAutocontrolOliva(AutocontrolOliva autocontrolOliva) {
		this.autocontrolOliva = autocontrolOliva;
	}
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	public Diposit getDiposit() {
		return diposit;
	}
	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}
	public Integer getTipus() {
		return tipus;
	}
	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}
	public String getEtiquetaLletra() {
		return etiquetaLletra;
	}
	public void setEtiquetaLletra(String etiquetaLletra) {
		this.etiquetaLletra = etiquetaLletra;
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
	public Integer getTipusOliva() {
		return tipusOliva;
	}
	public void setTipusOliva(Integer tipusOliva) {
		this.tipusOliva = tipusOliva;
	}
	public Map<String, Long> getOliAfegit() {
		return oliAfegit;
	}
	public void setOliAfegit(Map<String, Long> oliAfegit) {
		this.oliAfegit = oliAfegit;
	}
	public String getLotOliAfegit() {
		return lotOliAfegit;
	}
	public void setLotOliAfegit(String lotOliAfegit) {
		this.lotOliAfegit = lotOliAfegit;
	}

	public Double getVolumDiposit() {
		return volumDiposit;
	}

	public void setVolumDiposit(Double volumDiposit) {
		this.volumDiposit = volumDiposit;
	}

	public List<Diposit> getDipositsAmbPartidaId() {
		return dipositsAmbPartidaId;
	}

	public void setDipositsAmbPartidaId(List<Diposit> dipositsAmbPartidaId) {
		this.dipositsAmbPartidaId = dipositsAmbPartidaId;
	}
	
	
}
