/**
 * DipositCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.util.Date;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;



/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 */
public class DipositCommand extends Diposit {
	
	private static final long serialVersionUID = 1465768940114210213L;

	Long idZona;
	Integer idMaterialDiposit;
	Date data;
	Integer idCategoriaOli;
	Integer idVarietatOli;
	Integer[] varietatsOlivaArray;
	Double litros;
	Double kilos;
	Long idPartidaOli;
	Long idEstabliment;
	Boolean esEnvasadora;
	
	
	/**
     * Rellena los campos de este objeto con la información
     * del diposit.
     * @param d Diposit
     */
    public void fromDiposit(Diposit d) {
    	setActiu(d.getActiu());
    	setCapacitat(d.getCapacitat());
    	setCodiAssignat(d.getCodiAssignat());
    	setFictici(d.getFictici());
    	setId(d.getId());
    	setObservacions(d.getObservacions());
    	setMaterialDiposit(d.getMaterialDiposit());
    	setZona(d.getZona());
    	setEstabliment(d.getEstabliment());
    	setVolumActual(d.getVolumActual());
    	setAcidesa(d.getAcidesa());
    	setPartidaOli(d.getPartidaOli());
    	setIdPartidaOli(d.getPartidaOli() == null ? null : d.getPartidaOli().getId());
    	setDeEnvasadora(d.getDeEnvasadora());
    }


	public Long getIdZona() {
		return idZona;
	}


	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}


	public Integer getIdMaterialDiposit() {
		return idMaterialDiposit;
	}


	public void setIdMaterialDiposit(Integer idMaterialDiposit) {
		this.idMaterialDiposit = idMaterialDiposit;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Integer getIdCategoriaOli() {
		return idCategoriaOli;
	}


	public void setIdCategoriaOli(Integer idCategoriaOli) {
		this.idCategoriaOli = idCategoriaOli;
	}


	public Integer getIdVarietatOli() {
		return idVarietatOli;
	}


	public void setIdVarietatOli(Integer idVarietatOli) {
		this.idVarietatOli = idVarietatOli;
	}


	public Integer[] getVarietatsOlivaArray() {
		return varietatsOlivaArray;
	}


	public void setVarietatsOlivaArray(Integer[] varietatsOlivaArray) {
		this.varietatsOlivaArray = varietatsOlivaArray;
	}


	public Double getLitros() {
		return litros;
	}


	public void setLitros(Double litros) {
		this.litros = litros;
	}


	public Double getKilos() {
		return kilos;
	}


	public void setKilos(Double kilos) {
		this.kilos = kilos;
	}

	public Long getIdPartidaOli() {
		return idPartidaOli;
	}

	public void setIdPartidaOli(Long idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}
	
	public Long getIdEstabliment() {
		return idEstabliment;
	}

	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Boolean getEsEnvasadora() {
		return esEnvasadora;
	}

	public void setEsEnvasadora(Boolean esEnvasadora) {
		this.esEnvasadora = esEnvasadora;
	}
}
