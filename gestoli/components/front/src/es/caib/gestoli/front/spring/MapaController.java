package es.caib.gestoli.front.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class MapaController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(MapaController.class);
	
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

		String longitud = request.getParameter("x"); 
		String latitud = request.getParameter("y");
		String municipi = request.getParameter("v");
		String poligon = request.getParameter("po");
		String parcela = request.getParameter("pa");
		String catastre = request.getParameter("ca");
			
		myModel.put("x", longitud);
		myModel.put("y", latitud);
		myModel.put("v", municipi);
		myModel.put("po", poligon);
		myModel.put("pa", parcela);
		myModel.put("ca", catastre);

		return myModel;
	}
}
