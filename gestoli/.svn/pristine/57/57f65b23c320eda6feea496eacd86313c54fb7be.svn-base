package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.util.Constants;


/**
 * <p>Controlador para las acciones de dar de alta o editar un
 * registro de tipos de información.</p>
 * <p>Los parámetros de la petición HTTP relevantes para este
 * controlador son:
 * <ul>
 *   <li><code>id</code>
 *    - Identificador del registro. Este parámetro es el que
 *      determina si se ha de mostrar la página de creación o la
 *      página de edición.</li>
 * </ul></p>
 * <p>No hay información para la vista:
 * <p>Este controlador distingue entre las peticiones del tipo
 * GET y las de tipo POST. Si la petición es de tipo POST
 * se ejecuta la acción de creación o de edición. Si la petición es de
 * tipo GET solo se muestra la página.
 *
 * 
 */

public class FincasFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(FincasFormController.class);
	private String rolDoGestor;
	private String rolOlivicultor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String campanyaSessionKey;
	private HibernateTemplate hibernateTemplate;


	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		FincasCommand finca = (FincasCommand)command;
		Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
		
		String cambioEstado = finca.getCambioEstadoActivo();
		
		Long fincaId = finca.getId();
		String nombre = finca.getNom();		
		String observaciones = finca.getObservacions();
		Long olivicultorId = Long.valueOf(finca.getIdOlivicultor());
		Boolean activa = finca.getActiu();
		if (activa == null){
			activa = new Boolean(false);
		}
		Set plantaciones = finca.getPlantacios();
		
		Map myModel = new HashMap();
		if (isCreate(request)) {
			
			try {
				olivicultorId = Long.valueOf(request.getParameter("idOlivicultor"));
				myModel.put("idOlivicultor", olivicultorId);
				logger.info("Creant el registre: " + finca);
				if (finca.getFincaBaixaId() != null) {
					Long fincaBaixaId = finca.getFincaBaixaId();
					
					try {
						fincaId = fincaBaixaId;
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Finca fincaBaixa = oliInfraestructuraEjb.fincaAmbId(fincaBaixaId);
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Olivicultor olivicultor = oliInfraestructuraEjb.olivicultorAmbId(olivicultorId);
					
						// Donam d'alta la finca i l'assignam a l'olivicultor seleccionat.
						fincaBaixa.setOlivicultor(olivicultor);
						fincaBaixa.setDeBaixa(false);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.finquesActualitzar(fincaBaixa);
						
						// Afegim la finca a l'olivicultor.
						Set finques = olivicultor.getFincas();
						finques.add(fincaBaixa);
						olivicultor.setFincas(finques);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.olivicultorActualitzar(olivicultor);
						ControllerUtils.saveMessageInfo(request, missatge("finques.missatge.assignar.ok"));
					} catch (Exception ex) {
						logger.error("Error associant la finca", ex);
						ControllerUtils.saveMessageError(request, missatge("finques.missatge.assignar.no"));
					}
				} else {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		        	fincaId = oliInfraestructuraEjb.fincaCrear(nombre, olivicultorId, observaciones, new Boolean(true));
					finca.setId(fincaId);
		        	ControllerUtils.saveMessageInfo(request, missatge("fincas.missatge.crear.ok"));
				}
			} catch (Exception ex) {
				logger.error("Error creant la finca", ex);
				ControllerUtils.saveMessageError(request, missatge("fincas.missatge.crear.no"));
			}
			
		} else {
			try {

				fincaId = Long.valueOf(request.getParameter("id"));
				olivicultorId = Long.valueOf(request.getParameter("idOlivicultor"));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection idPlantacionesActivas = oliInfraestructuraEjb.idPlantacionesActivas(fincaId);

				if (!(cambioEstado != null && cambioEstado.equalsIgnoreCase("true") && activa.booleanValue() &&  !(idPlantacionesActivas.size() > 0))){
					if (cambioEstado != null && cambioEstado.equalsIgnoreCase("true") && !activa.booleanValue()  &&  idPlantacionesActivas.size() > 0){
						cambiarActivoPlantaciones(idPlantacionesActivas);
					}
					logger.info("Modificant el registre: " + finca);
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.fincaModificar(fincaId, nombre, observaciones, activa);
					myModel.put("id", fincaId);
					ControllerUtils.saveMessageInfo(request, missatge("fincas.missatge.modificar.ok"));
				}else{
					ControllerUtils.saveMessageError(request, missatge("fincas.missatge.modificar.plantaActivas"));
				}

			} catch (Exception ex) {
				logger.error("Error modificant la finca ", ex);
				ControllerUtils.saveMessageError(request, missatge("fincas.missatge.modificar.no"));
			}

		}
		String forward = getSuccessView()+ "?id=" + fincaId;
		return new ModelAndView(forward, myModel);
	}


	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		if (request.getAttribute("idAlEntrar")==null){
			if (request.getParameter("id")!=null){
				request.setAttribute("idAlEntrar",Boolean.valueOf(true));
			}else{
				request.setAttribute("idAlEntrar",Boolean.valueOf(false));
			}
		}	
		
		Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
		Map myModel = new HashMap();
		String idOlivicultor =null;
		if (request.getParameter("idOlivicultor")!= null && !request.getParameter("idOlivicultor").equals("")){
			idOlivicultor = request.getParameter("idOlivicultor");
			myModel.put("idOlivicultor",idOlivicultor);
		}
	
		
		FincasCommand finca = (FincasCommand)command;
		if (request.getParameter("idOlivicultor")!= null && !request.getParameter("idOlivicultor").equals("")){
			finca.setIdOlivicultor(idOlivicultor);
		}
		if (request.getParameter("id")!= null) {
			Long fincaId = new Long(Long.parseLong(request.getParameter("id")));
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection lista_plantaciones = oliInfraestructuraEjb.getPlantacionesDeFinca(fincaId);
			myModel.put("plantaciones", lista_plantaciones);
			//Calculamos descomposición variedades
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection variedades = oliInfraestructuraEjb.getDescPlantacioActivasAmbFinca(fincaId);
			Map resultadoVariedades = new HashMap();

			for (Iterator itVar = variedades.iterator(); itVar.hasNext(); ){
				DescomposicioPlantacio descomposicion = (DescomposicioPlantacio)itVar.next();
				VarietatOliva variedad = descomposicion.getVarietatOliva();
				Variedad nueva_variedad = new Variedad();
				if (resultadoVariedades.containsKey(variedad)){
					Variedad v = (Variedad) resultadoVariedades.get(variedad);
					Double superficie = new Double(descomposicion.getSuperficie().doubleValue() + v.getSuperficie().doubleValue());
					Double produccion= new Double(descomposicion.getMaxProduccio().doubleValue() + v.getProduccion().doubleValue());
					nueva_variedad = new Variedad (superficie, produccion);
				}else{
					nueva_variedad = new Variedad (descomposicion.getSuperficie(), descomposicion.getMaxProduccio());
				}
				
				if ((variedad.getExperimental() != null) && variedad.getExperimental()) {
					nueva_variedad.setNomVariedad(variedad.getNomVarietat());
				} else {
					nueva_variedad.setNomVariedad(variedad.getNom());
				}
				if (variedad.getId()!=Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA)
					resultadoVariedades.put(variedad,nueva_variedad);
			}
			
			myModel.put("variedades", resultadoVariedades);

		}

