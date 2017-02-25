package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class QualitatPlaControlTransport extends QualitatControl {

	private static final long serialVersionUID = 1L;

	private Long id;
	private QualitatDescripcioEquip vehicle;
	private Boolean estibaCorrecta;
	private Boolean netejaCorrecta;
	private Establiment establiment;
	private String estibaCorrectaString;
	private String netejaCorrectaString;

	
	public QualitatPlaControlTransport() {
		super();
	}


	public QualitatPlaControlTransport(Long id, QualitatDescripcioEquip vehicle,
			Date dataVerificacio, Boolean estibaCorrecta,
			Boolean netejaCorrecta,
			QualitatDescripcioPersonal responsableVerificacio, Establiment establiment) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.dataVerificacio = dataVerificacio;
		this.estibaCorrecta = estibaCorrecta;
		this.netejaCorrecta = netejaCorrecta;
		this.responsableVerificacio = responsableVerificacio;
		this.establiment = establiment;
		satisfactori = (estibaCorrecta && netejaCorrecta);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public QualitatDescripcioEquip getVehicle() {
		return vehicle;
	}


	public void setVehicle(QualitatDescripcioEquip equip) {
		this.vehicle = equip;
	}


	public Date getDataVerificacio() {
		return dataVerificacio;
	}


	public void setDataVerificacio(Date dataVerificacio) {
		this.dataVerificacio = dataVerificacio;
	}


	public Boolean getEstibaCorrecta() {
		return estibaCorrecta;
	}


	public void setEstibaCorrecta(Boolean estibaCorrecta) {
		this.estibaCorrecta = estibaCorrecta;
	}


	public Boolean getNetejaCorrecta() {
		return netejaCorrecta;
	}


	public void setNetejaCorrecta(Boolean netejaCorrecta) {
		this.netejaCorrecta = netejaCorrecta;
	}


	public QualitatDescripcioPersonal getResponsableVerificacio() {
		return responsableVerificacio;
	}


	public void setResponsableVerificacio(QualitatDescripcioPersonal responsableVerificacio) {
		this.responsableVerificacio = responsableVerificacio;
	}


	public Establiment getEstabliment() {
		return establiment;
	}


	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}


	public String getEstibaCorrectaString() {
		return estibaCorrectaString;
	}


	public void setEstibaCorrectaString(String estibaCorrectaString) {
		this.estibaCorrectaString = estibaCorrectaString;
	}


	public String getNetejaCorrectaString() {
		return netejaCorrectaString;
	}


	public void setNetejaCorrectaString(String netejaCorrectaString) {
		this.netejaCorrectaString = netejaCorrectaString;
	}
	
	
}
