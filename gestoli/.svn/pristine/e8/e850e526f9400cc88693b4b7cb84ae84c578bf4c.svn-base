/**
 * ProcesElaboracioOliCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.ElaboracioOliva;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaElaboracio;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.VarietatOliva;


/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class ProcesElaboracioOlivaCommand extends ElaboracioOliva {
	
	private Integer tipusOlivaTaula;
	private PartidaOliva[] partides;
	private String idBota;
	private Double kgMaxim;
	private Double[] kgOliva;
	private Double[] kgCriba;
	private Double kgBota;
	private Double concentracioSalmorra;
	private String lotSal;
	private Double gFonoll;
	private String lotFonoll;
	private Long partidaFonollId;
	private boolean fonollPropi;
	private Double gPebre;
	private String lotPebre;
	private Establiment establiment;
	private String errorObservaciones;
	private Long zonaId;
	

	public String getErrorObservaciones() {
		return errorObservaciones;
	}

	public void setErrorObservaciones(String errorObservaciones) {
		this.errorObservaciones = errorObservaciones;
	}

	public Integer getTipusOlivaTaula() {
		return tipusOlivaTaula;
	}

	public void setTipusOlivaTaula(Integer tipusOlivaTaula) {
		this.tipusOlivaTaula = tipusOlivaTaula;
	}


	public PartidaOliva[] getPartides() {
		return partides;
	}

	public void setPartides(PartidaOliva[] partides) {
		this.partides = partides;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public String getIdBota() {
		return idBota;
	}

	public void setIdBota(String idBota) {
		this.idBota = idBota;
	}

	public Double[] getKgOliva() {
		return kgOliva;
	}

	public void setKgOliva(Double[] kgOliva) {
		this.kgOliva = kgOliva;
	}

	public Double[] getKgCriba() {
		return kgCriba;
	}

	public void setKgCriba(Double[] kgCriba) {
		this.kgCriba = kgCriba;
	}

	public Double getKgBota() {
		return kgBota;
	}

	public void setKgBota(Double kgBota) {
		this.kgBota = kgBota;
	}

	public Double getConcentracioSalmorra() {
		return concentracioSalmorra;
	}

	public void setConcentracioSalmorra(Double concentracioSalmorra) {
		this.concentracioSalmorra = concentracioSalmorra;
	}

	public String getLotSal() {
		return lotSal;
	}

	public void setLotSal(String lotSal) {
		this.lotSal = lotSal;
	}

	public Double getgFonoll() {
		return gFonoll;
	}

	public void setgFonoll(Double gFonoll) {
		this.gFonoll = gFonoll;
	}

	public String getLotFonoll() {
		return lotFonoll;
	}

	public void setLotFonoll(String lotFonoll) {
		this.lotFonoll = lotFonoll;
	}

	public Long getPartidaFonollId() {
		return partidaFonollId;
	}

	public void setPartidaFonollId(Long partidaFonollId) {
		this.partidaFonollId = partidaFonollId;
	}

	public Boolean getFonollPropi() {
		return fonollPropi;
	}

	public void setFonollPropi(Boolean fonollPropi) {
		this.fonollPropi = fonollPropi;
	}

	public Double getgPebre() {
		return gPebre;
	}

	public void setgPebre(Double gPebre) {
		this.gPebre = gPebre;
	}

	public String getLotPebre() {
		return lotPebre;
	}

	public void setLotPebre(String lotPebre) {
		this.lotPebre = lotPebre;
	}


	public Double getKgMaxim() {
		return kgMaxim;
	}

	public void setKgMaxim(Double kgMaxim) {
		this.kgMaxim = kgMaxim;
	}

	public void setFonollPropi(boolean fonollPropi) {
		this.fonollPropi = fonollPropi;
	}

	public Long getZonaId() {
		return zonaId;
	}

	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getData());
	}
	

	/**
	 * Devuelve una descripcion de total kilos de oliva que intervienen en la elaboracion
	 */
	public String getTotalQuilos() {
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		double total = 0;
		Set partidas = this.getPartidaElaboracions();
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaElaboracio partida = (PartidaElaboracio)it.next();
			if (partida.getPartida().getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getPartida().getDescomposicioPartidesOlives().iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next();
					total += dpo.getKilos().doubleValue();
				}
			}
			
		}		
		return numberDecimalFormat.format(total);
	}
	
	/**
	 * Devuelve una descripcion de total kilos de oliva que intervienen en la elaboracion
	 */
	public double getTotalKilos() {
		
		double total = 0;
		Set partidas = this.getPartidaElaboracions();
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaElaboracio partida = (PartidaElaboracio)it.next();
			if (partida.getPartida().getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getPartida().getDescomposicioPartidesOlives().iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next();
					total += dpo.getKilos().doubleValue();
				}
			}
			
		}		
		return total;
	}

	/**
	 * Devuelve una descripcion de total kilos
	 */
	public String getNomVarietat(){
		String nom = "";
		Set partidas = this.getPartidaElaboracions();
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaElaboracio partida = (PartidaElaboracio)it.next();
			if (partida.getPartida().getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getPartida().getDescomposicioPartidesOlives().iterator();
				while (desc.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next();
					if (dpo.getDescomposicioPlantacio() != null) {
						DescomposicioPlantacio descompPlanta = dpo.getDescomposicioPlantacio();
						VarietatOliva varietat = descompPlanta.getVarietatOliva();
						if (varietat != null && varietat.getNom() != null) {
							if (nom != "" && !nom.equalsIgnoreCase(varietat.getNom())) {
								nom = "mezcla";
								break;
							} else {
								if (varietat.getExperimental().booleanValue()) {
									nom = varietat.getNomVarietat();
								} else {
									nom = varietat.getNom();
								}
							}
						}
					}					
				}
				
				if(nom.equalsIgnoreCase("mezcla")){
					break;
				}
			}
		}		
		return nom;
	}
	
	
}
