package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliUsuariEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Zona;
import es.caib.gestoli.logic.util.Constants;

/**
 * <p>Controlador per a la pantalla principal del establecimiento.</p>
 * <p>Retorna la informaciÃ³ per omplir els combos d'establiment
 * i zona i tambÃ© la informaciÃ³ dels diposits de la zona
 * seleccionada. Els parÃ metres relevants per a aquest
 * controlador son:
 * <ul>
 *     <li>establimentId: id de l'establiment seleccionat</li>
 *     <li>zonaId: id de la zona seleccionada</li>
 *     <li>clearZones: val 'S' quan canviam d'establiment. AixÃ­
 *     es pot esbrinar quina Ã©s la zona seleccionada</li>
 * </ul></p>
 * 
 * @author Carlos PÃ©rez <cperez@at4.net>
 */
public class EstablimentPrincipalController implements Controller {


	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliUsuariEjb oliUsuariEjb;
	private String usuariSessionKey;
	private String establimentSessionKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;
	private Integer olivaAltres;
	private String colorAltres;
	private String desqualificat;
	private String pendentQualificar;
	private String qualificat;
	private String seleccioSessionKeyOrigen;
	private String trazaPartidas;
	private String trazaDiposits;
	private String trazaLots;
	private String trazaBotes;
	private String trazaEnvasos;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta funciÃ³n en el caso de que se haya ejecutado la
	 * validaciÃ³n sin producir ningÃºn error.
	 * 
	 * @see Controller#handleRequest(HttpServletRequest, HttpServletResponse)
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public ModelAndView handleRequest(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException {
		Map myModel = new HashMap();

		HttpSession session = request.getSession();
		ProcesSeleccio procesSeleccio = (ProcesSeleccio)request.getAttribute("procesSeleccio");


		try  {
			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
			
			
			String establimentId = request.getParameter("establimentId");
			if (establimentId != null){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(Long.valueOf(establimentId));
			}
			myModel.put("establimentId", establiment.getId().toString());    		    		

			if (establiment !=null) {
				myModel.put("establiment",establiment );
				myModel.put("path", "vista_establecimiento");
				if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
					myModel.put("path_extension1", establiment.getNom());
					myModel.put("establecimientNom", establiment.getNom());
				}    			

				// Introdueix el llistat de les zones		        
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection zones = oliInfraestructuraEjb.zonaActiusAmbEstabliment(establiment.getId());

				Long zid = null;
				Boolean zonaFicticia = Boolean.valueOf(false);
				if (zones != null && zones.size() > 0) {
					// Si hi ha zones
					myModel.put("zones", zones);
					String zonaId = request.getParameter("zonaId");
					if (zonaId != null && !"S".equals(request.getParameter("clearZones"))) {
						zid = new Long(zonaId);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						zonaFicticia=new Boolean(oliInfraestructuraEjb.zonaFicticia(zid));
					} else {
						// Si no hi ha zona seleccionada selecciona la zona per defecte de
						// l'establiment. Si no n'hi ha selecciona (la primera del establiment -- AHORA NO) no situats. 
						for (Iterator it = zones.iterator(); it.hasNext();) {
							Zona z = (Zona)it.next();		        			
							if (zid == null){
								zid = z.getId();
								zonaFicticia=z.getFictici();
							}
							if (z.getDefecte().booleanValue()) {
								zid = z.getId();
								zonaFicticia=z.getFictici();
								break;
							}		        			
						}
						//CARLOS: esto se usaba en caso de no existir zona por defecto establecer como zona por defecto la zona de NS
						//if (!defecte) zid = null;
					}
				}

				if (zid == null) {
					//CARLOS: esto se usaba en caso de no existir zona por defecto o no existir zomas establecer como zona por defecto la zona de NS

					/*
		        	myModel.put("zonaId", "NS");
		            // Introdueix el llistat dels diposits no situats

		        	Collection contsNoRaim = infraestructuraEjb.contenidorInfo(eid, null, null, new Boolean(false), true);
	    			esborrarTipusProhibits(contsNoRaim, procesSeleccio);
	    			myModel.put("diposits", contsNoRaim);
	    			Collection contsRaim = infraestructuraEjb.contenidorInfo(eid, null, null, new Boolean(true), true);
	    			esborrarTipusProhibits(contsRaim, procesSeleccio);
	    			myModel.put("partidesRaim", contsRaim);
					 */
				} else {
					myModel.put("zonaId", zid.toString());
					request.setAttribute("zonaFicticia", zonaFicticia);


					Collection dipos = new ArrayList();
					Collection partides = null;
					Collection lotes = null;
					Collection botes = null;

					if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("2") ){
						/**************************************/
						/******* ELABORACION DE ACEITE ********/
						/**************************************/
						myModel.put("path", "elaboracion");
						myModel.put("path_extension1", null);

						//	Listado de depositos ACTIVOS , VACIOS O NO llenos con aceite con categoria PENDIENTE DE CUALIFICAR o DESCUALIFICADA
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoLlenosByZonaAndCategorias(zid, categorias.toArray());

						// Listado de partides d'oliva que no se han utilizado para ninguna elaboracion
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						partides = oliInfraestructuraEjb.findPartidesNoUsadasByZona(zid);


					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("3")){
						/**************************************/
						/********** TRASVASE DE ACEITE ********/
						/**************************************/
						myModel.put("path", "trasbalsOli");
						myModel.put("path_extension1", null);
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						if(procesSeleccio.getAccio().equalsIgnoreCase("ORIGEN")){
							//	Listado de depositos ACTIVOS , NO VACIOS , con aceite con categoria PENDIENTE DE CUALIFICAR o DESCUALIFICADA
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());
						}else{	
							//	Listado de depositos ACTIVOS , VACIOS O NO llenos con aceite con categoria PENDIENTE DE CUALIFICAR o DESCUALIFICADA
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							dipos = oliInfraestructuraEjb.findDipositsActiusNoLlenosByZonaAndCategorias(zid, categorias.toArray());
							// Eliminamos los depositos que son origen en caso de estar en los posibles dipos destino
							ArrayList origenList = (ArrayList)request.getSession().getAttribute(seleccioSessionKeyOrigen);
							Collection diposAux = new ArrayList();
							Diposit diposit = new Diposit();
							for(Iterator it = dipos.iterator();it.hasNext();){
								diposit = (Diposit) it.next();
								if(!origenList.contains(diposit.getId())){									
									diposAux.add(diposit);
								}
							}
							dipos = diposAux;
						}


					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("4")){
						/**************************************/
						/********** PERDIDA DE ACEITE ********/
						/**************************************/
						myModel.put("path", "perdues");
						myModel.put("path_extension1", null);
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						//Listado de depositos ACTIVOS , NO VACIOS
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("5")){
						/**************************************/
						/******** INTRODUCIR ANALITICA ********/
						/**************************************/
						myModel.put("path", "introducir_analitica");
						if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
							myModel.put("path_extension1", establiment.getNom());
						}
						//Listado de depositos ACTIVOS , NO VACIOS 
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("6")){
						/**************************************/
						/******** DESQUALIFICAR OLI ********/
						/**************************************/
						myModel.put("path", "desqualificar_oli");
						myModel.put("path_extension1", null);
						//Recuperamos los depositos ACTIVOS con aceite de la categoria QUALIFICAT
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("7")){
						/**************************************/
						/******** CREAR LOTE ********/
						/**************************************/
						myModel.put("path", "embotellar");
						myModel.put("path_extension1", null);
						//Recuperamos los depositos ACTIVOS con aceite de la categoria QUALIFICAT
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						categorias.add(Integer.valueOf(desqualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("8")){
						/**************************************/
						/******** ETIQUETAR OLI********/
						/**************************************/
						myModel.put("path", "etiquetar");
						myModel.put("path_extension1", null);
						//	Recuperamos los LOTES NO ETIQUETADOS con aceite de la categoria QUALIFICAT
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						lotes = oliInfraestructuraEjb.findLotesByZonaAndCategorias(zid, categorias.toArray());	
						
					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("20")){
						/**************************************/
						/******** ETIQUETAR OLIVA TAULA ********/
						/**************************************/
						myModel.put("path", "etiquetar");
						myModel.put("path_extension1", null);
						//	Recuperamos los LOTES NO ETIQUETADOS con aceite de la categoria QUALIFICAT
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						lotes = oliInfraestructuraEjb.findLotesOlivaTaulaByZona(zid);		

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("9")){
						/*************************************/
						/************ SORTIDA OLI ************/
						/*************************************/
						myModel.put("path", "sortida_oli");
						myModel.put("path_extension1", null);
						// Recuperamos los depÃ³sitos de la zona
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());
						//Recuperamos los LOTES DE LA ZONA (solo se veran para rol envasador)
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Collection lots = new ArrayList();
						lotes = oliInfraestructuraEjb.findLotesNoVendidosByZona(zid, false, false);
						if(lotes.size()>0){
							for(Object lo:lotes){
								Lot lotL = (Lot)lo;
								if(lotL.getPartidaOli().getEsDO()){
									if(lotL.getEtiquetesLots().size() > 0){
										lots.add(lotL);
									}
								}else{
									lots.add(lotL);
								}
							}
							lotes.clear();
							lotes.addAll(lots);	
						}
					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("21")){
						/*************************************/
						/************ SORTIDA OLIVA ************/
						/*************************************/
						myModel.put("path", "sortida_oliva");
						myModel.put("path_extension1", null);
						// Recuperamos los depÃ³sitos de la zona
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						//dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());
						//Recuperamos los LOTES DE LA ZONA (solo se veran para rol envasador)
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						lotes = oliInfraestructuraEjb.findLotesNoVendidosByZona(zid, false, true);				        
					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("22")){
						/**************************************/
						/******** INTRODUCIR AUTOCONTROL ********/
						/**************************************/
						myModel.put("path", "introducir_autocontrol");
						if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
							myModel.put("path_extension1", establiment.getNom());
						}
						//Listado de depositos ACTIVOS , NO VACIOS 
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));
						categorias.add(Integer.valueOf(qualificat));
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						botes = oliInfraestructuraEjb.findBotesActivesByZona(zid);

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("23")){
						/**************************************/
						/******** CONSULTAR AUTOCONTROL  ********/
						/**************************************/
						
						if(request.getParameter("fromEstabliment")!= null && request.getParameter("fromEstabliment").equalsIgnoreCase("true")){
							myModel.put("path", "consultar_autocontrolsEstabliment");
							if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
								myModel.put("path_extension1", establiment.getNom());
							}
						}else{
							myModel.put("path", "consultar_autocontrols");
							myModel.put("path_extension1", null);
						}
						
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));				         
						//	Listado de depositos ACTIVOS , NO VACIOS , con aceite con cualquier categoria
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						botes = oliInfraestructuraEjb.findBotesActivesByZona(zid);


					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("10")){
						/**************************************/
						/******** MOURE DIPOSIT ********/
						/**************************************/
						myModel.put("path", "moure_diposit");
						myModel.put("path_extension1", null);
			         
						//	Listado de depositos ACTIVOS , con aceite con cualquier categoria
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusByZonaNoTraslladats(zid);
						//dipos = oliInfraestructuraEjb.findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("12")){
						/**************************************/
						/******** MOURE OLI *******************/
						/**************************************/
						myModel.put("path", "moure_oli");
						myModel.put("path_extension1", null);
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));				         
						//	Listado de depositos ACTIVOS , NO VACIOS , con aceite con cualquier categoria
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());
						//dipos = oliInfraestructuraEjb.findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("13")){
						/**************************************/
						/******** ACCEPTAR OLI GRANEL *********/
						/**************************************/
						myModel.put("path", "acceptarOliGranel");
						myModel.put("path_extension1", null);
			         
						//	Listado de depositos ACTIVOS , NO VACIOS , con aceite con cualquier categoria
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusByZona(zid);
						//dipos = oliInfraestructuraEjb.findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(zid, categorias.toArray());

					}else if( request.getAttribute("tipusProces")!= null && ((String)request.getAttribute("tipusProces")).equals("11")){
						/**************************************/
						/******** CONSULTAR ANALITICA  ********/
						/**************************************/
						
						if(request.getParameter("fromEstabliment")!= null && request.getParameter("fromEstabliment").equalsIgnoreCase("true")){
							myModel.put("path", "consultar_analiticasEstabliment");
							if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
								myModel.put("path_extension1", establiment.getNom());
							}
						}else{
							myModel.put("path", "consultar_analiticas");
							myModel.put("path_extension1", null);
						}
						
						List categorias = new ArrayList();
						categorias.add(Integer.valueOf(qualificat));
						categorias.add(Integer.valueOf(desqualificat));
						categorias.add(Integer.valueOf(pendentQualificar));				         
						//	Listado de depositos ACTIVOS , NO VACIOS , con aceite con cualquier categoria
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusNoVaciosByZonaAndCategorias(zid, categorias.toArray());


					}else{				    	 
						//Llista de diposits por zona
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						dipos = oliInfraestructuraEjb.findDipositsActiusByZonaNoFicticio(zid);
						// Listado de partides d'oliva que no se han utilizado para ninguna elaboracion
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						partides = oliInfraestructuraEjb.findPartidesNoUsadasByZona(zid);
						// Llista de lotes que no han sido vendidos d'oli por zona  
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						lotes = oliInfraestructuraEjb.findLotesNoVendidosByZona(zid, true);
						// Llista de lotes que no han sido vendidos d'oli por zona  
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						botes = oliInfraestructuraEjb.findBotesActivesByZona(zid);
						
					}

					if (procesSeleccio != null) {	    	
						if (procesSeleccio.isOlivaTaula()) {
							partides = oliInfraestructuraEjb.findPartidesOlivaTaulaNoUsadasByZona(zid);
						} else {
							partides = oliInfraestructuraEjb.findPartidesPerOliNoUsadasByZona(zid);
						}
						if (procesSeleccio.isMostrarNomesDO()) {
							Collection tmpDipos = new HashSet();
							for (Iterator it= dipos.iterator(); it.hasNext();){
								Diposit dip = (Diposit) it.next();
								if (dip.getPartidaOli()!=null) 
									if (dip.getPartidaOli().getEsDO()==true && dip.getVolumActual()>0) tmpDipos.add(dip);
							}
							dipos = tmpDipos;
							Collection tmpLotes = new HashSet();
							for (Iterator it= lotes.iterator(); it.hasNext();){
								Lot lot = (Lot) it.next();
								Boolean olivaTaula = lot.getOlivaTaula() !=null? lot.getOlivaTaula(): false;
								if (lot.getPartidaOli()!=null && !olivaTaula) 
									if (lot.getPartidaOli().getEsDO()==true && lot.getNumeroBotellesActuals()>0) tmpLotes.add(lot);
							}
							lotes = tmpLotes;
						}
						if (!procesSeleccio.isMostrarPartida())
							partides = null;
						if (!procesSeleccio.isMostrarDiposit())
							dipos = null;	
						if (!procesSeleccio.isMostrarLote())
							lotes = null;
						if (!procesSeleccio.isMostrarBotes())
							botes = null;
					}
					
					/* Joan - Creem array amb valors per contenidors en cami­ */
					
					List<Object[]> dipEnCami = new ArrayList<Object[]>();
					List diposSenseContsEnCami = new ArrayList();
					List dipositsAmbContsEnCami = new ArrayList();
					
					if(dipos != null){
						for(Object o: dipos){
							Diposit d = (Diposit)o;
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
							
							//Si es tracta d'una accio NO s'han de visualitzar els contenidors en cami
							if( request.getAttribute("tipusProces")!= null){
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								if(!oliInfraestructuraEjb.findContenidorEnCami(d.getId())){
									infoDiposit.setEnCami(false);
									diposSenseContsEnCami.add(infoDiposit);
									
									obj[0] = d.getId();
									obj[1] = false;
									dipEnCami.add(obj);
								}
							} else { //Si es la vista principal, nomes posam el contenidor apunt per ser transparent
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								if(oliInfraestructuraEjb.findContenidorEnCami(d.getId())){
									obj[0] = d.getId();
									obj[1] = true;
									infoDiposit.setEnCami(true);
									dipEnCami.add(obj);
								} else {
									obj[0] = d.getId();
									obj[1] = false;
									infoDiposit.setEnCami(false);
									dipEnCami.add(obj);
								}
								
								dipositsAmbContsEnCami.add(infoDiposit);
							}
						}
					}
					
					Collection lots = new ArrayList();
					if (lotes != null) {
						for(Object o: lotes){
							Lot l = (Lot)o;
							InformacioLotEstablimentCommand infoLot = new InformacioLotEstablimentCommand();
							infoLot.setId(l.getId());
							infoLot.setZona(l.getZona());
							infoLot.setEtiquetatge(l.getEtiquetatge());
							infoLot.setPartidaOli(l.getPartidaOli());
							infoLot.setDiposit(l.getDiposit());
							infoLot.setData(l.getData());
							infoLot.setCodiAssignat(l.getCodiAssignat());
							infoLot.setAcidesa(l.getAcidesa());
							infoLot.setLitresPerduts(l.getLitresPerduts());
							infoLot.setMotiuPerdua(l.getMotiuPerdua());
							infoLot.setNumeroBotellesInicials(l.getNumeroBotellesInicials());
							infoLot.setLitresEnvassats(l.getLitresEnvassats());
							infoLot.setPosicioX(l.getPosicioX());
							infoLot.setPosicioY(l.getPosicioY());
							infoLot.setObservacions(l.getObservacions());
							infoLot.setValid(l.getValid());
							infoLot.setNumeroBotellesActuals(l.getNumeroBotellesActuals());
							infoLot.setCreatTancament(l.getCreatTancament());
							infoLot.setNumeroLot(l.getNumeroLot());
							infoLot.setDataConsum(l.getDataConsum());
							infoLot.setSortidaDiposits(l.getSortidaDiposits());
							infoLot.setVarietatOlivas(l.getVarietatOlivas());
							infoLot.setSortidaLots(l.getSortidaLots());
							infoLot.setEntradaLots(l.getEntradaLots());
							infoLot.setProducte(l.getProducte());
							infoLot.setOlivaTaula(l.getOlivaTaula());
							infoLot.setTipusOlivaTaula(l.getTipusOlivaTaula());
							infoLot.setOlivaDO(l.getOlivaDO());
							infoLot.setLotTap(l.getLotTap());
							
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							Collection etiquetesLot = oliInfraestructuraEjb.findEtiquetesLotByIdLot(l.getId());
							List<Object[]> etiquetes = new ArrayList<Object[]>();
							for (Object obj : etiquetesLot) {
								EtiquetesLot el = (EtiquetesLot)obj;
								Object[] etiq = new Object[2];
								etiq[0] = el.getEtiquetaInicial();
								etiq[1] = el.getEtiquetaFinal();
								etiquetes.add(etiq);
							}
							infoLot.setEtiquetesLot(etiquetes);
							lots.add(infoLot);

							
							
							
						}
					}

					myModel.put("dipositsEnCami", dipEnCami);
					
					if( request.getAttribute("tipusProces")!= null){
						//Proces pendent analitica: només mostra contenidors que no estiguin precintats
						//Proces desfer pendent analitica: només mostra contenidors precintats
						//Altres processos: no mostra contenidors precintats
						List dipositsProces = new ArrayList();
						int proces = Integer.parseInt((String)request.getAttribute("tipusProces"));
						for(Object o: diposSenseContsEnCami){
							InformacioDipositEstablimentCommand info = (InformacioDipositEstablimentCommand)o;
							
							if(proces == Constants.PROCES_PENDENT_ANALITICA && !info.getPrecintat()){
								dipositsProces.add(o);
							}
							
							if(proces == Constants.PROCES_DESFER_PENDENT_ANALITICA && info.getPrecintat()){
								dipositsProces.add(o);
							}
							
							if(proces == Constants.PROCES_ANALITICA){
								dipositsProces.add(o);
							}
							
							if(proces != Constants.PROCES_PENDENT_ANALITICA && 
							   proces != Constants.PROCES_DESFER_PENDENT_ANALITICA && 
							   proces != Constants.PROCES_ANALITICA && 
							   !info.getPrecintat()){
								dipositsProces.add(o);
							}
							
						}
						myModel.put("diposits", dipositsProces);
					} else {
						myModel.put("diposits", dipositsAmbContsEnCami);
					}
					
					myModel.put("partides", partides);
					myModel.put("lotes", lots);
					myModel.put("botes", botes);

					myModel.put("olivaAltres", this.olivaAltres);
					myModel.put("colorAltres", this.colorAltres);
					
					myModel.put("trazaPartidas", this.trazaPartidas);
					myModel.put("trazaDiposits", this.trazaDiposits);
					myModel.put("trazaLots", this.trazaLots);
					myModel.put("trazaBotes", this.trazaBotes);
					myModel.put("trazaEnvasos", this.trazaEnvasos);
				}

			}


		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		myModel.put("procsel", procesSeleccio);

		return new ModelAndView("EstablimentPrincipal", myModel);
	}


	/*
    private void esborrarTipusProhibits (Collection seleccioInfo, ProcesSeleccio procesSeleccio) throws RemoteException {

    	if (procesSeleccio != null) {

	    	Collection esborrar = new ArrayList();
	    	for (Iterator it = seleccioInfo.iterator(); it.hasNext();) {
	    		Object[] contInfo = (Object[])it.next();

	    		TipusProducte tp = infraestructuraEjb.contenidorTipusProducte((Long)contInfo[0]);
	    		boolean correcto = false;
	    		if (tp != null) {
	    			// Cas de que no estigui buit
		    		if (procesSeleccio.isMostrarRaim() && tp.getId().longValue() == 1)
		    			correcto = true;
		    		if (procesSeleccio.isMostrarMost() && tp.getEsMost().booleanValue()) {
		    			if (procesSeleccio.getTipusProces()==15 || procesSeleccio.getTipusProces()==17) {
		    				if (procesSeleccio.getAccio().equalsIgnoreCase("ORIGEN") && tp.getColor().intValue()==3) correcto = true;
		    				if (procesSeleccio.getAccio().equalsIgnoreCase("DESTI") && tp.getColor().intValue()==2) correcto = true;
		    			}
		    			else correcto = true;
		    			if (correcto && procesSeleccio.getColor()!= null) {
		    				if (procesSeleccio.getColor().intValue()!=tp.getColor().intValue()) correcto = false;
			    		}
		    		}
		    		if (procesSeleccio.isMostrarVi()) {
		    			if (tp.getEsVi().booleanValue()) {
		    				int tipusContenidor = ((Long)contInfo[22]).intValue();
		    				correcto = (tipusContenidor != 4 || procesSeleccio.isMostrarBotella());
		    			}
		    			if (correcto && procesSeleccio.getColor()!= null) {
		    				if (procesSeleccio.getColor().intValue()!=tp.getColor().intValue()) correcto = false;
			    		}
		    		}
		    		if (procesSeleccio.isMostrarResultatPracPrc()) {
		    			if ((tp.getEsResultatPractica().booleanValue() || tp.getEsResultatProcesElaboracio().booleanValue()) && tp.getEsProducte().booleanValue()) {
		    				int tipusContenidor = ((Long)contInfo[22]).intValue();
		    				correcto = (tipusContenidor != 4 || procesSeleccio.isMostrarBotella());
		    			}
		    		}
		    		if (procesSeleccio.isMostrarBotella()) {
	    				int tipusContenidor = ((Long)contInfo[22]).intValue();
	    				if (tipusContenidor == 4) {
	    					correcto = true;
	    				}
		    		}
		    		if (procesSeleccio.isMostrarProducte()) {
		    			if (tp.getEsProducte().booleanValue() && (!tp.getEsResultatPractica().booleanValue() || !tp.getEsResultatProcesElaboracio().booleanValue()))
		    				correcto = true;
		    		}
		    		if (procesSeleccio.isMostrarDesfangat()) {
		    			if (tp.getEsDesfangat().booleanValue())
		    				correcto = true;
		    		}
		    		if (!procesSeleccio.isMostrarPle()) {
		    			Float capacitat = (Float)contInfo[2];
		    			float contingut = ((Float)contInfo[10]).floatValue();
		    			if (contInfo[11] != null)
		    				contingut -= ((Float)contInfo[11]).floatValue();
		    			if (contingut>=capacitat.floatValue()) correcto = false;
		    		}

	    		} else {
	    			// Cas de que sigui buit
	    			correcto = procesSeleccio.isMostrarBuit();
	    		}
	    		if (correcto && procesSeleccio.getSeleccio() != null) {
	    			for (int i = 0; i < procesSeleccio.getSeleccio().length; i++) {
	    				Long contId = procesSeleccio.getSeleccio()[i];
	    				if (((Long)contInfo[0]).longValue() == contId.longValue())
	    					correcto = false;
	    			}
	    		}
	    		if (!correcto)
	    			esborrar.add(contInfo);
	    	}
	    	seleccioInfo.removeAll(esborrar);	    	
    	}

    }
	 */

	/**
	 * InjecciÃ³ de la dependÃ¨ncia oliInfraestructuraEjb
	 *
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia oliUsuariEjb
	 *
	 * @param oliUsuariEjb La classe a injectar.
	 */
	public void setOliUsuariEjb(OliUsuariEjb oliUsuariEjb) {
		this.oliUsuariEjb = oliUsuariEjb;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}



	/**
	 * InjecciÃ³ de la dependÃ¨ncia esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setOlivaAltres(Integer olivaAltres) {
		this.olivaAltres = olivaAltres;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setColorAltres(String colorAltres) {
		this.colorAltres = colorAltres;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia desqualificat
	 * @param desqualificat La classe a injectar.
	 */
	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia pendentQualificar
	 * @param desqualificat La classe a injectar.
	 */
	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}

	/**
	 * InjecciÃ³ de la dependÃ¨ncia pendentQualificar
	 * @param desqualificat La classe a injectar.
	 */
	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}

	/**
     * InjecciÃ³ de la dependÃ¨ncia seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }


	/**
	 * @param trazaDiposits the trazaDiposits to set
	 */
	public void setTrazaDiposits(String trazaDiposits) {
		this.trazaDiposits = trazaDiposits;
	}

	/**
	 * @param trazaPartidas the trazaPartidas to set
	 */
	public void setTrazaPartidas(String trazaPartidas) {
		this.trazaPartidas = trazaPartidas;
	}

	/**
	 * @param trazaLots the trazaLots to set
	 */
	public void setTrazaLots(String trazaLots) {
		this.trazaLots = trazaLots;
	}
	
	/**
	 * @param trazaBotes the trazaBotes to set
	 */
	public void setTrazaBotes(String trazaBotes) {
		this.trazaBotes = trazaBotes;
	}


	public void setTrazaEnvasos(String trazaEnvasos) {
		this.trazaEnvasos = trazaEnvasos;
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
	 * Injeccio de dependencia 
	 * @param usuariSessionKey
	 */
	public void setUsuariSessionKey(String usuariSessionKey) {
		this.usuariSessionKey = usuariSessionKey;
	}

	
	
}
