package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliUsuariEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Grup;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Usuari;
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
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class UsuariFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(UsuariFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliUsuariEjb oliUsuariEjb;
	private String usuariSessionKey;
	private String establimentSessionKey;
	private String rolDoGestor;
	private String rolOlivicultor;
	private String rolProductor;
	private String rolEnvasador;
	private String rolDoControl;
	private String rolAdministracio;
	private String rolEstAdministrador;
	private String rolEstEncarregat;
	private String rolEstTreballador;
	private String rolEstConsulta;
	private String rolOlivaTaula;
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

		UsuariCommand usuari = (UsuariCommand)command;
		
		if (usuari.getContrasenya() != null && usuari.getContrasenyaEncriptada() != null) {
			if (usuari.getContrasenya().equals(usuari.getContrasenyaEncriptada())) {
				usuari.setContrasenya(null);
			}
		}
		if (usuari.getContrasenya() != null) {
			usuari.setContrasenya(Util.parseContrasenya(usuari.getContrasenya()));
		}
		String contrasenya = usuari.getContrasenya();
		Long establimentId = null;
//		String[] grupos = usuari.getGrupsArray();
		List<String> grupos = new ArrayList();
		
		if (usuari.getTipusUsuari().equals(Constants.TAFONA_ENVASADORA)){
			// Dos rols: tafona i envasadora
			grupos.add(rolProductor);
			grupos.add(rolEnvasador);
			establimentId = usuari.getEstablimentIdTafEnv();
		} else {
			// Un únic rol
			if (usuari.getTipusUsuari().equals(Constants.ADMINISTRACIO)){
				grupos.add(rolAdministracio);
			} else if (usuari.getTipusUsuari().equals(Constants.DO_CONTROL)){
				grupos.add(rolDoControl);
			} else if (usuari.getTipusUsuari().equals(Constants.DO_GESTOR)){
				grupos.add(rolDoGestor);
			} else if (usuari.getTipusUsuari().equals(Constants.TAFONA)){
				grupos.add(rolProductor);
				establimentId = usuari.getEstablimentIdTafona();
			} else if (usuari.getTipusUsuari().equals(Constants.ENVASADORA)){
				grupos.add(rolEnvasador);
				establimentId = usuari.getEstablimentIdEnvasadora();
			}
		}
		
		if(usuari.getTipusUsuariEstabliment() != null){
			if(usuari.getTipusUsuariEstabliment().equals(Constants.EST_ADMINISTRADOR)){
				grupos.add(rolEstAdministrador);
			} else if(usuari.getTipusUsuariEstabliment().equals(Constants.EST_ENCARREGAT)){
				grupos.add(rolEstEncarregat);
			} else if(usuari.getTipusUsuariEstabliment().equals(Constants.EST_TREBALLADOR)){
				grupos.add(rolEstTreballador);
			} else if(usuari.getTipusUsuariEstabliment().equals(Constants.EST_CONSULTOR)){
				grupos.add(rolEstConsulta);
			}
		//} else {
		//	grupos.add(rolEstAdministrador);
		}
		
		if (usuari.getOlivaTaula() != null) {
			grupos.add(rolOlivaTaula);
		}
		
		String usuario = usuari.getUsuari();
		Boolean activo = Boolean.valueOf(true);
		if(usuari.getActiu()== null){
			activo = Boolean.valueOf(false);
		}
		
		String idioma = usuari.getIdiomaId();
		Long usuariId = usuari.getId();
		String observaciones = usuari.getObservacions();
		if (observaciones != null && observaciones.equals("")) observaciones = null;
		String email = usuari.getEmail();
		if (email != null && email.equals("")) email = null;

		Map myModel = new HashMap();	
		
		String[] grups = new String[grupos.size()];
		
		for(int i=0; i<grupos.size(); i++){
			grups[i] = grupos.get(i);
		}
		
		if (isCreate(request)) {
			try {
				logger.info("Creant el registre: " + usuari);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				usuariId = oliInfraestructuraEjb.usuariCrear(activo, grups, idioma, usuario, contrasenya, email, observaciones, establimentId);
				usuari.setId(usuariId);
				ControllerUtils.saveMessageInfo(request, missatge("usuari.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el usuari", ex);
				ControllerUtils.saveMessageError(request, missatge("usuari.missatge.crear.no"));
			}
		} else {
			try {
				logger.info("Modificant el registre: " + usuari);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if (request.isUserInRole(rolDoGestor)){
					oliInfraestructuraEjb.usuariModificar(activo, grups, idioma, usuario, contrasenya, email, observaciones, usuariId);
				}else{
					oliInfraestructuraEjb.usuariModificar(activo, null, idioma, usuario, contrasenya, email, observaciones, usuariId);
				}
				request.getSession().removeAttribute(usuariSessionKey);
				//forward = getSuccessView()+ "?id=" + usuari.getId();
				ControllerUtils.saveMessageInfo(request, missatge("usuari.missatge.modificar.ok"));
				
			} catch (Exception ex) {
				logger.error("Error modificant el usuari ", ex);
				ControllerUtils.saveMessageError(request, missatge("usuari.missatge.modificar.no"));
			}
		}
		
		/*
		 if (request.isUserInRole(rolOlivicultor) || request.isUserInRole(rolProductor) || request.isUserInRole(rolEnvasador))
	        	return new ModelAndView(successViewUsuari + "?id=" + usuari.getId(), myModel);
	        else
	        	return new ModelAndView(getSuccessView(), myModel);
		*/
		
		// 20100219 obarnes [at4SAC-ID#: 2010020910116] Crear nou usuari
		// No han pedido la modificacion, pero lo preparamos: evitar usuari.getId() sigui null
		String forward = getSuccessView();
		if (usuari != null && usuari.getId() != null) {
			forward += "?id=" + usuari.getId();
		}
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
		Map myModel = new HashMap();
		
		UsuariCommand usuari = (UsuariCommand)command;
		
		if (request.getAttribute("idAlEntrar")==null){
			if (request.getParameter("id")!=null){
				request.setAttribute("idAlEntrar",Boolean.valueOf(true));
			}else{
				request.setAttribute("idAlEntrar",Boolean.valueOf(false));
			}
		}
		
//		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//		myModel.put("grupsArray", oliInfraestructuraEjb.grupCercaTots());
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		myModel.put("idiomas", oliInfraestructuraEjb.idiomaCercaTots());
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Long campanya = oliInfraestructuraEjb.campanyaCercaActual();
		
		List tipos = new ArrayList();
    	
		//Tots
		tipos.add(3);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection establimentsTots = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanya, tipos.toArray());
		
		//Tafones
		tipos.clear();
		tipos.add(1);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection establimentsTafones = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanya, tipos.toArray());
		
		//Envasadores
		tipos.clear();
		tipos.add(2);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection establimentsEnvasadores = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanya, tipos.toArray());

		
		Establiment establiment =(Establiment)request.getSession().getAttribute(establimentSessionKey);
		Collection establiments = new ArrayList();
		establiments.add(establiment);
		
		if(request.isUserInRole(rolEstAdministrador)){
			myModel.put("establiments", establiments);
			myModel.put("tafones", establiments);
			myModel.put("envasadores", establiments);
		} else {
			myModel.put("establiments", establimentsTots);
			myModel.put("tafones", establimentsTafones);
			myModel.put("envasadores", establimentsEnvasadores);
		}
		
		if (establiment != null && establiment.getOlivaTaula())
			myModel.put("estOlivaTaula", "true");
		
		if (((Boolean)request.getAttribute("idAlEntrar")).booleanValue()){
			myModel.put("path", "modificar_usuario");
		}else{
			myModel.put("path", "crear_usuario");
		}
		
		if(request.isUserInRole(rolDoGestor) && isCreate(request)){
			usuari.setTipusUsuariEstabliment(Constants.EST_ADMINISTRADOR);
		}
		
		if(request.isUserInRole(rolEstAdministrador) && (!isFormSubmission(request)) && (!isCreate(request))){
			
			Long codi = new Long(Long.parseLong(request.getParameter("id")));
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Usuari u = oliInfraestructuraEjb.usuariAmbId(codi);
			usuari = new UsuariCommand();
			usuari.fromUsuari(u);
	    	
	    	usuari.setGrups(u.getGrups());
	    	
	    	boolean esTafona = false;
	    	boolean esEnvasadora = false;
	    	
	    	Integer tipusUsuari = null;
	    	Integer tipusUsuariEstabliment = null;
	    	
	    	for(Object o: u.getGrups()){
	    		Grup g = (Grup)o;
	    		
	    		if(g.getId().equals(rolDoGestor)){
	    			tipusUsuari = Constants.DO_GESTOR;
	    		} else if(g.getId().equals(rolProductor)){
	    			esTafona = true;
	    		} else if(g.getId().equals(rolEnvasador)){
	    			esEnvasadora = true;
	    		} else if(g.getId().equals(rolDoControl)){
	    			tipusUsuari = Constants.DO_CONTROL;
	    		} else if(g.getId().equals(rolAdministracio)){
	    			tipusUsuari = Constants.ADMINISTRACIO;
	    		} else if(g.getId().equals(rolEstAdministrador)){
	    			tipusUsuariEstabliment = Constants.EST_ADMINISTRADOR;
	    		} else if(g.getId().equals(rolEstEncarregat)){
	    			tipusUsuariEstabliment = Constants.EST_ENCARREGAT;
	    		} else if(g.getId().equals(rolEstTreballador)){
	    			tipusUsuariEstabliment = Constants.EST_TREBALLADOR;
	    		} else if(g.getId().equals(rolEstConsulta)){
	    			tipusUsuariEstabliment = Constants.EST_CONSULTOR;
	    		} else if(g.getId().equals(rolOlivaTaula)){
	    			usuari.setOlivaTaula(true);
	    		}
	    		
	    		if(esTafona && esEnvasadora){
	    			tipusUsuari = Constants.TAFONA_ENVASADORA;
	    		} else if(esTafona && !esEnvasadora){
	    			tipusUsuari = Constants.TAFONA;
	    		} else if(!esTafona && esEnvasadora){
	    			tipusUsuari = Constants.ENVASADORA;
	    		}
	    	}
	    	
	    	usuari.setTipusUsuari(tipusUsuari);
	    	usuari.setTipusUsuariEstabliment(tipusUsuariEstabliment);
	    	
		} else if(request.isUserInRole(rolEstAdministrador) && (!isFormSubmission(request)) && isCreate(request)){
			oliUsuariEjb.setHibernateTemplate(getHibernateTemplate());
			Usuari u = oliUsuariEjb.verificaLogin();
			usuari = new UsuariCommand();
			usuari.fromUsuari(u);
	    	
	    	usuari.setGrups(u.getGrups());
	    	
	    	boolean esTafona = false;
	    	boolean esEnvasadora = false;
	    	
	    	Integer tipusUsuari = null;
	    	Integer tipusUsuariEstabliment = null;
	    	
	    	for(Object o: u.getGrups()){
	    		Grup g = (Grup)o;
	    		
	    		if(g.getId().equals(rolDoGestor)){
	    			tipusUsuari = Constants.DO_GESTOR;
	    		} else if(g.getId().equals(rolProductor)){
	    			esTafona = true;
	    		} else if(g.getId().equals(rolEnvasador)){
	    			esEnvasadora = true;
	    		} else if(g.getId().equals(rolDoControl)){
	    			tipusUsuari = Constants.DO_CONTROL;
	    		} else if(g.getId().equals(rolAdministracio)){
	    			tipusUsuari = Constants.ADMINISTRACIO;
	    		} else if(g.getId().equals(rolEstAdministrador)){
	    			tipusUsuariEstabliment = Constants.EST_ADMINISTRADOR;
	    		} else if(g.getId().equals(rolEstEncarregat)){
	    			tipusUsuariEstabliment = Constants.EST_ENCARREGAT;
	    		} else if(g.getId().equals(rolEstTreballador)){
	    			tipusUsuariEstabliment = Constants.EST_TREBALLADOR;
	    		} else if(g.getId().equals(rolEstConsulta)){
	    			tipusUsuariEstabliment = Constants.EST_CONSULTOR;
	    		} else if(g.getId().equals(rolOlivaTaula)){
	    			usuari.setOlivaTaula(true);
	    		}
	    		
	    		if(esTafona && esEnvasadora){
	    			tipusUsuari = Constants.TAFONA_ENVASADORA;
	    		} else if(esTafona && !esEnvasadora){
	    			tipusUsuari = Constants.TAFONA;
	    		} else if(!esTafona && esEnvasadora){
	    			tipusUsuari = Constants.ENVASADORA;
	    		}
	    	}
	    	
	    	usuari.setTipusUsuari(tipusUsuari);
	    	usuari.setTipusUsuariEstabliment(tipusUsuariEstabliment);
		}
		myModel.put("usuariCommand", usuari);
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		UsuariCommand usuari = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Usuari u = oliInfraestructuraEjb.usuariAmbId(codi);
				usuari = new UsuariCommand();
				usuari.fromUsuari(u);

				String[] grupIds = new String[usuari.getGrups().size()];
	            int index = 0;
	            for (Iterator it = usuari.getGrups().iterator(); it.hasNext();) {
	            	grupIds[index++] = ((Grup)it.next()).getId();
	            }
