package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.VarietatOliva;
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
 * 
 */

public class PlantacioFormController extends AbstractWizardFormController {
	private static Logger logger = Logger.getLogger(PlantacioFormController.class);
	private String rolDoGestor;
	private String rolDoControlador;
	private String rolOlivicultor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String esDoGestorRequestKey;
	private PlantacioCommand plantacioOriginal;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */

	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

		
		
		PlantacioCommand plantacio = (PlantacioCommand)command;

		//Para la plantacion
		
		Boolean activa = Boolean.valueOf(true);
		String activaParam = request.getParameter("actiu");
		if (activaParam == null) {
			activa = Boolean.valueOf(false);
		}
		if (isCreate(request)) {
			activa = Boolean.valueOf(true);
		}

		DescomposicioPlantacio[] descompo = plantacio.getVariedades();
		Set descomp_plantaciones = new HashSet();
		DescomposicioPlantacio dpTaula = new DescomposicioPlantacio();
		for (int i=0; i<descompo.length;i++){
			
			if (descompo[i]!= null && ((descompo[i].getNumeroOliveres()!= null && descompo[i].getNumeroOliveres().intValue()!= 0) || 
					(descompo[i].getMaxProduccio()!= null && descompo[i].getMaxProduccio().intValue() != 0) ||
					(descompo[i].getSuperficie()!= null && descompo[i].getSuperficie().intValue()!= 0))){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				VarietatOliva vo = oliInfraestructuraEjb.variedadAmbId(descompo[i].getVarietatOliva().getId());
				descompo[i].setVarietatOliva(vo);
				//descompo[i].setObservacions(plantacio.getObservacionsVariedad());
				descomp_plantaciones.add(descompo[i]);
				Finca f = oliInfraestructuraEjb.fincaAmbId(plantacio.getFinca().getId());
				DescomposicioPlantacio dpAntiga = oliInfraestructuraEjb.descomposicioPlantacioByPlantacioIdVariedadId(plantacio.getId(), descompo[i].getVarietatOliva().getId());
				if (f.getOlivicultor().getCodiDOPOliva()!=null && descompo[i].getVarietatOliva().getId() == Constants.VARIETAT_OLIVA_MALLORQUINA) {
					VarietatOliva vot = oliInfraestructuraEjb.variedadAmbId(Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
					String trTaula = oliInfraestructuraEjb.obtenirTipusRendimentCampanyaVarietat(oliInfraestructuraEjb.campanyaCercaActual(), vot.getId());
					Double rvTaula = oliInfraestructuraEjb.obtenirRendimentCampanyaVarietat(oliInfraestructuraEjb.campanyaCercaActual(), vot.getId());
					Double rvMall = oliInfraestructuraEjb.obtenirRendimentCampanyaVarietat(oliInfraestructuraEjb.campanyaCercaActual(), vo.getId());
					dpTaula.setVarietatOliva(vot);
					dpTaula.setNumeroOliveres(descompo[i].getNumeroOliveres());
					dpTaula.setSuperficie(descompo[i].getSuperficie());
					dpTaula.setPlantacio(descompo[i].getPlantacio());
					dpTaula.setIdOriginal(descompo[i].getIdOriginal());
					dpTaula.setObservacions(descompo[i].getObservacions());
					if (trTaula.equals("hectarea")) 
						dpTaula.setMaxProduccio(rvTaula * dpTaula.getSuperficie());
					else dpTaula.setMaxProduccio(rvTaula * dpTaula.getNumeroOliveres());
					if (dpAntiga!=null) {
						Double usada = dpAntiga.getMaxProduccio()-dpAntiga.getProduccioRestant();
						dpTaula.setProduccioRestant(dpTaula.getMaxProduccio()-(usada/rvMall*rvTaula));
					} else dpTaula.setProduccioRestant(dpTaula.getMaxProduccio());
					descomp_plantaciones.add(dpTaula);
				}
			}
		}
		for (int i=0; i<descompo.length;i++){
			if (descompo[i].getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA) {
				if (dpTaula.getVarietatOliva()!=null) descompo[i]=dpTaula;
			}
		}

		Integer anyPlantacio = plantacio.getAnyPlantacio();
		Finca finca = plantacio.getFinca();
		Long id = plantacio.getId();
		Long idOriginal = plantacio.getIdOriginal();
		Long municipi = plantacio.getMunicipiId();
		String observaciones = plantacio.getObservacions();
		String parcela = plantacio.getParcela();
		String poligon = plantacio.getPoligon();


		Boolean regadiu = Boolean.valueOf(true);
		String regadiuParam = request.getParameter("regadiu");
		if (regadiuParam == null) {
			regadiu = Boolean.valueOf(false);
		}

		// en caso de que se trate de modificación, creamos ya el objecto Plantacio y las descomposiciones
		Plantacio plantacion = null;
		if ((plantacio.getPlantacioBaixaId() == null) || (plantacio.getPlantacioBaixaId().equals(""))) {
	 		plantacion = new Plantacio();
			plantacion.setActiu(activa);
			plantacion.setAnyPlantacio(anyPlantacio);
			plantacion.setDescomposicioPlantacios(descomp_plantaciones);
			plantacion.setFinca(finca);
			plantacion.setId(id);
			plantacion.setIdOriginal(idOriginal);
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			plantacion.setMunicipi(oliInfraestructuraEjb.municipiAmbId(municipi));
			plantacion.setObservacions(observaciones);
			plantacion.setParcela(parcela);
			plantacion.setPoligon(poligon);
			plantacion.setRegadiu(regadiu);
			
			String[] coords = oliInfraestructuraEjb.consultaCoordenades(Constants.ILLES_BALEARS, municipi, poligon, parcela);
			if (coords != null){
				plantacion.setCoordX(coords[0]);
				plantacion.setCoordY(coords[1]);
				plantacion.setCatastre(coords[2]);
			}
	
//			// Codi per a fer una actualització de les coordenades de totes les plantacions...
//			// --------------------------------------------------------------
//			Collection plantacions = oliInfraestructuraEjb.findPlantacions();
//			int i = 1;
//			for (Iterator it = plantacions.iterator(); it.hasNext();){
//				Plantacio plant = (Plantacio)it.next();
//				logger.info(i++ + ". Plantació: " + plant.getId() + " - " + plant.getMunicipi().getNom() + ",(" + plant.getPoligon() + ", " + plant.getParcela() + ")");
//				coords = oliInfraestructuraEjb.consultaCoordenades(Constants.ILLES_BALEARS, plant.getMunicipi().getId(), plant.getPoligon(), plant.getParcela());
//				if (coords != null){
//					plant.setCoordX(coords[0]);
//					logger.info("   Coordenada X: " + plant.getCoordX());
//					plant.setCoordY(coords[1]);
//					logger.info("   Coordenada Y: " + plant.getCoordY());
//					plant.setCatastre(coords[2]);
//					logger.info("   Catastre: " + plant.getCatastre());
//					oliInfraestructuraEjb.plantacioActualitzar(plant);
//				} else {
//					plant.setCoordX(null);
//					plant.setCoordY(null);
//					plant.setCatastre(null);
//					logger.error("No s'han pogut obtenir les coordenades de la plantació: " + plant.getId() + " - " + plant.getMunicipi().getNom() + ",(" + plant.getPoligon() + ", " + plant.getParcela() + ")");
//				}
//			}
//			// --------------------------------------------------------------
			
			if (isCreate(request)) {
				//Asignamos la finca
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Finca finque = oliInfraestructuraEjb.fincaAmbId(Long.valueOf(plantacio.getIdFinca()));
				plantacion.setFinca(finque);
	
			}
		} else {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			plantacion = oliInfraestructuraEjb.plantacioAmbId(plantacio.getPlantacioBaixaId());
		}

		Map myModel = new HashMap();
		if (isCreate(request)) {

			try {
				if (plantacio.getPlantacioBaixaId() != null) {
					Long plantacioBaixaId = plantacio.getPlantacioBaixaId();
					Long idFinca = Long.valueOf(request.getParameter("idFinca"));
					try {
						id = plantacioBaixaId;
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Plantacio plantacioBaixa = oliInfraestructuraEjb.plantacioAmbId(plantacioBaixaId);
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Finca fincaPare = oliInfraestructuraEjb.fincaAmbId(idFinca);
					
						// Donam d'alta la plantació i l'assignam a la finca seleccionada.
						plantacioBaixa.setFinca(fincaPare);
						plantacioBaixa.setDeBaixa(false);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.plantacioActualitzar(plantacioBaixa);
						
						// Afegim la plantació a la finca.
						Set plantacions = fincaPare.getPlantacios();
						plantacions.add(plantacioBaixa);
						fincaPare.setPlantacios(plantacions);
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.finquesActualitzar(fincaPare);
						ControllerUtils.saveMessageInfo(request, missatge("plantacions.missatge.assignar.ok"));
					} catch (Exception ex) {
						logger.error("Error associant la finca", ex);
						ControllerUtils.saveMessageError(request, missatge("plantacions.missatge.assignar.no"));
					}
				} else {
					logger.info("Creant el registre: " + plantacio);
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					oliInfraestructuraEjb.plantacioModificar(plantacion, descomp_plantaciones);
					ControllerUtils.saveMessageInfo(request, missatge("plantacio.missatge.crear.ok"));
				}
			} catch (Exception ex) {
				logger.error("Error creant la finca", ex);
				ControllerUtils.saveMessageError(request, missatge("plantacio.missatge.crear.no"));
			}

		} else {

			try {
				logger.info("Modificant el registre: " + plantacio);
				Collection descomposiciones = plantacion.getDescomposicioPlantacios();
				List descomposicionModificar = new ArrayList();
				//COMPROBAMOS QUE LAS DESCOMPOSICIONES ASOCIADAS A LA PLANTACION NO TENGAN PARTIDAS ASOCIADAS
				for (int i = 0; i< descompo.length; i++) {
				//for (Iterator it = descomposiciones.iterator(); it.hasNext(); ){
					//DescomposicioPlantacio dp = (DescomposicioPlantacio) it.next();
					DescomposicioPlantacio dp = (DescomposicioPlantacio) descompo[i];
					DescomposicioPlantacio descomposicioAntiga = oliInfraestructuraEjb.descomposicioPlantacioByPlantacioIdVariedadId(dp.getPlantacio().getId(), dp.getVarietatOliva().getId());
					if (descomposicioAntiga != null) { // Ya existia la descomposicio
						Collection partides = oliInfraestructuraEjb.descomposicioPartidaOlivaByDescomposicioPlantacio(descomposicioAntiga.getId());
						
						if (partides != null && partides.size() > 0) { // Si tiene partidas modificamos
							// Comprovam la producció ja utilitzada
							if (dp.getProduccioRestant()!=null) descomposicioAntiga.setProduccioRestant(dp.getProduccioRestant());
							else {
								Double produccioUsada = (descomposicioAntiga.getProduccioRestant() != null? descomposicioAntiga.getMaxProduccio() - descomposicioAntiga.getProduccioRestant() : 0.0);
								descomposicioAntiga.setMaxProduccio(dp.getMaxProduccio());
								descomposicioAntiga.setProduccioRestant(produccioUsada >= dp.getMaxProduccio()? 0.0 : dp.getMaxProduccio() - produccioUsada);
							}
							descomposicioAntiga.setNumeroOliveres(dp.getNumeroOliveres());
							descomposicioAntiga.setSuperficie(dp.getSuperficie());
							descomposicionModificar.add(descomposicioAntiga);
						} else { // Si no tiene partidas comprobamos si hay que borrar
							if (dp != null && (dp.getNumeroOliveres() != null && dp.getSuperficie() != null && dp.getMaxProduccio() != null && 
									!dp.getNumeroOliveres().equals(new Integer(0)) && !dp.getSuperficie().equals(new Double(0)) && 
									!dp.getMaxProduccio().equals(new Double(0)))) { // Comprobamos si no estan en blanco -> Modificamos
								if (dp.getProduccioRestant()!=null) descomposicioAntiga.setProduccioRestant(dp.getProduccioRestant());
								else {
									Double produccioUsada = (descomposicioAntiga.getProduccioRestant() != null? descomposicioAntiga.getMaxProduccio() - descomposicioAntiga.getProduccioRestant() : 0.0);
									descomposicioAntiga.setMaxProduccio(dp.getMaxProduccio());
									descomposicioAntiga.setProduccioRestant(produccioUsada >= dp.getMaxProduccio()? 0.0 : dp.getMaxProduccio() - produccioUsada);
								}
								descomposicioAntiga.setNumeroOliveres(dp.getNumeroOliveres());
								descomposicioAntiga.setSuperficie(dp.getSuperficie());
								descomposicionModificar.add(descomposicioAntiga);
							} else { // Si estan en blanco -> Borramos
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								oliInfraestructuraEjb.borrarDescomposicio(descomposicioAntiga);
							}
						}
					} else { // Si no existia, es comprova que esta emplenada i s'inserta nova.
						if (dp != null && (dp.getNumeroOliveres() != null && dp.getSuperficie() != null && dp.getMaxProduccio() != null && 
								!dp.getNumeroOliveres().equals(new Integer(0)) && !dp.getSuperficie().equals(new Double(0)) && 
								!dp.getMaxProduccio().equals(new Double(0)))) {
							dp.setProduccioRestant(dp.getMaxProduccio());
							descomposicionModificar.add(dp);
						}
					}
				}
				//MODIFICAMOS LA PLANTACION
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.plantacioModificar(plantacion, descomposicionModificar);//descomp_plantaciones);
				ControllerUtils.saveMessageInfo(request, missatge("plantacio.missatge.modificar.ok"));
			} catch (Exception ex) {
				logger.error("Error modificant la plantacio ", ex);
				ControllerUtils.saveMessageError(request, missatge("plantacio.missatge.modificar.no"));
			}

		}

		Long fincaId = plantacio.getFinca().getId();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Long olivi = oliInfraestructuraEjb.findOlivicultorDeFinca(fincaId);
		
		String modelAndView = "redirect:PlantacioForm.html?id="+ plantacion.getId() +"&idOlivicultor=" + olivi;
		if(request.getParameter("fromFinca")!= null){
			modelAndView = "redirect:PlantacioForm.html?id="+ plantacion.getId() +"&idOlivicultor=" + olivi+"&fromFinca=" + request.getParameter("fromFinca");
		}
		
		return new ModelAndView(modelAndView, myModel);
		
	}


	protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws Exception {
		PlantacioCommand plantacio = (PlantacioCommand)command;
		plantacioOriginal = plantacio;
		Long fincaId = plantacio.getFinca().getId();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Long olivi = oliInfraestructuraEjb.findOlivicultorDeFinca(fincaId);		
		String modelAndView = "redirect:PlantacioForm.html?_target0&idOlivicultor=" + olivi;
		if(plantacio.getFromFinca()!= null){
			modelAndView = "redirect:PlantacioForm.html?_target0&idOlivicultor=" + olivi+"&fromFinca=" + fincaId;
		}
		return new ModelAndView(modelAndView);
	}
	



	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
		
		Map myModel = new HashMap();
		PlantacioCommand plantacio = (PlantacioCommand) command;

		//Vemos primero si se trata del gestor o un olivicultor
		if (request.getAttribute(esDoGestorRequestKey)!=null || request.isUserInRole(rolOlivicultor) || request.isUserInRole(rolDoControlador)){

			String idOlivicultor = request.getParameter("idOlivicultor");
			if (idOlivicultor != null && !idOlivicultor.equals("")){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection fincas = oliInfraestructuraEjb.fincasOlivicultorCercaTots(Long.valueOf(idOlivicultor));
				myModel.put("todasFincas", fincas);
				myModel.put("idOlivicultor", Long.valueOf(idOlivicultor));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				myModel.put("idFinca", request.getParameter("idFinca"));
			}
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection descomposiciones = oliInfraestructuraEjb.descomposicioVarietatsNoExperimentals();
			myModel.put("descomposiciones", descomposiciones);
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection experimentals = oliInfraestructuraEjb.varietatOlivaExperimental();
			myModel.put("experimentals", experimentals);
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection olivaTaula = oliInfraestructuraEjb.varietatOlivaTaula();
			myModel.put("olivaTaula", olivaTaula);
		}
		
