package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.ejb.OliConsultaEjbBean;
import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliConsultaEjbLocal;
import es.caib.gestoli.logic.interfaces.OliConsultaEjbLocalHome;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.DocumentInspeccio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.TipusEstabliment;
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
 * @author Carlos Pérez (cperez@at4.net)
 */
public class EstablimentFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(EstablimentFormController.class);
	private String rolProductor;
	private String rolEnvasador;
	private String rolDoGestor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private OliArxiuEjb oliArxiuEjb;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private String campanyaSessionKey;
	private String establimentSessionKey;
	private HibernateTemplate hibernateTemplate;
	private OliConsultaEjb oliConsultaEjb;
	


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
		EstablimentCommand establiment = (EstablimentCommand)command;
		
//		Long usuariId = establiment.getUsuariId();
		Long idOriginal = establiment.getIdOriginal() ;
		Campanya campanya = new Campanya();		
		if(establiment.getCampanyaId()!= null){
			campanya.setId(establiment.getCampanyaId());
		}else{
			Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
			campanya.setId(campanyaId);
		}
		
		String establecimiento = establiment.getNom();
		String cif = establiment.getCif();
		String direccion = establiment.getDireccio();
		if (direccion != null && direccion.equals("")) direccion = null;
		Long localidad = establiment.getMunicipiId();
		String cpostal = establiment.getCodiPostal();
		if (cpostal != null && cpostal.equals("")) cpostal = null;
		String email = establiment.getEmail();
		String telefono = establiment.getTelefon();
		if (telefono != null && telefono.equals("")) telefono = null;
		String fax = establiment.getFax();
		if (fax != null && fax.equals("")) fax = null;
		Integer trabajadores = establiment.getNumeroTreballadors();
		Double superficie = establiment.getSuperficie();
		Long tipoEstablecimientoId = establiment.getTipusEstablimentId();
		String nombresolicitante = establiment.getNombreSolicitant();
		String dnisolicitante = establiment.getNifSolicitant();
		if (dnisolicitante != null && dnisolicitante.equals("")) dnisolicitante = null;
		String perfil = establiment.getPerfilSolicitant();
		if (perfil != null && perfil.equals("")) perfil = null;
		String telefonosolicitante = establiment.getTelefonSolicitant();
		if (telefonosolicitante != null && telefonosolicitante.equals("")) telefonosolicitante = null;
		String unidadmedida = establiment.getUnitatMesura();
		String observaciones = establiment.getObservacions();
		if (observaciones != null && observaciones.equals("")) observaciones = null;
		String nomResponsable = establiment.getNomResponsable();
		String cifResponsable = establiment.getCifResponsable();
		
		
		Long[] marcas = establiment.getMarcasArray();		
		Integer tempMaximaPasta = establiment.getTemperaturaMaximaPasta();
		Double capacidadProduccion = establiment.getCapacitatProduccio();
		Boolean envasadoManual = establiment.getEnvassamentManual();
		if (envasadoManual == null)
			envasadoManual = new Boolean(false);
		else
			envasadoManual = new Boolean(true);
		Boolean etiquetadoManual = establiment.getEtiquetatManual();
		if (etiquetadoManual == null)
			etiquetadoManual = new Boolean(false);
		else
			etiquetadoManual = new Boolean(true);
		String codiRB = establiment.getCodiRB(); // Codi Tafona
		if (codiRB != null && codiRB.equals("") || tipusEstablimentEnvasadora.intValue() == tipoEstablecimientoId.intValue()) codiRB = null;
		// 20090908 obarnes: para las ordenaciones de las tablas, nos interesa que no sea NULL
		if (codiRB == null) codiRB = "";
		String codiRC = establiment.getCodiRC(); // Codi Envasadora
		if (codiRC != null && codiRC.equals("") || tipusEstablimentTafona.intValue() == tipoEstablecimientoId.intValue()) codiRC = null;
		// 20090908 obarnes: para las ordenaciones de las tablas, nos interesa que no sea NULL
		if (codiRC == null) codiRC = "";

		Boolean olivaTaula = establiment.getOlivaTaula();
		String codiRT = establiment.getCodiRT();
		if (olivaTaula == null) {
			olivaTaula = new Boolean(false);
			codiRT = "";
		} else {
			olivaTaula = new Boolean(true);
			if (codiRT != null && codiRT.equals("")) codiRC = null;
			if (codiRC == null) codiRC = "";
		}
		
		//Nos aseguramos que solo se guardan los campos que se tienen que guardar dependiendo si el establecimiento es de un tipo u otro
		if(tipusEstablimentEnvasadora.intValue() == tipoEstablecimientoId.intValue()){
			tempMaximaPasta = null;
		}
		if(tipusEstablimentTafona.intValue() == tipoEstablecimientoId.intValue()){
			envasadoManual = new Boolean(false);
			etiquetadoManual = new Boolean(false);
			marcas = null;
		}
				
		Long establecimientoId = establiment.getId();
		Boolean activo = new Boolean(true); 
		if (establiment.getActiu()==null){
			activo = new Boolean(false);
		}
		String cambioEstado = establiment.getCambioEstadoActivo();
		Map myModel = new HashMap();
		
		if (isCreate(request)) {
			try {
				logger.info("Creant el registre: " + establiment);	
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				establecimientoId = oliInfraestructuraEjb.establimentCrear(usuariId, campanya, idOriginal, establecimiento, cif, direccion, localidad, cpostal, email, telefono, fax, trabajadores,
//						superficie, tipoEstablecimientoId, nombresolicitante, dnisolicitante, perfil, telefonosolicitante, unidadmedida, observaciones, tempMaximaPasta, capacidadProduccion, envasadoManual, etiquetadoManual, marcas, codiRB, codiRC);
				establecimientoId = oliInfraestructuraEjb.establimentCrear(
						campanya, 
						idOriginal, 
						establecimiento, 
						cif, 
						direccion, 
						localidad, 
						cpostal, 
						email, 
						telefono, 
						fax, 
						trabajadores,
						superficie, 
						tipoEstablecimientoId, 
						nombresolicitante, 
						dnisolicitante, 
						nomResponsable,
						cifResponsable,
						perfil, 
						telefonosolicitante, 
						unidadmedida, 
						observaciones, 
						tempMaximaPasta, 
						capacidadProduccion, 
						envasadoManual, 
						etiquetadoManual, 
						marcas, 
						codiRB, 
						codiRC,
						codiRT,
						olivaTaula);
				establiment.setId(establecimientoId);
				
				//Cream el diposit fictici de l'establiment
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Boolean deEnvasadora = new Boolean(false);
				if (tipoEstablecimientoId.intValue() == Constants.ENV_ENVASADORA) deEnvasadora = new Boolean(true);
				oliInfraestructuraEjb.dipositCrear(null, establiment.getNomDipositFictici(), establecimientoId, null, null, null, false, true, deEnvasadora);
				
				ControllerUtils.saveMessageInfo(request, missatge("establiment.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant la establiment", ex);
				ControllerUtils.saveMessageError(request, missatge("establiment.missatge.crear.no"));
			}
		} else {
			try {
				logger.info("Modificant el registre: " + establiment);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if (!request.isUserInRole(rolProductor)){
//					oliInfraestructuraEjb.establimentModificar(usuariId, campanya, idOriginal, establecimiento, cif, direccion, localidad, cpostal, email, telefono, fax, trabajadores,
//							superficie, tipoEstablecimientoId, nombresolicitante, dnisolicitante, perfil, telefonosolicitante, unidadmedida, observaciones, tempMaximaPasta, capacidadProduccion, envasadoManual, etiquetadoManual, activo, establecimientoId, marcas, cambioEstado, codiRB, codiRC);
					oliInfraestructuraEjb.establimentModificar(
							campanya, 
							idOriginal, 
							establecimiento, 
							cif, 
							direccion, 
							localidad, 
							cpostal, 
							email, 
							telefono, 
							fax, 
							trabajadores,
							superficie, 
							tipoEstablecimientoId, 
							nombresolicitante, 
							dnisolicitante, 
							nomResponsable,
							cifResponsable,
							perfil, 
							telefonosolicitante, 
							unidadmedida, 
							observaciones, tempMaximaPasta, 
							capacidadProduccion, 
							envasadoManual, 
							etiquetadoManual, 
							activo, 
							establecimientoId, 
							marcas, 
							cambioEstado, 
							codiRB, 
							codiRC,
							codiRT,
							olivaTaula);
				}else{
//					oliInfraestructuraEjb.establimentModificar(usuariId, campanya, idOriginal, null, null, direccion, localidad, cpostal, email, telefono, fax, trabajadores,
//							superficie, null, nombresolicitante, dnisolicitante, perfil, telefonosolicitante, unidadmedida, observaciones, tempMaximaPasta, capacidadProduccion, envasadoManual, etiquetadoManual, activo, establecimientoId, marcas, cambioEstado, null, null);
					oliInfraestructuraEjb.establimentModificar(
							campanya, 
							idOriginal, 
							null, 
							null, 
							direccion, 
							localidad, 
							cpostal, 
							email, 
							telefono, 
							fax, 
							trabajadores,
							superficie, 
							null, 
							nombresolicitante, 
							dnisolicitante, 
							nomResponsable,
							cifResponsable,
							perfil, 
							telefonosolicitante, 
							unidadmedida, 
							observaciones, 
							tempMaximaPasta, 
							capacidadProduccion, 
							envasadoManual, 
							etiquetadoManual, 
							activo, 
							establecimientoId, 
							marcas, 
							cambioEstado, 
							null, 
							null,
							null,
							olivaTaula);
				}
				request.getSession().removeAttribute(establimentSessionKey);
				
				//Modificam diposit fictici
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit dipFictici = oliInfraestructuraEjb.findDipositFicticiByEstabliment(establecimientoId);
				
				dipFictici.setCodiAssignat(establiment.getNomDipositFictici());
				
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.dipositModificar(dipFictici);
				
				ControllerUtils.saveMessageInfo(request, missatge("establiment.missatge.modificar.ok"));
			} catch (Exception ex) {
				logger.error("Error modificant el establiment ", ex);
				ControllerUtils.saveMessageError(request, missatge("establiment.missatge.modificar.no"));
			}
		}
		String forward = getSuccessView()+ "?id=" + establiment.getId();
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
		
		String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
		Map myModel = new HashMap();
				
//		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//		Collection usuarios =  oliInfraestructuraEjb.usuariCercaTotsActivos();
//		Collection usuariosProEnv = new ArrayList();
//		// Validamos que el tipo es olivicultor
//		Iterator iteUsuarios = usuarios.iterator();
//		while (iteUsuarios.hasNext()){
//			
//			Usuari usu = (Usuari)iteUsuarios.next();
//			
//			Iterator iteGrupos = usu.getGrups().iterator();
//			while (iteGrupos.hasNext()){
//				Grup g = (Grup)iteGrupos.next();
//				if (g.getId().equals(rolProductor) || g.getId().equals(rolEnvasador)){
//					usuariosProEnv.add(usu);
//					break;
//				}
//			}
//			
//		}
//		
//		myModel.put("usuarios",usuariosProEnv);
//		if(usuariosProEnv == null || usuariosProEnv.size()==0 ){
//			myModel.put("precondicion",Boolean.valueOf(false));
//			ControllerUtils.saveMessageError(request, missatge("establiment.missatge.crear.noUsuari"));
//		}
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection itTipusEstabliments = oliInfraestructuraEjb.tipusEstablimentCercaTots();
		Collection tipusEstabliments = new ArrayList();
		for (Iterator it = itTipusEstabliments.iterator(); it.hasNext();){
			TipusEstabliment tipusEstabliment = (TipusEstabliment)it.next();
			if (tipusEstabliment.getId() != null) {
				BasicData basicData = new BasicData();
				basicData.setId(tipusEstabliment.getId().toString());
				basicData.setNom(bundle.getString("establiment.tipus." + tipusEstabliment.getId()));
				tipusEstabliments.add(basicData);
			}
		}
		myModel.put("tipusEstabliments", tipusEstabliments);
		myModel.put("tipusEstablimentTafona", tipusEstablimentTafona);
		myModel.put("tipusEstablimentEnvasadora", tipusEstablimentEnvasadora);
		myModel.put("tipusEstablimentTafonaEnvasadora", tipusEstablimentTafonaEnvasadora);
		myModel.put("municipis", (List<Municipi>)oliInfraestructuraEjb.obtenirMunicipis());
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection marcasArray = oliInfraestructuraEjb.marcaCercaTotsActivos();
		myModel.put("marcasArray", marcasArray);
		// Si no existen marcas activas quitamos de los tipos de establecimiento la envasadora y la tafonaEnvasadora
		if(marcasArray == null || marcasArray.size() == 0){
			List tipusEstablimentsAux = new ArrayList();
			for (Iterator it = itTipusEstabliments.iterator(); it.hasNext();){
				TipusEstabliment tipusEstabliment = (TipusEstabliment)it.next();
				if(tipusEstabliment.getId().intValue()!= tipusEstablimentEnvasadora.intValue() && tipusEstabliment.getId().intValue()!= tipusEstablimentTafonaEnvasadora.intValue()){
					BasicData basicData = new BasicData();
					basicData.setId(tipusEstabliment.getId().toString());
					basicData.setNom(bundle.getString("establiment.tipus." + tipusEstabliment.getId()));
					tipusEstablimentsAux.add(basicData);
				}
			}
			myModel.put("tipusEstabliments", tipusEstablimentsAux);
		}
		
		//Unidades de medida (l (litro), k (kilo))
		ArrayList unidadesMedida = new ArrayList();
		BasicData basicData = new BasicData();
		basicData.setId("l");
		basicData.setNom(bundle.getString("establiment.medida.l"));
		unidadesMedida.add(basicData);
		basicData = new BasicData();
		basicData.setId("k");
		basicData.setNom(bundle.getString("establiment.medida.k"));
		unidadesMedida.add(basicData);
		myModel.put("unidadesMedida", unidadesMedida);		
		
		Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
		myModel.put("campanyaId", campanyaId);	
		
		EstablimentCommand est = (EstablimentCommand)command;
		
		
		
		if (((Boolean)request.getAttribute("idAlEntrar")).booleanValue()){
			myModel.put("path", "modificar_establecimiento");
		}else{
			myModel.put("path", "crear_establecimiento");
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			String codiRB = oliInfraestructuraEjb.findLastCodiRB();
			String codiRC = oliInfraestructuraEjb.findLastCodiRC();
			String codiRT = oliInfraestructuraEjb.findLastCodiRT();
			est.setCodiRB(codiRB);
			est.setCodiRC(codiRC);
			est.setCodiRT(codiRT);
		}		
		
		
		// DOCUMENTOS ASIGNADOS
        if (est.getId() != null && request.isUserInRole(rolDoGestor)) {
        	List llistatDocuments = new ArrayList();
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Iterator itDocumentos = oliInfraestructuraEjb.documentsInspeccioByEstabliment(est.getId()).iterator();
        	while (itDocumentos.hasNext()) {
        		DocumentInspeccioCommand document = new DocumentInspeccioCommand();
        		document.fromDocumentInspeccio((DocumentInspeccio) itDocumentos.next());
        		oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
				document.setArxiuObject(oliArxiuEjb.arxiuCercaId(document.getArxiu()));
				llistatDocuments.add(document);
        	}
        	myModel.put("llistatDocuments", llistatDocuments);
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
		EstablimentCommand establiment = null;
		
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Establiment es = oliInfraestructuraEjb.establimentAmbId(codi);
				establiment = new EstablimentCommand();
				establiment.fromEstabliment(es);
				
				Long[] marcasIds = new Long[establiment.getMarcas().size()];
	            int index = 0;
	            for (Iterator it = establiment.getMarcas().iterator(); it.hasNext();) {
	            	marcasIds[index++] = ((Marca)it.next()).getId();
	            }
	            establiment.setMarcasArray(marcasIds);			
	            
	            oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	            Diposit dipFictici = oliInfraestructuraEjb.findDipositFicticiByEstabliment(establiment.getId());
	           
	            if(dipFictici != null){
	            	establiment.setNomDipositFictici(dipFictici.getCodiAssignat());
	            }
	            
				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			establiment = new EstablimentCommand();
		}
		if (isCreate(request)) {
			establiment.setActiu(new Boolean(true));
		}
		
		return establiment;
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
		NumberFormat nf = NumberFormat.getNumberInstance();
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, nf, true));


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


	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
	}
	
	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}
	
	
	 /**
     * Injecció de la dependència tipusEstablimentTafona
     *
     * @param tipusEstablimentTafona La classe a injectar.
     */
    public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
        this.tipusEstablimentTafona = tipusEstablimentTafona;
    }
    /**
     * Injecció de la dependència tipusEstablimentEnvasadora
     *
     * @param tipusEstablimentEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
        this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
    }
    
    /**
     * Injecció de la dependència tipusEstablimentTafonaEnvasadora
     *
     * @param tipusEstablimentTafonaEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
        this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
    }
    
    /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}


	/**
	 * Injecció de la dependència establimentSessionKey
	 * @param establimentSessionKey La classe a injectar.
	 */
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


	public String getRolProductor() {
		return rolProductor;
	}


	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}


	public String getRolEnvasador() {
		return rolEnvasador;
	}


	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}


	public String getRolDoGestor() {
		return rolDoGestor;
	}


	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

}
