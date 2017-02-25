package es.caib.gestoli.front.spring.qualitat;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliQualitatEjb;

/**
 * Validador pels camps associats amb el formulari de sortides d'orujo.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class QualitatDescripcioEquipValidator implements Validator {

	private static Logger logger = Logger.getLogger(QualitatDescripcioEquipValidator.class);
	
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	
	public boolean supports(Class clazz) {
		return clazz.equals(QualitatDescripcioEquipCommand.class);
	}

	public void validate(Object object, Errors errors) {
	}

	/**
	 * Injecció de la dependència oliQualitatEjb
	 * @param oliQualitatEjb La classe a injectar.
	 */
	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
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
