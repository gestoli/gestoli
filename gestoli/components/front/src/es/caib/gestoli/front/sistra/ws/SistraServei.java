package es.caib.gestoli.front.sistra.ws;


import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.interfaces.OliSistraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.util.Constants;
/*
 * Servei per a la integració entre sistra i Gestoli. Implementa totes les peticions
 * de dominis necessàries per als tràmits de Sistra.
 * 
 * @author Sion andreu <sandreu@limit.es>
 */
public class SistraServei implements SistraFacade {

	/*
	 * Identificador del domini genèric AP_GESTOLI
	 */
	// Dominis genèrics
	private static final String DOMINI_DAD_TAFONA = "AP_DAD_TAFONA"; //
	private static final String DOMINI_ENT_OLV = "AP_ENT_OLV"; //
	private static final String DOMINI_ENT_OLI = "AP_ENT_OLI";
	private static final String DOMINI_PRO_OLI = "AP_PRO_OLI"; //
	private static final String DOMINI_PRO_ENVPRO = "AP_PRO_ENVPRO";//
	private static final String DOMINI_PRO_ALTDO = "AP_PRO_ALTDO";//
	private static final String DOMINI_PRO_ALTNDO = "AP_PRO_ALTNODO";//
	private static final String DOMINI_SOR_OLI_M = "AP_SOR_OLI_M"; //
	private static final String DOMINI_SOR_OLI_A = "AP_SOR_OLI_A"; //
	private static final String DOMINI_EXI_GRADO = "AP_EXI_GRADO"; //
	private static final String DOMINI_EXI_GRANDO = "AP_EXI_GRANODO"; //
	private static final String DOMINI_EXI_GRAPEN = "AP_EXI_GRAPEN"; //
	private static final String DOMINI_EXI_EMB = "AP_EXI_EMB"; //
	private static final String DOMINI_PRE_OLV = "AP_PRE_OLV";//
	private static final String DOMINI_PRE_OLI = "AP_PRE_OLI";//
	private static final String DOMINI_CIR_DES = "AP_CIR_DES";
	private static final String DOMINI_CIR_MER = "AP_CIR_MER";
	private static final String DOMINI_DES_ETQ = "AP_DES_ETQ";
	private static final String DOMINI_PAR_CONETQ = "AP_PAR_CONETQ";//
	private static final String DOMINI_COM_INI = "AP_COM_INI";
	private static final String DOMINI_CAMPANYES = "AP_CAMPANYES"; //
	private static final String DOMINI_CAPACITATS = "AP_CAPACITATS";//
	
	private OliSistraEjb oliSistraEjb;
	private HibernateTemplate hibernateTemplate;

	public ValoresDominio obtenerDominio(
			String id,
			ParametrosDominio parametros) throws SistraFacadeException_Exception {
		// Dominis genèrics
		if (DOMINI_DAD_TAFONA.equals(id)) {
			return valorsDominiDadesTafona(parametros);		}
		else if (DOMINI_ENT_OLV.equals(id)) {
			return valorsDominiEntradaOliva(parametros);		}
		else if (DOMINI_ENT_OLI.equals(id)) {
			return valorsDominiEntradaOli(parametros);		}
		else if (DOMINI_PRO_OLI.equals(id)) {
			return valorsDominiOliProduit(parametros);		}
		else if (DOMINI_PRO_ENVPRO.equals(id)) {
			return valorsDominiOliProduitEnvasamentPropi(parametros);		}
		else if (DOMINI_PRO_ALTDO.equals(id)) {
			return valorsDominiOliProduitEnvasamentAltresDO(parametros);		}
		else if (DOMINI_PRO_ALTNDO.equals(id)) {
			return valorsDominiOliProduitEnvasamentAltresNoDO(parametros);		}
		else if (DOMINI_SOR_OLI_M.equals(id)) {
			return valorsDominiSortidaOliMensual(parametros);		}
		else if (DOMINI_SOR_OLI_A.equals(id)) {
			return valorsDominiSortidaOliAnual(parametros);		}
		else if (DOMINI_EXI_GRADO.equals(id)) {
			return valorsDominiExistenciesGranelDO(parametros);		}
		else if (DOMINI_EXI_GRANDO.equals(id)) {
			return valorsDominiExistenciesGranelNoDO(parametros);		}
		else if (DOMINI_EXI_EMB.equals(id)) {
			return valorsDominiExistenciesEmbotellat(parametros);		}
		else if (DOMINI_EXI_GRAPEN.equals(id)) {
			return valorsDominiExistenciesGranelPendent(parametros);		}
		else if (DOMINI_PRE_OLV.equals(id)) {
			return valorsDominiPreuOliva(parametros);		}
		else if (DOMINI_PRE_OLI.equals(id)) {
			return valorsDominiPreuOli(parametros);		}
		else if (DOMINI_CIR_DES.equals(id)) {
			return valorsDominiCirculacioDestinatari(parametros);		}
		else if (DOMINI_CIR_MER.equals(id)) {
			return valorsDominiCirculacioMercaderia(parametros);		}
		else if (DOMINI_DES_ETQ.equals(id)) {
			return valorsDominiDescripcioEtiqueta(parametros);		}
		else if (DOMINI_PAR_CONETQ.equals(id)) {
			return valorsDominiPartidaContraetiquetes(parametros);		}
		else if (DOMINI_COM_INI.equals(id)) {
			return valorsDominiComunicacioInici(parametros);		}
		else if (DOMINI_CAMPANYES.equals(id)) {
			return valorsDominiLlistatCampanyes(parametros);		}
		else if (DOMINI_CAPACITATS.equals(id)) {
			return valorsDominiCapacitatsEstabliment(parametros);		}
		else {
			throw new SistraFacadeException_Exception("Identificador de domini desconegut");
		}
	}

