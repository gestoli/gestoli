 /**
 * ProcesIniciController.java
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.util.Constants;

/**
 * Controla les redireccions cap a l'acció de procés que se passa
 * com a paràmetre.
 * 
 */
public class ProcesIniciController implements Controller, ApplicationContextAware {

       
    private static Logger logger = Logger.getLogger(ProcesIniciController.class);

    private static final boolean S = true;
    private static final boolean N = false;

    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String seleccioSessionKey;
    private String seleccioSessionKeyOrigen;
    private String seleccioSessionKeyDesti;
    private ApplicationContext applicationContext;

	private HibernateTemplate hibernateTemplate;



    /**
     * @see Controller#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException {

    	HttpSession session = request.getSession();
    	Collection seleccio = (Collection)session.getAttribute(seleccioSessionKey);
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	Collection destis = (Collection)session.getAttribute(seleccioSessionKeyDesti);

    	// Inicialitza els paràmetres de la sessió
    	Map myModel = new HashMap();
    	String pas = request.getParameter("pas");
    	if (pas == null || pas.equals("")) {
    		if (seleccio == null) {
    			seleccio = new ArrayList();
    			session.setAttribute(seleccioSessionKey, seleccio);
    		}
    		seleccio.clear();
    		if (origens == null) {
    			origens = new ArrayList();
    			session.setAttribute(seleccioSessionKeyOrigen, origens);
    		}
    		origens.clear();
    		if (destis == null) {
    			destis = new ArrayList();
    			session.setAttribute(seleccioSessionKeyDesti, destis);
    		}
    		destis.clear();
    		session.removeAttribute("procesSeleccio");
    		session.removeAttribute("trasllatId");
    		session.setAttribute("tipus", "1");
    	}


    	// Mira a quina página s'ha d'anar
    	String desti = "EstablimentPrincipal.html";
    	String tipusProces = request.getParameter("tipusProces");
    	request.setAttribute("tipusProces",tipusProces);
    	if (tipusProces != null && !tipusProces.equals("")) {
    		int itp = Integer.parseInt(tipusProces);
	    	switch (itp) {
	    	case Constants.PROCES_ENTRADA_OLIVA:
	    		desti = "ProcesEntradaOlivaForm";
	    		if(request.getParameter("idOlivicultor")!=null){
	    			request.setAttribute("idOlivicultor",request.getParameter("idOlivicultor"));
	    		}
	    		break;
	    	case Constants.PROCES_ENTRADA_OLIVA_TAULA:
	    		desti = "ProcesEntradaOlivaTaulaForm";
	    		if(request.getParameter("idOlivicultor")!=null){
	    			request.setAttribute("idOlivicultor",request.getParameter("idOlivicultor"));
	    		}
	    		break;
	    	case Constants.PROCES_ENTRADA_FONOLL:
	    		desti = "ProcesEntradaFonollForm";
	    		break;
	    	case Constants.PROCES_SITUAR_DIPOSIT:
	    		desti = "ProcesSituarDiposit";
	    		request.setAttribute("tipusProces",tipusProces);
	    		break;
	    		
	    	case Constants.PROCES_ELABORACIO_OLI:
	    		desti = iniciarElaboracionAceite(request);
	    		break;
	    		
	    	case Constants.PROCES_ELABORACIO_OLIVA:
	    		desti = iniciarElaboracioOliva(request);
	    		break;
	    		
	    	case Constants.PROCES_TRASBALS:
	    		try{
	    			desti = iniciarTrasvase(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarTrasvase", e);
	    		}
	    	case Constants.PROCES_PERDUES:
	    		try{
	    			desti = iniciarPerdues(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarPerdues", e);
	    		}
	    	case Constants.PROCES_ANALITICA:
	    		try{
	    			desti = iniciarAnalitica(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarAnalitica", e);
	    		}
	    	case Constants.PROCES_DESQUALIFICAR:
	    		try{
	    			desti = iniciarDesqualificacion(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarDesqualificacion", e);
	    		}
	    	case Constants.PROCES_CREAR_LOT:
	    		try{
	    			desti = iniciarCrearLote(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarCrearLote", e);
	    		}
	    	case Constants.PROCES_ETIQUETAR:
	    		try{
	    			desti = iniciarEtiquetar(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarEtiquetar", e);
	    		}
	    	case Constants.PROCES_SORTIDA_OLI:
	    		try{
	    			desti = iniciarSortidaOli(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarSortidaEmbotelladora", e);
	    		}
	    	case Constants.PROCES_MOURE_DIPOSIT:
	    		try{
	    			desti = iniciarMoureDeposit(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarMoureDeposit", e);
	    		}
    		case Constants.PROCES_CONSULTA_ANALITIQUES:
	    		try{
	    			desti = iniciarConsultaAnaliticas(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarConsultaAnaliticas", e);
	    		}	    	
    		case Constants.PROCES_MOURE_OLI_GRANEL:
	    		try{
	    			desti = iniciarMoureOli(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarMoureDeposit", e);
	    		}
	    	case Constants.PROCES_ACCEPTAR_OLI_GRANEL:
	    		try{
	    			desti = iniciarAcceptarOliGranel(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarAcceptarOliGranel", e);
	    		}
	    	case Constants.PROCES_PENDENT_ANALITICA:
	    		try{
	    			desti = iniciarPendentAnalitica(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarPendentAnalitica", e);
	    		}
		    case Constants.PROCES_DESFER_PENDENT_ANALITICA:
				try{
					desti = iniciarDesferPendentAnalitica(request);
		    		break;
				}catch (Exception e) {
					logger.error("Error iniciarDesferPendentAnalitica", e);
				}
		    case Constants.PROCES_ENVASAT_OLIVA:
	    		try{
	    			desti = iniciarEnvasatOliva(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarEnvasatOliva", e);
	    		}
		    case Constants.PROCES_ETIQUETAT_OLIVA:
	    		try{
	    			desti = iniciarEtiquetatOlivaTaula(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarEtiquetatOliva", e);
	    		}
		    case Constants.PROCES_SORTIDA_OLIVA:
	    		try{
	    			desti = iniciarSortidaOliva(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarEtiquetatOliva", e);
	    		}
		    case Constants.PROCES_AUTOCONTROL:
	    		try{
	    			desti = iniciarAutocontrol(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarAutocontrol", e);
	    		}
    		case Constants.PROCES_CONSULTA_AUTOCONTROL:
	    		try{
	    			desti = iniciarConsultaAutocontrol(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarConsultaAutocontrol", e);
	    		}
	    		
    		case Constants.PROCES_SORTIDA_OLIVA_CRUA_GRANEL:
	    		try{
	    			desti = iniciarSortidaOlivaCruaGranel(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarSortidaOlivaCruaGranel", e);
	    		}
	    		
    		case Constants.PROCES_SORTIDA_OLIVA_BOTA_GRANEL:
	    		try{
	    			desti = iniciarSortidaOlivaBotaGranel(request);
		    		break;
	    		}catch (Exception e) {
	    			logger.error("Error iniciarSortidaOlivaBotaGranel", e);
	    		}
	    		
	    		
	    		
	    		
	    	}
    	}

    	
    	if (request.getParameter("zonaId")!=null && !request.getParameter("zonaId").equals("")){
        	Long zid = new Long(Integer.parseInt((String)request.getParameter("zonaId")));
        	try {
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		request.setAttribute("zonaFicticia", Boolean.valueOf(oliInfraestructuraEjb.zonaFicticia(zid)));
        	} catch(Exception e){
    			logger.error("EXCEPTION", e);
        	}    		
    	}
    	
    	myModel.put("zonaId", request.getParameter("zonaId"));
    	
    	if (tipusProces != null) {
    		String establimentId = request.getParameter("establimentId");
    		String zonaId = request.getParameter("zonaId");
    		if (establimentId != null && zonaId != null) {
    			request.setAttribute("establimentEx", establimentId);
    			if (request.getAttribute("zonaEx")==null) request.setAttribute("zonaEx", zonaId);
    		}
    		
    		return new ModelAndView("forward:" + desti + ".html");
    	} else {
    		String establimentId = request.getParameter("establimentId");
    		String zonaId = request.getParameter("zonaId");
    		if (establimentId != null && zonaId != null){
    			String url = "redirect:" + desti + "?";
    			url = url + "establimentId=" + establimentId + "&zonaId=" + zonaId;
    			return new ModelAndView(url);
    		}
    		else
    			return new ModelAndView("redirect:" + desti);
    	}
    	
    }


    
    private String iniciarElaboracionAceite(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	try{	    	
			HttpSession session = request.getSession();
			Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
			Collection destins = (Collection)session.getAttribute(seleccioSessionKeyDesti);
			
			String proces = "proces.elaboracion.titol";
			String spas = request.getParameter("pas");
			String cambizona = request.getParameter("cambizona");
			String accio = request.getParameter("accio");
			
			int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
			switch (pas) {
			case 1:
					if (origens.size() == 0){
						ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.partida"));
						request.setAttribute(
								"procesSeleccio",
								new ProcesSeleccio(
				    					2,
				    					proces,
				    					"ORIGEN",
				    					1,
				    					N, S, N));
					} else if(cambizona != null){

						if(accio.equals("DESTI")){
							request.setAttribute(
									"procesSeleccio",
					    			new ProcesSeleccio(
						    				2,
						    				proces,
						    				accio,
											2,
											S, N, N));
						}else{
							request.setAttribute(
									"procesSeleccio",
					    			new ProcesSeleccio(
						    				2,
						    				proces,
						    				accio,
											1,
											N, S, N));
						}					
						
					}else {
						request.setAttribute(
								"procesSeleccio",
								new ProcesSeleccio(
										2,
					    				proces,
					    				"DESTI",
				    					2,
				    					S, N, N));
					}
					break;
			case 2:
					
				/*
					//Vemos si todos las partidas petenecen al mismo olivicultor				
					List origenesList = (List)session.getAttribute(seleccioSessionKeyOrigen);
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Collection partidas =  oliInfraestructuraEjb.partidasInfo(origenesList.toArray());
					int i = 0;
					int idOli = 0;
					boolean partidasMismoOlivicultor = true;
					for (Iterator it = partidas.iterator(); it.hasNext();) {
						PartidaOliva pao = (PartidaOliva)it.next();
						if(pao.getOlivicultor()!= null && i == 0){
							idOli = pao.getOlivicultor().getId().intValue();
						}
						if(idOli != pao.getOlivicultor().getId().intValue()){
							partidasMismoOlivicultor = false;
							break;
						}
						i++;
	        		}
					*/
					//if (destins.size() == 0 && !partidasMismoOlivicultor) {	
//					if (destins.size() == 0) {					
//						
//						ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.desti.aceite"));// Ha de seleccionar como minimo un deposito
//						request.setAttribute(
//								"procesSeleccio",
//								new ProcesSeleccio(
//										2,
//				    					proces,
//				    					"DESTI",
//				    					2,
//				    					S, N, N));
//					} else {
						//guardarSeleccioOrigenIDesti(request);
			    		desti = "ProcesElaboracioOliForm";
//					}
					break;
			default:
					request.setAttribute(
							"procesSeleccio",
			    			new ProcesSeleccio(
				    				2,
				    				proces,
				    				"ORIGEN",
									1,
									N, S, N));
					break;
			}
			
			
    	}catch (Exception e) {
			logger.error("Error iniciarElaboracionAceite", e);
		}
    	return desti;
	}
    
    
    private String iniciarElaboracioOliva(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	try{	    	
			HttpSession session = request.getSession();
			Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
			//Collection destins = (Collection)session.getAttribute(seleccioSessionKeyDesti);
			
			String proces = "proces.elaboracion.oliva.verda.titol";
			String spas = request.getParameter("pas");
			String cambizona = request.getParameter("cambizona");
			String accio = request.getParameter("accio");
			
			int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
			switch (pas) {
			case 1:
					if (origens.size() == 0){
						ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.partida"));
						request.setAttribute(
								"procesSeleccio",
								new ProcesSeleccio(
				    					17,
				    					proces,
				    					"ORIGEN",
				    					1,
				    					N, S, N, S, N, N));
					} else if(cambizona != null){
						request.setAttribute(
								"procesSeleccio",
				    			new ProcesSeleccio(
					    				17,
					    				proces,
					    				"ORIGEN",
										1,
										N, S, N, S, N, N));
					} else if ("ORIGEN".equals(accio)){
						request.setAttribute(
								"procesSeleccio",
				    			new ProcesSeleccio(
					    				17,
					    				proces,
					    				"ORIGEN",
										1,
										N, S, N, S, N, N));
					} else {
						List origenesList = (List)session.getAttribute(seleccioSessionKeyOrigen);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Collection partidas =  oliInfraestructuraEjb.partidasInfo(origenesList.toArray());
						int i = 0;
						int tipusOlivaTaula = 0;
						boolean mateixTipus = true;
						for (Iterator it = partidas.iterator(); it.hasNext();) {
							PartidaOliva pao = (PartidaOliva)it.next();
							if(pao.getTipusOlivaTaula()!= null && i == 0){
								tipusOlivaTaula = pao.getTipusOlivaTaula();
							}
							if(tipusOlivaTaula != pao.getTipusOlivaTaula()){
								mateixTipus = false;
								break;
							}
							i++;
		        		}
						if (!mateixTipus) {
							ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.oliva.tipus"));// Ha de seleccionar como minimo un deposito
							request.setAttribute(
									"procesSeleccio",
									new ProcesSeleccio(
											17,
					    					proces,
					    					"ORIGEN",
					    					1,
					    					N, S, N, S, N, N));
						} else {
							desti = "ProcesElaboracioOlivaForm";
						}
					}
					break;
			default:
					request.setAttribute(
							"procesSeleccio",
			    			new ProcesSeleccio(
				    				17,
				    				proces,
				    				"ORIGEN",
									1,
									N, S, N, S, N, N));
					break;
			}
			
			
    	}catch (Exception e) {
			logger.error("Error iniciarElaboracionAceite", e);
		}
    	return desti;
	}
    
    
    private String iniciarTrasvase(HttpServletRequest request) throws RemoteException {
		String desti = "EstablimentPrincipal";
		try{
			HttpSession session = request.getSession();
			Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
			Collection destins = (Collection)session.getAttribute(seleccioSessionKeyDesti);
			String proces = "proces.trasbals.titol";
		String spas = request.getParameter("pas");
		String cambizona = request.getParameter("cambizona");
		String accio = request.getParameter("accio");
		int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
		switch (pas) {
		case 1:			
			if (origens.size() == 0){
				ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit"));
				request.setAttribute(
						"procesSeleccio",
		    			new ProcesSeleccio(
			    				3,
			    				proces,
			    				"ORIGEN",
								1,
								S, N, N));
				
			}else if(cambizona != null){

				if(accio.equals("DESTI")){
					request.setAttribute(
							"procesSeleccio",
			    			new ProcesSeleccio(
				    				3,
				    				proces,
				    				accio,
									2,
									S, N, N));
				}else{
					request.setAttribute(
							"procesSeleccio",
			    			new ProcesSeleccio(
				    				3,
				    				proces,
				    				accio,
									1,
									S, N, N));
				}
				
			}else{
				request.setAttribute("procesSeleccio", new ProcesSeleccio(
						3,
						proces,
						"DESTI",
						2,
						S, N, N) );
			}
			break;
		case 2:
			if (destins.size() == 0) {
				ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.unicoDesti"));// Ha de seleccionar un deposito
				request.setAttribute("procesSeleccio", new ProcesSeleccio(
						3,
						proces,
						"DESTI",
						2,
						S, N, N));
			}else if (destins.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
    			request.setAttribute("procesSeleccio", new ProcesSeleccio(
						3,
						proces,
						"DESTI",
						2,
						S, N, N));
    		}else if(cambizona != null){
    			request.setAttribute("procesSeleccio", new ProcesSeleccio(
						3,
						proces,
						"DESTI",
						2,
						S, N, N));
    		}else {
				desti = "ProcesTrasbalsForm";
			}
			break;
		default:
			request.setAttribute(
					"procesSeleccio",
	    			new ProcesSeleccio(
		    				3,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
		}
		}catch (Exception e) {
			logger.error("Error iniciarTrasvase", e);
		}
		return desti;
	}

    private String iniciarPerdues(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.perdues.titol";
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				4,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesPerduesForm";
        		break;
    		}    		
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				4,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }

    private String iniciarAnalitica(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.analitica.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				5,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "AnaliticaForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				5,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarPendentAnalitica(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.analitica.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				14,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "PendentAnaliticaForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				14,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarDesferPendentAnalitica(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.analitica.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				15,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "DesferPendentAnaliticaForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				15,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }

    private String iniciarDesqualificacion(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.desqualificar.titol";
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	String cambizona = request.getParameter("cambizona");
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				6,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "ProcesDesqualificarForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				6,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarCrearLote(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.crearLote.titol";
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				7,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if(cambizona == null){
    			desti = "ProcesLotForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				7,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarEnvasatOliva(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.crearLote.titol";
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				19,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, N, S, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    	case 2:
    		Bota bota = new Bota();
			try {
				bota = oliInfraestructuraEjb.botaAmbId((Long)origens.iterator().next());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    		if (bota.getTipusOlivaTaula()==Constants.TIPUS_OLIVA_TAULA_NEGRA) {
	    		request.setAttribute(
	    				"procesSeleccio",
		    			new ProcesSeleccio(
			    				19,
			    				proces,
			    				"ORIGEN",
								2,
								S, N, N, N, N, S));
	    		if (pas==1){
	    			desti = "ProcesEnvasatOlivaForm";
	    			break;
	    		} 
	    		if (origens.size()==2 && cambizona==null) {
	    			desti = "ProcesEnvasatOlivaForm";
	        		break;
	    		}
	    		if (origens.size() > 2) {
	    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
	        		break;
	    		}
	    		if (origens.size() == 1) {
	    			desti = "ProcesEnvasatOlivaForm";
	        		break;
	    		}
    		} else if(cambizona == null){
    			desti = "ProcesEnvasatOlivaForm";
        		break;
    		}
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				19,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, N, S, N));
    	}
    	return desti;
    }
    
    private String iniciarEtiquetar(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.etiquetar.titol";
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				8,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, S));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.lote.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.lote.uno"));
        		break;
    		}
    		if(cambizona == null){
    			desti = "ProcesEtiquetarForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				8,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, S));
    	}
    	return desti;
    }
    
    private String iniciarEtiquetatOlivaTaula(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.etiquetar.titol";
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				20,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, S));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.lote.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.lote.uno"));
        		break;
    		}
    		if(cambizona == null){
    			desti = "ProcesEtiquetarOlivaTaulaForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				20,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, S));
    	}
    	return desti;
    }
    
    
    private String iniciarSortidaOli(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.sortidaOli.titol";
    	boolean mostrarLot = ((request.getAttribute("esTafona") == null && request.getAttribute("esEnvasadora") == null) || (request.getAttribute("esEnvasadora") != null));
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				9,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
        		break;
    		}
    		try {
    			if (!origens.isEmpty()){ 
    				Long id = (Long) origens.iterator().next();
    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
    					ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.moure"));
    					break;
    				}else{
    					request.setAttribute("tipusSortida", "l");
    				} 
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesCambioZonaForm";
        		break;
    		}
			
    	
    	case 2:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				9,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
