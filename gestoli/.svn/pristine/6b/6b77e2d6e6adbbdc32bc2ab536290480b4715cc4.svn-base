package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.SortidaOrujo;

/**
 * Controlador per donar d'alta o editar les sortides d'orujo.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class SortidaOrujoFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(SortidaOrujoFormController.class);
	
	private String rolProductor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;
	
	/**
	 * Es crida quan s'accepten les modificacions de l'objecte.
	 * Només s'executa aquesta funció si la validació no ha tornat errors.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws ServletException {
	
		SortidaOrujoCommand sortidaOrujoCommand = (SortidaOrujoCommand)command;
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment establiment = (Establiment)session.getAttribute(establimentSessionKey);
		
		String id = request.getParameter("id");
		if ((id != null) && (!id.equals(""))) {
			// Modificam una sortida d'orujo existent.
			try {
				Long lid = Long.parseLong(id);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				SortidaOrujo sortidaOrujo = oliInfraestructuraEjb.sortidaOrujoAmbId(lid);
				sortidaOrujo.setId(sortidaOrujoCommand.getId());
				sortidaOrujo.setData(sortidaOrujoCommand.getData());
				sortidaOrujo.setKilos(sortidaOrujoCommand.getKilos());
				sortidaOrujo.setAlbara(sortidaOrujoCommand.getAlbara());
				sortidaOrujo.setDesti(sortidaOrujoCommand.getDesti());
				sortidaOrujo.setDocument(sortidaOrujoCommand.getDocument());
				sortidaOrujo.setEstabliment(establiment);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.sortidaOrujoModificar(sortidaOrujo);
				ControllerUtils.saveMessageInfo(request, missatge("sortida.orujo.missatge.modificar.ok"));
			} catch (Exception ex) {
				logger.error("Error modificant la finca ", ex);
				ControllerUtils.saveMessageError(request, missatge("sortida.orujo.missatge.modificar.no"));
			}
		} else {
			// Cream una nova sortida d'orujo.
			try {
				SortidaOrujo sortidaOrujo = new SortidaOrujo();
				sortidaOrujo.setData(sortidaOrujoCommand.getData());
				sortidaOrujo.setKilos(sortidaOrujoCommand.getKilos());
				sortidaOrujo.setAlbara(sortidaOrujoCommand.getAlbara());
				sortidaOrujo.setDesti(sortidaOrujoCommand.getDesti());
				sortidaOrujo.setDocument(sortidaOrujoCommand.getDocument());
				sortidaOrujo.setEstabliment(establiment);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.sortidaOrujoCrear(sortidaOrujo);
	        	ControllerUtils.saveMessageInfo(request, missatge("sortida.orujo.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant la sortida d'orujo", ex);
				ControllerUtils.saveMessageError(request, missatge("sortida.orujo.missatge.crear.no"));
			}
		}
		
		String forward = getSuccessView();
		return new ModelAndView(forward, myModel);
	}

	/**
	 * Retorna totes es dades del model per mostrar el formulari d'inserció.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
	
		Map myModel = new HashMap();
		
		if (request.getAttribute("idAlEntrar") == null) {
			if (request.getParameter("id") != null) {
				request.setAttribute("idAlEntrar", Boolean.valueOf(true));
				myModel.put("path", "modificar_sortida_orujo");
			} else {
				request.setAttribute("idAlEntrar", Boolean.valueOf(false));
				myModel.put("path", "crear_sortida_orujo");
			}
		}
		
		return myModel;
	}

	/**
	 * Si és una edició es retorna l'objecte emplenat.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		SortidaOrujoCommand sortidaOrujoCommand = null;
		String id = request.getParameter("id");
		
		if ((!isFormSubmission(request)) && ((id != null) && (!id.equals("")))) {
			try {
				SortidaOrujo sortidaOrujo = null;
				if ((id != null) && (!id.equals(""))) {
					Long lid = new Long(Long.parseLong(id));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					sortidaOrujo = oliInfraestructuraEjb.sortidaOrujoAmbId(lid);
				}
				sortidaOrujoCommand = new SortidaOrujoCommand();
				sortidaOrujoCommand.fromSortidaOrujo(sortidaOrujo);
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			sortidaOrujoCommand = new SortidaOrujoCommand();
		}

		return sortidaOrujoCommand;
	}
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	binder.registerCustomEditor(
    			Date.class,
    			new CustomDateEditor(sdf, true));
    	binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
	}

	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}
	
	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * Injecció de la dependència rolProductor
	 * @param rolProductor La classe a injectar.
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
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
	
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
}
