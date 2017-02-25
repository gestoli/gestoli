package es.caib.gestoli.front.dwr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.QualitatPuestoTreball;

public class QualitatService {

	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;

	
	
	/**
	 * Torna els càrrecs superiors del nivell actual.
	 * @param idEstabliment
	 * @param nivell
	 * @return
	 * @throws RemoteException
	 */
	public Collection getCarrecsSuperiors(Long idEstabliment, Integer nivell) throws RemoteException {

		List carrecs = new ArrayList();
        if ((idEstabliment != null) && (nivell != null) && (nivell > 1)){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Iterator itCarrecs = oliQualitatEjb.puestoCercaTotsPerNivell(idEstabliment, nivell-1).iterator();
        	
			//itCarrecs = oliQualitatEjb.puestoCercaTotsPerEstabliment(est.getId()).iterator();

        	while (itCarrecs.hasNext()) {
        		QualitatPuestoTreball carrec = (QualitatPuestoTreball) itCarrecs.next();
        		BasicData basic = new BasicData();
        		basic.setId(String.valueOf(carrec.getId()));
        		basic.setNom(carrec.getCarrec());
        		carrecs.add(basic);
        	}
        }
        
        return carrecs;
        
	}
	
	
	/**
	 * Injecció de la dependència OliQualitatEjb
	 * @param OliQualitatEjb La classe a injectar.
	 */
	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
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