//    		if (origens.size() > 1) {
//    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
//        		break;
//    		}
    		
    		try {
    			
    			if (!origens.isEmpty()){
	    			boolean sonLots = false;
	    			boolean sonDips = false;
	    			boolean valid = true;
					for (Iterator it = origens.iterator(); it.hasNext();){
						Long id = (Long)it.next();
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
							if (sonDips){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.un"));
								valid = false;
								break;
							} else if (sonLots){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.lot"));
								valid = false;
								break;
							}
							sonDips = true;
						} else {
							if (sonDips){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.lot"));
								valid = false;
								break;
							}
							sonLots = true;
						}
					}
					if (valid) {
						if (sonLots){
							request.setAttribute("tipusSortida", "l");
						} else {
							request.setAttribute("tipusSortida", "d");
						}
					} else break;
    			}
    			
//    			if (!origens.isEmpty()){ // && origens.iterator().next().getClass().equals(Diposit.class)) {
//    				Long id = (Long) origens.iterator().next();
//    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
//    					request.setAttribute("tipusSortida", "d");
//    				} else {
//    					request.setAttribute("tipusSortida", "l");
//    				}
//    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesSortidaForm";    		
    			break;
    		}
    		
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				9,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    	}
    	return desti;
    }
    
    private String iniciarSortidaOliva(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.sortidaOliva.titol";
    	boolean mostrarLot = ((request.getAttribute("esTafona") == null && request.getAttribute("esEnvasadora") == null) || (request.getAttribute("esEnvasadora") != null));
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				21,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
        		break;
    		}
    		try {
    			if (!origens.isEmpty()){ 
    				Long id = (Long) origens.iterator().next();
    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
    					ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.moure"));
    					break;
    				}else{
    					request.setAttribute("tipusSortida", "l");
    				} 
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesCambioZonaForm";
        		break;
    		}
			
    	
    	case 2:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				21,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
