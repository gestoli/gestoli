package es.caib.gestoli.logic.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class Color
 * @see es.caib.gestoli.logic.model.Color
 * @author Oriol Barnés
 */
public class CategoriaOliDao {
	private static Logger logger = Logger.getLogger(CategoriaOliDao.class);
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
			col = getHibernateTemplate().find("from CategoriaOli as co order by co.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public CategoriaOli getById(Integer id) throws InfrastructureException {
		CategoriaOli categoria;
		try {
			logger.debug("getById ini");
			categoria = (CategoriaOli)getHibernateTemplate().load(CategoriaOli.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return categoria;
	}


	/**
	 * Actualiza un registro
	 * @param color
	 * @throws InfrastructureException
	 */
	public void makePersistent(CategoriaOli categoria) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(categoria);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	public CategoriaOli categoriaResultantMescla(CategoriaOli cat1, CategoriaOli cat2) {
		if(cat1 == null) return cat2;
		if(cat2 == null) return cat1;
		
		if(cat1.getId().equals(Constants.CATEGORIA_NO_DO)) return cat1;
		if(cat2.getId().equals(Constants.CATEGORIA_NO_DO)) return cat2;
		
		if(cat1.getId().equals(Constants.CATEGORIA_PENDENT)) return cat1;
		if(cat2.getId().equals(Constants.CATEGORIA_PENDENT)) return cat2;
		
		if(cat1.getId().equals(Constants.CATEGORIA_DO)) return cat1;
		if(cat2.getId().equals(Constants.CATEGORIA_DO)) return cat2;
		
		return null;
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
