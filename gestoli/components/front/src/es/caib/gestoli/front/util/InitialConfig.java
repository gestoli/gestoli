package es.caib.gestoli.front.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;



/**
 * Configuracio inicial 
 * @author obarnes
 */
public class InitialConfig {
	private static Logger logger = Logger.getLogger(InitialConfig.class);

	private InitialContext ic;
	private static InitialConfig me;

	static {
		try {
			me = new InitialConfig();
		} catch(InitialConfigException se) {
			logger.error("EXCEPTION", se);
		}
	}

	/**
	 * getInstance()
	 * @return
	 */
	static public InitialConfig getInstance() {
		return me;
	}


	/**
	 * InitialConfig()
	 * @throws InitialConfigException
	 */
	private InitialConfig() throws InitialConfigException  {
		try {
			ic = new InitialContext();
		} catch (NamingException ne) {
			throw new InitialConfigException(ne);
		} catch (Exception e) {
			throw new InitialConfigException(e);
		}
	}
	
	/**
	 * Email del administrador
	 * @return
	 */
	public String getEmailAdministrator() throws InitialConfigException{
		String email = "";
		try {
			Context environment = (Context)ic.lookup("java:comp/env");
			email = (String)environment.lookup("emailAdministrator");
			email = email.trim();
		} catch (NamingException ex) {
			throw new InitialConfigException(ex);
		}
		return email;
	}

//	/**
//	 * Contexto BackOffice
//	 * @return
//	 */
//	public String getContextoBackOffice() {
//		String backofficeContext = "";
//		try {
//			Context environment = (Context)ic.lookup("java:comp/env");
//			backofficeContext = (String)environment.lookup("backofficeContext");
//			backofficeContext = backofficeContext.trim();
//		} catch (NamingException ex) {
//			backofficeContext = "/crsoffer";
//		}
//		return backofficeContext;
//	}


}

