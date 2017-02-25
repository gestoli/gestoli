/**
 * ProcesMoureDipositCommand.java
 *
 * Creada el 18 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.text.SimpleDateFormat;
import java.util.Date;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;


/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class ProcesMoureDipositCommand {
	
	Date data;
	Diposit[] dipositsOrigen;	
	Long establimentDesti;
	
	public ProcesMoureDipositCommand(){
		
	}

		

	public Diposit[] getDipositsOrigen() {
		return dipositsOrigen;
	}

	public void setDipositsOrigen(Diposit[] dipositsOrigen) {
		this.dipositsOrigen = dipositsOrigen;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getData());
	}



	public Long getEstablimentDesti() {
		return establimentDesti;
	}



	public void setEstablimentDesti(Long establimentDesti) {
		this.establimentDesti = establimentDesti;
	}

	
	

	
}