//	            usuari.setGrupsArray(grupIds);
	            
	            //Para cuando falla la validación
	            if(u.getOlivicultors()!= null && u.getOlivicultors().size()>0 ){
	            	String olivi = "";
		            for (Iterator it = usuari.getOlivicultors().iterator(); it.hasNext();) {
		            	olivi = ((Olivicultor)it.next()).getNom();		            	
		            }
		            usuari.setOlivicultorName(olivi);
	            }
//	            if(u.getOlivicultor()!= null){
//		            usuari.setOlivicultorName(u.getOlivicultor().getNom());
//	            }
	            //=================================================================================
	            //TODO: Un usuari realment només pot pertanyer a un establiment.
	            // Ara bé, aquest establiment es pot duplicar cada campanya.
	            // Crec que aquí seria suficient agafant el nom del primer establiment de l'usuari.
	            Long establimentId = null;
	            Boolean olivaTaula = false;
	            if(u.getEstabliments()!= null && u.getEstabliments().size()>0 ){
	            	String establecimiento = "";
	            	for(Object establiment: u.getEstabliments()){
	            		establecimiento = ((Establiment)establiment).getNom();	
		            	establimentId = ((Establiment)establiment).getId();
	            	}
//		            for (Iterator it = usuari.getEstabliments().iterator(); it.hasNext();) {
//		            	establecimiento = ((Establiment)it.next()).getNom();	
//		            	establimentId = ((Establiment)it.next()).getId();
//		            }
		            usuari.setEstablimentName(establecimiento);
	            }	
