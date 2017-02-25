package es.caib.gestoli.logic.model;

// Generated 02-dic-2009 9:12:42 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class Trasllat implements java.io.Serializable {

	private Long id;

	private Establiment establimentByTdiCodede;

	private Traza traza;

	private Establiment establimentByTdiCodeor;

	private Date data;

	private Boolean acceptatTrasllat;

	private Boolean retornatEstablimentOrigen;

	private Boolean traslladant;

	private Boolean valid;
	
	private Boolean esDiposit;

	private Date dataAlta;

	private Set diposits = new HashSet(0);
	
	private Double quantitatEnviament;
	private Date dataAcceptarEnviament;
	private Double quantitatRetorn;
	private Date dataAcceptarRetorn;
	
//	private Long idPartida;

	public Trasllat(){}
	
	public Trasllat(Establiment establimentByTdiCodede, Traza traza,
			Establiment establimentByTdiCodeor, Date data,
			Boolean acceptatTrasllat, Boolean retornatEstablimentOrigen,
			Boolean traslladant, Boolean valid, Boolean esDiposit, Date dataAlta, Set diposits) { //, Long idPartida) {
		this.establimentByTdiCodede = establimentByTdiCodede;
		this.traza = traza;
		this.establimentByTdiCodeor = establimentByTdiCodeor;
		this.data = data;
		this.acceptatTrasllat = acceptatTrasllat;
		this.retornatEstablimentOrigen = retornatEstablimentOrigen;
		this.traslladant = traslladant;
		this.valid = valid;
		this.dataAlta = dataAlta;
		this.diposits = diposits;
//		this.idPartida = idPartida;
		this.esDiposit = esDiposit;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Establiment getEstablimentByTdiCodede() {
		return this.establimentByTdiCodede;
	}

	public void setEstablimentByTdiCodede(Establiment establimentByTdiCodede) {
		this.establimentByTdiCodede = establimentByTdiCodede;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Establiment getEstablimentByTdiCodeor() {
		return this.establimentByTdiCodeor;
	}

	public void setEstablimentByTdiCodeor(Establiment establimentByTdiCodeor) {
		this.establimentByTdiCodeor = establimentByTdiCodeor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getAcceptatTrasllat() {
		return this.acceptatTrasllat;
	}

	public void setAcceptatTrasllat(Boolean acceptatTrasllat) {
		this.acceptatTrasllat = acceptatTrasllat;
	}

	public Boolean getRetornatEstablimentOrigen() {
		return this.retornatEstablimentOrigen;
	}

	public void setRetornatEstablimentOrigen(Boolean retornatEstablimentOrigen) {
		this.retornatEstablimentOrigen = retornatEstablimentOrigen;
	}

	public Boolean getTraslladant() {
		return this.traslladant;
	}

	public void setTraslladant(Boolean traslladant) {
		this.traslladant = traslladant;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getEsDiposit() {
		return esDiposit;
	}

	public void setEsDiposit(Boolean esDiposit) {
		this.esDiposit = esDiposit;
	}
	
	public Date getDataAlta() {
		return this.dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public Set getDiposits() {
		return this.diposits;
	}

	public void setDiposits(Set diposits) {
		this.diposits = diposits;
	}
	
	
	
	public Double getQuantitatEnviament() {
		return quantitatEnviament;
	}

	public void setQuantitatEnviament(Double quantitatEnviament) {
		this.quantitatEnviament = quantitatEnviament;
	}

	public Double getQuantitatRetorn() {
		return quantitatRetorn;
	}

	public void setQuantitatRetorn(Double quantitatRetorn) {
		this.quantitatRetorn = quantitatRetorn;
	}
	
	public Date getDataAcceptarEnviament() {
		return dataAcceptarEnviament;
	}

	public void setDataAcceptarEnviament(Date dataAcceptarEnviament) {
		this.dataAcceptarEnviament = dataAcceptarEnviament;
	}

	public Date getDataAcceptarRetorn() {
		return dataAcceptarRetorn;
	}

	public void setDataAcceptarRetorn(Date dataAcceptarRetorn) {
		this.dataAcceptarRetorn = dataAcceptarRetorn;
	}

	
	
	
//	public Long getIdPartida() {
//		return idPartida;
//	}
//
//	public void setIdPartida(Long idPartida) {
//		this.idPartida = idPartida;
//	}
	
	// The following is extra code specified in the hbm.xml files



	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getData());
	}
	
	public String getDataAcceptarEnviamentFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.getDataAcceptarEnviament() != null)
			return sdf.format(this.getDataAcceptarEnviament());
		else
			return "";
	}
	
	public String getDataAcceptarRetornFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.getDataAcceptarRetorn() != null)
			return sdf.format(this.getDataAcceptarRetorn());
		else
			return "";
	}

	// end of extra code specified in the hbm.xml files

}
