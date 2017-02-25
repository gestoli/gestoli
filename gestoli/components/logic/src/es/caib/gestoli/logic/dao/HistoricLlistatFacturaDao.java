package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.HistoricLlistatFactura;

/**
 * Home object for domain model class HistoricLlistatFactura
 * @see es.caib.gestoli.logic.model.HistoricLlistatFactura
 * 
 */
public class HistoricLlistatFacturaDao {

	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public HistoricLlistatFactura getById(Long id) throws InfrastructureException {
		try {
			String q = "from HistoricLlistatFactura where id = " + id;
			List historics = getHibernateTemplate().find(q);
			if (historics.size() > 0)
				return (HistoricLlistatFactura)historics.get(0);

		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return null;		
	}
	
	public HistoricLlistatFactura findHistoricLlistatFacturaPreformaByOlivicultorAny(String idOlivicultor, String anyActual) {
			try {
				String q = 	" from HistoricLlistatFactura " +
							"where idOlivicultor = '" + idOlivicultor + "' " +
							"  and campanya = '" + anyActual + "' " +
							"  and detallProformaEtiqueta is not null";
				List historics = getHibernateTemplate().find(q);
				if (historics.size() > 0) return (HistoricLlistatFactura)historics.get(0);
			} catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			}
			return null;
	}
	
	public HistoricLlistatFactura findHistoricLlistatFacturaByOlivicultorAny(String idOlivicultor, String anyActual) {
		try {
			String q = 	" from HistoricLlistatFactura " +
						"where idOlivicultor = '" + idOlivicultor + "' " +
						"  and campanya = '" + anyActual + "' " +
						"  and facturaEtiqueta is not null";
			List historics = getHibernateTemplate().find(q);
			if (historics.size() > 0) return (HistoricLlistatFactura)historics.get(0);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return null;
}

	/**
	 * Actualitza o crea un nou registre d'històric de llistat de factures.
	 * @param historicLlistatFactura
	 */
	public void actualitzaLlistatFactura(HistoricLlistatFactura historicLlistatFactura) {
		this.makePersistent(historicLlistatFactura);
	}
	
	/**
	 * Obté l'identificador per la factura.
	 */
	public Long identificadorFactura() {
		Long ident = 0L;
		String q = "select max(identificador) " +
					"from HistoricLlistatFactura";
		List identificadors = getHibernateTemplate().find(q);
		if (identificadors.size() > 0) {
			Object tmp = identificadors.get(0);
			if (tmp != null) {
				ident = (Long)tmp + 1;
			}
		}
		return ident;
	}
	
	/**
	 * Actualiza un registro
	 * @param HistoricLlistatFactura
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricLlistatFactura historicLlistatFactura) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if (historicLlistatFactura.getId() != null) {
				getHibernateTemplate().evict(this.getById(historicLlistatFactura.getId()));
				getHibernateTemplate().update(historicLlistatFactura);
			} else {
				getHibernateTemplate().save(historicLlistatFactura);
			}
			
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Borra un registro
	 * @param historicLlistatFactura
	 * @throws InfrastructureException
	 */
	public void makeTransient(HistoricLlistatFactura historicLlistatFactura) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(historicLlistatFactura);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find("from HistoricLlistatFactura order by id desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera les diferentes factures que es poden consultar.
	 * @return
	 * @throws InfrastructureException
	 */
	//and ho.hol_codoli = regexp_replace(f.hlf_idolivic,'[^0-9]+','', 'g')::int
	public Collection findHistoricFactures() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"select hf.campanya, hf.data, hf.identificador, hf.idOlivicultor, hf.dadesOlivicultor, hf.factura, hf.total, oliv.cartilla, hf.inscripcio " +
					"from HistoricLlistatFactura hf, Olivicultor oliv " +
					"where hf.facturaEtiqueta not like '' " +
					"and cast(oliv.id as string) = regexp_replace(hf.idOlivicultor,'[^0-9]+','', 'g') " +
					"and hf.id in (select max(hf2.id) " +
					"			from HistoricLlistatFactura hf2" +
					"			where hf2.facturaEtiqueta not like '' " +
					"			group by hf2.campanya, hf2.idOlivicultor)" +
					"group by hf.campanya, hf.data, hf.identificador, hf.idOlivicultor, hf.dadesOlivicultor, hf.factura, hf.total, oliv.cartilla, hf.inscripcio " +
					"order by hf.data, hf.identificador desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera les dades de la fatura per l'identificador donat.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findDadesPerIdntificador(Long identificador) throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"from HistoricLlistatFactura " +
					"where identificador = " + identificador + " " +
					"order by id asc ");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
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
	
	private static Logger logger = Logger.getLogger(HistoricLlistatFacturaDao.class);
}
