/**
 * GenerarPdfInformeProductors.java
 *
 * Creada el 4 de agosto de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.util.Constants;


public class GenerarPdfInformeProductorsRA implements Controller {
	private static Logger logger = Logger.getLogger(GenerarPdfInformeProductorsRA.class);
	private ReloadableResourceBundleMessageSource messageSource;
	private CookieLocaleResolver localeResolver;
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private Long tipusArbequina;
	private Long tipusMallorquina;
	private Long tipusMallorquinaTaula;
	private Long tipusPicual;
	private Long tipusExperimental;
	private String campanyaSessionKey;

	private HashMap PARAMS = new HashMap();
	private final static String STREAM = "es/caib/gestoli/logic/resources/informeProductorsRA.jrxml";
	private final static String FILE_NAME = "informeProductors";


	/**
	 * Exportación de datos en formato PDF
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		logger.debug("handleRequest ini");
		System.setProperty("java.awt.headless", "true");

		try {
			DecimalFormat numberDecimalFormat = new DecimalFormat();
			numberDecimalFormat.setMaximumFractionDigits(2);
			Long campanyaActualId = (Long)request.getSession().getAttribute(campanyaSessionKey); 
			Collection olivicultoresActual = null;
			logger.debug("1.- Recuperando datos");
			// consultas
			logger.debug("2.- Cargando parametros");
			PARAMS = setParams(request);
			//***************************************************************************************************	
			//********************************  Valores Tabla 1  ************************************************
			//***************************************************************************************************
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
				Iterator temporadasIt = oliInfraestructuraEjb.campanyaCercaTotes().iterator();
				Collection[] arrayOlivicultoresTemporadas = new  Collection[4];
				String[] arrayTemporadasNames = new  String[4];
				Long[] arrayTemporadesId = new Long[4];
				Long[] arrayTafonesTemporadas = new  Long[4];							
				Long[] arrayEnvasadoresTemporadas = new  Long[4];
				Long[] olivicultorsId = null;
				
				int indiceArrayOlivicultoresTemporadas = 3;
				for(Iterator it=temporadasIt; it.hasNext();){
					Campanya temporada= (Campanya)it.next();
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Collection olivicultores = oliInfraestructuraEjb.findAllIdAltaDOByCampanyaRA(temporada.getId());
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Long numTafonas = oliInfraestructuraEjb.numTafonasByCampanya(temporada.getId());
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Long numEnvasadoras = oliInfraestructuraEjb.numEnvasadoraByCampanya(temporada.getId());
					if(indiceArrayOlivicultoresTemporadas>= 0){
						arrayOlivicultoresTemporadas[indiceArrayOlivicultoresTemporadas]=olivicultores;
						arrayTemporadasNames[indiceArrayOlivicultoresTemporadas]= temporada.getNom();
						arrayTemporadesId[indiceArrayOlivicultoresTemporadas] = temporada.getId();
						arrayTafonesTemporadas[indiceArrayOlivicultoresTemporadas] = numTafonas;							
						arrayEnvasadoresTemporadas[indiceArrayOlivicultoresTemporadas] = numEnvasadoras;
						indiceArrayOlivicultoresTemporadas--;
					}else{
						break;
					}					
				}
				
				// Campanya actual
				if(arrayTemporadasNames[3]!= null){
					PARAMS.put("pTabla1Titulo5", arrayTemporadasNames[3]);
					PARAMS.put("pTabla1Titulo20", missatge("consulta.informeProductors.campanya", request)+" "+arrayTemporadasNames[3]);
					if(arrayOlivicultoresTemporadas[3]!= null && arrayOlivicultoresTemporadas[3].size()>0){	
						Collection olivicultores3 = arrayOlivicultoresTemporadas[3];
						olivicultoresActual = oliInfraestructuraEjb.findAllAltaDOByCampanyaRA(arrayTemporadesId[3]);//arrayOlivicultoresTemporadas[3];
						int numOlivicultores = olivicultores3.size();
						PARAMS.put("pTabla1Valor4", String.valueOf(numOlivicultores));
						
						olivicultorsId = new Long[olivicultores3.size()];
						
						int i = 0;
						for (Object o: olivicultores3){
							//Olivicultor ol = (Olivicultor)o;
							//olivicultorsId[i] = ol.getId();
							olivicultorsId[i] = (Long)o;
							i++;
						}
						Collection superficieVariedades = oliConsultaEjb.getSuperficiesVarietatsByCampanya(olivicultorsId, arrayTemporadesId[3], false, false);
						Double[] supsDO = {0.0, 0.0, 0.0, 0.0, 0.0};
						Long[] olivsDO = {0L, 0L, 0L, 0L, 0L};
						List<Integer> idVarNODO = new ArrayList<Integer>();
						for (Iterator it = superficieVariedades.iterator(); it.hasNext();){
							Object[] obj = (Object[])it.next();
							Integer idVar = (Integer)obj[0];
							Double sup = (Double)obj[1];
							Long oliv = (Long)obj[2];
							if (idVar <= Constants.VARIETAT_OLIVA_PICUAL || idVar == Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA){
								if(idVar == 26){
									supsDO[2] += sup;
									olivsDO[2] += oliv;
								}else{
									supsDO[idVar.intValue()] = sup;
									olivsDO[idVar.intValue()] = oliv;	
								}
							} else {
								supsDO[4] += sup;
								olivsDO[4] += oliv;
							}
						}
						
						// Superfícies
						supsDO[0] = supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + supsDO[Constants.VARIETAT_OLIVA_PICUAL];
						
						PARAMS.put("pTabla1Valor9", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA]));
						PARAMS.put("pTabla1Valor14", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA]));
						PARAMS.put("pTabla1Valor19", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_PICUAL]));
						PARAMS.put("pTabla1Valor24", numberDecimalFormat.format(supsDO[0]));
						PARAMS.put("pTabla1Valor116", numberDecimalFormat.format(supsDO[4]));
						
						// Arbres
						olivsDO[0] = olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + olivsDO[Constants.VARIETAT_OLIVA_PICUAL];
						PARAMS.put("pTabla1Valor29", olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA].toString());
						PARAMS.put("pTabla1Valor34", olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA].toString());
						PARAMS.put("pTabla1Valor39", olivsDO[Constants.VARIETAT_OLIVA_PICUAL].toString());
						PARAMS.put("pTabla1Valor44", olivsDO[0].toString());
						PARAMS.put("pTabla1Valor117", olivsDO[4].toString());
											
					}else{
						PARAMS.put("pTabla1Valor4", "0");
						PARAMS.put("pTabla1Valor9", "0");
						PARAMS.put("pTabla1Valor9", "0");
						PARAMS.put("pTabla1Valor14", "0");
						PARAMS.put("pTabla1Valor19", "0");
						PARAMS.put("pTabla1Valor24", "0");
						PARAMS.put("pTabla1Valor29", "0");	
						PARAMS.put("pTabla1Valor34", "0");	
						PARAMS.put("pTabla1Valor39", "0");	
						PARAMS.put("pTabla1Valor44", "0");
						PARAMS.put("pTabla1Valor44", "0");
						PARAMS.put("pTabla1Valor116", "0");
						PARAMS.put("pTabla1Valor117", "0");
						
					}
					
					//Tafonas
					if(arrayTafonesTemporadas[3]!= null){
						PARAMS.put("pTabla1Valor49", arrayTafonesTemporadas[3].toString());
					}else{
						PARAMS.put("pTabla1Valor49", "0");
					}
					//Envasadoras
					if(arrayEnvasadoresTemporadas[3]!= null){
						PARAMS.put("pTabla1Valor54", arrayEnvasadoresTemporadas[3].toString());
					}else{
						PARAMS.put("pTabla1Valor54", "0");
					}	
				}				
				
				if(arrayTemporadasNames[2]!= null){
					PARAMS.put("pTabla1Titulo4", arrayTemporadasNames[2]);
					if(arrayOlivicultoresTemporadas[2]!= null && arrayOlivicultoresTemporadas[2].size()>0){
						Collection olivicultores2 = arrayOlivicultoresTemporadas[2];
						int numOlivicultores = (arrayOlivicultoresTemporadas[2]).size();
						PARAMS.put("pTabla1Valor3", String.valueOf(numOlivicultores));
						
						Long[] olivicultorsId2 = new Long[olivicultores2.size()];
						int i = 0;
						for (Object o: olivicultores2){
							//Olivicultor ol = (Olivicultor)o;
							//olivicultorsId2[i] = ol.getId();
							olivicultorsId2[i] = (Long)o;
							i++;
						}
						Collection superficieVariedades = oliConsultaEjb.getSuperficiesVarietatsByCampanya(olivicultorsId2, arrayTemporadesId[2], false, false);
						Double[] supsDO = {0.0, 0.0, 0.0, 0.0, 0.0};
						Long[] olivsDO = {0L, 0L, 0L, 0L, 0L};
						for (Iterator it = superficieVariedades.iterator(); it.hasNext();){
							Object[] obj = (Object[])it.next();
							Integer idVar = (Integer)obj[0];
							Double sup = (Double)obj[1];
							Long oliv = (Long)obj[2];
							if (idVar <= Constants.VARIETAT_OLIVA_PICUAL || idVar == Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA){
								if(idVar == 26){
									supsDO[2] += sup;
									olivsDO[2] += oliv;
								}else{
									supsDO[idVar.intValue()] = sup;
									olivsDO[idVar.intValue()] = oliv;	
								}
							} else {
								supsDO[4] += sup;
								olivsDO[4] += oliv;
							}
						}
						
						// Superfícies
						supsDO[0] = supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + supsDO[Constants.VARIETAT_OLIVA_PICUAL];
						
						PARAMS.put("pTabla1Valor8", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA]));
						PARAMS.put("pTabla1Valor13", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA]));
						PARAMS.put("pTabla1Valor18", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_PICUAL]));
						PARAMS.put("pTabla1Valor23", numberDecimalFormat.format(supsDO[0]));
						PARAMS.put("pTabla1Valor114", numberDecimalFormat.format(supsDO[4]));
						
						// Arbres
						olivsDO[0] = olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + olivsDO[Constants.VARIETAT_OLIVA_PICUAL];
						PARAMS.put("pTabla1Valor28", olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA].toString());
						PARAMS.put("pTabla1Valor33", olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA].toString());
						PARAMS.put("pTabla1Valor38", olivsDO[Constants.VARIETAT_OLIVA_PICUAL].toString());
						PARAMS.put("pTabla1Valor43", olivsDO[0].toString());
						PARAMS.put("pTabla1Valor115", olivsDO[4].toString());
					}
					//Tafonas
					if(arrayTafonesTemporadas[2]!= null){
						PARAMS.put("pTabla1Valor48", arrayTafonesTemporadas[2].toString());
					}else{
						PARAMS.put("pTabla1Valor48", "0");
					}
					//Envasadoras
					if(arrayEnvasadoresTemporadas[2]!= null){
						PARAMS.put("pTabla1Valor53", arrayEnvasadoresTemporadas[2].toString());
					}else{
						PARAMS.put("pTabla1Valor53", "0");
					}
				}
				
				if(arrayTemporadasNames[1]!= null){
					PARAMS.put("pTabla1Titulo3", arrayTemporadasNames[1]);
					if(arrayOlivicultoresTemporadas[1]!= null && arrayOlivicultoresTemporadas[1].size()>0){
						Collection olivicultores1 = arrayOlivicultoresTemporadas[1];
						int numOlivicultores = (arrayOlivicultoresTemporadas[1]).size();
						PARAMS.put("pTabla1Valor2", String.valueOf(numOlivicultores));
						
						Long[] olivicultorsId2 = new Long[olivicultores1.size()];
						int i = 0;
						for (Object o: olivicultores1){
							//Olivicultor ol = (Olivicultor)o;
							//olivicultorsId2[i] = ol.getId();
							olivicultorsId2[i] = (Long)o;
							i++;
						}
						Collection superficieVariedades = oliConsultaEjb.getSuperficiesVarietatsByCampanya(olivicultorsId2, arrayTemporadesId[1], false, false);
						Double[] supsDO = {0.0, 0.0, 0.0, 0.0, 0.0};
						Long[] olivsDO = {0L, 0L, 0L, 0L, 0L};
						for (Iterator it = superficieVariedades.iterator(); it.hasNext();){
							Object[] obj = (Object[])it.next();
							Integer idVar = (Integer)obj[0];
							Double sup = (Double)obj[1];
							Long oliv = (Long)obj[2];
							if (idVar <= Constants.VARIETAT_OLIVA_PICUAL || idVar == Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA){
								if(idVar == 26){
									supsDO[2] += sup;
									olivsDO[2] += oliv;
								}else{
									supsDO[idVar.intValue()] = sup;
									olivsDO[idVar.intValue()] = oliv;	
								}
							} else{
								supsDO[4] += sup;
								olivsDO[4] += oliv;
							}
						}
						
						// Superfícies
						supsDO[0] = supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + supsDO[Constants.VARIETAT_OLIVA_PICUAL];
						
						PARAMS.put("pTabla1Valor7", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA]));
						PARAMS.put("pTabla1Valor12", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA]));
						PARAMS.put("pTabla1Valor17", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_PICUAL]));
						PARAMS.put("pTabla1Valor22", numberDecimalFormat.format(supsDO[0]));
						PARAMS.put("pTabla1Valor112", numberDecimalFormat.format(supsDO[4]));
						
						// Arbres
						olivsDO[0] = olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + olivsDO[Constants.VARIETAT_OLIVA_PICUAL];
						PARAMS.put("pTabla1Valor27", olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA].toString());
						PARAMS.put("pTabla1Valor32", olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA].toString());
						PARAMS.put("pTabla1Valor37", olivsDO[Constants.VARIETAT_OLIVA_PICUAL].toString());
						PARAMS.put("pTabla1Valor42", olivsDO[0].toString());
						PARAMS.put("pTabla1Valor113", olivsDO[4].toString());
					}
					//Tafonas
					if(arrayTafonesTemporadas[1]!= null){
						PARAMS.put("pTabla1Valor47", arrayTafonesTemporadas[1].toString());
					}else{
						PARAMS.put("pTabla1Valor47", "0");
					}
					//Envasadoras
					if(arrayEnvasadoresTemporadas[1]!= null){
						PARAMS.put("pTabla1Valor52", arrayEnvasadoresTemporadas[1].toString());
					}else{
						PARAMS.put("pTabla1Valor52", "0");
					}	
				}
				
				if(arrayTemporadasNames[0]!= null){
					PARAMS.put("pTabla1Titulo2", arrayTemporadasNames[0]);
					if(arrayOlivicultoresTemporadas[0]!= null && arrayOlivicultoresTemporadas[0].size()>0){
						Collection olivicultores0 = arrayOlivicultoresTemporadas[0];
						int numOlivicultores = (arrayOlivicultoresTemporadas[0]).size();
						PARAMS.put("pTabla1Valor1", String.valueOf(numOlivicultores));
						
						Long[] olivicultorsId2 = new Long[olivicultores0.size()];
						int i = 0;
						for (Object o: olivicultores0){
							//Olivicultor ol = (Olivicultor)o;
							//olivicultorsId2[i] = ol.getId();
							olivicultorsId2[i] = (Long)o;
							i++;
						}
						Collection superficieVariedades = oliConsultaEjb.getSuperficiesVarietatsByCampanya(olivicultorsId2, arrayTemporadesId[0], false, false);
						Double[] supsDO = {0.0, 0.0, 0.0, 0.0, 0.0};
						Long[] olivsDO = {0L, 0L, 0L, 0L, 0L};
						for (Iterator it = superficieVariedades.iterator(); it.hasNext();){
							Object[] obj = (Object[])it.next();
							Integer idVar = (Integer)obj[0];
							Double sup = (Double)obj[1];
							Long oliv = (Long)obj[2];
							if (idVar <= Constants.VARIETAT_OLIVA_PICUAL || idVar == Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA){
								if(idVar == 26){
									supsDO[2] += sup;
									olivsDO[2] += oliv;
								}else{
									supsDO[idVar.intValue()] = sup;
									olivsDO[idVar.intValue()] = oliv;	
								}
							} else{
								supsDO[4] += sup;
								olivsDO[4] += oliv;
							}
						}
						
						// Superfícies
						supsDO[0] = supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + supsDO[Constants.VARIETAT_OLIVA_PICUAL];
						
						PARAMS.put("pTabla1Valor6", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_MALLORQUINA]));
						PARAMS.put("pTabla1Valor11", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_ARBEQUINA]));
						PARAMS.put("pTabla1Valor16", numberDecimalFormat.format(supsDO[Constants.VARIETAT_OLIVA_PICUAL]));
						PARAMS.put("pTabla1Valor21", numberDecimalFormat.format(supsDO[0]));
						PARAMS.put("pTabla1Valor110", numberDecimalFormat.format(supsDO[4]));
						
						// Arbres
						olivsDO[0] = olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA] + olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA] + olivsDO[Constants.VARIETAT_OLIVA_PICUAL];
						PARAMS.put("pTabla1Valor26", olivsDO[Constants.VARIETAT_OLIVA_MALLORQUINA].toString());
						PARAMS.put("pTabla1Valor31", olivsDO[Constants.VARIETAT_OLIVA_ARBEQUINA].toString());
						PARAMS.put("pTabla1Valor36", olivsDO[Constants.VARIETAT_OLIVA_PICUAL].toString());
						PARAMS.put("pTabla1Valor41", olivsDO[0].toString());
						PARAMS.put("pTabla1Valor111", olivsDO[4].toString());
					}
					//Tafonas
					if(arrayTafonesTemporadas[0]!= null){
						PARAMS.put("pTabla1Valor46", arrayTafonesTemporadas[0].toString());
					}else{
						PARAMS.put("pTabla1Valor46", "0");
					}
					//Envasadoras
					if(arrayEnvasadoresTemporadas[0]!= null){
						PARAMS.put("pTabla1Valor51", arrayEnvasadoresTemporadas[0].toString());
					}else{
						PARAMS.put("pTabla1Valor51", "0");
					}
				}
				
				
						//Variaciones
				int variacio = 0;
				if(arrayOlivicultoresTemporadas[2]!= null && arrayOlivicultoresTemporadas[2].size()>0 && arrayOlivicultoresTemporadas[3]!= null && arrayOlivicultoresTemporadas[3].size()>0){
					variacio= (((arrayOlivicultoresTemporadas[3]).size() -(arrayOlivicultoresTemporadas[2]).size())*100 )/((Collection)arrayOlivicultoresTemporadas[2]).size() ;
					PARAMS.put("pTabla1Valor5", String.valueOf(variacio)+"%");
				}else if(arrayOlivicultoresTemporadas[2]!= null && arrayOlivicultoresTemporadas[2].size()>0){
					PARAMS.put("pTabla1Valor5", "-100%");
				}else{
					PARAMS.put("pTabla1Valor5", "100%");
				}
				double variacioSuper = 0;
				double valorActual = 0;
				double valorAnterior= 0;
				if(PARAMS.get("pTabla1Valor8")!= null && !PARAMS.get("pTabla1Valor8").equals("0") && PARAMS.get("pTabla1Valor9")!= null && !PARAMS.get("pTabla1Valor9").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor9")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor8")).doubleValue();
					variacioSuper= (( (valorActual -  valorAnterior)*100 ) / valorAnterior) ;
					PARAMS.put("pTabla1Valor10", numberDecimalFormat.format(variacioSuper)+"%");
				}else if(PARAMS.get("pTabla1Valor9")!= null && !PARAMS.get("pTabla1Valor9").equals("0")){
					PARAMS.put("pTabla1Valor10", "100%");
				}else{
					PARAMS.put("pTabla1Valor10", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor13")!= null && !PARAMS.get("pTabla1Valor13").equals("0") && PARAMS.get("pTabla1Valor14")!= null && !PARAMS.get("pTabla1Valor14").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor14")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor13")).doubleValue();
					variacioSuper= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor15", numberDecimalFormat.format(variacioSuper)+"%");
				}else if(PARAMS.get("pTabla1Valor14")!= null && !PARAMS.get("pTabla1Valor14").equals("0")){
					PARAMS.put("pTabla1Valor15", "100%");
				}else{
					PARAMS.put("pTabla1Valor15", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor18")!= null && !PARAMS.get("pTabla1Valor18").equals("0") && PARAMS.get("pTabla1Valor19")!= null && !PARAMS.get("pTabla1Valor19").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor19")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor18")).doubleValue();
					variacioSuper= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor20", numberDecimalFormat.format(variacioSuper)+"%");
				}else if(PARAMS.get("pTabla1Valor19")!= null && !PARAMS.get("pTabla1Valor19").equals("0")){
					PARAMS.put("pTabla1Valor20", "100%");
				}else{
					PARAMS.put("pTabla1Valor20", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor23")!= null && !PARAMS.get("pTabla1Valor23").equals("0") && PARAMS.get("pTabla1Valor24")!= null && !PARAMS.get("pTabla1Valor24").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor24")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor23")).doubleValue();
					variacioSuper= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor25", numberDecimalFormat.format(variacioSuper)+"%");
				}else if(PARAMS.get("pTabla1Valor24")!= null && !PARAMS.get("pTabla1Valor24").equals("0")){
					PARAMS.put("pTabla1Valor25", "100%");
				}else{
					PARAMS.put("pTabla1Valor25", "-100%");
				}
				
				
				
				double variacioArboles = 0;				 
				if(PARAMS.get("pTabla1Valor28")!= null && !PARAMS.get("pTabla1Valor28").equals("0") && PARAMS.get("pTabla1Valor29")!= null && !PARAMS.get("pTabla1Valor29").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor29")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor28")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor30", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor29")!= null && !PARAMS.get("pTabla1Valor29").equals("0")){
					PARAMS.put("pTabla1Valor30", "100%");
				}else{
					PARAMS.put("pTabla1Valor30", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor33")!= null && !PARAMS.get("pTabla1Valor33").equals("0") && PARAMS.get("pTabla1Valor34")!= null && !PARAMS.get("pTabla1Valor34").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor34")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor33")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor35", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor34")!= null){
					PARAMS.put("pTabla1Valor35", "100%");
				}else{
					PARAMS.put("pTabla1Valor35", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor38")!= null && !PARAMS.get("pTabla1Valor38").equals("0") && PARAMS.get("pTabla1Valor39")!= null && !PARAMS.get("pTabla1Valor39").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor39")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor38")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor40", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor39")!= null && !PARAMS.get("pTabla1Valor39").equals("0")){
					PARAMS.put("pTabla1Valor40", "100%");
				}else{
					PARAMS.put("pTabla1Valor40", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor43")!= null && !PARAMS.get("pTabla1Valor43").equals("0") && PARAMS.get("pTabla1Valor44")!= null && !PARAMS.get("pTabla1Valor44").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor44")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor43")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor45", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor44")!= null && !PARAMS.get("pTabla1Valor44").equals("0")){
					PARAMS.put("pTabla1Valor45", "100%");
				}else{
					PARAMS.put("pTabla1Valor45", "-100%");
				}
				
				if(PARAMS.get("pTabla1Valor114")!= null && !PARAMS.get("pTabla1Valor114").equals("0") && PARAMS.get("pTabla1Valor116")!= null && !PARAMS.get("pTabla1Valor116").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor116")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor114")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor118", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor116")!= null && !PARAMS.get("pTabla1Valor116").equals("0")){
					PARAMS.put("pTabla1Valor118", "100%");
				}else{
					PARAMS.put("pTabla1Valor118", "-100%");
				}
				
				
				if(PARAMS.get("pTabla1Valor115")!= null && !PARAMS.get("pTabla1Valor115").equals("0") && PARAMS.get("pTabla1Valor117")!= null && !PARAMS.get("pTabla1Valor117").equals("0")){
					valorActual = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor117")).doubleValue();
					valorAnterior = numberDecimalFormat.parse((String)PARAMS.get("pTabla1Valor115")).doubleValue();
					variacioArboles= ( (valorActual -  valorAnterior)*100 ) / valorAnterior ;
					PARAMS.put("pTabla1Valor119", numberDecimalFormat.format(variacioArboles)+"%");
				}else if(PARAMS.get("pTabla1Valor117")!= null && !PARAMS.get("pTabla1Valor117").equals("0")){
					PARAMS.put("pTabla1Valor119", "100%");
				}else{
					PARAMS.put("pTabla1Valor119", "-100%");
				}
				
				
				double variacioTafonas = 0;	
				if(PARAMS.get("pTabla1Valor48")!= null && !PARAMS.get("pTabla1Valor48").equals("0") && PARAMS.get("pTabla1Valor49")!= null && !PARAMS.get("pTabla1Valor49").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor49")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor48")).doubleValue();
					variacioTafonas= (valorActual -  valorAnterior) ;
					PARAMS.put("pTabla1Valor50", numberDecimalFormat.format(variacioTafonas));
				}else if(PARAMS.get("pTabla1Valor49")!= null && !PARAMS.get("pTabla1Valor49").equals("0")){
					PARAMS.put("pTabla1Valor50", PARAMS.get("pTabla1Valor49"));
				}else{
					PARAMS.put("pTabla1Valor50", "-"+PARAMS.get("pTabla1Valor48"));
				}
				double variacioEnvasadoras = 0;	
				if(PARAMS.get("pTabla1Valor53")!= null && !PARAMS.get("pTabla1Valor53").equals("0") && PARAMS.get("pTabla1Valor54")!= null && !PARAMS.get("pTabla1Valor54").equals("0")){
					valorActual = Double.valueOf((String)PARAMS.get("pTabla1Valor54")).doubleValue();
					valorAnterior = Double.valueOf((String)PARAMS.get("pTabla1Valor53")).doubleValue();
					variacioEnvasadoras= (valorActual -  valorAnterior) ;
					PARAMS.put("pTabla1Valor55", numberDecimalFormat.format(variacioEnvasadoras));
				}else if(PARAMS.get("pTabla1Valor54")!= null && !PARAMS.get("pTabla1Valor54").equals("0")){
					PARAMS.put("pTabla1Valor55", PARAMS.get("pTabla1Valor54"));
				}else{
					PARAMS.put("pTabla1Valor55", "-"+PARAMS.get("pTabla1Valor53"));
				}
				
				
				//***************************************************************************************************
				//*****************************    Valores Tabla 2     **********************************************
				//***************************************************************************************************
				
				Object[] arrayFincasBySuperficie = getArrayFincasBySuperficie(olivicultoresActual);
				
				//menor o igual a 1
				List fincas1 = (List)arrayFincasBySuperficie[0];
				String[] datosSuperficie1 = getArrayDatosFincasBySuperficie(fincas1); //(numOlivicultores,total,totalMallorquina,totalArbequina,totalPicual)
				//PARAMS.put("pTabla1Valor56", datosSuperficie1[0]);
				PARAMS.put("pTabla1Valor56", Integer.toString(fincas1.size()));
				PARAMS.put("pTabla1Valor57", datosSuperficie1[1]);
				PARAMS.put("pTabla1Valor58", datosSuperficie1[2]);
				PARAMS.put("pTabla1Valor59", datosSuperficie1[3]);
				PARAMS.put("pTabla1Valor60", datosSuperficie1[4]);
				PARAMS.put("pTabla1Valor121", datosSuperficie1[5]);
				
				//entre 1.01 y 5
				List fincas1y5 = (List)arrayFincasBySuperficie[1];
				String[] datosSuperficie1y5 = getArrayDatosFincasBySuperficie(fincas1y5);
				//PARAMS.put("pTabla1Valor61", datosSuperficie1y5[0]);
				PARAMS.put("pTabla1Valor61", Integer.toString(fincas1y5.size()));
				PARAMS.put("pTabla1Valor62", datosSuperficie1y5[1]);
				PARAMS.put("pTabla1Valor63", datosSuperficie1y5[2]);
				PARAMS.put("pTabla1Valor64", datosSuperficie1y5[3]);
				PARAMS.put("pTabla1Valor65", datosSuperficie1y5[4]);
				PARAMS.put("pTabla1Valor122", datosSuperficie1y5[5]);
				
				//entre 5.01 y 10
				List fincas5y10 = (List)arrayFincasBySuperficie[2];
				String[] datosSuperficie5y10 = getArrayDatosFincasBySuperficie(fincas5y10);
				//PARAMS.put("pTabla1Valor66", datosSuperficie5y10[0]);
				PARAMS.put("pTabla1Valor66", Integer.toString(fincas5y10.size()));
				PARAMS.put("pTabla1Valor67", datosSuperficie5y10[1]);
				PARAMS.put("pTabla1Valor68", datosSuperficie5y10[2]);
				PARAMS.put("pTabla1Valor69", datosSuperficie5y10[3]);
				PARAMS.put("pTabla1Valor70", datosSuperficie5y10[4]);
				PARAMS.put("pTabla1Valor123", datosSuperficie5y10[5]);
				
				//entre 10.01 y 20
				List fincas10y20 = (List)arrayFincasBySuperficie[3];
				String[] datosSuperficie10y20 = getArrayDatosFincasBySuperficie(fincas10y20);
				//PARAMS.put("pTabla1Valor71", datosSuperficie10y20[0]);
				PARAMS.put("pTabla1Valor71", Integer.toString(fincas10y20.size()));
				PARAMS.put("pTabla1Valor72", datosSuperficie10y20[1]);
				PARAMS.put("pTabla1Valor73", datosSuperficie10y20[2]);
				PARAMS.put("pTabla1Valor74", datosSuperficie10y20[3]);
				PARAMS.put("pTabla1Valor75", datosSuperficie10y20[4]);
				PARAMS.put("pTabla1Valor124", datosSuperficie10y20[5]);
				//mas de 20
				List fincas20 = (List)arrayFincasBySuperficie[4];
				String[] datosSuperficie20 = getArrayDatosFincasBySuperficie(fincas20);
				//PARAMS.put("pTabla1Valor76", datosSuperficie20[0]);
				PARAMS.put("pTabla1Valor76", Integer.toString(fincas20.size()));
				PARAMS.put("pTabla1Valor77", datosSuperficie20[1]);
				PARAMS.put("pTabla1Valor78", datosSuperficie20[2]);
				PARAMS.put("pTabla1Valor79", datosSuperficie20[3]);
				PARAMS.put("pTabla1Valor80", datosSuperficie20[4]);
				PARAMS.put("pTabla1Valor125", datosSuperficie20[5]);
				    
					
				//double totalOli = numberDecimalFormat.parse(datosSuperficie1[0]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[0]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[0]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[0]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[0]).doubleValue();
				int totalFinques = fincas1.size() + fincas1y5.size() + fincas5y10.size() + fincas10y20.size() + fincas20.size();
				double totalTotal = numberDecimalFormat.parse(datosSuperficie1[1]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[1]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[1]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[1]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[1]).doubleValue();
				double totalMallorquina = numberDecimalFormat.parse(datosSuperficie1[2]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[2]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[2]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[2]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[2]).doubleValue();
				double totalArbequina = numberDecimalFormat.parse(datosSuperficie1[3]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[3]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[3]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[3]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[3]).doubleValue();
				double totalPicual = numberDecimalFormat.parse(datosSuperficie1[4]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[4]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[4]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[4]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[4]).doubleValue();
				double totalAltres = numberDecimalFormat.parse(datosSuperficie1[5]).doubleValue()+numberDecimalFormat.parse(datosSuperficie1y5[5]).doubleValue()+numberDecimalFormat.parse(datosSuperficie5y10[5]).doubleValue()+numberDecimalFormat.parse(datosSuperficie10y20[5]).doubleValue()+	numberDecimalFormat.parse(datosSuperficie20[5]).doubleValue();
				//PARAMS.put("pTabla1Valor81", numberDecimalFormat.format(totalOli));
				PARAMS.put("pTabla1Valor81", Integer.toString(totalFinques));
				PARAMS.put("pTabla1Valor82", numberDecimalFormat.format(totalTotal));
				PARAMS.put("pTabla1Valor83", numberDecimalFormat.format(totalMallorquina));
				PARAMS.put("pTabla1Valor84", numberDecimalFormat.format(totalArbequina));
				PARAMS.put("pTabla1Valor85", numberDecimalFormat.format(totalPicual));
				PARAMS.put("pTabla1Valor126", numberDecimalFormat.format(totalAltres));
			
				//***************************************************************************************************
				//*****************************    Valores Tabla 3     **********************************************
				//***************************************************************************************************
				
				//menor o igual a 1
				
				String[] datosNumOliveres1 = getArrayDatosNumOliFincasBySuperficie(fincas1); //(total,totalMallorquina,totalArbequina,totalPicual, totalAltres)
				PARAMS.put("pTabla3Valor86", datosNumOliveres1[0]);
				PARAMS.put("pTabla3Valor87", datosNumOliveres1[1]);
				PARAMS.put("pTabla3Valor88", datosNumOliveres1[2]);
				PARAMS.put("pTabla3Valor89", datosNumOliveres1[3]);
				PARAMS.put("pTabla1Valor128", datosNumOliveres1[4]);
				
				//entre 1.01 y 5
				String[] datosNumOliveres1y5 = getArrayDatosNumOliFincasBySuperficie(fincas1y5);
				PARAMS.put("pTabla3Valor90", datosNumOliveres1y5[0]);
				PARAMS.put("pTabla3Valor91", datosNumOliveres1y5[1]);
				PARAMS.put("pTabla3Valor92", datosNumOliveres1y5[2]);
				PARAMS.put("pTabla3Valor93", datosNumOliveres1y5[3]);
				PARAMS.put("pTabla1Valor129", datosNumOliveres1y5[4]);				
				//entre 5.01 y 10
				String[] datosNumOliveres5y10 = getArrayDatosNumOliFincasBySuperficie(fincas5y10);
				PARAMS.put("pTabla3Valor94", datosNumOliveres5y10[0]);
				PARAMS.put("pTabla3Valor95", datosNumOliveres5y10[1]);
				PARAMS.put("pTabla3Valor96", datosNumOliveres5y10[2]);
				PARAMS.put("pTabla3Valor97", datosNumOliveres5y10[3]);
				PARAMS.put("pTabla1Valor130", datosNumOliveres5y10[4]);
								
				//entre 10.01 y 20
				String[] datosNumOliveres10y20 = getArrayDatosNumOliFincasBySuperficie(fincas10y20);
				PARAMS.put("pTabla3Valor98", datosNumOliveres10y20[0]);
				PARAMS.put("pTabla3Valor99", datosNumOliveres10y20[1]);
				PARAMS.put("pTabla3Valor100", datosNumOliveres10y20[2]);
				PARAMS.put("pTabla3Valor101", datosNumOliveres10y20[3]);
				PARAMS.put("pTabla1Valor131", datosNumOliveres10y20[4]);
								//mas de 20
				String[] datosNumOliveres20 = getArrayDatosNumOliFincasBySuperficie(fincas20);
				PARAMS.put("pTabla3Valor102", datosNumOliveres20[0]);
				PARAMS.put("pTabla3Valor103", datosNumOliveres20[1]);
				PARAMS.put("pTabla3Valor104", datosNumOliveres20[2]);
				PARAMS.put("pTabla3Valor105", datosNumOliveres20[3]);
				PARAMS.put("pTabla1Valor132", datosNumOliveres20[4]);				    
					
				double totalNumOliveres = numberDecimalFormat.parse(datosNumOliveres1[0]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres1y5[0]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres5y10[0]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres10y20[0]).doubleValue()+	numberDecimalFormat.parse(datosNumOliveres20[0]).doubleValue();
				double totalNumOliveresMallorquina = numberDecimalFormat.parse(datosNumOliveres1[1]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres1y5[1]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres5y10[1]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres10y20[1]).doubleValue()+	numberDecimalFormat.parse(datosNumOliveres20[1]).doubleValue();
				double totalNumOliveresArbequina = numberDecimalFormat.parse(datosNumOliveres1[2]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres1y5[2]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres5y10[2]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres10y20[2]).doubleValue()+	numberDecimalFormat.parse(datosNumOliveres20[2]).doubleValue();
				double totalNumOliveresPicual = numberDecimalFormat.parse(datosNumOliveres1[3]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres1y5[3]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres5y10[3]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres10y20[3]).doubleValue()+	numberDecimalFormat.parse(datosNumOliveres20[3]).doubleValue();
				double totalNumOliveresAltres = numberDecimalFormat.parse(datosNumOliveres1[4]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres1y5[4]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres5y10[4]).doubleValue()+numberDecimalFormat.parse(datosNumOliveres10y20[4]).doubleValue()+	numberDecimalFormat.parse(datosNumOliveres20[4]).doubleValue();
				
				PARAMS.put("pTabla3Valor106", numberDecimalFormat.format(totalNumOliveres));
				PARAMS.put("pTabla3Valor107", numberDecimalFormat.format(totalNumOliveresMallorquina));
				PARAMS.put("pTabla3Valor108", numberDecimalFormat.format(totalNumOliveresArbequina));
				PARAMS.put("pTabla3Valor109", numberDecimalFormat.format(totalNumOliveresPicual));
				PARAMS.put("pTabla1Valor133", numberDecimalFormat.format(totalNumOliveresAltres));
			
			
			// Compilacion
			logger.debug("3.- Leyendo el stream del jrxml (" + STREAM + ")");
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(STREAM);
			logger.debug("4.- Compilando el report");
			JasperReport report = JasperCompileManager.compileReport(is);

			// Rellenando el report
			logger.debug("5.- Cargando los datos al report");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, PARAMS);

			// Configure exporter and set parameters
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "store");
			response.setHeader("Pragma", "cache");            
			response.setHeader("Content-Disposition", "attachment; filename=" + FILE_NAME + ".pdf");

			logger.debug("6.- Exportando el report a pdf");
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			for (int i = 0; i < bytes.length; i ++) {
				out.write(bytes[i]);
			}
			out.close();
			out.flush();

			logger.debug("handleRequest fin");
		} catch (Exception ex) {
			logger.error("handleRequest failed. EXCEPTION", ex);
			throw new ServletException(ex);
		}

		return null;
	}


	/**
	 * Informa los parametros
	 * @param request
	 * @return
	 */
	private HashMap setParams(HttpServletRequest request) {
		HashMap params = new HashMap();
		params.put("pLogo","es/caib/gestoli/logic/resources/do.jpg");
		params.put("pTabla1Titulo1", missatge("consulta.informeProductors.do_oli", request));
		params.put("pTabla1Titulo6", missatge("consulta.informeProductors.variacio", request));
		params.put("pTabla1Titulo7", missatge("consulta.informeProductors.numOlivicultores", request));
		params.put("pTabla1Titulo8", missatge("consulta.informeProductors.superficie", request));
		params.put("pTabla1Titulo9", missatge("consulta.informeProductors.mallorquina", request));
		params.put("pTabla1Titulo10", missatge("consulta.informeProductors.arbequina", request));
		params.put("pTabla1Titulo11", missatge("consulta.informeProductors.picual", request));
		params.put("pTabla1Titulo12", missatge("consulta.informeProductors.total", request));
		params.put("pTabla1Titulo13", missatge("consulta.informeProductors.numArboles", request));
		params.put("pTabla1Titulo14", missatge("consulta.informeProductors.mallorquina", request));
		params.put("pTabla1Titulo15", missatge("consulta.informeProductors.arbequina", request));
		params.put("pTabla1Titulo16", missatge("consulta.informeProductors.picual", request));
		params.put("pTabla1Titulo17", missatge("consulta.informeProductors.total", request));
		params.put("pTabla1Titulo18", missatge("consulta.informeProductors.tafones", request));
		params.put("pTabla1Titulo19", missatge("consulta.informeProductors.envasadoras", request));		
		//params.put("pTabla1Titulo21", missatge("consulta.informeProductors.numOlivicultores", request));
		params.put("pTabla1Titulo21", missatge("consulta.informeProductors.finques", request));
		params.put("pTabla1Titulo22", missatge("consulta.informeProductors.superficieHa", request));
		params.put("pTabla1Titulo23", missatge("consulta.informeProductors.total", request));
		params.put("pTabla1Titulo24", missatge("consulta.informeProductors.mallorquina", request));
		params.put("pTabla1Titulo25", missatge("consulta.informeProductors.arbequina", request));
		params.put("pTabla1Titulo26", missatge("consulta.informeProductors.picual", request));
		params.put("pTabla1Titulo27", missatge("consulta.informeProductors.menos1", request));
		params.put("pTabla1Titulo28", missatge("consulta.informeProductors.entre1y5", request));
		params.put("pTabla1Titulo29", missatge("consulta.informeProductors.entre5y10", request));
		params.put("pTabla1Titulo30", missatge("consulta.informeProductors.entre10y20", request));
		params.put("pTabla1Titulo31", missatge("consulta.informeProductors.mas20", request));
		params.put("pTabla1Titulo32", missatge("consulta.informeProductors.totales", request));	
		params.put("pTabla1Titulo33", missatge("consulta.informeProductors.altres", request));
		params.put("pTabla1Titulo34", missatge("consulta.informeProductors.altres", request));
		params.put("pTabla1Titulo120", missatge("consulta.informeProductors.altres", request));
		params.put("pTabla1Titulo127", missatge("consulta.informeProductors.altres", request));
	
		return params;
	}
	
	
	private Double[] getSuperficiesVariedades(Collection olivicultores ) throws RemoteException {
		Double[] superficieVariedades = new Double[4];
		try{
			for (Iterator olivicultoresIt= olivicultores.iterator(); olivicultoresIt.hasNext();) {
				Olivicultor olivicultor = (Olivicultor) olivicultoresIt.next();
				oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
				Iterator itDescomposicio = oliConsultaEjb.findDescomposicioPlantacioPerOlivicultor(olivicultor.getId()).iterator();
				while(itDescomposicio.hasNext()){
					DescomposicioPlantacio descomposicionPlantacion = (DescomposicioPlantacio)itDescomposicio.next();
					if(descomposicionPlantacion.getPlantacio().getFinca().getActiu().booleanValue() && descomposicionPlantacion.getPlantacio().getActiu().booleanValue()){
						if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusMallorquina.intValue()){
							if(superficieVariedades[0]!= null ){
								superficieVariedades[0] = Double.valueOf(String.valueOf(superficieVariedades[0].doubleValue()+descomposicionPlantacion.getSuperficie().doubleValue()));
							}else{
								superficieVariedades[0] = descomposicionPlantacion.getSuperficie();
							}
						}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusArbequina.intValue()){
							if(superficieVariedades[1]!= null ){
								superficieVariedades[1] = Double.valueOf(String.valueOf(superficieVariedades[1].doubleValue()+descomposicionPlantacion.getSuperficie().doubleValue()));
							}else{
								superficieVariedades[1] = descomposicionPlantacion.getSuperficie();
							}
						}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusPicual.intValue()){
							if(superficieVariedades[2]!= null ){
								superficieVariedades[2] = Double.valueOf(String.valueOf(superficieVariedades[2].doubleValue()+descomposicionPlantacion.getSuperficie().doubleValue()));
							}else{
								superficieVariedades[2] = descomposicionPlantacion.getSuperficie();
							}
						}
					}					
				}
			}
			
			Double totalSuper = null;
			if(superficieVariedades[0]!= null){
				if(totalSuper!= null){
					totalSuper = Double.valueOf(String.valueOf(totalSuper.doubleValue() + superficieVariedades[0].doubleValue()));
				}else{
					totalSuper = superficieVariedades[0];
				}
			}
			if(superficieVariedades[1]!= null){
				if(totalSuper!= null){
					totalSuper = Double.valueOf(String.valueOf(totalSuper.doubleValue() + superficieVariedades[1].doubleValue()));
				}else{
					totalSuper = superficieVariedades[1];
				}
			}
			if(superficieVariedades[2]!= null){
				if(totalSuper!= null){
					totalSuper = Double.valueOf(String.valueOf(totalSuper.doubleValue() + superficieVariedades[2].doubleValue()));
				}else{
					totalSuper = superficieVariedades[2];
				}
			}			
			superficieVariedades[3]=  totalSuper;
			                   
		}catch (Exception e) {
			try{
				logger.error("getSuperficiesVaruiedades failed. EXCEPTION", e);
				throw new ServletException(e);
			}catch (Exception ex) {			
			}			
		}		
	
		return superficieVariedades;
	}

	private Integer[] getArbolesVariedades(Collection olivicultores ) throws RemoteException {
		Integer[] arbolesVariedades = new Integer[4];
		try{
			for (Iterator olivicultoresIt= olivicultores.iterator(); olivicultoresIt.hasNext();) {
				Olivicultor olivicultor = (Olivicultor) olivicultoresIt.next();
				oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
				Iterator itDescomposicio = oliConsultaEjb.findDescomposicioPlantacioPerOlivicultor(olivicultor.getId()).iterator();
				while(itDescomposicio.hasNext()){
					DescomposicioPlantacio descomposicionPlantacion = (DescomposicioPlantacio)itDescomposicio.next();
					if(descomposicionPlantacion.getPlantacio().getFinca().getActiu().booleanValue() && descomposicionPlantacion.getPlantacio().getActiu().booleanValue()){
						if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusMallorquina.intValue()){
							if(arbolesVariedades[0]!= null ){
								arbolesVariedades[0] = Integer.valueOf(String.valueOf(arbolesVariedades[0].intValue()+descomposicionPlantacion.getNumeroOliveres().intValue()));
							}else{
								arbolesVariedades[0] = descomposicionPlantacion.getNumeroOliveres();
							}
						}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusArbequina.intValue()){
							if(arbolesVariedades[1]!= null ){
								arbolesVariedades[1] = Integer.valueOf(String.valueOf(arbolesVariedades[1].intValue()+descomposicionPlantacion.getNumeroOliveres().intValue()));
							}else{
								arbolesVariedades[1] = descomposicionPlantacion.getNumeroOliveres();
							}
						}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusPicual.intValue()){
							if(arbolesVariedades[2]!= null ){
								arbolesVariedades[2] = Integer.valueOf(String.valueOf(arbolesVariedades[2].intValue()+descomposicionPlantacion.getNumeroOliveres().intValue()));
							}else{
								arbolesVariedades[2] = descomposicionPlantacion.getNumeroOliveres();
							}
						}
					}
				}
			}
			
			Integer totalSuper = null;
			if(arbolesVariedades[0]!= null){
				if(totalSuper!= null){
					totalSuper = Integer.valueOf(String.valueOf(totalSuper.intValue() + arbolesVariedades[0].intValue()));
				}else{
					totalSuper = arbolesVariedades[0];
				}
			}
			if(arbolesVariedades[1]!= null){
				if(totalSuper!= null){
					totalSuper = Integer.valueOf(String.valueOf(totalSuper.intValue() + arbolesVariedades[1].intValue()));
				}else{
					totalSuper = arbolesVariedades[1];
				}
			}
			if(arbolesVariedades[2]!= null){
				if(totalSuper!= null){
					totalSuper = Integer.valueOf(String.valueOf(totalSuper.intValue() + arbolesVariedades[2].intValue()));
				}else{
					totalSuper = arbolesVariedades[2];
				}
			}			
			arbolesVariedades[3]=  totalSuper;
			                   
		}catch (Exception e) {
			try{
				logger.error("getArbolesVariedades failed. EXCEPTION", e);
				throw new ServletException(e);
			}catch (Exception ex) {			
			}			
		}		
	
		return arbolesVariedades;
	}
	
	
	private Object[] getArrayFincasBySuperficie(Collection olivicultoresActual ){
		Object[] arrayFincasBySuperficie = new  Object[5];
		List fincas1 = new ArrayList();
		List fincas1y5 = new ArrayList();
		List fincas5y10 = new ArrayList();
		List fincas10y20 = new ArrayList();
		List fincas20 = new ArrayList();
		if(olivicultoresActual!= null && olivicultoresActual.size()>0){
			for (Iterator olivicultoresActualIt= olivicultoresActual.iterator(); olivicultoresActualIt.hasNext();) {
				Olivicultor olivicultor = (Olivicultor) olivicultoresActualIt.next();
				for (Iterator fincasolivicultorIt= olivicultor.getFincas().iterator(); fincasolivicultorIt.hasNext();) {
					Finca finca = (Finca)fincasolivicultorIt.next();
					if(finca.getActiu().booleanValue()){
						double supFinca = 0;
						for (Iterator plantacionesIt= finca.getPlantacios().iterator(); plantacionesIt.hasNext();){
							Plantacio plantacio = (Plantacio)plantacionesIt.next();
							if(plantacio.getActiu().booleanValue()){
								for (Iterator descompPlantacionIt= plantacio.getDescomposicioPlantacios().iterator(); descompPlantacionIt.hasNext();){
									DescomposicioPlantacio descomposicionPlantacio = (DescomposicioPlantacio)descompPlantacionIt.next();
									if(!descomposicionPlantacio.getVarietatOliva().getOlivaTaula()){	
										supFinca+= descomposicionPlantacio.getSuperficie().doubleValue();	
									}
								}
							}
						}
						if(supFinca<=1){
							fincas1.add(finca);
						}else if(supFinca>1 && supFinca<=5){
							fincas1y5.add(finca);
						}else if(supFinca>5 && supFinca<=10){
							fincas5y10.add(finca);
						}else if(supFinca>10 && supFinca<=20){
							fincas10y20.add(finca);
						}else{
							fincas20.add(finca);
						}
					}
					
				}
			}					
		}
		arrayFincasBySuperficie[0]=fincas1;
		arrayFincasBySuperficie[1]=fincas1y5;
		arrayFincasBySuperficie[2]=fincas5y10;
		arrayFincasBySuperficie[3]=fincas10y20;
		arrayFincasBySuperficie[4]=fincas20;
		return arrayFincasBySuperficie;
	}

	private String[] getArrayDatosFincasBySuperficie(List fincas){
		DecimalFormat numberDecimalFormat = new DecimalFormat();
		numberDecimalFormat.setMaximumFractionDigits(2);
		String[] datosSuperficie = new String[6]; //(numOlivicultores,total,totalMallorquina,totalArbequina,totalPicual, total altres)
		double numOlivicultores = 0;
		double total = 0;
		double totalMallorquina = 0;
		double totalArbequina = 0;
		double totalPicual = 0;
		double totalAltres = 0;
		List olivicultores = new ArrayList();
		for(int i=0; i<fincas.size(); i++){
			Finca finca = (Finca)fincas.get(i);
			boolean soloExp = true;
			for (Iterator plantacionesIt= finca.getPlantacios().iterator(); plantacionesIt.hasNext();){
				Plantacio plantacio = (Plantacio)plantacionesIt.next();
				if(plantacio.getActiu().booleanValue()){
					for (Iterator descompPlantacionIt= plantacio.getDescomposicioPlantacios().iterator(); descompPlantacionIt.hasNext();){
						DescomposicioPlantacio descomposicionPlantacion = (DescomposicioPlantacio)descompPlantacionIt.next();
						if(descomposicionPlantacion.getSuperficie()!= null && ( 
								!descomposicionPlantacion.getVarietatOliva().getOlivaTaula() && 
								!descomposicionPlantacion.getVarietatOliva().getExperimental())){
							
							if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusMallorquina.intValue()){
								total+= descomposicionPlantacion.getSuperficie().doubleValue();
								totalMallorquina+= 	descomposicionPlantacion.getSuperficie().doubleValue();
								soloExp = false;
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusArbequina.intValue()){
								total+= descomposicionPlantacion.getSuperficie().doubleValue();
								totalArbequina+= 	descomposicionPlantacion.getSuperficie().doubleValue();
								soloExp = false;
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusPicual.intValue()){
								total+= descomposicionPlantacion.getSuperficie().doubleValue();
								totalPicual+= 	descomposicionPlantacion.getSuperficie().doubleValue();
								soloExp = false;
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusMallorquina.intValue() &&
									descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusArbequina.intValue() && 
									descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusPicual.intValue()){
								total+= descomposicionPlantacion.getSuperficie().doubleValue();
								totalAltres += descomposicionPlantacion.getSuperficie().doubleValue();
								soloExp = false;
							}
							
						}
						
					}
				}
			}
			if(!soloExp){
				Olivicultor oliv = (Olivicultor)finca.getOlivicultor();
				if(!olivicultores.contains(oliv.getId())){
					olivicultores.add(oliv);
					numOlivicultores++;
				}	
			}					
		}
		
		datosSuperficie[0]= numberDecimalFormat.format(numOlivicultores);
		datosSuperficie[1]= numberDecimalFormat.format(total);
		datosSuperficie[2]= numberDecimalFormat.format(totalMallorquina);
		datosSuperficie[3]= numberDecimalFormat.format(totalArbequina);
		datosSuperficie[4]= numberDecimalFormat.format(totalPicual);
		datosSuperficie[5]= numberDecimalFormat.format(totalAltres);
		return datosSuperficie;
	}

	private String[] getArrayDatosNumOliFincasBySuperficie(List fincas){
		DecimalFormat numberDecimalFormat = new DecimalFormat();
		numberDecimalFormat.setMaximumFractionDigits(2);
		String[] datosNumOliveres = new String[5]; //(total,totalMallorquina,totalArbequina,totalPicual, totalAltres)
		
		double total = 0;
		double totalMallorquina = 0;
		double totalArbequina = 0;
		double totalPicual = 0;
		double totalAltres = 0;
		for(int i=0; i<fincas.size(); i++){
			Finca finca = (Finca)fincas.get(i);
			for (Iterator plantacionesIt= finca.getPlantacios().iterator(); plantacionesIt.hasNext();){
				Plantacio plantacio = (Plantacio)plantacionesIt.next();
				if(plantacio.getActiu().booleanValue()){
					for (Iterator descompPlantacionIt= plantacio.getDescomposicioPlantacios().iterator(); descompPlantacionIt.hasNext();){
						DescomposicioPlantacio descomposicionPlantacion = (DescomposicioPlantacio)descompPlantacionIt.next();
						if(descomposicionPlantacion.getNumeroOliveres()!= null && !descomposicionPlantacion.getVarietatOliva().getOlivaTaula()){
							
							if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusMallorquina.intValue()){
								total+= descomposicionPlantacion.getNumeroOliveres().doubleValue();
								totalMallorquina+= 	descomposicionPlantacion.getNumeroOliveres().doubleValue();							
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusArbequina.intValue()){
								total+= descomposicionPlantacion.getNumeroOliveres().doubleValue();
								totalArbequina+= 	descomposicionPlantacion.getNumeroOliveres().doubleValue();							
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() == tipusPicual.intValue()){
								total+= descomposicionPlantacion.getNumeroOliveres().doubleValue();
								totalPicual+= 	descomposicionPlantacion.getNumeroOliveres().doubleValue();													
							}else if(descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusMallorquina.intValue() &&
									descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusArbequina.intValue() && 
									descomposicionPlantacion.getVarietatOliva().getId().intValue() != tipusPicual.intValue()){
								total+= descomposicionPlantacion.getNumeroOliveres().doubleValue();
								totalAltres+= 	descomposicionPlantacion.getNumeroOliveres().doubleValue();													
							}
						}
						
					}
				}
			}
							
		}		
		datosNumOliveres[0]= numberDecimalFormat.format(total);
		datosNumOliveres[1]= numberDecimalFormat.format(totalMallorquina);
		datosNumOliveres[2]= numberDecimalFormat.format(totalArbequina);
		datosNumOliveres[3]= numberDecimalFormat.format(totalPicual);
		datosNumOliveres[4]= numberDecimalFormat.format(totalAltres);
		return datosNumOliveres;
	}
	/**
	 * Recupera un texto del resource boundle
	 * @param clave
	 * @param request
	 * @return
	 */
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getMessageSource().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}


	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}


	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}


	/**
	 * Metodo 'getResourceBundle'
	 * @return el resourceBundle
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}


	/**
	 * Metodo 'setResourceBundle'
	 * @param resourceBundle el resourceBundle a modificar
	 */
	public void setMessageSource(
			ReloadableResourceBundleMessageSource resourceBundle) {
		this.messageSource = resourceBundle;
	}


	/**
	 * Metodo 'getLocaleResolver'
	 * @return el localeResolver
	 */
	public CookieLocaleResolver getLocaleResolver() {
		return localeResolver;
	}


	/**
	 * Metodo 'setLocaleResolver'
	 * @param localeResolver el localeResolver a modificar
	 */
	public void setLocaleResolver(CookieLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
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


	public Long getTipusArbequina() {
		return tipusArbequina;
	}


	public void setTipusArbequina(Long tipusArbequina) {
		this.tipusArbequina = tipusArbequina;
	}


	public Long getTipusMallorquina() {
		return tipusMallorquina;
	}


	public void setTipusMallorquina(Long tipusMallorquina) {
		this.tipusMallorquina = tipusMallorquina;
	}


	public Long getTipusMallorquinaTaula() {
		return tipusMallorquinaTaula;
	}


	public void setTipusMallorquinaTaula(Long tipusMallorquinaTaula) {
		this.tipusMallorquinaTaula = tipusMallorquinaTaula;
	}


	public Long getTipusPicual() {
		return tipusPicual;
	}


	public void setTipusPicual(Long tipusPicual) {
		this.tipusPicual = tipusPicual;
	}


	public Long getTipusExperimental() {
		return tipusExperimental;
	}


	public void setTipusExperimental(Long tipusExperimental) {
		this.tipusExperimental = tipusExperimental;
	}


	public String getCampanyaSessionKey() {
		return campanyaSessionKey;
	}


	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}


}