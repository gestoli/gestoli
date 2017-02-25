/**
 * TrasllatDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Avis;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Trasllat;

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
public class TrasllatDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(TrasllatDelegate.class);
	private String listView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private ApplicationContext applicationContext;
    private String establimentSessionKey;
    private String retorno; 
	private HibernateTemplate hibernateTemplate;
	private String rolDoControlador;
	private String rolDoGestor;
	private String emailFrom;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private MessageSource messageSource;

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
	    		
	        	Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		   		Long idEstablecimiento =  est.getId();
		   		String tipus = request.getParameter("tipus");
		   		Long trasllatId = null;
		   		Trasllat t = null;
		   		if(request.getParameter("trasllatId")!= null){
		   			trasllatId = Long.valueOf(request.getParameter("trasllatId"));
		   			t = oliInfraestructuraEjb.findTrasllatById(trasllatId);
		   		}
		   		
		   		String esDiposit = null;
		   		if(request.getParameter("esDiposit")!= null){
		   			esDiposit = request.getParameter("esDiposit");
		   		}
		   		
		   		String aceptar = request.getParameter("aceptar");
		   		
		   		String dataString = request.getParameter("data");
		   		boolean dataplena = true;
	        	boolean processoscorrecte = true;
	        	boolean dataposteriortrasllat = true;
	        	Date data = new Date();
			   	
	        	if(tipus!= null && aceptar != null  && aceptar.equals("t") && (tipus.equals("recibir") || tipus.equals("aceptardevolver")) ){
		        	if( (dataString == null || dataString.equals(""))){
		        		dataplena = false;
		        	} else if(dataString != null && !dataString.equals("")) {
		        		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				        data = formatter.parse(dataString);
				        
		        		Collection diposits = t.getDiposits();
		        		
		        		for(Iterator it=diposits.iterator(); it.hasNext();){
		        			Diposit d = (Diposit)it.next();
		        			
		        			if(!oliInfraestructuraEjb.esDataDipositCorrecte(data, d.getId())){
		        				processoscorrecte = false;
		        			}
		        			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(data)){
		        				processoscorrecte = false;
		    				}
		        		}
		        		
		        		if(t.getData().after(data)){
		        			dataposteriortrasllat = false;
		        		}
		        	}
	        	}

	        	if(dataplena && processoscorrecte && dataposteriortrasllat){
			   		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
		   			oliProcessosEjb.setMessageSourceAccessor(new MessageSourceAccessor(messageSource));
		   			oliProcessosEjb.setTipusEstablimentTafona(tipusEstablimentTafona);
					oliProcessosEjb.setTipusEstablimentEnvasadora(tipusEstablimentEnvasadora);
					oliProcessosEjb.setTipusEstablimentTafonaEnvasadora(tipusEstablimentTafonaEnvasadora);
					
			   		if(tipus!= null && !tipus.equals("") && trasllatId!= null && (esDiposit == null || esDiposit.equals("t"))){
		   				if(tipus.equals("recibir") && aceptar.equals("t") ){
			   				oliProcessosEjb.aceptarTraslado(trasllatId, data);
			   			}else if(tipus.equals("recibir") && aceptar.equals("f") ){
			   				oliProcessosEjb.rechazarTraslado(trasllatId);
			   			}else if(tipus.equals("devolver") && aceptar.equals("t") ){
			   				//Comprovem dos casos
			   				//1- si origen ha enviat contenidor buit s'ha de tornar ple
			   				//2- si origen ha enviat contenidor ple s'ha de tornar buit
			   				Double quantitatARetornar = 0.0;
			   				for(Iterator it = t.getDiposits().iterator(); it.hasNext();){
			   					Diposit d = (Diposit)it.next();
			   					
			   					if(d.getVolumActual() != null)
			   						quantitatARetornar += d.getVolumActual();
			   				}
//			   				if(t.getQuantitatEnviament() > 0 && quantitatARetornar > 0){
//			   					ControllerUtils.saveMessageError(request, missatge("error.trasllat.retornBuit", request));
////			   				} else if(t.getQuantitatEnviament() == 0 && quantitatARetornar == 0){
////			   					ControllerUtils.saveMessageError(request, missatge("error.trasllat.retornPle", request));
//			   				} else {
			   					oliProcessosEjb.devolverTraslado(trasllatId, rolDoControlador, rolDoGestor, emailFrom);
			   					Long[] volants = {trasllatId};
			   					ControllerUtils.showVolantCirculacio(request, volants, 1);
//			   				}
			   			}else if(tipus.equals("aceptardevolver") && aceptar.equals("t") ){
			   				oliProcessosEjb.acceptarDevolverTraslado(trasllatId,retorno,data);
			   			}
			   		}		   
			   		
			   		if(tipus!= null && !tipus.equals("") && trasllatId!= null && (esDiposit != null && esDiposit.equals("f"))){
		   				if(tipus.equals("recibir") && aceptar.equals("f") ){
			   				oliProcessosEjb.rechazarTraslado(trasllatId);
			   			}
			   		}	
	        	} else {
	        		if(!dataplena)
	        			ControllerUtils.saveMessageError(request, missatge("error.dataExecucio.buit", request));
	        		
	        		if(!processoscorrecte)
	        			ControllerUtils.saveMessageError(request, missatge("error.dataExecucio.posterior", request));
	        		
	        		if(!dataposteriortrasllat)
	        			ControllerUtils.saveMessageError(request, missatge("error.dataExecucio.dataposteriortrasllat", request));
	        	}
	        	
	        	if (tipus!= null && aceptar != null  && aceptar.equals("t") && tipus.equals("avis")){
	        		Long avisId = null;
			   		Avis avis = null;
			   		if(request.getParameter("avisId")!= null){
			   			avisId = Long.valueOf(request.getParameter("avisId"));
				   		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			   			oliInfraestructuraEjb.avisActualitzaData(avisId);
			   		}
	        	}
		   		
		   		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	Collection llistatPendientesAceptar = oliInfraestructuraEjb.findTrasllatsByEstablimentPendientesAceptar(idEstablecimiento);
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	Collection llistatPendientesDevolver = oliInfraestructuraEjb.findTrasllatsByEstablimentPendientesDevolver(idEstablecimiento);
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	Collection llistatPendientesAceptarDevolver = oliInfraestructuraEjb.findTrasllatsByEstablimentPendientesAceptarDevolver(idEstablecimiento);
	        	
	        	
	        	String lang = Idioma.getLang(request);
				ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	Collection llistatAvisosPendientes = oliInfraestructuraEjb.avisCercaPendentsPerEstabliment(idEstablecimiento);
	        	for (Object avis: llistatAvisosPendientes){
	        		if ("1".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.diari"));
	        		else if ("7".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.setmanal"));
	        		else if ("15".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.quinzenal"));
	        		else if ("30".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.mensual"));
	        		else if ("60".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.bimensual"));
	        		else if ("90".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.trimestral"));
	        		else if ("180".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.semestral"));
	        		else if ("365".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.anual"));
	        		else if ("730".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.bianual"));
	        		else if ("1095".equals(((Avis)avis).getFrecuencia()))
	        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.trianual"));

	        		((Avis)avis).setTipus(bundle.getString("avis.tipus."+((Avis)avis).getTipus()).toString());
	        	}
	        	
	        	
	        	myModel.put("llistatPendientesAceptar", llistatPendientesAceptar);
	        	myModel.put("llistatPendientesDevolver", llistatPendientesDevolver);
	        	myModel.put("llistatPendientesAceptarDevolver", llistatPendientesAceptarDevolver);
	        	myModel.put("llistatAvisosPendientes", llistatAvisosPendientes);
	        	myModel.put("path", "tareas_pendientes");
	            logger.info("Obtenemos el listado de tareas pendientes ");  
	            if(llistatPendientesAceptar.size()== 0 && llistatPendientesDevolver.size() == 0 && llistatPendientesAceptarDevolver.size() == 0){
	            	ControllerUtils.saveMessageError(request, missatge("manteniment.tareasPendientes.no", request));
	            }
	         
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de diposits", ex);
            ControllerUtils.saveMessageError(request, missatge("diposit.missatge.llistat.no", request));
        }
        return new ModelAndView(listView, myModel);
    }

    /**
     * Inyección de la dependencia listView
     * @param listView la cadena a inyectar.
     */
    public void setListView(String listView) {
        this.listView = listView;
    }
   
    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

    /**
     * Inyección de la dependencia oliProcessosEjb
     * @param oliProcessosEjb La clase a inyectar.
     */
    public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
        this.oliProcessosEjb = oliProcessosEjb;
    }

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }
    
    /**
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
	
	 public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}

	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}

	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	/**
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setRetorno(String retorno) {
		this.retorno = retorno;
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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}

