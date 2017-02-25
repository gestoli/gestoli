package es.caib.gestoli.logic.model;

import java.io.Serializable;
import java.util.Date;

public class Avis implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;
		private String tipus;
		private String descripcio;
		private String frecuencia; //dia->Diario / sema->Semanal / ...
		private Date dataSeguent;

		private Establiment establiment;

		public Avis() {
			super();
		}
		
		public Avis(
				Long id, String tipus, String descripcio, String frecuencia, Date dataSeguent,
				Boolean actiu, Establiment establiment){
			this.id = id;
			this.tipus = tipus;
			this.descripcio = descripcio;
			this.frecuencia = frecuencia;
			this.dataSeguent = dataSeguent;
			this.establiment = establiment;			
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTipus() {
			return tipus;
		}
		public void setTipus(String tipus) {
			this.tipus = tipus;
		}
		public String getDescripcio() {
			return descripcio;
		}
		public void setDescripcio(String descripcio) {
			this.descripcio = descripcio;
		}
		public String getFrecuencia() {
			return frecuencia;
		}
		public void setFrecuencia(String frecuencia) {
			this.frecuencia = frecuencia;
		}
		public Date getDataSeguent() {
			return dataSeguent;
		}
		public void setDataSeguent(Date dataSeguent) {
			this.dataSeguent = dataSeguent;
		}

		public Establiment getEstabliment() {
			return establiment;
		}

		public void setEstabliment(Establiment establiment) {
			this.establiment = establiment;
		}
		
}
