/**
 * ConsultaTrazabilitatController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.util.TrazabilitatResumida;

/**
 * <p>Controlador per a mostrar la trazabilitat d'un
 * contenidor.</p>
 * <p>Els paràmetres de la petició HTTP relevants per
 * a aquest controlador són:
 * <ul>
 *   <li>contenidorId: Identificador del contenidor.</li>
 * </ul></p>
 *
 */
public class ConsultaTrazabilitatResumidaController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(ConsultaTrazabilitatResumidaController.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String trazaTipusOliElaborat;
	private String trazaTipusOliDisponibleDiposit;
	private String trazaTipusOliDisponibleLote;
	private String trazaTipusEntradaVolant;
	private String trazaTipusSortidaVolant;
	private String trazaTipusOliComercialitzat;
	private String trazaTipusPartidaOli;
	private String trazaTipusOlivaElaborada;
	private String trazaTipusOlivaComercialitzada;
	private String trazaTipusOlivaDisponibleBota;
	private String trazaTipusOlivaDisponibleEnvas;
	


    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * 
     * @see AbstractController#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    public ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
    	Map myModel = new HashMap();
    	

//    	Long establimentId = null;
//    	if (request.getParameter("establimentId")!=null && !request.getParameter("establimentId").equals("")){ 
//    		establimentId = new Long(request.getParameter("establimentId"));
//    	}
    	
    	String tipus = request.getParameter("tipus");
    	String id = request.getParameter("id");
    	 	
    	try  {
    		
    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		TrazabilitatResumida trazabilitat = oliConsultaEjb.trazabilitatResumida(tipus,id);
    		List diposits = trazabilitat.getDipositsAmbPartidaId();
    		myModel.put("trazabilitat", trazabilitat.getArbre());
    		String dips = "";
    		Double totalPartida = new Double(0.00);
    		if(diposits.size()>0){
    			
    			for(Diposit dip:trazabilitat.getDipositsAmbPartidaId()){
					if(trazabilitat.getDiposit().getId().doubleValue() != dip.getId().doubleValue()){
						dips += dip.getCodiAssignat()+",";	
					}
	    		}
    			if(!dips.equals("")){
    				dips = dips.substring(0, dips.length()-1);	
    			}
    			
    		}
    		if(trazabilitat.getPartidaOli()!=null){
	    		if(trazabilitat.getPartidaOli().getEsDO() || !trazabilitat.getPartidaOli().getEsQualificada()){
			    	if(trazabilitat.getAnalitica() != null){
						totalPartida = trazabilitat.getAnalitica().getLitresAnalitzats();
						trazabilitat.setLitresTotal(totalPartida);
					}else{
						if(dips.equals("")){
							totalPartida = trazabilitat.getDiposit().getVolumActual();	
						}
						
					}
	    		}
    		}
    		myModel.put("diposits", dips);
    		myModel.put("totalPartida",totalPartida);
    		if(tipus.equalsIgnoreCase(trazaTipusOliElaborat)){
    			myModel.put("path", "consulta_elaboracionTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOliDisponibleDiposit)){
				myModel.put("path", "consulta_DisponibleOliDipositTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOliDisponibleLote)){ // Muestra la trazabilidad con respecto a la entrada de aceite y el numero de botellas actuales
				myModel.put("path", "consulta_DisponibleOliLoteTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusSortidaVolant)){
				myModel.put("path", "consulta_salidaVolanteTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusEntradaVolant)){
				myModel.put("path", "consulta_entradaVolanteTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOliComercialitzat)){ // Muestra la trazabilidad con respecto a una salida del lote en concreto y el numero de botellas actuales
				myModel.put("path", "consulta_oliComercializatTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusSortidaVolant)){
				myModel.put("path", "consulta_salidaVolanteTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOlivaElaborada)){
				myModel.put("path", "consulta_elaboracionOlivaTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOlivaComercialitzada)){
				myModel.put("path", "consulta_olivaComercializatTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOlivaDisponibleBota)){
				myModel.put("path", "consulta_DisponibleOlivaBotaTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusOlivaDisponibleEnvas)){
				myModel.put("path", "consulta_DisponibleOlivaEnvasTraza");
			}else if(tipus.equalsIgnoreCase(trazaTipusPartidaOli)){
				myModel.put("path", "consulta_partidaOlivaTraza");
			}
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	    	
    	
    	
    	return new ModelAndView("ConsultaTrazabilitatResumida", myModel);
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

	/**
	 * Injecció de la dependència setTrazaTipusOliElaborat
	 *
	 * @param trazaTipusOliElaborat La classe a injectar.
	 */
	public void setTrazaTipusOliElaborat(String trazaTipusOliElaborat) {
		this.trazaTipusOliElaborat = trazaTipusOliElaborat;
	}
	/**
	 * Injecció de la dependència trazaTipusOliDisponibleDiposit
	 *
	 * @param trazaTipusOliDisponibleDiposit La classe a injectar.
	 */
	public void setTrazaTipusOliDisponibleDiposit(String trazaTipusOliDisponibleDiposit) {
		this.trazaTipusOliDisponibleDiposit = trazaTipusOliDisponibleDiposit;
	}
	
	/**
	 * Injecció de la dependència trazaTipusOliDisponiblePartida
	 *
	 * @param trazaTipusOliDisponiblePartida La classe a injectar.
	 */
	public void setTrazaTipusOliDisponibleLote(String trazaTipusOliDisponibleLote) {
		this.trazaTipusOliDisponibleLote = trazaTipusOliDisponibleLote;
	}
	
	/**
	 * Injecció de la dependència trazaTipusEntradaVolant
	 *
	 * @param trazaTipusEntradaVolant La classe a injectar.
	 */
	public void setTrazaTipusEntradaVolant(String trazaTipusEntradaVolant) {
		this.trazaTipusEntradaVolant = trazaTipusEntradaVolant;
	}
	/**
	 * Injecció de la dependència trazaTipusSortidaVolant
	 *
	 * @param trazaTipusSortidaVolant La classe a injectar.
	 */
	public void setTrazaTipusSortidaVolant(String trazaTipusSortidaVolant) {
		this.trazaTipusSortidaVolant = trazaTipusSortidaVolant;
	}
	/**
	 * Metodo 'seTtrazaTipusOliComercialitzat'
	 * @param trazaTipusOliComercialitzat
	 */
	public void setTrazaTipusOliComercialitzat(String trazaTipusOliComercialitzat) {
		this.trazaTipusOliComercialitzat = trazaTipusOliComercialitzat;
	}
	/**
	 * Metodo 'setTrazaTipusPartidaOli'
	 * @param trazaTipusPartidaOli
	 */
	public void setTrazaTipusPartidaOli(String trazaTipusPartidaOli) {
		this.trazaTipusPartidaOli = trazaTipusPartidaOli;
	}
	
	/**
	 * Injecció de la dependència setTrazaTipusOlivaElaborada
	 *
	 * @param trazaTipusOlivaElaborada La classe a injectar.
	 */
	public void setTrazaTipusOlivaElaborada(String trazaTipusOlivaElaborada) {
		this.trazaTipusOlivaElaborada = trazaTipusOlivaElaborada;
	}

	public void setTrazaTipusOlivaComercialitzada(
			String trazaTipusOlivaComercialitzada) {
		this.trazaTipusOlivaComercialitzada = trazaTipusOlivaComercialitzada;
	}

	public void setTrazaTipusOlivaDisponibleBota(
			String trazaTipusOlivaDisponibleBota) {
		this.trazaTipusOlivaDisponibleBota = trazaTipusOlivaDisponibleBota;
	}

	public void setTrazaTipusOlivaDisponibleEnvas(
			String trazaTipusOlivaDisponibleEnvas) {
		this.trazaTipusOlivaDisponibleEnvas = trazaTipusOlivaDisponibleEnvas;
	}
}
