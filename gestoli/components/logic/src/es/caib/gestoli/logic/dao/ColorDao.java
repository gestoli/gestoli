package es.caib.gestoli.logic.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Color;


/**
 * Home object for domain model class Color
 * @see es.caib.gestoli.logic.model.Color
 * @author Oriol Barnés
 */
public class ColorDao {
	private static Logger logger = Logger.getLogger(IdiomaDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from Color as co order by co.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Recupera numero de colores (util para el OliInitDBEjbBean)
	 * @return
	 * @throws InfrastructureException
	 */
	public Long numeroColors() throws InfrastructureException {
		Collection col;
		Long numero = null;
		try {
			logger.debug("numeroColors ini");
			col = getHibernateTemplate().find("select count(col.id) from Color as col");
			while (col.iterator().hasNext()){
				numero = (Long)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("numeroColors failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("numeroColors fin");
		return numero;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Color getById(Integer id) throws InfrastructureException {
		Color color;
		try {
			logger.debug("getById ini");
			color = (Color)getHibernateTemplate().load(Color.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return color;
	}


	/**
	 * Actualiza un registro
	 * @param color
	 * @throws InfrastructureException
	 */
	public void makePersistent(Color color) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(color);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 *
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
