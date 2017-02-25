package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.AnaliticaParametreTipus;
import es.caib.gestoli.logic.model.AnaliticaValor;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.VarietatOli;
import es.caib.gestoli.logic.util.Constants;

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
public class AnaliticaFormController extends SimpleFormController {
	private static Logger logger = Logger
			.getLogger(AnaliticaFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private String seleccioSessionKeyOrigen;
	private String establimentSessionKey;
	private String stringAnalitica;

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

		AnaliticaCommand analiticaCommand = (AnaliticaCommand) command;
		Establiment establiment = null;
		
		Map myModel = new HashMap();

		if (isCreate(request)) {
			try {
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				VarietatOli varietat = oliInfraestructuraEjb.getVarietatOliById(analiticaCommand.getIdVarietatOli());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				PartidaOli partidaOli = oliInfraestructuraEjb.getPartidaOliById(analiticaCommand.getIdPartidaOli());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit diposit = oliInfraestructuraEjb.dipositAmbId(analiticaCommand.getIdDiposit());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				establiment = oliInfraestructuraEjb.establimentAmbId(analiticaCommand.getIdEstabliment());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				VarietatOli varietat1 = oliInfraestructuraEjb.getVarietatOliById(analiticaCommand.getIdVarietatOli1());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				VarietatOli varietat2 = oliInfraestructuraEjb.getVarietatOliById(analiticaCommand.getIdVarietatOli2());
				
				Analitica analitica = new Analitica(
						analiticaCommand.getData(), //Data
						diposit.getVolumActual(), //Litres analitzats
						analiticaCommand.getUsuari(), //Usuari
						varietat, //Varietat
						partidaOli, //Partida
						null, //Traza
						diposit, //Diposit
						establiment, //Establiment
						analiticaCommand.getAnalisiSensorialNomLaboratori(), //A. Sensorial Nom laboratori
						analiticaCommand.getAnalisiSensorialDataRecepcio(), //A. Sensorial Data recepció
						analiticaCommand.getAnalisiSensorialDataTast(), //A. Sensorial Data tast
						analiticaCommand.getAnalisiSensorialObservacions(), //A. Sensorial observacions
						analiticaCommand.getAnalisiFisicoQuimicNomLaboratori(), //A. Fisicoquímic Nom laboratori
						analiticaCommand.getAnalisiFisicoQuimicDataRecepcio(), //A. Fisicoquímic Data recepció
						analiticaCommand.getAnalisiFisicoQuimicDataInici(), //A. Fisicoquímic Data inici
						analiticaCommand.getAnalisiFisicoQuimicDataFi(), //A. Fisicoquímic Data fi
						//analiticaCommand.getAnalisiFisicoQuimicValid(), //A. Fisicoquímic Vàlid
						true, //Si creem l'analítica SEMPRE serà vàlida. A. Fisicoquímic Vàlid
						analiticaCommand.getAnalisiFisicoQuimicObservacions(), //A. Fisicoquímic Observacions
						analiticaCommand.getAnalisiFisicoQuimicAcidesa(), //A. Fisicoquimic acidesa
						varietat1, //Varietat Oli 1 - sensorial
						varietat2 //Varietat Oli 2 - fisicoquimic
				);
				
				//Ara ja no s'empra perquè l'analítica sempre serà vàlida
				/*if(analitica.getAnalisiFisicoQuimicValid() == null){
					analitica.setAnalisiFisicoQuimicValid(false);
				}*/

				
				logger.info("Creant el registre: " + analitica);
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.analiticaCrear(analitica, stringAnalitica);
				
				//Insertem els paràmetres SENSORIALS
				for(int i=0; i<analiticaCommand.getIdAnaliticaParametreTipusSensorial().length; i++){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					AnaliticaParametreTipus analiticaParametreTipus = oliInfraestructuraEjb.analiticaParametreTipusAmbId(analiticaCommand.getIdAnaliticaParametreTipusSensorial()[i]);
					
					AnaliticaValor analiticaValor = new AnaliticaValor(
							analiticaParametreTipus.getNom(),
							analiticaParametreTipus.getNomCast(),
							analiticaParametreTipus.getOrdre(),
							analiticaCommand.getAnaliticaParametreTipusSensorial()[i],
							analitica,
							analiticaParametreTipus.getTipus(),
							Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL
					);
					
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.analiticaValorCrear(analiticaValor);
				}
				
				//Insertem els paràmetres FISICOQUIMICS
				for(int i=0; i<analiticaCommand.getIdAnaliticaParametreTipusFisicoQuimic().length; i++){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					AnaliticaParametreTipus analiticaParametreTipus = oliInfraestructuraEjb.analiticaParametreTipusAmbId(analiticaCommand.getIdAnaliticaParametreTipusFisicoQuimic()[i]);
					
					AnaliticaValor analiticaValor = new AnaliticaValor(
							analiticaParametreTipus.getNom(),
							analiticaParametreTipus.getNomCast(),
							analiticaParametreTipus.getOrdre(),
							analiticaCommand.getAnaliticaParametreTipusFisicoQuimic()[i],
							analitica,
							analiticaParametreTipus.getTipus(),
							Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC
					);
					
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.analiticaValorCrear(analiticaValor);
				}
				
				ControllerUtils.saveMessageInfo(request, missatge("analitica.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el analitica", ex);
				ControllerUtils.saveMessageError(request,
						missatge("analitica.missatge.crear.no"));
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
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();

		HttpSession session = request.getSession();
		String consulta = request.getParameter("consulta");
		String traza = request.getParameter("traza");
		String establimentId = request.getParameter("establimentId");
		
		Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		if (establimentId != null && !"".equals(establimentId)) establiment = oliInfraestructuraEjb.establimentAmbId(new Long(establimentId));

		AnaliticaCommand analitica = (AnaliticaCommand) command;

		Collection origenList = (Collection) request.getSession().getAttribute(seleccioSessionKeyOrigen);

		Collection origen = null;
		Diposit diposit = null;
		if (origenList!= null && origenList.size() != 0) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
			Iterator it = origen.iterator();
			if (it.hasNext())
				diposit = (Diposit) it.next();
		}		
		request.getSession().setAttribute("diposit", diposit);
		if(establiment!= null){
			request.getSession().setAttribute("establiment", establiment);
		}		
		if (traza != null && traza.equalsIgnoreCase("t")) {
			myModel.put("traza", "t");
		}
		
		Collection partidesOli = null;
		
		if (consulta != null && consulta.equalsIgnoreCase("t")) { //CONSULTA D'ANALITICA
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			VarietatOli varOli = oliInfraestructuraEjb.getVarietatOliById((analitica.getVarietatOli().getId()));
			myModel.put("varietat", varOli.getNom());
			
			VarietatOli varOli2 = oliInfraestructuraEjb.getVarietatOliById((analitica.getVarietatOli2().getId()));
			myModel.put("varietat2", varOli2.getNom());
			
			//Paràmetres de l'analítica
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			List<AnaliticaValor> valorsParametres = (List<AnaliticaValor>)oliInfraestructuraEjb.analiticaValorByIdAnalitica(analitica.getId());
			List<AnaliticaParametreTipus> parametresSensorial = new ArrayList<AnaliticaParametreTipus>();
			List<AnaliticaParametreTipus> parametresFisicoQuimic = new ArrayList<AnaliticaParametreTipus>();
			
			for(AnaliticaValor analiticaValor: valorsParametres){
				AnaliticaParametreTipus a = new AnaliticaParametreTipus(
						analiticaValor.getNom(),
						analiticaValor.getOrdre(),
						analiticaValor.getTipus(),
						analiticaValor.getTipusControl(),
						true,
						analiticaValor.getNomCast()
						);
				
				if(a.getTipusControl() == Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL){
					parametresSensorial.add(a);
				} else if(a.getTipusControl() == Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC){
					parametresFisicoQuimic.add(a);
				}
			}
			
			myModel.put("parametresSensorial", parametresSensorial);
			myModel.put("parametresFisicoQuimic", parametresFisicoQuimic);
			
			//Configurem el command
			String[] paramsSensorial = new String[parametresSensorial.size()];
			String[] paramsFisicoquimic = new String[parametresFisicoQuimic.size()];
			int indexSensorial = 0;
			int indexFisicoQuimic = 0;
			
			for(AnaliticaValor analiticaValor: valorsParametres){
				if(analiticaValor.getTipusControl() == Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL){
					paramsSensorial[indexSensorial] = analiticaValor.getValor();
					indexSensorial++;
				} else if(analiticaValor.getTipusControl() == Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC){
					paramsFisicoquimic[indexFisicoQuimic] = analiticaValor.getValor();
					indexFisicoQuimic++;
				}
			}
			
			analitica.setAnaliticaParametreTipusSensorial(paramsSensorial);
			analitica.setAnaliticaParametreTipusFisicoQuimic(paramsFisicoquimic);
			
			
			myModel.put("formData", analitica);
			
			myModel.put("path", "consultar_analitica");
			
			//PARTIDES D'OLI
	        if (establiment != null){
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	partidesOli = oliInfraestructuraEjb.findPartidesOliByEstabliment(establiment.getId());
	        }
			
		} else if ((!isFormSubmission(request)) && (!isCreate(request))) { //MODIFICACIO D'ANALITICA
			
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			VarietatOli varOli = oliInfraestructuraEjb.getVarietatOliById((analitica.getVarietatOli().getId()));
//			myModel.put("varietat", varOli.getNom());
//			
//			myModel.put("path", "modificar_analitica");
			
			//PARTIDES D'OLI
	        if (establiment != null){
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstablimentExceptDO(establiment.getId());
	        }
		} else { //CREACIO D'ANALITICA
			
			//Paràmetres de l'analítica
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			List<AnaliticaParametreTipus> parametresSensorial = oliInfraestructuraEjb.analiticaParametreTipusLlistatActius(Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL);
			
			myModel.put("parametresSensorial", parametresSensorial);
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			List<AnaliticaParametreTipus> parametresFisicoQuimic = oliInfraestructuraEjb.analiticaParametreTipusLlistatActius(Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC);
			
			myModel.put("parametresFisicoQuimic", parametresFisicoQuimic);
			
			
			myModel.put("path", "introducir_analitica");
			if(establiment!= null && establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
				if(diposit!= null && diposit.getCodiAssignat()!= null && !diposit.getCodiAssignat().equals("")){
					myModel.put("path_extension1",establiment.getNom()+" - "+ diposit.getCodiAssignat());
				}
				
			}
			
			//PARTIDES D'OLI
	        if (establiment != null){
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstablimentExceptDO(establiment.getId());
	        }
		}
		
		//Categories d'oli
		 List varietats = new ArrayList();
		 varietats.add(new BasicData("1", missatge("consulta.general.camp.varietatOli.1")));
		 varietats.add(new BasicData("2", missatge("consulta.general.camp.varietatOli.2")));
		 varietats.add(new BasicData("3", missatge("consulta.general.camp.varietatOli.3")));
		 
	     myModel.put("varietats",varietats);
    	
        myModel.put("partidesOli", partidesOli);
		
		return myModel;
	}

	/**
	 * En caso de que sea una edición retorna el objeto rellenado con los datos
	 * actuales del registro. En caso de que sea una creación retorna el objeto
	 * vacío.
	 * 
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		AnaliticaCommand analitica = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Analitica a = oliInfraestructuraEjb.analiticaAmbId(codi);
				analitica = new AnaliticaCommand();
				analitica.fromAnalitica(a);	
				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			try {
				analitica = new AnaliticaCommand();
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				List<AnaliticaParametreTipus> parametresSensorials = oliInfraestructuraEjb.analiticaParametreTipusLlistatActius(Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL);
				analitica.setAnaliticaParametreTipusSensorial(new String[parametresSensorials.size()]);
				analitica.setIdAnaliticaParametreTipusSensorial(new Long[parametresSensorials.size()]);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				List<AnaliticaParametreTipus> parametresFisicoQuimics = oliInfraestructuraEjb.analiticaParametreTipusLlistatActius(Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC);
				analitica.setAnaliticaParametreTipusFisicoQuimic(new String[parametresFisicoQuimics.size()]);
				analitica.setIdAnaliticaParametreTipusFisicoQuimic(new Long[parametresFisicoQuimics.size()]);
				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		}
		return analitica;
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

	public void setStringAnalitica(String stringAnalitica) {
		this.stringAnalitica = stringAnalitica;
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