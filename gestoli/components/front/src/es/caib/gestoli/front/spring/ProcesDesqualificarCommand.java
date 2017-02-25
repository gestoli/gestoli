/**
 * ProcesDesqualificarCommand.java
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
public class ProcesDesqualificarCommand  {
	
	Date data;
	Diposit diposit;
	String observaciones;
	
	Establiment establiment;
	
	public ProcesDesqualificarCommand(){
		
	}

	
	

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	

	
	
	public String getObservaciones() {
		return observaciones;
	}




	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}




	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}




	public Diposit getDiposit() {
		return diposit;
	}




	public void setDiposit(Diposit diposit) {
		this.diposit = diposit;
	}

	
}
