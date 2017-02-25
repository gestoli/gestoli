package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;

public class ProducteDelegate implements ApplicationContextAware {

	private final Logger logger = Logger.getLogger(getClass());
	private String establimentSessionKey;
	private String listView;
    private String deleteView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Funció cridada pel mètode GET del formulari Producte.html
	 * Llistat de Partides d'oli.
	 * 
	 * @param request
	 * @param command
	 * @param myModel
	 * @return
	 * @throws ServletException
	 */
	 public ModelAndView list(
	            HttpServletRequest request,
	            HttpServletResponse response) {
		 Map myModel = new HashMap();
		 try {
			HttpSession session = request.getSession();
			Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
	   		Long idEstabliment =  est.getId();
	   		
	   		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistat = oliInfraestructuraEjb.producteAmbEstabliment(idEstabliment);
			myModel.put("llistat", llistat);
			myModel.put("path", "lista_productes");
		} catch (Exception ex) {
			logger.error("Error obtenint el llistat de productes", ex);
			ControllerUtils.saveMessageError(request, missatge("productes.llistat.no", request));
		}
		return new ModelAndView(listView, myModel);
	}
	 
	 public ModelAndView delete(
	            HttpServletRequest request,
	            HttpServletResponse response) {
	    Map myModel = new HashMap();
		String id = request.getParameter("id");
		try {
			if (id != null && !"".equals(id)) {
				Long lid = new Long(Long.parseLong(id));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if (oliInfraestructuraEjb.existeixenLotsAssociatsProducte(lid)) {
					ControllerUtils.saveMessageError(request, missatge("producte.missatge.esborrar.no.lots", request));
				} else {
					logger.info("Eliminant el producte [" + lid + "]");
					oliInfraestructuraEjb.producteEsborrar(lid);
					ControllerUtils.saveMessageInfo(request, missatge("producte.missatge.esborrar.ok", request));
				}
			}
		} catch (Exception ex) {
			logger.error("Error eliminant la partida d'oli", ex);
			ControllerUtils.saveMessageError(request, missatge("producte.missatge.esborrar.no", request));
		}
		return new ModelAndView(deleteView, myModel);
	}

	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
    public void setListView(String listView) {
        this.listView = listView;
    }

    public void setDeleteView(String deleteView) {
		this.deleteView = deleteView;
	}

	public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }
}
