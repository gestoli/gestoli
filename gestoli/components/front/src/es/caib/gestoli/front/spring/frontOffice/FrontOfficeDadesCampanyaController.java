package es.caib.gestoli.front.spring.frontOffice;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.util.Constants;

public class FrontOfficeDadesCampanyaController extends SimpleFormController { //implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(FrontOfficeDadesCampanyaController.class);
	private OliFrontOfficeEjb oliFrontOfficeEjb;
	private HibernateTemplate hibernateTemplate;
	private String qualificat;

	 public ModelAndView onSubmit(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object command,
	            BindException errors)
	    throws ServletException {
	    	FrontOfficeDadesCampanyaCommand consulta = (FrontOfficeDadesCampanyaCommand) command;
        	
	    	Map myModel = new HashMap();
	    	String idioma = request.getParameter("idioma");
	    	try {
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
	    		if (consulta.getDataFi() != null)    myModel.put("dataFi", sdf.format(consulta.getDataFi()));

	    		myModel.put("path", "dadesCampanya");
	    		
	    	} catch (Exception ex) {
				throw new ServletException(ex);
			}
	    	
			return new ModelAndView("redirect:dadescampanya."+idioma+".html?idioma="+idioma, myModel);
	    }
	
	
	 /**
     * Retorna todos los datos del modelo necesarios para mostrar
     * el formulario de inserción (LOVs y cosas de esas) si no hay.
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        Map myModel = new HashMap();

		myModel.put("formData", command);

        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	FrontOfficeDadesCampanyaCommand consulta = (FrontOfficeDadesCampanyaCommand) command;
        	myModel.put("dataInici", sdf.format(consulta.getDataInici()));
        	myModel.put("dataFi", sdf.format(consulta.getDataFi()));
        	
        	String idioma = request.getParameter("idioma");
        	myModel.put("idioma", idioma);
        	
			Collection llistat = new ArrayList();
	    		
			//Collection campanyes = oliFrontOfficeEjb.campanyesCercaPerData(dataInici, dataFi);
			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
			Collection campanyes = oliFrontOfficeEjb.campanyaCercaTotes();
			
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator(',');
			simbolos.setGroupingSeparator('.');
			DecimalFormat df = new DecimalFormat("#,###,###.00", simbolos);
			
			int numCampanyes = 0;
			
			// Oliva Molturada
			Double totalOlives = Double.valueOf("0");
			String titol = "";
			if ("es".equals(idioma)){
				titol = "Cantidad de Oliva Molida (desde " + sdf.format(consulta.getDataInici()) + " hasta " + sdf.format(consulta.getDataFi()) + ")";				
			} else {
				titol = "Quantitat d'Oliva Molturada (des de " + sdf.format(consulta.getDataInici()) + " fins al " + sdf.format(consulta.getDataFi()) + ")";
			}
			for (Object camp : campanyes){
				Campanya campanya = (Campanya)camp;
				Date dataInici = campanya.getData();
				Date dataFi = new Date();
				if (campanya.getDataFi() != null)	dataFi = campanya.getDataFi();
				
				// Comprobamos que la campaña comprende algún trozo del rango de fechas indicados en la consulta.
				if ((dataInici.compareTo(consulta.getDataFi()) <= 0) && (dataFi.compareTo(consulta.getDataInici()) >= 0)){
					numCampanyes ++;
					
					if (dataInici.compareTo(consulta.getDataInici()) < 0 ) dataInici = consulta.getDataInici();
					if (dataFi.compareTo(consulta.getDataFi()) > 0 )       dataFi = consulta.getDataFi();
					//dataFi = consulta.getDataFi();
					
					oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
					Double olivesEntrades = oliFrontOfficeEjb.getTotalOlivasEntradas(campanya.getId(), dataInici, dataFi, campanya.getId());
					//Double olivesEntrades = oliFrontOfficeEjb.getTotalOlivasEntradas(dataInici, dataFi);
					if(olivesEntrades == null){
						olivesEntrades = Double.valueOf("0");
					}
					totalOlives += olivesEntrades;
	
					if ("es".equals(idioma)){
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (desde " + sdf.format(dataInici) + " hasta " + sdf.format(dataFi) + ")", df.format(olivesEntrades) + " kg."));
					} else {
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (des de " + sdf.format(dataInici) + " fins al " + sdf.format(dataFi) + ")", df.format(olivesEntrades) + " kg."));
					}
				}
			}
			if (numCampanyes > 1){
				llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, "Total", df.format(totalOlives) + " kg."));
			}
			
			
			// Oli Produït
			Double totalOli = Double.valueOf("0");
			
			if ("es".equals(idioma)){
				titol = "Cantidad de Aceite producido (desde " + sdf.format(consulta.getDataInici()) + " hasta " + sdf.format(consulta.getDataFi()) + ")";
			} else {
				titol = "Quantitat d'Oli produït (des de " + sdf.format(consulta.getDataInici()) + " fins al " + sdf.format(consulta.getDataFi()) + ")";
			}
			numCampanyes = 0;
			for (Object camp : campanyes){
				Campanya campanya = (Campanya)camp;
				Date dataInici = campanya.getData();
				Date dataFi = new Date();
				if (campanya.getDataFi() != null)	dataFi = campanya.getDataFi();
				
				// Comprobamos que la campaña comprende algún trozo del rango de fechas indicados en la consulta.
				if ((dataInici.compareTo(consulta.getDataFi()) <= 0) && (dataFi.compareTo(consulta.getDataInici()) >= 0)){
					numCampanyes ++;
					
					if (dataInici.compareTo(consulta.getDataInici()) < 0 ) dataInici = consulta.getDataInici();
					if (dataFi.compareTo(consulta.getDataFi()) > 0 )       dataFi = consulta.getDataFi();
					//dataFi = consulta.getDataFi();
				
					oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
					Double oli = oliFrontOfficeEjb.getTotalOliElaborat(campanya.getId(), dataInici, dataFi, campanya.getId());
					//Double oli = oliFrontOfficeEjb.getTotalOliElaborat(dataInici, dataFi);
					if(oli == null){
						oli = Double.valueOf("0");
					}
					totalOli += oli;
					if ("es".equals(idioma)){
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (desde " + sdf.format(dataInici) + " hasta " + sdf.format(dataFi) + ")", df.format(oli) + " l."));
					} else {
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (des de " + sdf.format(dataInici) + " fins al " + sdf.format(dataFi) + ")", df.format(oli) + " l."));
					}
				}
			}
			if (numCampanyes > 1){
				llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, "Total", df.format(totalOli) + " l."));
			}
			
			
			// Oli DO Produït
			Double totalOliDO = Double.valueOf("0");
			if ("es".equals(idioma)){
				titol = "Cantidad de Aceite DO producido (desde " + sdf.format(consulta.getDataInici()) + " hasta " + sdf.format(consulta.getDataFi()) + ")";
			} else {
				titol = "Quantitat d'Oli DO produït (des de " + sdf.format(consulta.getDataInici()) + " fins al " + sdf.format(consulta.getDataFi()) + ")";
			}
			numCampanyes = 0;
			for (Object camp : campanyes){
				Campanya campanya = (Campanya)camp;
				Date dataInici = campanya.getData();
				Date dataFi = new Date();
				if (campanya.getDataFi() != null)	dataFi = campanya.getDataFi();
				
				// Comprobamos que la campaña comprende algún trozo del rango de fechas indicados en la consulta.
				if ((dataInici.compareTo(consulta.getDataFi()) <= 0) && (dataFi.compareTo(consulta.getDataInici()) >= 0)){
					numCampanyes ++;
					
					if (dataInici.compareTo(consulta.getDataInici()) < 0 ) dataInici = consulta.getDataInici();
					if (dataFi.compareTo(consulta.getDataFi()) > 0 )       dataFi = consulta.getDataFi();
				
		    		List categorias = new ArrayList();
					categorias.add(Integer.valueOf(qualificat));
				   	oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
				   	
//	                Double existenciaInicialDO = oliFrontOfficeEjb.getQuantitatOliExistenciDOSistema(dataInici);
//	                Double existenciaFinalDO = oliFrontOfficeEjb.getQuantitatOliExistenciDOSistema(dataFi);
//	                Double sortidesDO = oliFrontOfficeEjb.getSortidesDOEntreDias(dataInici, dataFi);
//	                Double oliDO = existenciaFinalDO - existenciaInicialDO + sortidesDO;
	                
	                Double oliDO = oliFrontOfficeEjb.getQuantitatOliAnalisiDOEntreDates(dataInici, dataFi);
					if(oliDO == null){
						oliDO = Double.valueOf("0");
					}
					totalOliDO += oliDO;
					if ("es".equals(idioma)){
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (desde " + sdf.format(dataInici) + " hasta " + sdf.format(dataFi) + ")", df.format(oliDO) + " l."));
					} else {
						llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, campanya.getNom() + " (des de " + sdf.format(dataInici) + " fins al " + sdf.format(dataFi) + ")", df.format(oliDO) + " l."));
					} 
				}
			}
			if (numCampanyes > 1){
				llistat.add(new FrontOfficeDadesCampanyaControllerRow(titol, "Total", df.format(totalOliDO) + " l."));
			}
			
			
			myModel.put("llistat", llistat);
			
	       	myModel.put("path", "dadesCampanya");
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint les dades de campanya", ex);
        }
        return myModel;
    }
    
    /**
     * En el cas de que sigui una edició retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creació
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	FrontOfficeDadesCampanyaCommand consulta = (FrontOfficeDadesCampanyaCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new FrontOfficeDadesCampanyaCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
    	try{
    		Date dataInici = null;
    		Date dataFi = null;
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Calendar cal = Calendar.getInstance();
    		
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = cal.getTime();
    		}
    		
    		if(request.getParameter("dataInici") != null && !((String)request.getParameter("dataInici")).equals("")) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} else {
    			// Coger fecha inicio última campaña
    			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
    			Long idCampanya = oliFrontOfficeEjb.campanyaCercaActual();
    			Campanya campanya = oliFrontOfficeEjb.campanyaAmbId(idCampanya.intValue());
    			//cal.add( Calendar.MONTH, -1 );
    			dataInici = campanya.getData();
    		}
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataInici != null) consulta.setDataFi(dataFi);
    		
    

    		//request.setAttribute("llistat", llistat);    		
    		
/*    		Collection llistat = new ArrayList();
    		if (idOlivicultor != null){
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.quadernCampCercaTotsPerOlivicultor(idOlivicultor, dataInici, dataFi);
        		request.setAttribute("llistat", llistat);
        	}
*/    		
    		
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   	
    	
    	return consulta;
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
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
    	binder.registerCustomEditor(java.util.Date.class, dateEditor);
    }	
	    
    
    /**
     * Inyección de la dependencia oliFrontOfficeEjb
     * @param oliFrontOfficeEjb La clase a inyectar.
     */
    public void setOliFrontOfficeEjb(OliFrontOfficeEjb oliFrontOfficeEjb) {
        this.oliFrontOfficeEjb = oliFrontOfficeEjb;
    }

	/**
	 * Injecció de la dependència pendentQualificar
	 * @param desqualificat La classe a injectar.
	 */
	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
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

