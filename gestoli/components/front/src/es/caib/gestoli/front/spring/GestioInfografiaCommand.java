package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.GestioInfografia;

public class GestioInfografiaCommand extends GestioInfografia {
	private static final long serialVersionUID = -3454772861320876579L;
	
	private Arxiu  arxiuArxiu; //arxiuImatge;
	private byte[] contingutArxiu; //imatgeImatge;
	private Long   idArxiu; //idImatge;
	
	private Arxiu  arxiuImatge; //arxiuImatge;
	private byte[] contingutImatge; //imatgeImatge;
	private Long   idImatge; //idImatge;
	
	private String errorImatges;
	
	/**
	 * Rellena los campos de este objeto con la información
	 * de la GestioInfografia
	 * 
	 * @param m GestioInfografia
	 */
	public void fromGestioInfografia(GestioInfografia m) {
		setId(m.getId());
		setDataAlta(m.getDataAlta());
		setNom(m.getNom());
		setDescripcio(m.getDescripcio());
		setNomEs(m.getNomEs());
		setDescripcioEs(m.getDescripcioEs());
		setIdArxiu(m.getArxiu());
		setIdImatge(m.getImatge());
	}

	public Arxiu getArxiuArxiu() {
		return arxiuArxiu;
	}

	public void setArxiuArxiu(Arxiu arxiuArxiu) {
		this.arxiuArxiu = arxiuArxiu;
	}

	public byte[] getContingutArxiu() {
		return contingutArxiu;
	}

	public void setContingutArxiu(byte[] contingutArxiu) {
		this.contingutArxiu = contingutArxiu;
	}

	public Long getIdArxiu() {
		return idArxiu;
	}

	public void setIdArxiu(Long idArxiu) {
		this.idArxiu = idArxiu;
	}

	public Arxiu getArxiuImatge() {
		return arxiuImatge;
	}

	public void setArxiuImatge(Arxiu arxiuImatge) {
		this.arxiuImatge = arxiuImatge;
	}

	public byte[] getContingutImatge() {
		return contingutImatge;
	}

	public void setContingutImatge(byte[] contingutImatge) {
		this.contingutImatge = contingutImatge;
	}

	public Long getIdImatge() {
		return idImatge;
	}

	public void setIdImatge(Long idImatge) {
		this.idImatge = idImatge;
	}

	public String getErrorImatges() {
		return errorImatges;
	}

	public void setErrorImatges(String errorImatges) {
		this.errorImatges = errorImatges;
	}

}
