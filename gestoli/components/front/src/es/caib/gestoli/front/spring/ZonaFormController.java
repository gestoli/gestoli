package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Usuari;
import es.caib.gestoli.logic.model.Zona;


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
public class ZonaFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(ZonaFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliArxiuEjb oliArxiuEjb;
	private String rolProductor;
	private String rolEnvasador;
	private String establimentSessionKey;
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
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		ZonaCommand zona = (ZonaCommand)command;
		Long zonaId = zona.getId();
		Long imagePlanol = zona.getImatgePlanol();
		String nombre = zona.getNom();
		Long idEstablecimiento =  est.getId();
		String observaciones = zona.getObservacions();
		Long idOriginal = zona.getIdOriginal();
		
		Boolean activo = Boolean.valueOf(true);
		if(zona.getActiu()== null){
			activo = Boolean.valueOf(false);
		}

		Boolean defecte= Boolean.valueOf(true);
		if(zona.getDefecte() == null){
			defecte = Boolean.valueOf(false);
		}
		
		Boolean defecteTrasllat = Boolean.valueOf(true);
		if(zona.getDefecteTrasllat() == null){
			defecteTrasllat = Boolean.valueOf(false);
		}
		
		Boolean fictici = Boolean.valueOf(true);
		if(zona.getFictici() == null){
			fictici = Boolean.valueOf(false);
		}
		
		MultipartFile[] files = getFiles(request);
		String cambioEstado = zona.getCambioEstadoActivo();

		Map myModel = new HashMap();	

		if (isCreate(request)) {
//			if (files != null) {
				try {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Collection zonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(idEstablecimiento);
					if (zonas.size()==0){
						defecte = Boolean.valueOf(true);
						defecteTrasllat = Boolean.valueOf(true);
					}else {
						if (defecte.equals(Boolean.valueOf(true))){
							cambiarDefecte(zonas);
						}
						if (defecteTrasllat.equals(Boolean.valueOf(true))){
							cambiarDefecteTrasllat(zonas);
						}
					}
					
					logger.info("Creant el registre: " + zona);	
					if (files != null) {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						zonaId = oliInfraestructuraEjb.zonaCrear(nombre, idEstablecimiento, imagePlanol, observaciones, new Boolean(true),
								defecte, defecteTrasllat, fictici, files[0].getOriginalFilename(), files[0].getContentType(), files[0].getBytes(), null);
					} else {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						zonaId = oliInfraestructuraEjb.zonaCrear(nombre, idEstablecimiento, imagePlanol, observaciones, new Boolean(true),
								defecte, defecteTrasllat, fictici, null, null, null, null);
					}
					zona.setId(zonaId);
					ControllerUtils.saveMessageInfo(request, missatge("zona.missatge.crear.ok"));
				} catch (Exception ex) {
					logger.error("Error creant la zona", ex);
					ControllerUtils.saveMessageError(request, missatge("zona.missatge.crear.no"));
				}
//			}else {
//				ControllerUtils.saveMessageError(request, missatge("zona.missatge.crear.no"));
//			}
		} else {
			try {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection zonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(idEstablecimiento);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Boolean diposits = oliInfraestructuraEjb.existenDipositsActiusEnZona(zonaId);
				
								
				if (!(cambioEstado != null && cambioEstado.equalsIgnoreCase("true") && !activo.booleanValue()  &&  diposits.booleanValue())){
				//if (  activo.booleanValue() || (!activo.booleanValue() &&  diposits.booleanValue())){

					if (!activo.booleanValue() && esUnicaZonaActiva(zonas, zonaId).booleanValue()){	
						est.setActiu(Boolean.valueOf(false));
						session.removeAttribute(establimentSessionKey);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.establimentModificar(est);
					}
					
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Collection zonasByEstabliment = oliInfraestructuraEjb.zonaAmbEstabliment(idEstablecimiento);
					if (zonasByEstabliment.size() > 1){
						if (defecte.equals(Boolean.valueOf(true))){
							cambiarDefecte(zonasByEstabliment);
						}
						if (defecteTrasllat.equals(Boolean.valueOf(true))){
							cambiarDefecteTrasllat(zonasByEstabliment);
						}
					}else{
						defecte = Boolean.valueOf(true);
						defecteTrasllat = Boolean.valueOf(true);
					}
					

					if (files!=null){
						logger.info("Modificant el registre: " + zona);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.zonaModificar(nombre, idEstablecimiento, imagePlanol, observaciones, activo, defecte, defecteTrasllat, fictici, zonaId, files[0].getOriginalFilename(), files[0].getContentType(), files[0].getBytes(), idOriginal, cambioEstado);
					}else{
						logger.info("Modificant el registre: " + zona);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.zonaModificar(nombre, idEstablecimiento, imagePlanol, observaciones, activo, defecte, defecteTrasllat, fictici, zonaId, null, null, null, idOriginal, cambioEstado);
					}

					ControllerUtils.saveMessageInfo(request, missatge("zona.missatge.modificar.ok"));
				} else {
					ControllerUtils.saveMessageError(request, missatge("zona.missatge.modificar.depositactivos"));
				}
			} catch (Exception ex) {
				logger.error("Error modificant la zona ", ex);
				ControllerUtils.saveMessageError(request, missatge("zona.missatge.modificar.no"));
			}
		}

		String forward = getSuccessView()+ "?id=" + zona.getId();
		session.removeAttribute(establimentSessionKey); // 20090617 obarnes: al modificar una zona, s'ha de modificar l'establiment de la sessio
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
		
		Map myModel = new HashMap();
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		if (est != null) {
			myModel.put("est", est);
		}
		Usuari u = (Usuari)session.getAttribute("usuariSessionKey");

		if (((Boolean)request.getAttribute("idAlEntrar")).booleanValue()){
			myModel.put("path", "modificar_zona");
		}else{
			myModel.put("path", "crear_zona");
		}

		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		ZonaCommand zona = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Zona z = oliInfraestructuraEjb.zonaAmbId(codi);
				zona = new ZonaCommand();
				zona.fromZona(z);
				if(z.getImatgePlanol()!= null){
					oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
					zona.setArxiuc(oliArxiuEjb.arxiuCercaId(z.getImatgePlanol()));
				}				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			zona = new ZonaCommand();
		}
		if (isCreate(request)) {
			zona.setActiu(new Boolean(true));
		}
		return zona;
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
				byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
	}


	
	

    /*
     * Retorna els arxius associats a aquesta peticio o null
     * si no n'hi ha 
     */
    private MultipartFile[] getFiles(HttpServletRequest request) {
    	if (request instanceof MultipartHttpServletRequest) {
        	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
        	Map mFiles = mpRequest.getFileMap();
        	if (mFiles.size() > 0) {
	        	MultipartFile[] files = new MultipartFile[mFiles.size()];
	        	int i = 0;
	        	for (Iterator it = mFiles.keySet().iterator(); it.hasNext();) {
	        		files[i++] = mpRequest.getFile((String)it.next());
	        	}
	        	if (files[0].getSize() > 0)
	        		return files;
	        	else
	        		return null;
        	}
    	}
        return null;
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
	 * Inyección de la dependencia oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * Inyección de la dependencia oliArxiuEjbBean
	 * @param oliArxiuEjbBean La clase a inyectar.
	 */
	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
	}
	
	

	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}


	/**
	 * Injecció de la dependència rolEnvasador
	 * @param rolEnvasador La classe a injectar.
	 */
	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	
	/**
	 * Injecció de la dependència rolProductor
	 * @param rolProductor La classe a injectar.
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}

	
	/**
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	private void cambiarDefecte (Collection col) {
		for (Iterator it = col.iterator(); it.hasNext();){
			Zona zona = (Zona)it.next();
			if (zona.getDefecte().equals(Boolean.valueOf(true))){
				try{
					zona.setDefecte(Boolean.valueOf(false));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.zonaModificar(zona);
				} catch (Exception ex) {
					logger.error("Error modificant la zona ", ex);
				}
			}
		}
	}
	
	private void cambiarDefecteTrasllat (Collection col) {
		for (Iterator it = col.iterator(); it.hasNext();){
			Zona zona = (Zona)it.next();
			if (zona.getDefecteTrasllat().equals(Boolean.valueOf(true))){
				try{
					zona.setDefecteTrasllat(Boolean.valueOf(false));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.zonaModificar(zona);
				} catch (Exception ex) {
					logger.error("Error modificant la zona ", ex);
				}
			}
		}
	}
	
	
	
	private Boolean esUnicaZonaActiva(Collection zonas, Long zonaId){
		for (Iterator it = zonas.iterator(); it.hasNext();){
			Zona zona = (Zona) it.next();
			if (zona.getActiu().equals(Boolean.valueOf(true)) && !zona.getId().equals(zonaId))
				return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
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