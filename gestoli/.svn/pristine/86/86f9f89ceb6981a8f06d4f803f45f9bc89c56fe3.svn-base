/**
 * ProcesSortidaOlivaTaulaCruaGranelValidator.java
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
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.PartidaOliva;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesSortidaOlivaTaulaCruaGranelValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesSortidaOlivaTaulaCruaGranelValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String motiuVenta;
	private String venda;
    private String canviZona;
    private OliProcessosEjb oliProcessosEjb;
    
	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesSortidaOlivaTaulaCruaGranelCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		try {
			ProcesSortidaOlivaTaulaCruaGranelCommand psc = (ProcesSortidaOlivaTaulaCruaGranelCommand) obj;
			
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
				
			//comprovam que les sortides d'oliva crua a granel siguin superiors a 0
			if(psc.getKilos()== null || psc.getKilos().doubleValue() == new Double(0.0)){
				errors.rejectValue(
						"kilos",
						"error.quantitatAVendre.negativo",
						"La quantitat d'oliva que es vol vendre ha de ser major que 0");
			}
			//comprovam que no es tregui més oliva de la que hi ha
			oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			PartidaOliva pOliva = oliProcessosEjb.partidaAmbId(psc.getIdPartidaOliva());
			if(psc.getKilos() > pOliva.getQuantitat()){
				errors.rejectValue(
						"kilos",
						"error.quantitatAVendre.exces",
						"La quantitat d'oliva que es vol vendre és superior a la restant");
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

	public OliProcessosEjb getOliProcessosEjb() {
		return oliProcessosEjb;
	}

	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}
}