/*		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			myModel.put("path", "modificar_fincas");
		}else{
			myModel.put("path", "crear_fincas");
		} */
		
		if (((Boolean)request.getAttribute("idAlEntrar")).booleanValue()){
			myModel.put("path", "modificar_fincas");
		}else{
			myModel.put("path", "crear_fincas");
		}
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		myModel.put("finquesBaixa", oliInfraestructuraEjb.finquesDeBaixa());
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		FincasCommand finca = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Finca f = null;
				if ((request.getParameter("id") != null) && (!request.getParameter("id").equals(""))) {
					Long codi = new Long(Long.parseLong(request.getParameter("id")));
					f = oliInfraestructuraEjb.fincaAmbId(codi);
				}
				finca = new FincasCommand();
				finca.fromFinca(f);
				finca.setIdOlivicultor(f.getOlivicultor().getId().toString());
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			
			finca = new FincasCommand();
		}

		return finca;
	}


	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i>
	 * con tipos que no sean <i>String</i> se ha de configurar su
	 * correspondiente binder aquí.
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder)
	throws Exception {
		binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
	}


	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}


	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}
	
	/**
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
	

	/**
	 * Injecció de la dependència rolAdmin
	 * @param rolAdmin La classe a injectar.
	 */
	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
	}

	
	 /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}
	
	
	private void cambiarActivoPlantaciones(Collection col) {
		for (Iterator it = col.iterator(); it.hasNext();){
			Long idPlantacio = (Long)it.next();
			try{
				oliInfraestructuraEjb.plantacioSetActiuFalse(idPlantacio);
			} catch (Exception ex) {
				logger.error("Error modificant la plantacio ", ex);
			}
		}
	}
	
	
	public class Variedad {
		private String nomVariedad;
		private Double superficie;
		private Double produccion;
		
		Variedad (Double superficie, Double produccion){
			this.superficie = superficie;
			this.produccion = produccion;
		}
		Variedad(){
		
		}
		
		public Double getProduccion() {
			return produccion;
		}
		public void setProduccion(Double produccion) {
			this.produccion = produccion;
		}
		public Double getSuperficie() {
			return superficie;
		}
		public void setSuperficie(Double superficie) {
			this.superficie = superficie;
		}
		public String getNomVariedad() {
			return nomVariedad;
		}
		public void setNomVariedad(String nomVariedad) {
			this.nomVariedad = nomVariedad;
		}
		

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
