package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import es.caib.gestoli.logic.interfaces.OliInitDBEjb;

/**

 */
public class InitDBController implements Controller {
	private static Logger logger = Logger.getLogger(InitDBController.class);

	/*
	 * Servicio para acceder a las funcionalidades de la aplicación.
	 */
	private OliInitDBEjb oliInitDBEjb;

	private HibernateTemplate hibernateTemplate;


	/**
	 * 
	 * @see Controller#handleRequest(HttpServletRequest, HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			oliInitDBEjb.setHibernateTemplate(getHibernateTemplate());
//			String temporal = request.getParameter("temporal");
//			if (temporal != null && !temporal.equals("")) {
//				if (temporal.equals("1")) {
//					oliInitDBEjb.initDBTemporal1();
//				}
//			} else {
//				oliInitDBEjb.initDB();
//			}
			oliInitDBEjb.initDB(response);
			logger.debug("handleRequest successful");
		} catch (RemoteException ex) {
			logger.error("handleRequest failed", ex);
			throw new ServletException(ex);
		}
		return null;
	}


	/**
	 * Inyección de la dependencia oliInitDBEjb
	 *
	 * @param oliInitDBEjb La clase a inyectar.
	 */
	public void setOliInitDBEjb(OliInitDBEjb oliInitDBEjb) {
		this.oliInitDBEjb = oliInitDBEjb;
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
