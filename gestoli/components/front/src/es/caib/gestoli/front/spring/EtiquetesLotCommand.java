package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.EtiquetesLot;

public class EtiquetesLotCommand extends EtiquetesLot {

	public EtiquetesLotCommand(){
		super();
	}
	
	public EtiquetesLotCommand(String etiquetaLletra, Integer etiquetaInici,
			Integer etiquetaFi, Double capacitat, 
			Boolean utilitzat, Boolean retirat, Boolean olivaTaula,
			Establiment establiment, Boolean esEcologic) {
		super(etiquetaLletra, etiquetaInici, etiquetaFi, capacitat, utilitzat, retirat, olivaTaula,
				establiment, esEcologic);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;

	private Double quantitat;
	private Long idEstabliment;
	private Boolean teFills;


	public Double getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(Double quantitat) {
		this.quantitat = quantitat;
	}
	public Long getIdEstabliment() {
		return idEstabliment;
	}
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}

	public Boolean getTeFills() {
		return teFills;
	}

	public void setTeFills(Boolean teFills) {
		this.teFills = teFills;
	}
	
}
