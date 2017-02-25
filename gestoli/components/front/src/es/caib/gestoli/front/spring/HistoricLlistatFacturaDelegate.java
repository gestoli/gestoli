package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

public class HistoricLlistatFacturaDelegate {

	private static Logger logger = Logger.getLogger(HistoricLlistatFacturaDelegate.class);

	private String listView;
	private String rolDoGestor;
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
		try {
			if (request.isUserInRole(rolDoGestor)) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection llistat = oliInfraestructuraEjb.historicsLlistatsFactures();
				for (Iterator it = llistat.iterator(); it.hasNext();){
					Object[] fact = (Object[])it.next();
					String oliv = (String)fact[4];
					if (oliv != null){
						fact[4] = oliv.substring(0, oliv.indexOf("\n"));
					}
				}
				myModel.put("llistat", llistat);
				myModel.put("path", "llista_historic_factura");
			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistat d'històrics de llistats de factures", ex);
			ControllerUtils.saveMessageError(request, missatge("historic.llistat.factures.missatge.llistat.no", request));
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
	 * Injecció de la dependència rolDoGestor
	 * 
	 * @param rolDoGestor La classe a injectar.
	 */
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
