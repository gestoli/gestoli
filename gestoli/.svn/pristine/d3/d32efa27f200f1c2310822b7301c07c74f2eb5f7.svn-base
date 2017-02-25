package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;

public class ModificarDocumentSortidaDelegate {

	private static Logger logger = Logger.getLogger(ModificarDocumentSortidaDelegate.class);
	
	private String listView;
	private String rolProductor;
	private String rolEnvasador;
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
			if ((request.isUserInRole(rolProductor) || request.isUserInRole(rolEnvasador)) && establiment != null) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				Collection diposits = oliInfraestructuraEjb.sortidesDiposits();
				Collection diposits = oliInfraestructuraEjb.sortidesDipositsPerEstabliment(establiment.getId());
				myModel.put("diposits", diposits);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				Collection lots = oliInfraestructuraEjb.sortidesLots();
				Collection lots = oliInfraestructuraEjb.sortidesLotsPerEstabliment(establiment.getId());
				myModel.put("lots", lots);
				
				myModel.put("path", "modificar_llista_sortides");
			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistats de sortides ", ex);
			ControllerUtils.saveMessageError(request, missatge("sortides.missatge.llistat.no", request));
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

	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}

	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
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
