package es.caib.gestoli.logic.model;

// Generated 10-dic-2009 16:00:09 by Hibernate Tools 3.2.0.b9

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class SortidaDiposit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Establiment establiment;
	private Diposit dipositBySdiCoddor;
	private Traza traza;
	private Lot lot;
	private CategoriaOli categoriaOli;
	private PartidaOli partidaOli;
	private Diposit dipositBySdiCoddde;
	private Olivicultor olivicultor;
	private Date data;
	private Double litres;
	private Double acidesa;
	private String desti;
	private String observacions;
	private Boolean valid;
	private String vendaTipusDocument;
	private String vendaNumeroDocument;
	private Pais pais;
	private Provincia provincia;
	private Municipi municipi;
	
	public SortidaDiposit() {
	}

	public SortidaDiposit(Establiment establiment, Diposit dipositBySdiCoddor,
			Traza traza, PartidaOli partidaOli, Double litres, Boolean valid) {
		this.establiment = establiment;
		this.dipositBySdiCoddor = dipositBySdiCoddor;
		this.traza = traza;
		this.categoriaOli = (partidaOli != null)?partidaOli.getCategoriaOli():null;
		this.partidaOli = partidaOli;
		this.litres = litres;
		this.valid = valid;
	}

	public SortidaDiposit(Establiment establiment, Diposit dipositBySdiCoddor,
			Traza traza, Lot lot, PartidaOli partidaOli,
			Diposit dipositBySdiCoddde, Olivicultor olivicultor, Date data,
			Double litres, Double acidesa, String desti, String observacions,
			Boolean valid, String vendaTipusDocument, String vendaNumeroDocument,
			Pais pais, Provincia provincia, Municipi municipi) {
		this.establiment = establiment;
		this.dipositBySdiCoddor = dipositBySdiCoddor;
		this.traza = traza;
		this.lot = lot;
		this.categoriaOli = (partidaOli != null)?partidaOli.getCategoriaOli():null;
		this.partidaOli = partidaOli;
		this.dipositBySdiCoddde = dipositBySdiCoddde;
		this.olivicultor = olivicultor;
		this.data = data;
		this.litres = litres;
		this.acidesa = acidesa;
		this.desti = desti;
		this.observacions = observacions;
		this.valid = valid;
		this.vendaTipusDocument = vendaTipusDocument;
		this.vendaNumeroDocument = vendaNumeroDocument;
		this.pais = pais;
		this.provincia = provincia;
		this.municipi = municipi;
	}
	
	public SortidaDiposit(Establiment establiment, Diposit dipositBySdiCoddor,
			Traza traza, Lot lot, PartidaOli partidaOli,
			Diposit dipositBySdiCoddde, Olivicultor olivicultor, Date data,
			Double litres, Double acidesa, String desti, String observacions,
			Boolean valid, String vendaTipusDocument, String vendaNumeroDocument, CategoriaOli categoriaOli) {
		this.establiment = establiment;
		this.dipositBySdiCoddor = dipositBySdiCoddor;
		this.traza = traza;
		this.lot = lot;
		this.categoriaOli = categoriaOli;
		this.partidaOli = partidaOli;
		this.dipositBySdiCoddde = dipositBySdiCoddde;
		this.olivicultor = olivicultor;
		this.data = data;
		this.litres = litres;
		this.acidesa = acidesa;
		this.desti = desti;
		this.observacions = observacions;
		this.valid = valid;
		this.vendaTipusDocument = vendaTipusDocument;
		this.vendaNumeroDocument = vendaNumeroDocument;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Establiment getEstabliment() {
		return this.establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public Diposit getDipositBySdiCoddor() {
		return this.dipositBySdiCoddor;
	}

	public void setDipositBySdiCoddor(Diposit dipositBySdiCoddor) {
		this.dipositBySdiCoddor = dipositBySdiCoddor;
	}

	public Traza getTraza() {
		return this.traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public CategoriaOli getCategoriaOli() {
		return this.categoriaOli;
	}

	public void setCategoriaOli(CategoriaOli categoriaOli) {
		this.categoriaOli = categoriaOli;
	}

	/**
	 * @return the partidaOli
	 */
	public PartidaOli getPartidaOli() {
		return partidaOli;
	}

	/**
	 * @param partidaOli the partidaOli to set
	 */
	public void setPartidaOli(PartidaOli partidaOli) {
		this.partidaOli = partidaOli;
		
//		if(partidaOli != null)
//			this.categoriaOli = partidaOli.getCategoriaOli();
//		else
//			this.categoriaOli = null;
	}

	public Diposit getDipositBySdiCoddde() {
		return this.dipositBySdiCoddde;
	}

	public void setDipositBySdiCoddde(Diposit dipositBySdiCoddde) {
		this.dipositBySdiCoddde = dipositBySdiCoddde;
	}

	public Olivicultor getOlivicultor() {
		return this.olivicultor;
	}

	public void setOlivicultor(Olivicultor olivicultor) {
		this.olivicultor = olivicultor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getLitres() {
		return this.litres;
	}

	public void setLitres(Double litres) {
		this.litres = litres;
	}

	public Double getAcidesa() {
		return this.acidesa;
	}

	public void setAcidesa(Double acidesa) {
		this.acidesa = acidesa;
	}

	public String getDesti() {
		return this.desti;
	}

	public void setDesti(String desti) {
		this.desti = desti;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getVendaTipusDocument() {
		return this.vendaTipusDocument;
	}

	public void setVendaTipusDocument(String vendaTipusDocument) {
		this.vendaTipusDocument = vendaTipusDocument;
	}

	public String getVendaNumeroDocument() {
		return this.vendaNumeroDocument;
	}

	public void setVendaNumeroDocument(String vendaNumeroDocument) {
		this.vendaNumeroDocument = vendaNumeroDocument;
	}

	// The following is extra code specified in the hbm.xml files

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = "";
		if (this.data != null) {
			fechaFormateada = sdf.format(this.data);
		}
		return fechaFormateada;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Municipi getMunicipi() {
		return municipi;
	}

	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
	}

	// end of extra code specified in the hbm.xml files

}
