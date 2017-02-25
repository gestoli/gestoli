/**
 * UsuariInterceptor.java
 *
 */
package es.caib.gestoli.front.spring.interceptor;


import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityAssociation;
import org.jboss.security.SimplePrincipal;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import es.caib.gestoli.front.util.ApplicationContextHolder;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliUsuariEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Idioma;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Usuari;
import es.caib.gestoli.logic.model.Zona;


/**
 * Carrega informació de l'usuari a la sessió i s'encarrega de
 * propagar les credencials de seguretat a les cridades a EJBs.
 * Aquesta classe es cridada cada vegada que es fa una petició
 * HTTP a l'spring.
 * 
 * @author Oriol Barnés <obarnes@at4.net>
 */
public class UsuariInterceptor extends HandlerInterceptorAdapter {

	private String rolAdministracio;
	private String rolDoGestor;
	private String rolDoControl;
	private String rolOlivicultor;
	private String rolProductor;
	private String rolEnvasador;
	private String rolEstAdministrador;
	private String rolEstEncarregat;
	private String rolEstTreballador;
	private String rolEstConsulta;
	private String rolOlivaTaula;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;
	private String esDoControladorRequestKey;
	private String esOlivicultorRequestKey;
	private String esProductorRequestKey;
	private String esEnvasadorRequestKey;
	private String esEstAdministradorRequestKey;
	private String esEstEncarregatRequestKey;
	private String esEstTreballadorRequestKey;
	private String esEstConsultaRequestKey;
	private String esOlivaTaulaRequestKey;

	private String usuariSessionKey;
	private String establimentSessionKey;
	private String zonasActivasEstablimentSession;
	private String zonasFicticiasEstablimentSession;
	private String campanyaSessionKey;
	private String consultaUsuariKey;
	private String consultaPasswdKey;
	private OliUsuariEjb oliUsuariEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliInformacioEjb oliInformacioEjb;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private HibernateTemplate hibernateTemplate;
	
	private String entornProves;