	public DocumentosConsulta realizarConsulta(
			FormulariosConsulta forms) throws SistraFacadeException_Exception {
		throw new SistraFacadeException_Exception("Funcionalitat no implementada");
	}

	//-------------------------------------------------------------------------------------------
	//IMPLEMENTACIÓ DELS DOMINIS
	//-------------------------------------------------------------------------------------------
	
	// Dominis genèrics
	//**********************************************
	

	/*
	 * @param nif
	 * @return El nif passat per paràmetres formatat, només amb lletres majúscules i números
	 */
	private String formataNIF(String nif) {
		String fNif = nif.replaceAll("\\W", "");
		return fNif.toUpperCase();
	}
	

	//Parametres
	//Param 0 = NIF
	private ValoresDominio valorsDominiDadesTafona(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		String nifTitular = formataNIF((String)parametros.getParametro().get(0));
		
		try {
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			if(establiment != null){
				Filas filas = new Filas();
				
				// Comprova si retorna algún resultat
				Fila f;
				Columna c;
					f = new Fila();
					c = new Columna();
					c.setCodigo("cTafonaNom");
					c.setValor(establiment.getNom());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cTafonaCif");
					c.setValor(establiment.getCif());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cTipusEstab");
					c.setValor(establiment.getTipusEstabliment().getNom());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cNumCRDO");
					c.setValor(establiment.getCodiRC());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cRepresentant");
					c.setValor(establiment.getNomResponsable());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cDniRepresentant");
					c.setValor(establiment.getCifResponsable());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cAdreça");
					c.setValor(establiment.getDireccio());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cLocalitat");
					c.setValor(establiment.getPoblacio().getNom());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cTelefon");
					c.setValor(establiment.getTelefon());
					f.getColumna().add(c);
					filas.getFila().add(f);
		
				resposta.setFilas(filas);
			} 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resposta;
	}
	
	
	private ValoresDominio valorsDominiEntradaOli(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
//		String cif = formataNIF((String)parametros.getParametro().get(0));
//		List<Establiment> Establiments = EstablimentDao.findByCif(cif);
		
		Filas filas = new Filas();
		Fila f;
		Columna c;
		
			f = new Fila();
			c = new Columna();
			c.setCodigo("cOliDO");
			c.setValor("999");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliNoDo");
			c.setValor("999");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliPendent");
			c.setValor("999");
			f.getColumna().add(c);
			filas.getFila().add(f);
			
		resposta.setFilas(filas);
		
		return resposta;
	}
	
	//Parametres
	//Param 0: nif
	//Param 1: data inici
	//Param 2: data fi
	private ValoresDominio valorsDominiEntradaOliva(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 3 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String) || !(parametros.getParametro().get(2) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(parametros.getParametro().get(1));
	        Date dataFi = formatter.parse(parametros.getParametro().get(2));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection entrades = oliSistraEjb.findEntradesOlivaEntreDates(dataInici,dataFi,establiment.getId());
		
			Double totalQuilos = 0.0;
			Double totalMallorquina = 0.0;
			Double totalArbequina = 0.0;
			Double totalPicual = 0.0;
			
			for(Iterator it=entrades.iterator(); it.hasNext();){
				PartidaOliva po = (PartidaOliva)it.next();
				
				Collection descomposicions = po.getDescomposicioPartidesOlives();
				
				for(Iterator it2=descomposicions.iterator(); it2.hasNext();){
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it2.next();
					
					if(dpo.getDescomposicioPlantacio().getVarietatOliva().getId().equals(Constants.VARIETAT_OLIVA_MALLORQUINA)){
						totalMallorquina += dpo.getKilos();
					} else if(dpo.getDescomposicioPlantacio().getVarietatOliva().getId().equals(Constants.VARIETAT_OLIVA_ARBEQUINA)){
						totalArbequina += dpo.getKilos();
					} else if(dpo.getDescomposicioPlantacio().getVarietatOliva().getId().equals(Constants.VARIETAT_OLIVA_PICUAL)){
						totalPicual += dpo.getKilos();
					}
					
				}
				
				totalQuilos += po.getTotalKilos();
			}
	        
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
				f = new Fila();
				c = new Columna();
				c.setCodigo("cOlivaMallorquina");
				c.setValor(String.valueOf(totalMallorquina));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cOlivaArbequina");
				c.setValor(String.valueOf(totalArbequina));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cOlivaPiqual");
				c.setValor(String.valueOf(totalPicual));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cTotal");
				c.setValor(String.valueOf(totalQuilos));
				f.getColumna().add(c);
				filas.getFila().add(f);
				
			resposta.setFilas(filas);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	
	//Parametres
	//param 0: nif
	//param 1: data inici
	//param 2: data fi
	private ValoresDominio valorsDominiOliProduit(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 3 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String) || !(parametros.getParametro().get(2) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(parametros.getParametro().get(1));
	        Date dataFi = formatter.parse(parametros.getParametro().get(2));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection elaboracions = oliSistraEjb.findElaboracionsEntreDates(dataInici,dataFi,establiment.getId(),true,true);
		
			Double oliDestinatDO = 0.0;
			Double oliNoDO = 0.0; 
			
			for(Iterator it=elaboracions.iterator(); it.hasNext();){
				Elaboracio e = (Elaboracio)it.next();
				
				if(e.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
					oliDestinatDO += e.getLitres();
				} else {
					oliNoDO += e.getLitres();
				}
			}
			
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection listaTrasllatEntrades = oliSistraEjb.movimentsEntreEstablimentsEntradaConsulta(dataInici, dataFi, establiment.getId());
			
			double trasllat_do = 0.0;
			double trasllat_pendent = 0.0;
			double trasllat_nodo = 0.0;
			
			for(Iterator it=listaTrasllatEntrades.iterator();it.hasNext();){
				Trasllat trasllat = (Trasllat)it.next();
				
				double quantitat = 0;
				
				if(trasllat.getEsDiposit()){
					if(trasllat.getEstablimentByTdiCodeor().getId().equals(establiment.getId())){
						quantitat = trasllat.getQuantitatRetorn();
					} else {
						quantitat = trasllat.getQuantitatEnviament();
					}
				} else {
					quantitat = trasllat.getQuantitatEnviament();
				}
				
				for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
					Traza t2 = (Traza)it2.next();
					if (t2.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
						
						oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
						EntradaDiposit ed = oliSistraEjb.findEntradaDipositByTraza(t2.getId());
						
						if(ed.getPartidaOli().getCategoriaOli().getId().equals(1)){ // 1 = no do
							trasllat_nodo += quantitat;
						} else if(ed.getPartidaOli().getCategoriaOli().getId().equals(2)){ // 2 = pendent
							trasllat_pendent += quantitat;
						} else { // 3 = do
							trasllat_do += quantitat;
						}
						break;
					}
				}
				
			}
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			f = new Fila();
			c = new Columna();
			c.setCodigo("cOliNoDO");
			c.setValor(String.valueOf(oliNoDO));
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliDestinatDO");
			c.setValor(String.valueOf(oliDestinatDO));
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliTrasllatDO");
			c.setValor(String.valueOf(trasllat_do));
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliTrasllatPendent");
			c.setValor(String.valueOf(trasllat_pendent));
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cOliTrasllatNoDO");
			c.setValor(String.valueOf(trasllat_nodo));
			f.getColumna().add(c);
			filas.getFila().add(f);
			
			
			resposta.setFilas(filas);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	
	//Parametres
	//param 0: nif
	//param 1: data inici
	//param 2: data fi
	private ValoresDominio valorsDominiOliProduitEnvasamentPropi(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 3 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String) || !(parametros.getParametro().get(2) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(parametros.getParametro().get(1));
	        Date dataFi = formatter.parse(parametros.getParametro().get(2));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection lots = oliSistraEjb.findLotsByEstablimentEntreDates(establiment.getId(), dataInici,dataFi, true);
		
			Double envasamentPropi = 0.0;
			
			for(Iterator it=lots.iterator(); it.hasNext();){
				Lot l = (Lot)it.next();
				envasamentPropi += l.getLitresEnvassats();
			}
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			f = new Fila();
			c = new Columna();
			c.setCodigo("cEnvasamentPropi");
			c.setValor(String.valueOf(envasamentPropi));
			f.getColumna().add(c);
			filas.getFila().add(f);
			
			resposta.setFilas(filas);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
//De moment no s'empra
	private ValoresDominio valorsDominiOliProduitEnvasamentAltresDO(ParametrosDominio parametros) throws SistraFacadeException_Exception {
//		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
//			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
//		
//		Filas filas = new Filas();
//		Fila f;
//		Columna c;
//		
//		
//			for (Integer i=0; i<3;i++) {
//				f = new Fila();
//				c = new Columna();
//				c.setCodigo("cDesti");
//				c.setValor("aaa " + i.toString());
//				f.getColumna().add(c);
//				c = new Columna();
//				c.setCodigo("cLitres");
//				c.setValor("999");
//				f.getColumna().add(c);
//				filas.getFila().add(f);
//			}
//	
//		resposta.setFilas(filas);
		return resposta;
	}
	
//De moment no s'empra	
	private ValoresDominio valorsDominiOliProduitEnvasamentAltresNoDO(ParametrosDominio parametros) throws SistraFacadeException_Exception {
//		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
//			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
//		
//		Filas filas = new Filas();
//		Fila f;
//		Columna c;
//		
//		
//			for (Integer i=0; i<4;i++) {
//				f = new Fila();
//				c = new Columna();
//				c.setCodigo("cDesti");
//				c.setValor("EnvasadorNoDo " + i.toString());
//				f.getColumna().add(c);
//				c = new Columna();
//				c.setCodigo("cLitres");
//				c.setValor("175");
//				f.getColumna().add(c);
//				filas.getFila().add(f);
//			}
//	
//		resposta.setFilas(filas);
		return resposta;
	}
	
	
	//Parametres
	//param 0: nif
	//param 1: data inici
	//param 2: data fi
	private ValoresDominio valorsDominiSortidaOliMensual(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 3 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String) || !(parametros.getParametro().get(2) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(parametros.getParametro().get(1));
	        Date dataFi = formatter.parse(parametros.getParametro().get(2));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection sortidesLot = oliSistraEjb.findSortidesLotEntreDates(dataInici,dataFi,establiment.getId());
			
			//oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			//Collection sortidesDiposit = oliSistraEjb.findSortidesDipositEntreDates(dataInici,dataFi,establiment.getId());
			
			Double granelDO = 0.0;
			Double granelPendent = 0.0;
			Double granelNoDO = 0.0;
			
			Double embotellatDO = 0.0;
			Double embotellatNoDO = 0.0;
			
			for(Iterator it=sortidesLot.iterator(); it.hasNext();){
				SortidaLot sl = (SortidaLot)it.next();
				if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
					embotellatDO += sl.getVendaLitres();
				} else {
					embotellatNoDO += sl.getVendaLitres();
				}
			}
			
			///
			//oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection sortidesDiposit = oliSistraEjb.getSortidesDipositEntreFechasAndEstablecimiento(dataInici, dataFi, establiment.getId(), true);
			
			for(Iterator it=sortidesDiposit.iterator(); it.hasNext();){
				SortidaDiposit sd = (SortidaDiposit)it.next();
				
				if (sd.getValid() && sd.getDipositBySdiCoddde() == null && sd.getLot() == null){ 
					if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
						granelDO += sd.getLitres();
					} else if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
						granelPendent += sd.getLitres();
					} else {
						granelNoDO += sd.getLitres();
					}
				}
			}
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
				f = new Fila();
				c = new Columna();
				c.setCodigo("cGranelDO");
				c.setValor(String.valueOf(granelDO));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cGranelNoDO");
				c.setValor(String.valueOf(granelNoDO));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cGranelPendent");
				c.setValor(String.valueOf(granelPendent));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cEmbotellatDO");
				c.setValor(String.valueOf(embotellatDO));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cEmbotellatNoDO");
				c.setValor(String.valueOf(embotellatNoDO));
				f.getColumna().add(c);
				filas.getFila().add(f);
				
			resposta.setFilas(filas);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	//Parametres
	//param 0: nif
	//param 1: data inici
	//param 2: data fi
	private ValoresDominio valorsDominiSortidaOliAnual(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 3 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String) || !(parametros.getParametro().get(2) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(parametros.getParametro().get(1));
	        Date dataFi = formatter.parse(parametros.getParametro().get(2));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection sortidesLot = oliSistraEjb.findSortidesLotEntreDates(dataInici,dataFi,establiment.getId());
			
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection sortidesDiposit = oliSistraEjb.findSortidesDipositEntreDates(dataInici,dataFi,establiment.getId());
			
			Double granelDO = 0.0;
			
			Double ventaBalears = 0.0;
			Double ventaEspanya = 0.0;
			List<Object[]> ventaUE = new ArrayList<Object[]>();
			
			for(Iterator it=sortidesLot.iterator(); it.hasNext();){
				SortidaLot sl = (SortidaLot)it.next();
				if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
					if(sl.getPais() != null && sl.getProvincia() != null && sl.getPais().getId().equals(Long.valueOf(Constants.PAIS_ESPANYA)) && sl.getProvincia().getId().equals(Long.valueOf(Constants.PROVINCIA_BALEARS))){
						ventaBalears += sl.getVendaLitres();
					} else if(sl.getPais() != null && sl.getPais().getId().equals(Long.valueOf(Constants.PAIS_ESPANYA))){
						ventaEspanya += sl.getVendaLitres();
					} else if(sl.getPais() != null){
						ventaUE.add(new Object[]{sl.getPais().getNomcat(),sl.getVendaLitres()});
					}
				}
			}
			
			//Les sortides de dipòsit que siguin de DO se considerarà que serà a les Balears
			for(Iterator it=sortidesDiposit.iterator(); it.hasNext();){
				SortidaDiposit sd = (SortidaDiposit)it.next();
				
				if(sd.getDipositBySdiCoddde() == null){ //Dipòsit destí == null vol dir que és una venta
					if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
						granelDO += sd.getLitres();
					}
				}
			}
		
		
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			f = new Fila();
			c = new Columna();
			c.setCodigo("cBalears");
			c.setValor(String.valueOf(ventaBalears));
			f.getColumna().add(c);
			filas.getFila().add(f);
			f = new Fila();
			c = new Columna();
			c.setCodigo("cEspanya");
			c.setValor(String.valueOf(ventaEspanya));
			f.getColumna().add(c);
			filas.getFila().add(f);
			
