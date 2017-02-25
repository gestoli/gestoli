package es.caib.gestoli.logic.model;

// Generated 10-dic-2009 16:00:09 by Hibernate Tools 3.2.0.b9

import java.util.HashSet;
import java.util.Set;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Traza implements java.io.Serializable {

	private Long id;

	private Integer tipus;

	private Set trazasForTtrCodtrafill = new HashSet(0);

	private Set sortidesDiposits = new HashSet(0);

	private Set entradesDiposits = new HashSet(0);

	private Set trasllatsDiposits = new HashSet(0);

	private Set partidesOlives = new HashSet(0);

	private Set sortidesLots = new HashSet(0);

	private Set trazasForTtrCodtrapare = new HashSet(0);

	private Set elaboracions = new HashSet(0);

	private Set entradesLots = new HashSet(0);
	
	private Set analitiques = new HashSet(0);

	public Traza() {
	}

	public Traza(Integer tipus) {
		this.tipus = tipus;
	}

	public Traza(Integer tipus, Set trazasForTtrCodtrafill,
			Set sortidesDiposits, Set entradesDiposits, Set trasllatsDiposits,
			Set partidesOlives, Set sortidesLots, Set trazasForTtrCodtrapare,
			Set elaboracions, Set entradesLots, Set analitiques) {
		this.tipus = tipus;
		this.trazasForTtrCodtrafill = trazasForTtrCodtrafill;
		this.sortidesDiposits = sortidesDiposits;
		this.entradesDiposits = entradesDiposits;
		this.trasllatsDiposits = trasllatsDiposits;
		this.partidesOlives = partidesOlives;
		this.sortidesLots = sortidesLots;
		this.trazasForTtrCodtrapare = trazasForTtrCodtrapare;
		this.elaboracions = elaboracions;
		this.entradesLots = entradesLots;
		this.analitiques = analitiques;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipus() {
		return this.tipus;
	}

	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}

	public Set getTrazasForTtrCodtrafill() {
		return this.trazasForTtrCodtrafill;
	}

	public void setTrazasForTtrCodtrafill(Set trazasForTtrCodtrafill) {
		this.trazasForTtrCodtrafill = trazasForTtrCodtrafill;
	}

	public Set getSortidesDiposits() {
		return this.sortidesDiposits;
	}

	public void setSortidesDiposits(Set sortidesDiposits) {
		this.sortidesDiposits = sortidesDiposits;
	}

	public Set getEntradesDiposits() {
		return this.entradesDiposits;
	}

	public void setEntradesDiposits(Set entradesDiposits) {
		this.entradesDiposits = entradesDiposits;
	}

	public Set getTrasllatsDiposits() {
		return this.trasllatsDiposits;
	}

	public void setTrasllatsDiposits(Set trasllatsDiposits) {
		this.trasllatsDiposits = trasllatsDiposits;
	}

	public Set getPartidesOlives() {
		return this.partidesOlives;
	}

	public void setPartidesOlives(Set partidesOlives) {
		this.partidesOlives = partidesOlives;
	}

	public Set getSortidesLots() {
		return this.sortidesLots;
	}

	public void setSortidesLots(Set sortidesLots) {
		this.sortidesLots = sortidesLots;
	}

	public Set getTrazasForTtrCodtrapare() {
		return this.trazasForTtrCodtrapare;
	}

	public void setTrazasForTtrCodtrapare(Set trazasForTtrCodtrapare) {
		this.trazasForTtrCodtrapare = trazasForTtrCodtrapare;
	}

	public Set getElaboracions() {
		return this.elaboracions;
	}

	public void setElaboracions(Set elaboracions) {
		this.elaboracions = elaboracions;
	}

	public Set getEntradesLots() {
		return this.entradesLots;
	}

	public void setEntradesLots(Set entradesLots) {
		this.entradesLots = entradesLots;
	}

	public Set getAnalitiques() {
		return this.analitiques;
	}

	public void setAnalitiques(Set analitiques) {
		this.analitiques = analitiques;
	}
}
