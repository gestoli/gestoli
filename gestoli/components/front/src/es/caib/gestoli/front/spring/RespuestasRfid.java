package es.caib.gestoli.front.spring;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;


public class RespuestasRfid extends SimpleFormController {

    private static Logger logger = Logger.getLogger(RespuestasRfid.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
    private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;

	public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	Map myModel = new HashMap();

    	try{
	    	//Recogemos los datos de la peticion
			ObjectInputStream input = new ObjectInputStream(request.getInputStream());
	
			Object dataInput;
			dataInput = input.readObject();
		
			String uid = (String) dataInput;
			
			String res;
			int accion= Integer.parseInt(request.getParameter("acc"));
			switch (accion){
				case 0:
					oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
					res = oliConsultaEjb.findCodigoOlivicultorFromUidTarjeta(uid, new Integer(oliInfraestructuraEjb.campanyaCercaActual().intValue())).toString();
					break;
				case 1:
					res="0";
					if (request.getParameter("idoliv")!=null){
						oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
						Long oliCod= oliConsultaEjb.findCodigoOlivicultorFromUidTarjeta(uid, new Integer(oliInfraestructuraEjb.campanyaCercaActual().intValue()));
						if (oliCod.intValue()==0 || oliCod.intValue()==Integer.parseInt(request.getParameter("idoliv"))){
							oliCod= new Long(request.getParameter("idoliv"));
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							oliInfraestructuraEjb.olivicultorGrabaUidTarjeta(uid, oliCod);
							res="ok";
						}
					}
					break;
				default:
					res="0";
			}
			
			Object obj = res;
			
			//Especificamos que la respuesta será un objeto serializado
			response.setContentType("java-internal/" + obj.getClass().getName());
					
			ObjectOutputStream output = new ObjectOutputStream(response.getOutputStream());
			
			output.writeObject(obj);
					
			output.flush();
			output.close();

    	} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}    	
    	
		
		return new ModelAndView(getSuccessView(), myModel);
    		

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

        return myModel;
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

    }


    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }


    /**
     * Inyección de la dependencia oliConsultaEjb
     * @param oliConsultaEjb La clase a inyectar.
     */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
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