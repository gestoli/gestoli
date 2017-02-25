/**
* ProcesEntradaFonollFormController.java
*/
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Establiment;

/**
* <p>Controlador per a l'acció d'executar un proces d'entrada de oliva.</p>
* <p>Aquest controlador distingueix entre les peticions del tipus GET y les de tipus POST.
* Si la petició es de tipus POST s'executa l'acció de creació o d'edició.
* Si la petició es de tipus GET només mostra la pàgina.
*
* @author cperez <cperez@at4.net>
*/
public class ProcesEntradaFonollFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(ProcesEntradaFonollFormController.class);
	private OliProcessosEjb oliProcessosEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String seleccioSessionKeyOrigen;
	private String tipusTrazaPartidaFonoll;
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
			ProcesEntradaFonollCommand pEntradaFonoll = (ProcesEntradaFonollCommand)command;
			Map myModel = new HashMap();
			Long partidaId = null;

			// Obtenim l'establiment.
			Establiment establiment = new Establiment();
			if (request.getSession().getAttribute("establiment") != null) {
				establiment = (Establiment)request.getSession().getAttribute("establiment");
			}
			

			try {
						
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				partidaId = oliProcessosEjb.entradaFonoll(
						establiment.getId(),
						pEntradaFonoll.getDataExecucio(),
						pEntradaFonoll.getHora(),
						pEntradaFonoll.getCodi(),
						pEntradaFonoll.getTitular(),
						pEntradaFonoll.getKgInici(),
						pEntradaFonoll.getMunicipiId(),
						pEntradaFonoll.getPoligon(),
						pEntradaFonoll.getParcela(),
						tipusTrazaPartidaFonoll);
				
				
				ControllerUtils.saveMessageInfo(request, missatge("proces.entradaFonoll.missatge.ok"));
			
			} catch (Exception e) {
				logger.error("Error processant l'entrada de oliva", e);
				ControllerUtils.saveMessageError(request, missatge("proces.entradaFonoll.missatge.no"));
			}

			String urlRetorn = getSuccessView() + "?establimentId=" + establiment.getId();
			return new ModelAndView(urlRetorn, myModel);
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

		ProcesEntradaFonollCommand procesEntradaFonollCommand = (ProcesEntradaFonollCommand)command;
		String lang = Idioma.getLang(request);

		myModel.put("municipis", oliInfraestructuraEjb.obtenirMunicipis());
		myModel.put("path", "entrada_fonoll");

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
		ProcesEntradaFonollCommand pEntradaFonoll = (ProcesEntradaFonollCommand)request.getSession().getAttribute("pEntradaFonoll");
		if (pEntradaFonoll == null)
			pEntradaFonoll = new ProcesEntradaFonollCommand();
		else
			request.getSession().removeAttribute("pEntradaFonoll");

		pEntradaFonoll.setDataExecucio(new Date());

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String hora = df.format(gc.getTime());
		pEntradaFonoll.setHora(hora);

		try {
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}

		return pEntradaFonoll;
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
	 * Injecció de la dependència tipusTraza
	 *
	 * @param tipusTraza La classe a injectar.
	 */
	public void setTipusTrazaPartidaFonoll(String tipusTrazaPartidaFonoll) {
		this.tipusTrazaPartidaFonoll = tipusTrazaPartidaFonoll;
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
