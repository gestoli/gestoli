/**
 * DipositValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.MaterialDiposit;
import es.caib.gestoli.logic.model.Zona;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 */
public class DipositValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(DipositValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;



	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(DipositCommand.class);
	}

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	public void validate(Object obj, Errors errors) {
		
		DipositCommand diposit = (DipositCommand) obj;
		
		//Mirem si el codi està duplicat
		try {
			
			List dipositsEstabliment = oliInfraestructuraEjb.findDipositsByEstabliment(diposit.getIdEstabliment());
			
			for(Object o: dipositsEstabliment){
				Diposit dipEst = (Diposit)o;

				if(diposit.getCodiAssignat().equalsIgnoreCase(dipEst.getCodiAssignat()) && 
				   (diposit.getId() == null || !diposit.getId().equals(dipEst.getId())) ){
					errors.rejectValue(
						"codiAssignat",
						"error.diposit.duplicat",
						"El codi del dipòsit ja existeix");
				}
				
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		
		//Validam que el diposit no estigui a un trasllat
		try {
			if(oliInfraestructuraEjb.existeixenDipositsTraslladatsByEstabliment(diposit.getIdEstabliment(), diposit.getCodiAssignat())){
				errors.rejectValue(
					"codiAssignat",
					"error.diposit.duplicatTrasllat",
					"El codi del dipòsit ja existeix a un altre contenidor traslladat");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		if (diposit.getFictici()== null ||(diposit.getFictici()!= null && !diposit.getFictici().booleanValue())) {
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"capacitat",
					"error.capacitat.buit",
			"El camp Capacitat no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"idMaterialDiposit",
					"error.material.buit",
			"El camp Material no pot estar buit");
		} else {
			try {
				if (diposit.getIdZona() != null){
					Zona zona = oliInfraestructuraEjb.zonaAmbId(diposit.getIdZona());
					if (zona != null && zona.getFictici() != null && !zona.getFictici().booleanValue()) {
						errors.rejectValue("idZona", "error.zona.noFicticia", "Si el dipòsit es fictici s'ha de situar a una zona fictícia");
					}
				}
			} catch (Exception ex) {
				logger.error("EXCEPTION", ex);
			}
		}
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"idZona",
				"error.zona.buit",
		"El camp Zona no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"codiAssignat",
				"error.codigo.buit",
		"El camp Codigo no pot estar buit");


		

		if (diposit.getIdMaterialDiposit() != null) {
			MaterialDiposit material = new MaterialDiposit();
			material.setId(diposit.getIdMaterialDiposit());
			diposit.setMaterialDiposit(material);
		}
		if (diposit.getIdZona() != null){
			Zona zona = new Zona();
			zona.setId(diposit.getIdZona());
			diposit.setZona(zona);
		}
		
		if (diposit.getCapacitat() != null && diposit.getCapacitat().doubleValue() <= 0) {
			errors.rejectValue("capacitat", "error.capacitat.buit", "El camp Capacitat no pot estar buit");
		}
		
		
		try {
			if (diposit.getCapacitat() != null) {
				if (diposit.getId() != null) {
					Diposit dip = oliInfraestructuraEjb.dipositAmbId(diposit.getId());
					if (dip != null && dip.getVolumActual() != null && dip.getVolumActual().doubleValue() > diposit.getCapacitat().doubleValue()) {
						errors.rejectValue("capacitat", "error.capacitat.incorrecte", "La modificació de la capacitat no pot ser inferior al volum que hi ha actualment");
					}
				}
			}
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
		}
		// TODO: validar que si se pasa de activo a no activo, solo sera posible si el deposito
		//       no contiene aceite.
		
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

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	
}
