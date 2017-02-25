package es.caib.gestoli.front.dwr;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.VarietatOliva;

public class VarietatOlivaService {

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Torna la producció màxima de la varietat depenent dels valors introduïts.
	 * @param idVarietat
	 * @param idCampanya
	 * @return
	 * @throws RemoteException
	 */
	public Double produccioMaximaVarietat(Long idVarietat, Long idCampanya, Double superficie, Integer oliveres) throws RemoteException {
		Long campanyaId = idCampanya;
		if (idCampanya == null) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			campanyaId = oliInfraestructuraEjb.campanyaCercaActual();
		}
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		String tipusRendiment = oliInfraestructuraEjb.obtenirTipusRendimentCampanyaVarietat(campanyaId, idVarietat.intValue());
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Double rendiment = oliInfraestructuraEjb.obtenirRendimentCampanyaVarietat(campanyaId, idVarietat.intValue());
		
		Double maxProduccio = 0D;
		if (tipusRendiment.equals("hectarea")) {
			if (superficie != null) maxProduccio = rendiment * superficie;
		} else if (tipusRendiment.equals("arbre")) {
			if (oliveres != null) maxProduccio = rendiment * oliveres;
		}
		return maxProduccio;
	}
	
	/**
	 * Torna les quantitats que s'han d'utilitzar de cada varietat.
	 * @param quantitat
	 * @param plantacions
	 * @return
	 * @throws RemoteException
	 */
	public Collection calculaPercentatgeMescla(Integer quantitat, String plantacions) throws RemoteException {
		List<Object[]> percentatges = new ArrayList<Object[]>();
		
		Double totalRestant = 0D;
		List<DescomposicioPlantacio> descomp = new ArrayList<DescomposicioPlantacio>();
		String[] plants = plantacions.split(",");
		if ((!plantacions.equals("")) && (plants.length > 0)) {
			for (int i = 0; i < plants.length; i++) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection varietats = oliInfraestructuraEjb.cercaVarietatsOlivaPerPlantacions(plants[i]);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection descomposicions = oliInfraestructuraEjb.descomposicioPlantacioAmbIdPlantacio(new Long(plants[i]));
				
				for (Object obj : varietats) {
					Object[] object = (Object[])obj;
					VarietatOliva vo = (VarietatOliva)object[0];
					if (vo.getActiu()) {
						for (Object o : descomposicions) {
							DescomposicioPlantacio dp = (DescomposicioPlantacio)o;
							if ((vo.getId().longValue() == dp.getVarietatOliva().getId().longValue()) && (dp.getVarietatOliva().getActiu())) {
								totalRestant += dp.getProduccioRestant();
								descomp.add(dp);
							}
						}
					}
				}
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		Double quantitatAux = 0D;
		for (int n = 0; n < descomp.size(); n++) {
			Double percent = 0.0;
			if (totalRestant != 0){
				percent = ((descomp.get(n).getProduccioRestant() * quantitat) / totalRestant);
			}
			String percentFormat = df.format(percent).replaceAll(",", ".");
			quantitatAux += Double.parseDouble(percentFormat);
			Object[] total = new Object[4];
			total[0] = n;
			// Si estam al darrer registre i les quantitats són diferents, sumam la diferència.
			if ((n == (descomp.size() - 1)) && (quantitat.doubleValue() != quantitatAux.doubleValue())) {
				total[1] = df.format(Double.parseDouble(percentFormat) + (quantitat.doubleValue() - quantitatAux)).replaceAll(",", ".");
			} else {
				total[1] = percentFormat;
			}
			total[2] = descomp.get(n).getPlantacio().getId();
			total[3] = descomp.size();
		
			percentatges.add(total);
		}
		
		return percentatges;
	}
	
	/**
	 * Injecció de la dependència oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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