//    		if (origens.size() > 1) {
//    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
//        		break;
//    		}
    		
    		try {
    			
    			if (!origens.isEmpty()){
	    			boolean sonLots = false;
	    			boolean sonDips = false;
	    			boolean valid = true;
					for (Iterator it = origens.iterator(); it.hasNext();){
						Long id = (Long)it.next();
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
							if (sonDips){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.un"));
								valid = false;
								break;
							} else if (sonLots){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.lot"));
								valid = false;
								break;
							}
							sonDips = true;
						} else {
							if (sonDips){
								ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.diposit.lot"));
								valid = false;
								break;
							}
							sonLots = true;
						}
					}
					if (valid) {
						if (sonLots){
							request.setAttribute("tipusSortida", "l");
						} else {
							request.setAttribute("tipusSortida", "d");
						}
					} else break;
    			}
    			
//    			if (!origens.isEmpty()){ // && origens.iterator().next().getClass().equals(Diposit.class)) {
//    				Long id = (Long) origens.iterator().next();
//    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
//    					request.setAttribute("tipusSortida", "d");
//    				} else {
//    					request.setAttribute("tipusSortida", "l");
//    				}
//    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesSortidaOlivaTaulaForm";    		
    			break;
    		}
    		
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				21,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, mostrarLot));
    	}
    	return desti;
    }
    
    
    private String iniciarSortidaOlivaCruaGranel(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.sortidaOlivaCruaGranel.titol";
    	//boolean mostrarLot = ((request.getAttribute("esTafona") == null && request.getAttribute("esEnvasadora") == null) || (request.getAttribute("esEnvasadora") != null));
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				24,
		    				proces,
		    				"ORIGEN",
							2,
							S, N, N,S,S,N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
        		break;
    		}
    		try {
    			if (!origens.isEmpty()){ 
    				Long id = (Long) origens.iterator().next();
    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
    					ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.moure"));
    					break;
    				}else{
    					request.setAttribute("tipusSortida", "l");
    				} 
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesCambioZonaForm";
        		break;
    		}
			
    	
    	case 2:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				24,
		    				proces,
		    				"ORIGEN",
							2,
							N, S, N,S,N,N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		
    		try {
    			
    			if (!origens.isEmpty()){
	    			boolean sonPartides = false;
	    			boolean valid = true;
	    			Collection partides = new HashSet();
					for (Iterator it = origens.iterator(); it.hasNext();){
						Long id = (Long)it.next();
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						if (oliInfraestructuraEjb.partidaAmbId(id) != null) {
							PartidaOliva partida = oliInfraestructuraEjb.partidaAmbId(id);
							partides.add(partida);
							sonPartides = true;
						} 
					}
					request.setAttribute("partides", partides);
					if (valid) {
						if (sonPartides){
							request.setAttribute("tipusSortida", "p");
						}
					} else break;
    			}
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesSortidaOlivaTaulaCruaGranelForm";    		
    			break;
    		}
    		
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				24,
		    				proces,
		    				"ORIGEN",
							2,
							N, S, N,S,N,N));
    	}
    	return desti;
    }
    
    
    private String iniciarSortidaOlivaBotaGranel(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.sortidaOliva.titol";
//    	boolean mostrarLot = ((request.getAttribute("esTafona") == null && request.getAttribute("esEnvasadora") == null) || (request.getAttribute("esEnvasadora") != null));
    	String spas = request.getParameter("pas");
    	String cambizona = request.getParameter("cambizona");
    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				25,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N,S,S,N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen.uno"));
        		break;
    		}
    		try {
    			if (!origens.isEmpty()){ 
    				Long id = (Long) origens.iterator().next();
    				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    				if (oliInfraestructuraEjb.dipositAmbId(id) != null) {
    					ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.moure"));
    					break;
    				}else{
    					request.setAttribute("tipusSortida", "b");
    				} 
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesSortidaOlivaTaulaBotaGranelForm";
        		break;
    		}
			
    	
    	case 2:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				25,
		    				proces,
		    				"ORIGEN",
							2,
							N, N, N,S,S,N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.origen"));
        		break;
    		}
    		
    		try {
    			
    			if (!origens.isEmpty()){
	    			boolean valid = true;
	    			boolean sonBotes = false;
					for (Iterator it = origens.iterator(); it.hasNext();){
						Long id = (Long)it.next();
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						if (oliInfraestructuraEjb.botaAmbId(id) != null) {
							sonBotes = true;
						} else {
							sonBotes = false;
						}
					}
					if (valid) {
						if (sonBotes){
							request.setAttribute("tipusSortida", "b");
						}
					}else break;
    			}
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    		if( cambizona == null ){
    			desti = "ProcesSortidaOlivaTaulaBotaGranelForm";    		
    			break;
    		}
    		
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				25,
		    				proces,
		    				"ORIGEN",
							2,
							N, N, N,S,S,N));
    	}
    	return desti;
    }
    
    
    
    private String iniciarAutocontrol(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.analitica.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");    	
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				22,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, S, S, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		}
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "AutocontrolForm";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				22,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, S, S, N));
    	}
    	return desti;
    }
    
    private String iniciarConsultaAutocontrol(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.consultarAutocontrol.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				23,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, N, S, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		} 
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "ProcesConsultaAutocontrolLlistat";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				23,
		    				proces,
		    				"ORIGEN",
							1,
							N, N, N, N, S, N));
    	}
    	return desti;
    }
    
    private String iniciarMoureDeposit(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.moureDiposit.titol";
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				10,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit"));
        		break;
    		}    		
			desti = "ProcesMoureDipositForm";
    		break;
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				10,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarMoureOli(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.moureOli.titol";
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				12,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit"));
        		break;
    		}    		
			desti = "ProcesMoureOliForm";
    		break;
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				12,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    private String iniciarAcceptarOliGranel(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection destins = (Collection)session.getAttribute(seleccioSessionKeyDesti);
    	String proces = "proces.acceptarOliGranel.titol";

    	if(request.getParameter("trasllatId") != null){
    		request.getSession().setAttribute("trasllatId", request.getParameter("trasllatId"));
    	}
    	
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				13,
		    				proces,
		    				"DESTI",
							1,
							S, N, N));
    		if (destins.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit"));
        		break;
    		}    	
    		if (destins.size( )> 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}  
			desti = "ProcesAcceptarOliGranelForm";
    		break;
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				13,
		    				proces,
		    				"DESTI",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    
    private String iniciarConsultaAnaliticas(HttpServletRequest request) {
    	String desti = "EstablimentPrincipal";
    	HttpSession session = request.getSession();
    	Collection origens = (Collection)session.getAttribute(seleccioSessionKeyOrigen);
    	String proces = "proces.consultarAnalitica.titol";
    	String cambizona = request.getParameter("cambizona");
    	String spas = request.getParameter("pas");
    	int pas = (spas == null || spas.equalsIgnoreCase("")) ? 0 : Integer.parseInt(spas);
    	switch (pas) {
    	case 1:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				11,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    		if (origens.size( )== 0) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.unico"));
        		break;
    		} 
    		if (origens.size() > 1) {
    			ControllerUtils.saveMessageError(request, missatge("procesInici.missatge.contenidor.diposit.uno"));
        		break;
    		}
    		if( cambizona == null ){
    			desti = "ProcesConsultaAnaliticaLlistat";
        		break;
    		}
			
    	default:
    		request.setAttribute(
    				"procesSeleccio",
	    			new ProcesSeleccio(
		    				11,
		    				proces,
		    				"ORIGEN",
							1,
							S, N, N));
    	}
    	return desti;
    }
    
    /**
     * Injecció de la dependència infraestructuraEjb
     *
     * @param infraestructuraEjb La classe a injectar.
     */
       
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
     * Injecció de la dependència seleccioSessionKey
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKey(String seleccioSessionKey) {
        this.seleccioSessionKey = seleccioSessionKey;
    }

    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }

    public void setSeleccioSessionKeyDesti(String seleccioSessionKeyDesti) {
        this.seleccioSessionKeyDesti = seleccioSessionKeyDesti;
    }

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }
    private String missatge(String clave){
    	String valor = applicationContext.getMessage(clave, null, null);
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