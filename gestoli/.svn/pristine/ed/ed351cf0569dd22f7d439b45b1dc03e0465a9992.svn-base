package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * Classe delegada encarregada de gestionar els llistats.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class VarietatsExperimentalsDelegate implements ApplicationContextAware {

	private static Logger logger = Logger.getLogger(VarietatsExperimentalsDelegate.class);
	
	private String listView;
    private String deleteView;
	private String rolDoGestor;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Llistat amb els registres de la taula de varietats experimentals.
	 */
	public ModelAndView list(
			HttpServletRequest request,
			HttpServletResponse response) {
		
		Map myModel = new HashMap();
		
		try {
			if (request.isUserInRole(rolDoGestor)) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection llistat = oliInfraestructuraEjb.varietatOlivaExperimental();
				myModel.put("llistat", llistat);
				myModel.put("path", "llista_varietats_experimentals");
			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de varietats experimentals", ex);
			ControllerUtils.saveMessageError(request, missatge("varietats.experimentals.missatge.llistat.no", request));
		}
		
		return new ModelAndView(listView, myModel);
	}

	/**
	 * Esborra un registre i torna el llistat actualitzat.
	 */
	public ModelAndView delete(
			HttpServletRequest request,
			HttpServletResponse response) {
		
		Map myModel = new HashMap();
		String id = request.getParameter("id");
		if ((id != null) && (!id.equals(""))) {
			try {
				Long lid = new Long(Long.parseLong(id));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.varietatOlivaExperimentalEsborrar(lid);
				ControllerUtils.saveMessageInfo(request, missatge("varietats.experimentals.missatge.eliminat.ok", request));
			} catch (Exception ex) {
				logger.error("Error esborrant la varietat experimental [" + id + "]", ex);
				ControllerUtils.saveMessageError(request, missatge("varietats.experimentals.missatge.eliminat.no", request));
			}
		}
		
		return new ModelAndView(deleteView, myModel);
	}
	
	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setDeleteView(String deleteView) {
		this.deleteView = deleteView;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	private String missatge(String clave, HttpServletRequest request) {
		String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
		return valor;
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
