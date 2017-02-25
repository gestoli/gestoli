package es.caib.gestoli.front.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * 
 * @author obarnes
 */
public class Idioma {
	private static Logger logger = Logger.getLogger(Idioma.class);

	/**
	 * getLang
	 * @param request
	 * @return
	 */
	public static String getLang(HttpServletRequest request) { 

		String lang = "es";
		try {
			Locale locale = new Locale(lang);
			CookieLocaleResolver localeResolver = (CookieLocaleResolver)ApplicationContextHolder.getInstance().getService("localeResolver");
			if (localeResolver != null) {
				locale = localeResolver.resolveLocale(request);
				lang = locale.getLanguage();
			}
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
		return (lang);
	}


	/**
	 * getLocale
	 * @param request
	 * @return
	 */
	public static Locale getLocale(HttpServletRequest request) { 
		return (new Locale(getLang(request)));
	}

}
