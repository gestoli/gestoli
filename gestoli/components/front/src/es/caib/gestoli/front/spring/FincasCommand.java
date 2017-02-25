/**
 * FincasCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Finca;

/**
 * Objeto que representa el formulario de mantenimento de usuarios.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class FincasCommand extends Finca {

	private static final long serialVersionUID = -8758752998801321623L;

	private String idOlivicultor;
	private String cambioEstadoActivo;
	private Long fincaId;
	private Long fincaBaixaId;

	/**
	 * Rellena los campos de este objeto con la información del finca.
	 * 
	 * @param f Finca
	 */
	public void fromFinca(Finca f) {
		setActiu(f.getActiu());
		setId(f.getId());
		setNom(f.getNom());
		setObservacions(f.getObservacions());
		setOlivicultor(f.getOlivicultor());
		setPlantacios(f.getPlantacios());
	}

	public String getIdOlivicultor() {
		return idOlivicultor;
	}
	public void setIdOlivicultor(String idOlivicultor) {
		this.idOlivicultor = idOlivicultor;
	}

	public String getCambioEstadoActivo() {
		return cambioEstadoActivo;
	}
	public void setCambioEstadoActivo(String cambioEstadoActivo) {
		this.cambioEstadoActivo = cambioEstadoActivo;
	}

	public Long getFincaId() {
		return fincaId;
	}
	public void setFincaId(Long fincaId) {
		this.fincaId = fincaId;
	}

	public Long getFincaBaixaId() {
		return fincaBaixaId;
	}
	public void setFincaBaixaId(Long fincaBaixaId) {
		this.fincaBaixaId = fincaBaixaId;
	}

}
