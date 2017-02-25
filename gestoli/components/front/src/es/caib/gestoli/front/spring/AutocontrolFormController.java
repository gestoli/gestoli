package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.AutocontrolOliva;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;

/**
 * <p>
 * Controlador para las acciones de dar de alta o editar un registro de tipos de
 * información.
 * </p>
 * <p>
 * Los parámetros de la petición HTTP relevantes para este controlador son:
 * <ul>
 * <li><code>id</code> - Identificador del registro. Este parámetro es el que
 * determina si se ha de mostrar la página de creación o la página de edición.</li>
 * </ul>
 * </p>
 * <p>
 * No hay información para la vista:
 * <p>
 * Este controlador distingue entre las peticiones del tipo GET y las de tipo
 * POST. Si la petición es de tipo POST se ejecuta la acción de creación o de
 * edición. Si la petición es de tipo GET solo se muestra la página.
 * 
 * 
 */
public class AutocontrolFormController extends SimpleFormController {
	private static Logger logger = Logger
			.getLogger(AutocontrolFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private String seleccioSessionKeyOrigen;
	private String establimentSessionKey;
	private String stringAutocontrol;

	private HibernateTemplate hibernateTemplate;
    
	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo se ejecuta
	 * esta función en el caso de que se haya ejecutado la validación sin
	 * producir ningún error.
	 * 
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException {

		AutocontrolCommand autocontrolCommand = (AutocontrolCommand) command;
		Establiment establiment = null;
		
		
//		Map myModel = new HashMap();

		if (isCreate(request)) {
			try {
				
				
//				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				Lot lot = oliInfraestructuraEjb.lotAmbId(autocontrolCommand.getIdLot());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				establiment = oliInfraestructuraEjb.establimentAmbId(autocontrolCommand.getIdEstabliment());
				Bota bota = oliInfraestructuraEjb.botaAmbId(autocontrolCommand.getIdBota());
				
				AutocontrolOliva autocontrol = new AutocontrolOliva(
						autocontrolCommand.getData(),
						autocontrolCommand.getHora(),
						null,
						autocontrolCommand.getResponsable(),
						establiment,
						null,
						bota.getId(),
						autocontrolCommand.getColorGroc() == null ? false : true,
						autocontrolCommand.getColorVerdGroc() == null ? false : true,
						autocontrolCommand.getColorVerd() == null ? false : true,
						autocontrolCommand.getColorMarro() == null ? false : true,
						autocontrolCommand.getColorMarroObscur() == null ? false : true,
						autocontrolCommand.getColorNegre() == null ? false : true,
						autocontrolCommand.getColorAltres(),
						autocontrolCommand.getOKcolorCorrecte() == null ? false : true,
						autocontrolCommand.getAromaVegetal() == null ? false : true,
						autocontrolCommand.getAromaMineral() == null ? false : true,
						autocontrolCommand.getAromaFonoll() == null ? false : true,
						autocontrolCommand.getAromaPebre() == null ? false : true,
						autocontrolCommand.getAromaTerra() == null ? false : true,
						autocontrolCommand.getAromaAltres(),
						autocontrolCommand.getOKaromaCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaFermesa1() == null ? false : true,
						autocontrolCommand.getTexturaFermesa2() == null ? false : true,
						autocontrolCommand.getTexturaFermesa3() == null ? false : true,
						autocontrolCommand.getTexturaFermesa4() == null ? false : true,
						autocontrolCommand.getTexturaFermesa5() == null ? false : true,
						autocontrolCommand.getOKtexturaFermesaCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaDeformabilitat1() == null ? false : true,
						autocontrolCommand.getTexturaDeformabilitat2() == null ? false : true,
						autocontrolCommand.getTexturaDeformabilitat3() == null ? false : true,
						autocontrolCommand.getTexturaDeformabilitat4() == null ? false : true,
						autocontrolCommand.getTexturaDeformabilitat5() == null ? false : true,
						autocontrolCommand.getOKtexturaDeformabilitatCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaElasticitat1() == null ? false : true,
						autocontrolCommand.getTexturaElasticitat2() == null ? false : true,
						autocontrolCommand.getTexturaElasticitat3() == null ? false : true,
						autocontrolCommand.getTexturaElasticitat4() == null ? false : true,
						autocontrolCommand.getTexturaElasticitat5() == null ? false : true,
						autocontrolCommand.getOKtexturaElasticitatCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaSuavitat1() == null ? false : true,
						autocontrolCommand.getTexturaSuavitat2() == null ? false : true,
						autocontrolCommand.getTexturaSuavitat3() == null ? false : true,
						autocontrolCommand.getTexturaSuavitat4() == null ? false : true,
						autocontrolCommand.getTexturaSuavitat5() == null ? false : true,
						autocontrolCommand.getOKtexturaSuavitatCorrecte() == null ? false : true,
						autocontrolCommand.getTexturabocaFermesaEnBoca1() == null ? false : true,
						autocontrolCommand.getTexturabocaFermesaEnBoca2() == null ? false : true,
						autocontrolCommand.getTexturabocaFermesaEnBoca3() == null ? false : true,
						autocontrolCommand.getTexturabocaFermesaEnBoca4() == null ? false : true,
						autocontrolCommand.getTexturabocaFermesaEnBoca5() == null ? false : true,
						autocontrolCommand.getOKtexturabocaFermesaEnBocaCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaFriabilitat1() == null ? false : true,
						autocontrolCommand.getTexturaFriabilitat2() == null ? false : true,
						autocontrolCommand.getTexturaFriabilitat3() == null ? false : true,
						autocontrolCommand.getTexturaFriabilitat4() == null ? false : true,
						autocontrolCommand.getTexturaFriabilitat5() == null ? false : true,
						autocontrolCommand.getOKtexturaFriabilitatCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaCohesio1() == null ? false : true,
						autocontrolCommand.getTexturaCohesio2() == null ? false : true,
						autocontrolCommand.getTexturaCohesio3() == null ? false : true,
						autocontrolCommand.getTexturaCohesio4() == null ? false : true,
						autocontrolCommand.getTexturaCohesio5() == null ? false : true,
						autocontrolCommand.getOKtexturaCohesioCorrecte() == null ? false : true,
						autocontrolCommand.getTexturaUntuositat1() == null ? false : true,
						autocontrolCommand.getTexturaUntuositat2() == null ? false : true,
						autocontrolCommand.getTexturaUntuositat3() == null ? false : true,
						autocontrolCommand.getTexturaUntuositat4() == null ? false : true,
						autocontrolCommand.getTexturaUntuositat5() == null ? false : true,
						autocontrolCommand.getOKtexturaUntuositatCorrecte() == null ? false : true,
						autocontrolCommand.getSaborAcid() == null ? false : true,
						autocontrolCommand.getSaborSalat() == null ? false : true,
						autocontrolCommand.getSaborAmarg() == null ? false : true,
						autocontrolCommand.getSaborAltres(),
						autocontrolCommand.getOKsaborCorrecte() == null ? false : true,
						autocontrolCommand.getSensacioAstringent() == null ? false : true,
						autocontrolCommand.getSensacioPicant() == null ? false : true,
						autocontrolCommand.getSensacioAltres(),
						autocontrolCommand.getOKsensacioCorrecte() == null ? false : true,
						autocontrolCommand.getRegustBaix() == null ? false : true,
						autocontrolCommand.getRegustMitja() == null ? false : true,
						autocontrolCommand.getRegustProlongat() == null ? false : true,
						autocontrolCommand.getOKregustCorrecte() == null ? false : true,
						autocontrolCommand.getApta() == null ? false : true,
						autocontrolCommand.getMesuresCorrectores(),
						autocontrolCommand.getVerificacioMesuresCorrectores());
				
				logger.info("Creant el registre: " + autocontrol);
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.autocontrolCrear(autocontrol, stringAutocontrol);
				
				
				
				ControllerUtils.saveMessageInfo(request, missatge("autocontrol.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el analitica", ex);
				ControllerUtils.saveMessageError(request,
						missatge("autocontrol.missatge.crear.no"));
			}
		}

		String desti = "EstablimentPrincipal.html?establimentId=" + establiment.getId();
		return new ModelAndView("redirect:" + desti);

	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar el formulario
	 * de inserción (LOVs y cosas de esas) si no hay.
	 * 
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();

//		HttpSession session = request.getSession();
		String consulta = request.getParameter("consulta");
		String traza = request.getParameter("traza");
		String establimentId = request.getParameter("establimentId");
		
		Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		if (establimentId != null && !"".equals(establimentId)) establiment = oliInfraestructuraEjb.establimentAmbId(new Long(establimentId));

		AutocontrolCommand autocontrol = (AutocontrolCommand) command;

		Collection origenList = (Collection) request.getSession().getAttribute(seleccioSessionKeyOrigen);

		Collection origen = null;
		//Lot lot = null;
		Bota bota = null;
		if (origenList!= null && origenList.size() != 0) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			origen = oliInfraestructuraEjb.botesInfo(origenList.toArray());
			Iterator it = origen.iterator();
			if (it.hasNext())
				bota = (Bota) it.next();
		}		
		request.getSession().setAttribute("bota", bota);
		request.getSession().setAttribute("nomBota", bota.getIdentificador());
		request.getSession().setAttribute("tipusOliva", bota.getTipusOlivaTaula());
		if(establiment!= null){
			request.getSession().setAttribute("establiment", establiment);
		}		
		if (traza != null && traza.equalsIgnoreCase("t")) {
			myModel.put("traza", "t");
		}
		
		
		if (consulta != null && consulta.equalsIgnoreCase("t")) { //CONSULTA D'ANALITICA
			myModel.put("formData", autocontrol);
			myModel.put("path", "consultar_autocontrol");
		} else { //CREACIO D'AUTOCONTROL
			myModel.put("path", "introducir_autocontrol");
			if(establiment!= null && establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
				if(bota!= null && bota.getIdentificador()!= null && !bota.getIdentificador().equals("")){
					myModel.put("path_extension1",establiment.getNom()+" - "+ bota.getIdentificador());
				}
				
			}
			
		}
    	
		
		return myModel;
	}

	/**
	 * En caso de que sea una edición retorna el objeto rellenado con los datos
	 * actuales del registro. En caso de que sea una creación retorna el objeto
	 * vacío.
	 * 
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		AutocontrolCommand autocontrol = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				AutocontrolOliva a = oliInfraestructuraEjb.autocontrolAmbId(codi);
				autocontrol = new AutocontrolCommand();
				autocontrol.fromAutocontrolOliva(a);	
				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			autocontrol = new AutocontrolCommand();
		}
		return autocontrol;
	}

	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i> con
	 * tipos que no sean <i>String</i> se ha de configurar su correspondiente
	 * binder aquí.
	 * 
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(
    			Integer.class,
        		new CustomNumberEditor(Integer.class, true));
    	binder.registerCustomEditor(
        		Long.class,
        		new CustomNumberEditor(Long.class, true));
    	binder.registerCustomEditor(
        		Float.class,
        		new CustomNumberEditor(Float.class, true));
    	binder.registerCustomEditor(
        		Boolean.class,
        		new CustomBooleanEditor("S", "N", true));
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	/**
	 * Indica si la petición es de creación o no.
	 * 
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * 
	 * @param informacioEjb
	 *            La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(
			OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave) {
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}


	public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
		this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
	}

	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	public void setStringAutocontrol(String stringAutocontrol) {
		this.stringAutocontrol = stringAutocontrol;
	}
	

	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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