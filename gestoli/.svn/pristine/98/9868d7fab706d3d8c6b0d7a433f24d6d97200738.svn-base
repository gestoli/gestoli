/**
 * MarcaCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.List;

import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Marca;


/**
 * Objeto que representa el formulario de mantenimento
 * de marcas de aceite.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class MarcaCommand extends Marca {
	private static final long serialVersionUID = -3454772861320876579L;
	private List etiquetatgesList;
	private Long[] establimentsList;
			
	private String observacionsEtiquetatge;
	private Long tipusEnvasIdEtiquetatge;
	private Boolean actiuEtiquetatge;
	
	private Arxiu arxiucImatgeFrontal;
	private byte[] imatgeImatgeFrontal;
	private Long imatgeFrontal;
	
	private Arxiu arxiucImatgePosterior;
	private byte[] imatgeImatgePosterior;
	private Long imatgePosterior;	
	private Integer etiPosition;
	private String cambioEstadoActivo;
	
	private String errorImagenes;
	

	public String getCambioEstadoActivo() {
		return cambioEstadoActivo;
	}

	public void setCambioEstadoActivo(String cambioEstadoActivo) {
		this.cambioEstadoActivo = cambioEstadoActivo;
	}

	/**
	 * Rellena los campos de este objeto con la información
	 * de la Marca
	 * 
	 * @param m Marca
	 */
	public void fromMarca(Marca m) {
		setId(m.getId());
		setDenominacioOrigen(m.getDenominacioOrigen());
		setEstabliments(m.getEstabliments());
		setEtiquetatges(m.getEtiquetatges());
		setNom(m.getNom());
		setActiu(m.getActiu());
		setOlivaTaula(m.getOlivaTaula());
		setObservacions(m.getObservacions());
	}

	/**
	 * Para imprimir bien el registro
	 */
	public String toString() {
		return "Marca(" + getNom() + ")";
	}

	
	public String getObservacionsEtiquetatge() {
		return observacionsEtiquetatge;
	}

	public void setObservacionsEtiquetatge(String observacionsEtiquetatge) {
		this.observacionsEtiquetatge = observacionsEtiquetatge;
	}



	/**
	 * Metodo 'getEstablimentsList'
	 * @return el establimentsList
	 */
	public Long[] getEstablimentsList() {
		return establimentsList;
	}

	/**
	 * Metodo 'setEstablimentsList'
	 * @param establimentsList el establimentsList a modificar
	 */
	public void setEstablimentsList(Long[] establimentsList) {
		this.establimentsList = establimentsList;
	}

	
	public List getEtiquetatgesList() {
		return etiquetatgesList;
	}

	public void setEtiquetatgesList(List etiquetatgesList) {
		this.etiquetatgesList = etiquetatgesList;
	}

	public Long getTipusEnvasIdEtiquetatge() {
		return tipusEnvasIdEtiquetatge;
	}

	public void setTipusEnvasIdEtiquetatge(Long tipusEnvasIdEtiquetatge) {
		this.tipusEnvasIdEtiquetatge = tipusEnvasIdEtiquetatge;
	}

	
	public Arxiu getArxiucImatgeFrontal() {
		return arxiucImatgeFrontal;
	}

	public void setArxiucImatgeFrontal(Arxiu arxiucImatgeFrontal) {
		this.arxiucImatgeFrontal = arxiucImatgeFrontal;
	}

	public byte[] getImatgeImatgeFrontal() {
		return imatgeImatgeFrontal;
	}

	public void setImatgeImatgeFrontal(byte[] imatgeImatgeFrontal) {
		this.imatgeImatgeFrontal = imatgeImatgeFrontal;
	}

	public Long getImatgeFrontal() {
		return imatgeFrontal;
	}

	public void setImatgeFrontal(Long imatgeFrontal) {
		this.imatgeFrontal = imatgeFrontal;
	}

	public Integer getEtiPosition() {
		return etiPosition;
	}

	public void setEtiPosition(Integer etiPosition) {
		this.etiPosition = etiPosition;
	}

	public Boolean getActiuEtiquetatge() {
		return actiuEtiquetatge;
	}

	public void setActiuEtiquetatge(Boolean actiuEtiquetatge) {
		this.actiuEtiquetatge = actiuEtiquetatge;
	}

	public Arxiu getArxiucImatgePosterior() {
		return arxiucImatgePosterior;
	}

	public void setArxiucImatgePosterior(Arxiu arxiucImatgePosterior) {
		this.arxiucImatgePosterior = arxiucImatgePosterior;
	}

	public byte[] getImatgeImatgePosterior() {
		return imatgeImatgePosterior;
	}

	public void setImatgeImatgePosterior(byte[] imatgeImatgePosterior) {
		this.imatgeImatgePosterior = imatgeImatgePosterior;
	}

	public Long getImatgePosterior() {
		return imatgePosterior;
	}

	public void setImatgePosterior(Long imatgePosterior) {
		this.imatgePosterior = imatgePosterior;
	}

	public String getErrorImagenes() {
		return errorImagenes;
	}

	public void setErrorImagenes(String errorImagenes) {
		this.errorImagenes = errorImagenes;
	}

	
	
}
