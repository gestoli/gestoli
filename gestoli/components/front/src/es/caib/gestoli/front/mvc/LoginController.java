package es.caib.gestoli.front.mvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.gestoli.front.mvc.BaseController;

/**
 * Controlador dels formularis d'accés a l'aplicació
 * 
 * @author Sion andreu <sandreu@limit.es>
 *
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * Funció cridada pel formulari login.html
	 * 
	 * @param request
	 * @param myModel
	 * @return
	 */
	@RequestMapping(value="/login.html")
	public String login(
			HttpServletRequest request,
	        ModelMap myModel){
		return "login";
	}

	/**
	 * Funció cridada pel formulari loginError.html
	 * 
	 * @param request
	 * @param myModel
	 * @return
	 */
	@RequestMapping(value="/loginError.html")
	public String loginError(
			HttpServletRequest request,
	        ModelMap myModel){
		return "loginError";
	}

	/**
	 * Funció cridada pel mètode GET del formulari logout.html
	 * 
	 * @param request
	 * @param myModel
	 * @return
	 */
	@RequestMapping(value="/logout.html")
	public String logout(
			HttpServletRequest request,
	        ModelMap myModel){
		request.getSession().invalidate();
		return "redirect:Inici.html";
	}

	protected final Logger logger = Logger.getLogger(getClass());

}
