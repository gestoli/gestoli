package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.model.Zona;
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
 * @author Carlos Pérez (cperez@at4.net)
 */
public class TancamentLlibresFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(TancamentLlibresFormController.class);

	private String establimentSessionKey;
	private String formView;
	private String tancamentLlibres;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;

	private HibernateTemplate hibernateTemplate;

	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		TancamentLlibresCommand tancament = (TancamentLlibresCommand)command;
		Map myModel = new HashMap();
		
		try{
			
		Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
		
		// Etiquetatges
		Long[] etis = tancament.getEtiquetatgeId();
		Etiquetatge[] etiquetatge = new Etiquetatge[etis.length];
		
		int i = 0;
		for (Long eti : etis){
			if (eti != null){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				etiquetatge[i] = oliInfraestructuraEjb.etiquetatgeAmbId(eti);
			}
			i++;
		}
		
		//Zones
		Long[] zs = tancament.getZonaId();
		Zona[] zona = new Zona[zs.length];
		
		i = 0;
		for (Long z : zs){
			if (z != null){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				zona[i] = oliInfraestructuraEjb.zonaAmbId(z);
			}
			i++;
		}
		
		//Varietats
		Integer[][] vars = tancament.getVarietatsId();
		VarietatOliva[][] varietat = new VarietatOliva[vars.length][];
		
		i = 0;
		for (Integer[] vs : vars) {
			int j = 0;
			if (vs != null){
				varietat[i] = new VarietatOliva[vs.length]; 
				for (Integer v : vs){
					if (v != null) {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						varietat[i][j] = oliInfraestructuraEjb.variedadAmbId(v);
					}
					j++;
				}
			}
			i++;
		}
		
//		// Etiquetes.
//		Integer totalEtiquetatges = tancament.getEtiquetatgeId().length;
//		EtiquetesLot[] etiquetesLots = new EtiquetesLot[totalEtiquetatges];
//		for (int n = 0; n < totalEtiquetatges; n++) {
//			EtiquetesLot etiquetesLot = new EtiquetesLot();
//			etiquetesLot.setEtiquetaLletra(tancament.getEtiquetaLletra()[n]);
//			etiquetesLot.setEtiquetaInici(tancament.getEtiquetaInici()[n]);
//			etiquetesLot.setEtiquetaFi(tancament.getEtiquetaFi()[n]);
//			
//			etiquetesLots[n] = etiquetesLot;
//		}
			
		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
		oliProcessosEjb.generarTancamentLlibres(
				establiment,
				tancament.getData(),
				tancament.getDipositId(),
				tancament.getAcidesa(), 
				tancament.getIdPartidaOli(),
				tancament.getLitros(),
				tancament.getObservacions(),
				tancament.getLotId(),
				tancament.getMarcaId(),
				etiquetatge,
				tancament.getNumeroLot(),
				varietat,
				tancament.getIdPartidaOliLots(),
				zona,
				tancament.getAcidesaLots(),
				tancament.getNumeroBotellesInicials(),
				tancament.getNumeroBotellesActuals(),
				tancament.getEtiquetar(),
				tancament.getObservacionsLots(),
//				etiquetesLots,
				tancament.getDataConsum(),
				tancamentLlibres);
				
				
				ControllerUtils.saveMessageInfo(request, missatge("tancamentLlibres.diposit.missatge.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant l'introducció d'existències dipòsit", e);
	             ControllerUtils.saveMessageError(request, missatge("tancamentLlibres.diposit.missatge.no"));
			}
		
//        return new ModelAndView(getSuccessView(), myModel);
	    	String desti = "EstablimentPrincipal.html";
			
			return new ModelAndView("redirect:" + desti);
	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		Map myModel = new HashMap();
		TancamentLlibresCommand tancament = (TancamentLlibresCommand)command;
		
		try{
			Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
			myModel.put("establiment", establiment);
			
			Collection<Diposit> diposits = new ArrayList();
			Collection<Lot> lots = new ArrayList();
			
			for (Long id : tancament.getDipositId()){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				diposits.add(oliInfraestructuraEjb.dipositAmbId(id));
			}
			for (Long id : tancament.getLotId()){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				lots.add(oliInfraestructuraEjb.lotAmbId(id));
			}

			// Partides d'oli
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection partidesOli = null;
	        if (establiment != null){
	        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstabliment(establiment.getId());
	        }	    	
	        myModel.put("partidesOli", partidesOli);
	        
	        // Marques
	        List<BasicData> marcas = new ArrayList<BasicData>();
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection<Marca> itMarcas = oliInfraestructuraEjb.getMarcasEstabliment(establiment.getId());
	        ArrayList<Marca> marcasEstablecimiento = new ArrayList<Marca>();
	        for (Marca marca : itMarcas) {
	        	if (marca.getActiu().booleanValue()) {
	        		if (!marcasEstablecimiento.contains(marca)) marcasEstablecimiento.add(marca);
	        	}
	        }
	        for (Marca marca : marcasEstablecimiento) {
	        	BasicData basic = new BasicData();
	        	basic.setId(String.valueOf(marca.getId()));
	        	basic.setNom(marca.getNom());
	        	marcas.add(basic);
	        }
	        myModel.put("marcas", marcas);
	        
	        // Etiquetatges
	        List capacitats = new ArrayList();
            BasicData[][] etiquetatges = new BasicData[lots.size()][];
	        List<BasicData> etis = new ArrayList<BasicData>();
	        int i = 0;
	        for (Long marca : tancament.getMarcaId()){
		        if (marca != null) {
		        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		        	List<Etiquetatge> itEtiquetajes = oliInfraestructuraEjb.etiquetatgeByMarca(marca);
		        	for (Etiquetatge etiquetaje : itEtiquetajes) {
		        		BasicData basic = new BasicData();
		        		basic.setId(String.valueOf(etiquetaje.getId()));
		        		basic.setNom(etiquetaje.getTipusEnvas().getVolum() + " - " + etiquetaje.getTipusEnvas().getColor().getNom() + " - " + etiquetaje.getTipusEnvas().getMaterialTipusEnvas().getNom());
		        		etis.add(basic);
		        		
		        		if (!capacitats.contains(etiquetaje)) {
	        				HashMap<String, Object> capacitat = new HashMap<String, Object>();
	        				capacitat.put("value", ""+etiquetaje.getTipusEnvas().getVolum());
	        				capacitat.put("label", ""+etiquetaje.getTipusEnvas().getVolum());
	        				capacitats.add(capacitat);
	            		}
		        	}
		        }
		        etiquetatges[i] = new BasicData[etis.size()];
		        etiquetatges[i] = etis.toArray(etiquetatges[i]);
		        i++;
	        }
	        tancament.setEtiquetatges(etiquetatges);
	        
            myModel.put("capacitats", capacitats);
	        
	        // Zones
	        List<BasicData> zonas = new ArrayList<BasicData>();
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection<Zona> itZonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(establiment.getId());
	        for(Zona zona : itZonas) {
	        	BasicData basic = new BasicData();
	        	basic.setId(String.valueOf(zona.getId()));
	        	basic.setNom(zona.getNom());
	        	zonas.add(basic);
	        }
	        myModel.put("zonas", zonas);
	        
	     // Varietats d'oliva
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection<VarietatOliva> varietatsOliva = oliInfraestructuraEjb.varietatsOliva();
	        Collection vars = new ArrayList();
	        for (VarietatOliva var : varietatsOliva){
	        	if (var.getExperimental() != null && var.getExperimental().booleanValue() && var.getNomVarietat() != null){
	        		vars.add(new BasicData(var.getId().toString(), var.getNomVarietat()));
	        	} else {
	        		vars.add(new BasicData(var.getId().toString(), var.getNom()));
	        	}
	        }
	        myModel.put("varietatsArray", vars);
	        
	        myModel.put("qualificat", new Integer(Constants.CATEGORIA_DO));
        	myModel.put("path", "tancament_llibres");
        	myModel.put("diposits", diposits);
        	myModel.put("lots", lots);
        	myModel.put("formData", command);
        	
        } catch (Exception ex) {
            logger.error("Error obtenint llistats de tancament de llibres", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no"));
        }
        return myModel;
	}

	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		TancamentLlibresCommand tancament = null;
		tancament = new TancamentLlibresCommand();
		if (!isFormSubmission(request)) {
			try{
				Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
				
				boolean bTotsDiposits = false;
				boolean bTotsLots = false;
				
				Collection<Diposit> diposits = new ArrayList();
				Collection<Lot> lots = new ArrayList();
				
				if (request.getParameter("totsDiposits")!=null){
					if(request.getParameter("totsDiposits").equals("S") || request.getParameter("totsDiposits").equals("true")){
						bTotsDiposits=true;
					}
				}
				if (request.getParameter("totsLots")!=null){
					if(request.getParameter("totsLots").equals("S") || request.getParameter("totsLots").equals("true")){
						bTotsLots=true;
					}
				}
				
				// DIPOSITS
				if (bTotsDiposits == true){
					logger.info("Demandada acció: Tancament de llibres de tots el dipòsits.");
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					diposits = oliInfraestructuraEjb.dipositCercaTotsNoFicticisPerEstabliment(establiment.getId());
				} else {
					if (request.getParameter("diposits")!=null){
						String[] dipositsId = request.getParameterValues("diposits");
						for (String id : dipositsId){
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							diposits.add(oliInfraestructuraEjb.dipositAmbId(new Long(Integer.parseInt(id))));
						}
					}
				}
				
				//Omplim les dades que es mostraran al formulari
				Long[] dPartides = new Long[diposits.size()];
				String[] nomCategoria = new String[diposits.size()];
				Double[] litros = new Double[diposits.size()];
				Double[] kilos = new Double[diposits.size()];
				Double[] acidesa = new Double[diposits.size()];
				Long[] dipositId = new Long[diposits.size()];
				String[] observacions = new String[diposits.size()];
				
				int i = 0;
				for(Diposit dip : diposits){
					if (dip.getPartidaOli() != null) {
						dPartides[i] = dip.getPartidaOli().getId();
						nomCategoria[i] = missatge("consulta.general.camp.categoriaOli." + dip.getPartidaOli().getCategoriaOli().getId());
					} else {
						dPartides[i] = 0L;
						nomCategoria[i] = "";
					}
					litros[i] = dip.getVolumActual() != null ? dip.getVolumActual() : 0.0;
					kilos[i] = litros[i] * 0.916;
					acidesa[i] = dip.getAcidesa();
					dipositId[i] = dip.getId();
					i++;
				}
				tancament.setIdPartidaOli(dPartides);
				tancament.setNomCategoriaOli(nomCategoria);
				tancament.setLitros(litros);
				tancament.setKilos(kilos);
				tancament.setAcidesa(acidesa);
				tancament.setDipositId(dipositId);
				tancament.setObservacions(observacions);
//				tancament.setEstabliment(establiment);
				
				logger.info("Obtenint llistat de diposits: " + diposits.size() + " registres trobats");
				
				// LOTS
				if (bTotsLots == true){
					logger.info("Demandada acció: Tancament de llibres de tots el lots.");
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//					lots = oliInfraestructuraEjb.lotCercaTotsPerEstabliment(establiment.getId());
					lots = oliInfraestructuraEjb.lotCercaTotsBuits(establiment.getId());
				} else {
					if (request.getParameter("lots")!=null){
						String[] lotsId = request.getParameterValues("lots");
						for (String id : lotsId){
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							lots.add(oliInfraestructuraEjb.lotAmbId(new Long(Integer.parseInt(id))));
						}
					}
				}
				
				//Omplim les dades que es mostraran al formulari
				Long[] lPartides = new Long[lots.size()];
				Integer[] idCategoriaOli = new Integer[lots.size()];
				String[] nomCategoriaLot = new String[lots.size()];
				Double[] acidesaLot = new Double[lots.size()];
				Long[] lotId = new Long[lots.size()];
				String[] observacionsLot = new String[lots.size()];
				Long[] marcaId = new Long[lots.size()];
				Long[] etiquetatgeId = new Long[lots.size()];
				BasicData[][] etiquetatges = new BasicData[lots.size()][];
				String[] numeroLot = new String[lots.size()];
				Integer[][] varietats = new Integer[lots.size()][];
				Long[] zonaId = new Long[lots.size()];
				Integer[] numeroBotellesInicials = new Integer[lots.size()];
				Integer[] numeroBotellesActuals = new Integer[lots.size()];
				Double[] litrosInicialsLot = new Double[lots.size()];
				Double[] kilosInicialsLot = new Double[lots.size()];
				Double[] litrosActualsLot = new Double[lots.size()];
				Double[] kilosActualsLot = new Double[lots.size()];
				Double[] volum = new Double[lots.size()];
				Integer[] numeroEtiquetaInicial = new Integer[lots.size()];
				Integer[] numeroEtiquetaFinal = new Integer[lots.size()];
				Boolean[] etiquetar = new Boolean[lots.size()];
				String[] etiquetaLletra = new String[lots.size()];
				String[] etiquetaInici = new String[lots.size()];
				Double[] quantitat = new Double[lots.size()];
				String[] etiquetaFi = new String[lots.size()];
				String[] etiquetaCapacitat = new String[lots.size()];
				Double[] litres = new Double[lots.size()];
				Date[] dataConsum = new Date[lots.size()];
				
				i = 0;
				for(Lot lot : lots){
					if (lot.getPartidaOli() != null) {
						lPartides[i] = lot.getPartidaOli().getId();
						nomCategoriaLot[i] = missatge("consulta.general.camp.categoriaOli." + lot.getPartidaOli().getCategoriaOli().getId());
					} else {
						lPartides[i] = 0L;
						nomCategoriaLot[i] = "";
					}
					acidesaLot[i] = lot.getAcidesa() != null ? lot.getAcidesa() : 0.0;
					lotId[i] = lot.getId();
					etiquetatgeId[i] = lot.getEtiquetatge().getId();
					volum[i] = lot.getEtiquetatge().getTipusEnvas().getVolum();
					marcaId[i] = lot.getEtiquetatge().getMarca().getId();
					numeroLot[i] = lot.getNumeroLot();
					litrosInicialsLot[i] = lot.getNumeroBotellesInicials() * volum[i];
					kilosInicialsLot[i] = litrosInicialsLot[i] * 0.916;
					litrosActualsLot[i] = lot.getNumeroBotellesActuals() * volum[i];
					kilosActualsLot[i] = litrosInicialsLot[i] * 0.916;
					idCategoriaOli[i] = lot.getPartidaOli() != null? lot.getPartidaOli().getCategoriaOli().getId() : 0;
					etiquetaLletra[i] = "";
					etiquetaInici[i] = "";
					quantitat[i] = null;
					etiquetaFi[i] = "";
					etiquetaCapacitat[i] = "";
					litres[i] = null;
					dataConsum[i] = lot.getDataConsum();
					
					//Vareietats
					List<Integer> varietatsOlives = new ArrayList();
					if (lot.getVarietatOlivas() != null || lot.getVarietatOlivas().size() > 0){
						for (Object var : lot.getVarietatOlivas()){
							varietatsOlives.add(((VarietatOliva)var).getId());
						}
					}

					varietats[i] = new Integer[varietatsOlives.size()]; 
					varietats[i] = varietatsOlives.toArray(varietats[i]);
					
					zonaId[i] = lot.getZona().getId();
					numeroBotellesInicials[i] = lot.getNumeroBotellesInicials(); 
					numeroBotellesActuals[i] = lot.getNumeroBotellesActuals();
					
			        // Etiquetatges
			        List<BasicData> etis = new ArrayList<BasicData>();
			        if (marcaId[i] != null) {
			        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			        	List<Etiquetatge> itEtiquetajes = oliInfraestructuraEjb.etiquetatgeByMarca(marcaId[i]);
			        	for (Etiquetatge etiquetaje : itEtiquetajes) {
			        		BasicData basic = new BasicData();
			        		basic.setId(String.valueOf(etiquetaje.getId()));
			        		basic.setNom(etiquetaje.getTipusEnvas().getVolum() + " - " + etiquetaje.getTipusEnvas().getColor().getNom() + " - " + etiquetaje.getTipusEnvas().getMaterialTipusEnvas().getNom());
			        		etis.add(basic);
			        	}
			        }
			        etiquetatges[i] = new BasicData[etis.size()];
			        etiquetatges[i] = etis.toArray(etiquetatges[i]);
					i++;
				}
				tancament.setIdPartidaOliLots(lPartides);
				tancament.setIdCategoriaOli(idCategoriaOli);
				tancament.setNomCategoriaOliLots(nomCategoriaLot);
				tancament.setAcidesaLots(acidesaLot);
				tancament.setLotId(lotId);
				tancament.setObservacionsLots(observacionsLot);
				tancament.setMarcaId(marcaId);
				tancament.setEtiquetatgeId(etiquetatgeId);
				tancament.setEtiquetatges(etiquetatges);
				tancament.setNumeroLot(numeroLot);
				tancament.setVarietatsId(varietats);
				tancament.setZonaId(zonaId);
				tancament.setNumeroBotellesInicials(numeroBotellesInicials);
				tancament.setNumeroBotellesActuals(numeroBotellesActuals);
				tancament.setVolum(volum);
				tancament.setLitrosInicialsLots(litrosInicialsLot);
				tancament.setLitrosActualsLots(litrosActualsLot);
				tancament.setKilosInicialsLots(kilosInicialsLot);
				tancament.setKilosActualsLots(kilosActualsLot);
				tancament.setEtiquetar(etiquetar);
				tancament.setEtiquetaLletra(etiquetaLletra);
				tancament.setEtiquetaInici(etiquetaInici);
				tancament.setQuantitat(quantitat);
				tancament.setEtiquetaFi(etiquetaFi);
				tancament.setEtiquetaCapacitat(etiquetaCapacitat);
				tancament.setLitres(litres);
				tancament.setDataConsum(dataConsum);
				
				logger.info("Obtenint llistat de lots: " + lots.size() + " registres trobats");
			} catch (Exception ex) {
	            logger.error("Error obtenint llistats de tancament de llibres", ex);
	            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no"));
	        }
		} else {
			Object[] params = request.getParameterMap().entrySet().toArray();
			int diposits = 0;
			int lots = 0;
			for (Object param : params){
				if(((String)(((Entry)param).getKey())).substring(0, 3).equals("lot")){
					lots++;
				} else if(((String)(((Entry)param).getKey())).substring(0, 4).equals("dipo")){
					diposits++;
				}
			}
			
//			tancament.setEstabliment(establiment);
			int nvar = 0;
			try {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			    nvar =  oliInfraestructuraEjb.varietatsOliva().size();
			} catch (Exception ex){
				logger.error("Error obtenint llistats de varietats", ex);
	            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no"));
			}
			tancament.setDipositId(new Long[diposits]);
			tancament.setAcidesa(new Double[diposits]);
			tancament.setIdPartidaOli(new Long[diposits]);
			tancament.setIdCategoriaOli(new Integer[diposits]);
			tancament.setNomCategoriaOli(new String[diposits]);
			tancament.setKilos(new Double[diposits]);
			tancament.setLitros(new Double[diposits]);
			tancament.setObservacions(new String[diposits]);
			tancament.setLotId(new Long[lots]);
			tancament.setMarcaId(new Long[lots]);
			tancament.setEtiquetatgeId(new Long[lots]);
			tancament.setNumeroLot(new String[lots]);
			tancament.setVarietatsId(new Integer[lots][nvar]);
			tancament.setIdPartidaOliLots(new Long[lots]);
			tancament.setZonaId(new Long[lots]);
			tancament.setAcidesaLots(new Double[lots]);
			tancament.setNumeroBotellesInicials(new Integer[lots]);
			tancament.setNumeroBotellesActuals(new Integer[lots]);
			tancament.setEtiquetar(new Boolean[lots]);
			tancament.setObservacionsLots(new String[lots]);
			tancament.setNomCategoriaOliLots(new String[lots]);
//			tancament.setEtiquetatges();
			tancament.setVolum(new Double[lots]);
			tancament.setLitrosInicialsLots(new Double[lots]);
			tancament.setLitrosActualsLots(new Double[lots]);
			tancament.setKilosInicialsLots(new Double[lots]);
			tancament.setKilosActualsLots(new Double[lots]);
			tancament.setEtiquetaLletra(new String[lots]);
			tancament.setEtiquetaInici(new String[lots]);
			tancament.setQuantitat(new Double[lots]);
			tancament.setEtiquetaFi(new String[lots]);
			tancament.setEtiquetaCapacitat(new String[lots]);
			tancament.setLitres(new Double[lots]);
			tancament.setDataConsum(new Date[lots]);
		}
		return tancament;
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
		binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Double.class,
				new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(
				Integer.class,
				new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true)); 
		binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		binder.registerCustomEditor(ArrayList.class, new CustomCollectionEditor(ArrayList.class));

	}

	private Double[] omplirArrayDouble(HttpServletRequest request, String propietat) {
		String[] valors = request.getParameterValues(propietat);
		if (valors != null) {
			Double[] array = new Double[valors.length];
			return array;
		}
		return null;
	}
	private Float[] omplirArrayFloat(HttpServletRequest request, String propietat) {
		String[] valors = request.getParameterValues(propietat);
		if (valors != null) {
			Float[] array = new Float[valors.length];
//			for (int i = 0; i < array.length; i++) {
//				try {
//					array[i] = new Float(valors[i]);
//				} catch (NumberFormatException nfex) {
//					array[i] = null;
//				}
//			}
			return array;
		}
		return null;
	}
	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
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

	/**
	 * @param tancamentLlibres the tancamentLlibres to set
	 */
	public void setTancamentLlibres(String tancamentLlibres) {
		this.tancamentLlibres = tancamentLlibres;
	}

	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
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