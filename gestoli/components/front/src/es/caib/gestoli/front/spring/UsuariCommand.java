/**
 * UsuariCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import es.caib.gestoli.logic.model.Usuari;


/**
 * Objeto que representa el formulario de mantenimento
 * de usuarios.
 * 
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class UsuariCommand extends Usuari {
	private static final long serialVersionUID = 3660614058015788632L;
//	private String[] grupsArray;
	private String idiomaId;
	private String olivicultorName;
	private String establimentName;
	private String contrasenyaEncriptada;
	private Integer tipusUsuari;
	private Integer tipusUsuariEstabliment;
	
	private Long establimentIdTafona;
	private Long establimentIdEnvasadora;
	private Long establimentIdTafEnv;

	private Boolean olivaTaula;

	
	/**
     * Rellena los campos de este objeto con la información
     * del usuario.
     * @param u Usuari
     */
    public void fromUsuari(Usuari u) {
    	setId(u.getId());
    	setIdioma(u.getIdioma());
    	setGrups(u.getGrups());
    	setActiu(u.getActiu());
    	setUsuari(u.getUsuari());
    	setContrasenya(u.getContrasenya());
		setContrasenyaEncriptada(u.getContrasenya());
    	setEmail(u.getEmail());
    	setObservacions(u.getObservacions());
    	setOlivicultors(u.getOlivicultors());
//    	setOlivicultor(u.getOlivicultor());
//    	setEstabliment(u.getEstabliment());
    	setEstabliments(u.getEstabliments());
    }

    /**
     * Para imprimir bien el registro
     */
    public String toString() {
    	return "Usuari(" + getUsuari() + ")";
    }

	

	public String getIdiomaId() {
		return idiomaId;
	}

	public void setIdiomaId(String idiomaId) {
		this.idiomaId = idiomaId;
	}

//	public String[] getGrupsArray() {
//		return grupsArray;
//	}
//
//	public void setGrupsArray(String[] grupsArray) {
//		this.grupsArray = grupsArray;
//	}

	public String getOlivicultorName() {
		return olivicultorName;
	}

	public void setOlivicultorName(String olivicultorName) {
		this.olivicultorName = olivicultorName;
	}

	public String getEstablimentName() {
		return establimentName;
	}

	public void setEstablimentName(String establimentName) {
		this.establimentName = establimentName;
	}

	public String getContrasenyaEncriptada() {
		return contrasenyaEncriptada;
	}

	public void setContrasenyaEncriptada(String contrasenyaEncriptada) {
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}

	public Integer getTipusUsuari() {
		return tipusUsuari;
	}

	public void setTipusUsuari(Integer tipusUsuari) {
		this.tipusUsuari = tipusUsuari;
	}
	
	public Integer getTipusUsuariEstabliment() {
		return tipusUsuariEstabliment;
	}

	public void setTipusUsuariEstabliment(Integer tipusUsuariEstabliment) {
		this.tipusUsuariEstabliment = tipusUsuariEstabliment;
	}

	public Long getEstablimentIdTafona() {
		return establimentIdTafona;
	}

	public void setEstablimentIdTafona(Long establimentIdTafona) {
		this.establimentIdTafona = establimentIdTafona;
	}

	public Long getEstablimentIdEnvasadora() {
		return establimentIdEnvasadora;
	}

	public void setEstablimentIdEnvasadora(Long establimentIdEnvasadora) {
		this.establimentIdEnvasadora = establimentIdEnvasadora;
	}

	public Long getEstablimentIdTafEnv() {
		return establimentIdTafEnv;
	}

	public void setEstablimentIdTafEnv(Long establimentIdTafEnv) {
		this.establimentIdTafEnv = establimentIdTafEnv;
	}

	public Boolean getOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(Boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}


}
