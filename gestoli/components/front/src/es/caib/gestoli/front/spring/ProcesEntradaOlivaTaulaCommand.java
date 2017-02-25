/**
 * ProcesEntradaOlivaCommand.java
 */
package es.caib.gestoli.front.spring; 

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;



/**
 * Objecte que representa un formulari web d'entrada
 * de oliva.
 * 
 * @author cperez <cperez@at4.net>
 */
public class ProcesEntradaOlivaTaulaCommand {

	private Date dataExecucio;
	private String hora;	
	private String codi;
	private Long trazaId;
	private Long fincaId;
	private Long plantacioId;
	private VarietatOlivaCommand[] variedades;
	private Long zonaId;
	private Long olivicultorId;
	private Long entrada;
	private Boolean envasPetit;
	private Boolean envasRigid;
	private Boolean envasVentilat;
	private Integer tipusOlivaTaula;
	private Float quantitat;
	private HashMap descomposicionPlantacioVariedadHash;
	private HashMap kgsAcumulatsMap;
	private Collection descomposiciones;
	private String idsPlantacions;
	private String idsPlantacionsIVarietats;
	private Boolean esEcologic;
	
	public Long getZonaId() {
		return zonaId;
	}
	public void setZonaId(Long zonaId) {
		this.zonaId = zonaId;
	}
	
	public Date getDataExecucio() {
		return dataExecucio;
	}
	public void setDataExecucio(Date dataExecucio) {
		this.dataExecucio = dataExecucio;
	}
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	
	
	public Long getFincaId() {
		return fincaId;
	}
	public void setFincaId(Long fincaId) {
		this.fincaId = fincaId;
	}
	
	public Float getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(Float quantitat) {
		this.quantitat = quantitat;
	}
	
	
	public Long getPlantacioId() {
		return plantacioId;
	}
	public void setPlantacioId(Long plantacioId) {
		this.plantacioId = plantacioId;
	}
	
	
	
	public VarietatOlivaCommand[] getVariedades() {
		return variedades;
	}
	public void setVariedades(VarietatOlivaCommand[] variedades) {
		this.variedades = variedades;
	}
	public Long getOlivicultorId() {
		return olivicultorId;
	}
	public void setOlivicultorId(Long olivicultorId) {
		this.olivicultorId = olivicultorId;
	}
	public Long getEntrada() {
		return entrada;
	}
	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	public Boolean getEnvasPetit() {
		return envasPetit;
	}
	public void setEnvasPetit(Boolean envasPetit) {
		this.envasPetit = envasPetit;
	}
	public Boolean getEnvasRigid() {
		return envasRigid;
	}
	public void setEnvasRigid(Boolean envasRigid) {
		this.envasRigid = envasRigid;
	}
	public Boolean getEnvasVentilat() {
		return envasVentilat;
	}
	public void setEnvasVentilat(Boolean envasVentilat) {
		this.envasVentilat = envasVentilat;
	}
	public Integer getTipusOlivaTaula() {
		return tipusOlivaTaula;
	}
	public void setTipusOlivaTaula(Integer tipusOlivaTaula) {
		this.tipusOlivaTaula = tipusOlivaTaula;
	}
	public Collection getDescomposiciones() {
		return descomposiciones;
	}
	public void setDescomposiciones(Collection descomposiciones) {
		this.descomposiciones = descomposiciones;
	}
	public HashMap getDescomposicionPlantacioVariedadHash() {
		return descomposicionPlantacioVariedadHash;
	}
	public void setDescomposicionPlantacioVariedadHash(
			HashMap descomposicionPlantacioVariedadHash) {
		this.descomposicionPlantacioVariedadHash = descomposicionPlantacioVariedadHash;
	}
	public HashMap getKgsAcumulatsMap() {
		return kgsAcumulatsMap;
	}
	public void setKgsAcumulatsMap(HashMap kgsAcumulatsMap) {
		this.kgsAcumulatsMap = kgsAcumulatsMap;
	}
	public String getIdsPlantacions() {
		return idsPlantacions;
	}
	public void setIdsPlantacions(String idsPlantacions) {
		this.idsPlantacions = idsPlantacions;
	}
	public Long getTrazaId() {
		return trazaId;
	}
	public void setTrazaId(Long trazaId) {
		this.trazaId = trazaId;
	}
	public String getIdsPlantacionsIVarietats() {
		return idsPlantacionsIVarietats;
	}
	public void setIdsPlantacionsIVarietats(String idsPlantacionsIVarietats) {
		this.idsPlantacionsIVarietats = idsPlantacionsIVarietats;
	}
	public Boolean getEsEcologic() {
		return esEcologic;
	}
	public void setEsEcologic(Boolean esEcologic) {
		this.esEcologic = esEcologic;
	}
	
	
}
