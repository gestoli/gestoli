/**
 * TipusEnvasCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.TipusEnvas;


/**
 * Objeto que representa el formulario de mantenimento
 * de tipus d'envasos.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class TipusEnvasCommand extends TipusEnvas {
	private static final long serialVersionUID = 435920557951264105L;
	private Integer materialTipusEnvasId;
	private Integer colorId;
//	private Long[] etiquetatgesList;


	/**
	 * Rellena los campos de este objeto con la información
	 * del Tipo de envase.
	 * 
	 * @param te TipusEnvas
	 */
	public void fromTipusEnvas(TipusEnvas te) {
		setId(te.getId());
		setMaterialTipusEnvas(te.getMaterialTipusEnvas());
		setColor(te.getColor());
		setActiu(te.getActiu());
		setVolum(te.getVolum());
//		setEtiquetatges(te.getEtiquetatges());
		setObservacions(te.getObservacions());
	}

	/**
	 * Para imprimir bien el registro
	 */
	public String toString() {
		return "TipusEnvas(" + getId() + ")";
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getMaterialTipusEnvasId() {
		return materialTipusEnvasId;
	}

	public void setMaterialTipusEnvasId(Integer materialTipusEnvasId) {
		this.materialTipusEnvasId = materialTipusEnvasId;
	}

//	public Long[] getEtiquetatgesList() {
//		return etiquetatgesList;
//	}
//
//	public void setEtiquetatgesList(Long[] etiquetatgesList) {
//		this.etiquetatgesList = etiquetatgesList;
//	}


}
