package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.VarietatOliva;;

/**
 * Controlador per donar d'alta o editar les varietats experimentals.
 * 
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class VarietatsExperimentalsFormController extends SimpleFormController {
	
	private static Logger logger = Logger.getLogger(VarietatsExperimentalsFormController.class);
	
	private String rolDoGestor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
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
	
		VarietatsExperimentalsCommand varietatOlivaExperimentalCommand = (VarietatsExperimentalsCommand)command;
		Map myModel = new HashMap();
		
		String id = request.getParameter("id");
		Integer vExpId = 0;
		String nom = "Experimental";
		Boolean autoritzada = false;
		Long icona = 10L;
		String color = "968B3A";
		Boolean experimental = true;
		
		if ((id != null) && (!id.equals(""))) {
			// Modificam una varietat experimental existent.
			try {
				vExpId = Integer.parseInt(id);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				VarietatOliva varietatOlivaExperimental = oliInfraestructuraEjb.varietatOlivaExperimentalAmbId(vExpId.longValue());
				varietatOlivaExperimental.setNom(nom);
				varietatOlivaExperimental.setAutoritzada(autoritzada);
				varietatOlivaExperimental.setIcona(icona);
				varietatOlivaExperimental.setColor(color);
				varietatOlivaExperimental.setObservacions(varietatOlivaExperimentalCommand.getObservacions());
				varietatOlivaExperimental.setDescomposicioPlantacios(varietatOlivaExperimentalCommand.getDescomposicioPlantacios());
				varietatOlivaExperimental.setLots(varietatOlivaExperimentalCommand.getLots());
				varietatOlivaExperimental.setNomVarietat(varietatOlivaExperimentalCommand.getNomVarietat());
				varietatOlivaExperimental.setExperimental(experimental);
				varietatOlivaExperimental.setActiu(varietatOlivaExperimentalCommand.getActiu());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.varietatOlivaExperimentalModificar(varietatOlivaExperimental);
				ControllerUtils.saveMessageInfo(request, missatge("varietats.experimentals.modificar.ok"));
			} catch (Exception ex) {
				logger.error("Error modificant la varietat experimental ", ex);
				ControllerUtils.saveMessageError(request, missatge("varietats.experimentals.modificar.no"));
			}
		} else {
			// Cream una nova varietat experimental.
			try {
				VarietatOliva varietatOlivaExperimental = new VarietatOliva();
				varietatOlivaExperimental.setNom(nom);
				varietatOlivaExperimental.setAutoritzada(autoritzada);
				varietatOlivaExperimental.setIcona(icona);
				varietatOlivaExperimental.setColor(color);
				varietatOlivaExperimental.setObservacions(varietatOlivaExperimentalCommand.getObservacions());
				varietatOlivaExperimental.setDescomposicioPlantacios(varietatOlivaExperimentalCommand.getDescomposicioPlantacios());
				varietatOlivaExperimental.setLots(varietatOlivaExperimentalCommand.getLots());
				varietatOlivaExperimental.setNomVarietat(varietatOlivaExperimentalCommand.getNomVarietat());
				varietatOlivaExperimental.setExperimental(experimental);
				varietatOlivaExperimental.setActiu(varietatOlivaExperimentalCommand.getActiu());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				vExpId = oliInfraestructuraEjb.varietatOlivaExperimentalCrear(varietatOlivaExperimental);
	        	ControllerUtils.saveMessageInfo(request, missatge("varietats.experimentals.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant la varietat experimental", ex);
				ControllerUtils.saveMessageError(request, missatge("varietats.experimentals.missatge.crear.no"));
			}
		}
		
		try {
			// Modificam el rendiment de la varietat.
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.modificarRendimentVarietat(
					null,
					vExpId,
					varietatOlivaExperimentalCommand.getRendiment(),
					varietatOlivaExperimentalCommand.getTipusRendimentCampanya());
		} catch (Exception ex) {
			logger.error("Error modificant el rendiment de la varietat experimental", ex);
		}
		
		String forward = getSuccessView()+ "?id=" + vExpId;
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
				myModel.put("path", "modificar_varietats_experimentals");
			} else {
				request.setAttribute("idAlEntrar", Boolean.valueOf(false));
				myModel.put("path", "crear_varietats_experimentals");
			}
		}
		
		Collection tipusRendiment = new ArrayList();
		HashMap<String, String> hectarea = new HashMap<String, String>();
		hectarea.put("value", "hectarea");
		hectarea.put("label", "Hectarea");
		tipusRendiment.add(hectarea);
		HashMap<String, String> arbre = new HashMap<String, String>();
		arbre.put("value", "arbre");
		arbre.put("label", "Arbre");
		tipusRendiment.add(arbre);
        myModel.put("tipusRendiment", tipusRendiment);
		
		return myModel;
	}

	/**
	 * Si és una edició es retorna l'objecte emplenat.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		VarietatsExperimentalsCommand varietatsExperimentalsCommand = null;
		String id = request.getParameter("id");
		
		if ((!isFormSubmission(request)) && ((id != null) && (!id.equals("")))) {
			try {
				VarietatOliva varietatOlivaExperimental = null;
				if ((id != null) && (!id.equals(""))) {
					Long vExpId = new Long(Long.parseLong(id));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					varietatOlivaExperimental = oliInfraestructuraEjb.varietatOlivaExperimentalAmbId(vExpId);
				}
				varietatsExperimentalsCommand = new VarietatsExperimentalsCommand();
				varietatsExperimentalsCommand.fromVarietatOlivaExperimental(varietatOlivaExperimental);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				String tipusRendimentCampanya = oliInfraestructuraEjb.obtenirTipusRendimentCampanyaVarietat(null, varietatOlivaExperimental.getId());
				varietatsExperimentalsCommand.setTipusRendimentCampanya(tipusRendimentCampanya);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Double rendimentCampanya = oliInfraestructuraEjb.obtenirRendimentCampanyaVarietat(null, varietatOlivaExperimental.getId());
				varietatsExperimentalsCommand.setRendiment(rendimentCampanya);
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			varietatsExperimentalsCommand = new VarietatsExperimentalsCommand();
		}

		return varietatsExperimentalsCommand;
	}
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
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
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
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
