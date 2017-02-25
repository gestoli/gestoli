package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerUtils {

	public static void saveMessageInfo(HttpServletRequest request, String missatge) {
		HttpSession session = request.getSession();
		Collection missatges = (Collection)session.getAttribute("missatgesInfo");
		if (missatges == null) {
			missatges = new ArrayList();
			session.setAttribute("missatgesInfo", missatges);
		}
		missatges.add(missatge);
	}

	public static void saveMessageError(HttpServletRequest request, String missatge) {
		HttpSession session = request.getSession();
		Collection missatges = (Collection)session.getAttribute("missatgesError");
		if (missatges == null) {
			missatges = new ArrayList();
			session.setAttribute("missatgesError", missatges);
		}
		missatges.add(missatge);
	}

	public static void showDocumentRendiment(HttpServletRequest request, Long idElaboracio, Long idEstabliment) {
		HttpSession session = request.getSession();
		Object[] docRendiment =  new Object[2];
		docRendiment[0] = idElaboracio;
		docRendiment[1] = idEstabliment;
		session.removeAttribute("missatgesDocumentRendiment");
		session.setAttribute("missatgesDocumentRendiment", docRendiment);
	}
	
	public static void showDocumentCartillaOlivicultor(HttpServletRequest request, Integer tipus, Long idOlivicultor) {
		HttpSession session = request.getSession();
		Object[] cartillaOlivicultor =  new Object[2];
		cartillaOlivicultor[0] = tipus;
		cartillaOlivicultor[1] = idOlivicultor;
		session.removeAttribute("missatgesCartillaOlivicultor");
		session.setAttribute("missatgesCartillaOlivicultor", cartillaOlivicultor);
	}
	
	public static void showVolantCirculacio(HttpServletRequest request, Long[] idVolantCirculacio, Integer sentit) {
		HttpSession session = request.getSession();
		Object[] volant =  new Object[2];
		volant[0] = idVolantCirculacio;
		volant[1] = sentit;
		session.removeAttribute("missatgesVolantCirculacio");
		session.setAttribute("missatgesVolantCirculacio", volant);
	}
}
