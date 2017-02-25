/**
* ProcesEntradaOlivaTaulaFormController.java
*/
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.VarietatOliva;

/**
* <p>Controlador per a l'acció d'executar un proces d'entrada de oliva.</p>
* <p>Aquest controlador distingueix entre les peticions del tipus GET y les de tipus POST.
* Si la petició es de tipus POST s'executa l'acció de creació o d'edició.
* Si la petició es de tipus GET només mostra la pàgina.
*
* @author cperez <cperez@at4.net>
*/
public class ProcesEntradaOlivaTaulaFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(ProcesEntradaOlivaTaulaFormController.class);
	private OliProcessosEjb oliProcessosEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String seleccioSessionKeyOrigen;
	private String tipusOlivaVerda;
	private String tipusOlivaTrencada;
	private String tipusOlivaNegra;
	private String tipusTrazaPartidaOliva;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Es crida quan s'accepten les modificacions a l'objecte.
	 * Només s'executa aquesta funció en el cas de que s'hagi executat la validació sense produïr cap error.
	 * 
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		if ((request.getParameter("olivicultorId") != null) && (!request.getParameter("olivicultorId").equals("app"))) {
			ProcesEntradaOlivaTaulaCommand pEntradaOliv = (ProcesEntradaOlivaTaulaCommand)command;
			Map myModel = new HashMap();
			Long partidaId = null;

			// Obtenim l'establiment.
			Establiment establiment = new Establiment();
			if (request.getSession().getAttribute("establiment") != null) {
				establiment = (Establiment)request.getSession().getAttribute("establiment");
			}
			
//			// Obtenim l'estat de l'oliva.
//			Boolean sana = Boolean.valueOf("false");
//			if (pEntradaOliv.getTipusOlivaTaula().intValue() == (Integer.valueOf(tipusOlivaTaula)).intValue()) {
//				sana = Boolean.valueOf("true");
//			}
			Integer tipusOlivaTaula = pEntradaOliv.getTipusOlivaTaula().intValue();

			try {
				DescomposicioPartidaOliva[] descomposicioPartidaOlivaArray = null;
				DescomposicioPartidaOliva descomposicioPartidaOliva = new DescomposicioPartidaOliva();
				VarietatOlivaCommand[] variedadesOlivasCommand = pEntradaOliv.getVariedades();
				
				String ids = pEntradaOliv.getIdsPlantacions();
				String[] idsPlantacions = ids.split(",");
				
				if ((!ids.equals("")) && (idsPlantacions.length > 0)) {
					// Obtenim el total de varietats.
					int totalVarietats = 0;
					for (int n = 0; n < idsPlantacions.length; n++) {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Collection varietatsPlantacions = oliInfraestructuraEjb.cercaVarietatsOlivaPerPlantacions(""+idsPlantacions[n]);
						if (varietatsPlantacions.size() > 0) {
							totalVarietats += varietatsPlantacions.size();
						}
					}
					
					if (totalVarietats > 0) {
						DescomposicioPartidaOliva[] descomposicionsArray = new DescomposicioPartidaOliva[totalVarietats];
						int x = 0;
						
						for (int n = 0; n < idsPlantacions.length; n++) {
							Long idPlant = Long.parseLong(idsPlantacions[n]);
							if (variedadesOlivasCommand != null) {
								descomposicioPartidaOlivaArray = new DescomposicioPartidaOliva[variedadesOlivasCommand.length];
								for (int i = 0; i < variedadesOlivasCommand.length; i++) {
									VarietatOlivaCommand varietatOlivaCommand = variedadesOlivasCommand[i];
									descomposicioPartidaOliva = new DescomposicioPartidaOliva();
									if ((varietatOlivaCommand.getIdPlantacio() != null) && (varietatOlivaCommand.getIdPlantacio().intValue() == idPlant.intValue()) && (varietatOlivaCommand.getProduccion() != null)) {
										oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
										DescomposicioPlantacio descomposicioPlantacio = oliInfraestructuraEjb.descomposicioPlantacioByPlantacioIdVariedadId(idPlant, varietatOlivaCommand.getId());
										
										descomposicioPartidaOliva.setKilos(varietatOlivaCommand.getProduccion());
										descomposicioPartidaOliva.setDescomposicioPlantacio(descomposicioPlantacio);
										descomposicioPartidaOlivaArray[i] = descomposicioPartidaOliva;
										
										descomposicionsArray[x++] = descomposicioPartidaOliva;
									}
								}
							}
						}
						
						oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
						partidaId = oliProcessosEjb.entradaOliva(
								establiment.getId(),
								pEntradaOliv.getDataExecucio(),
								pEntradaOliv.getHora(),
								pEntradaOliv.getCodi(),
								pEntradaOliv.getFincaId(),
								pEntradaOliv.getPlantacioId(),
								descomposicionsArray,
								pEntradaOliv.getZonaId(),
								pEntradaOliv.getOlivicultorId(),
								pEntradaOliv.getEnvasPetit(),
								pEntradaOliv.getEnvasRigid(),
								pEntradaOliv.getEnvasVentilat(),
								tipusOlivaTaula,
								pEntradaOliv.getQuantitat(),
								tipusTrazaPartidaOliva,
								pEntradaOliv.getEsEcologic()==null?false:true);
					}
				}
				
				myModel.put("idsPlantacions", ids);
				
				ControllerUtils.saveMessageInfo(request, missatge("proces.entradaOliva.missatge.ok"));
				ControllerUtils.showDocumentCartillaOlivicultor(request, 3, pEntradaOliv.getOlivicultorId());
			} catch (Exception e) {
				logger.error("Error processant l'entrada de oliva", e);
				ControllerUtils.saveMessageError(request, missatge("proces.entradaOliva.missatge.no"));
			}

			String urlRetorn = getSuccessView() + "?establimentId=" + establiment.getId() + "&zonaId=" + pEntradaOliv.getZonaId();
			return new ModelAndView(urlRetorn, myModel);
		} else {
			return new ModelAndView(getSuccessView());
		}
	}

	/**
	 * Retorna totes les dades del model necessaries per a mostrar el formulari d'inserció (LOVs).
	 * 
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors)
	throws Exception {
		Map myModel = new HashMap();

		ProcesEntradaOlivaTaulaCommand procesEntradaOlivaTaulaCommand = (ProcesEntradaOlivaTaulaCommand)command;
		String lang = Idioma.getLang(request);

		String paramOlivicultor = request.getParameter("idOlivicultor");
		String paramNif = request.getParameter("nif");
		
		paramOlivicultor = (paramOlivicultor != null) ? paramOlivicultor :"";
		paramNif = paramNif != null ? paramNif : "";

		Olivicultor olivicultor = null;
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		if (!paramNif.equals("")){
			olivicultor = oliInfraestructuraEjb.cercaOlivicultorAmbNif(paramNif);
			if(olivicultor == null){
				Long oliId = oliInfraestructuraEjb.cercaOlivicultorPerRfid(paramNif);
				olivicultor = oliInfraestructuraEjb.olivicultorAmbId(oliId);
			}
		} else {
			olivicultor = oliInfraestructuraEjb.cercaOlivicultorAmbValor(paramOlivicultor);
		}
		Long idOlivicultor = null;
		if (olivicultor == null) {
			if (!paramOlivicultor.equals("app")) {
				paramOlivicultor = "app";
				ValidationUtils.rejectIfEmpty(
						errors,
						"olivicultorId",
						"error.olivicultor.notrobat",
						"No s'ha trobat cap olivicultor");
			}
		} else {
			idOlivicultor = olivicultor.getId();
		}
		
		if (idOlivicultor != null) {
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Double totalProduccioRestant = oliInfraestructuraEjb.obtenirTotalProduccioRestantOlivicultor(idOlivicultor);
			
			procesEntradaOlivaTaulaCommand.setOlivicultorId(idOlivicultor);
			if (!olivicultor.getCartilla().booleanValue()){
					paramOlivicultor = "app";
					errors.rejectValue("olivicultorId", "error.olivicultor.no.cartilla", "L'olivicultor no té cartilla");
			}
			if (totalProduccioRestant <= 0) {
				paramOlivicultor = "app";
				errors.rejectValue("olivicultorId", "error.produccio.restant.zero", "Aquest olivicultor ha esgotat tota la seva producció");
			}
			
			myModel.put("totalProduccioRestant", totalProduccioRestant);
		}

		if (paramOlivicultor.equals("app")) {
			myModel.put("appletRecOli", new Boolean(false));

			String navegador = request.getHeader("User-Agent");
			if (navegador.indexOf("MSIE") != -1) {
				myModel.put("MSIE", new Boolean(true));
			}

			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			ArrayList olivicultoresArray = new ArrayList(oliInfraestructuraEjb.findOlivicultorsAltaDOCartillaByCampanyaOrderNom(oliInfraestructuraEjb.campanyaCercaActual()));
			for (int indOli = 0; indOli < olivicultoresArray.size(); indOli++) {
				Olivicultor oli = (Olivicultor)olivicultoresArray.get(indOli);

				String oliRa = null;
				if ((oli.getCodiDO() != null) && (!oli.getCodiDO().equals(""))) {
					oliRa = oli.getCodiDO();
				}

				String oliRe = null;
				if ((oli.getCodiDOExperimental() != null) && (!oli.getCodiDOExperimental().equals(""))) {
					oliRe=oli.getCodiDOExperimental();
				}
				
				String oliRt = null;
				if ((oli.getCodiDOPOliva() != null) && (!oli.getCodiDOPOliva().equals(""))) {
					oliRt=oli.getCodiDOPOliva();
				}
				
				if (oliRa != null) oliRa = "RA-"+oliRa;
				if (oliRe != null) oliRe = "RE-"+oliRe;
				if (oliRt != null) oliRt = "RT-"+oliRt;
				
				String oliR = "(";
				if (oliRa != null) {
					oliR += oliRa;
					if (oliRe != null) oliR = oliR + " - " + oliRe;
					if (oliRt != null) oliR = oliR + " - " + oliRt;
				} else if (oliRe != null) {
					oliR += oliRe;
					if (oliRt != null) oliR = oliR + " - " + oliRt;
				} else if (oliRt != null) oliR = oliR + oliRt;
				oliR += ")";
				

				if (oliR.length()>2) {
					oli.setNom(oli.getNom() + " " + oliR);
				} else {
					oli.setNom(oli.getNom());
				}

				olivicultoresArray.set(indOli, oli);
			}

			Collection olivicultores = olivicultoresArray;
			myModel.put("olivicultores", olivicultores);
		} else {
			// Zones actives de l'establiment del productor.
			Establiment est = (Establiment)request.getSession().getAttribute("establiment");
			if (est != null) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				List zonasActivasEst = oliInfraestructuraEjb.zonaActiusAmbEstabliment(est.getId());
				if (zonasActivasEst != null) {
					myModel.put("zonasActivasEst", zonasActivasEst);
				}
			}

			// Finques actives associades a l'olivicultor que fa l'entrada.
			if (idOlivicultor != null) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection fincas = oliInfraestructuraEjb.fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion(idOlivicultor);

				// Obtenim totes les plantacions de totes les finques de l'olivicultor.
				int i = 0;
				List items = new ArrayList();
				for (Object objf : fincas) {
					Finca f = (Finca)objf;
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Collection plantacions = oliInfraestructuraEjb.getPlantacionesDeFinca(f.getId());
					for (Iterator itp = plantacions.iterator(); itp.hasNext();) {
						Plantacio p = (Plantacio)itp.next();
						items.add(p);
						
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Collection varietatsPlantacions = oliInfraestructuraEjb.cercaVarietatsOlivaTaulaPerPlantacions(""+p.getId());
						
						
						String v = "varietatsPlantacions_" + i;
						myModel.put(v, varietatsPlantacions);
						i++;
					}
				}
				
				myModel.put("plantacions", items);
			}

			myModel.put("tipusOlivaTaula", tipusOlivaTaula(lang));
			myModel.put("olivicultor", olivicultor);
		}

		
		if (errors.hasErrors()){
			String nomParam;
			Enumeration params = request.getParameterNames();
			List<Long> plantacionsSeleccionades = new ArrayList<Long>();
			
			while (params.hasMoreElements()){
				nomParam=(String)params.nextElement();
				String splitParam[]=nomParam.split("_");
				if (splitParam.length==2){
					if (splitParam[0].equals("plantacio")){
						plantacionsSeleccionades.add(Long.parseLong(splitParam[1]));
					}
				}
			}
			
			myModel.put("plantacionsSeleccionades", plantacionsSeleccionades);
		}
		
		myModel.put("path", "entrada_oliva");

		return myModel;
	}

	/**
	 * En el cas de que sigui una edició retorna l'objecte omplit amb
	 * les dades actuals del registre. En cas de que sigui una creació
	 * retorna l'objecte buit.
	 * 
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		ProcesEntradaOlivaTaulaCommand pEntradaOliv = (ProcesEntradaOlivaTaulaCommand)request.getSession().getAttribute("pEntradaOliv");
		if (pEntradaOliv == null)
			pEntradaOliv = new ProcesEntradaOlivaTaulaCommand();
		else
			request.getSession().removeAttribute("pEntradaOliv");

		pEntradaOliv.setDataExecucio(new Date());

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String hora = df.format(gc.getTime());
		pEntradaOliv.setHora(hora);
		
		pEntradaOliv.setEnvasPetit(true);
		pEntradaOliv.setEnvasRigid(true);
		pEntradaOliv.setEnvasVentilat(true);

		try {
			Long idOlivicultor = null;

			if ((request.getAttribute("idOlivicultor") != null) && (!((String)request.getAttribute("idOlivicultor")).equals("") && !((String)request.getAttribute("idOlivicultor")).equals("app"))) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Olivicultor olivicultor = oliInfraestructuraEjb.cercaOlivicultorAmbValor((String)request.getAttribute("idOlivicultor"));
				if (olivicultor != null) {
					idOlivicultor = olivicultor.getId();
				}
			} else if ((request.getParameter("idOlivicultor") != null) && (!request.getParameter("idOlivicultor").equals("") && !request.getParameter("idOlivicultor").equals("app"))) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Olivicultor olivicultor = oliInfraestructuraEjb.cercaOlivicultorAmbValor(request.getParameter("idOlivicultor"));
				if (olivicultor != null) {
					idOlivicultor = olivicultor.getId();
				}
			}
			
			if (idOlivicultor != null) {
				pEntradaOliv.setOlivicultorId(idOlivicultor);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				List variedadesList = oliInfraestructuraEjb.totesVarietatsOlivaByOlivicultor(idOlivicultor);
				if (variedadesList != null) {
					VarietatOlivaCommand[] variedades = new VarietatOlivaCommand[variedadesList.size()];
					for (int i = 0; i < variedadesList.size(); i++) {
						variedades[i] = new VarietatOlivaCommand();
					}
					pEntradaOliv.setVariedades(variedades);
				}
			} else {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection variedadesList = oliInfraestructuraEjb.descomposicioVariedades();
				if (variedadesList != null) {
					VarietatOlivaCommand[] variedades = new VarietatOlivaCommand[variedadesList.size()];
					for (int i = 0; i < variedadesList.size(); i++) {
						variedades[i] = new VarietatOlivaCommand();
					}
					pEntradaOliv.setVariedades(variedades);
				}
			}
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}

		return pEntradaOliv;
	}
	
	/**
	 * Llistat amb els estats de l'oliva.
	 * @param lang
	 * @return
	 */
	private List tipusOlivaTaula(String lang) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", new java.util.Locale(lang));
		List tipusOliva = new ArrayList();
		
		BasicData olivaVerda = new BasicData();
		olivaVerda.setId(tipusOlivaVerda);
		olivaVerda.setNom(bundle.getString("proces.entradaOlivaTaula.camp.tipus.verda"));
		tipusOliva.add(olivaVerda);
		
		BasicData olivaNegra = new BasicData();
		olivaNegra.setId(tipusOlivaNegra);
		olivaNegra.setNom(bundle.getString("proces.entradaOlivaTaula.camp.tipus.negra"));
		tipusOliva.add(olivaNegra);
		
		return tipusOliva;
	}

	/**
	 * Configuració del <i>binder</i>. Si hi ha camps en el <i>command</i>
	 * amb tipus que no siguin <i>String</i>, s'ha de configurar el seu corresponent binder aquí.
	 * 
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder)
	throws Exception {
		binder.registerCustomEditor(
				Integer.class,
				new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Float.class,
				new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
		binder.registerCustomEditor(
				Date.class,
				new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
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
	 * Injecció de la dependència oliProcessb
	 *
	 * @param oliProcessosEjb La classe a injectar.
	 */
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	/**
	 * Injecció de la dependència seleccioSessionKey
	 *
	 * @param seleccioSessionKey La classe a injectar.
	 */
	public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
		this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
	}
	
	/**
	 * Injecció de la dependència tipusOlivaVerda
	 *
	 * @param tipusOlivaVerda La classe a injectar.
	 */
	public void setTipusOlivaVerda(String tipusOlivaVerda) {
		this.tipusOlivaVerda = tipusOlivaVerda;
	}

	/**
	 * Injecció de la dependència tipusOlivaTrencada
	 *  
	 * @param tipusOlivaTrencada La classe a injectar.
	 */
	public void setTipusOlivaTrencada(String tipusOlivaTrencada) {
		this.tipusOlivaTrencada = tipusOlivaTrencada;
	}

	/**
	 * Injecció de la dependència tipusOlivaNegra
	 *  
	 * @param tipusOlivaNegra La classe a injectar.
	 */
	public void setTipusOlivaNegra(String tipusOlivaNegra) {
		this.tipusOlivaNegra = tipusOlivaNegra;
	}


	/**
	 * Injecció de la dependència tipusTraza
	 *
	 * @param tipusTraza La classe a injectar.
	 */
	public void setTipusTrazaPartidaOliva(String tipusTrazaPartidaOliva) {
		this.tipusTrazaPartidaOliva = tipusTrazaPartidaOliva;
	}

	private String missatge(String clave) {
		String valor= getMessageSourceAccessor().getMessage(clave);
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
