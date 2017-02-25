/**
 * ConsultaOliComercialitzatDelegate.java
 */
package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;

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
public class ConsultaOliComercialitzatDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(ConsultaOliComercialitzatDelegate.class);

	private String listView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliConsultaEjb oliConsultaEjb;
    private ApplicationContext applicationContext;
    private String rolDoGestor;
	private String rolAdministracio;
	private String rolDoControl;
	private String campanyaSessionKey;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	
    private String desqualificat;
	private String pendentQualificar;
	private String qualificat;

	private HibernateTemplate hibernateTemplate;

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
        	Collection llistatEstabliments = new ArrayList();        
        	Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        	List tipos = new ArrayList();
        	tipos.add(tipusEstablimentEnvasadora);
        	tipos.add(tipusEstablimentTafonaEnvasadora);
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio)|| request.isUserInRole(rolDoControl)) {   
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistatEstabliments = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanyaId, tipos.toArray());
        	}
        	
        	Calendar avui = Calendar.getInstance();
        	Calendar principiAny = Calendar.getInstance();
        	principiAny.set(Calendar.DAY_OF_MONTH, 1);
        	principiAny.set(Calendar.MONTH, Calendar.JANUARY);
        	
        	Collection llistat = new ArrayList();
        	for(Object o: llistatEstabliments){
        		Establiment e = (Establiment)o;
        		
    		   	Double dipositDO = 0.0;
    		   	Double dipositPendent = 0.0;
    		   	Double dipositNoDO = 0.0;
    		   	
    		   	Integer lotDO = 0;
    		   	Integer lotNoDO = 0;
        		
    		   		
        	 	// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
    		   	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		   	Collection listaDepositos = oliConsultaEjb.getSortidaDipositVendaEntreDiasEnEstablecimiento(principiAny.getTime(), new Date(), e.getId());
    		   
    		   	
        		for(Object obj: listaDepositos){
        			SortidaDiposit deposit = (SortidaDiposit)obj;
    		   		
    		   		if(deposit.getCategoriaOli().getId().toString().equals(qualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositDO += deposit.getLitres(); 
    		   		} else if(deposit.getCategoriaOli().getId().toString().equals(pendentQualificar)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositPendent += deposit.getLitres(); 
    		   		} else if(deposit.getCategoriaOli().getId().toString().equals(desqualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositNoDO += deposit.getLitres(); 
    		   		}
    		   	}
    		   	
    		   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		        oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		        Collection listaLotes = oliConsultaEjb.getSortidaLotVendaEntreDiasEnEstablecimiento(principiAny.getTime(), new Date(), e.getId());
		        
		        for(Object obj: listaLotes){
		        	SortidaLot sortidalot = (SortidaLot)obj;
		        	
    		   		if(sortidalot.getLot().getPartidaOli().getCategoriaOli().getId().toString().equals(qualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			lotDO += sortidalot.getVendaNumeroBotelles(); 
    		   		} else if(sortidalot.getLot().getPartidaOli().getCategoriaOli().getId().toString().equals(desqualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			lotNoDO += sortidalot.getVendaNumeroBotelles(); 
    		   		}
    		   	}
			        
        		Object[] item = {e.getId(),
        						e.getNom(),
        						e.getCif(),
        						dipositDO,
        						dipositPendent,
        						dipositNoDO,
        						lotDO,
        						lotNoDO,
        						(dipositDO + dipositPendent + dipositNoDO + lotDO + lotNoDO)}; //Total oli
        		
        		llistat.add(item);
        	}
        	
        	myModel.put("llistat", llistat);
        	myModel.put("path", "lista_establecimientos_oliComercialitzat");
            logger.info("Obtenint llistat de establiments: " + llistat.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de establiments", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no", request));
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
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
	
	
	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}
	
	
	  /**
	 * Injecció de la dependència tipusEstablimentTafona
	 * @param tipusEstablimentTafona La classe a injectar.
	 */
	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}
	
	 /**
	 * Injecció de la dependència tipusEstablimentTafonaEnvasadora
	 * @param tipusEstablimentTafona La classe a injectar.
	 */
	public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	
	/**
	 * Injecció de la dependència rolOlivicultor
	 * @param rolAdministracio La classe a injectar.
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}
	

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    
    /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
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



	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}



	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}



	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}



	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}

	
}

