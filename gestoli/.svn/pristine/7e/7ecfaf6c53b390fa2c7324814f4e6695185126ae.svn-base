/**
 * ProcesSortidaOlivaTaulaValidator.java
 *
 * Creada el 6 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Bota;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesSortidaOlivaTaulaBotaGranelValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesSortidaOlivaTaulaBotaGranelValidator.class);

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
		return clazz.equals(ProcesSortidaOlivaTaulaBotaGranelCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		try {
			ProcesSortidaOlivaTaulaBotaGranelCommand psc = (ProcesSortidaOlivaTaulaBotaGranelCommand) obj;
			
			if (psc.getAccion().equals("v")) {
			
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

				}
				
			
			
				//COMPROVEM DATA EXECUCIO
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Bota diposit = oliInfraestructuraEjb.botaAmbId(psc.getIdBota());
				if(diposit != null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					if(!oliInfraestructuraEjb.esDataDipositCorrecte(psc.getVendaData(),diposit.getId())){
						errors.rejectValue(
								"vendaData",
								"error.dataExecucio.posterior",
								"Hi ha processos posteriors a la data introduida");
					}
				}
				
				if(psc.getKilos() == null || psc.getKilos().doubleValue() == new Double(0.0)){
					errors.rejectValue(
						"kilos",
						"error.quantitatAVendre.negativo",
						"La quantitat d'oliva que es vol vendre ha de ser major que 0");
				}
				
				
				if(psc.getKilos() > diposit.getKgOlivaRestant()){
					errors.rejectValue(
							"kilos",
							"error.quantitatAVendre.exces",
							"La quantitat d'oliva que es vol vendre és superior a la que resta a la bota");
				}
				
				
				//Comprovació campanya
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
				if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(psc.getVendaData())){
					errors.rejectValue(
							"data",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
				}
			
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
