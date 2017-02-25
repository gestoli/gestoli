package es.caib.gestoli.front.spring;

// Generated 16-jun-2009 18:15:18 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author  Carlos Pérez(cperez@at4.net)
 * 		
 */
public class TrasllatCommand extends Trasllat {
	
	private Diposit dipositSeleccionado = new Diposit();
	private String nomDiposits;
	private Double quantitatMostrar;
	private CategoriaOli categoriaOli;
	private Integer sentit;
	
	public TrasllatCommand(Establiment establimentByTdiCodede, Traza traza,
			Establiment establimentByTdiCodeor, Date data,
			Boolean acceptatTrasllat, Boolean retornatEstablimentOrigen,
			Boolean traslladant, Boolean valid, Boolean esDiposit, Date dataAlta, Set diposits){ //, Long idPartida){
		super(establimentByTdiCodede,
				traza,
				establimentByTdiCodeor,
				data,
				acceptatTrasllat,
				retornatEstablimentOrigen,
				traslladant,
				valid,
				esDiposit,
				dataAlta,
				diposits); //,
//				idPartida);
	}
	
	/**
	 * Rellena los campos de este objeto con la información
	 * del Trasllat
	 * 
	 * @param te Trasllat
	 */
	public void fromTrasllat(Trasllat td) {
		setId(td.getId());
		setEstablimentByTdiCodeor(td.getEstablimentByTdiCodeor());
		setDiposits(td.getDiposits());
		setAcceptatTrasllat(td.getAcceptatTrasllat());
		setData(td.getData());
		setEstablimentByTdiCodede(td.getEstablimentByTdiCodede());
		setRetornatEstablimentOrigen(td.getRetornatEstablimentOrigen());
		setTraza(td.getTraza());
		setTraslladant(td.getTraslladant());
		setValid(td.getValid());
		setDataAlta(td.getDataAlta());
//		setIdPartida(td.getIdPartida());
		setEsDiposit(td.getEsDiposit());
	}
	
	public Diposit getDipositSeleccionado() {
		return dipositSeleccionado;
	}

	public void setDipositSeleccionado(Diposit dipositSeleccionado) {
		this.dipositSeleccionado = dipositSeleccionado;
	}


	public String getNomDiposits() {
		return nomDiposits;
	}

	public void setNomDiposits(String nomDiposits) {
		this.nomDiposits = nomDiposits;
	}

	
	public Double getQuantitatMostrar() {
		return quantitatMostrar;
	}
	public void setQuantitatMostrar(Double quantitatAMostrar) {
		this.quantitatMostrar = quantitatAMostrar;
	}

	
	public CategoriaOli getCategoriaOli() {
		return categoriaOli;
	}

	public void setCategoriaOli(CategoriaOli categoriaOli) {
		this.categoriaOli = categoriaOli;
	}

	public Integer getSentit() {
		return sentit;
	}

	public void setSentit(Integer sentit) {
		this.sentit = sentit;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.getData());
	}
	
}
