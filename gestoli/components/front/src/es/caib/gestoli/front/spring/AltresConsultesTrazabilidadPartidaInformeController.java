/**
 * AltresConsultesTrazabilidadPartidaInformeController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.util.Constants;

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
public class AltresConsultesTrazabilidadPartidaInformeController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(AltresConsultesTrazabilidadPartidaInformeController.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;


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
    
    	String id = request.getParameter("id");
    	 	
    	try  {
    		
    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection trazabilitat =  oliConsultaEjb.trazabilitatPartidaOli(id);    
    		
    		// Subpartidas y Lotes
    		List elaboraciones = new ArrayList();
    		List elaboracionesId = new ArrayList();
    		List lotes = new ArrayList();
    		List lotesId = new ArrayList();
    		List analiticas = new ArrayList();
    		List analiticasId = new ArrayList();
    		List entradas = new ArrayList();
    		List entradasId = new ArrayList();
    		for(Iterator trazabilidatIt = trazabilitat.iterator(); trazabilidatIt.hasNext();){
    			Object[] fulla = (Object[]) trazabilidatIt.next();
    			String tipo = (String)fulla[3];
    			
    			if(tipo.equalsIgnoreCase("Elaboracio") ){
    				Elaboracio elaboracion = (Elaboracio)fulla[1];
    				if( !elaboracionesId.contains(elaboracion.getId()) ){
    					elaboraciones.add(elaboracion);
        				elaboracionesId.add(elaboracion.getId());
    				}    				
    			}else if(tipo.equalsIgnoreCase("Analitica") ){
    				Analitica analitica = (Analitica)fulla[1];
    				if( !analiticasId.contains(analitica.getId()) ){
    					analiticas.add(analitica);
    					analiticasId.add(analitica.getId());
    				}   				
    			}else if(tipo.equalsIgnoreCase("EntradaDiposit") ){
    				EntradaDiposit entradaDiposit = (EntradaDiposit)fulla[1];
    				if( !entradasId.contains(entradaDiposit.getId()) ){
    					entradas.add(entradaDiposit);
    					entradasId.add(entradaDiposit.getId());
    				}    				
    			}   			
    			
    		}
    		
    		Collections.sort(elaboraciones);
    		myModel.put("elaboraciones",elaboraciones );
    		
    		//Collections.sort(analiticas);
    		myModel.put("analiticas", analiticas);
    		
    		//LOTES (Vemos que hijos de las entradas de oliva son salidas de oliva con destino EMBOTELLADO)
    		for(int i=0; i< entradas.size();i++){    			
    			EntradaDiposit entradaDiposit = (EntradaDiposit)entradas.get(i);
    			Traza traza = (Traza) entradaDiposit.getTraza();
    			Collection hijos = traza.getTrazasForTtrCodtrafill();
    			for(Iterator hijosIt = hijos.iterator();hijosIt.hasNext(); ){
    				Traza trazaHijo = (Traza) hijosIt.next();
    				if(trazaHijo.getTipus().intValue()== Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
    					oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    					SortidaDiposit sortidaDiposit =  oliConsultaEjb.getSortidaDipositByTraza(trazaHijo.getId());
    					if(sortidaDiposit!= null && sortidaDiposit.getDesti()!= null && sortidaDiposit.getDesti().equalsIgnoreCase(Constants.DESTI_EMBOTELLAT)){
    						//LOTE
    						Traza trazaSortida = sortidaDiposit.getTraza();
    						Collection hijosSalida = trazaSortida.getTrazasForTtrCodtrafill();
    						Traza trazaEntradaLot = null;
    						EntradaLot entradaLot = null;
    						for(Iterator itHijosSalida= hijosSalida.iterator(); itHijosSalida.hasNext();){
    							trazaEntradaLot = (Traza)itHijosSalida.next();
    							oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
            					entradaLot =  oliConsultaEjb.getEntradaLotByTraza(trazaEntradaLot.getId());    							
    							break;
    						}
    						if(entradaLot!= null && entradaLot.getLot()!= null){
    							Lot lote = entradaLot.getLot();
    							if( !lotesId.contains(lote.getId()) ){
    		    					lotes.add(lote);
    		    					lotesId.add(lote.getId());
    		    				} 
    						}					
    					}    					
    				}
    			}
    		}    		
    		
    		Collections.sort(lotes);
    		myModel.put("lotes", lotes);
    		
    		//Calculamos el rendimineto de las de las fincas
    		Map fincasMap = new HashMap();
    		Map fincasKgsMap = new HashMap();
    		for(int i=0; i< elaboraciones.size();i++){
				Elaboracio elaboracion = (Elaboracio)elaboraciones.get(i);
				Collection partidasOliva = elaboracion.getPartidaOlivas();
				for(Iterator itPartidas=partidasOliva.iterator();itPartidas.hasNext();){
					PartidaOliva partidaOliva = (PartidaOliva)itPartidas.next();
					Collection descomposicionesPartida = partidaOliva.getDescomposicioPartidesOlives();
					for(Iterator itDescomposicionesPartida = descomposicionesPartida.iterator();itDescomposicionesPartida.hasNext();){
						DescomposicioPartidaOliva descomposicioPartidaOliva = (DescomposicioPartidaOliva)itDescomposicionesPartida.next();
						DescomposicioPlantacio descomposicioPlantacio =  descomposicioPartidaOliva.getDescomposicioPlantacio();
						Finca finca = descomposicioPlantacio.getPlantacio().getFinca();
						
						fincasMap.put(finca.getId(), finca );
						
						if(fincasKgsMap.get(finca.getId())!= null ){
							Double kilos = (Double)fincasKgsMap.get(finca.getId());
							double sumaKilos = kilos.doubleValue()+ descomposicioPartidaOliva.getKilos().doubleValue();
							fincasKgsMap.put(finca.getId(), Double.valueOf(String.valueOf(sumaKilos)));
						}else{
							fincasKgsMap.put(finca.getId(), descomposicioPartidaOliva.getKilos());
						}	
					}									
				}
    		}
    		
    		Collection fincasId = fincasMap.keySet();
    		List rendimientos = new ArrayList();
    		
    		for(Iterator itFincasId = fincasId.iterator();itFincasId.hasNext();){
    			String textoRendimiento = "";
    			Long fincaId = (Long) itFincasId.next();
    			Finca finca = (Finca)fincasMap.get(fincaId);
    			Collection plantraciones = finca.getPlantacios();
    			double hectareas = 0;
    			for(Iterator itPlantraciones=plantraciones.iterator();itPlantraciones.hasNext();){
    				Plantacio plantacio= (Plantacio)itPlantraciones.next();
    				Collection descomposicionPlantacion = plantacio.getDescomposicioPlantacios();
    				for(Iterator itDescompPlantacio=descomposicionPlantacion.iterator();itDescompPlantacio.hasNext();){
    					DescomposicioPlantacio  descomposicionPlantacio = (DescomposicioPlantacio) itDescompPlantacio.next();
    					hectareas+= descomposicionPlantacio.getSuperficie().doubleValue();
    				}
    			}
    			Double kilos = (Double)fincasKgsMap.get(fincaId);
    			Double rendimiento = Double.valueOf(String.valueOf(kilos.doubleValue() / hectareas));
    			DecimalFormat numberDecimalFormat = new DecimalFormat();
				numberDecimalFormat.setMaximumFractionDigits(3);
    			textoRendimiento = missatge("consulta.trazabilitat.partidaOli.finca")+": "+ finca.getNom()+" / "+ missatge("consulta.trazabilitat.partidaOli.rendimiento")+": "+numberDecimalFormat.format(rendimiento);
    			rendimientos.add(textoRendimiento);
    		}
    		myModel.put("rendimientos", rendimientos);    		
    		
    		myModel.put("path", "consulta_trazabilidadPartida");
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	    	
    	
    	
    	return new ModelAndView("AltresConsultesTrazabilidadPartidaInforme", myModel);
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
	
   
	 private String missatge(String clave){
	    	String valor= getMessageSourceAccessor().getMessage(clave);
	    	return valor;
	  }
	
}
