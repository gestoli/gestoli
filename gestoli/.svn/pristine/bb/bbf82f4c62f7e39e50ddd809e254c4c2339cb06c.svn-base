<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" type="text/css" 
	href="../css/web/microweb/cercaId.css" media="screen" />

<title>Gest-OLI - Cercar Informació</title>

<link rel="stylesheet" type="text/css" href="../css/web/tree.css" media="screen" />
<script type="text/javascript" src="../js/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../js/yahoo/dom.js"></script>
<script type="text/javascript" src="../js/yahoo/event.js"></script>
<script type="text/javascript" src="../js/yahoo/treeview.js"></script>

<script type="text/javascript" src="../dwr/engine.js"></script>
<script type="text/javascript" src="../dwr/util.js"> </script>
<script type="text/javascript" src="../dwr/interface/frontOfficeService.js"></script>


<script type="text/javascript">
	// <![CDATA[
	
	
	function canviEnvassadora() {
		var idEnvassadora = document.getElementById("envasadoraId").value;
		if (idEnvassadora == "0"){
			document.getElementById("divIdFormarcaId").style.display = "none";
		} else {
			frontOfficeService.marquesAmbEnvassadora(idEnvassadora, carregarMarques);
			document.getElementById("divIdFormarcaId").style.display = "block";
		}
		document.getElementById("marcaId").value = "0";
		canviMarca();
		//traza();
		//
	}
	
	function carregarMarques(dades) {
		DWRUtil.removeAllOptions("marcaId");
   		DWRUtil.addOptions("marcaId", dades, 'id', 'nom');
	}
	
	
	function canviMarca() {
   		var idMarca = document.getElementById("marcaId").value;
   		if (idMarca == "0"){
			document.getElementById("divIdForetiquetatgeId").style.display = "none";
		} else {
			frontOfficeService.etiquetajesConMarca(idMarca, carregarEtiquetatges);
			document.getElementById("divIdForetiquetatgeId").style.display = "block";
		}
		document.getElementById("etiquetatgeId").value = "0";
		canviEtiquetatge();
	}

	function carregarEtiquetatges(dades) {
		DWRUtil.removeAllOptions("etiquetatgeId");
	    	DWRUtil.addOptions("etiquetatgeId", dades, 'id', 'nom');
	}

	
	function canviEtiquetatge() {
		var idEtiquetatge = document.getElementById("etiquetatgeId").value;
		if (idEtiquetatge == "0"){
			document.getElementById("divIdForpartidaId").style.display = "none";
		} else {
			frontOfficeService.partidaAmbEtiquetatge(idEtiquetatge, carregarPartides);
			document.getElementById("divIdForpartidaId").style.display = "block";
		}
		document.getElementById("partidaId").value = "0";
		canviPartida();
	}

	function carregarPartides(dades) {
		DWRUtil.removeAllOptions("partidaId");
		DWRUtil.addOptions("partidaId", dades, 'id', 'nom');
	}

	function canviPartida() {
		var idPartida = document.getElementById("partidaId").value;
		var idEtiquetatge = document.getElementById("etiquetatgeId").value;
		//alert(idPartida);
		//alert(idEtiquetatge);
		//var trazabilitat = frontOfficeService.lotByPartidaEtiquetatge(idPartida, idEtiquetatge);
		//Cargar variable trazabilitat.
		document.getElementById("arbol").style.display = "none";
		
		if (idPartida != "0" && idEtiquetatge != "0"){
			traza(idPartida, idEtiquetatge);
		}
	}
	
	
	function traza(idPartida, idEtiquetatge) {
		//var trazabilitat;
		//frontOfficeService.trazabilitatAmbPartida(9201, 18, treeInit);
		//();
		//document.getElementById("arbol").style.display = "none";
		document.getElementById("carregant").style.display = "block";
		frontOfficeService.trazabilitatAmbPartida(idPartida, idEtiquetatge, treeInit);
		
	}
	
	//]
</script>

