// $Id: OliDoLibCustomStrategy.java,v 1.6 2009/12/03 17:02:23 at4.obarnes Exp $
 /*
 *   +------------------------------------------------------------------------------+
 *       at4.net internet y comunicación                                             
 *   +------------------------------------------------------------------------------+
 *       P169 - Gest Oli                                                             
 *   +------------------------------------------------------------------------------+
 *       OliDoLib                                                                    
 *       Librería de objetos de P169 - Gest Oli                                      
 *                                                                                   
 *   +------------------------------------------------------------------------------+
 */

import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;


/**
 * Conversor de nombres de tabla a java para hibernate tools
 * Elimina prefijos y sufijos en los nombres
 * @author agarcia
 *
 */
public class OliDoLibCustomStrategy extends GeneralStrategy {

    public OliDoLibCustomStrategy (ReverseEngineeringStrategy strategy) {
            super(strategy);

            //Modo prefijo en atributos
            this.modoPrefijoEnAtributos = true;
            
            
            this.tables.put("oli_usuari", 
							new TableData(
								"oli_usuari", 
								"OliUsuari", 
								"Usuaris", 
								"Usuari", 
								"usu"));
		    this.tables.put("oli_grup", 
		    				new TableData(
		    					"oli_grup", 
		    					"OliGrup", 
		    					"Grups", 
		    					"Grup", 
		    					"gru"));
		    this.tables.put("oli_taxa", 
		    				new TableData(
		    					"oli_taxa", 
		    					"OliTaxa", 
		    					"Taxas", 
		    					"Taxa", 
		    					"tax"));
		    this.tables.put("oli_color", 
		    				new TableData(
		    					"oli_color", 
		    					"OliColor", 
		    					"Colors", 
		    					"Color", 
		    					"col"));
		    this.tables.put("oli_idioma", 
		    				new TableData(
		    					"oli_idioma", 
		    					"OliIdioma", 
		    					"Idiomes", 
		    					"Idioma", 
		    					"idi"));
		    this.tables.put("oli_tipus_envas", 
		    				new TableData(
		    					"oli_tipus_envas", 
		    					"OliTipusEnvas", 
		    					"TipusEnvasos", 
		    					"TipusEnvas", 
		    					"ten"));
		    this.tables.put("oli_arxiu", 
		    				new TableData(
		    					"oli_arxiu", 
		    					"OliArxiu", 
		    					"Arxius", 
		    					"Arxiu", 
		    					"arx"));
		    this.tables.put("oli_material_tipus_envas", 
		    				new TableData(
		    					"oli_material_tipus_envas", 
		    					"OliMaterialTipusEnvas", 
		    					"MaterialsTipusEnvasos", 
		    					"MaterialTipusEnvas", 
		    					"men"));
		    this.tables.put("oli_etiquetatge", 
		    				new TableData(
		    					"oli_etiquetatge", 
		    					"OliEtiquetatge", 
		    					"Etiquetatges", 
		    					"Etiquetatge", 
		    					"eti"));
		    this.tables.put("oli_marca", 
		    				new TableData(
		    					"oli_marca", 
		    					"OliMarca", 
		    					"Marques", 
		    					"Marca", 
		    					"mar"));
		    this.tables.put("oli_establiment", 
		    				new TableData(
		    					"oli_establiment", 
		    					"OliEstabliment", 
		    					"Establiments", 
		    					"Establiment", 
		    					"est"));
		    this.tables.put("oli_tipus_establiment", 
		    				new TableData(
		    					"oli_tipus_establiment", 
		    					"OliTipusEstabliment", 
		    					"TipusEstabliments", 
		    					"TipusEstabliment", 
		    					"tes"));
		    this.tables.put("oli_solicitant", 
		    				new TableData(
		    					"oli_solicitant", 
		    					"OliSolicitant", 
		    					"Solicitants", 
		    					"Solicitant", 
		    					"sol"));
		    this.tables.put("oli_estmar", 
		    				new TableData(
		    					"oli_estmar", 
		    					"OliEstmar", 
		    					"EstablimentsMarques", 
		    					"EstablimentMarca", 
		    					"ema"));
		    this.tables.put("oli_olivicultor", 
		    				new TableData(
		    					"oli_olivicultor", 
		    					"OliOlivicultor", 
		    					"Olivicultors", 
		    					"Olivicultor", 
		    					"oli"));
		    this.tables.put("oli_finca", 
		    				new TableData(
		    					"oli_finca", 
		    					"OliFinca", 
		    					"Finques", 
		    					"Finca", 
		    					"fin"));
		    this.tables.put("oli_plantacio", 
		    				new TableData(
		    					"oli_plantacio", 
		    					"OliPlantacio", 
		    					"Plantacions", 
		    					"Plantacio", 
		    					"pla"));
		    this.tables.put("oli_descomposicio_plantacio", 
		    				new TableData(
		    					"oli_descomposicio_plantacio", 
		    					"OliDescomposicioPlantacio", 
		    					"DescomposicionsPlantacions", 
		    					"DescomposicioPlantacio", 
		    					"dpl"));
		    this.tables.put("oli_varietat_oliva", 
		    				new TableData(
		    					"oli_varietat_oliva", 
		    					"OliVarietatOliva", 
		    					"VarietatsOlives", 
		    					"VarietatOliva", 
		    					"vov"));
		    this.tables.put("oli_zona", 
		    				new TableData(
		    					"oli_zona", 
		    					"OliZona", 
		    					"Zones", 
		    					"Zona", 
		    					"zon"));
		    this.tables.put("oli_diposit", 
		    				new TableData(
		    					"oli_diposit", 
		    					"OliDiposit", 
		    					"Diposits", 
		    					"Diposit", 
		    					"dip"));
		    this.tables.put("oli_material_diposit", 
		    				new TableData(
		    					"oli_material_diposit", 
		    					"OliMaterialDiposit", 
		    					"MaterialsDiposits", 
		    					"MaterialDiposit", 
		    					"mdi"));
		    this.tables.put("oli_usugru", 
		    				new TableData(
		    					"oli_usugru", 
		    					"OliUsugru", 
		    					"UsuarisGrups", 
		    					"UsuariGrup", 
		    					"ugr"));
		    this.tables.put("oli_campanya", 
		    				new TableData(
		    					"oli_campanya", 
		    					"OliCampanya", 
		    					"Campanyes", 
		    					"Campanya", 
		    					"cam"));
		    this.tables.put("oli_descompo_partida_oliva", 
		    				new TableData(
		    					"oli_descompo_partida_oliva", 
		    					"OliDescompoPartidaOliva", 
		    					"DescomposicioPartidesOlives", 
		    					"DescomposicioPartidaOliva", 
		    					"dpo"));
		    this.tables.put("oli_partida_oliva", 
		    				new TableData(
		    					"oli_partida_oliva", 
		    					"OliPartidaOliva", 
		    					"PartidesOlivess", 
		    					"PartidaOliva", 
		    					"pao"));
		    this.tables.put("oli_varietat_oli", 
		    				new TableData(
		    					"oli_varietat_oli", 
		    					"OliVarietatOli", 
		    					"VarietatsOlis", 
		    					"VarietatOli", 
		    					"vol"));
		    this.tables.put("oli_elaboracio", 
		    				new TableData(
		    					"oli_elaboracio", 
		    					"OliElaboracio", 
		    					"Elaboracions", 
		    					"Elaboracio", 
		    					"ela"));
		    this.tables.put("oli_entrada_diposit", 
		    				new TableData(
		    					"oli_entrada_diposit", 
		    					"OliEntradaDiposit", 
		    					"EntradesDiposits", 
		    					"EntradaDiposit", 
		    					"edi"));
		    this.tables.put("oli_sortida_diposit", 
		    				new TableData(
		    					"oli_sortida_diposit", 
		    					"OliSortidaDiposit", 
		    					"SortidesDiposits", 
		    					"SortidaDiposit", 
		    					"sdi"));
		    this.tables.put("oli_categoria_oli", 
		    				new TableData(
		    					"oli_categoria_oli", 
		    					"OliCategoriaOli", 
		    					"CategoriesOlis", 
		    					"CategoriaOli", 
		    					"cao"));
		    this.tables.put("oli_analitica", 
		    				new TableData(
		    					"oli_analitica", 
		    					"OliAnalitica", 
		    					"Analitiques", 
		    					"Analitica", 
		    					"ana"));
		    this.tables.put("oli_lot", 
		    				new TableData(
		    					"oli_lot", 
		    					"OliLot", 
		    					"Lots", 
		    					"Lot", 
		    					"lot"));
		    this.tables.put("oli_entrada_lot", 
		    				new TableData(
		    					"oli_entrada_lot", 
		    					"OliEntradaLot", 
		    					"EntradesLots", 
		    					"EntradaLot", 
		    					"elo"));
		    this.tables.put("oli_sortida_lot", 
		    				new TableData(
		    					"oli_sortida_lot", 
		    					"OliSortidaLot", 
		    					"SortidesLots", 
		    					"SortidaLot", 
		    					"slo"));
		    this.tables.put("oli_factura", 
		    				new TableData(
		    					"oli_factura", 
		    					"OliFactura", 
		    					"Factures", 
		    					"Factura", 
		    					"fac"));
		    this.tables.put("oli_elapao", 
		    				new TableData(
		    					"oli_elapao", 
		    					"OliElapao", 
		    					"ElaboracionsPartidesOlives", 
		    					"ElaboracioPartidaOliva", 
		    					"epa"));
		    this.tables.put("oli_trasllat", 
		    				new TableData(
		    					"oli_trasllat", 
		    					"OliDiposit", 
		    					"Trasllats", 
		    					"Trasllat", 
		    					"tdi"));
		    this.tables.put("oli_diptdi", 
		    				new TableData(
		    					"oli_diptdi", 
		    					"OliDiptdi", 
		    					"DipositsTrasllats", 
		    					"DipositTrasllat", 
		    					"dtd"));
		    this.tables.put("oli_traza", 
		    				new TableData(
		    					"oli_traza", 
		    					"OliTraza", 
		    					"Traces", 
		    					"Traza", 
		    					"tra"));
		    this.tables.put("oli_tratra", 
		    				new TableData(
		    					"oli_tratra", 
		    					"OliTratra", 
		    					"TracesTraces", 
		    					"TracaTraca", 
		    					"ttr"));
            this.tables.put("oli_categoria_informacio", 
		    				new TableData(
		    					"oli_categoria_informacio", 
		    					"OliCategoriaInformacio", 
		    					"CategoriaInformacions", 
		    					"CategoriaInformacio", 
		    					"cai"));
		    this.tables.put("oli_informacio", 
		    				new TableData(
		    					"oli_informacio", 
		    					"OliInformacio", 
		    					"Informacions", 
		    					"Informacio", 
		    					"inf"));
		    this.tables.put("oli_estinf", 
		    				new TableData(
		    					"oli_estinf", 
		    					"OliEstinf", 
		    					"EstablimentsInformacions", 
		    					"EstablimentInformacio", 
		    					"ein"));
		    this.tables.put("oli_oliinf", 
		    				new TableData(
		    					"oli_oliinf", 
		    					"OliOliinf", 
		    					"OlivicultorsInformacions", 
		    					"OlivicultorInformacio", 
		    					"oin"));
		    this.tables.put("oli_document", 
		    				new TableData(
		    					"oli_document", 
		    					"OliDocument", 
		    					"Documents", 
		    					"Document", 
		    					"doc"));
		    this.tables.put("oli_lotvov", 
		    				new TableData(
		    					"oli_lotvov", 
		    					"OliLotvov", 
		    					"LotsVarietatsOliva", 
		    					"LotVarietatOliva", 
		    					"lvo"));


    }

    //::FIN GENERACIÓN AUTOMÁTICA
    //::INICIO MÉTODOS DEFINIDOS POR EL USUARIO
}