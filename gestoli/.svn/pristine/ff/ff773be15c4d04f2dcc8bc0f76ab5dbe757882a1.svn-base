package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
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
 */
public class ConsultaEtiquetatgeMarcaLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaEtiquetatgeMarcaLlistatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String establimentSessionKey;
    private String trazaTipusEntradaVolant;
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
    	ConsultaEtiquetatgeMarcaLlistatCommand consulta = (ConsultaEtiquetatgeMarcaLlistatCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());
    		if (consulta.getIdMarca() != null) myModel.put("idMarca", consulta.getIdMarca());
    		myModel.put("path", "consultar_etiquetatgeMarca");
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
	    				myModel.put("path", "consulta_etiquetatgeMarcaEstabliment");
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
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        Map myModel = new HashMap();
        HttpSession session = request.getSession();

        try {
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	ConsultaEtiquetatgeMarcaLlistatCommand consulta = (ConsultaEtiquetatgeMarcaLlistatCommand) command;
        	//Long idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        	Long idEstabliment = null;
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
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        		long ini = System.currentTimeMillis();
        		System.out.println("COMENÇAMENT");      		
        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi = sdf.parse(request.getParameter("dataFi"));
        		
        		String fromEstabliment = request.getParameter("fromEstabliment");
        		String marca = request.getParameter("idMarca");
        		Long idMarca = null;
        		if (marca != null && !marca.equals("")){
        			idMarca = Long.parseLong(marca);
        		}
        		
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Establiment est = oliInfraestructuraEjb.establimentAmbId(idEstabliment);
        		
        		List etiquetatges = new ArrayList();
        		List categorias = new ArrayList();
    		   	categorias.add(Constants.CATEGORIA_DO);
    		   	categorias.add(Constants.CATEGORIA_NO_DO);
    		   	categorias.add(Constants.CATEGORIA_PENDENT);
        		
        		//LLISTAT EMBOTELLAT I SORTIDES
        		Collection listaEmbotellat = new ArrayList(); //LLista amb els objectes d'embotellat
        		Collection listaSortides = new ArrayList(); //LLista amb els objectes d'embotellat
        		
        		Calendar cal = Calendar.getInstance();
        		cal.setTime(dataInici);
        		
        		int numDies = Util.daysBetween(dataInici, dataFi) + 1;
//        		System.out.println("INICI CAPÇALERA" + (System.currentTimeMillis() - ini)/1000 + " segons");
        		
        		if (idMarca != null)
        			etiquetatges = oliInfraestructuraEjb.etiquetatgeByMarca(idMarca);
        		else
        			etiquetatges = oliInfraestructuraEjb.etiquetatgeByEstabliment(est.getId());
        		int columnes = etiquetatges.size();
        			
        		Double[] listLit = new Double[columnes];
				Double[] listCap = new Double[columnes];
				Integer[] listUd = new Integer[columnes];
				Double[] listLitFi = new Double[columnes];
				Integer[] listUdFi = new Integer[columnes];
				Integer[] listUdSor = new Integer[columnes];
				Double[] listLitSor = new Double[columnes];
				Double[] listLitTot = new Double[columnes];
				
				//Capçalera -> Existencies inicials
				//Primera linia: litres inicials
				//Segona linia: capacitat envàs
				//Tercera línia: número botelles
				int i = 0;
				
        		for(Object objetiquet : etiquetatges){
        			Etiquetatge etiquetatge = (Etiquetatge)objetiquet;
        			
        			listUd[i] = oliConsultaEjb.findBotellesByEstablimentEtiquetatgeAndData(est.getId(), etiquetatge.getId(), dataInici);
        			listUdFi[i] = 0 + listUd[i];
        			listCap[i] = etiquetatge.getTipusEnvas().getVolum();
        			listLit[i] = listUd[i] * listCap[i];
        			listUdSor[i] = 0;
					i++;
        		}
        		
        		//Primera linia: litres inicials
        		listaEmbotellat.add(new Object[]{"Litres inicials",listLit});
        		//Segona linia: capacitat envàs
        		listaEmbotellat.add(new Object[]{"Capacitat",listCap});
        		//Tercera línia: número botelles
        		listaEmbotellat.add(new Object[]{"Botelles",listUd});
        		
//        		System.out.println("FI CAPÇALERA" + (System.currentTimeMillis() - ini)/1000 + " segons");
//        		System.out.println("INICI DIES" + (System.currentTimeMillis() - ini)/1000 + " segons");
        		
        		for(i=0; i<numDies; i++){
        			
        			Object[] filaEmbtellat = new Object[columnes];
        			Object[] filaSortides = new Object[columnes];
        			for (int j = 0; j < columnes; j++){
        				filaEmbtellat[j] = 0;
        				filaSortides[j] = 0;
        			}
        			
        			List botellaEti = oliConsultaEjb.findBotellesLotsNousByEstablimentAndDataGroupedByEtiquetatge(est.getId(), cal.getTime());
        			List sortidesEti = oliConsultaEjb.findSoridesLotByEstablimentAndDataGroupedByEtiquetatge(est.getId(), cal.getTime());
        			
        			for(Object objbe: botellaEti){
        				Object[] botEti = (Object[])objbe;
        				
        				Etiquetatge etiquetatge = oliInfraestructuraEjb.etiquetatgeAmbId((Long)botEti[0]);
            			Long botelles = (Long)botEti[1];
            			int index = etiquetatges.indexOf(etiquetatge);
            			if (index >= 0){
            				filaEmbtellat[index] = botelles.intValue();
            				listUdFi[index] += botelles.intValue();
            			}
            		}
        			
        			for(Object objse: sortidesEti){
        				Object[] sorEti = (Object[])objse;
        				
        				Etiquetatge etiquetatge = oliInfraestructuraEjb.etiquetatgeAmbId((Long)sorEti[0]);
            			Long botelles = (Long)sorEti[1];
            			int index = etiquetatges.indexOf(etiquetatge);
            			if (index >= 0){
            				filaSortides[index] = botelles.intValue();
            				listUdSor[index] += botelles.intValue();
            			}
            		}
        			
        			//Afegim linia a la llista
        			Object[] objecteEmbotellat = {Util.getDataFormat(cal.getTime()),filaEmbtellat};
        			listaEmbotellat.add(objecteEmbotellat);
        			Object[] objecteSortides = {Util.getDataFormat(cal.getTime()),filaSortides};
        			listaSortides.add(objecteSortides);
        			
        			//Actualitzam la data
        			cal.add(Calendar.DAY_OF_MONTH, 1);
        			
        		}
//        		System.out.println("FI DIES" + (System.currentTimeMillis() - ini)/1000 + " segons");
//        		System.out.println("INICI PEU" + (System.currentTimeMillis() - ini)/1000 + " segons");
        		
        		i = 0;
        		
        		for(Object objetiquet : etiquetatges){
//        			Etiquetatge etiquetatge = (Etiquetatge)objetiquet;
        			listLitFi[i] = listUdFi[i] * listCap[i];
        			listLitSor[i] = listUdSor[i] * listCap[i];
        			listLitTot[i] = listLitFi[i] - listLitSor[i];
					i++;
        		}
        		
        		listaEmbotellat.add(new Object[]{"UD EMBOT",listUdFi});
        		listaEmbotellat.add(new Object[]{"Capacitat",listCap});
        		listaEmbotellat.add(new Object[]{"LITRES",listLitFi});
        		listaSortides.add(new Object[]{"UD SORT",listUdSor});
        		listaSortides.add(new Object[]{"Capacitat",listCap});
        		listaSortides.add(new Object[]{"LITRES",listLitSor});
        		listaSortides.add(new Object[]{"LITRES TOTAL",listLitTot});
        		
//        		Sy1stem.out.println("FI PEU" + (System.currentTimeMillis() - ini)/1000 + " segons");
        		
        		myModel.put("etiquetatges", etiquetatges);
        		
        		if(!errors.hasErrors()){
        			myModel.put("listaEmbotellat", listaEmbotellat);
        			myModel.put("listaSortides", listaSortides);
        			myModel.put("trazaTipusEntradaVolant", trazaTipusEntradaVolant);
        		}
        		
        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setFromEstabliment(fromEstabliment);
        		if (idMarca != null) consulta.setIdMarca(idMarca);
        		
        	}
        	myModel.put("path", "consultar_etiquetatgeMarca");
        	if(consulta.getFromEstabliment()!= null && consulta.getFromEstabliment().equalsIgnoreCase("true")){
    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    			if (establiment==null){
    				if (idEstabliment != null){
        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(idEstabliment);        				
        			}
        		}
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path", "consulta_etiquetatgeMarcaEstabliment");
    				myModel.put("path_extension1", establiment.getNom());
    			}
    		}
        	// Marques
        	if (idEstabliment != null){
        		Collection marcas = oliInfraestructuraEjb.getMarcasEstabliment(idEstabliment);
        		myModel.put("marcas", marcas);
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
    	ConsultaEtiquetatgeMarcaLlistatCommand consulta = (ConsultaEtiquetatgeMarcaLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaEtiquetatgeMarcaLlistatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;
    		Date dataInici = null;
    		Date dataFi = null;
    		Long idMarca = null;
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
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		if(request.getParameter("idMarca") != null && !((String)request.getParameter("idMarca")).equals("")) {
    			consulta.setIdMarca(Long.parseLong(request.getParameter("idMarca")));
    		}
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataFi != null)consulta.setDataFi(dataFi);			
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);
    		
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   	
    	
    	return consulta;
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
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
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
	 * Metodo 'setTrazaTipusSortidaVolant'
	 * @param trazaTipusSortidaVolant
	 */
	public void setTrazaTipusEntradaVolant(String trazaTipusEntradaVolant) {
		this.trazaTipusEntradaVolant = trazaTipusEntradaVolant;
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
