/**
 * ProcessosService.java
 * 
 */
package es.caib.gestoli.front.dwr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.front.spring.EtiquetatgeCommand;
import es.caib.gestoli.front.spring.PlantacioCommand;
import es.caib.gestoli.front.spring.VarietatOlivaCommand;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.util.Constants;


/**
 * Clase encarregada de gestionar les funcions AJAX
 * pels processos
 * 
 */
public class ProcessosService {

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private HibernateTemplate hibernateTemplate;
	
	private String acidesaVergeExtra;
	private String acidesaVerge;

	public List plantacionesConFinca(Long fincaId, Long idOlivicultor, String idioma) throws RemoteException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(idioma));
		Collection plantaciones  = null;
		List plantacionesAux  = null;
		if (fincaId != null ) {
			try {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				plantaciones = oliInfraestructuraEjb.plantacionesActivasConDescomposicionesByFinca(fincaId);
				if(plantaciones!= null){
	    			plantacionesAux = new ArrayList();
	    			PlantacioCommand plantacionesDesconocida = new PlantacioCommand();
	    			plantacionesDesconocida.setNom(bundle.getString("proces.entradaOliva.camp.fincas.desconocido"));
	    			plantacionesDesconocida.setId(Long.valueOf("-1"));
	    			plantacionesAux.add(plantacionesDesconocida);
	    			
	    			PlantacioCommand plantacionCommandAux = null;
	    			for (Iterator it = plantaciones.iterator(); it.hasNext();) {	    			
	    				Plantacio plantacio = (Plantacio)it.next();
	    				plantacionCommandAux = new PlantacioCommand();
	    				plantacionCommandAux.setId(plantacio.getId());
	    				plantacionCommandAux.setNom(plantacio.getMunicipi().getNom()+" - "+plantacio.getPoligon()+" - "+plantacio.getParcela());
	    				plantacionesAux.add(plantacionCommandAux);
	    			}    			
	    		}
			} catch (Exception ignored) {}
		}else{
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				plantaciones = oliInfraestructuraEjb.plantacioActivasConDescomposicionesByIdOlivicultor(idOlivicultor);
               	if(plantaciones!= null){
	    			plantacionesAux = new ArrayList();
	    			
	    			PlantacioCommand plantacionesVacia = new PlantacioCommand();
	    			plantacionesVacia.setNom("- - - - -");
	    			plantacionesAux.add(plantacionesVacia);	    			
	    			
	    			PlantacioCommand plantacionesDesconocida = new PlantacioCommand();
	    			plantacionesDesconocida.setNom(bundle.getString("proces.entradaOliva.camp.fincas.desconocido"));
	    			plantacionesDesconocida.setId(Long.valueOf("-1"));
	    			plantacionesAux.add(plantacionesDesconocida);
	    			
	    			PlantacioCommand plantacionCommandAux = null;
	    			for (Iterator it = plantaciones.iterator(); it.hasNext();) {	    			
	    				Plantacio plantacio = (Plantacio)it.next();
	    				plantacionCommandAux = new PlantacioCommand();
	    				plantacionCommandAux.setId(plantacio.getId());
	    				plantacionCommandAux.setNom(plantacio.getMunicipi().getNom()+" - "+plantacio.getPoligon()+" - "+plantacio.getParcela());
	    				plantacionesAux.add(plantacionCommandAux);
	    			}		
	    		}
		}
		return plantacionesAux;
	}
	
	public List etiquetajesConMarca(Long marcaId, String idioma) throws RemoteException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(idioma));
		Collection etiquetajes  = null;
		List etiquetajesAux  = null;
		if (marcaId != null) { // Si se ha escogido marca, listamos sus etiquetajes
			try {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				etiquetajes = oliInfraestructuraEjb.etiquetatgeByMarca(marcaId);
				if (etiquetajes != null) {
					etiquetajesAux = new ArrayList();
	    			
					EtiquetatgeCommand etiquetatgeVacia = new EtiquetatgeCommand();
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
			etiquetatgeVacia.setNom("- - - - -");
			etiquetajesAux.add(etiquetatgeVacia);
		}
		return etiquetajesAux;
	}
	
	public String getVolumEtiquetatge(Long etiquetatgeId) throws RemoteException {
		String volum = "";
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Etiquetatge etiquetatge = oliInfraestructuraEjb.etiquetatgeAmbId(etiquetatgeId);
		
		if (etiquetatge.getTipusEnvas().getVolum() != null) {
			volum = etiquetatge.getTipusEnvas().getVolum().toString();
		}
		
		return volum;
	}
	
	public List variedadesConPlantacion( Long fincaId, Long plantacionId,Long idOlivicultor) throws RemoteException {
		List variedades = new ArrayList();
		List variedadesPosibles = new ArrayList();
		List variedadesNoPosibles = new ArrayList();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		List variedadesList = oliInfraestructuraEjb.varietatsOlivaByOlivicultorPlantacio(idOlivicultor, plantacionId);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection variedadesDePlantacion = null;
		if(plantacionId!= null){
			variedadesDePlantacion = oliInfraestructuraEjb.descomposicioPlantacioAmbIdPlantacio(plantacionId);
		}
		else{
			plantacionId = Long.valueOf("-1");
		}
		
		if(variedadesDePlantacion != null && variedadesDePlantacion.size()>0){
			for (Iterator it = variedadesDePlantacion.iterator(); it.hasNext();) {	
				DescomposicioPlantacio descomp = (DescomposicioPlantacio) it.next();
				VarietatOliva vo = (VarietatOliva)descomp.getVarietatOliva();
				String nombreVariedad = "";
				if (vo.getNomVarietat() != null) {
					nombreVariedad = vo.getNomVarietat();
				} else {
					nombreVariedad = vo.getNom();
				}
				variedadesPosibles.add(nombreVariedad);
			}
		}else if(plantacionId.intValue() == -1){// Si plantacion desconocida
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if(fincaId!= null){
				variedadesDePlantacion = oliInfraestructuraEjb.getDescPlantacioActivasAmbFinca(fincaId);
			}			
			if(variedadesDePlantacion != null && variedadesDePlantacion.size()==0){
				for(int i=0;i<variedadesList.size();i++){
					VarietatOliva vo = (VarietatOliva)variedadesList.get(i);
					String nombreVariedad = "";
					if (vo.getNomVarietat() != null) {
						nombreVariedad = vo.getNomVarietat();
					} else {
						nombreVariedad = vo.getNom();
					}
					variedadesPosibles.add(nombreVariedad);				
				}
			}else if(variedadesDePlantacion != null && variedadesDePlantacion.size()>0){
				for (Iterator it = variedadesDePlantacion.iterator(); it.hasNext();) {	
					DescomposicioPlantacio descomp = (DescomposicioPlantacio) it.next();
					VarietatOliva vo = (VarietatOliva)descomp.getVarietatOliva();
					String nombreVariedad = "";
					if (vo.getNomVarietat() != null) {
						nombreVariedad = vo.getNomVarietat();
					} else {
						nombreVariedad = vo.getNom();
					}
					variedadesPosibles.add(nombreVariedad);
				}				
			}else{
				for(int i=0;i<variedadesList.size();i++){
					VarietatOliva vo = (VarietatOliva)variedadesList.get(i);
					String nombreVariedad = "";
					if (vo.getNomVarietat() != null) {
						nombreVariedad = vo.getNomVarietat();
					} else {
						nombreVariedad = vo.getNom();
					}
					variedadesPosibles.add(nombreVariedad);				
				}
			}
		}
				
		for(int i=0;i<variedadesList.size();i++){
			VarietatOliva vo = (VarietatOliva)variedadesList.get(i);
			String nombreVariedad = "";
			if (vo.getNomVarietat() != null) {
				nombreVariedad = vo.getNomVarietat();
			} else {
				nombreVariedad = vo.getNom();
			}
			if(nombreVariedad!= null && !nombreVariedad.equals("") && !variedadesPosibles.contains(nombreVariedad)){
				variedadesNoPosibles.add(nombreVariedad);
			}
		}
		
		variedades.add(variedadesPosibles);
		variedades.add(variedadesNoPosibles);

		return variedades;
	}
	
	public List variedadesConFinca( Long fincaId,Long idOlivicultor) throws RemoteException {
		List variedades = new ArrayList();
		List variedadesPosibles = new ArrayList();
		List variedadesNoPosibles = new ArrayList();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		List variedadesList = oliInfraestructuraEjb.varietatsOlivaByOlivicultor(idOlivicultor);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection variedadesDePlantacion = oliInfraestructuraEjb.getDescPlantacioActivasAmbFinca(fincaId);
		
		if(variedadesDePlantacion != null && variedadesDePlantacion.size()>0){
			for (Iterator it = variedadesDePlantacion.iterator(); it.hasNext();) {	
				DescomposicioPlantacio descomp = (DescomposicioPlantacio) it.next();
				variedadesPosibles.add(descomp.getVarietatOliva().getNom());
			}
		}else{
			for(int i=0;i<variedadesList.size();i++){
				String nombreVariedad = ((VarietatOliva)variedadesList.get(i)).getNom();
				variedadesPosibles.add(nombreVariedad);				
			}
		}
		
		for(int i=0;i<variedadesList.size();i++){
			String nombreVariedad = ((VarietatOliva)variedadesList.get(i)).getNom();
			if(nombreVariedad!= null && !nombreVariedad.equals("") && !variedadesPosibles.contains(nombreVariedad)){
				variedadesNoPosibles.add(nombreVariedad);
			}
		}
		
		variedades.add(variedadesPosibles);
		variedades.add(variedadesNoPosibles);

		return variedades;
	}
	
	public List variedades( boolean mezcla, Long fincaId, Long plantacionId,Long idOlivicultor) throws RemoteException {
		List variedades = new ArrayList();
		if(mezcla){
			List variedadesPosibles = new ArrayList();
			List variedadesNoPosibles = new ArrayList();
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			List variedadesList = oliInfraestructuraEjb.varietatsOlivaByOlivicultor(idOlivicultor);
			
			for(int i=0;i<variedadesList.size();i++){
				String nombreVariedad = ((VarietatOliva)variedadesList.get(i)).getNom();
				variedadesNoPosibles.add(nombreVariedad);				
			}
			
			variedades.add(variedadesPosibles);
			variedades.add(variedadesNoPosibles);
			
		}else if (plantacionId != null){
			variedades = variedadesConPlantacion (fincaId, plantacionId,idOlivicultor);
		}else if (fincaId != null){
			variedades = variedadesConFinca (fincaId, idOlivicultor);
		}else{
			List variedadesPosibles = new ArrayList();
			List variedadesNoPosibles = new ArrayList();
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			List variedadesList = oliInfraestructuraEjb.varietatsOlivaByOlivicultor(idOlivicultor);
			
			for(int i=0;i<variedadesList.size();i++){
				String nombreVariedad = ((VarietatOliva)variedadesList.get(i)).getNom();
				variedadesPosibles.add(nombreVariedad);				
			}
			
			variedades.add(variedadesPosibles);
			variedades.add(variedadesNoPosibles);
		}

		return variedades;
	}
	
	
	public List calculoMezcla( Long fincaId, Long plantacionId, Long idOlivicultor, Double cantidadTotalOliva, String idioma) throws RemoteException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(idioma));
		List variedades = new ArrayList();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		List variedadesList = oliInfraestructuraEjb.varietatsOlivaByOlivicultor(idOlivicultor);
		
		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
		HashMap kgsAcumulatsMap = oliProcessosEjb.findkgsAcumulatsDescomposicioPartidaOlivaPerOlivicultor(idOlivicultor);
		HashMap kgsPosibles = new HashMap();
		boolean existeAlgunaVariedadExperimental = false;
		
		//CASO 1: PLANTACION CONOCIDA
		if(plantacionId!= null && plantacionId.intValue() != -1 ){
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection descomposiciones = oliInfraestructuraEjb.descomposicioPlantacioAmbIdPlantacio(plantacionId);
			
			double kgacu = 0;
			double totalKgsPosibles = 0;
			for (Iterator it = descomposiciones.iterator(); it.hasNext();) {	    			
				DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)it.next();
				VarietatOliva varietatOliva = descomposicionPlantacio.getVarietatOliva();
				if(!varietatOliva.getAutoritzada().booleanValue()){//Si la variedad es experimental
					existeAlgunaVariedadExperimental = true;
				}
				if(kgsAcumulatsMap.get(descomposicionPlantacio.getId())!= null && varietatOliva.getAutoritzada().booleanValue()){
					 kgacu = ((Double)kgsAcumulatsMap.get(descomposicionPlantacio.getId()) ).doubleValue();
				 }
				
				double posible = descomposicionPlantacio.getMaxProduccio().doubleValue() - kgacu;
				if(posible<0){// NUNCA SE DARA , SOLO SI SE HA TOCADO LA BBDD A MANO
					posible = 0;
				}
				kgsPosibles.put(descomposicionPlantacio.getId(),new Double(posible) );
				
				//Solo si la variedad esta autorizada incorporamos los kgPosibles al totalKgsPosibles
				if(varietatOliva.getAutoritzada().booleanValue()){
					totalKgsPosibles+= posible;
				}
				
			}
			if(totalKgsPosibles> cantidadTotalOliva.doubleValue() || existeAlgunaVariedadExperimental ){
				//Calculamos las proporciones y las introducimos en un hash con el id de variedad
				
				//*****************************************************************************************************
				//!!!IMPORTANTE!!!! Si existe alguna variedad experimental el sistema repartirÃƒÂ¡ de manera proporcinal 
				// 					la cantidadTotalOliva entre las variedades autorizadas en caso de que la cantidad
				//					cantidadTotalOliva supere el totalKgsPosibles entonces la diferencia se asignara
				//					a una de las variedades NO AUTORIZADAS
				//*****************************************************************************************************
				
				double proporcion = 0;
				HashMap proporcionVariedadesHash = new HashMap();
				VarietatOliva varietatOliva = null;
				double proporcionSobrante = 0;
				if(totalKgsPosibles< cantidadTotalOliva.doubleValue()){	
					proporcionSobrante = cantidadTotalOliva.doubleValue() - totalKgsPosibles;
					cantidadTotalOliva = Double.valueOf(String.valueOf(totalKgsPosibles));
				}				
					
				for (Iterator it = descomposiciones.iterator(); it.hasNext();) {
					DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)it.next();
					varietatOliva = descomposicionPlantacio.getVarietatOliva();
					if(varietatOliva.getAutoritzada().booleanValue()){
						double kgPosiblesVariedad = ((Double)kgsPosibles.get(descomposicionPlantacio.getId()) ).doubleValue();
						proporcion = (cantidadTotalOliva.doubleValue() * kgPosiblesVariedad )/totalKgsPosibles;						
					}else{
						proporcion = proporcionSobrante;
						proporcionSobrante = 0;
					}					
					proporcionVariedadesHash.put(varietatOliva.getId(), new Double(proporcion));
				}
				
				//Actualizamos el valor de las variedades que se le pasan de nuevo a la jsp 
				VarietatOlivaCommand varietatOlivaCommand = new VarietatOlivaCommand();
				for (int i=0; i<variedadesList.size(); i++){
					varietatOliva = (VarietatOliva)variedadesList.get(i);
					varietatOlivaCommand = new VarietatOlivaCommand();
					varietatOlivaCommand.fromVarietatOliva(varietatOliva);
					if(proporcionVariedadesHash.get(varietatOliva.getId())!= null){
						int proporcionParteEntera = ((Double)proporcionVariedadesHash.get(varietatOliva.getId())).intValue();
						varietatOlivaCommand.setProduccion(Double.valueOf(String.valueOf(proporcionParteEntera)));
					}else{
						varietatOlivaCommand.setProduccion(null);
					}					
					variedades.add(varietatOlivaCommand);
				}
				
				
				
			}else{
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				VarietatOlivaCommand varietatOlivaCommand = new VarietatOlivaCommand();
				varietatOlivaCommand.setProduccion(Double.valueOf("-1"));
				int kilosPos = Double.valueOf(String.valueOf(totalKgsPosibles)).intValue();
				varietatOlivaCommand.setObservacions(bundle.getString("error.kilosVariedadTotal.incorrecto")+" "+kilosPos+" "+bundle.getString("establiment.medida.k"));
				variedades.add(varietatOlivaCommand);
						
			}
		}else{
			Collection descomposiciones = null;
			if (fincaId!= null && fincaId.intValue() != -1 ){//CASO 2: FINCA CONOCIDA Y PLANTACION NO CONOCIDA ***************************************************************************
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				descomposiciones = oliInfraestructuraEjb.getDescPlantacioActivasAmbFinca(fincaId);
			}else{
				//CASO 3: NO conocida la FINCA y NO la PLANTACION ***************************************************************************************************************************
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				descomposiciones = oliInfraestructuraEjb.descomposicioPlantacioActivasByOlivicultor(idOlivicultor);
			}
			
			//Creamos un Map con todas las descomposiciones(List) por variedad(Key) y comprobamos si alguna es experimental
			HashMap descomposicionPlantacioVariedadHash = new HashMap();// 
			List descomposicionesListByVariedad = new ArrayList();
			VarietatOliva varietatOliva = null; 
			
			
			for (Iterator it = descomposiciones.iterator(); it.hasNext();) {	    			
				DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)it.next();
				varietatOliva = descomposicionPlantacio.getVarietatOliva();
				if(!varietatOliva.getAutoritzada().booleanValue()){//Si la variedad es experimental
					existeAlgunaVariedadExperimental = true;
				}

				if( descomposicionPlantacioVariedadHash.get(varietatOliva.getId())!= null){
					descomposicionesListByVariedad = (List)descomposicionPlantacioVariedadHash.get(varietatOliva.getId());
					descomposicionesListByVariedad.add(descomposicionPlantacio);
				}else{
					descomposicionesListByVariedad = new ArrayList();
					descomposicionesListByVariedad.add(descomposicionPlantacio);    					
				}
				descomposicionPlantacioVariedadHash.put(varietatOliva.getId(),descomposicionesListByVariedad);				
			}
			
			
			//*****************************************************************************************************
			//!!!IMPORTANTE!!!! Si existe alguna variedad experimental el sistema repartirÃƒÂ¡ de manera proporcinal 
			// 					la cantidadTotalOliva entre las variedades autorizadas en caso de que la cantidad
			//					cantidadTotalOliva supere el totalKgsPosibles entonces la diferencia se asignara
			//					a una de las variedades NO AUTORIZADAS
			//*****************************************************************************************************
			
			
			//Calculamos total de kilos posibles para cada descomposicion y los introducimos en un hash
			// al mismo tiempo calculamos el totalPosible entre todas las descomposiciones
			
			double totalKgsPosibles = 0;
			for(Iterator keyVar = descomposicionPlantacioVariedadHash.keySet().iterator();keyVar.hasNext();){
				//Para cada variedad:
				descomposicionesListByVariedad = new ArrayList();
				Integer variedadId = (Integer)keyVar.next();						
				descomposicionesListByVariedad = (List)descomposicionPlantacioVariedadHash.get(variedadId);
				
				//Calculamos los kilos posibles (maxprod - acumulados) para cada descomposicion de esta variedad
				// y el total para todas las descomposiciones de esta variedad
				for(int i=0;i<descomposicionesListByVariedad.size();i++){
					double kgacu = 0;
					DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)descomposicionesListByVariedad.get(i);
					//Si esta autorizada comprobamos loa kilos acumulados 
					if(kgsAcumulatsMap.get(descomposicionPlantacio.getId())!= null && descomposicionPlantacio.getVarietatOliva().getAutoritzada().booleanValue()){
						 kgacu = ((Double)kgsAcumulatsMap.get(descomposicionPlantacio.getId()) ).doubleValue();
					 }
					double posible = descomposicionPlantacio.getMaxProduccio().doubleValue() - kgacu;
					if(posible<0){// NUNCA SE DARA , SOLO SI SE HA TOCADO LA BBDD A MANO
						posible = 0;
					}
   					kgsPosibles.put(descomposicionPlantacio.getId(),new Double(posible) );
   					
   					//Solo si la variedad esta autorizada incorporamos los kgPosibles al totalKgsPosibles
   					if(descomposicionPlantacio.getVarietatOliva().getAutoritzada().booleanValue()){
   						totalKgsPosibles+= posible;
   					}
   				}
			}	
			
			if(totalKgsPosibles> cantidadTotalOliva.doubleValue() || existeAlgunaVariedadExperimental){
				
				double proporcionSobrante = 0;
				if(totalKgsPosibles< cantidadTotalOliva.doubleValue()){	
					proporcionSobrante = cantidadTotalOliva.doubleValue() - totalKgsPosibles;
					cantidadTotalOliva = Double.valueOf(String.valueOf(totalKgsPosibles));
				}	
				
				//Calculamos la proporcion que vamos a repartir a cada descomposicion y 
				//al mismo tiempo generamos un hash con el total que se repartira para cada variedad 
				
				
				HashMap proporcionVariedadesHash = new HashMap();
				for(Iterator keyVar = descomposicionPlantacioVariedadHash.keySet().iterator();keyVar.hasNext();){
					//Para cada variedad:
					descomposicionesListByVariedad = new ArrayList();
					Integer variedadId = (Integer)keyVar.next();
					descomposicionesListByVariedad = (List)descomposicionPlantacioVariedadHash.get(variedadId);
					boolean esAutorizada = false;
					if(descomposicionesListByVariedad.get(0)!= null){
						esAutorizada = ((DescomposicioPlantacio)descomposicionesListByVariedad.get(0)).getVarietatOliva().getAutoritzada().booleanValue();
					}
					
					double proporcionTotalVariedad = 0;
					if(esAutorizada){
						for(int i=0;i<descomposicionesListByVariedad.size();i++){
							double proporcion = 0;
							DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)descomposicionesListByVariedad.get(i);
							double kgPosiblesDescomposicion = ((Double)kgsPosibles.get(descomposicionPlantacio.getId()) ).doubleValue();
							proporcion = (cantidadTotalOliva.doubleValue() * kgPosiblesDescomposicion )/totalKgsPosibles;
							proporcionTotalVariedad+=proporcion;					
						}
					}else{
						proporcionTotalVariedad = proporcionSobrante;
						proporcionSobrante = 0;
					}					
					proporcionVariedadesHash.put(variedadId,Double.toString(proporcionTotalVariedad));
					
					
				}
				//Actualizamos el valor de las variedades que se le pasan de nuevo a la jsp 
				VarietatOlivaCommand varietatOlivaCommand = new VarietatOlivaCommand();
				for (int i=0; i<variedadesList.size(); i++){
					varietatOliva = (VarietatOliva)variedadesList.get(i);
					varietatOlivaCommand = new VarietatOlivaCommand();
					varietatOlivaCommand.fromVarietatOliva(varietatOliva);
					if(proporcionVariedadesHash.get(varietatOliva.getId())!= null){
						int proporcionParteEntera = Double.valueOf((String)proporcionVariedadesHash.get(varietatOliva.getId())).intValue();
						varietatOlivaCommand.setProduccion(Double.valueOf(String.valueOf(proporcionParteEntera)));
						//varietatOlivaCommand.setProduccion(Double.valueOf((String)proporcionVariedadesHash.get(varietatOliva.getId())));
					}else{
						varietatOlivaCommand.setProduccion(null);
					}					
					variedades.add(varietatOlivaCommand);
				}
				
			}else{
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				VarietatOlivaCommand varietatOlivaCommand = new VarietatOlivaCommand();
				varietatOlivaCommand.setProduccion(Double.valueOf("-1"));
				int kilosPos = Double.valueOf(String.valueOf(totalKgsPosibles)).intValue();
				varietatOlivaCommand.setObservacions(bundle.getString("error.kilosVariedadTotal.incorrecto")+" "+kilosPos+" "+bundle.getString("establiment.medida.k"));
				variedades.add(varietatOlivaCommand);
			}
				
			
		}
		
						
		return variedades;
	}

