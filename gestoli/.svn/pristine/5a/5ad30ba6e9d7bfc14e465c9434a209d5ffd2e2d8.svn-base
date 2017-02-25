package es.caib.gestoli.front.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Permite obtener servicios (beans) spring en cualquier entorno
 * Es necesario configurar el bean en spring, claro
 * @author agarcia
 *
 */
public class ApplicationContextHolder implements ApplicationContextAware
{
    private static BeanFactory factory;
    private static ApplicationContextHolder context;

    private ApplicationContextHolder() {}
    
    public static ApplicationContextHolder getInstance ()
    {
        if ( context == null )
        	context = new ApplicationContextHolder ();
        return context;
    }

    public void setApplicationContext ( ApplicationContext applicationContext )
            throws BeansException
    {
    	factory = applicationContext;
    }

    public Object getService ( String serviceName )
    {
        return factory.getBean ( serviceName );
    }

}