/**
 * MarcaDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;
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
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.AutocontrolOliva;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Establiment;

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
 * @author Carlos Pérez (cperez@at4.net)
 */
public class AutocontrolDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(AutocontrolDelegate.class);

	private String listView;
	private String seleccioSessionKeyOrigen;	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliConsultaEjb oliConsultaEjb;
	private String establimentSessionKey;
	private ApplicationContext applicationContext;

	private HibernateTemplate hibernateTemplate;
	


	/**
	 * Retorna una lista con el contenido de la tabla.
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView list(
			HttpServletRequest request,
			HttpServletResponse response) {
		Map myModel = new HashMap();
		HttpSession session = request.getSession();
		try {
			
			Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
			Collection llistat = null ;
			String nomBota = null;
			if(origenList != null && origenList.size()>0){
				Object[] botes = origenList.toArray();
				oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
				llistat = oliConsultaEjb.findAutocontrolPerBota((Long)botes[0]); 
				if(llistat!= null && llistat.iterator().hasNext()){
					AutocontrolOliva autocontrol = (AutocontrolOliva)(llistat.iterator().next());
					Bota bota = oliInfraestructuraEjb.botaAmbId(autocontrol.getBota());
					myModel.put("tipusOlivaTaula", bota.getTipusOlivaTaula());
					nomBota = bota.getIdentificador();
				}else{
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Bota bota = oliInfraestructuraEjb.botaAmbId((Long)botes[0]);
					if(bota!= null){
						nomBota = bota.getIdentificador();
					}					
				}
			}
			
			myModel.put("llistat", llistat);
			if(request.getParameter("fromEstabliment")!= null && request.getParameter("fromEstabliment").equalsIgnoreCase("true")){
				if (request.getParameter("establimentId") != null && !request.getParameter("establimentId").equalsIgnoreCase("")){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Establiment establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(Long.valueOf(request.getParameter("establimentId")));
    				if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    					if(nomBota!= null){
    						myModel.put("path_extension1", nomBota+" ("+establiment.getNom()+")");
    					}else{
    						myModel.put("path_extension1", establiment.getNom());
    					}    					
    				}
    			}        		
				myModel.put("path", "lista_autocontrolEstabliment");
				
			}else{
				myModel.put("path", "lista_autocontrolEstabliment");
				if(nomBota!= null){
					myModel.put("path_extension1", nomBota);
				}
			}
			       	
			logger.info("Obtenint llistat de autocontrols: " + llistat.size() + " registres trobats");								
			
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de analiticas", ex);
			ControllerUtils.saveMessageError(request, missatge("analitica.missatge.llistat.no", request));
		}
		return new ModelAndView(listView, myModel);
	}
	
    public ModelAndView delete(
            HttpServletRequest request,
            HttpServletResponse response) {
    	Map myModel = new HashMap();
    	
        String id = request.getParameter("id");
        String idEstabliment = request.getParameter("establimentId");
        String idZona = request.getParameter("zonaId");
        String tipusProces = request.getParameter("tipusProces");
        String pas = request.getParameter("pas");
        String fromEstabliment = request.getParameter("fromEstabliment");
        
        try {
            Long lid = new Long(Long.parseLong(id));

        	logger.info("Eliminant l'autocontrol[" + lid + "]");
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	AutocontrolOliva autocontrol = oliInfraestructuraEjb.autocontrolAmbId(lid);
        	oliInfraestructuraEjb.autocontrolEsborrar(autocontrol);
        	ControllerUtils.saveMessageInfo(request, missatge("autocontrol.missatge.eliminat.ok", request));
        } catch (Exception ex) {
            logger.error("Error esborrant l'analitica [" + id + "]", ex);
            ControllerUtils.saveMessageError(request, missatge("autocontrol.missatge.eliminat.ko", request));
        }

        return new ModelAndView("redirect:ProcesInici.html?tipusProces="+tipusProces+"&pas="+pas+"&zonaId="+idZona+"&establimentId="+idEstabliment+"&fromEstabliment="+fromEstabliment+"", myModel);
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
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}

	/**
     * Injecció de la dependència seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }
	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}
	
	private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }
	
	/**
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
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

	
}