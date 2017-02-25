/**
 * ProcesSortidaOlivaTaulaValidator.java
 *
 * Creada el 6 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Lot;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesSortidaOlivaTaulaValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesSortidaOlivaTaulaValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String motiuVenta;
	private String venda;
    private String canviZona;
    
	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesSortidaOlivaTaulaCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		try {
			ProcesSortidaOlivaTaulaCommand psc = (ProcesSortidaOlivaTaulaCommand) obj;
			
			if (psc.getAccion().equals(venda)) {
			
				ValidationUtils.rejectIfEmpty(
						errors,
						"vendaData",
						"error.data.buit",
						"El camp Data no pot estar buit");

				if (psc.getVendaMotiu() != null && !"".equals(psc.getVendaMotiu()) && psc.getVendaMotiu().equals(motiuVenta)) {

					ValidationUtils.rejectIfEmpty(
							errors,
							"vendaDestinatari",
							"error.destinatari.buit",
					"S'ha d'indicar el destinatari de la sortida");

/*					
					ValidationUtils.rejectIfEmpty(
							errors,
							"vendaTipusDocument",
							"error.tipusDocument.buit",
					"S'ha d'indicar el tipus de document");

					ValidationUtils.rejectIfEmpty(
							errors,
							"vendaNumeroDocument",
							"error.numeroDocument.buit",
					"S'ha d'indicar el número de document");
*/
				}
				
				if (psc.getTipusSortida().equals("l")) {

					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"vendaMotiu",
							"error.motiu.buit",
							"El camp motiu no pot estar buit");
			
					for (int i = 0; i < psc.getLot().length; i++){
						if (psc.getVendaNumeroBotelles()[i] == null ) {
							errors.rejectValue("vendaNumeroBotelles[" + i + "]", "error.numeroEnvassos.buit" ,"El número d'envassos no pot estar buit");
						}
						if (psc.getVendaNumeroBotelles() != null && psc.getVendaNumeroBotelles()[i].intValue() < 1) {
							errors.rejectValue("vendaNumeroBotelles[" + i + "]", "error.numeroEnvassos.negativo" ,"El número d'envassos introduïts ha de ser major que 0");					
						}

						Lot lot = oliInfraestructuraEjb.lotAmbId(psc.getLot()[i].getId());

						if (psc.getVendaNumeroBotelles()[i] != null && psc.getVendaNumeroBotelles()[i].doubleValue() > lot.getNumeroBotellesActuals().doubleValue()) {
							Object[] errorArgs = new Object[1];
							errorArgs[0]= lot.getNumeroBotellesActuals();
							errors.rejectValue("vendaNumeroBotelles[" + i + "]", "error.numeroEnvassos.incorrecto", errorArgs, "El número d'envassos no pot ser superior als envasos existents");
						}
						
						if (psc.getVendaMotiu() != null && !"".equals(psc.getVendaMotiu()) && psc.getVendaMotiu().equals(motiuVenta)) {
							if(lot.getPartidaOli().getEsDO() && (lot.getEtiquetesLots() == null || lot.getEtiquetesLots().isEmpty())){
								errors.rejectValue(
										"vendaNumeroBotelles[" + i + "]",
										"error.sortida.do.contraetiqueta",
										"No es pot vendre oli DO sense contraetiquetes.");
							}
						}
					}

				} else if (psc.getTipusSortida().equals("d")) {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());					
					Diposit diposit = oliInfraestructuraEjb.dipositAmbId(psc.getDiposit().getId());
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"vendaDestinatari",
							"error.destinatari.buit",
					"S'ha d'indicar el destinatari de la sortida");

					String mesura = diposit.getEstabliment().getUnitatMesura().equals("l")?"litros":"kilos";
					
					ValidationUtils.rejectIfEmpty(
							errors,
							mesura,
							"error.quantitatAVendre.buit",
							"S'ha d'indicar la quantitat que es vol vendre");
					
					DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
					if (psc.getLitros() != null) {
						Object[] valor = new Object[1];
						valor[0] =  String.valueOf(mesura.equals("litros")?diposit.getVolumActual().toString():numberDecimalFormat.format(diposit.getVolumActual().doubleValue() * 0.916));
						if (psc.getLitros().doubleValue() > diposit.getVolumActual().doubleValue()) {
							errors.rejectValue(mesura, "error.quantitatAVendre.incorrecto", valor, "La quantitat que es vole vendre supera la que hi ha actualment: {0}");
						}						
						if (psc.getLitros().doubleValue() < 0.001) {
							errors.rejectValue(mesura, "error.quantitatAVendre.negativo", "La quantitat d'oli que es vol vendre ha de ser major que 0");
						}
					}
				}
			} else if (psc.getAccion().equals(canviZona)) {
				ValidationUtils.rejectIfEmpty(
						errors,
						"canviZonaData",
						"error.data.buit",
						"El camp Data no pot estar buit");
				
				ValidationUtils.rejectIfEmpty(
						errors,
						"idZona",
						"error.zona.buit",
						"El camp Zona no pot estar buit");
			}
			
			
			//COMPROVEM DATA EXECUCIO
			
			if(psc.getDiposit() != null){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if(!oliInfraestructuraEjb.esDataDipositCorrecte(psc.getVendaData(), psc.getDiposit().getId())){
					errors.rejectValue(
							"vendaData",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
				}
			}
			
			if(psc.getLot() != null && psc.getLot().length > 0){
				for(int i=0; i<psc.getLot().length; i++){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					if(!oliInfraestructuraEjb.esDataLotCorrecte(psc.getVendaData(), psc.getLot()[i].getId())){
						errors.rejectValue(
								"vendaData",
								"error.dataExecucio.posterior",
								"Hi ha processos posteriors a la data introduida");
					}
				}
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(psc.getVendaData())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
		
	}
	
	
	public void setMotiuVenta(String motiuVenta) {
		this.motiuVenta = motiuVenta;
	}

	public void setVenda(String venda) {
		this.venda = venda;
	}

	public void setCanviZona(String canviZona) {
		this.canviZona = canviZona;
	}

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	
	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
}
