package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class DescomposicioPartidaOliva implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private DescomposicioPlantacio descomposicioPlantacio;

	private PartidaOliva partidaOliva;

	private Double kilos;

	public DescomposicioPartidaOliva() {
	}

	public DescomposicioPartidaOliva(
			DescomposicioPlantacio descomposicioPlantacio,
			PartidaOliva partidaOliva, Double kilos) {
		this.descomposicioPlantacio = descomposicioPlantacio;
		this.partidaOliva = partidaOliva;
		this.kilos = kilos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DescomposicioPlantacio getDescomposicioPlantacio() {
		return this.descomposicioPlantacio;
	}

	public void setDescomposicioPlantacio(
			DescomposicioPlantacio descomposicioPlantacio) {
		this.descomposicioPlantacio = descomposicioPlantacio;
	}

	public PartidaOliva getPartidaOliva() {
		return this.partidaOliva;
	}

	public void setPartidaOliva(PartidaOliva partidaOliva) {
		this.partidaOliva = partidaOliva;
	}

	public Double getKilos() {
		return this.kilos;
	}

	public void setKilos(Double kilos) {
		this.kilos = kilos;
	}

}
