package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Lot;

public class ModificarSortidaValidator implements Validator {

	private static Logger logger = Logger.getLogger(ProcesSortidaCommand.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String motiuVenta;
	private String venda;
    private String canviZona;
	private HibernateTemplate hibernateTemplate;
	
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesSortidaCommand.class);
	}
	public void validate(Object obj, Errors errors) {
		try {
			
			/*TODO
			 * Si el lot s'havia acabat, llevar la data de la darrera venda a lot
			*/
			ProcesSortidaCommand psc = (ProcesSortidaCommand) obj;
			Integer venudesInicialment = psc.getVendaNumeroBotelles()[1];
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
			
			if (psc.getTipusSortida().equals("l")) {

				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				
				ValidationUtils.rejectIfEmpty(
						errors,
						"vendaMotiu",
						"error.motiu.buit",
						"El camp motiu no pot estar buit");
		
					if (psc.getVendaNumeroBotelles()[0] == null ) {
						errors.rejectValue("vendaNumeroBotelles[" + 0 + "]", "error.numeroEnvassos.buit" ,"El número d'envassos no pot estar buit");
					}
					if (psc.getVendaNumeroBotelles() != null && psc.getVendaNumeroBotelles()[0].intValue() < 1) {
						errors.rejectValue("vendaNumeroBotelles[" + 0 + "]", "error.numeroEnvassos.negativo" ,"El número d'envassos introduïts ha de ser major que 0");					
					}
					//Integer botellesInicials = Integer.parseInt(request.)
					Lot lot = oliInfraestructuraEjb.lotAmbId(psc.getLot()[0].getId());

					if (psc.getVendaNumeroBotelles()[0] != null 
							&& psc.getVendaNumeroBotelles()[0].doubleValue() > (lot.getNumeroBotellesActuals().doubleValue()+venudesInicialment.doubleValue())) {
						Object[] errorArgs = new Object[1];
						errorArgs[0]= lot.getNumeroBotellesActuals()+psc.getVendaNumeroBotelles()[1];
						errors.rejectValue("vendaNumeroBotelles[" + 0 + "]", "error.numeroEnvassos.incorrecto", errorArgs, "El número d'envassos no pot ser superior als envasos existents");
					}
					
					if (psc.getVendaMotiu() != null && !"".equals(psc.getVendaMotiu()) && psc.getVendaMotiu().equals(motiuVenta)) {
						if(lot.getPartidaOli().getEsDO() && (lot.getEtiquetesLots() == null || lot.getEtiquetesLots().isEmpty())){
							errors.rejectValue(
									"vendaNumeroBotelles[" + 0 + "]",
									"error.sortida.do.contraetiqueta",
									"No es pot vendre oli DO sense contraetiquetes.");
						}
					}

			}
		
		//COMPROVEM DATA EXECUCIO
		
		
		if(psc.getLot() != null && psc.getLot().length > 0){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if(!oliInfraestructuraEjb.esDataLotCorrecte(psc.getVendaData(), psc.getLot()[0].getId())){
					errors.rejectValue(
							"vendaData",
							"error.dataExecucio.posterior",
							"Hi ha processos posteriors a la data introduida");
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
		
			
		} catch (Exception ex) {
			logger.error("Error validant el document. ", ex);
		}
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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