	/**
	 * Mètode callback que es crida cada vegada que arriba una
	 * petició.
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
	throws Exception {
		
//		oliUsuariEjb.setHibernateTemplate(getHibernateTemplate());
//		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		
		HttpSession session = request.getSession();
		if (request.getUserPrincipal() != null) {
			
			oliUsuariEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
			
			// Propaga les credencials de seguretat a les cridades que es
			// puguin realitzar a l'EJB
			String serverInfo = session.getServletContext().getServerInfo();
			if (serverInfo.indexOf("5.5") != -1) {
				SecurityAssociation.setServer();
				SecurityAssociation.setPrincipal(new SimplePrincipal(request.getUserPrincipal().getName()));
				SecurityAssociation.setCredential(getContrasenya(request));
			}
			Usuari u = (Usuari)session.getAttribute(usuariSessionKey);
			// Si no hi ha informació de l'usuari a la sessió, la 
			// inicialitza
			if (u == null) {
				// Guarda a la sessió les dades de l'usuari
				u = oliUsuariEjb.verificaLogin();
				session.setAttribute(usuariSessionKey, u);

				//Ponemos el idioma del web en el idioma del usuario				
				Locale locale = new Locale(u.getIdioma().getId());
				CookieLocaleResolver localeResolver = (CookieLocaleResolver)ApplicationContextHolder.getInstance().getService("localeResolver");
				if (localeResolver != null) {
					localeResolver.setLocale(request, response, locale);			
				}				
			}
			
			//Entorn de proves
			request.setAttribute("entornProves", entornProves);
			

			// Recuperamos la campanya actual y la ponemos en session si no existe
			Long campanyaId = (Long)session.getAttribute(campanyaSessionKey);
			if (campanyaId == null) {
				campanyaId = oliInfraestructuraEjb.campanyaCercaActual();
				if (campanyaId != null ) {
					session.setAttribute(campanyaSessionKey, campanyaId);
					request.setAttribute("idCampanya", campanyaId.toString());
				}
			}else{
				request.setAttribute("idCampanya", campanyaId.toString());
			}

			// Guarda la informació dels rols de l'usuari
			if (request.isUserInRole(rolAdministracio)) {
				request.setAttribute(esAdministracioRequestKey, esAdministracioRequestKey);
			}
			if (request.isUserInRole(rolOlivaTaula)) {
				request.setAttribute(esOlivaTaulaRequestKey, esOlivaTaulaRequestKey);
			}

			if (request.isUserInRole(rolDoGestor)) {
				request.setAttribute(esDoGestorRequestKey, esDoGestorRequestKey);
				//Comprobamos que existen en la BBDD olivicultores dados de alta DO
				boolean existenOlivicultoresActivos = oliInfraestructuraEjb.existenOlivicultoresAltaDOConFincas();
				if (existenOlivicultoresActivos)
					request.setAttribute("olivDO", Boolean.valueOf(existenOlivicultoresActivos));
			}

			if (request.isUserInRole(rolDoControl)) {
				request.setAttribute(esDoControladorRequestKey, esDoControladorRequestKey);
			}
			if (request.isUserInRole(rolOlivicultor)) {
				request.setAttribute(esOlivicultorRequestKey, esOlivicultorRequestKey);
				//Comprobamos que esta dado de alta como olivicultor y si altaDO esta a true
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Olivicultor oliv = oliInfraestructuraEjb.olivicultorUsuari(u.getId(), campanyaId);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				boolean tieneFincas = oliInfraestructuraEjb.olivicultorUsuariTieneFincas(u.getId(), campanyaId);

				boolean olivAltaDo = oliv != null && oliv.getAltaDO() != null && oliv.getAltaDO().booleanValue();
				boolean olivCartilla = oliv != null && oliv.getCartilla() != null && oliv.getCartilla().booleanValue();

				boolean olivFactura = false;
				if (olivAltaDo && olivCartilla){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					olivFactura = oliInfraestructuraEjb.facturaExisteByOliId(oliv.getId());
				}

				if (olivAltaDo) {
					request.setAttribute("oliv", oliv);
					if (tieneFincas)
						request.setAttribute("fincas", Boolean.valueOf(tieneFincas));
				}

				// Comprobem i guardem que tingui cartilla i factura actual
				request.setAttribute("oliConsultaCartilla", Boolean.valueOf(olivAltaDo && olivCartilla));
				request.setAttribute("oliConsultaFactura", Boolean.valueOf(olivAltaDo && olivCartilla && olivFactura));

				if (oliv!= null) {
					// Si no hay informaciones, no mostramos la opcion de menu "Informacion"
					boolean existenInformaciones = oliInformacioEjb.existenInformaciones();
					if (!existenInformaciones) {
						request.setAttribute("noExistenInformaciones", "t");
					} else {
						// Informaciones pendientes
						oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
						Boolean existenInformacionesPendientes =  new Boolean(oliInformacioEjb.olivicultorHaLeidoTodo(oliv.getId()));
						if(existenInformacionesPendientes != null && !existenInformacionesPendientes.booleanValue() ){
							request.setAttribute("existenInformacionesPendientes", "t");
						}
					}
				}
				
			}

			if (request.isUserInRole(rolProductor)) {
				request.setAttribute(esProductorRequestKey, esProductorRequestKey);
				// Guarda el Establiment a la sessió d'usuari en el cas de que no
				// hi sigui
				Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
				if (est == null) {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					est = oliInfraestructuraEjb.getEstablecimientoActivoByUsuari(u.getId(), campanyaId);
					if (est != null ) {
						session.setAttribute(establimentSessionKey, est);

						Set zonas = est.getZonas();
						for (Iterator it = zonas.iterator(); it.hasNext();){
							if (((Zona)it.next()).getActiu().booleanValue()){
								session.setAttribute(zonasActivasEstablimentSession, zonasActivasEstablimentSession);
								break;
							}
						}
						for (Iterator it = zonas.iterator(); it.hasNext();){
							if (((Zona)it.next()).getFictici().booleanValue()){
								session.setAttribute(zonasFicticiasEstablimentSession, zonasFicticiasEstablimentSession);
								break;
							}
						}
					}
				}
				if(est != null && est.getTipusEstabliment()!= null && est.getTipusEstabliment().getId()!= null && (est.getTipusEstabliment().getId().intValue() == tipusEstablimentTafona.intValue() || est.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue())){
					request.setAttribute("esTafona", "t");
				}
				if(est != null && est.getTipusEstabliment()!= null && est.getTipusEstabliment().getId()!= null && (est.getTipusEstabliment().getId().intValue() == tipusEstablimentEnvasadora.intValue() || est.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue())){
					request.setAttribute("esEnvasadora", "t");
				}
				
				if(est!= null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Boolean existenAccionesPendientes =  oliInfraestructuraEjb.existenAccionesPendientesByEstablecimiento(est.getId());
					if(existenAccionesPendientes != null && existenAccionesPendientes.booleanValue() ){
						request.setAttribute("existenAccionesPendientes", "t");
					}
				}
				
				if(est!= null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Boolean existenAvisosPendientes =  oliInfraestructuraEjb.avisExisteixenPendentsPerEstabliment(est.getId());
					if(existenAvisosPendientes != null && existenAvisosPendientes.booleanValue() ){
						request.setAttribute("existenAvisosPendientes", "t");
					}
				}
				
				if(est!= null){
					// Si no hay informaciones, no mostramos la opcion de menu "Informacion"
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					boolean existenInformaciones = oliInformacioEjb.existenInformaciones();
					if (!existenInformaciones) {
						request.setAttribute("noExistenInformaciones", "t");
					} else {
						// Informaciones pendientes
						oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
						Boolean existenInformacionesPendientes =  new Boolean(oliInformacioEjb.establecimientoHaLeidoTodo(est.getId()));
						if(existenInformacionesPendientes != null && !existenInformacionesPendientes.booleanValue() ){
							request.setAttribute("existenInformacionesPendientes", "t");
						}
					}
				}
				

			}				

			if (request.isUserInRole(rolEnvasador)) {
				request.setAttribute(esEnvasadorRequestKey, esEnvasadorRequestKey);
				//Guarda el Establiment a la sessió d'usuari en el cas de que no
				// hi sigui
				Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
				if (est == null) {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					est = oliInfraestructuraEjb.getEstablecimientoActivoByUsuari(u.getId(), campanyaId);
					if (est != null ) {
						session.setAttribute(establimentSessionKey, est);

						Set zonas = est.getZonas();
						for (Iterator it = zonas.iterator(); it.hasNext();){
							if (((Zona)it.next()).getActiu().booleanValue()){
								session.setAttribute(zonasActivasEstablimentSession, zonasActivasEstablimentSession);
								break;
							}
						}
						for (Iterator it = zonas.iterator(); it.hasNext();){
							if (((Zona)it.next()).getFictici().booleanValue()){
								session.setAttribute(zonasFicticiasEstablimentSession, zonasFicticiasEstablimentSession);
								break;
							}
						}
					}
				}
				
				if(est!= null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Boolean existenAccionesPendientes =  oliInfraestructuraEjb.existenAccionesPendientesByEstablecimiento(est.getId());
					if(existenAccionesPendientes != null && existenAccionesPendientes.booleanValue() ){
						request.setAttribute("existenAccionesPendientes", "t");
					}
				}
				
				if(est!= null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Boolean existenAvisosPendientes =  oliInfraestructuraEjb.avisExisteixenPendentsPerEstabliment(est.getId());
					if(existenAvisosPendientes != null && existenAvisosPendientes.booleanValue() ){
						request.setAttribute("existenAvisosPendientes", "t");
					}
				}
				
				if(est!= null) {
					// Si no hay informaciones, no mostramos la opcion de menu "Informacion"
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					boolean existenInformaciones = oliInformacioEjb.existenInformaciones();
					if (!existenInformaciones) {
						request.setAttribute("noExistenInformaciones", "t");
					} else {
						// Informaciones pendientes
						oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
						Boolean existenInformacionesPendientes =  new Boolean(oliInformacioEjb.establecimientoHaLeidoTodo(est.getId()));
						if(existenInformacionesPendientes != null && !existenInformacionesPendientes.booleanValue() ){
							request.setAttribute("existenInformacionesPendientes", "t");
						}
					}
				}
				
			}

			//Comprobamos si se ha producido un cambio de idioma y cambiamos el idioma del usuario
			if (request.getParameter("cambioIdioma")!= null && request.getParameter("cambioIdioma").equalsIgnoreCase("true") ) {
				if (request.getParameter("siteLanguage")!= null){
					Idioma idioma= new Idioma();
					idioma.setId(request.getParameter("siteLanguage"));
					u.setIdioma(idioma);
					oliInfraestructuraEjb.usuariModificar(u);
				}				
			}

			if (request.isUserInRole(rolEstAdministrador)) {
				request.setAttribute(esEstAdministradorRequestKey, esEstAdministradorRequestKey);
			}
			
			if(request.isUserInRole(rolEstEncarregat)) {
				request.setAttribute(esEstEncarregatRequestKey, esEstEncarregatRequestKey);
			}
			
			if (request.isUserInRole(rolEstTreballador)) {
				request.setAttribute(esEstTreballadorRequestKey, esEstTreballadorRequestKey);
			}
			
			if (request.isUserInRole(rolEstConsulta)) {
				request.setAttribute(esEstConsultaRequestKey, esEstConsultaRequestKey);
			}
			
		}
		
		if ( (request.getServletPath().equals("/login.html") || request.getServletPath().equals("/loginError.html")) &&
			!request.isRequestedSessionIdValid() && 
			(request.getParameter("caducat") == null || request.getParameter("caducat").equals("")) ){
			response.sendRedirect("Inici.html?caducat=true");
			return false;
		}
		
		return true;
	}



	/*
	 * Aconsegueix la contrasenya de l'objecte UserPrincipal
	 * (només funciona amb Tomcat!!!)
	 */
	private String getContrasenya(HttpServletRequest request) throws Exception {
		Principal principal = request.getUserPrincipal();
		Method m = principal.getClass().getMethod("getPassword", (Class[])null);
		return (String)m.invoke(principal, (Object[])null);
	}


	
	/**
	 * Injecció de la dependència usuariSessionKey
	 * @param usuariSessionKey La classe a injectar.
	 */
	public void setUsuariSessionKey(String usuariSessionKey) {
		this.usuariSessionKey = usuariSessionKey;
	}


