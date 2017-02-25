package es.caib.gestoli.logic.model;

public class PartidaElaboracio implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	Long id;
	ElaboracioOliva elaboracio;
	PartidaOliva partida;
	Double quantitat;
	Double quantitatCriba;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ElaboracioOliva getElaboracio() {
		return elaboracio;
	}
	public void setElaboracio(ElaboracioOliva elaboracio) {
		this.elaboracio = elaboracio;
	}
	public PartidaOliva getPartida() {
		return partida;
	}
	public void setPartida(PartidaOliva partida) {
		this.partida = partida;
	}
	public Double getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(Double quantitat) {
		this.quantitat = quantitat;
	}
	public Double getQuantitatCriba() {
		return quantitatCriba;
	}
	public void setQuantitatCriba(Double quantitatCriba) {
		this.quantitatCriba = quantitatCriba;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
