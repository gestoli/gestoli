/**
 * ProcesEtiquetarValidator.java
 *
 * Creada el 6 de juliol de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.EtiquetesLot;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class ProcesEtiquetarValidator implements Validator {
	private static Logger logger = Logger.getLogger(ProcesEtiquetarValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesEtiquetarCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		try {
			ProcesEtiquetarCommand command = (ProcesEtiquetarCommand)obj;
			
			for(int i=0; i<command.getIdEtiquetes().length; i++){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				EtiquetesLot et = oliInfraestructuraEjb.findEtiquetesLotById(command.getIdEtiquetes()[i]);
				
				if(command.getEtiquetesInicials()[i] != null && command.getEtiquetesFinals()[i] != null && 
				   command.getEtiquetesFinals()[i] < command.getEtiquetesInicials()[i]){
					errors.rejectValue("etiquetesInicials["+i+"]", "error.etiquetesLot.overflow.major", "El rang és major del permès");
					errors.rejectValue("etiquetesFinals["+i+"]", "error.etiquetesLot.overflow.menor", "El rang és menor del permès");
				}
				
				if(command.getEtiquetesInicials()[i] != null && et.getEtiquetaInici() > command.getEtiquetesInicials()[i]){
					errors.rejectValue("etiquetesInicials["+i+"]", "error.etiquetesLot.overflow.menor", "El rang és menor del permès");
				}
				
				if(command.getEtiquetesFinals()[i] != null && et.getEtiquetaFi() < command.getEtiquetesFinals()[i]){
					errors.rejectValue("etiquetesFinals["+i+"]", "error.etiquetesLot.overflow.major", "El rang és major del permès");
				}
				
				if(command.getEtiquetesFinals()[i] != null && command.getEtiquetesInicials()[i] == null){
					errors.rejectValue("etiquetesInicials["+i+"]", "error.buit", "El camp no pot estar buit");
				}
				
				if(command.getEtiquetesFinals()[i] == null && command.getEtiquetesInicials()[i] != null ){
					errors.rejectValue("etiquetesFinals["+i+"]", "error.buit", "El camp no pot estar buit");
				}
			}
			
			//COMPROVEM DATA EXECUCIO
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(!oliInfraestructuraEjb.esDataLotCorrecte(command.getData(), command.getId())){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
			
			//Comprovació campanya
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
//			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(command.getData())){
//				errors.rejectValue(
//						"data",
//						"error.dataExecucio.posterior",
//						"Hi ha processos posteriors a la data introduida");
//			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
