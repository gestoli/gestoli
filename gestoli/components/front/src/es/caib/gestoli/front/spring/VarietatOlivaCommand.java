/**
 * VarietatOlivaCommand.java
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.VarietatOliva;



/**
 * Objeto que representa el formulario de mantenimento
 * de usuarios.
 * 
 */
public class VarietatOlivaCommand extends VarietatOliva {
	
	private static final long serialVersionUID = -8758752998801321623L;
	
	private String idOlivicultor;
	private Double produccion;
	private Long idPlantacio;

	/**
     * Rellena los campos de este objeto con la información
     * del finca.
     * @param f Finca
     */
    public void fromVarietatOliva(VarietatOliva varOliv) {
    	setId(varOliv.getId());
    	setNom(varOliv.getNom());
    	setNomVarietat(varOliv.getNomVarietat());
    	setObservacions(varOliv.getObservacions());
    	setAutoritzada(varOliv.getAutoritzada());
    	setDescomposicioPlantacios(varOliv.getDescomposicioPlantacios());
    }

	public String getIdOlivicultor() {
		return idOlivicultor;
	}

	public void setIdOlivicultor(String idOlivicultor) {
		this.idOlivicultor = idOlivicultor;
	}

	public Double getProduccion() {
		return produccion;
	}

	public void setProduccion(Double produccion) {
		this.produccion = produccion;
	}

	public Long getIdPlantacio() {
		return idPlantacio;
	}

	public void setIdPlantacio(Long idPlantacio) {
		this.idPlantacio = idPlantacio;
	}
}
