package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;

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
 * @author Raul Morales
 */
public class ConsultaLlibresFormController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaLlibresFormController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolEnvasador;
    private String rolDoGestor;
    private String rolAdministracio;
    private String rolDoControl;
    private String campanyaSessionKey;
    private String establimentSessionKey;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private HibernateTemplate hibernateTemplate;


	public class LlibreDeControl implements java.io.Serializable {
		
		private Integer val;
		public String nom;
		
		LlibreDeControl(Integer val, String nom){
			this.val=val;
			this.nom=nom;
		}

		public Integer getVal() {
			return val;
		}
		public void setVal(Integer val) {
			this.val = val;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
	}
	
	
	
	public class TipusMesura implements java.io.Serializable {
		
		private String val;
		public String nom;
		
		TipusMesura(String val, String nom){
			this.val=val;
			this.nom=nom;
		}

		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
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
        
        Long estId = null;
        
        if (request.isUserInRole(rolProductor) || request.isUserInRole(rolEnvasador)){
        	
        	Establiment est;
        	HttpSession session = request.getSession();
        	est = (Establiment)session.getAttribute(establimentSessionKey);
        	estId = est.getId();
        	
        }else if (request.getParameter("idEst")!=null){
        	estId= new Long(request.getParameter("idEst"));
        }
        
        
        if (estId!=null){ 
        	
        	myModel.put("idEstabliment", estId);	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Establiment establiment=oliInfraestructuraEjb.establimentAmbId(estId);
        	
        	Collection llibres = new ArrayList();
        	
        	if(establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue()){
        		
                llibres.add(new LlibreDeControl(new Integer(1),missatge("consulta.llibres.llibre_entrada_oliva",request)));
                llibres.add(new LlibreDeControl(new Integer(2),missatge("consulta.llibres.llibre_elaboracio_oli",request)));
                llibres.add(new LlibreDeControl(new Integer(3),missatge("consulta.llibres.llibre_existencies_oli",request)));
                llibres.add(new LlibreDeControl(new Integer(4),missatge("consulta.llibres.llibre_embotellat",request)));
                
        	}else if(establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafona.intValue()){

                llibres.add(new LlibreDeControl(new Integer(1),missatge("consulta.llibres.llibre_entrada_oliva",request)));
                llibres.add(new LlibreDeControl(new Integer(2),missatge("consulta.llibres.llibre_elaboracio_oli",request)));
                llibres.add(new LlibreDeControl(new Integer(3),missatge("consulta.llibres.llibre_existencies_oli",request)));

        	}else if(establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentEnvasadora.intValue()){

                llibres.add(new LlibreDeControl(new Integer(3),missatge("consulta.llibres.llibre_existencies_oli",request)));
                llibres.add(new LlibreDeControl(new Integer(4),missatge("consulta.llibres.llibre_embotellat",request)));

        	}

            myModel.put("llibres", llibres);
            
            Collection tipusMesura = new ArrayList();
            tipusMesura.add(new TipusMesura("l",missatge("establiment.medida.l",request)));
            tipusMesura.add(new TipusMesura("kg",missatge("establiment.medida.k",request)));
            myModel.put("tipusMesura", tipusMesura);
            
            
            myModel.put("path", "consultar_llibres");
            if(request.getParameter("fromEstabliment")!= null && request.getParameter("fromEstabliment").equalsIgnoreCase("true")){
	        	if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
	        		myModel.put("path", "consultar_llibresEstabliment");
	        		myModel.put("path_extension1", establiment.getNom());
				}
            }
        	
        }else if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio) || request.isUserInRole(rolDoControl) ){
        	Collection llistat;
        	try{
        		Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
   	        } catch (RemoteException ex) {
   	        	llistat=null;
        	}
        	myModel.put("llistat", llistat);
        	myModel.put("path", "consultar_llibres");
        }
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	try {
			Date data=Calendar.getInstance().getTime();
			myModel.put("datactual", sdf.format(data));
			
	        ConsultaLlibresCommand consulta = (ConsultaLlibresCommand)command;
	        consulta.setDataInici(data);
	        consulta.setDataFi(data);			
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
        
        
        
        return myModel;
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
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
    	binder.registerCustomEditor(java.util.Date.class, dateEditor);
    }

    
    /**
     * Injecció de la dependència consultaEjb
     *
     * @param consultaEjb La classe a injectar.
     */
  
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
        this.oliConsultaEjb = oliConsultaEjb;
    }
	
    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }


	/**
	 * Metodo 'setRolProductor'
	 * @param rolProductor el rolProductor a modificar
	 */
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

	public String getRolAdministracio() {
		return rolAdministracio;
	}
	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}
	
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}

	public String getRolProductor() {
		return rolProductor;
	}

	public String getCampanyaSessionKey() {
		return campanyaSessionKey;
	}

	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}

	public String getEstablimentSessionKey() {
		return establimentSessionKey;
	}

	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public Integer getTipusEstablimentTafona() {
		return tipusEstablimentTafona;
	}

	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}

	public Integer getTipusEstablimentEnvasadora() {
		return tipusEstablimentEnvasadora;
	}

	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}

	public Integer getTipusEstablimentTafonaEnvasadora() {
		return tipusEstablimentTafonaEnvasadora;
	}

	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
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

	
}
