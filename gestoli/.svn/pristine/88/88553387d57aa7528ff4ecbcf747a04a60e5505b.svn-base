/**
 * ProcesSortidaFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.spring.views.ExcelHssfGet;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.Producte;
import es.caib.gestoli.logic.model.Provincia;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOliFacturacio;



/**
 * <p>Controlador per a l'acció d'executar un proces d'entrada
 * de oliva.</p>
 * <p>Aquest controlador distingueix entre les peticions del
 * tipus GET y les de tipus POST. Si la petición es de tipus POST
 * s'executa l'acció de creació o d'edició. Si la petició es de
 * tipo GET només mostra la página.
 *
 * @author cperez <cperez@at4.net>
 */
public class ImportacioProcesSortidaFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ImportacioProcesSortidaFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String seleccioSessionKeyOrigen;
    private String establimentSessionKey;
    private String venda;
    private String entrada;
    private String canviZona;
    private String motiuPromocio;
    private String motiuConsumPropi;
    private String motiuPerdua;
    private String motiuConsumidorFinal;

    //    private String pendentQualificar;
	private String successView;
    
	private HibernateTemplate hibernateTemplate;
    
    /**
     * Es crida quan s'accepten les modificacions a l'objecte. Només
     * s'executa aquesta funció en el cas de que s'hagi executat la
     * validació sense produïr cap error.
     * 
     * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	
    	ImportacioProcesSortidaCommand importacio = (ImportacioProcesSortidaCommand)command;
    	HttpSession session = request.getSession();
    	Establiment establiment = (Establiment)session.getAttribute(establimentSessionKey);

    	Map myModel = new HashMap();
    	String forward = getSuccessView();
    	
    	try{
    		
	    	/*if (importacio.getTipusSortida() != null && !"".equals(importacio.getTipusSortida())){
	    		myModel.put("tipusSortida", importacio.getTipusSortida());
	    	}
	    	*/
	    	// Recollim arxiu
			Arxiu arxiu = null;
	    	MultipartFile[] files = getFiles(request);
			if (files != null) {
				if(files.length>=1){
					MultipartFile file = files[0];
					if(file.getSize()>0){
						arxiu = new Arxiu();
						arxiu.setId(importacio.getArxiu());
						arxiu.setBinari(file.getBytes());
						arxiu.setMime(file.getContentType());
						arxiu.setNom(file.getOriginalFilename());
						arxiu.setTamany(new Integer(file.getBytes().length));
					}
				}
			}
			
			if (arxiu != null){
				
				ExcelHssfGet excelGet = new ExcelHssfGet();
				List cellDataList = excelGet.readExcelByteArray(arxiu.getBinari());
				SortidaOliFacturacio sortida;
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Long idImportacio = oliInfraestructuraEjb.sortidaOliFacturacioDarreraImportacio() + Long.valueOf("1");
				forward += "?id=" + idImportacio;
				
				for (int i = 1; i < cellDataList.size(); i++) {
					boolean ok = true;
					String error = null;
					sortida = new SortidaOliFacturacio();

					try{
						
						sortida.setIdImportacio(idImportacio);
						sortida.setNomArxiu(arxiu.getNom());
						sortida.setAccioSortida(venda);
						sortida.setDataInsercio(new Date());
					
						List cellTempList = (List) cellDataList.get(i);
					
						for (int x = 0; x < cellTempList.size(); x++) {
							String stringCellValue;
							/*try{
								XSSFCell hssfCell = (XSSFCell) cellTempList.get(x);
								stringCellValue = hssfCell.toString();
							} catch (Exception e) {
								HSSFCell hssfCell = (HSSFCell) cellTempList.get(x);
								stringCellValue = hssfCell.toString();
							}*/
							stringCellValue = (String) cellTempList.get(x);
							try{
								switch (x){
									//case(0): sortida.setTipusSortida(stringCellValue);	break; // lot / deposit
									case(0): sortida.setPais(stringCellValue);      	break;
									case(1): sortida.setProvincia(stringCellValue);		break;
									case(2): sortida.setMunicipi(stringCellValue);		break;
									case(3): sortida.setLot(stringCellValue);			break;
									case(4): sortida.setProducte(stringCellValue);		break;
									case(5): sortida.setVendaData(stringCellValue);		break;
									case(6): sortida.setVendaNumeroBotelles(stringCellValue);  break;
									case(7): sortida.setVendaMotiu(stringCellValue);   		   break;
									case(8): sortida.setVendaObservacions(stringCellValue);   break;
									case(9): sortida.setVendaDestinatari(stringCellValue);    break;
									case(10): sortida.setVendaNumeroDocument(stringCellValue); break;
									case(11): sortida.setVendaTipusDocument(stringCellValue);  break;
									default: break;
								}
							} catch (Exception e){
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.format") + ", columna: " + x;
							}
						}
					
					} catch (Exception e){
						ok = false;
						error = missatge("importacioProcesSortida.camp.error.format");
					}
					
					sortida = guardarSortida(ok, error, sortida);

					
					// Si no hemos tenido problemas recogiendo los datos del excel, continuamos con su tratamiento.
//					String 	  tipus 	= null;
					Pais 	  pais 		= null;
					Provincia provincia = null;
					Municipi  municipi 	= null;
					//String    accio     = "venda";
					Lot		  lot 		= null;
					Date 	  vendaData = null;
					Integer	  vendaNumeroBotelles = null;
					String	  vendaMotiu 		  = null;
					String	  vendaObservacions   = null;
					String	  vendaDestinatari    = null;
					String 	  vendaNumeroDocument = null;
					String	  vendaTipusDocument  = null;
					
					
					// Tipus Sortida
				/*	if (ok){
						try{
							if ( !("l".equals(sortida.getTipusSortida())) && !("d".equals(sortida.getTipusSortida()))){
								throw new Exception();
							}
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.tipusSortida");
							sortida = guardarSortida(ok, error, sortida);
						}
					}
				*/	
					// Pais
					if (ok && sortida.getPais()!=null && !"".equals(sortida.getPais()) && !"-".equals(sortida.getPais())){
						try{
							oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
							pais = oliInfraestructuraEjb.paisAmbNom(sortida.getPais());
							if ( pais == null ){
								throw new Exception();
							}
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.pais");
							sortida = guardarSortida(ok, error, sortida);
						}
					}
					
					// Provincia
					if (pais.getIso().equals("ESP")){
						if (ok && sortida.getProvincia()!=null && !"".equals(sortida.getProvincia()) && !"-".equals(sortida.getProvincia())){
							try{
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								provincia = oliInfraestructuraEjb.provinciaAmbNom(sortida.getProvincia());
								if ( provincia == null ){
									throw new Exception();
								}
							} catch (Exception e){
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.provincia");
								sortida = guardarSortida(ok, error, sortida);
							}
						}
						
						// Municipi
						if (ok && sortida.getMunicipi()!=null && !"".equals(sortida.getMunicipi()) && !"-".equals(sortida.getMunicipi())){
							try{
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								municipi = oliInfraestructuraEjb.municipiAmbNom(sortida.getMunicipi());
								if ( municipi == null ){
									throw new Exception();
								}
							} catch (Exception e){
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.municipi");
								sortida = guardarSortida(ok, error, sortida);
							}
						}
					} else {
						municipi = oliInfraestructuraEjb.municipiAmbNom("Altres");
					}
					
					// Lot
					if (ok){

						// Producte
						List producte = new ArrayList(0);
						try{
							if (sortida.getProducte()!=null && !"".equals(sortida.getProducte()) && !"-".equals(sortida.getProducte())){
								oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
								producte = oliInfraestructuraEjb.producteAmbNom(sortida.getProducte(), establiment.getId());
								if (producte.size() > 1){
									ok = false;
									error = missatge("importacioProcesSortida.camp.error.producte1");
									sortida = guardarSortida(ok, error, sortida);
								} else if (producte.size() == 0){
									throw new Exception();
								}
							}
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.producte2");
							sortida = guardarSortida(ok, error, sortida);
						}

						// Lot
						if (ok){
							try{
								if (sortida.getLot()!=null && !"".equals(sortida.getLot()) && !"-".equals(sortida.getLot())){
									oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
									List lots = new ArrayList(0);
									if (producte.size() == 0){
										lots = oliInfraestructuraEjb.lotAmbNomIProducte(sortida.getLot(), null);
									} else {
										lots = oliInfraestructuraEjb.lotAmbNomIProducte(sortida.getLot(), ((Producte)(producte.get(0))).getId());
									}
									
									if ( lots.size() > 1 ){
										ok = false;
										error = missatge("importacioProcesSortida.camp.error.lot1");
										sortida = guardarSortida(ok, error, sortida);
									} else if ( lots.size() == 0 ){
										throw new Exception();
									} else {
										lot = (Lot)lots.get(0);
									}
								} else {
									ok = false;
									error = missatge("importacioProcesSortida.camp.error.lot2");
									sortida = guardarSortida(ok, error, sortida);
								}
							} catch (Exception e){
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.lot3");
								sortida = guardarSortida(ok, error, sortida);
							}
						}
					
					}
					
					// Data venda
					if (ok){
						try{
							// Comprobamos que se haya rellenado el campo de fecha de venta.
							if (sortida.getVendaData()!=null && !"".equals(sortida.getVendaData()) && !"-".equals(sortida.getVendaData())){
								SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
								String ventaData = sortida.getVendaData();
								try {
									vendaData = HSSFDateUtil.getJavaDate(Double.parseDouble(sortida.getVendaData()));
								} catch (Exception ex) { 
									vendaData = formatoDelTexto.parse(ventaData);
								}
								
								// Comprobamos que la fecha de venta es mayor que la fecha del lote.
								if (vendaData.compareTo(lot.getData()) < 0){
									ok = false;
									error = missatge("importacioProcesSortida.camp.error.vendaData1");
									sortida = guardarSortida(ok, error, sortida);
								} else {
									// Comprobamos que la fecha de venta sea mayor que la de la campaña actual.
									Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(oliInfraestructuraEjb.campanyaCercaActual().intValue());
									if (vendaData.compareTo(campanya.getData()) < 0){
										ok = false;
										error = missatge("importacioProcesSortida.camp.error.vendaData3");
										sortida = guardarSortida(ok, error, sortida);
									}
								}
							} else {
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.vendaData4");
								sortida = guardarSortida(ok, error, sortida);
							}
							
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.vendaData2");
							sortida = guardarSortida(ok, error, sortida);
						}
					}
					
					// Número botelles venda
					if (ok){
						try{
							if (sortida.getVendaNumeroBotelles()!=null && !"".equals(sortida.getVendaNumeroBotelles()) && !"-".equals(sortida.getVendaNumeroBotelles())){
								vendaNumeroBotelles = Integer.parseInt(sortida.getVendaNumeroBotelles());
								
								if(vendaNumeroBotelles > lot.getNumeroBotellesActuals()){
									ok = false;
									error = missatge("importacioProcesSortida.camp.error.vendaNumeroBotelles1");
									sortida = guardarSortida(ok, error, sortida);
								}
							} else {
								ok = false;
								error = missatge("importacioProcesSortida.camp.error.vendaNumeroBotelles2");
								sortida = guardarSortida(ok, error, sortida);
							}
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.vendaNumeroBotelles3");
							sortida = guardarSortida(ok, error, sortida);
						}
					}
					
					// Motiu venda / Destinatari / Document / Tipus Document / Observacions
					if (ok && sortida.getVendaMotiu()!=null && !"".equals(sortida.getVendaMotiu()) && !"-".equals(sortida.getVendaMotiu())){
						try{
							vendaMotiu = sortida.getVendaMotiu();
							vendaObservacions = sortida.getVendaObservacions();
								
							if (sortida.getVendaMotiu().equals(motiuPromocio) || 
								sortida.getVendaMotiu().equals(motiuConsumPropi) ||
								sortida.getVendaMotiu().equals(motiuPerdua) ||
								sortida.getVendaMotiu().equals(motiuConsumidorFinal) ){
								
								vendaDestinatari = sortida.getVendaMotiu();
							} else {
								vendaDestinatari = sortida.getVendaDestinatari();
								vendaNumeroDocument = sortida.getVendaNumeroDocument();
								vendaTipusDocument = sortida.getVendaTipusDocument();
							}

						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.venda");
							sortida = guardarSortida(ok, error, sortida);
						}
					}
					
					if (ok){
						try{
							SortidaLot sortidaLot = new SortidaLot();
							sortidaLot.setLot(lot);
							sortidaLot.setZona(lot.getZona());
							sortidaLot.setAccioSortida(venda);
							sortidaLot.setPais(pais);
							sortidaLot.setProvincia(provincia);
							sortidaLot.setMunicipi(municipi);
							sortidaLot.setVendaData(vendaData);
							sortidaLot.setVendaNumeroBotelles(vendaNumeroBotelles);
							sortidaLot.setVendaMotiu(vendaMotiu);
							sortidaLot.setVendaObservacions(vendaObservacions);
							sortidaLot.setVendaDestinatari(vendaDestinatari);
							sortidaLot.setVendaNumeroDocument(vendaNumeroDocument);
							sortidaLot.setVendaTipusDocument(vendaTipusDocument);
							
							oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
							oliProcessosEjb.sortidaEmbotelladora(sortidaLot, null, entrada, venda, canviZona);
							
							sortida.setIdSortida(sortidaLot.getId());
							sortida = guardarSortida(ok, error, sortida);
						} catch (Exception e){
							ok = false;
							error = missatge("importacioProcesSortida.camp.error.sortidaLot");
							sortida = guardarSortida(ok, error, sortida);
						}
						
					}
				
					
				}
			
			}
			
    	} catch (Exception e) {
    		 logger.error("Error processant la sortida del lot", e);
             ControllerUtils.saveMessageError(request, missatge("sortidaLot.missatge.no"));
		}
	    	
	    return new ModelAndView(forward, myModel);
    }

    /**
     * Métode per guardar una copia de la sortida introduïda a l'excel
     * @param ok
     * @param error
     * @param sortida
     * @return
     */
    private SortidaOliFacturacio guardarSortida(Boolean ok, String error, SortidaOliFacturacio sortida){

		sortida.setEstat(ok);
		sortida.setError(error);
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.sortidaOliFacturacioCrear(sortida);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return sortida;
    }
    
    /**
     * Retorna totes les dades del model necessàries per a mostrar
     * el formulari d'inserció (LOVs y coses per l'estil) si no
     * n'hi ha.
     * 
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        
    	Map myModel = new HashMap();

    	
    	try{
    		ImportacioProcesSortidaCommand importacio = (ImportacioProcesSortidaCommand) command;
    	
    		HttpSession session = request.getSession();
    		Establiment establiment = (Establiment)session.getAttribute(establimentSessionKey);
    		/*
    		if (request.getParameter("tipusSortida") != null){
    			 importacio.setTipusSortida(request.getParameter("tipusSortida"));
    		}
    		
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection origen = null;
        
        String tipusSortida = (String) request.getAttribute("tipusSortida");
        
        if(pSortida.getTipusSortida()!= null && !pSortida.getTipusSortida().equalsIgnoreCase("")){
        	tipusSortida = pSortida.getTipusSortida();
        }
               
        Lot lots[] = null;
        Diposit diposit = null;
        if (origenList.size() != 0) {
        	if ("d".equals(tipusSortida)) {
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
        		for(Iterator it= origen.iterator();it.hasNext();){
        			diposit = (Diposit)it.next();
        		}
        	} else if ("l".equals(tipusSortida)) {
        		lots = new Lot[origenList.size()];
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		origen = oliInfraestructuraEjb.lotesInfo(origenList.toArray());
        		int i = 0;
        		for(Iterator it= origen.iterator();it.hasNext();){
        			lots[i] = (Lot)it.next();
        			i++;
        		}
        	}
        	
        }
        */
	    
    		myModel.put("path", "importacioProcesSortida");
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de importacions de sortida", ex);
        }
    	return myModel;
    	
    }

    
    /**
     * En el cas de que sigui una edició retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creació
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	ImportacioProcesSortidaCommand command = (ImportacioProcesSortidaCommand)request.getSession().getAttribute("command");
    	if (command == null)	
    		command = new ImportacioProcesSortidaCommand();
    	//else
    	//	request.getSession().removeAttribute("command");
    	
    	try{
	    	if (request.getParameter("id") != null){
	    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	    		Collection llistatOK = new ArrayList();
	    		llistatOK = oliInfraestructuraEjb.sortidesOliFacturacioByImportacioEstat(Long.valueOf(request.getParameter("id")), true);
	    		request.setAttribute("llistatOK", llistatOK);
	    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	    		Collection llistatKO = new ArrayList();
	    		llistatKO = oliInfraestructuraEjb.sortidesOliFacturacioByImportacioEstat(Long.valueOf(request.getParameter("id")), false);
	    		request.setAttribute("llistatKO", llistatKO);
	    	}
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   
    	
    	
    	//if (request.getParameter("prueba") != null){
    	//	command.
    	//}
    	/*try {
    					
			if (!isFormSubmission(request)) {
				pSortida.setVendaData(new Date());
				pSortida.setCanviZonaData(new Date());
			}
			
			Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
			
			Lot lots[] = new Lot[origenList.size()];
			for (int i = 0; i < origenList.size(); i++){
				lots[i] = new Lot();
			}
			pSortida.setLot(lots);
			pSortida.setVendaNumeroBotelles(new Integer[origenList.size()]);
			pSortida.setDiposit(new Diposit());			
			
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}*/
    	    	
        return command;
        
    }

    /**
     * Configuració del <i>binder</i>. Si hi ha camps en el
     * <i>command</i> amb tipus que no siguin <i>String</i>
     * s'ha de configurar el seu corresponent binder aquí.
     * 
     * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(
            HttpServletRequest request,
            ServletRequestDataBinder binder)
    throws Exception {
    	binder.registerCustomEditor(
    			Integer.class,
        		new CustomNumberEditor(Integer.class, true));
    	binder.registerCustomEditor(
        		Long.class,
        		new CustomNumberEditor(Long.class, true));
    	binder.registerCustomEditor(
        		Float.class,
        		new CustomNumberEditor(Float.class, true));
    	binder.registerCustomEditor(
        		Boolean.class,
        		new CustomBooleanEditor("S", "N", true));
        binder.registerCustomEditor(
				byte[].class,
				new ByteArrayMultipartFileEditor());
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }
 
    /**
	 * @param infraestructuraEjb the infraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
	 * @param oliProcessosEjb the oliProcessosEjb to set
	 */
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	/**
     * Injecció de la dependència seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }
    
	public void setVenda(String venda) {
		this.venda = venda;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public void setCanviZona(String canviZona) {
		this.canviZona = canviZona;
	}
	
    public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
    
	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }

	public void setMotiuPromocio(String motiuPromocio) {
		this.motiuPromocio = motiuPromocio;
	}

	public void setMotiuConsumPropi(String motiuConsumPropi) {
		this.motiuConsumPropi = motiuConsumPropi;
	}

	public void setMotiuPerdua(String motiuPerdua) {
		this.motiuPerdua = motiuPerdua;
	}

	public void setMotiuConsumidorFinal(String motiuConsumidorFinal) {
		this.motiuConsumidorFinal = motiuConsumidorFinal;
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
	
	
	/*
     * Retorna els arxius associats a aquesta peticio o null
     * si no n'hi ha 
     */
    private MultipartFile[] getFiles(HttpServletRequest request) {
    	if (request instanceof MultipartHttpServletRequest) {
        	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
        	Map mFiles = mpRequest.getFileMap();
        	if (mFiles.size() > 0) {
	        	MultipartFile[] files = new MultipartFile[mFiles.size()];
	        	int i = 0;
	        	for (Iterator it = mFiles.keySet().iterator(); it.hasNext();) {
	        		files[i++] = mpRequest.getFile((String)it.next());
	        	}
	        	if (files[0].getSize() > 0 || files[1].getSize()>0)
	        		return files;
	        	else
	        		return null;
        	}
    	}
        return null;
    }

	
}