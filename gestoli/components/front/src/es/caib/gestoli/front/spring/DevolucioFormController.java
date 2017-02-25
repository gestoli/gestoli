package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import sun.nio.cs.ext.MacIceland;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.Zona;
import es.caib.gestoli.logic.util.Constants;

public class DevolucioFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(DevolucioFormController.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private HibernateTemplate hibernateTemplate;
	private String rolEnvasador;
	
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
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		DevolucioCommand devolucioCommand = (DevolucioCommand)command;

		try {
			if (devolucioCommand.getIdLot() != null) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Lot lot = oliInfraestructuraEjb.lotAmbId(devolucioCommand.getIdLot());
				
				if (lot != null) {
					oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
					oliProcessosEjb.insertarDevolucioBotelles(
							lot,
							devolucioCommand.getIdSortida(),
							devolucioCommand.getDevolucioBotelles(),
							devolucioCommand.getData(),
							missatge("devolucio.camp.observacio"));
				}
			}
			
			ControllerUtils.saveMessageInfo(request, missatge("devolucio.missatge.modificar.ok"));
		} catch (Exception ex) {
			logger.error("Error fent la devolució ", ex);
			ControllerUtils.saveMessageError(request, missatge("devolucio.missatge.modificar.no"));
		}
		return new ModelAndView(forward, myModel);
	}


	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors)
	throws Exception {
		Map myModel = new HashMap();
		myModel.put("path", "modificar_devolucio");
		
		DevolucioCommand devolucioCommand = (DevolucioCommand)command;
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		DevolucioCommand devolucioCommand = new DevolucioCommand();
		Integer maximBotelles = 0;
		
		try {
			String id = request.getParameter("id");
			id = (id != null)?id:"";
			
			if (!id.equals("")) {
				Long lid = Long.parseLong(id);
				
				devolucioCommand.setIdSortida(lid);
				
				try {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					SortidaLot sortidaLot = oliInfraestructuraEjb.sortidaLotAmbId(lid);
					
					if (sortidaLot != null) {
						devolucioCommand.setIdLot(sortidaLot.getLot().getId());
						devolucioCommand.setTotalBotelles(sortidaLot.getVendaNumeroBotelles());
						if (sortidaLot.getBotellesDevolucio() != null) maximBotelles = sortidaLot.getBotellesDevolucio();
						devolucioCommand.setBotellesTornades(maximBotelles);
					}
				} catch (Exception ex) {
					logger.error("Error obtenint les sortides", ex);
				}
			}
			
		} catch (Exception ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}
		
		return devolucioCommand;
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
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
		binder.registerCustomEditor(
	    		Double.class,
	    		new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(
	    		Long.class,
	    		new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
	    		Integer.class,
	    		new CustomNumberEditor(Integer.class, true));
    	binder.registerCustomEditor(
    			java.util.Date.class, 
    			new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave){
		return getMessageSourceAccessor().getMessage(clave);
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

	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}
}