//	public List carregarPartidesOliByEstabliment(Long idEstabliment){
//		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//		return oliInfraestructuraEjb.partidesOliByEstabliment(idEstabliment);
//	}
	
	public String[] tipusPartidaOlis(String idPartida, String partidaOriginal) throws RemoteException {
		String[] resultat = new String[2];
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partidaOli = oliInfraestructuraEjb.getPartidaOliById(Long.valueOf(idPartida));
		if (partidaOli != null) {
			resultat[0] = partidaOli.getCategoriaOli().getNom();
		} else {
			resultat[0] = "";
		}
		
		if( partidaOli != null && 
			partidaOli.getEsDO() && 
			partidaOriginal != null && 
			!partidaOriginal.equals("") && 
			partidaOli.getId().equals(Long.parseLong(partidaOriginal))){
			resultat[1] = "DO";
		} else {
			resultat[1] = "";
		}
		
		return resultat;
	}
	
	public String tipusPartidaOli(String idPartida) throws RemoteException {
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partidaOli = oliInfraestructuraEjb.getPartidaOliById(Long.valueOf(idPartida));
		if (partidaOli != null) {
			return partidaOli.getCategoriaOli().getNom();
		} else {
			return "";
		}
	}
	
	public List partidesOliByEstablimentExceptDO(String idEstabliment) throws RemoteException {
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection cPartides =  oliInfraestructuraEjb.findPartidesOliVisiblesByEstablimentExceptDO(Long.valueOf(idEstabliment));
		List partides = null;
		
		if(cPartides!= null){
			partides = new ArrayList();
			
			Iterator itPartides =cPartides.iterator();
	        while (itPartides.hasNext()) {
	        	PartidaOli partida = (PartidaOli) itPartides.next();
	        	partides.add(new BasicData(String.valueOf(partida.getId()), partida.getNom()));
	        }
		}
		return partides;
	}
	
	public List partidesOliByEstabliment(String idEstabliment) throws RemoteException {
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection cPartides =  oliInfraestructuraEjb.findPartidesOliVisiblesByEstabliment(Long.valueOf(idEstabliment));
		List partides = null;
		
		if(cPartides!= null){
			partides = new ArrayList();
			
			Iterator itPartides =cPartides.iterator();
	        while (itPartides.hasNext()) {
	        	PartidaOli partida = (PartidaOli) itPartides.next();
	        	partides.add(new BasicData(String.valueOf(partida.getId()), partida.getNom()));
	        }
		}
		return partides;
	}
	
	public Object[] calcularVarietatOli(Long[] idsSensorial, String[] valorsSensorial, Long[] idsFisicoQuimic, String[] valorsFisicoQuimic, String idioma, Double acidesa) throws RemoteException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(idioma));
		Object[] resultat = new Object[2];
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		int varietatOli = oliInfraestructuraEjb.calcularVarietatOli(idsSensorial, valorsSensorial, idsFisicoQuimic, valorsFisicoQuimic, acidesa, Double.parseDouble(this.acidesaVergeExtra), Double.parseDouble(this.acidesaVerge));
		
		resultat[0] = varietatOli;
		resultat[1] = bundle.getString("analitica.varietat." + varietatOli);
		
		return resultat;
	}
	
	public Object[] varietatOliResultant(Long idVarietatSensorial, Long idVarietatFisicoQuimic, String idioma) throws RemoteException {
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(idioma));
		Object[] resultat = new Object[2];
		int varietatOli = 0;
		
		if(idVarietatSensorial != null && idVarietatFisicoQuimic != null && idVarietatSensorial > 0 && idVarietatFisicoQuimic > 0){
			if(idVarietatSensorial.equals(Long.valueOf(Constants.VARIETAT_LLAMPANT)) || idVarietatFisicoQuimic.equals(Long.valueOf(Constants.VARIETAT_LLAMPANT))){
				varietatOli = Constants.VARIETAT_LLAMPANT;
			} else {
				if(idVarietatSensorial.equals(Long.valueOf(Constants.VARIETAT_VERGE)) || idVarietatFisicoQuimic.equals(Long.valueOf(Constants.VARIETAT_VERGE))){
					varietatOli = Constants.VARIETAT_VERGE;
				} else {
					varietatOli  = Constants.VARIETAT_VERGE_EXTRA;
				}
			}
		}
		
		resultat[0] = varietatOli;
		resultat[1] = bundle.getString("analitica.varietat." + varietatOli);
		
		return resultat;
	}

