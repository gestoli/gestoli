package es.caib.gestoli.logic.model;

import java.util.Date;

public class QualitatAPPCCPlaVerificacio {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private QualitatDescripcioPersonal responsable;
	private Date dataRealitzacio;
	
	// Apartado 1
	private Boolean p1;
		private String  p11;
		private Boolean p12;
		private String  p12_comments;
		private Boolean p13;
		private String  p13_comments;
		private String  p14;
		private String  p15;

	// Apartado 2
	private Boolean p2;
		private String  p21;
		private Boolean p22;
		private String  p22_comments;

	// Apartado 3
		private Boolean p31;
		private String  p31_comments;
		private Boolean p32;
			private Boolean p321;
			private String  p321_comments;
		private Boolean p33;
			private String p331;
		private Boolean p34;
			private String p341;
		private Boolean p35;
			private String p351;

	// Apartado 4
	private String p4;
	private String p4_comments;
	
	// Apartado 5
	private Boolean p5;
	private String  p5_comments;
	
	// Apartado 6
	private String p6;
	
	private Establiment establiment;
	
	public QualitatAPPCCPlaVerificacio() {
		super();
	}
	
	public QualitatAPPCCPlaVerificacio(Long id,	QualitatDescripcioPersonal responsable,
			Date dataRealitzacio, Boolean p1, String p11, Boolean p12,
			String p12_comments, Boolean p13, String  p13_comments,	String  p14,
			String p15, Boolean p2,	String p21, Boolean p22, String p22_comments,
			Boolean p31, String p31_comments, Boolean p32, Boolean p321,
			String p321_comments, Boolean p33, String p331,	Boolean p34,
			String p341, Boolean p35, String p351, String p4, String p4_comments,
			Boolean p5, String p5_comments,	String p6, Establiment establiment){
		this.id = id;
		this.responsable = responsable;
		this.dataRealitzacio = dataRealitzacio;
		this.p1 = p1;
		this.p11 = p11;
		this.p12 = p12;
		this.p12_comments = p12_comments;
		this.p13 = p13;
		this.p13_comments = p13_comments;
		this.p14 = p14;
		this.p15 = p15;
		this.p2 = p2;
		this.p21 = p21;
		this.p22 = p22;
		this.p22_comments = p22_comments;
		this.p31 = p31;
		this.p31_comments = p31_comments;
		this.p32 = p32;
		this.p321 = p321;
		this.p321_comments = p321_comments;
		this.p33 = p33;
		this.p331 = p331;
		this.p34 = p34;
		this.p341 = p341;
		this.p35 = p35;
		this.p351 = p351;
		this.p4 = p4;
		this.p4_comments = p4_comments;
		this.p5 = p5;
		this.p5_comments = p5_comments;
		this.p6 = p6;
		this.establiment = establiment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QualitatDescripcioPersonal getResponsable() {
		return responsable;
	}

	public void setResponsable(QualitatDescripcioPersonal responsable) {
		this.responsable = responsable;
	}

	public Date getDataRealitzacio() {
		return dataRealitzacio;
	}

	public void setDataRealitzacio(Date dataRealitzacio) {
		this.dataRealitzacio = dataRealitzacio;
	}

	public Boolean getP1() {
		return p1;
	}

	public void setP1(Boolean p1) {
		this.p1 = p1;
	}

	public String getP11() {
		return p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}

	public Boolean getP12() {
		return p12;
	}

	public void setP12(Boolean p12) {
		this.p12 = p12;
	}

	public String getP12_comments() {
		return p12_comments;
	}

	public void setP12_comments(String p12_comments) {
		this.p12_comments = p12_comments;
	}

	public Boolean getP13() {
		return p13;
	}

	public void setP13(Boolean p13) {
		this.p13 = p13;
	}

	public String getP13_comments() {
		return p13_comments;
	}

	public void setP13_comments(String p13_comments) {
		this.p13_comments = p13_comments;
	}

	public String getP14() {
		return p14;
	}

	public void setP14(String p14) {
		this.p14 = p14;
	}

	public String getP15() {
		return p15;
	}

	public void setP15(String p15) {
		this.p15 = p15;
	}

	public Boolean getP2() {
		return p2;
	}

	public void setP2(Boolean p2) {
		this.p2 = p2;
	}

	public String getP21() {
		return p21;
	}

	public void setP21(String p21) {
		this.p21 = p21;
	}

	public Boolean getP22() {
		return p22;
	}

	public void setP22(Boolean p22) {
		this.p22 = p22;
	}

	public String getP22_comments() {
		return p22_comments;
	}

	public void setP22_comments(String p22_comments) {
		this.p22_comments = p22_comments;
	}

	public Boolean getP31() {
		return p31;
	}

	public void setP31(Boolean p31) {
		this.p31 = p31;
	}

	public String getP31_comments() {
		return p31_comments;
	}

	public void setP31_comments(String p31_comments) {
		this.p31_comments = p31_comments;
	}

	public Boolean getP32() {
		return p32;
	}

	public void setP32(Boolean p32) {
		this.p32 = p32;
	}

	public Boolean getP321() {
		return p321;
	}

	public void setP321(Boolean p321) {
		this.p321 = p321;
	}

	public String getP321_comments() {
		return p321_comments;
	}

	public void setP321_comments(String p321_comments) {
		this.p321_comments = p321_comments;
	}

	public Boolean getP33() {
		return p33;
	}

	public void setP33(Boolean p33) {
		this.p33 = p33;
	}

	public String getP331() {
		return p331;
	}

	public void setP331(String p331) {
		this.p331 = p331;
	}

	public Boolean getP34() {
		return p34;
	}

	public void setP34(Boolean p34) {
		this.p34 = p34;
	}

	public String getP341() {
		return p341;
	}

	public void setP341(String p341) {
		this.p341 = p341;
	}

	public Boolean getP35() {
		return p35;
	}

	public void setP35(Boolean p35) {
		this.p35 = p35;
	}

	public String getP351() {
		return p351;
	}

	public void setP351(String p351) {
		this.p351 = p351;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP4_comments() {
		return p4_comments;
	}

	public void setP4_comments(String p4_comments) {
		this.p4_comments = p4_comments;
	}

	public Boolean getP5() {
		return p5;
	}

	public void setP5(Boolean p5) {
		this.p5 = p5;
	}

	public String getP5_comments() {
		return p5_comments;
	}

	public void setP5_comments(String p5_comments) {
		this.p5_comments = p5_comments;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}
	
}
