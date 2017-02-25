
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Zona;

/**
 * <p>Controlador per a la pantalla principal del establecimiento.</p>
 * <p>Retorna la informació per omplir els combos d'establiment
 * i zona i també la informació dels diposits de la zona
 * seleccionada. Els paràmetres relevants per a aquest
 * controlador son:
 * <ul>
 *     <li>establimentId: id de l'establiment seleccionat</li>
 *     <li>zonaId: id de la zona seleccionada</li>
 *     <li>clearZones: val 'S' quan canviam d'establiment. Així
 *     es pot esbrinar quina és la zona seleccionada</li>
 * </ul></p>
 * 
 * 
 *  */
public class ProcesSituarDipositController implements Controller {

   
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
	private HibernateTemplate hibernateTemplate;


    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * 
     * @see Controller#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    public ModelAndView handleRequest(
    		HttpServletRequest request,
    		HttpServletResponse response)
    throws ServletException {
    	Map myModel = new HashMap();

    	HttpSession session = request.getSession();
    	ProcesSeleccio procesSeleccio = (ProcesSeleccio)request.getAttribute("procesSeleccio");


    	try  {
    		Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    		String dipositId = request.getParameter("dipositId");
    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    		Diposit d = oliInfraestructuraEjb.dipositAmbId(Long.valueOf(dipositId));
    		
    		if (establiment==null){
    			Long establimentId = d.getZona().getEstabliment().getId();
    			if (establimentId != null){
    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(establimentId);
    			}
    		}
    		myModel.put("establimentId", establiment.getId().toString()); 
    					
    		if (establiment !=null) {
    			myModel.put("establiment",establiment );
    			myModel.put("path", "vista_establecimiento");
    			Collection zonas = new ArrayList();
    			zonas.add(d.getZona());
    			myModel.put("zones", zonas);
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path_extension1", establiment.getNom());
    				myModel.put("establecimientNom", establiment.getNom());
    			}    			

    				
    			String dipos = request.getParameter("dipositId");
    			String zid = d.getZona().getId().toString();
//    			request.setAttribute("establimentEx", establiment.getId().toString());
//    			if (request.getAttribute("zonaEx")==null){
//    				request.setAttribute("zonaEx", zid);
//    			}
    			myModel.put("zonaId", zid);	
    			
    			Collection infoDiposits = new ArrayList(); 
    			
				Object[] obj = new Object[2];
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Object[] infoOliva = oliInfraestructuraEjb.obtenirInfoOliva(d.getId());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection varietatsDiposit = oliInfraestructuraEjb.obtenirVarietatsDiposit(d.getId());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection lot = oliInfraestructuraEjb.obtenirLotDiposit(d.getId());
				
				InformacioDipositEstablimentCommand infoDiposit = new InformacioDipositEstablimentCommand();
				infoDiposit.setId(d.getId());
				infoDiposit.setResponsable((String)infoOliva[0]);
				infoDiposit.setDataEntrada((Date)infoOliva[1]);
				infoDiposit.setEstabliment(d.getEstabliment());
				infoDiposit.setCodiAssignat(d.getCodiAssignat());
				infoDiposit.setCapacitat(d.getCapacitat());
				infoDiposit.setMaterialDiposit(d.getMaterialDiposit());
				infoDiposit.setPartidaOli(d.getPartidaOli());
				infoDiposit.setZona(d.getZona());
				infoDiposit.setIdOriginal(d.getIdOriginal());
				infoDiposit.setActiu(d.getActiu());
				infoDiposit.setFictici(d.getFictici());
				infoDiposit.setPosicioX(d.getPosicioX());
				infoDiposit.setPosicioY(d.getPosicioY());
				infoDiposit.setVolumActual(d.getVolumActual());
				infoDiposit.setAcidesa(d.getAcidesa());
				infoDiposit.setObservacions(d.getObservacions());
				infoDiposit.setVolumTrasllat(d.getVolumTrasllat());
				infoDiposit.setVarietatsDiposit(varietatsDiposit);
				infoDiposit.setLotsDiposit(lot);
				infoDiposit.setPrecintat(d.getPrecintat());
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				if(oliInfraestructuraEjb.findContenidorEnCami(d.getId())){
					obj[0] = d.getId();
					obj[1] = true;
					infoDiposit.setEnCami(true);
				} else {
					obj[0] = d.getId();
					obj[1] = false;
					infoDiposit.setEnCami(false);
				}
				
				infoDiposits.add(infoDiposit);
    			
    			myModel.put("diposits", infoDiposits);		        	


    		}
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	myModel.put("tipusProces", request.getAttribute("tipusProces"));
    	myModel.put("procsel", procesSeleccio);

    	//return new ModelAndView("ProcesSituarDiposit", myModel);
    	return new ModelAndView("EstablimentPrincipal", myModel);
    }




    /**
     * Injecció de la dependència oliInfraestructuraEjb
     *
     * @param oliInfraestructuraEjb La classe a injectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
    	this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

    /**
     * Injecció de la dependència establimentSessionKey
     *
     * @param establimentSessionKey La classe a injectar.
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