//	public Boolean canviaEstablimentUsuariLimit2(Long idEstabliment){
//	
//		try {
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			Usuari limit2 = oliInfraestructuraEjb.usuariAmbId(552L);
//			Establiment establiment = oliInfraestructuraEjb.establimentAmbId(idEstabliment);
//			Set establiments = limit2.getEstabliments();
//			establiments.clear();
//			establiments.add(establiment);
//			limit2.setEstabliments(establiments);
//			oliInfraestructuraEjb.usuariModificar(limit2);
//			return true;
//		} catch (Exception ex){
//			return false;
//		}
//	}
	
	/**
	 * Injecció de la dependència oliInfraestructuraEjb
	 * @param oliInfraestructuraEjb La classe a injectar.
	 */
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}
	/**
	 * InjecciÃƒÂ³ de la dependÃƒÂ¨ncia oliInfraestructuraEjb
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
	
	public String getAcidesaVergeExtra() {
		return acidesaVergeExtra;
	}
	
	public void setAcidesaVergeExtra(String acidesaVergeExtra) {
		this.acidesaVergeExtra = acidesaVergeExtra;
	}

	public String getAcidesaVerge() {
		return acidesaVerge;
	}

	public void setAcidesaVerge(String acidesaVerge) {
		this.acidesaVerge = acidesaVerge;
	}

	
}