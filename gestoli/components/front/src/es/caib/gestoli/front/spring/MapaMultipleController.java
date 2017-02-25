package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Plantacio;

public class MapaMultipleController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(MapaMultipleController.class);
	
	private OliFrontOfficeEjb oliFrontOfficeEjb;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		Map myModel = new HashMap();

		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
		String idsPlantacions = request.getParameter("idsPlantacions");
		
		String[] arrayIdsPlantacions = idsPlantacions.split(",");
		
		Object[] arrayPlantacions = new Object[arrayIdsPlantacions.length];
		int i = 0;
		for (String id : arrayIdsPlantacions) {
			Plantacio plantacio = oliFrontOfficeEjb.plantacioAmbId(Long.valueOf(id));
			arrayPlantacions[i] = plantacio;
			i++;
		}
		
		myModel.put("plantacions", arrayPlantacions);
		myModel.put("tam", arrayPlantacions.length);

		return myModel;
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


	public void setOliFrontOfficeEjb(OliFrontOfficeEjb oliFrontOfficeEjb) {
		this.oliFrontOfficeEjb = oliFrontOfficeEjb;
	}

}
