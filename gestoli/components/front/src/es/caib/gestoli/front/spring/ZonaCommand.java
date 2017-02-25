/**
 * ZonaCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Zona;


/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class ZonaCommand extends Zona {
	
	private Arxiu arxiuc;
	private byte[] imatge;
	private static final long serialVersionUID = 7614151055057623937L;
	private Long arxcodi;
	private String cambioEstadoActivo;
	

	Long idEstablecimiento;

	
	/**
     * Rellena los campos de este objeto con la información
     * del zona.
     * @param z Zona
     */
    public void fromZona(Zona z) {
    	setActiu(z.getActiu());
    	setDefecte(z.getDefecte());
    	setDefecteTrasllat(z.getDefecteTrasllat());
    	setDiposits(z.getDiposits());
    	setEstabliment(z.getEstabliment());
    	setFictici(z.getFictici());
    	setId(z.getId());
    	setImatgePlanol(z.getImatgePlanol());
    	setNom(z.getNom());
    	setObservacions(z.getObservacions());
    	setIdOriginal(z.getIdOriginal());
    }


	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}


	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}


	public Arxiu getArxiuc() {
		return arxiuc;
	}


	public void setArxiuc(Arxiu arxiuc) {
		this.arxiuc = arxiuc;
	}


	public byte[] getImatge() {
		return imatge;
	}


	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}


	public Long getArxcodi() {
		return arxcodi;
	}


	public void setArxcodi(Long arxcodi) {
		this.arxcodi = arxcodi;
	}

	public String getCambioEstadoActivo() {
		return cambioEstadoActivo;
	}

	public void setCambioEstadoActivo(String cambioEstadoActivo) {
		this.cambioEstadoActivo = cambioEstadoActivo;
	}

    

}
