/**
 * ProcesElaboracioOliCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.text.SimpleDateFormat;
import java.util.Date;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;


/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class ProcesTrasbalsCommand {
	
	Date data;
	Double acidesa;
	Diposit[] dipositsOrigen;
	Diposit dipositDesti;
	Double[] litros;
	Double[] kilos;
	Double litrosAfegits;
	Double kilosAfegits;
	Establiment establiment;
	Long idPartidaOli;
	String nomCategoriaOli;
	
	public ProcesTrasbalsCommand(){
		
	}

	public Double getAcidesa() {
		return acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public Double getKilosAfegits() {
		return kilosAfegits;
	}

	public void setKilosAfegits(Double kilosAfegits) {
		this.kilosAfegits = kilosAfegits;
	}

	public Double[] getLitros() {
		return litros;
	}

	public void setLitros(Double[] litros) {
		this.litros = litros;
	}

	public Double getLitrosAfegits() {
		return litrosAfegits;
	}

	public void setLitrosAfegits(Double litrosAfegits) {
		this.litrosAfegits = litrosAfegits;
	}

	public Double[] getKilos() {
		return kilos;
	}

	public Diposit[] getDipositsOrigen() {
		return dipositsOrigen;
	}

	public void setDipositsOrigen(Diposit[] dipositsOrigen) {
		this.dipositsOrigen = dipositsOrigen;
	}

	public Diposit getDipositDesti() {
		return dipositDesti;
	}

	public void setDipositDesti(Diposit dipositDesti) {
		this.dipositDesti = dipositDesti;
	}

	public void setKilos(Double[] kilos) {
		this.kilos = kilos;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdPartidaOli() {
		return idPartidaOli;
	}

	public void setIdPartidaOli(Long idPartidaOli) {
		this.idPartidaOli = idPartidaOli;
	}

	public String getNomCategoriaOli() {
		return nomCategoriaOli;
	}

	public void setNomCategoriaOli(String nomCategoriaOli) {
		this.nomCategoriaOli = nomCategoriaOli;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getData());
	}
	

	
}