			for (Object[] obj: ventaUE) {
				f = new Fila();
				c = new Columna();
				c.setCodigo("cDesti");
				c.setValor((String)obj[0]);
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cLitres");
				c.setValor(String.valueOf((Double)obj[1]));
				f.getColumna().add(c);
				filas.getFila().add(f);
			}
		
			resposta.setFilas(filas);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	//Paràmetres
	//param 0: nif establiment
	//param 1: data dia existències
	private ValoresDominio valorsDominiExistenciesGranelDO(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		List categorias = new ArrayList();
	   	categorias.add(Constants.CATEGORIA_DO);
		
		return valorsDominiExistenciesGranel(parametros,categorias);
	}
	
	//Paràmetres
	//param 0: nif establiment
	//param 1: data dia existències
	private ValoresDominio valorsDominiExistenciesGranelNoDO(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		List categorias = new ArrayList();
	   	categorias.add(Constants.CATEGORIA_NO_DO);
		
		return valorsDominiExistenciesGranel(parametros,categorias);
	}
	
	
	//Paràmetres
	//param 0: nif establiment
	//param 1: data dia existències
	private ValoresDominio valorsDominiExistenciesGranelPendent(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		List categorias = new ArrayList();
	   	categorias.add(Constants.CATEGORIA_PENDENT);
		
		return valorsDominiExistenciesGranel(parametros,categorias);
	}
	
