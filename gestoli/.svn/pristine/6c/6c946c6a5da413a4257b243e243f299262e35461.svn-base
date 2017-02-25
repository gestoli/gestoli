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
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.VarietatOliva;


/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class ProcesElaboracioOliCommand extends Elaboracio {
	
	private Integer idCategoriaOli;
	private PartidaOliva[] partides;
	private Diposit[] diposits;
	private Double[] litros;
	private Double[] kilos;
	private Double totalLitrosOli;
	private Double totalKilosOli;
	private Establiment establiment;
	private String errorObservaciones;
	private Long idPartidaOli;
	private String nomCategoriaOli;
	private Double rendiment;

	private Double[] litrosRetirats;
	private Double totalLitresRetirats;
	private Double[] kilosRetirats;
	private Long[] idOlivicultors;
	private String[] nomOlivicultors;
	
	public String getErrorObservaciones() {
		return errorObservaciones;
	}

	public void setErrorObservaciones(String errorObservaciones) {
		this.errorObservaciones = errorObservaciones;
	}

	public ProcesElaboracioOliCommand(){
		
	}

	public Double[] getLitros() {
		return litros;
	}

	public void setLitros(Double[] litros) {
		this.litros = litros;
	}

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

	public Double getTotalLitrosOli() {
		return totalLitrosOli;
	}

	public void setTotalLitrosOli(Double totalLitrosOli) {
		this.totalLitrosOli = totalLitrosOli;
	}

	public Double getTotalKilosOli() {
		return totalKilosOli;
	}

	public void setTotalKilosOli(Double totalKilosOli) {
		this.totalKilosOli = totalKilosOli;
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

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Long getIdPartidaOli() {
		return idPartidaOli;
	}

	public void setIdPartidaOli(Long idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}

	
	public Double getRendiment() {
		return rendiment;
	}

	public void setRendiment(Double rendiment) {
		this.rendiment = rendiment;
	}


	public String getNomCategoriaOli() {
		return nomCategoriaOli;
	}

	public void setNomCategoriaOli(String nomCategoriaOli) {
		this.nomCategoriaOli = nomCategoriaOli;
	}

	public Double[] getLitrosRetirats() {
		return litrosRetirats;
	}

	public void setLitrosRetirats(Double[] litrosRetirats) {
		this.litrosRetirats = litrosRetirats;
	}

	public Double[] getKilosRetirats() {
		return kilosRetirats;
	}

	public void setKilosRetirats(Double[] kilosRetirats) {
		this.kilosRetirats = kilosRetirats;
	}

	public Long[] getIdOlivicultors() {
		return idOlivicultors;
	}

	public void setIdOlivicultors(Long[] idOlivicultors) {
		this.idOlivicultors = idOlivicultors;
	}

	public String[] getNomOlivicultors() {
		return nomOlivicultors;
	}

	public void setNomOlivicultors(String[] nomOlivicultors) {
		this.nomOlivicultors = nomOlivicultors;
	}

	public Double getTotalLitresRetirats() {
		return totalLitresRetirats;
	}

	public void setTotalLitresRetirats(Double totalLitresRetirats) {
		this.totalLitresRetirats = totalLitresRetirats;
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
		Set partidas = this.getPartidaOlivas();
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives().iterator();
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
		Set partidas = this.getPartidaOlivas();
		for (Iterator it = partidas.iterator(); it.hasNext();) {
			PartidaOliva partida = (PartidaOliva)it.next();
			if (partida.getDescomposicioPartidesOlives() != null) {
				Iterator desc = partida.getDescomposicioPartidesOlives().iterator();
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
		Set partidas = this.getPartidaOlivas();
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
	
	public String getDipositsDestiQuilos() {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		String valor = "";
		if (this.getDiposits() != null) {
			for(int i=0;i<this.getDiposits().length;i++){
				Diposit dip = (Diposit) this.getDiposits()[i];
				Double cantidad = dip.getVolumActual();// SON LITROS
				//PASAMOS A KG
				if(cantidad != null){
					cantidad = Double.valueOf(String.valueOf((Double.valueOf("0.916").doubleValue() * cantidad.doubleValue())));
					if (valor != null && !"".equals(valor))
						valor += "<br/>";
					valor += dip.getCodiAssignat()
							+ " ("
							+ numberDecimalFormat.format(cantidad.doubleValue()) + " kgs.)";
				}
								
			}
		}
		return valor;
	}
	public String getDipositsDestiLitros() {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		String valor = "";
		if (this.getDiposits() != null) {
			for(int i=0;i<this.getDiposits().length;i++){
				Diposit dip = (Diposit) this.getDiposits()[i];
				if (valor != null && !"".equals(valor))
					valor += "<br/>";
				if(dip.getVolumActual()!= null){
					valor += dip.getCodiAssignat()
					+ " ("
					+ numberDecimalFormat.format(dip.getVolumActual().doubleValue()) + " l.)";	
				}
							
			}
		}
		return valor;
	}
	
	public Double rendiment(){
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
	
	public String getOlivicultors(){
		String olivicultors = "";
		if (nomOlivicultors != null && nomOlivicultors.length > 0) {
			for (String olivicultor :  nomOlivicultors){
				olivicultors += olivicultor + " - ";
			}
			return olivicultors.substring(0, olivicultors.length() - 4);
		} else {
			return olivicultors;
		}
	}
}