		if ((!isCreate(request)) || plantacio.getId()!= null) {
			myModel.put("path", "modificar_plantacio");
		} else if (page == 1 && plantacio.getId()!= null ) {
			myModel.put("path", "modificar_plantacio");
		}else{
			myModel.put("path", "crear_plantacio");
		}

		myModel.put("municipis", (List<Municipi>)oliInfraestructuraEjb.obtenirMunicipis());
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		myModel.put("plantacionsBaixa", oliInfraestructuraEjb.plantacionsDeBaixa());
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		PlantacioCommand plantacio = null;
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			Collection descomposiciones = oliInfraestructuraEjb.descomposicioVariedades();
			Collection descomposiciones = oliInfraestructuraEjb.descomposicioVarietatsNoExperimentals();
			Collection experimentals = oliInfraestructuraEjb.varietatOlivaExperimental();
			Collection olivaTaula = oliInfraestructuraEjb.varietatOlivaTaula();
			//Collection olivaTaula = oliInfraestructuraEjb.varietatOlivaTaula();
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Plantacio p = oliInfraestructuraEjb.plantacioAmbId(codi);
				plantacio = new PlantacioCommand();
//				plantacio.fromPlantacio(p, descomposiciones);
				plantacio.fromPlantacio(p, descomposiciones, experimentals, olivaTaula);

			} else {
				if (plantacioOriginal != null && request.getParameter("crearPlantacio")== null) {
					plantacio = plantacioOriginal;
				} else {
					plantacio = new PlantacioCommand(descomposiciones.size()+experimentals.size()+olivaTaula.size());	
					
				}
				int i = 0;
				for (Iterator it = descomposiciones.iterator(); it.hasNext();){
					(plantacio.getVariedades())[i].getVarietatOliva().setId(((VarietatOliva)it.next()).getId());
					i++;
				}
				for (Iterator it = experimentals.iterator(); it.hasNext();){
					(plantacio.getVariedades())[i].getVarietatOliva().setId(((VarietatOliva)it.next()).getId());
					i++;
				}
				for (Iterator it = olivaTaula.iterator(); it.hasNext();){
					(plantacio.getVariedades())[i].getVarietatOliva().setId(((VarietatOliva)it.next()).getId());
					i++;
				}
			}
			
			String idFinca = request.getParameter("idFinca");
			if ((idFinca != null) && (!idFinca.equals(""))) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Finca finca = oliInfraestructuraEjb.fincaAmbId(Long.valueOf(idFinca));
				plantacio.setFinca(finca);
			}
		} catch (RemoteException ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}

		return plantacio;
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
				Integer.class,
				new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(
				Double.class,
				new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
	}




	protected void validatePage(Object command, Errors errors, int page, boolean finish, HttpServletRequest request) {

		PlantacioValidator mi_validator = (PlantacioValidator) this.getValidator();
		PlantacioCommand pc = (PlantacioCommand) command;
		switch (page) {
		case 0:
			mi_validator.validateFirstPage(pc, errors);
			break;
		case 1:
			mi_validator.validateSecondPage(pc, errors);
			break;

		}
		if (finish){
			mi_validator.validateOnFinish(pc, errors);
			Iterator itErrores = errors.getAllErrors().iterator();
			while (itErrores.hasNext())  {
				FieldError error = (FieldError) itErrores.next();
				if (error.getField().equals("noDescomposicio")) {
					ControllerUtils.saveMessageError(request, missatge("plantacio.missatge.crear.descomposicio.no"));
				}
			}
		}
		setAllowDirtyBack(false);
	}





	protected void onBindAndValidate(HttpServletRequest request,
			Object command,
			BindException errors,
			int page){
		validatePage(command, errors, page, this.isFinishRequest(request), request);
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
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}

	/**
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

	public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}


	/**
	 * Injecció de la dependència rolAdmin
	 * @param rolAdmin La classe a injectar.
	 */
	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
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