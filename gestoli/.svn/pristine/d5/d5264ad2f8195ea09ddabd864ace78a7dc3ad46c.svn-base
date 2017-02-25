package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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

public class PartidaOliDelegate implements ApplicationContextAware {

	private final Logger logger = Logger.getLogger(getClass());
	private String establimentSessionKey;
	private String listView;
    private String deleteView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * FunciÃƒÂ³ cridada pel mÃƒÂ¨tode GET del formulari PartidaOli.html
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
        	Collection llistat = oliInfraestructuraEjb.findPartidesOliByEstabliment(idEstabliment);
			myModel.put("llistat", llistat);
			myModel.put("path", "lista_partidesOli");
		} catch (Exception ex) {
			logger.error("Error obtenint el llistat de partides d'oli", ex);
			ControllerUtils.saveMessageError(request, missatge("partidaOli.llistat.no", request));
		}
		return new ModelAndView(listView, myModel);
	}

	/**
	 * FunciÃƒÂ³ cridada pel mÃƒÂ¨tode POST del formulari PartidaOliDelete.html
	 * Elimina Partida d'oli.
	 * 
	 * @param request
	 * @param command
	 * @param myModel
	 * @return
	 * @throws ServletException
	 */
	 public ModelAndView delete(
	            HttpServletRequest request,
	            HttpServletResponse response) {
	    Map myModel = new HashMap();
		String id = request.getParameter("id");
		try {
			if (id != null && !"".equals(id)) {
				 Long lid = new Long(Long.parseLong(id));
                 oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                 if (oliInfraestructuraEjb.existeixenEntradesDipositsAssociatsPartidesOli(lid)) {
                 	ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no.entrades.diposit", request));
                 } else if (oliInfraestructuraEjb.existeixenElaboracionsAssociadesPartidesOli(lid)) {
                 	ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no.elaboracio", request));
                 } else if (oliInfraestructuraEjb.existeixenSortidesDipositsAssociatsPartidesOli(lid)) {
                 	ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no.sortides.diposit", request));
                 } else if (oliInfraestructuraEjb.existeixenLotsAssociatsPartidesOli(lid)) {
                 	ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no.lot", request));
                 } else if (oliInfraestructuraEjb.existeixenDipositsAssociatsPartidesOli(lid)) {
                 	ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no.diposit", request));
                 } else {
                 	logger.info("Eliminant la partidaOli [" + lid + "]");
                 	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                 	oliInfraestructuraEjb.partidaOliEsborrar(lid);
                 	ControllerUtils.saveMessageInfo(request, missatge("partidaOli.missatge.esborrar.ok", request));
                 }
			}
		} catch (Exception ex) {
			logger.error("Error eliminant la partida d'oli", ex);
			ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.esborrar.no", request));
		}
		return new ModelAndView(deleteView, myModel);
	}
	
	 /**
		 * Funció cridada pel mètode POST del formulari PartidaOliDelete.html
		 * Elimina Partida d'oli.
		 * 
		 * @param request
		 * @param command
		 * @param myModel
		 * @return
		 * @throws ServletException
		 */
		 public ModelAndView desactivar(
		            HttpServletRequest request,
		            HttpServletResponse response) {
		    Map myModel = new HashMap();
		    
		    Enumeration params = request.getParameterNames();
			List<Long> partidesOli = new ArrayList<Long>();
			List<Long> partidesOliOcult = new ArrayList<Long>();
			
			while (params.hasMoreElements()){
				String nomParam=(String)params.nextElement();
				String splitParam[]=nomParam.split("_");
				if (splitParam.length==2){
					if (splitParam[0].equals("partidaOliDesactivar")){
						partidesOli.add(Long.parseLong(splitParam[1]));
					}
				}
			}
			
			params = request.getParameterNames();
			
			while (params.hasMoreElements()){
				String nomParam=(String)params.nextElement();
				String splitParam[]=nomParam.split("_");
				if (splitParam.length==2){
					if (splitParam[0].equals("partidaOliOcultar")){
						partidesOliOcult.add(Long.parseLong(splitParam[1]));
					}
				}
			}
		    
			try {
				if (partidesOli.size() > 0){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.desactivaPartidesOli(partidesOli);
				}
				if (partidesOliOcult.size() > 0){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.ocultaPartidesOli(partidesOliOcult);
				}
			} catch (Exception ex) {
				logger.error("Error eliminant la partida d'oli", ex);
				ControllerUtils.saveMessageError(request, missatge("partidaOli.missatge.modificar.no", request));
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
