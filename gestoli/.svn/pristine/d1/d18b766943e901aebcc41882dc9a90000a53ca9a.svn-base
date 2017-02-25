/**
 * ContenidorService.java
 * 
 * Creada el 18 de maig de 2006, 16:49:55
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.dwr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * Clase encarregada de gestionar la situació dels
 * diposits a les zones
 */
public class ContenidorService {

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String seleccioSessionKey;
	private String seleccioSessionKeyOrigen;
	private String seleccioSessionKeyDesti;
	private HibernateTemplate hibernateTemplate;



	public boolean situar(Long id, Long establimentId, Long zonaId, Integer posx, Integer posy, Integer objTipus) throws RemoteException {
		try {
			// objTipus = 0 -> Diposit   #   objTipus = 1 -> Partida oliva    objTipus = 2 -> Lote aceite
			if (objTipus.intValue() == 0) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.dipositSituar(id, establimentId, zonaId, posx, posy);
			} else if (objTipus.intValue() == 1) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.partidaSituar(id, establimentId, zonaId, posx, posy);
			} else if (objTipus.intValue() == 2) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.loteSituar(id, establimentId, zonaId, posx, posy);
			} else if (objTipus.intValue() == 3) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.botaSituar(id, establimentId, zonaId, posx, posy);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void noSituar(Long id, Long establimentId, Integer objTipus) throws RemoteException {
		// objTipus = 0 -> Diposit   #   objTipus = 1 -> Partida oliva   objTipus = 2 -> Lote aceite
		if (objTipus.intValue() == 0) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.dipositSituar(id, establimentId, null, null, null);
		} else if (objTipus.intValue() == 1) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.partidaSituar(id, establimentId, null, null, null);
		}else if (objTipus.intValue() == 2) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.loteSituar(id, establimentId, null, null, null);
		}
	}
	
	public Collection seleccionar(Long id, String accio, String tipusCont, HttpSession session) throws RemoteException {
		List seleccio = null;
		String tipusContPartida= "N";
		String tipusContDiposit= "N";
		String tipusContLot= "N";
		String tipusContBota= "N";
		
		if(tipusCont!= null && tipusCont.equals("0")){
			tipusContDiposit= "S";
		}else if(tipusCont!= null && tipusCont.equals("1")){
			tipusContPartida= "S";
		}else if(tipusCont!= null && tipusCont.equals("2")){
			tipusContLot= "S";
		}else if(tipusCont!= null && tipusCont.equals("3")){
			tipusContLot= "S";
			tipusContDiposit= "S";
		}else if(tipusCont!= null && tipusCont.equals("4")){
			tipusContBota= "S";
		}		
		
		if (accio==null || accio.equalsIgnoreCase("")) seleccio = (List)session.getAttribute(seleccioSessionKey);
		else {
			if (accio.equalsIgnoreCase("ORIGEN")) seleccio = (List)session.getAttribute(seleccioSessionKeyOrigen);
			if (accio.equalsIgnoreCase("DESTI")) seleccio = (List)session.getAttribute(seleccioSessionKeyDesti);
		}
		if (seleccio == null) {
			seleccio = new ArrayList();
			if (accio==null || accio.equalsIgnoreCase("")) session.setAttribute(seleccioSessionKey, seleccio);
			else {
				if (accio.equalsIgnoreCase("ORIGEN")) session.setAttribute(seleccioSessionKeyOrigen, seleccio);
				if (accio.equalsIgnoreCase("DESTI")) session.setAttribute(seleccioSessionKeyDesti, seleccio);
			}
		}
		boolean found = false;
		for (Iterator it = seleccio.iterator(); it.hasNext();) {
			Long sid = (Long)it.next();
			if (sid.equals(id)) {
				seleccio.remove(sid);
				found = true;
				break;
			}
		}
		if (!found)
			seleccio.add(id);
		return seleccioLlistat(accio, tipusContPartida, tipusContDiposit, tipusContLot, tipusContBota, session);
			
	}
	
	public Collection seleccioLlistat(String accio, String tipusContPartida, String tipusContDiposit, String tipusContLot, String tipusContBota, HttpSession session) throws RemoteException {
		
		List seleccio = null;
		Collection collection = new ArrayList();
		if (accio==null || accio.equalsIgnoreCase("")) seleccio = (List)session.getAttribute(seleccioSessionKey);
		else {
			if (accio.equalsIgnoreCase("ORIGEN")) seleccio = (List)session.getAttribute(seleccioSessionKeyOrigen);
			if (accio.equalsIgnoreCase("DESTI")) seleccio = (List)session.getAttribute(seleccioSessionKeyDesti);
		}
		if (seleccio != null && seleccio.size() > 0){
			
			if(tipusContDiposit != null && tipusContDiposit.equalsIgnoreCase("S")){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				collection = oliInfraestructuraEjb.dipositsInfo(seleccio.toArray());
			}
			if (tipusContPartida != null && tipusContPartida.equalsIgnoreCase("S")){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				collection = oliInfraestructuraEjb.partidasInfo(seleccio.toArray());
			}
			if(tipusContLot != null && tipusContLot.equalsIgnoreCase("S")){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection collection2 = oliInfraestructuraEjb.lotesInfo(seleccio.toArray());	
				if(collection2!= null && collection2.size()>0){
					collection.addAll(collection2);						
				}
			}
			if (tipusContBota != null && tipusContBota.equalsIgnoreCase("S")){
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				collection = oliInfraestructuraEjb.botesInfo(seleccio.toArray());
			}
		}
		return collection ;		
	}

	/**
	 * Injecció de la dependència oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
     * Injecció de la dependència seleccioSessionKey
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKey(String seleccioSessionKey) {
        this.seleccioSessionKey = seleccioSessionKey;
    }
    
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }
  
    public void setSeleccioSessionKeyDesti(String seleccioSessionKeyDesti) {
        this.seleccioSessionKeyDesti = seleccioSessionKeyDesti;
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