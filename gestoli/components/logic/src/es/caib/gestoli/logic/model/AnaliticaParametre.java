package es.caib.gestoli.logic.model;

// Generated 10-nov-2009 17:31:19 by Hibernate Tools 3.2.0.b9


/**
 * 			Javadoc de les clases Model de Gest Oli
 * 			@author Oriol Barnes Cruz (obarnes@at4.net)
 * 		
 */
public class AnaliticaParametre implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private int operador;
	
	private String valor;
	
	private Boolean obligatori;
	
	private VarietatOli varietatOli;
	
	private AnaliticaParametreTipus analiticaParametreTipus;
	

	public AnaliticaParametre() {
	}

	public AnaliticaParametre(int operador, String valor, VarietatOli varietatOli,AnaliticaParametreTipus analiticaParametreTipus, Boolean obligatori) {
		this.operador = operador;
		this.valor = valor;
		this.varietatOli = varietatOli;
		this.analiticaParametreTipus = analiticaParametreTipus;
		this.obligatori = obligatori;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public VarietatOli getVarietatOli() {
		return varietatOli;
	}

	public void setVarietatOli(VarietatOli varietatOli) {
		this.varietatOli = varietatOli;
	}

	public int getOperador() {
		return operador;
	}

	public void setOperador(int operador) {
		this.operador = operador;
	}

	public AnaliticaParametreTipus getAnaliticaParametreTipus() {
		return analiticaParametreTipus;
	}

	public void setAnaliticaParametreTipus(AnaliticaParametreTipus analiticaParametreTipus) {
		this.analiticaParametreTipus = analiticaParametreTipus;
	}

	public Boolean getObligatori() {
		return obligatori;
	}

	public void setObligatori(Boolean obligatori) {
		this.obligatori = obligatori;
	}
	

}
