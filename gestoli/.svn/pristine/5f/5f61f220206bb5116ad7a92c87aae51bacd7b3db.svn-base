/**
 * EdicioProcessosDelegate.java
 *
 * Creada el 13 de novembre de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.SortidaOrujo;
import es.caib.gestoli.logic.model.Usuari;

/**
 * <p>Clase delegada del MultiActionController que trata
 * distintas acciones. La acción a executar está basada en el
 * contenido de un parámetro de la petición.</p>
 * <p>Los parámetros de la petición HTTP relevantes para esta
 * clase son:
 * <ul>
 *   <li><code>action</code>
 *    - determina la acción a executar.</li>
 *   <li><code>id</code>
 *    - Identificador del registro cuando la acción a ejecutar es
 *      "delete".</li>
 * </ul></p>
 * 
 * 
 */
public class EdicioProcessosDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(EdicioProcessosDelegate.class);
	private String listView;
    private String deleteView;
    private String editarView;
	private OliProcessosEjb oliProcessosEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;
	private String perdua;
	private String retorno;
	private String tancamentLlibres;
	private String entrada;
	private String pendentQualificacio;
	private String establimentSessionKey;
	private String emailFrom;
	private String rolDoControlador;
	private String rolDoGestor;
	private String stringAnalitica;
	private String desqualificat;
	

    /**
     * Retorna una lista con el contenido de la tabla.
     * 
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView list(
            HttpServletRequest request,
            HttpServletResponse response) {
        Map myModel = new HashMap();
        try {

        	HttpSession session = request.getSession();
    		       	
        	Usuari usuario = (Usuari)session.getAttribute("usuari");
        	Establiment est = null;
        	if(request.getAttribute("idCampanya")!= null ){
        		Long campanyaId = Long.valueOf((String)request.getAttribute("idCampanya"));
            	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
            	est = oliInfraestructuraEjb.getEstablecimientoActivoByUsuari(usuario.getId(), campanyaId);
        	}
        	
        	Long idEstablecimiento = null;
        	
    		if (est != null) {
    			idEstablecimiento =  est.getId();
    		}
    		int tipusUsuari = 0;
    		boolean oliva = false;
        	if (est != null) {
        		if (request.getAttribute("esTafona") == null && request.getAttribute("esEnvasadora") == null) {
        			// Envasador
        			tipusUsuari = 1;
        		} else {
        			if (request.getAttribute("esTafona") != null && request.getAttribute("esEnvasadora") != null) {
        				// Tafona envasadora
        				tipusUsuari = 3;
        			} else if (request.getAttribute("esTafona") != null) {
        				// Tafona
        				tipusUsuari = 2;
        			} else if (request.getAttribute("esEnvasadora") != null) {
        				// Envasadora
        				tipusUsuari = 1;
        			}
        		}
        		if (request.getAttribute("esOlivaTaula") != null) {
        			oliva = true;
        		}
        	} else {
        		// Controlador
        		tipusUsuari = 4;
        	}
        	
        	String di = request.getParameter("dataInici");
        	String df = request.getParameter("dataFi");
        	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	        	
        	Date dataInici = null;
        	Date dataFi = null;
        	
        	if (di != null && !di.equals("")){
        		dataInici = sdf.parse(di);
        	}
        	if (df != null && !df.equals("")){
        		dataFi = sdf.parse(df);
        	}
        	
        	if (dataInici != null && dataFi != null){
        		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection llistat = oliProcessosEjb.processosFindAllByEstablimentAndData(idEstablecimiento, perdua, pendentQualificacio, tipusUsuari, retorno, tancamentLlibres, entrada, stringAnalitica, desqualificat, dataInici, dataFi, oliva);

        		myModel.put("llistat", llistat);
        		logger.info("Obtenint llistat d'edició de processos: " + llistat.size() + " registres trobats");
        	} else if (request.getMethod().equalsIgnoreCase("post")) {
        		ControllerUtils.saveMessageError(request, missatge("edicioProcessos.missatge.dates", request));
        	}
        	myModel.put("path", "edicio_processos");
        	if (dataInici != null) myModel.put("dataInici", di);
        	if (dataFi != null) myModel.put("dataFi", df);

        } catch (Exception ex) {
            logger.error("Error obtenint llistat d'edició de processos", ex);
            ControllerUtils.saveMessageError(request, missatge("edicioProcessos.missatge.llistat.no", request));
        }
        return new ModelAndView(listView, myModel);
    }

    /**
     * Borra un registro y retorna el listado.
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView delete(
            HttpServletRequest request,
            HttpServletResponse response) {
    	Map myModel = new HashMap();
    	
    	Long id = null;
		int tipus = 0;
		
    	try{
    		id = new Long(request.getParameter("id"));
    		tipus = new Integer(request.getParameter("tipus")).intValue();

    		oliProcessosEjb.eliminarProces(id, tipus, rolDoControlador, rolDoGestor, emailFrom, pendentQualificacio, tancamentLlibres, stringAnalitica, desqualificat);
    		
    		String di = request.getParameter("dataInici");
        	String df = request.getParameter("dataFi");
        	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	        	
        	Date dataInici = null;
        	Date dataFi = null;
        	
        	if (di != null && !di.equals("")){
        		dataInici = sdf.parse(di);
        	}
        	if (df != null && !df.equals("")){
        		dataFi = sdf.parse(df);
        	}
        	
        	myModel.put("path", "edicio_processos");
        	if (dataInici != null) myModel.put("dataInici", di);
        	if (dataFi != null) myModel.put("dataFi", df);
        	
    		ControllerUtils.saveMessageInfo(request, missatge("edicioProcessos.missatge.borrar.ok", request));
    	} catch (Exception ex) {
    		logger.error("Error esborrant el proces [" + id + "]", ex);
    		ControllerUtils.saveMessageError(request, missatge("edicioProcessos.missatge.borrar.no", request));
    	}

        return new ModelAndView(deleteView, myModel);
    }


    /**
     * Inyección de la dependencia listView
     * @param listView la cadena a inyectar.
     */
    public void setListView(String listView) {
        this.listView = listView;
    }


    /**
     * Inyección de la dependencia deleteView
     * @param deleteView la cadena a inyectar.
     */
    public void setDeleteView(String deleteView) {
        this.deleteView = deleteView;
    }
	

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
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


	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public void setPerdua(String perdua) {
		this.perdua = perdua;
	}
	public void setTancamentLlibres(String tancamentLlibres) {
		this.tancamentLlibres = tancamentLlibres;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	
	public void setPendentQualificacio(String pendentQualificacio) {
		this.pendentQualificacio = pendentQualificacio;
	}

	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}


	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}


	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}


	public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}


	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}


	public void setStringAnalitica(String stringAnalitica) {
		this.stringAnalitica = stringAnalitica;
	}


	public void setEditarView(String editarView) {
		this.editarView = editarView;
	}

	
}

