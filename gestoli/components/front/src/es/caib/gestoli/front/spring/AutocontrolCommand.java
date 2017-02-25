/**
 * DipositCommand.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import es.caib.gestoli.logic.model.AutocontrolOliva;



/**
 * Objeto que representa el formulario de mantenimento
 * de zona.
 * 
 * 
 */
public class AutocontrolCommand extends AutocontrolOliva {
	
	private static final long serialVersionUID = 1465768940114210213L;

	private Long idTraza;
	private Long idLot;
	private Long idEstabliment;
	private Long idBota;
	
	
	private String nomLot;
	private String nomEstabliment;
	
	
	
	/**
     * Rellena los campos de este objeto con la información
     *
     * @param d Diposit
     */
    public void fromAutocontrolOliva(AutocontrolOliva a) {
    	setData(a.getData());
    	setHora(a.getHora());
    	setLot(null);
    	setResponsable(a.getResponsable());
    	setIdBota(a.getBota());
    	setTraza(a.getTraza());
    	setEstabliment(a.getEstabliment());
    	setIdLot(null);
    	setIdTraza(a.getTraza().getId());
    	setIdEstabliment(a.getEstabliment().getId());
    	setNomLot(null);
    	setNomEstabliment(a.getEstabliment().getNom());
    	setColorGroc(a.getColorGroc());
    	setColorVerdGroc(a.getColorVerdGroc());
    	setColorVerd(a.getColorVerd());
    	setColorMarro(a.getColorMarro());
    	setColorMarroObscur(a.getColorMarroObscur());
    	setColorNegre(a.getColorNegre());
    	setColorAltres(a.getColorAltres());
    	setOKcolorCorrecte(a.getOKcolorCorrecte());
    	setAromaVegetal(a.getAromaVegetal());
    	setAromaMineral(a.getAromaMineral());
    	setAromaFonoll(a.getAromaFonoll());
    	setAromaPebre(a.getAromaPebre());
    	setAromaTerra(a.getAromaTerra());
    	setAromaAltres(a.getAromaAltres());
    	setOKaromaCorrecte(a.getOKaromaCorrecte());
    	setTexturaFermesa1(a.getTexturaFermesa1());
    	setTexturaFermesa2(a.getTexturaFermesa2());
    	setTexturaFermesa3(a.getTexturaFermesa3());
    	setTexturaFermesa4(a.getTexturaFermesa4());
    	setTexturaFermesa5(a.getTexturaFermesa5());
    	setOKtexturaFermesaCorrecte(a.getOKtexturaFermesaCorrecte());
    	setTexturaDeformabilitat1(a.getTexturaDeformabilitat1());
    	setTexturaDeformabilitat2(a.getTexturaDeformabilitat2());
    	setTexturaDeformabilitat3(a.getTexturaDeformabilitat3());
    	setTexturaDeformabilitat4(a.getTexturaDeformabilitat4());
    	setTexturaDeformabilitat5(a.getTexturaDeformabilitat5());
    	setOKtexturaDeformabilitatCorrecte(a.getOKtexturaDeformabilitatCorrecte());
    	setTexturaElasticitat1(a.getTexturaElasticitat1());
    	setTexturaElasticitat2(a.getTexturaElasticitat2());
    	setTexturaElasticitat3(a.getTexturaElasticitat3());
    	setTexturaElasticitat4(a.getTexturaElasticitat4());
    	setTexturaElasticitat5(a.getTexturaElasticitat5());
    	setOKtexturaElasticitatCorrecte(a.getOKtexturaElasticitatCorrecte());
    	setTexturaSuavitat1(a.getTexturaSuavitat1());
    	setTexturaSuavitat2(a.getTexturaSuavitat2());
    	setTexturaSuavitat3(a.getTexturaSuavitat3());
    	setTexturaSuavitat4(a.getTexturaSuavitat4());
    	setTexturaSuavitat5(a.getTexturaSuavitat5());
    	setOKtexturaSuavitatCorrecte(a.getOKtexturaSuavitatCorrecte());
    	setTexturabocaFermesaEnBoca1(a.getTexturabocaFermesaEnBoca1());
    	setTexturabocaFermesaEnBoca2(a.getTexturabocaFermesaEnBoca2());
    	setTexturabocaFermesaEnBoca3(a.getTexturabocaFermesaEnBoca3());
    	setTexturabocaFermesaEnBoca4(a.getTexturabocaFermesaEnBoca4());
    	setTexturabocaFermesaEnBoca5(a.getTexturabocaFermesaEnBoca5());
    	setOKtexturabocaFermesaEnBocaCorrecte(a.getOKtexturabocaFermesaEnBocaCorrecte());
    	setTexturaFriabilitat1(a.getTexturaFriabilitat1());
    	setTexturaFriabilitat2(a.getTexturaFriabilitat2());
    	setTexturaFriabilitat3(a.getTexturaFriabilitat3());
    	setTexturaFriabilitat4(a.getTexturaFriabilitat4());
    	setTexturaFriabilitat5(a.getTexturaFriabilitat5());
    	setOKtexturaFriabilitatCorrecte(a.getOKtexturaFriabilitatCorrecte());
    	setTexturaCohesio1(a.getTexturaCohesio1());
    	setTexturaCohesio2(a.getTexturaCohesio2());
    	setTexturaCohesio3(a.getTexturaCohesio3());
    	setTexturaCohesio4(a.getTexturaCohesio4());
    	setTexturaCohesio5(a.getTexturaCohesio5());
    	setOKtexturaCohesioCorrecte(a.getOKtexturaCohesioCorrecte());
    	setTexturaUntuositat1(a.getTexturaUntuositat1());
    	setTexturaUntuositat2(a.getTexturaUntuositat2());
    	setTexturaUntuositat3(a.getTexturaUntuositat3());
    	setTexturaUntuositat4(a.getTexturaUntuositat4());
    	setTexturaUntuositat5(a.getTexturaUntuositat5());
    	setOKtexturaUntuositatCorrecte(a.getOKtexturaUntuositatCorrecte());
    	setSaborAcid(a.getSaborAcid());
    	setSaborSalat(a.getSaborSalat());
    	setSaborAmarg(a.getSaborAmarg());
    	setSaborAltres(a.getSaborAltres());
    	setOKsaborCorrecte(a.getOKsaborCorrecte());
    	setSensacioAstringent(a.getSensacioAstringent());
    	setSensacioPicant(a.getSensacioPicant());
    	setSensacioAltres(a.getSensacioAltres());
    	setOKsensacioCorrecte(a.getOKsensacioCorrecte());
    	setRegustBaix(a.getRegustBaix());
    	setRegustMitja(a.getRegustMitja());
    	setRegustProlongat(a.getRegustProlongat());
    	setOKregustCorrecte(a.getOKregustCorrecte());
    	setApta(a.getApta());
    	setMesuresCorrectores(a.getMesuresCorrectores());
    	setVerificacioMesuresCorrectores(a.getVerificacioMesuresCorrectores());
    	
    }




	public Long getIdTraza() {
		return idTraza;
	}
	public void setIdTraza(Long idTraza) {
		this.idTraza = idTraza;
	}

	public Long getIdLot() {
		return idLot;
	}
	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public Long getIdEstabliment() {
		return idEstabliment;
	}
	
	public void setIdEstabliment(Long idEstabliment) {
		this.idEstabliment = idEstabliment;
	}
	
	public String getNomLot() {
		return nomLot;
	}
	public void setNomLot(String nomLot) {
		this.nomLot = nomLot;
	}

	public String getNomEstabliment() {
		return nomEstabliment;
	}
	public void setNomEstabliment(String nomEstabliment) {
		this.nomEstabliment = nomEstabliment;
	}
	public Long getIdBota() {
		return idBota;
	}
	public void setIdBota(Long idBota) {
		this.idBota = idBota;
	}



}