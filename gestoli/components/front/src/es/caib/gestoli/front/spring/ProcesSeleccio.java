
package es.caib.gestoli.front.spring;

/**
 * @author cperez
 *
 */
public class ProcesSeleccio {

	private int tipusProces;
	private String nomProces;
	private String accio;
	private int pas;
	private boolean mostrarPartida;
	private boolean mostrarDiposit;
	private boolean mostrarLote;	
	private boolean olivaTaula;
	private boolean mostrarBotes;
	private boolean mostrarNomesDO;

	private Long[] seleccio;

	
	public ProcesSeleccio(
			int tipusProces,
			String nomProces,
			String accio,
			int pas,
			boolean mostrarDiposit,
			boolean mostrarPartida,
			boolean mostrarLote) {
		this.tipusProces = tipusProces;
		this.nomProces = nomProces;
		this.accio = accio;
		this.pas = pas;
		this.mostrarDiposit = mostrarDiposit;
		this.mostrarPartida = mostrarPartida;
		this.mostrarLote = mostrarLote;
		this.olivaTaula = false;
		this.mostrarBotes = false;

	}
	
	public ProcesSeleccio(
			int tipusProces,
			String nomProces,
			String accio,
			int pas,
			boolean mostrarDiposit,
			boolean mostrarPartida,
			boolean mostrarLote,
			boolean olivaTaula,
			boolean mostrarBotes,
			boolean mostrarNomesDO) {
		this.tipusProces = tipusProces;
		this.nomProces = nomProces;
		this.accio = accio;
		this.pas = pas;
		this.mostrarDiposit = mostrarDiposit;
		this.mostrarPartida = mostrarPartida;
		this.mostrarLote = mostrarLote;
		this.olivaTaula = olivaTaula;
		this.mostrarBotes = mostrarBotes;
		this.mostrarNomesDO = mostrarNomesDO;

	}

	public String getAccio() {
		return accio;
	}

	public void setAccio(String accio) {
		this.accio = accio;
	}

	public boolean isMostrarPartida() {
		return mostrarPartida;
	}

	public void setMostrarPartida(boolean mostrarPartida) {
		this.mostrarPartida = mostrarPartida;
	}
	
	public boolean isMostrarDiposit() {
		return mostrarDiposit;
	}

	public void setMostrarDiposit(boolean mostrarDiposit) {
		this.mostrarDiposit = mostrarDiposit;
	}
	
	public boolean isMostrarLote() {
		return mostrarLote;
	}

	public void setMostrarLote(boolean mostrarLote) {
		this.mostrarLote = mostrarLote;
	}

	public boolean isOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}

	public boolean isMostrarBotes() {
		return mostrarBotes;
	}

	public void setMostrarBotes(boolean mostrarBotes) {
		this.mostrarBotes = mostrarBotes;
	}

	public boolean isMostrarNomesDO() {
		return mostrarNomesDO;
	}

	public void setMostrarNomesDO(boolean mostrarNomesDO) {
		this.mostrarNomesDO = mostrarNomesDO;
	}

	public String getNomProces() {
		return nomProces;
	}

	public void setNomProces(String nomProces) {
		this.nomProces = nomProces;
	}

	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}

	public Long[] getSeleccio() {
		return seleccio;
	}

	public void setSeleccio(Long[] seleccio) {
		this.seleccio = seleccio;
	}

	public int getTipusProces() {
		return tipusProces;
	}

	public void setTipusProces(int tipusProces) {
		this.tipusProces = tipusProces;
	}

}
