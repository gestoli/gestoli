<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>

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

<script type="text/javascript">
	// <![CDATA[
	
/*	function buscar() {
		var lletra = document.getElementById("etiquetaLletra").value;
		var numero = document.getElementById("etiquetaNumero").value;
		frontOfficeService.getInformacioOli(lletra, numero, function(resp){
			alert(resp.id);
		});
	};*/
	//]
</script>

<script type="text/javascript" language="javascript">
		var tree; 
		function treeInit() { 
		   	tree = new YAHOO.widget.TreeView("arbreAqui"); 
		   	var root = tree.getRoot(); 
		   	<%--  <c:set var="parent">root</c:set> --%>
			
			<%-- Node de Titol --%>
			<c:choose>
				<c:when test="${trazabilitat[16] == 1}"> <%-- Elaboració --%>
					<c:set var="titol">Elaboración: <fmt:formatDate value="${trazabilitat[12].data}" type="date" dateStyle="short" timeStyle="short"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 2}"> <%-- Dipòsit --%>
					<c:set var="titol">Depósito: <c:out value="${trazabilitat[15].codiAssignat}"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 3}"> <%-- Lot --%>
					<c:set var="titol">Lote: <c:out value="${trazabilitat[14].codiAssignat}"/></c:set>
				</c:when>
				<c:otherwise> <%-- Partida d'oliva --%>
					<c:set var="titol">Partida</c:set>
				</c:otherwise> 
			</c:choose>
			var tmpNode_Titol = new YAHOO.widget.TextNode({label:"<c:out value="${titol}"/>",id:"Titol"}, root, true);
			tmpNode_Titol.labelStyle = "arbre_bot";

			<%-- Node de categoria d'oli --%>
			<c:if test="${not empty trazabilitat[1]}">
				<c:if test="${trazabilitat[1].id == 1}"><c:set var="categoria">Aceite de Mallorca Sin denominación de origen</c:set></c:if>
				<c:if test="${trazabilitat[1].id == 2}"><c:set var="categoria">Aceite de Mallorca pendiente de calificar</c:set></c:if>
				<c:if test="${trazabilitat[1].id == 3}"><c:set var="categoria">Aceite de Mallorca de Denominación de origen</c:set></c:if>
				var tmpNode_Categoria = new YAHOO.widget.TextNode({label:"<c:out value="${categoria}"/>",id:"Categoria"}, tmpNode_Titol, true);
			</c:if>
			
			<%-- Node de tipus d'oli --%>
			<c:if test="${not empty trazabilitat[2]}">
				<c:if test="${trazabilitat[2].id == 1}"><c:set var="tipusOli">Aceite de oliva virgen extra</c:set></c:if>
				<c:if test="${trazabilitat[2].id == 2}"><c:set var="tipusOli">Aceite de oliva virgen</c:set></c:if>
				<c:if test="${trazabilitat[2].id == 3}"><c:set var="tipusOli">Aceite de oliva lampante</c:set></c:if>
				var tmpNode_TipusOli = new YAHOO.widget.TextNode({label:"<c:out value="${tipusOli}"/>",id:"TipusOli"}, tmpNode_Titol, true);
			</c:if>
			
			<%-- Node d'acidesa --%>
			<c:if test="${not empty trazabilitat[3]}">
				<c:set var="acidesa">Acidez: <c:out value="${trazabilitat[3]}"/></c:set>
				<c:if test="${not empty trazabilitat[4]}">
					var tmpNode_Analitica = new YAHOO.widget.TextNode({href:"javascript:void(0);", params:"pdf#Mostrar análisis", label:"<c:out value="${acidesa}"/>",id:"Acidesa"}, tmpNode_Titol, true);
				</c:if>
				<c:if test="${empty trazabilitat[4]}">
					var tmpNode_Acidesa = new YAHOO.widget.TextNode({label:"<c:out value="${acidesa}"/>",id:"Acidesa"}, tmpNode_Titol, true);
				</c:if>
			</c:if>
			
			
			<%-- Node de varietats d'oliva, indicant percentatge --%>
			<c:if test="${not empty trazabilitat[5]}">
				var tmpNode_Varietats = new YAHOO.widget.TextNode({label:"Variedades de oliva",id:"Varietats"}, tmpNode_Titol, true);
				<c:forEach var="varietat" items="${trazabilitat[5]}" varStatus="status">
					<c:if test="${not empty varietat[0]}">
						<c:set var="varnom"><c:out value="${varietat[0].nomVarietat}"/></c:set>
						<fmt:formatNumber var="percent" type="number" pattern="0.00" value="${varietat[1]}"/>
						var tmpNode_Varietat_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${varnom}"/>: <c:out value="${percent}"/> %" ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Varietats, false);						
					</c:if>
				</c:forEach>
			</c:if>
			
			<%-- Node de quantitat d'oliva, desglosant el tipus d'oliva i la procedència --%>
			<c:if test="${not empty trazabilitat[6]}">
				<c:set var="quantitat">Cantidad de oliva: <c:out value="${trazabilitat[6]}"/>kg.</c:set>
				var tmpNode_Quantitat = new YAHOO.widget.TextNode({label:"<c:out value="${quantitat}"/>",id:"Quantitat"}, tmpNode_Titol, true);
			</c:if>
			<c:if test="${not empty trazabilitat[7]}">
				<c:forEach var="varietat" items="${trazabilitat[7]}" varStatus="status">
					<c:if test="${not empty varietat[0]}">
						<c:set var="varnom"><c:out value="${varietat[0].nomVarietat}"/></c:set>
						<c:set var="quantitat" value="0"/>
						<fmt:formatNumber var="quantitatTotal" type="number" pattern="0.00" value="${varietat[1]}"/>
						var tmpNode_Quantitat_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${varnom}"/> (Total <c:out value="${quantitatTotal}"/>kg.)",id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat, false);
						<c:forEach var="descPartida" items="${varietat[2]}" varStatus="status2">
							<fmt:formatNumber var="quant" type="number" pattern="0.00" value="${descPartida[1]}"/>
							<fmt:formatDate var="dia" dateStyle="short" value="${descPartida[0].partidaOliva.data}"/>
							<c:set var="olivicultor" value="${descPartida[0].descomposicioPlantacio.plantacio.finca.olivicultor}"/>
							<c:set var="plantacio" value="${descPartida[0].descomposicioPlantacio.plantacio}"/>
							<c:if test="${not empty plantacio.coordX}">
								var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapweb#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#Mostrar mapa",label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Productor: <c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>: <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
							</c:if>
							<c:if test="${empty descPartida[0].descomposicioPlantacio.plantacio.coordX}">
								var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Productor: <c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>: <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>						
					</c:if>
				</c:forEach>
			</c:if>
			
			<%-- Node de tamany de la partida --%>
			<c:if test="${not empty trazabilitat[8]}">
				<c:set var="tamany">Tamaño de la partida: <c:out value="${trazabilitat[8]}"/>l.</c:set>
				var tmpNode_Tamany = new YAHOO.widget.TextNode({label:"<c:out value="${tamany}"/>",id:"Tamany"}, tmpNode_Titol, true);
			</c:if>
			
			<%-- Node de rendiment --%>
			<c:if test="${not empty trazabilitat[9]}">
				<fmt:formatNumber var="rend" type="number" pattern="0.00" value="${trazabilitat[9]}"/>
				<c:set var="rendiment">Rendimiento: <c:out value="${rend}"/>%</c:set>
				var tmpNode_Rendiment = new YAHOO.widget.TextNode({label:"<c:out value="${rendiment}"/>",id:"Rendiment"}, tmpNode_Titol, true);
			</c:if>

			<%-- Node de municipis de producció de les olives --%>
			<c:if test="${not empty trazabilitat[10]}">
				var idsPlantacions = null;
				<c:forEach var="munpla" items="${trazabilitat[10]}" varStatus="status">
					<c:if test="${not empty munpla[0]}">
						<c:forEach var="plantacio" items="${munpla[1]}" varStatus="status2">
							<c:if test="${not empty plantacio.coordX}">
								idsPlantacions = idsPlantacions + <c:out value="${plantacio.id}"/> + ",";
							</c:if>
						</c:forEach>						
					</c:if>
				</c:forEach>
				var tmpNode_Municipis = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapmultweb#"+idsPlantacions+"#Mostrar mapa",label:"Municipios productores de oliva",id:"Municipis"}, tmpNode_Titol, true);
				<c:forEach var="munpla" items="${trazabilitat[10]}" varStatus="status">
					<c:if test="${not empty munpla[0]}">
						var tmpNode_Municipis_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${munpla[0]}"/>" ,id:"Municipis_<c:out value="${status.count}"/>"},tmpNode_Municipis, false);
						<c:forEach var="plantacio" items="${munpla[1]}" varStatus="status2">
							<c:set var="olivicultor" value="${plantacio.finca.olivicultor}"/>
							<c:if test="${not empty plantacio.coordX}">
								var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"mapweb#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#Mostrar mapa",label:"Productor: <c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>: <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
							</c:if>
							<c:if test="${empty plantacio.coordX}">
								var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"Productor: <c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>: <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>						
					</c:if>
				</c:forEach>
			</c:if>
			
			<%-- Node de elaboracions --%>
			<c:if test="${not empty trazabilitat[12]}">
				var tmpNode_Elaboracions = new YAHOO.widget.TextNode({label:"Elaboración",id:"Elaboracions"}, tmpNode_Titol, true);
				<c:set var="idOrg" value="0"/>
				<c:forEach var="elaboracio" items="${trazabilitat[12]}" varStatus="status">
					<c:set var="idElab"><c:out value="${elaboracio.partidaOli.establiment.id}"/></c:set>
					<c:if test="${idOrg != idElab}">
						<c:set var="elaborador"><c:if test="${not empty elaboracio.partidaOli.establiment.codiRB}">RB0 <c:out value="${elaboracio.partidaOli.establiment.codiRB}"/> </c:if><c:if test="${not empty elaboracio.partidaOli.establiment.codiRC}">RC0 <c:out value="${elaboracio.partidaOli.establiment.codiRC}"/></c:if></c:set>
						<c:set var="idOrg" value="${idElab}"/>
						var tmpNode_Elaboracions_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"Almazara: ${elaborador}",id:"Elaboracions_<c:out value="${idElab}"/>"}, tmpNode_Elaboracions, false);
					</c:if>
					<fmt:formatDate var="dia" dateStyle="short" value="${elaboracio.data}"/>
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/>" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Elaboracions_<c:out value="${idElab}"/>, false);
					<%-- var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.responsable"/>: ${elaboracio.responsable}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false); --%>
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"Kilos de oliva utilitzados: ${elaboracio.totalKilos}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"Litros de aciete producidos: ${elaboracio.litres}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"Acidez: ${elaboracio.acidesa}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"Partida de aceite: ${elaboracio.partidaOli.nom}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"Categoria de aceite: ${elaboracio.categoriaOli.nom}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"Variedades: ${elaboracio.porcentatjesVarietats}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"Temperatura: ${elaboracio.temperatura}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					<c:if test="${elaboracio.talcQuantitat > 0}">
						var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_9"/> = new YAHOO.widget.TextNode({label:"Talco: ${elaboracio.talcQuantitat} de ${elaboracio.talcMarcaComercial} (${elaboracio.talcLot})" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_9"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					</c:if>
				</c:forEach>
			</c:if>
			
			<%-- Node de embotellats --%>
			<c:if test="${not empty trazabilitat[13]}">
				var tmpNode_Embotellats = new YAHOO.widget.TextNode({label:"Embotellado",id:"Embotellats"}, tmpNode_Titol, true);
				<c:set var="idOrg" value="0"/>
				<c:forEach var="embotellat" items="${trazabilitat[13]}" varStatus="status">
					<c:set var="idElab"><c:out value="${embotellat.zona.establiment.id}"/></c:set>
					<c:if test="${idOrg != idElab}">
						<c:set var="embotellador"><c:out value="${embotellat.zona.establiment.nom}"/></c:set>
						<c:set var="idOrg" value="${idElab}"/>
						var tmpNode_Embotellats_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"Envasadora: ${embotellador}",id:"Embotellats_<c:out value="${idElab}"/>"}, tmpNode_Embotellats, false);
					</c:if>
					<fmt:formatDate var="dia" dateStyle="short" value="${embotellat.data}"/>
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/>" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Embotellats_<c:out value="${idElab}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"Depósito de procedencia: ${embotellat.lot.diposit.codiAssignat}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"Nombre del lote: ${embotellat.lot.codiAssignat}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					<fmt:formatDate var="dataConsum" dateStyle="short" value="${embotellat.lot.dataConsum}"/>
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"Fecha de consumo preferente: ${dataConsum}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"Número de botellas: ${embotellat.botelles} bot. de ${embotellat.lot.etiquetatge.tipusEnvas.volum}l." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"Litros envasados: ${embotellat.lot.litresEnvassats}l." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"Marca: ${embotellat.lot.etiquetatge.marca.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"Envase: ${embotellat.lot.etiquetatge.nomEnvas}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"Partida de aceite: ${embotellat.lot.partidaOli.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_9"/> = new YAHOO.widget.TextNode({label:"Categoría de aceite: ${embotellat.lot.partidaOli.categoriaOli.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_9"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10"/> = new YAHOO.widget.TextNode({label:"Acidez: ${embotellat.acidesa}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					
					<c:if test="${not empty embotellat.lot.etiquetesLots}"> 
						<c:forEach var="etiquetaLot" items="${embotellat.lot.etiquetesLots}" varStatus="status2">
							<c:set var="lletra" value="${etiquetaLot.etiquetaLletra}" />
						</c:forEach>
					</c:if>
					<c:if test="${not empty embotellat.numerosContraetiquetes}">
						var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_11"/> = new YAHOO.widget.TextNode({label:"Números de contraetiquetas: (${lletra}) ${embotellat.numerosContraetiquetes}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_11"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					</c:if>
					<c:if test="${not empty embotellat.observacions}">
						var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_12"/> = new YAHOO.widget.TextNode({label:"Observaciones: ${embotellat.observacions}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_12"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
					</c:if>
				</c:forEach>
			</c:if>
			
		   tree.draw(); 
		}
		YAHOO.util.Event.addListener(window, "load", treeInit);
	</script>

</head>

<body>

<form id="formulario" name="cercaId" action="cercaid.ca.html" method="post" class="extended seguit ancho888" onsubmit="">
<div id="cercaIdMicroweb">
	<div class="cercaId">

		<h2 class="microweb">Buscar información</h2>
		<br />
		<h3>Introduce el código de la etiqueta:</h3>

		<p>
			<div id="divIdForetiquetaLletra" class=" campoFormPequeno conMargen required">
				<div><label for="etiquetaLletra">Letras</label></div>
				<div id="etiquetaLletraBordeFoco" class="bordeFoco">
					<input id="lletra" name="lletra" value="${lletres}" type="text" class="inputText" value="" onfocus="activaFoco(this,'etiquetaLletraBordeFoco')" onblur="desactivaFoco(this,'etiquetaLletraBordeFoco'); " maxlength="5"/> 
				</div>
			</div>

			<div id="divIdForetiquetaNumero" class=" campoFormPequeno conMargen required">
				<div><label for="etiquetaNumero">Dígitos</label></div>
				<div id="etiquetaNumeroBordeFoco" class="bordeFoco">
					<input id="numero" name="numero" value="${numeros}" type="text" class="inputText" value="" onfocus="activaFoco(this,'etiquetaNumeroBordeFoco')" onblur="desactivaFoco(this,'etiquetaNumeroBordeFoco'); " maxlength="10"  />
				</div>
			</div>

			<br />
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					buscar
				</a>
			</div>
			<input type="hidden" id="idioma" value="es" name="idioma"/>
		</p>


	</div>

	<div class="separadorH"></div>

	<div id="listadoAncho" class="arbol">
		<c:if test="${(empty id) && (!empty lletres) && (!empty numeros)}">
			<div>La etiqueta ${lletres}${numeros} no está asociada con una botella de DO o no está introducida en GestOli.</div>
		</c:if>
		<c:if test="${not empty id}">
			<br />
			<div id="arbreAqui"></div>
		</c:if>
	</div>

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