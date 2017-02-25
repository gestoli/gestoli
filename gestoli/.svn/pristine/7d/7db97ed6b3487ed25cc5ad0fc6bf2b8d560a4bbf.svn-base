/**
 * DipositCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.AnaliticaParametre;



/**
 * Objeto que representa el formulario de mantenimento
 * 
 * 
 */
public class AnaliticaParametreCommand extends AnaliticaParametre {
	
	private static final long serialVersionUID = 1465768940114210213L;

	private Integer idVarietatOli;
	private Long idAnaliticaParametreTipus;
	
	/**
     * Rellena los campos de este objeto con la información
     * 
     * @param d Diposit
     */
    public void fromAnalitica(AnaliticaParametre a) {
    	setAnaliticaParametreTipus(a.getAnaliticaParametreTipus());
    	setId(a.getId());
    	setOperador(a.getOperador());
    	setValor(a.getValor());
    	setVarietatOli(a.getVarietatOli());
    	setObligatori(a.getObligatori());
    	
    	setIdVarietatOli(a.getVarietatOli().getId());
    	setIdAnaliticaParametreTipus(a.getAnaliticaParametreTipus().getId());
    }

	public Integer getIdVarietatOli() {
		return idVarietatOli;
	}

	public void setIdVarietatOli(Integer idVarietatOli) {
		this.idVarietatOli = idVarietatOli;
	}

	public Long getIdAnaliticaParametreTipus() {
		return idAnaliticaParametreTipus;
	}

	public void setIdAnaliticaParametreTipus(Long idAnaliticaParametreTipus) {
		this.idAnaliticaParametreTipus = idAnaliticaParametreTipus;
	}
    
    


}