//	            if (u.getEstabliment() != null){
//	            	usuari.setEstablimentName(u.getEstabliment().getNom());
//	            }
	            //=================================================================================
	        
	            if (usuari.getGrups().size() > 0){
//	            	if (grupIds[0].equals(rolAdministracio)){
//	            		usuari.setTipusUsuari(Constants.ADMINISTRACIO);
//	            	}else if (grupIds[0].equals(rolDoGestor)){
//	            		usuari.setTipusUsuari(Constants.DO_GESTOR);
//	            	}else if (grupIds[0].equals(rolDoControl)){
//	            		usuari.setTipusUsuari(Constants.DO_CONTROL);
//	            	}else if (grupIds[0].equals(rolProductor)){
//	            		if (usuari.getGrups().size() > 1){
//	            			usuari.setTipusUsuari(Constants.TAFONA_ENVASADORA);
//	            			usuari.setEstablimentIdTafEnv(establimentId);
//	            		} else {
//	            			usuari.setTipusUsuari(Constants.TAFONA);
//	            			usuari.setEstablimentIdTafona(establimentId);
//	            		}
//	            	}else if (grupIds[0].equals(rolEnvasador)){
//	            		if (usuari.getGrups().size() > 1){
//	            			usuari.setTipusUsuari(Constants.TAFONA_ENVASADORA);
//	            			usuari.setEstablimentIdTafEnv(establimentId);
//	            		} else {
//	            			usuari.setTipusUsuari(Constants.ENVASADORA);
//	            			usuari.setEstablimentIdEnvasadora(establimentId);
//	            		}
//	            	}
	            	
	            	
	            	boolean esTafona = false;
	    	    	boolean esEnvasadora = false;
	            	
	            	Integer tipusUsuari = null;
	    	    	Integer tipusUsuariEstabliment = null;
	    	    	
	    	    	for(Object o: u.getGrups()){
	    	    		Grup g = (Grup)o;
	    	    		
	    	    		if(g.getId().equals(rolDoGestor)){
	    	    			tipusUsuari = Constants.DO_GESTOR;
	    	    		} else if(g.getId().equals(rolProductor)){
	    	    			esTafona = true;
	    	    		} else if(g.getId().equals(rolEnvasador)){
	    	    			esEnvasadora = true;
	    	    		} else if(g.getId().equals(rolDoControl)){
	    	    			tipusUsuari = Constants.DO_CONTROL;
	    	    		} else if(g.getId().equals(rolAdministracio)){
	    	    			tipusUsuari = Constants.ADMINISTRACIO;
	    	    		} else if(g.getId().equals(rolEstAdministrador)){
	    	    			tipusUsuariEstabliment = Constants.EST_ADMINISTRADOR;
	    	    		} else if(g.getId().equals(rolEstEncarregat)){
	    	    			tipusUsuariEstabliment = Constants.EST_ENCARREGAT;
	    	    		} else if(g.getId().equals(rolEstTreballador)){
	    	    			tipusUsuariEstabliment = Constants.EST_TREBALLADOR;
	    	    		} else if(g.getId().equals(rolEstConsulta)){
	    	    			tipusUsuariEstabliment = Constants.EST_CONSULTOR;
	    	    		} else if(g.getId().equals(rolOlivaTaula)){
	    	    			usuari.setOlivaTaula(true);
	    	    		}
	    	    		
	    	    		if(esTafona && esEnvasadora){
	    	    			tipusUsuari = Constants.TAFONA_ENVASADORA;
	    	    			usuari.setEstablimentIdTafEnv(establimentId);
	    	    		} else if(esTafona && !esEnvasadora){
	    	    			tipusUsuari = Constants.TAFONA;
	    	    			usuari.setEstablimentIdTafona(establimentId);
	    	    		} else if(!esTafona && esEnvasadora){
	    	    			tipusUsuari = Constants.ENVASADORA;
	    	    			usuari.setEstablimentIdEnvasadora(establimentId);
	    	    		}
	    	    	}
	    	    	
	    	    	usuari.setGrups(u.getGrups());
	    	    	usuari.setTipusUsuari(tipusUsuari);
	    	    	usuari.setTipusUsuariEstabliment(tipusUsuariEstabliment);

	            	
	            }
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			usuari = new UsuariCommand();
		}
		if (isCreate(request)) {
			usuari.setActiu(new Boolean(true));
		}
		return usuari;
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
		binder.registerCustomEditor(
				Integer.class,
	    		new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(
				Double.class,
				new CustomNumberEditor(Double.class, true));
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


	public void setOliUsuariEjb(OliUsuariEjb oliUsuariEjb) {
		this.oliUsuariEjb = oliUsuariEjb;
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
	 * Injecció de la dependència rolEnvasador
	 * @param rolEnvasador La classe a injectar.
	 */
	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	/**
	 * Injecció de la dependència rolOlivicultor
	 * @param rolOlivicultor La classe a injectar.
	 */
	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
	}

	/**
	 * Injecció de la dependència rolProductor
	 * @param rolProductor La classe a injectar.
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}

	/**
	 * Injecció de la dependència rolControl
	 * @param rolControl La classe a injectar.
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}

	/**
	 * Injecció de la dependència rolAdministracio
	 * @param rolAdministracio La classe a injectar.
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}

	

	public void setRolEstAdministrador(String rolEstAdministrador) {
		this.rolEstAdministrador = rolEstAdministrador;
	}

	public void setRolEstEncarregat(String rolEstEncarregat) {
		this.rolEstEncarregat = rolEstEncarregat;
	}

	public void setRolEstTreballador(String rolEstTreballador) {
		this.rolEstTreballador = rolEstTreballador;
	}

	public void setRolEstConsulta(String rolEstConsulta) {
		this.rolEstConsulta = rolEstConsulta;
	}

	public void setRolOlivaTaula(String rolOlivaTaula) {
		this.rolOlivaTaula = rolOlivaTaula;
	}


	/**
	 * Injecció de la dependència usuariSessionKey
	 * @param usuariSessionKey La classe a injectar.
	 */
	public void setUsuariSessionKey(String usuariSessionKey) {
		this.usuariSessionKey = usuariSessionKey;
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