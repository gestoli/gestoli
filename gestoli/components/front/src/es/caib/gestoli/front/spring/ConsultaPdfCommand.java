/**
 * ConsultaPdfCommand.java
 */
package es.caib.gestoli.front.spring;



/**
 * Objeto que representa el formulario de consultas
 * @author Oriol Barnes (obarnes@at4.net)
 */
public class ConsultaPdfCommand {

	private Integer tipus;
	private Boolean prefactura;
	
	
	/**
	 * Metodo 'getPrefactura'
	 * @return el prefactura
	 */
	public Boolean getPrefactura() {
		return prefactura;
	}
	/**
	 * Metodo 'setPrefactura'
	 * @param prefactura el prefactura a modificar
	 */
	public void setPrefactura(Boolean prefactura) {
		this.prefactura = prefactura;
	}
	/**
	 * Metodo 'getTipus'
	 * @return el tipus
	 */
	public Integer getTipus() {
		return tipus;
	}
	/**
	 * Metodo 'setTipus'
	 * @param tipus el tipus a modificar
	 */
	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}



}
