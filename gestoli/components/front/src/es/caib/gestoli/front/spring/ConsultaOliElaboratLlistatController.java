package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;

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
 */
public class ConsultaOliElaboratLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaOliElaboratLlistatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String establimentSessionKey;
    private String trazaTipusOliElaborat;
	private String esProductorRequestKey;
	private String esEnvasadorRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;

	private HibernateTemplate hibernateTemplate;

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaOliElaboratLlistatCommand consulta = (ConsultaOliElaboratLlistatCommand) command;
    	HttpSession session = request.getSession();
    	Map myModel = new HashMap();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());
    		if (consulta.getMesura() != null) myModel.put("mesura", consulta.getMesura());
    		
    		myModel.put("path", "consultar_oliElaborat");
    		if (consulta.getFromEstabliment() != null && consulta.getFromEstabliment().equalsIgnoreCase("true")){	
	    		myModel.put("fromEstabliment", consulta.getFromEstabliment());
	    		
	    		if(request.getParameter("fromEstabliment")!= null){
	    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
	    			if (establiment==null){
	    				if (consulta.getIdEstabliment() != null){
	    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(consulta.getIdEstabliment());        				
	        			}
	        		}
	    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
	    				myModel.put("path", "consulta_oliElaboratEstabliment");
	    				myModel.put("path_extension1", establiment.getNom());
	    			}
	    		}
    		}
    		
    		

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
    	
		return new ModelAndView(getSuccessView(), myModel);
    }
    
    /**
     * Retorna todos los datos del modelo necesarios para mostrar
     * el formulario de inserción (LOVs y cosas de esas) si no hay.
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    @SuppressWarnings("unchecked")
	protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        Map myModel = new HashMap();
        HttpSession session = request.getSession();
        try {
        	ConsultaOliElaboratLlistatCommand consulta = (ConsultaOliElaboratLlistatCommand) command;
        	//Long idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        	
        	Long idEstabliment = null;
        	if (request.getAttribute(esEnvasadorRequestKey) != null || request.getAttribute(esProductorRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey ) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
        	String mesura = request.getParameter("mesura");
        	if (mesura == null || mesura.equals("")) mesura = "l";
        	
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        		        		
        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi = sdf.parse(request.getParameter("dataFi"));        		
        		String fromEstabliment = request.getParameter("fromEstabliment");

        		Collection lista = new ArrayList();
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection listaElaboraciones = oliConsultaEjb.oliElaboratConsulta(dataInici, dataFi, idEstabliment, false, new Boolean(true));
        		
        		//Creamos un hashMap donde la key es la elaboracion y el objeto es un listado de EntradaDiposit para esa elaboracion
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		HashMap entradasDipositElaboracioHash = oliConsultaEjb.entradaDipositConsultaByEstablimentHash(idEstabliment);
        		
//        		//Creamos un hashMap donde la key es la elaboracion y el objeto la cantidad de aceite retirado por el olivicultor en litros
//        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
//        		Collection oliRetiratPropietarioElaboracio = oliConsultaEjb.oliRetiratPropietarioEntradaDipositConsulta(idEstabliment);
        		
        		ProcesElaboracioOliCommand procesElaboracioOliCommand = null;
        		for(Iterator it= listaElaboraciones.iterator(); it.hasNext();){
        			procesElaboracioOliCommand = new ProcesElaboracioOliCommand();
        			Elaboracio elaboracio = (Elaboracio)it.next();
        			procesElaboracioOliCommand.setId(elaboracio.getId());
        			procesElaboracioOliCommand.setTraza(elaboracio.getTraza());
        			procesElaboracioOliCommand.setData(elaboracio.getData());
        			procesElaboracioOliCommand.setNumeroElaboracio(elaboracio.getNumeroElaboracio());
        			procesElaboracioOliCommand.setResponsable(elaboracio.getResponsable());
        			procesElaboracioOliCommand.setPartidaOlivas(elaboracio.getPartidaOlivas());        			
        			procesElaboracioOliCommand.setCategoriaOli(elaboracio.getCategoriaOli());
        			procesElaboracioOliCommand.setAcidesa(elaboracio.getAcidesa());
        			Double litrosTotal = elaboracio.getLitres();
        			//litros convertirlo a KG si procede
        			if(mesura !=null && mesura.equalsIgnoreCase("kg")){
        				litrosTotal =  Double.valueOf(String.valueOf((Double.valueOf("0.916").doubleValue() * litrosTotal.doubleValue())));
        				procesElaboracioOliCommand.setLitres(litrosTotal);
    				}else{
    					procesElaboracioOliCommand.setLitres(litrosTotal);
    				}
        			Set olivs = new HashSet();
        			for (Object objpao: elaboracio.getPartidaOlivas()){
        				PartidaOliva pao = (PartidaOliva)objpao;
        				olivs.add(pao.getOlivicultor().getNom());
        			}
        			String[] olivicultors = new String[olivs.size()];
        			olivs.toArray(olivicultors);
        			procesElaboracioOliCommand.setNomOlivicultors(olivicultors);
        			
        			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        			Collection oliRetirat = oliConsultaEjb.oliRetiratPropietarioEnElaboracio(idEstabliment, elaboracio.getId()); 
        			
//        			if( oliRetiratPropietarioElaboracio != null && 
//        				oliRetiratPropietarioElaboracio.size() > 0){
        			if( oliRetirat != null && oliRetirat.size() > 0){
        				Double[] litresRetirats = new Double[oliRetirat.size()];
        				Double[] kilosRetirats = new Double[oliRetirat.size()];
        				Long[] idOlivicultors = new Long[oliRetirat.size()];
        				int index = 0;
        				Double totalLitres = 0.0;
        				
        				for(Object o: oliRetirat){
        					EntradaDiposit ed = (EntradaDiposit)o;
        					litresRetirats[index] = ed.getLitres();
        					totalLitres += ed.getLitres();
        					kilosRetirats[index] = ed.getLitres() * 0.916;
        					idOlivicultors[index] = ed.getOlivicultor().getId();
        					
        					index++;
        				}
        				
        				//litrosRetirats convertirlo a KG si procede
        				if(mesura!= null && mesura.equalsIgnoreCase("kg")){
        					procesElaboracioOliCommand.setLitrosRetirats(kilosRetirats);
        					procesElaboracioOliCommand.setTotalLitresRetirats(totalLitres * 0.916);
        				}else{
        					procesElaboracioOliCommand.setLitrosRetirats(litresRetirats);
        					procesElaboracioOliCommand.setTotalLitresRetirats(totalLitres);
        				}       
        				procesElaboracioOliCommand.setIdOlivicultors(idOlivicultors);
        			}
        			
        			if( entradasDipositElaboracioHash.get(elaboracio.getId()) != null ){
        				List dipositList  = (List) entradasDipositElaboracioHash.get(elaboracio.getId());
        				Set dips = new HashSet();
        				for (Object objdip: dipositList){
        					Diposit dip = (Diposit)objdip;
        					if (!dip.getFictici().booleanValue()){
        						dips.add(dip);
        					}
        				}
//        				if(dipositList.size()>0){
        				if(dips.size()>0){
        					Diposit[] diposits = new Diposit[dips.size()];
        					dips.toArray(diposits);
        					procesElaboracioOliCommand.setDiposits(diposits);
        					
        					//Litres per diposit
        					Double[] lit = new Double[dips.size()];
        					Double[] kil = new Double[dips.size()];
        					int index = 0;
        					
        					for(Object o: dips){
        						Diposit d = (Diposit)o;
        						
        						oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        						Collection entradesDiposit = oliConsultaEjb.entradaDiposiOliByDipositAndElaboracio(d.getId(),elaboracio.getId(),true);
        						Double litaux = 0.0;
        						
        						for(Object o2: entradesDiposit){
        							EntradaDiposit ed = (EntradaDiposit)o2;
        							litaux += ed.getLitres();
        						}
        						
        						lit[index] = litaux;
        						kil[index] = litaux * 0.916;
        						index++;
        					}
        					procesElaboracioOliCommand.setLitros(lit);
        					procesElaboracioOliCommand.setKilos(kil);
        				}
        				
        				
        			}
        			lista.add(procesElaboracioOliCommand);
        		}        		
        		if(!errors.hasErrors()){
        			myModel.put("llistat", lista);
        			myModel.put("trazaTipusOliElaborat", trazaTipusOliElaborat);
        		}
        		
        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setMesura(mesura);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        	}
        	
    	    Collection tipusMesura = new ArrayList();
    	    tipusMesura.add(new TipusMesura("l",missatge("establiment.medida.l",request)));
            tipusMesura.add(new TipusMesura("kg",missatge("establiment.medida.k",request)));
            myModel.put("tipusMesura", tipusMesura);
        	myModel.put("path", "consultar_oliElaborat");
        	
   			consulta.setMesura(mesura);
        	
        	if(consulta.getFromEstabliment()!= null && consulta.getFromEstabliment().equalsIgnoreCase("true")){
    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    			if (establiment==null){
    				if (idEstabliment != null){
    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(idEstabliment);        				
        			}
        		}
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path", "consulta_oliElaboratEstabliment");
    				myModel.put("path_extension1", establiment.getNom());
    			}
    		}
        	
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de oli elaborat", ex);
        }
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
    	ConsultaOliElaboratLlistatCommand consulta = (ConsultaOliElaboratLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaOliElaboratLlistatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;
    		Date dataInici = null;
    		Date dataFi = null;
//    		if(request.getAttribute("idEstabliment") != null && !((String)request.getAttribute("idEstabliment")).equals("")){
//    			idEstabliment = Long.valueOf((String)request.getAttribute("idEstabliment"));
//    		}else if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
//    			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
//    		}    		
    		if (request.getAttribute(esEnvasadorRequestKey) != null || request.getAttribute(esProductorRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
    		
    		
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date data = Calendar.getInstance().getTime();
    		
    		if(request.getParameter("dataInici") != null && !((String)request.getParameter("dataInici")).equals("")) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} else {
    			dataInici = data;
    		}
    		
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = data;
    		}
    		if(request.getParameter("mesura") != null && !((String)request.getParameter("mesura")).equals("")) {
    			consulta.setMesura(request.getParameter("mesura"));
    		} else {
    			consulta.setMesura("l");
    		}
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataInici != null)consulta.setDataFi(dataFi);			
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);
    		
    		Collection tipusMesura = new ArrayList();
    		tipusMesura.add(new TipusMesura("l",missatge("establiment.medida.l",request)));
            tipusMesura.add(new TipusMesura("kg",missatge("establiment.medida.k",request)));            
            request.setAttribute("tipusMesura", tipusMesura);
    		
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   	
    	
    	return consulta;
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
    	binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("S", "N", true));
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
	
	/**
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
	
	/**
	 * Injecció de la dependència setTrazaTipusOliElaborat
	 *
	 * @param setTrazaTipusOliElaborat La classe a injectar.
	 */
	public void setTrazaTipusOliElaborat(String trazaTipusOliElaborat) {
		this.trazaTipusOliElaborat = trazaTipusOliElaborat;
	}
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}
	
	
	/**
	 * Metodo 'getEsEnvasadorRequestKey'
	 * @return el esEnvasadorRequestKey
	 */
	public String getEsEnvasadorRequestKey() {
		return esEnvasadorRequestKey;
	}

	/**
	 * Metodo 'setEsEnvasadorRequestKey'
	 * @param esEnvasadorRequestKey el esEnvasadorRequestKey a modificar
	 */
	public void setEsEnvasadorRequestKey(String esEnvasadorRequestKey) {
		this.esEnvasadorRequestKey = esEnvasadorRequestKey;
	}

	/**
	 * Metodo 'getEsProductorRequestKey'
	 * @return el esProductorRequestKey
	 */
	public String getEsProductorRequestKey() {
		return esProductorRequestKey;
	}

	/**
	 * Metodo 'setEsProductorRequestKey'
	 * @param esProductorRequestKey el esProductorRequestKey a modificar
	 */
	public void setEsProductorRequestKey(String esProductorRequestKey) {
		this.esProductorRequestKey = esProductorRequestKey;
	}
	
	/**
	 * Injecció de la dependència esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}

	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}

	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
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
