package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Factura;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class FacturaDao {
	private static Logger logger = Logger.getLogger(FacturaDao.class);
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
			col = getHibernateTemplate().find("from Factura as fac order by fac.data"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}


	/**
	 * Recupera una factura por Id de olivicultor
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Factura findByOlivicultorId(Long id){
		List factures;
		try {
			logger.debug("findByOlivicultorId ini");
			String q = "from Factura as fac where fac.olivicultor.id = '"+id+"'";
			factures = getHibernateTemplate().find(q);
			if (factures.size() > 0) {
				logger.debug("findByOlivicultorId fin");
				return (Factura)factures.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByOlivicultorId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByOlivicultorId fin");
		return null;	
	}
	
	/**
	 * Recupera una factura por Id de olivicultor
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Factura findByAnyOlivicultorId(int any, Long id){
		List factures;
		try {
			logger.debug("findByAnyOlivicultorId ini");
			String q = "from Factura as fac where fac.olivicultor.id = '"+id+"' and fac.any = " + any;
			factures = getHibernateTemplate().find(q);
			if (factures.size() > 0) {
				logger.debug("findByAnyOlivicultorId fin");
				return (Factura)factures.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByAnyOlivicultorId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByAnyOlivicultorId fin");
		return null;	
	}


	/**
	 * Recupera las facturas de la campaña actual
	 * @param campanyaId
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection facturaFindByCampanyaAltaDOCartilla(Long campanyaId)throws InfrastructureException {
		Collection col;
		try {
			logger.debug("facturaFindByCampanyaAltaDOCartilla ini");
			col = getHibernateTemplate().find("from Olivicultor oli where oli.altaDO = true and oli.cartilla = true and oli.campanya.id = "+ campanyaId +" and oli.id in (select fac.olivicultor.id from Factura as fac) order by oli.codiDO desc, oli.codiDOExperimental desc"); 
		} catch (HibernateException ex) {
			logger.error("facturaFindByCampanyaAltaDOCartilla failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("facturaFindByCampanyaAltaDOCartilla fin");
		return col; 	
	}	


	/**
	 * Comprueba si existe la factura
	 * @param anyo
	 * @throws InfrastructureException
	 */
	public boolean facturaExisteByOliId(Long id){
		List factures;
		try {
			logger.debug("existeFacturaByOliId ini");
			String q = "select count(*) from Factura as fac where fac.olivicultor.id="+id.toString();
			factures = getHibernateTemplate().find(q);
			if (factures.size() > 0) {
				int res = ((Long)factures.get(0)).intValue();
				if (res>0){
					logger.debug("existeFacturaByOliId fin");
					return true;
				}else{
					logger.debug("existeFacturaByOliId fin");
					return false;
				}
			}
		} catch (HibernateException ex) {
			logger.error("existeFacturaByOliId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existeFacturaByOliId fin");
		return false;			
	}


	/**
	 * Obtiene la numeracion maxima para ese anyo
	 * @param anyo
	 * @throws InfrastructureException
	 */
	public Integer getMaxNumeracionParaAnyo(Integer any){
		List factures;
		try {
			logger.debug("getMaxNumeracionParaAnyo ini");
			String q = "select max(fac.numero) from Factura as fac where fac.any="+any.toString();
			factures = getHibernateTemplate().find(q);
			if (factures.size() > 0) {
				Integer res = (Integer)factures.get(0);
				if (res==null){
					res=new Integer(0);
				}
				logger.debug("getMaxNumeracionParaAnyo fin");
				return res;
			}
		} catch (HibernateException ex) {
			logger.error("getMaxNumeracionParaAnyo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getMaxNumeracionParaAnyo fin");
		return null;			

	}


	/**
	 * Actualiza un registro
	 * @param factura
	 * @throws InfrastructureException
	 */
	public void makePersistent(Factura factura) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(factura);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


//	/**
//	 * Actualiza un registro
//	 * @param factura
//	 * @throws InfrastructureException
//	 */
//	public void makePersistent(Factura factura, Session session) throws InfrastructureException {
//		try {
//			session.saveOrUpdate(factura);
//			logger.debug("makePersistent fin");
//		} catch (HibernateException ex) {
//			logger.error("makePersistent failed", ex);
//			throw new InfrastructureException(ex);
//		}
//	}


	/**
	 * Borra un registro
	 * @param factura
	 * @throws InfrastructureException
	 */
	public void makeTransient(Factura factura)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(factura);
			getHibernateTemplate().flush();
			logger.debug("makeTransient ini");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
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