	/**
	 * Injecció de la dependència usuariSessionKey
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}


	/**
	 * Injecció de la dependència zonasActivasEstablimentSession
	 * @param zonasActivasEstablimentSession La classe a injectar.
	 */
	public void setZonasActivasEstablimentSession(String zonasActivasEstablimentSession) {
		this.zonasActivasEstablimentSession = zonasActivasEstablimentSession;
	}


	/**
	 * @param zonasFicticiasEstablimentSession the zonasFicticiasEstablimentSession to set
	 */
	public void setZonasFicticiasEstablimentSession(
			String zonasFicticiasEstablimentSession) {
		this.zonasFicticiasEstablimentSession = zonasFicticiasEstablimentSession;
	}


	/**
	 * Injecció de la dependència establimentSessionKey
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}


	/**
	 * Injecció de la dependència consultaUsuariKey
	 * @param consultaUsuariKey La classe a injectar.
	 */
	public void setConsultaUsuariKey(String consultaUsuariKey) {
		this.consultaUsuariKey = consultaUsuariKey;
	}


	/**
	 * Injecció de la dependència consultaPasswdKey
	 * @param consultaPasswdKey La classe a injectar.
	 */
	public void setConsultaPasswdKey(String consultaPasswdKey) {
		this.consultaPasswdKey = consultaPasswdKey;
	}


