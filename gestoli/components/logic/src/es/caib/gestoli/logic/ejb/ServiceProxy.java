package es.caib.gestoli.logic.ejb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliUsuariEjb;



/**
 * Proxy per accedir als missatges des de les classes de qualitat
 * 
 * @author miquelaa@limit.es
 */
public class ServiceProxy implements ApplicationContextAware {

	private static final ServiceProxy _inst = new ServiceProxy();
	private ApplicationContext ctx;

	/**
	 * @return La instància del servei
	 */
	public static ServiceProxy getInstance() {
		if (_inst == null)
			throw new RuntimeException("Application context not initialized!");
		return _inst;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext appCtx) {
		this.ctx = appCtx;
	}

	public OliConsultaEjb getOliConsultaEjb(){
		return (OliConsultaEjb)ctx.getBean("oliConsultaEjb", OliConsultaEjb.class);
	}
	
	public OliInfraestructuraEjb getOliInfraestructuraEjb(){
		return (OliInfraestructuraEjb)ctx.getBean("oliInfraestructuraEjb", OliInfraestructuraEjb.class);
	}

	public OliUsuariEjb getOliUsuariEjb(){
		return (OliUsuariEjb)ctx.getBean("oliUsuariEjb", OliUsuariEjb.class);
	}
}
