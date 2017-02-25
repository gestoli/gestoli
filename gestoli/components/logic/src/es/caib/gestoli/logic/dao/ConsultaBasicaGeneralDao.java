package es.caib.gestoli.logic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.ConsultaBasicaGeneral;


/**
 * Home object for domain model class SortidaLot
 * @see es.caib.gestoli.logic.model.SortidaLot
 * @author Carlos Perez Claro
 */
public class ConsultaBasicaGeneralDao {
	private static Logger logger = Logger.getLogger(ConsultaBasicaGeneralDao.class);
	private HibernateTemplate hibernateTemplate;


	public Collection getConsultaGeneral(ConsultaBasicaGeneral cmd, Long establimentId) throws InfrastructureException{
		
		List<ConsultaBasicaGeneral> consultes = new ArrayList<ConsultaBasicaGeneral>();
		try{
			 
			ArrayList camps = new ArrayList();
			String qr =	"select "; 
			
			if("S".equals(cmd.getLotNomC())){
				qr +=" l.codiAssignat ";
				camps.add("LotNom");
			}
			
			if("S".equals(cmd.getPartidaNomC())){
				qr +=" , po.nom ";
				camps.add("Partida");
			}
			if("S".equals(cmd.getZonaC())){
				qr +=" , z.nom ";
				camps.add("Zona");
			}
			
			
			if("select ".equals(qr)){
				qr = "select * ";
			}
			qr+= "from Lot l ";
			qr+= cmd.getPartidaNomC()!=null? " left join  l.partidaOli po ":"";
			qr+= (cmd.getZonaC()!=null && cmd.getLotNomC()!=null)? " left join  l.zona.id z ":"";
			qr+= " where l.id is not null ";
			qr+= cmd.getPartidaNomC().equals("S")?" and l.partidaOli.id = po.id ":"";
			qr+= (cmd.getPartidaNom()!=null && !cmd.getPartidaNom().equals(""))? " and LOWER(po.nom) like LOWER('%"+ cmd.getPartidaNom() +"%') ":"";
			qr+= cmd.getPartidaNom()!=null? " and po.esActiu = true ":"";
			qr+= (cmd.getLotNom()!=null && !cmd.getLotNom().equals(""))? " and LOWER(l.codiAssignat) like LOWER('%"+ cmd.getLotNom() +"%') ":"";
			qr+= (cmd.getZonaC()!=null && cmd.getLotNomC()!=null)? " and z.id = l.zona.id ":"";
			qr+= " and po.establiment.id = '" + establimentId + "'" ;
	//		qr+= (command.getPartidaNom()!=null && !command.getPartidaNom().equals(""))?" and LOWER(lot.partidaVi.nom) like LOWER('%"+ command.getPartidaNom() +"%') ":"";
	//		String groupBy = " group by lot.id, cont.id, lot.partidaVi.id ";
	//		groupBy+= command.getPartidaNom()!=null?", pv.nom ":" ";
			Collection resultat = getHibernateTemplate().find(qr);
	//		query.setParameter("dataAvui", dataAvui);
			
	//
			for(Object obj:resultat){
				ConsultaBasicaGeneral consultaGeneral = new ConsultaBasicaGeneral();
				Object[] fila = (Object[])obj;
				
				if(cmd.getPartidaNom()!=null){
					consultaGeneral.setPartidaNom((String)fila[camps.indexOf("Partida")]);
				}
				if(cmd.getLotNom()!=null){
					consultaGeneral.setLotNom((String)fila[camps.indexOf("LotNom")]);
				}
				if(cmd.getZona()!=null){
					consultaGeneral.setZona((String)fila[camps.indexOf("Zona")]);
				}
				consultes.add(consultaGeneral);
			}
		
		}catch (Exception e){
			e.getStackTrace();
		}
		return consultes;
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