	//Paràmetres
	//param 0: nif establiment
	//param 1: data dia existències
	private ValoresDominio valorsDominiExistenciesGranel(ParametrosDominio parametros,List categories) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 2 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date data = formatter.parse(parametros.getParametro().get(1));
	
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
		   	oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
		   	Collection llistatOliGranel = oliSistraEjb.findExistenciesDipositsByEstablimentCategoriasAndData(establiment.getId(),categories.toArray(),data);
	   	
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
	
			for (Iterator it=llistatOliGranel.iterator(); it.hasNext();) {
				
				Object[] o = (Object[])it.next();
				
				f = new Fila();
				c = new Columna();
				c.setCodigo("cDiposit");
				c.setValor((String)o[1]);
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cLitres");
				c.setValor(String.valueOf((Double)o[2]));
				f.getColumna().add(c);
				filas.getFila().add(f);
			}
	
			resposta.setFilas(filas);

		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	//Paràmetres
	//param 0: nif establiment
	//param 1: data dia existències
	private ValoresDominio valorsDominiExistenciesEmbotellat(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 2 || !(parametros.getParametro().get(0) instanceof String) || !(parametros.getParametro().get(1) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			String nifTitular = formataNIF((String)parametros.getParametro().get(0));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date data = formatter.parse(parametros.getParametro().get(1));
	
	        List categorias = new ArrayList();
		   	categorias.add(Constants.CATEGORIA_DO);
		   	categorias.add(Constants.CATEGORIA_NO_DO);
	        
	        oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
		   	oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
		   	Collection llistatOliLot = oliSistraEjb.findExistenciesLotsByEstablimentCategoriasAndData(establiment.getId(),categorias.toArray(),data);
	   	
		   	Double embotellatDO = 0.0;
		   	Double embotellatNoDO = 0.0;
		   	
		   	for(Iterator it = llistatOliLot.iterator(); it.hasNext();){
		   		Object[] o = (Object[])it.next();
		   		Double litresEmbotellats = (Double)o[2];
		   		CategoriaOli cat = (CategoriaOli)o[4];
		   		
		   		if(cat.getId().equals(Constants.CATEGORIA_DO)){
		   			embotellatDO += litresEmbotellats;
		   		} else {
		   			embotellatNoDO += litresEmbotellats;
		   		}
		   	}
		   	
		   	
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			f = new Fila();
			c = new Columna();
			c.setCodigo("cEmbotellatDO");
			c.setValor(String.valueOf(embotellatDO));
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cEmbotellatNoDO");
			c.setValor(String.valueOf(embotellatNoDO));
			f.getColumna().add(c);
			filas.getFila().add(f);
	
			resposta.setFilas(filas);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
//De moment no s'empra
	private ValoresDominio valorsDominiPreuOliva(ParametrosDominio parametros) throws SistraFacadeException_Exception {
//		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
//			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
//		
//		Filas filas = new Filas();
//		Fila f;
//		Columna c;
//		
//		f = new Fila();
//		c = new Columna();
//		c.setCodigo("cPreuMitjaOliva");
//		c.setValor("0.42");
//		f.getColumna().add(c);
//		filas.getFila().add(f);
//		
//		resposta.setFilas(filas);
		return resposta;
	}
	
//De moment no s'empra
	private ValoresDominio valorsDominiPreuOli(ParametrosDominio parametros) throws SistraFacadeException_Exception {
//		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
//			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
//		
//		Filas filas = new Filas();
//		Fila f;
//		Columna c;
//		
//		f = new Fila();
//		c = new Columna();
//		c.setCodigo("cPreuOliGranelDO");
//		c.setValor("1.23");
//		f.getColumna().add(c);
//		c = new Columna();
//		c.setCodigo("cPreuOliEmbotellatDO");
//		c.setValor("2.65");
//		f.getColumna().add(c);
//		c = new Columna();
//		c.setCodigo("cValorTotalComercialitzatDO");
//		c.setValor("46000");
//		f.getColumna().add(c);
//		filas.getFila().add(f);
//		
//
//	
//		resposta.setFilas(filas);
		return resposta;
	}

	
	private ValoresDominio valorsDominiCirculacioDestinatari(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();

		Filas filas = new Filas();
		Fila f;
		Columna c;

			f = new Fila();
			c = new Columna();
			c.setCodigo("cTafonaNom");
			c.setValor("Tafona Destinatari");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cTafonaCif");
			c.setValor("A07167488");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cTipusEstab");
			c.setValor("2");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cNumCRDO");
			c.setValor("000005483");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cRepresentant");
			c.setValor("Joan Miquel Llòpis");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cDniRepresentant");
			c.setValor("12345678Z");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cAdreça");
			c.setValor("Llevant, 4");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cLocalitat");
			c.setValor("Puigpunyent");
			f.getColumna().add(c);
			c = new Columna();
			c.setCodigo("cTelefon");
			c.setValor("971743354");
			f.getColumna().add(c);
			filas.getFila().add(f);

		resposta.setFilas(filas);
		return resposta;
	}

	
	private ValoresDominio valorsDominiCirculacioMercaderia(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		
		Filas filas = new Filas();
		Fila f;
		Columna c;
		
		f = new Fila();
		c = new Columna();
		c.setCodigo("cAcidesa");
		c.setValor("3");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cAutocontrol");
		c.setValor("true");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cDataTransport");
		c.setValor("15/12/2010");
		f.getColumna().add(c);
		filas.getFila().add(f);
		
			for (Integer i=1; i<=5;i++) {
				f = new Fila();
				c = new Columna();
				c.setCodigo("cNumEnvasos");
				c.setValor("10");
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cCapacitat");
				c.setValor(i.toString());
				f.getColumna().add(c);
				filas.getFila().add(f);
			}
	
		resposta.setFilas(filas);
		return resposta;
		

	}
	
	
	private ValoresDominio valorsDominiDescripcioEtiqueta(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		
		Filas filas = new Filas();
		Fila f;
		Columna c;
		
		f = new Fila();
		c = new Columna();
		c.setCodigo("cMarca");
		c.setValor("Caimari");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cMaterialEnvas");
		c.setValor("Vidre");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cCapacitat");
		c.setValor("0.75");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cEmparatDOP");
		c.setValor("true");
		f.getColumna().add(c);
		c = new Columna();
		c.setCodigo("cObservacions");
		c.setValor("la mida de l'etiqueta és de 10x5cm");
		f.getColumna().add(c);
		filas.getFila().add(f);
		
		resposta.setFilas(filas);
		return resposta;
	}
	
	//Paràmetres
	//param 0: nif establiment
	private ValoresDominio valorsDominiPartidaContraetiquetes(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		ValoresDominio resposta = new ValoresDominio();
		String nifTitular = formataNIF((String)parametros.getParametro().get(0));
		
		try {
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection lots = oliSistraEjb.findExistenciesLotsByEstabliment(establiment.getId());
			
			Double oliDO = 0.0;
			
			for(Iterator it=lots.iterator(); it.hasNext();){
				Lot lot = (Lot)it.next();
				
				if(lot.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
					oliDO += lot.getNumeroBotellesActuals() * lot.getEtiquetatge().getTipusEnvas().getVolum();
				}
			}
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			f = new Fila();
			c = new Columna();
			c.setCodigo("cLitresDisp");
			c.setValor(String.valueOf(oliDO));
			f.getColumna().add(c);
			filas.getFila().add(f);
			
			resposta.setFilas(filas);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	//Paràmetres
	//param 0: nif establiment
	private ValoresDominio valorsDominiComunicacioInici(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		
		ValoresDominio resposta = new ValoresDominio();
		
		String nifTitular = formataNIF((String)parametros.getParametro().get(0));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection lots = oliSistraEjb.findExistenciesLotsByEstabliment(establiment.getId());
			
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection diposits = oliSistraEjb.findDipositsActiusByEstabliment(establiment.getId());
			
			Double existencies = 0.0;
			
			for(Iterator it=lots.iterator(); it.hasNext();){
				Lot lot = (Lot)it.next();
				existencies += lot.getNumeroBotellesActuals() * lot.getEtiquetatge().getTipusEnvas().getVolum();
			}
			
			for(Iterator it=diposits.iterator(); it.hasNext();){
				Diposit diposit = (Diposit)it.next();
				
				if(diposit.getVolumActual() != null)
					existencies += diposit.getVolumActual();
			}
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
	
			for (Integer i=1; i<5;i++) {
				f = new Fila();
				c = new Columna();
				c.setCodigo("cDeclarantNom");
				c.setValor(establiment.getNomResponsable());
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cDeclarantNif");
				c.setValor(establiment.getCifResponsable());
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cExistenciesOli");
				c.setValor(String.valueOf(existencies));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cDataInici");
				c.setValor(formatter.format(new Date()));
				f.getColumna().add(c);
				filas.getFila().add(f);
			}
	
			resposta.setFilas(filas);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}

	//Parametres
	private ValoresDominio valorsDominiLlistatCampanyes(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 0)
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		
		ValoresDominio resposta = new ValoresDominio();
		
		try{
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection campanyes = oliSistraEjb.findAllCampanyes();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
			
				for (Iterator it=campanyes.iterator(); it.hasNext();) {
					
					Campanya campanya = (Campanya)it.next();
					
					oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
					Date dataFi = oliSistraEjb.findDataFiCampanya(campanya.getId());
					
					if(dataFi == null)
						dataFi = new Date();
					
					f = new Fila();
					c = new Columna();
					c.setCodigo("cIdCampanya");
					c.setValor(String.valueOf(campanya.getId()));
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cNomCampanya");
					c.setValor(campanya.getNom());
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cDataInici");
					c.setValor(formatter.format(campanya.getData()));
					f.getColumna().add(c);
					c = new Columna();
					c.setCodigo("cDataFi");
					c.setValor(formatter.format(dataFi));
					f.getColumna().add(c);
					filas.getFila().add(f);
				}
		
			resposta.setFilas(filas);
		
		} catch(Exception e){
			e.printStackTrace();
		}	
		
		return resposta;
	}
	
	//Paràmetres
	//param 0: nif establiment
	private ValoresDominio valorsDominiCapacitatsEstabliment(ParametrosDominio parametros) throws SistraFacadeException_Exception {
		if (parametros.getParametro().size() != 1 || !(parametros.getParametro().get(0) instanceof String))
			throw new SistraFacadeException_Exception("Paràmetres incorrectes");
		
		ValoresDominio resposta = new ValoresDominio();
		String nifTitular = formataNIF((String)parametros.getParametro().get(0));
		
		try {
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = oliSistraEjb.establimentAmbCifNifResponsable(nifTitular);
		
			oliSistraEjb.setHibernateTemplate(getHibernateTemplate());
			List etiquetatges = oliSistraEjb.findEtiquetatgesByEstabliment(establiment.getId());
			
			
			Filas filas = new Filas();
			Fila f;
			Columna c;
			
	
			for (Iterator it=etiquetatges.iterator(); it.hasNext();) {
				
				Etiquetatge eti = (Etiquetatge)it.next();
				
				f = new Fila();
				c = new Columna();
				c.setCodigo("cCapacitat");
				c.setValor(String.valueOf(eti.getTipusEnvas().getVolum()));
				f.getColumna().add(c);
				c = new Columna();
				c.setCodigo("cDescripcio");
				c.setValor(eti.getNomEnvas());
				f.getColumna().add(c);
				filas.getFila().add(f);
			}
	
			resposta.setFilas(filas);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return resposta;
	}


	public OliSistraEjb getOliSistraEjb() {
		return oliSistraEjb;
	}
	
	public void setOliSistraEjb(OliSistraEjb oliSistraEjb) {
		this.oliSistraEjb = oliSistraEjb;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public DocumentosConsulta realizarConsulta(String identificadorTramite,
			FormulariosConsulta forms) throws SistraFacadeException_Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
