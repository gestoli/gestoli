package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;

/**
 * Classe delegada per mostrar el llistat de sortides d'orujo.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class SortidaOrujoDelegate implements ApplicationContextAware {

	private static Logger logger = Logger.getLogger(SortidaOrujoDelegate.class);
	
	private String listView;
	private String rolProductor;
	private String establimentSessionKey;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Retorna una llista amb el contingut de la taula.
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		Map myModel = new HashMap();
		Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
		try {
			if (request.isUserInRole(rolProductor) && establiment != null) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				Collection llistat = oliInfraestructuraEjb.sortidesOrujoValides(true);sortidesOrujoAmbEstabliment
				Collection llistat = oliInfraestructuraEjb.sortidesOrujoAmbEstabliment(establiment.getId(), true);
				myModel.put("llistat", llistat);
				myModel.put("path", "llista_sortida_orujo");
			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de sortides d'orujo", ex);
			ControllerUtils.saveMessageError(request, missatge("sortida.orujo.missatge.llistat.no", request));
		}
		return new ModelAndView(listView, myModel);
	}

	/**
	 * Inyección de la dependencia listView
	 * 
	 * @param listView la cadena a inyectar.
	 */
	public void setListView(String listView) {
		this.listView = listView;
	}

	/**
	 * Inyección de la dependencia oliInfraestructuraEjb
	 * 
	 * @param oliInfraestructuraEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * Injecció de la dependència rolProductor
	 * 
	 * @param rolProductor La classe a injectar.
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}

	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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
	 * 
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * get the hibernate template.
	 * 
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}
}
