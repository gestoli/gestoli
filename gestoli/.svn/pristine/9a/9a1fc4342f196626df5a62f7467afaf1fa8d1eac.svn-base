package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Javadoc de les clases Model de Gest Oli
 * 
 * @author Oriol Barnes Cruz (obarnes@at4.net)
 * 
 */
public class Elaboracio implements Comparable {

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

	private Boolean valid;
	
	private PartidaOli partidaOli;
	
	private Integer numPrintsDocRendiment;
	
	private Integer autoNumDocRendiment;

	private Set partidaOlivas = new HashSet(0);

	public Elaboracio() {
	}

	public Elaboracio(CategoriaOli categoriaOli, Traza traza, Date data,
			Integer numeroElaboracio, String responsable, Double acidesa,
			Double temperatura, String talcMarcaComercial, String talcLot,
			Double talcQuantitat, Double litres, Boolean valid) {
//		this.varietatOli = varietatOli;
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
		this.valid = valid;
	}

	public Elaboracio(CategoriaOli categoriaOli, Traza traza, Date data,
			Integer numeroElaboracio, String responsable, Double acidesa,
			Double temperatura, String talcMarcaComercial, String talcLot,
			Double talcQuantitat, Double litres, String observacions,
			Boolean valid, Set partidaOlivas) {
//		this.varietatOli = varietatOli;
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
		this.valid = valid;
		this.partidaOlivas = partidaOlivas;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public VarietatOli getVarietatOli() {
//		return this.varietatOli;
//	}
//
//	public void setVarietatOli(VarietatOli varietatOli) {
//		this.varietatOli = varietatOli;
//	}
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

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Set getPartidaOlivas() {
		return this.partidaOlivas;
	}

	public void setPartidaOlivas(Set partidaOlivas) {
		this.partidaOlivas = partidaOlivas;
	}

	public Integer getNumPrintsDocRendiment() {
		return numPrintsDocRendiment;
	}

	public void setNumPrintsDocRendiment(Integer numPrintsDocRendiment) {
		this.numPrintsDocRendiment = numPrintsDocRendiment;
	}

	public Integer getAutoNumDocRendiment() {
		return autoNumDocRendiment;
	}

	public void setAutoNumDocRendiment(Integer autoNumDocRendiment) {
		this.autoNumDocRendiment = autoNumDocRendiment;
	}
	
	// The following is extra code specified in the hbm.xml files

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

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}

	/**
	 * Devuelve una descripcion de total kilos de oliva que intervienen en la
	 * elaboracion
	 */
	public String getTotalQuilos() {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		double total = 0;
		Set partidas = this.partidaOlivas;
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives()
						.iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc
							.next();
					total += dpo.getKilos().doubleValue();
				}
			}
		}
		return numberDecimalFormat.format(total);
	}
	
	/**
	 * Devuelve una descripcion de total kilos de oliva que intervienen en la
	 * elaboracion
	 */
	public double getTotalKilos() {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		double total = 0;
		Set partidas = this.partidaOlivas;
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives()
						.iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc
							.next();
					total += dpo.getKilos().doubleValue();
				}
			}
		}
		return total;
	}

	/**
	 * Devuelve una descripcion de total kilos
	 */
	public String getNomVarietat() {
		String nom = "";
		Set partidas = this.partidaOlivas;
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives()
						.iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc
							.next();
					if (dpo.getDescomposicioPlantacio() != null) {
						DescomposicioPlantacio descompPlanta = dpo
								.getDescomposicioPlantacio();
						VarietatOliva varietat = descompPlanta
								.getVarietatOliva();
						if (varietat != null && varietat.getNom() != null) {
							if (nom != ""
									&& !nom.equalsIgnoreCase(varietat.getNom())) {
								nom = "mezcla";
								break;
							} else {
								if (varietat.getExperimental() != null && varietat.getExperimental().booleanValue()){
									nom = varietat.getNomVarietat();
								} else {
									nom = varietat.getNom();
								}
							}
						}
					}
				}
				if (nom.equalsIgnoreCase("mezcla")) {
					break;
				}
			}
		}
		return nom;
	}
	
	
	/**
	 * Devuelve una descripcion de total kilos
	 */
	public String getPorcentatjesVarietats() {
		Map<String, Double> varietats = new HashMap<String, Double>();
		
		String nom = "";
		Set partidas = this.partidaOlivas;
		Double totalKilos = 0.0;
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives().iterator();
				while (desc.hasNext()) {
					
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next();
					if (dpo.getDescomposicioPlantacio() != null) {
						
						DescomposicioPlantacio descompPlanta = dpo.getDescomposicioPlantacio();
						VarietatOliva varietat = descompPlanta.getVarietatOliva();
						if (varietat != null && varietat.getNom() != null) {
							
							// Recogemos el nombre de la variedad.
							if (varietat.getExperimental() != null && varietat.getExperimental().booleanValue()){
								nom = varietat.getNomVarietat();
							} else {
								nom = varietat.getNom();
							}
							
							// Actualizamos el map.
							if (varietats.containsKey(nom)){
							    varietats.put(nom,varietats.get(nom)+dpo.getKilos());
							} else {
							    varietats.put(nom,dpo.getKilos());
							}
							totalKilos += dpo.getKilos();

						}
					}
				}
			}
		}
		
		//Generamos el string final.
		nom = "";
		Iterator<Entry<String,Double>> it = varietats.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String,Double> e = it.next();
			Double porc = (e.getValue()*100 / totalKilos);
			int aux = (int)(porc*100);
			porc = (double)aux / 100;
			//DecimalFormat twoDForm = new DecimalFormat("#.##");
			//porc = Double.valueOf(twoDForm.format(porc));
			nom += porc.toString();
			nom += "% ";
			nom += e.getKey();
			nom += " ";
		}
		
		return nom;
	}

	public Double getRendiment(){
		try{
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
	
			Number totalQuilos = numberDecimalFormat.parse(this.getTotalQuilos());
			Double rendiment = (this.getLitres() * 100) / totalQuilos.doubleValue();
			
			return rendiment;
		
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Comparator
	 */
	public int compareTo(Object e) {
		Elaboracio elaboracion = (Elaboracio) e;
		if (this.data.compareTo(elaboracion.getData()) == 0) {
			return this.numeroElaboracio.compareTo(elaboracion
					.getNumeroElaboracio());
		} else {
			return this.data.compareTo(elaboracion.getData());
		}
	}

	/**
	 * Devuelve el nombre del establecimiento donde se realiza la elaboracion
	 */
	public String getEstablimentName() {
		String establimentName = "";
		Set partidas = this.partidaOlivas;
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			establimentName = partida.getZona().getEstabliment().getNom();
			break;
		}
		return establimentName;
	}

	// end of extra code specified in the hbm.xml files

}