	/**
	 * Injecció de la dependència esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}


	/**
	 * Injecció de la dependència esDoControladorRequestKey
	 * @param esDoControladorRequestKey La classe a injectar.
	 */
	public void setEsDoControladorRequestKey(String esDoControladorRequestKey) {
		this.esDoControladorRequestKey = esDoControladorRequestKey;
	}


	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
	}


	/**
	 * Injecció de la dependència esEnvasadorRequestKey
	 * @param esEnvasadorRequestKey La classe a injectar.
	 */
	public void setEsEnvasadorRequestKey(String esEnvasadorRequestKey) {
		this.esEnvasadorRequestKey = esEnvasadorRequestKey;
	}


	/**
	 * Injecció de la dependència esOlivicultorRequestKey
	 * @param esOlivicultorRequestKey La classe a injectar.
	 */
	public void setEsOlivicultorRequestKey(String esOlivicultorRequestKey) {
		this.esOlivicultorRequestKey = esOlivicultorRequestKey;
	}


	/**
	 * Injecció de la dependència esProductorRequestKey
	 * @param esProductorRequestKey La classe a injectar.
	 */
	public void setEsProductorRequestKey(String esProductorRequestKey) {
		this.esProductorRequestKey = esProductorRequestKey;
	}

	/**
	 * Injecció de la dependència esEstAdministradorRequestKey
	 * @param esEstAdministradorRequestKey La classe a injectar.
	 */
	public void setEsEstAdministradorRequestKey(String esEstAdministradorRequestKey) {
		this.esEstAdministradorRequestKey = esEstAdministradorRequestKey;
	}

	
	public void setRolEstEncarregat(String rolEstEncarregat) {
		this.rolEstEncarregat = rolEstEncarregat;
	}



	public void setEsEstEncarregatRequestKey(String esEstEncarregatRequestKey) {
		this.esEstEncarregatRequestKey = esEstEncarregatRequestKey;
	}



	/**
	 * Injecció de la dependència esEstTreballadorRequestKey
	 * @param esEstTreballadorRequestKey La classe a injectar.
	 */
	public void setEsEstTreballadorRequestKey(String esEstTreballadorRequestKey) {
		this.esEstTreballadorRequestKey = esEstTreballadorRequestKey;
	}

	/**
	 * Injecció de la dependència esEstConsultaRequestKey
	 * @param esEstConsultaRequestKey La classe a injectar.
	 */
	public void setEsEstConsultaRequestKey(String esEstConsultaRequestKey) {
		this.esEstConsultaRequestKey = esEstConsultaRequestKey;
	}


	/**
	 * Injecció de la dependència rolAdministracio
	 * @param rolAdministracio La classe a injectar.
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}


	/**
	 * Injecció de la dependència rolDoControl
	 * @param rolDoControl La classe a injectar.
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
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
	 * Injecció de la dependència oliUsuariEjb
	 * @param oliUsuariEjb La classe a injectar.
	 */
	public void setOliUsuariEjb(OliUsuariEjb oliUsuariEjb) {
		this.oliUsuariEjb = oliUsuariEjb;
	}


	/**
	 * Injecció de la dependència oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	/**
	 * Injecció de la dependència tipusEstablimentTafona
	 * @param tipusEstablimentTafona La classe a injectar.
	 */
	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}


	/**
	 * Injecció de la dependència tipusEstablimentEnvasadora
	 * @param tipusEstablimentEnvasadora La classe a injectar.
	 */
	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}


	/**
	 * Injecció de la dependència tipusEstablimentTafonaEnvasadora
	 * @param tipusEstablimentTafonaEnvasadora La classe a injectar.
	 */
	public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
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

	public OliInformacioEjb getOliInformacioEjb() {
		return oliInformacioEjb;
	}
	public void setOliInformacioEjb(OliInformacioEjb oliInformacioEjb) {
		this.oliInformacioEjb = oliInformacioEjb;
	}
	public void setEntornProves(String entornProves) {
		this.entornProves = entornProves;
	}
	public void setRolEstAdministrador(String rolEstAdministrador) {
		this.rolEstAdministrador = rolEstAdministrador;
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
	public void setEsOlivaTaulaRequestKey(String esOlivaTaulaRequestKey) {
		this.esOlivaTaulaRequestKey = esOlivaTaulaRequestKey;
	}
}