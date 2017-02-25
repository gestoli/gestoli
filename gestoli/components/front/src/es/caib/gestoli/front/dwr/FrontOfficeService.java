package es.caib.gestoli.front.dwr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.front.spring.EtiquetatgeCommand;
import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.util.TrazabilitatResumida;

public class FrontOfficeService {

	private OliFrontOfficeEjb oliFrontOfficeEjb;
	private HibernateTemplate hibernateTemplate;
	
	public Lot getInformacioOli(String lletres, String numeros) throws RemoteException {
		try{
			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
			Lot lot = oliFrontOfficeEjb.findEtiquetesLotByEtiqueta(lletres, Integer.valueOf(numeros)).getLot();
			
			//TrazabilitatResumida trazabilitat = oliFrontOfficeEjb.trazabilitatResumidaOliDisponibleLote(lot.getId());
    		//myModel.put("trazabilitat", trazabilitat.getArbre());
    		
			return lot;
		} catch (Exception e){
			return null;
		}
	}
	
	/**
	 * Retorna totes les marques d'una envassadora donada.
	 * @param envassadoraId
	 * @return
	 * @throws RemoteException
	 */
	public Collection marquesAmbEnvassadora(Long envassadoraId) throws RemoteException {
		List<Marca> marques = new ArrayList<Marca>();
		Marca marcaVacia = new Marca();
		marcaVacia.setId(Long.valueOf(0));
		marcaVacia.setNom("- - - - -");
		marques.add(marcaVacia);
		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
		marques.addAll(oliFrontOfficeEjb.marquesCercaPerEstabliment(envassadoraId));
		
		return marques;
	}
	
	public List etiquetajesConMarca(Long marcaId) throws RemoteException {
		Collection etiquetajes  = null;
		List etiquetajesAux  = null;
		if (marcaId != Long.valueOf("0")) { // Si se ha escogido marca, listamos sus etiquetajes
			try {
				oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
				etiquetajes = oliFrontOfficeEjb.etiquetatgeByMarca(marcaId);
				if (etiquetajes != null) {
					etiquetajesAux = new ArrayList();
	    			
					EtiquetatgeCommand etiquetatgeVacia = new EtiquetatgeCommand();
					etiquetatgeVacia.setId(Long.valueOf(0));
					etiquetatgeVacia.setNom("- - - - -");
					etiquetajesAux.add(etiquetatgeVacia);
					
					EtiquetatgeCommand etiquetatgeCommandAux = null;
	    			for (Iterator it = etiquetajes.iterator(); it.hasNext();) {	    			
	    				Etiquetatge etiquetatge = (Etiquetatge)it.next();
	    				etiquetatgeCommandAux = new EtiquetatgeCommand();
	    				etiquetatgeCommandAux.setId(etiquetatge.getId());
	    				
	    				String observacions = "";
	    				if(etiquetatge.getObservacions() != null && !etiquetatge.getObservacions().equals(""))
	    					observacions = " - " + etiquetatge.getObservacions();
	    				
	    				etiquetatgeCommandAux.setNom(etiquetatge.getTipusEnvas().getVolum() + " - " + etiquetatge.getTipusEnvas().getColor().getNom() + " - " + etiquetatge.getTipusEnvas().getMaterialTipusEnvas().getNom()  +  observacions);
//	    				etiquetatgeCommandAux.setTipusEnvas(etiquetatge.getTipusEnvas());
	    				etiquetajesAux.add(etiquetatgeCommandAux);
	    			}
	    		}
			} catch (Exception ignored) {}
		} else { // Si no ha escogido marca, aÃƒÂ±adimos mensaje de que seleccione una.
			etiquetajesAux = new ArrayList();
			
			EtiquetatgeCommand etiquetatgeVacia = new EtiquetatgeCommand();
			etiquetatgeVacia.setId(Long.valueOf(0));
			etiquetatgeVacia.setNom("- - - - -");
			etiquetajesAux.add(etiquetatgeVacia);
		}
		return etiquetajesAux;
	}
	
	/**
	 * Retorna totes les partides d'un etiquetatge donat.
	 * @param envassadoraId
	 * @return
	 * @throws RemoteException
	 */
	public Collection partidaAmbEtiquetatge(Long etiquetatgeId) throws RemoteException {
		List<PartidaOli> partides = new ArrayList<PartidaOli>();

		PartidaOli partidaVacia = new PartidaOli();
		partidaVacia.setId(Long.valueOf(0));
		partidaVacia.setNom("- - - - -");
		partides.add(partidaVacia);
		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
		partides.addAll(oliFrontOfficeEjb.partidaByEtiquetatge(etiquetatgeId));
		
		return partides;
	}
	
	public List trazabilitatAmbPartida(Long partidaId, Long etiquetatgeId) throws RemoteException {
		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
		List lots = oliFrontOfficeEjb.lotByPartidaEtiquetatge(partidaId, etiquetatgeId);
		
		List trazabilitats = new ArrayList();
		for (Object lot : lots){
			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
			TrazabilitatResumida trazabilitat = oliFrontOfficeEjb.trazabilitatResumidaOliDisponibleLote(((Lot)lot).getId().toString());
			
			trazabilitats.add(trazabilitat.getArbre());
		}
		
		return trazabilitats;
	}

	
	/**
	 * Injecció de la dependència OliFrontOfficeEjb
	 * @param OliFrontOfficeEjb La classe a injectar.
	 */
	public void setOliFrontOfficeEjb(OliFrontOfficeEjb oliFrontOfficeEjb) {
		this.oliFrontOfficeEjb = oliFrontOfficeEjb;
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
}
