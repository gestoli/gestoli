/**
 * 
 */
package es.caib.gestoli.front.mvc;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;

/**
 * Controlador base
 * 
 */
public class BaseController implements MessageSourceAware {

	private MessageSource messageSource;

	/**
	 * Registre un missatge de tipus informació
	 * @param request
	 * @param missatge
	 */
	public void saveMessageInfo(HttpServletRequest request, String missatge) {
		HttpSession session = request.getSession();
		Collection missatges = (Collection)session.getAttribute("missatgesInfo");
		if (missatges == null) {
			missatges = new ArrayList();
			session.setAttribute("missatgesInfo", missatges);
		}
		missatges.add(missatge);
	}

	/**
	 * Registre un missatge de tipus error
	 * @param request
	 * @param missatge
	 */
	public void saveMessageError(HttpServletRequest request, String missatge) {
		HttpSession session = request.getSession();
		Collection missatges = (Collection)session.getAttribute("missatgesError");
		if (missatges == null) {
			missatges = new ArrayList();
			session.setAttribute("missatgesError", missatges);
		}
		missatges.add(missatge);
	}

	/**
	 * @param key
	 * @return Missatge en multiidioma del cosi indicat
	 */
	public String getMessage(String key) {
		try {
			return messageSource.getMessage(
					key,
					null,
					null);
		} catch (NoSuchMessageException ex) {
			return "???" + key + "???";
		}
	}



	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