<script type="text/javascript" language="javascript">
		var tree; 
		
		function treeInit(trazabilitats) { 
			
		   	tree = new YAHOO.widget.TreeView("arbreAqui"); 
		   	var root = tree.getRoot(); 
		   	<%--  <c:set var="parent">root</c:set> --%>
		   	var tmpNode_Titol = new Array();
		   	for (var z=0; z<trazabilitats.length;z++){
				var trazabilitat = trazabilitats[z];
			   	
				<%-- Node de Titol --%>
				if 	(trazabilitat[16] == 1)	{ titol = "Elaboració: " + trazabilitat[12].data; }
				else if (trazabilitat[16] == 2) { titol = "Dipòsit: " + trazabilitat[15].codiAssignat; }
				else if (trazabilitat[16] == 3) { titol = "Lot: " + trazabilitat[14].codiAssignat; }
				else 				{ titol = "Partida: "; }
					
				tmpNode_Titol[z] = new YAHOO.widget.TextNode({label:titol,id:"Titol"}, root, true);
				tmpNode_Titol[z].labelStyle = "arbre_bot";
	
				<%-- Node de categoria d'oli --%>
				if (trazabilitat[1] != null){
					if 	(trazabilitat[1].id == 1){ categoria = "Oli de Mallorca Sense denominació d'origen" }
					else if (trazabilitat[1].id == 2){ categoria = "Oli de Mallorca pendent de qualificar" }
					else if (trazabilitat[1].id == 3){ categoria = "Oli de Mallorca de Denominació d'origen" }
					var tmpNode_Categoria = new YAHOO.widget.TextNode({label:categoria,id:"Categoria"}, tmpNode_Titol[z], true);
				}
				
				<%-- Node de tipus d'oli --%>
				if (trazabilitat[2] != null){
					if 	(trazabilitat[2].id == 1){ tipusOli = "Oli d'oliva verge extra" }
					else if (trazabilitat[2].id == 2){ tipusOli = "Oli d'oliva verge" }
					else if (trazabilitat[2].id == 3){ tipusOli = "Oli d'oliva llampant" }
					var tmpNode_Categoria = new YAHOO.widget.TextNode({label:tipusOli,id:"TipusOli"}, tmpNode_Titol[z], true);
				}
				
				<%-- Node d'acidesa --%>
				if (trazabilitat[3] != null){
					var acidesa = "Acidesa: " + trazabilitat[3];
					if (trazabilitat[4] != null){
						var tmpNode_Analitica = new YAHOO.widget.TextNode({href:"javascript:void(0);", params:"pdf#Mostrar anàlisi", label:acidesa,id:"Acidesa"}, tmpNode_Titol[z], true);
						document.getElementById("idAna").value = trazabilitat[4].id;
						document.getElementById("idEst").value = trazabilitat[4].establiment.id;
						document.getElementById("idDip").value = trazabilitat[4].diposit.id;
					} else {
						var tmpNode_Acidesa = new YAHOO.widget.TextNode({label:acidesa,id:"Acidesa"}, tmpNode_Titol[z], true);
					}
				}
				
				<%-- Node de varietats d'oliva, indicant percentatge --%>
				if (trazabilitat[5] != null){
					var tmpNode_Varietats = new YAHOO.widget.TextNode({label:"Varietats d'oliva",id:"Varietats"}, tmpNode_Titol[z], true);
					var tmpNode_Varietat = new Array();
					for (var i=0; i<trazabilitat[5].length;i++){//varietat in trazabilitat[5]){
						var varietat = trazabilitat[5][i];
						if (varietat[0] != null){
							var varnom = varietat[0].nomVarietat;
							var percent = Math.round(varietat[1]*100)/100; //fmt:formatNumber var="percent" type="number" pattern="0.00" value="${varietat[1]}"
							tmpNode_Varietat[i] = new YAHOO.widget.TextNode({label:varnom + ": " + percent + "%" ,id:"Varietat" + i}, tmpNode_Varietats, false);
							//var tmpNode_Varietat_c:out value="${status.count}"
						}
					}
				}
				
				<%-- Node de quantitat d'oliva, desglosant el tipus d'oliva i la procedència --%>
				if (trazabilitat[6] != null){
					var quantitat = "Quantitat d'oliva: " + Math.round(trazabilitat[6]*100)/100 + " kg."
					var tmpNode_Quantitat = new YAHOO.widget.TextNode({label:quantitat,id:"Quantitat"}, tmpNode_Titol[z], true);
				}
				if (trazabilitat[7] != null){
					var tmpNode_Quantitat_child = new Array();
					for (var i=0; i<trazabilitat[7].length;i++){
						var varietat = trazabilitat[7][i];
						if (varietat[0] != null){
							var varnom = varietat[0].nomVarietat;
							var quantitat = 0;
							var quantitatTotal = varietat[1];
							tmpNode_Quantitat_child[i] = new YAHOO.widget.TextNode({label:varnom + " (Total " + quantitatTotal + " kg.)", id:"Quantitat_"+i}, tmpNode_Quantitat, false);
							for (var j=0; j<varietat[2].length; j++){
								var descPartida = varietat[2][j];
								var quant = Math.round(descPartida[1]*100)/100;
								var date = new Date();
								date = descPartida[0].partidaOliva.data;
								var dia = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
								var olivicultor = descPartida[0].descomposicioPlantacio.plantacio.finca.olivicultor;
								var plantacio = descPartida[0].descomposicioPlantacio.plantacio;
								var codi;
								if (olivicultor.codiDO != null)		   { codi = "RA " + olivicultor.codiDO; }
								if (olivicultor.codiDOExperimental != null){ codi = codi + " RE " + olivicultor.codiDOExperimenta; }
								if (plantacio.coordX != null){
									tmpNode_Quantitat_child[i][j] = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapweb#"+plantacio.coordX+"#"+plantacio.coordY+"#"+plantacio.municipi.nom+"#"+plantacio.poligon+"#"+plantacio.parcela+"#"+plantacio.catastre+"#Mostrar mapa",label:dia+" - "+quant+ " kg. - Productor: "+codi+": "+descPartida[0].descomposicioPlantacio.plantacio.nomComplet, id:"Quantitat_"+i+"_"+j}, tmpNode_Quantitat_child[i], false);
								} else {
									tmpNode_Quantitat_child[i][j] = new YAHOO.widget.TextNode({label:dia+" - "+quant+" kg. - Productor: "+codi+": "+descPartida[0].descomposicioPlantacio.plantacio.nomComplet,id:"Quantitat_"+i+"_"+j},tmpNode_Quantitat_child[i], false);
								}
							}
						}
					}
				}
				
				<%-- Node de tamany de la partida --%>
				if (trazabilitat[8] != null){
					var tamany = "Tamany de la partida: "+trazabilitat[8]+" l."
					var tmpNode_Tamany = new YAHOO.widget.TextNode({label:tamany,id:"Tamany"}, tmpNode_Titol[z], true);
				}
				
				<%-- Node de rendiment --%>
				if (trazabilitat[9] != null){
					var rend = Math.round(trazabilitat[9]*100)/100;
					var rendiment = "Rendiment: "+rend+"%";
					var tmpNode_Rendiment = new YAHOO.widget.TextNode({label:rendiment,id:"Rendiment"}, tmpNode_Titol[z], true);
				}
	
				<%-- Node de municipis de producció de les olives --%>
				if (trazabilitat[10] != null){
					var idsPlantacions = null;
					for (var i=0; i<trazabilitat[10].length;i++){
						var munpla = trazabilitat[10][i];
						if (munpla[0] != null){
							for (var j=0; j<munpla[1].length;j++){
								var plantacio = munpla[1][j];
								if (plantacio.coordX != null){
									idsPlantacions = idsPlantacions + plantacio.id + ",";
								}
							}					
						}
					}
					var tmpNode_Municipis = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapmultweb#"+idsPlantacions+"#Mostrar mapa",label:"Municipis productors d'oliva",id:"Municipis"}, tmpNode_Titol[z], true);
					var tmpNode_Municipis_child = new Array();
					for (var i=0; i<trazabilitat[10].length;i++){
						var munpla = trazabilitat[10][i];
						if (munpla[0] != null){
							tmpNode_Municipis_child[i] = new YAHOO.widget.TextNode({label:munpla[0] ,id:"Municipis_"+i},tmpNode_Municipis, false);
							for (var j=0; j<munpla[1].length;j++){
								var plantacio = munpla[1][j];
								var olivicultor = plantacio.finca.olivicultor;
								var codi;
								if (olivicultor.codiDO != null)		   { codi = "RA " + olivicultor.codiDO; }
								if (olivicultor.codiDOExperimental != null){ codi = codi + " RE " + olivicultor.codiDOExperimenta; }
								if (plantacio.coordX != null){
									tmpNode_Municipis_child[i][j] = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapweb#"+plantacio.coordX+"#"+plantacio.coordY+"#"+plantacio.municipi.nom+"#"+plantacio.poligon+"#"+plantacio.parcela+"#"+plantacio.catastre+"#Mostrar mapa",label:"Productor: "+codi+": "+plantacio.nomComplet,id:"Municipis_"+i+"_"+j},tmpNode_Municipis_child[i], false);
								} else {
									tmpNode_Municipis_child[i][j] = new YAHOO.widget.TextNode({label:"Productor: "+codi+": "+plantacio.nomComplet,id:"Municipis_"+i+"_"+j},tmpNode_Municipis_child[i], false);
								}
							}
						}
					}
				}
				
				<%-- Node de elaboracions --%>
				if (trazabilitat[12] != null){
					var tmpNode_Elaboracions = new YAHOO.widget.TextNode({label:"Elaboració",id:"Elaboracions"}, tmpNode_Titol[z], true);
					var tmpNode_Elaboracions_child = new Array();
					var idOrg = "0";
					for (var i=0; i<trazabilitat[12].length;i++){
						var elaboracio = trazabilitat[12][i];
						var idElab = elaboracio.partidaOli.establiment.id;
						if (idElab != idOrg){
							var elaborador;
							if (elaboracio.partidaOli.establiment.codiRB != null) { elaborador = "RB0" + elaboracio.partidaOli.establiment.codiRB; }
							if (elaboracio.partidaOli.establiment.codiRC != null) { elaborador = elaborador + " RC0" + elaboracio.partidaOli.establiment.codiRC; }
							idOrg = idElab;
							tmpNode_Elaboracions_child[idElab] = new YAHOO.widget.TextNode({label:"Tafona: "+elaborador,id:"Elaboracions_"+idElab}, tmpNode_Elaboracions, false);
						}
						var date = new Date();
						date = elaboracio.data;
						var dia = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
						tmpNode_Elaboracions_child[idElab][i] = new YAHOO.widget.TextNode({label:dia ,id:"Elaboracions_"+idElab+"_"+i},tmpNode_Elaboracions_child[idElab], false);
						<%-- tmpNode_Elaboracions_child[idElab][i][1] = new YAHOO.widget.TextNode({label:"Responsable: "+elaboracio.responsable ,id:"Elaboracions_"+idElab+"_"+i+"_1"},tmpNode_Elaboracions_child[idElab][i], false); --%>
						tmpNode_Elaboracions_child[idElab][i][2] = new YAHOO.widget.TextNode({label:"Oliva molturada: "+elaboracio.totalKilos+" kg" ,id:"Elaboracions_"+idElab+"_"+i+"_2"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][3] = new YAHOO.widget.TextNode({label:"Litres d'oli produïts: "+elaboracio.litres+" l" ,id:"Elaboracions_"+idElab+"_"+i+"_3"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][4] = new YAHOO.widget.TextNode({label:"Acidesa: "+elaboracio.acidesa ,id:"Elaboracions_"+idElab+"_"+i+"_4"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][5] = new YAHOO.widget.TextNode({label:"Partida d'oli: "+elaboracio.partidaOli.nom ,id:"Elaboracions_"+idElab+"_"+i+"_5"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][6] = new YAHOO.widget.TextNode({label:"Categoria d'oli: "+elaboracio.categoriaOli.nom ,id:"Elaboracions_"+idElab+"_"+i+"_6"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][7] = new YAHOO.widget.TextNode({label:"Varietats: "+elaboracio.porcentatjesVarietats ,id:"Elaboracions_"+idElab+"_"+i+"_7"},tmpNode_Elaboracions_child[idElab][i], false);
						tmpNode_Elaboracions_child[idElab][i][8] = new YAHOO.widget.TextNode({label:"Temperatura màxima de la pasta: "+elaboracio.temperatura ,id:"Elaboracions_"+idElab+"_"+i+"_8"},tmpNode_Elaboracions_child[idElab][i], false);
						if (elaboracio.talcQuantitat > 0){
							tmpNode_Elaboracions_child[idElab][i][9] = new YAHOO.widget.TextNode({label:"Talc: "+elaboracio.talcQuantitat+" de "+elaboracio.talcMarcaComercial+" ("+elaboracio.talcLot+")" ,id:"Elaboracions_"+idElab+"_"+i+"_9"},tmpNode_Elaboracions_child[idElab][i], false);
						}
					}
				}
				
				<%-- Node de embotellats --%>
				if (trazabilitat[13] != null){
					var tmpNode_Embotellats = new YAHOO.widget.TextNode({label:"Embotellat",id:"Embotellats"}, tmpNode_Titol[z], true);
					var tmpNode_Embotellats_child = new Array();
					var idOrg = "0";
					for (var i=0; i<trazabilitat[13].length;i++){
						var embotellat = trazabilitat[13][i];
						var idElab = embotellat.zona.establiment.id;
						if (idElab != idOrg){
							var embotellador = embotellat.zona.establiment.nom;
							idOrg = idElab;
							tmpNode_Embotellats_child[idElab] = new YAHOO.widget.TextNode({label:"Envasadora: "+embotellador,id:"Embotellats_"+idElab}, tmpNode_Embotellats, false);
						}
						var date = new Date();
						date = embotellat.data;
						var dia = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
						tmpNode_Embotellats_child[idElab][i] = new YAHOO.widget.TextNode({label:dia ,id:"Embotellats_"+idElab+"_"+i},tmpNode_Embotellats_child[idElab], false);
						var codiDiposit = "";
						if (embotellat.lot.diposit != null) { codiDiposit = embotellat.lot.diposit.codiAssignat; }
						tmpNode_Embotellats_child[idElab][i][1] = new YAHOO.widget.TextNode({label:"Dipòsit de procedència: "+codiDiposit ,id:"Embotellats_"+idElab+"_"+i+"_1"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][2] = new YAHOO.widget.TextNode({label:"Nom del lot: "+embotellat.lot.codiAssignat ,id:"Embotellats_"+idElab+"_"+i+"_2"},tmpNode_Embotellats_child[idElab][i], false);
						date = embotellat.lot.dataConsum;
						var dataConsum = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
						tmpNode_Embotellats_child[idElab][i][3] = new YAHOO.widget.TextNode({label:"Data de consum preferent: "+dataConsum ,id:"Embotellats_"+idElab+"_"+i+"_3"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][4] = new YAHOO.widget.TextNode({label:"Nombre de botelles: "+embotellat.botelles+" bot. de "+embotellat.lot.etiquetatge.tipusEnvas.volum+" l." ,id:"Embotellats_"+idElab+"_"+i+"_4"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][5] = new YAHOO.widget.TextNode({label:"Litres envassats: "+embotellat.lot.litresEnvassats+" l." ,id:"Embotellats_"+idElab+"_"+i+"_5"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][6] = new YAHOO.widget.TextNode({label:"Marca: "+embotellat.lot.etiquetatge.marca.nom ,id:"Embotellats_"+idElab+"_"+i+"_6"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][7] = new YAHOO.widget.TextNode({label:"Envàs: "+embotellat.lot.etiquetatge.nomEnvas ,id:"Embotellats_"+idElab+"_"+i+"_7"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][8] = new YAHOO.widget.TextNode({label:"Partida d'oli: "+embotellat.lot.partidaOli.nom ,id:"Embotellats_"+idElab+"_"+i+"_8"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][9] = new YAHOO.widget.TextNode({label:"Categoria d'oli: "+embotellat.lot.partidaOli.categoriaOli.nom ,id:"Embotellats_"+idElab+"_"+i+"_9"},tmpNode_Embotellats_child[idElab][i], false);
						tmpNode_Embotellats_child[idElab][i][10] = new YAHOO.widget.TextNode({label:"Acidesa: "+embotellat.acidesa ,id:"Embotellats_"+idElab+"_"+i+"_10"},tmpNode_Embotellats_child[idElab][i], false);

						var lletra = "";
						if (trazabilitat[17] != null){
							lletra = trazabilitat[17];  
						}
					
						if (embotellat.numerosContraetiquetes != null){
							tmpNode_Embotellats_child[idElab][i][11] = new YAHOO.widget.TextNode({label:"Números de contraetiquetes: (" + lletra + ") " + embotellat.numerosContraetiquetes ,id:"Embotellats_"+idElab+"_"+i+"_11"},tmpNode_Embotellats_child[idElab][i], false);
						}
						if (embotellat.observacions != null){
							tmpNode_Embotellats_child[idElab][i][12] = new YAHOO.widget.TextNode({label:"Observacions: "+embotellat.observacions ,id:"Embotellats_"+idElab+"_"+i+"_12"},tmpNode_Embotellats_child[idElab][i], false);
						}
					
					}
				}

			}
			
		   	tree.draw();
		   	document.getElementById("arbol").style.display = "block";
		   	document.getElementById("carregant").style.display = "none";
		}
		//YAHOO.util.Event.addListener(window, "load", treeInit);
	</script>


</head>

<body>

<form id="formulario" name="cercaId" action="cercaid.ca.html" method="post" class="extended seguit ancho888" onsubmit="">
<div id="cercaIdMicroweb">
	<div class="cercaId">

		<h2 class="microweb">Cercar informació</h2>
		<br />
		
		<div id="divIdForenvasadoraId" class=" campoFormMediano required">
			<div>
				<label for="envasadoraId">Selecciona Envasadora:</label>
			</div>
			<div class="bordeInput">
			<div class="bordeFoco">
				<select id='envasadoraId' name="envasadoraId" class="inputSelect" onchange="canviEnvassadora()">
					<option value="0" selected="selected">- - - - -</option>
					<c:forEach var="envasadora" items="${envasadores}">
						<option value='<c:out value="${envasadora.id}"/>'>
							<c:out value="${envasadora.nom}"/>
						</option>
					</c:forEach>
				</select>
			</div>
			</div>
		</div>
		
		<div class="separadorH"></div>
			
		<div id="divIdFormarcaId" class=" campoFormMediano required" style="display:none">
			<div><label for="marcaId">Selecciona Marca:</label></div>
			<div class="bordeInput">
			<div class="bordeFoco">
				<select id='marcaId' name="marcaId" class="inputSelect" onchange="canviMarca()">
					<option value="0" selected="selected">- - - - -</option>
				</select>
			</div>
			</div>
		</div>
		
		<div class="separadorH"></div>
			
		<div id="divIdForetiquetatgeId" class=" campoFormMediano required" style="display:none">
			<div><label for="etiquetatgeId">Selecciona Etiquetatge:</label></div>
			<div class="bordeInput">
			<div class="bordeFoco">
				<select id='etiquetatgeId' name="etiquetatgeId" class="inputSelect" onchange="canviEtiquetatge()">
					<option value="0" selected="selected">- - - - -</option>
				</select>
			</div>
			</div>
		</div>
		
		<div class="separadorH"></div>
			
		<div id="divIdForpartidaId" class=" campoFormMediano required" style="display:none">
			<div><label for="partidaId">Selecciona Partida:</label></div>
			<div class="bordeInput">
			<div class="bordeFoco">
				<select id='partidaId' name="partidaId" class="inputSelect" onchange="canviPartida()">
					<option value="0" selected="selected">- - - - -</option>
				</select>
			</div>
			</div>
		</div>
		
		

	</div>

	<div class="separadorH"></div>

	<div id="arbol" style="display:none">
		<div id="listadoAncho" class="arbol">
			<br />
			<div id="arbreAqui"></div>
		</div>
	</div>
	<div id="carregant" style="display:none">
		Carregant...
	<div>
</div>
</form>

<form id="formAnalitica" action="../GenerarPdfWeb.html" method="post" class="seguit">
	<input type="hidden" id="tipus" name="tipus" value="22" /> 
	<input type="hidden" id="idAna" name="idAna" value="${trazabilitat[4].id}"/> 
	<input type="hidden" id="idEst" name="idEst" value="${trazabilitat[4].establiment.id}"/> 
	<input type="hidden" id="idDip" name="idDip" value="${trazabilitat[4].diposit.id}"/>
</form>
<form id="formRendiment" action="../GenerarPdfWeb.html" method="post" class="seguit">
	<input type="hidden" id="tipus" name="tipus" value="23" /> 
	<input type="hidden" id="idElaboracio" name="idElaboracio" value=""/> 
	<input type="hidden" id="idEstabliment" name="idEstabliment" value=""/> 
</form>


</body>
</html>