/**
 * TancamentLlibresDipositFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.VarietatOli;



/**
 * <p>Controlador per a l'acció d'executar un proces d'entrada
 * de oliva.</p>
 * <p>Aquest controlador distingueix entre les peticions del
 * tipus GET y les de tipus POST. Si la petición es de tipus POST
 * s'executa l'acció de creació o d'edició. Si la petició es de
 * tipo GET només mostra la página.
 *
 * @author cperez <cperez@at4.net>
 */
public class TancamentLlibresDipositFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(TancamentLlibresDipositFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String establimentSessionKey;
    private String tancamentLlibres;
    
	private HibernateTemplate hibernateTemplate;
    
    /**
     * Es crida quan s'accepten les modificacions a l'objecte. Només
     * s'executa aquesta funció en el cas de que s'hagi executat la
     * validació sense produïr cap error.
     * 
     * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	
    	DipositCommand dipCommand = (DipositCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
//    	Integer[] varietats = pLot.getVarietatsArray();
    	
    	Diposit diposit = null;
		
			try{
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposit = oliInfraestructuraEjb.dipositAmbId(dipCommand.getId());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				CategoriaOli catOli = oliInfraestructuraEjb.findCategoriaOliById(dipCommand.getIdCategoriaOli());
				
				// Assignam les noves dades al dipòsit
				diposit.setAcidesa(dipCommand.getAcidesa());
//				diposit.setCategoriaOli(catOli);
				// TODO: Assignar partida
//				diposit.setPartidaOli();
				diposit.setVolumActual(dipCommand.getLitros());
				
				// Cream l'entrada corresponent
				EntradaDiposit entrada = new EntradaDiposit();
				entrada = new EntradaDiposit();
				entrada.setAcidesa(diposit.getAcidesa());
				entrada.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
				entrada.setPartidaOli(diposit.getPartidaOli());
				entrada.setData(dipCommand.getData());
				entrada.setDiposit(diposit);
				entrada.setElaboracio(null);
				entrada.setEstabliment(diposit.getEstabliment());
				entrada.setLitres(diposit.getVolumActual());
				entrada.setObservacions(tancamentLlibres + ". " + dipCommand.getObservacions());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.generarExistenciesDiposit(diposit, entrada);
				
				ControllerUtils.saveMessageInfo(request, missatge("tancamentLlibres.diposit.missatge.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant l'introducció d'existències dipòsit", e);
	             ControllerUtils.saveMessageError(request, missatge("tancamentLlibres.diposit.missatge.no"));
			}
		
		//String forward = getSuccessView()+ "?tipusProces=2&pas=2&id=" + idElaboracio;
		String desti = "EstablimentPrincipal.html";
		
		return new ModelAndView("redirect:" + desti);
    }

    /**
     * Retorna totes les dades del model necessàries per a mostrar
     * el formulari d'inserció (LOVs y coses per l'estil) si no
     * n'hi ha.
     * 
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        
    	DipositCommand dipCommand = (DipositCommand) command;
    	
        Map myModel = new HashMap();
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        
        // VARIEDADES DE OLIVA
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        myModel.put("varietatsOlivaArray", oliInfraestructuraEjb.varietatsOliva());
        
        // CATEGORIA ACEITE
        List catOli = new ArrayList();
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Iterator itCatOli = oliInfraestructuraEjb.categoriaOliCercaTots().iterator();
        while (itCatOli.hasNext()) {
        	CategoriaOli categoriaOli = (CategoriaOli) itCatOli.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(categoriaOli.getId()));
        	basic.setNom(missatge("consulta.general.camp.categoriaOli." + categoriaOli.getId()));
        	catOli.add(basic);
        }
        
        myModel.put("categoriasOli", catOli);
        
        // VARIEDAD ACEITE
        List varOli = new ArrayList();
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Iterator itVarOli = oliInfraestructuraEjb.varietatsOli().iterator();
        while (itVarOli.hasNext()) {
        	VarietatOli varietatOli = (VarietatOli) itVarOli.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(varietatOli.getId()));
        	basic.setNom(missatge("consulta.general.camp.varietatOli." + varietatOli.getId()));
        	varOli.add(basic);
        }
        
        myModel.put("variedadesOli", varOli);
        
        myModel.put("establiment", establiment);
        
        myModel.put("path", "crear_tancament_llibres_diposit");
        return myModel;
    }

    
    /**
     * En el cas de que sigui una edició retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creació
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	DipositCommand dipCommand = null;
    	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
//    	if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit dip = oliInfraestructuraEjb.dipositAmbId(codi);
				dipCommand = new DipositCommand();
				dipCommand.fromDiposit(dip);
				dipCommand.setData(new Date());
				
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
//		} else {
//			dipCommand = new DipositCommand();
//			dipCommand.setData(new Date());
//		}
    	
        return dipCommand;
    }

    /**
     * Configuració del <i>binder</i>. Si hi ha camps en el
     * <i>command</i> amb tipus que no siguin <i>String</i>
     * s'ha de configurar el seu corresponent binder aquí.
     * 
     * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(
            HttpServletRequest request,
            ServletRequestDataBinder binder)
    throws Exception {
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
	 * @param infraestructuraEjb the infraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
	 * @param oliProcessosEjb the oliProcessosEjb to set
	 */
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	/**
	 * @param establimentSessionKey the establimentSessionKey to set
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public void setTancamentLlibres(String tancamentLlibres) {
		this.tancamentLlibres = tancamentLlibres;
	}

	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
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