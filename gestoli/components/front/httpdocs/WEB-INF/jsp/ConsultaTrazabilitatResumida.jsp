<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<html>
<head>

<title><fmt:message key="consulta.trazabilitat.resumida.titol" /></title>
<link rel="stylesheet" type="text/css" href="css/tree.css" media="screen" />
<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
<script type="text/javascript" src="js/yahoo/dom.js"></script>
<script type="text/javascript" src="js/yahoo/event.js"></script>
<script type="text/javascript" src="js/yahoo/treeview.js"></script>
<script src="http://yui.yahooapis.com/3.16.0/build/yui/yui-min.js"></script>

<script type="text/javascript" language="javascript">
		var tree; 
		function treeInit() { 
		   	tree = new YAHOO.widget.TreeView("arbreAqui"); 
		   	var root = tree.getRoot(); 
		   	<%--  <c:set var="parent">root</c:set> --%>
			
			<%-- Node de Titol --%>
			<c:choose>
				<c:when test="${trazabilitat[16] == 1}"> <%-- Elaboració --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.elaboracio"/>: <fmt:formatDate value="${trazabilitat[12][0].data}" type="date" dateStyle="short" timeStyle="short"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 2}"> <%-- Dipòsit --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.deposito"/>: <c:out value="${trazabilitat[15].codiAssignat}"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 3}"> <%-- Lot --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.lote"/>: <c:out value="${trazabilitat[14].codiAssignat}"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 4}"> <%-- Elaboracio oliva --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.elaboracio.oliva"/>: <fmt:formatDate value="${trazabilitat[20][0].data}" type="date" dateStyle="short" timeStyle="short"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 5}"> <%-- Bota --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.bota"/>: <c:out value="${trazabilitat[21].identificador}"/></c:set>
				</c:when>
				<c:when test="${trazabilitat[16] == 6}"> <%-- Lot oliva --%>
					<c:set var="titol"><fmt:message key="consulta.trazabilitat.lot.oliva"/>: <c:out value="${trazabilitat[14].codiAssignat}"/></c:set>
				</c:when>
				<c:otherwise> <%-- Partida d'oliva --%>
					<c:choose>
						<c:when test="${trazabilitat[18]}"> <%-- Oliva de taula --%>
						<c:set var="titol"><fmt:message key="consulta.trazabilitat.partida.oliva"/></c:set>
						</c:when>
						<c:otherwise> <%-- Oli --%>
							<c:set var="titol"><fmt:message key="consulta.trazabilitat.partida"/></c:set>
						</c:otherwise>
					</c:choose>
				</c:otherwise> 
			</c:choose>
			var tmpNode_Titol = new YAHOO.widget.TextNode({label:"<c:out value="${titol}"/>",id:"Titol"}, root, true);
			tmpNode_Titol.labelStyle = "arbre_lot";

			<c:choose>
				<c:when test="${trazabilitat[18]}"> <%-- Oliva de taula --%>
					<%-- Node de categoria d'oli --%>
					<c:if test="${not empty trazabilitat[1]}">
						<c:if test="${trazabilitat[1] == 1}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.NoDO"/></c:set></c:if>
						<c:if test="${trazabilitat[1] == 2}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.Pendent"/></c:set></c:if>
						<c:if test="${trazabilitat[1] == 3}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.DO"/></c:set></c:if>
						var tmpNode_Categoria = new YAHOO.widget.TextNode({label:"<c:out value="${categoria}"/>",id:"Categoria"}, tmpNode_Titol, true);
					</c:if>

					<%-- Node de tipus d'oliva --%>
					<c:if test="${not empty trazabilitat[19]}">
						<c:if test="${trazabilitat[19] == 0}"><c:set var="tipusOliva"><fmt:message key="consulta.trazabilitat.resumida.tipus.oliva.verda"/></c:set></c:if>
						<c:if test="${trazabilitat[19] == 1}"><c:set var="tipusOliva"><fmt:message key="consulta.trazabilitat.resumida.tipus.oliva.trencada"/></c:set></c:if>
						<c:if test="${trazabilitat[19] == 2}"><c:set var="tipusOliva"><fmt:message key="consulta.trazabilitat.resumida.tipus.oliva.negra"/></c:set></c:if>
						var tmpNode_TipusOliva = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.tipus.oliva"/>: <c:out value="${tipusOliva}"/>",id:"TipusOliva"}, tmpNode_Titol, true);
					</c:if>
					
					<%-- Node de varietats d'oliva, indicant percentatge --%>
					<c:if test="${not empty trazabilitat[5]}">
						var tmpNode_Varietats = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.varietats"/>",id:"Varietats"}, tmpNode_Titol, true);
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
						<c:set var="quantitat"><fmt:message key="consulta.trazabilitat.resumida.quantitat.oliva"/>: <c:out value="${trazabilitat[6]}"/>kg.<c:if test="${not empty trazabilitat[24]}"> / <fmt:message key="consulta.trazabilitat.resumida.quantitat.oliva.cribada"/>: <c:out value="${trazabilitat[24]}"/>kg.</c:if></c:set>
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
										var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"map#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#<fmt:message key="consulta.trazabilitat.resumida.mostrar.mapa"/>",label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Olivicultor: <c:out value="${olivicultor.nom}"/> (RT <c:out value="${olivicultor.codiDOPOliva}"/>) - <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
									</c:if>
									<c:if test="${empty descPartida[0].descomposicioPlantacio.plantacio.coordX}">
										var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Olivicultor: <c:out value="${olivicultor.nom}"/> (RT <c:out value="${olivicultor.codiDOPOliva}"/>) - <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
									</c:if>
								</c:forEach>						
							</c:if>
						</c:forEach>
					</c:if>

					<%-- Node de tamany de la partida --%>
					<c:if test="${not empty trazabilitat[8]}">
						<c:set var="tamany"><fmt:message key="consulta.trazabilitat.resumida.tamany.partida"/>: <c:out value="${trazabilitat[8]}"/> kg.</c:set>
						var tmpNode_Tamany = new YAHOO.widget.TextNode({label:"<c:out value="${tamany}"/>",id:"Tamany"}, tmpNode_Titol, true);
					</c:if>
					

					<%-- Node de municipis de producció de les olives --%>
					<c:if test="${not empty trazabilitat[10]}">
						var tmpNode_Municipis = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.municipis.produccio"/>",id:"Municipis"}, tmpNode_Titol, true);
						<c:forEach var="munpla" items="${trazabilitat[10]}" varStatus="status">
							<c:if test="${not empty munpla[0]}">
								var tmpNode_Municipis_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${munpla[0]}"/>" ,id:"Municipis_<c:out value="${status.count}"/>"},tmpNode_Municipis, false);
								<c:forEach var="plantacio" items="${munpla[1]}" varStatus="status2">
									<c:set var="olivicultor" value="${plantacio.finca.olivicultor}"/>
									<c:if test="${not empty plantacio.coordX}">
										var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"map#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#<fmt:message key="consulta.trazabilitat.resumida.mostrar.mapa"/>",label:"Olivicultor: <c:out value="${olivicultor.nom}"/> (RT <c:out value="${olivicultor.codiDOPOliva}"/>) - <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
									</c:if>
									<c:if test="${empty plantacio.coordX}">
										var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"Olivicultor: <c:out value="${olivicultor.nom}"/> (RT <c:out value="${olivicultor.codiDOPOliva}"/>) - <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
									</c:if>
								</c:forEach>						
							</c:if>
						</c:forEach>
					</c:if>

					<%-- Node de oli afegit --%>
					<c:if test="${not empty trazabilitat[15]}"> <%-- Dipòsit propi1 --%> 
						var tmpNode_LotOliPropiAfegit = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.lot.oli.propi"/>",id:"LotOliPropiAfegit"}, tmpNode_Titol, true);
						var tmpNode_LotOliPropiAfegit_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.lot.oli.propi.diposit"/>: <c:out value="${trazabilitat[15].codiAssignat}"/>",id:"tmpNode_LotOliPropiAfegit_1"}, tmpNode_LotOliPropiAfegit, true);
						<c:if test="${not empty trazabilitat[27]}">
							var tmpNode_LotOliPropiAfegit_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.lot.oli.propi.partida"/>: <c:out value="${trazabilitat[27].nom}"/>",id:"tmpNode_LotOliPropiAfegit_2"}, tmpNode_LotOliPropiAfegit, true);
						</c:if>
						var tmpNode_LotOliPropiAfegit_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.lot.oli.propi.acidesa"/>: <c:out value="${trazabilitat[3]}"/>",id:"tmpNode_LotOliPropiAfegit_3"}, tmpNode_LotOliPropiAfegit, true);
					</c:if>
					<c:if test="${not empty trazabilitat[26]}"> <%-- Lot oli --%> 
						<c:set var="lotOli"><fmt:message key="consulta.trazabilitat.resumida.lot.oli"/>: <c:out value="${trazabilitat[26]}"/></c:set>
						var tmpNode_LotOliAfegit = new YAHOO.widget.TextNode({label:"<c:out value="${lotOli}"/>",id:"LotOliAfegit"}, tmpNode_Titol, true);
					</c:if>

					<%-- Node de elaboracions d'oliva --%>
					<c:if test="${not empty trazabilitat[20]}">
						var tmpNode_Elaboracions = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio"/>",id:"Elaboracions"}, tmpNode_Titol, true);
						<c:set var="idOrg" value="0"/>
						<c:forEach var="elaboracio" items="${trazabilitat[20]}" varStatus="status">
							<c:set var="idElab"><c:out value="${elaboracio.establiment.id}"/></c:set>
							<c:if test="${idOrg != idElab}">
								<c:set var="elaborador"><c:out value="${elaboracio.establiment.nom}"/></c:set>
								<c:set var="idOrg" value="${idElab}"/>
								var tmpNode_Elaboracions_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.tafona"/>: ${elaborador}",id:"Elaboracions_<c:out value="${idElab}"/>"}, tmpNode_Elaboracions, false);
							</c:if>
							<fmt:formatDate var="dia" dateStyle="short" value="${elaboracio.data}"/>
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/>" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Elaboracions_<c:out value="${idElab}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.kilos"/>: ${elaboracio.totalKilos}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.cribat"/>: ${elaboracio.bota.kgOliva}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.salmorra"/>: ${elaboracio.bota.concentracioSalmorra}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.salmorra.lot"/>: ${elaboracio.bota.lotSal}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${not empty elaboracio.bota.gFonoll}">
								var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll"/>: ${elaboracio.bota.gFonoll} gr." ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
								<c:if test="${not empty elaboracio.bota.partidaFonoll}">
									<fmt:formatDate var="dia_f" dateStyle="short" value="${elaboracio.bota.partidaFonoll.data}"/>
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot"/> (Propi): ${elaboracio.bota.partidaFonoll.codi}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/>_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot.data"/>: <c:out value="${dia_f}"/> ${elaboracio.bota.partidaFonoll.hora}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6_1"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6, false);
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/>_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot.titular"/>: ${elaboracio.bota.partidaFonoll.titular}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6_2"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6, false);
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/>_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot.finca"/>: ${elaboracio.bota.partidaFonoll.municipi.nom} (Pol. ${elaboracio.bota.partidaFonoll.poligon}, Par. ${elaboracio.bota.partidaFonoll.parcela})" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6_3"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6, false);
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/>_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot.quantitat"/>: ${elaboracio.bota.partidaFonoll.kgInici} kg." ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6_4"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6, false);
								</c:if>
								<c:if test="${not empty elaboracio.bota.lotFonoll}">
									var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.fonoll.lot"/>: ${elaboracio.bota.lotFonoll}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
								</c:if>
							</c:if>
							<c:if test="${not empty elaboracio.bota.gPebre}">
								var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.pebre"/>: ${elaboracio.bota.gPebre} gr." ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
								var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.pebre.lot"/>: ${elaboracio.bota.lotPebre}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>
					</c:if>

					<%-- Node de envassats --%>
					<c:if test="${not empty trazabilitat[13]}">
						var tmpNode_Embotellats = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat"/>",id:"Embotellats"}, tmpNode_Titol, true);
						<c:set var="idOrg" value="0"/>
						<c:forEach var="envassat" items="${trazabilitat[13]}" varStatus="status">
							<c:set var="idElab"><c:out value="${envassat.zona.establiment.id}"/></c:set>
							<c:if test="${idOrg != idElab}">
								<c:set var="envassador"><c:out value="${envassat.zona.establiment.nom}"/></c:set>
								<c:set var="idOrg" value="${idElab}"/>
								var tmpNode_Embotellats_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envasadora"/>: ${envassador}",id:"Embotellats_<c:out value="${idElab}"/>"}, tmpNode_Embotellats, false);
							</c:if>
							<fmt:formatDate var="dia" dateStyle="short" value="${envassat.data}"/>
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/>" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Embotellats_<c:out value="${idElab}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.bota.procedencia"/>: ${envassat.lot.bota.identificador}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.lot"/>: ${envassat.lot.codiAssignat}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.pots"/>: ${envassat.botelles} pots de ${envassat.lot.etiquetatge.tipusEnvas.volum}l." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.kilos.envassats"/>: ${envassat.lot.litresEnvassats} kg." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:set var="sortida" value="${trazabilitat[25][status.count-1]}"/>
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.kilos.oliva"/>: ${sortida.kgOliva} kg." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.marca"/>: ${envassat.lot.etiquetatge.marca.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.envas"/>: ${envassat.lot.etiquetatge.nomEnvas}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.lot.pots"/>: ${envassat.lot.numeroLot}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${empty envassat.lot.olivaDO}"><c:set var="catlot"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.Pendent"/></c:set></c:if>
							<c:if test="${envassat.lot.olivaDO == true}"><c:set var="catlot"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.DO"/></c:set></c:if>
							<c:if test="${envassat.lot.olivaDO == false}"><c:set var="catlot"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.NoDO"/></c:set></c:if>
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_9"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.categoria"/>: ${catlot}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_9"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${empty sortida.pH2}"><c:set var="ph" value="${sortida.pH1}"/></c:if><c:if test="${not empty sortida.pH2}"><c:set var="ph" value="${sortida.pH2}"/></c:if>
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.ph"/>: ${ph}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${not empty sortida.pH2}">
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.ph.inicial"/>: ${sortida.pH1}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10_1"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10, false);
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10_2"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.ph.acid"/>: ${sortida.quantitatAcidCitric} ml." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10_2"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10, false);
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10_3"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envassat.ph.acid.lot"/>: ${sortida.lotAcidCitric}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10_3"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10, false);
							</c:if>
							<c:if test="${not empty envassat.numerosContraetiquetes}">
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_11"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.numero.contraetiquetes"/>: ${embotellat.numerosContraetiquetes}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_11"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
							<c:if test="${not empty envassat.observacions}">
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_12"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.observacions"/>: ${embotellat.observacions}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_12"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>
					</c:if>
					
				</c:when>
				<c:otherwise> <%-- Oli --%>
					<%-- Node de categoria d'oli --%>
					<c:if test="${not empty trazabilitat[1]}">
						<c:if test="${trazabilitat[1] == 1}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.NoDO"/></c:set></c:if>
						<c:if test="${trazabilitat[1] == 2}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.Pendent"/></c:set></c:if>
						<c:if test="${trazabilitat[1] == 3}"><c:set var="categoria"><fmt:message key="consulta.trazabilitat.resumida.categoria.DO"/></c:set></c:if>
						var tmpNode_Categoria = new YAHOO.widget.TextNode({label:"<c:out value="${categoria}"/>",id:"Categoria"}, tmpNode_Titol, true);
					</c:if>
					
					<%-- Node de tipus d'oli --%>
					<c:if test="${not empty trazabilitat[2]}">
						<c:if test="${trazabilitat[2].id == 1}"><c:set var="tipusOli"><fmt:message key="consulta.trazabilitat.resumida.tipus.oli.vergeExtre"/></c:set></c:if>
						<c:if test="${trazabilitat[2].id == 2}"><c:set var="tipusOli"><fmt:message key="consulta.trazabilitat.resumida.tipus.oli.verge"/></c:set></c:if>
						<c:if test="${trazabilitat[2].id == 3}"><c:set var="tipusOli"><fmt:message key="consulta.trazabilitat.resumida.tipus.oli.llampant"/></c:set></c:if>
						var tmpNode_TipusOli = new YAHOO.widget.TextNode({label:"<c:out value="${tipusOli}"/>",id:"TipusOli"}, tmpNode_Titol, true);
					</c:if>
					
					<%-- Node d'acidesa --%>
					<c:if test="${not empty trazabilitat[3]}">
						<c:set var="acidesa"><fmt:message key="consulta.trazabilitat.resumida.acidesa"/>: <c:out value="${trazabilitat[3]}"/></c:set>
						<c:if test="${not empty trazabilitat[4]}">
							var tmpNode_Analitica = new YAHOO.widget.TextNode({href:"javascript:void(0);", params:"pdf#<fmt:message key="consulta.trazabilitat.resumida.mostrar.pdf.analisi"/>", label:"<c:out value="${acidesa}"/>",id:"Acidesa"}, tmpNode_Titol, true);
						</c:if>
						<c:if test="${empty trazabilitat[4]}">
							var tmpNode_Acidesa = new YAHOO.widget.TextNode({label:"<c:out value="${acidesa}"/>",id:"Acidesa"}, tmpNode_Titol, true);
						</c:if>
					</c:if>
					
					
					<%-- Node de varietats d'oliva, indicant percentatge --%>
					<c:if test="${not empty trazabilitat[5]}">
						var tmpNode_Varietats = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.varietats"/>",id:"Varietats"}, tmpNode_Titol, true);
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
						<c:set var="quantitat"><fmt:message key="consulta.trazabilitat.resumida.quantitat.oliva"/>: <c:out value="${trazabilitat[6]}"/>kg.</c:set>
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
										var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"map#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#<fmt:message key="consulta.trazabilitat.resumida.mostrar.mapa"/>",label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Olivicultor: <c:out value="${olivicultor.nom}"/> (<c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>) - <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
									</c:if>
									<c:if test="${empty descPartida[0].descomposicioPlantacio.plantacio.coordX}">
										var tmpNode_Quantitat_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/> - <c:out value="${quant}"/>kg. - Olivicultor: <c:out value="${olivicultor.nom}"/> (<c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>) - <c:out value="${descPartida[0].descomposicioPlantacio.plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Quantitat_<c:out value="${status.count}"/>, false);
									</c:if>
								</c:forEach>						
							</c:if>
						</c:forEach>
					</c:if>
					
					<%-- Node de tamany de la partida --%>
					<c:if test="${not empty trazabilitat[8]}">
						<c:set var="tamany"><fmt:message key="consulta.trazabilitat.resumida.tamany.partida"/>: <c:out value="${trazabilitat[8]}"/>l.</c:set>
						var tmpNode_Tamany = new YAHOO.widget.TextNode({label:"<c:out value="${tamany}"/>",id:"Tamany"}, tmpNode_Titol, true);
						
					</c:if>
					
					<%-- Node de altres diposits amb la mateixa partida --%>
					<c:if test="${not empty trazabilitat[29]}"> 
						<c:set var="diposit"><fmt:message key="consulta.trazabilitat.resumida.altres.diposits"/>: <c:out value="${diposits}"/></c:set>						
						var tmpNode_Diposit = new YAHOO.widget.TextNode({label:"<c:out value="${diposit}"/>",id:"Diposits"}, tmpNode_Titol, true);
					</c:if> 
					
					
										
					<%-- Node de volum del dipòsit --%>
					<c:if test="${not empty trazabilitat[28]}">
						<c:set var="volumActual"><fmt:message key="consulta.trazabilitat.resumida.volum.actual"/>: <c:out value="${trazabilitat[28]}"/> l.</c:set>
						var tmpNode_VolumActual = new YAHOO.widget.TextNode({label:"<c:out value="${volumActual}"/>",id:"Volum actual"}, tmpNode_Titol, true);
					</c:if>
					
					<%-- Node de rendiment --%>
					<c:if test="${not empty trazabilitat[9]}">
						<fmt:formatNumber var="rend" type="number" pattern="0.00" value="${trazabilitat[9]}"/>
						<c:set var="rendiment"><fmt:message key="consulta.trazabilitat.resumida.rendiment"/>: <c:out value="${rend}"/>%</c:set>
						var tmpNode_Rendiment = new YAHOO.widget.TextNode({label:"<c:out value="${rendiment}"/>",id:"Rendiment"}, tmpNode_Titol, true);
					</c:if>
		
					<%-- Node de municipis de producció de les olives --%>
					<c:if test="${not empty trazabilitat[10]}">
						var tmpNode_Municipis = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.municipis.produccio"/>",id:"Municipis"}, tmpNode_Titol, true);
						<c:forEach var="munpla" items="${trazabilitat[10]}" varStatus="status">
							<c:if test="${not empty munpla[0]}">
								var tmpNode_Municipis_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${munpla[0]}"/>" ,id:"Municipis_<c:out value="${status.count}"/>"},tmpNode_Municipis, false);
								<c:forEach var="plantacio" items="${munpla[1]}" varStatus="status2">
									<c:set var="olivicultor" value="${plantacio.finca.olivicultor}"/>
									<c:if test="${not empty plantacio.coordX}">
										var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"map#<c:out value="${plantacio.coordX}"/>#<c:out value="${plantacio.coordY}"/>#<c:out value="${plantacio.municipi.nom}"/>#<c:out value="${plantacio.poligon}"/>#<c:out value="${plantacio.parcela}"/>#<c:out value="${plantacio.catastre}"/>#<fmt:message key="consulta.trazabilitat.resumida.mostrar.mapa"/>",label:"Olivicultor: <c:out value="${olivicultor.nom}"/> (<c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>) - <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
									</c:if>
									<c:if test="${empty plantacio.coordX}">
										var tmpNode_Municipis_<c:out value="${status.count}"/>_<c:out value="${status2.count}"/> = new YAHOO.widget.TextNode({label:"Olivicultor: <c:out value="${olivicultor.nom}"/> (<c:if test="${not empty olivicultor.codiDO}">RA <c:out value="${olivicultor.codiDO}"/> </c:if><c:if test="${not empty olivicultor.codiDOExperimental}">RE <c:out value="${olivicultor.codiDOExperimental}"/></c:if>) - <c:out value="${plantacio.nomComplet}"/> " ,id:"Varietat_<c:out value="${status.count}"/>"},tmpNode_Municipis_<c:out value="${status.count}"/>, false);
									</c:if>
								</c:forEach>						
							</c:if>
						</c:forEach>
					</c:if>
					
					<%-- Node de elaboracions --%>
					<c:if test="${not empty trazabilitat[12]}">
						var tmpNode_Elaboracions = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio"/>",id:"Elaboracions"}, tmpNode_Titol, true);
						<c:set var="idOrg" value="0"/>
						<c:forEach var="elaboracio" items="${trazabilitat[12]}" varStatus="status">
							<c:set var="idElab"><c:out value="${elaboracio.partidaOli.establiment.id}"/></c:set>
							<c:if test="${idOrg != idElab}">
								<c:set var="elaborador"><c:out value="${elaboracio.partidaOli.establiment.nom}"/></c:set>
								<c:set var="idOrg" value="${idElab}"/>
								var tmpNode_Elaboracions_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.tafona"/>: ${elaborador}",id:"Elaboracions_<c:out value="${idElab}"/>"}, tmpNode_Elaboracions, false);
							</c:if>
							<fmt:formatDate var="dia" dateStyle="short" value="${elaboracio.data}"/>
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({href:"javascript:void(0);",params:"rend#<c:out value="${elaboracio.id}"/>#<c:out value="${idElab}"/>#<fmt:message key="consulta.trazabilitat.resumida.mostrar.pdf.rendiment"/>",label:"<c:out value="${dia}"/>" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Elaboracions_<c:out value="${idElab}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.responsable"/>: ${elaboracio.responsable}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.kilos"/>: ${elaboracio.totalKilos}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.litres"/>: ${elaboracio.litres}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.acidesa"/>: ${elaboracio.acidesa}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.partida.oli"/>: ${elaboracio.partidaOli.nom}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.categoria"/>: ${elaboracio.categoriaOli.nom}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.temperatura"/>: ${elaboracio.temperatura}" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${elaboracio.talcQuantitat > 0}">
								var tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.talc"/>: ${elaboracio.talcQuantitat} de ${elaboracio.talcMarcaComercial} (${elaboracio.talcLot})" ,id:"Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Elaboracions_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>
					</c:if>
					
					<%-- Node de embotellats --%>
					<c:if test="${not empty trazabilitat[13]}">
						var tmpNode_Embotellats = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat"/>",id:"Embotellats"}, tmpNode_Titol, true);
						<c:set var="idOrg" value="0"/>
						<c:forEach var="embotellat" items="${trazabilitat[13]}" varStatus="status">
							<c:set var="idElab"><c:out value="${embotellat.zona.establiment.id}"/></c:set>
							<c:if test="${idOrg != idElab}">
								<c:set var="embotellador"><c:out value="${embotellat.zona.establiment.nom}"/></c:set>
								<c:set var="idOrg" value="${idElab}"/>
								var tmpNode_Embotellats_<c:out value="${idElab}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.envasadora"/>: ${embotellador}",id:"Embotellats_<c:out value="${idElab}"/>"}, tmpNode_Embotellats, false);
							</c:if>
							<fmt:formatDate var="dia" dateStyle="short" value="${embotellat.data}"/>
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${dia}"/>" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>"},tmpNode_Embotellats_<c:out value="${idElab}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_1"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.diposit.procedencia"/>: ${embotellat.lot.diposit.codiAssignat}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_1"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_2"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.lot"/>: ${embotellat.lot.codiAssignat}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_2"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_3"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.botelles"/>: ${embotellat.botelles} bot. de ${embotellat.lot.etiquetatge.tipusEnvas.volum}l." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_3"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_4"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.litres.envassats"/>: ${embotellat.lot.litresEnvassats}l." ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_4"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_5"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.marca"/>: ${embotellat.lot.etiquetatge.marca.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_5"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_6"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.envas"/>: ${embotellat.lot.etiquetatge.nomEnvas}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_6"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_7"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.partida.oli"/>: ${embotellat.lot.partidaOli.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_7"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_8"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.elaboracio.categoria"/>: ${embotellat.lot.partidaOli.categoriaOli.nom}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_8"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_9"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.acidesa"/>: ${embotellat.acidesa}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_9"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							<c:if test="${not empty embotellat.numerosContraetiquetes}">
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_10"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.numero.contraetiquetes"/>: ${embotellat.numerosContraetiquetes}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_10"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
							<c:if test="${not empty embotellat.observacions}">
								var tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}_11"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.resumida.embotellat.observacions"/>: ${embotellat.observacions}" ,id:"Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>_11"},tmpNode_Embotellats_<c:out value="${idElab}"/>_<c:out value="${status.count}"/>, false);
							</c:if>
						</c:forEach>
					</c:if>
				</c:otherwise>
			</c:choose>
		   tree.draw(); 
		}
		YAHOO.util.Event.addListener(window, "load", treeInit);
	</script>

</head>
<body>
	<div id="listadoAncho" class="arbol">

		<div id="arbreAqui"></div>
		<div class="separadorH"></div>
		<div class="btnCorto" onclick="javascript:history.back();" onmouseover="underline('enlaceTornarForm')" onmouseout="underline('enlaceTornarForm')">
			<a id="enlaceTornarForm" href="javascript:void(0);"><fmt:message key="proces.tornar" /></a>
		</div>
	</div>

	<form id="formAnalitica" action="GenerarPdf.html" method="post" class="seguit">
		<input type="hidden" id="tipus" name="tipus" value="7" /> 
		<input type="hidden" id="idAna" name="idAna" value="${trazabilitat[4].id}"/> 
		<input type="hidden" id="idEst" name="idEst" value="${trazabilitat[4].establiment.id}"/> 
		<input type="hidden" id="idDip" name="idDip" value="${trazabilitat[4].diposit.id}"/>
	</form>
	<form id="formRendiment" action="GenerarPdf.html" method="post" class="seguit">
		<input type="hidden" id="tipus" name="tipus" value="8" /> 
		<input type="hidden" id="idElaboracio" name="idElaboracio" value=""/> 
		<input type="hidden" id="idEstabliment" name="idEstabliment" value="${trazabilitat[4].establiment.id}"/> 
	</form>

</body>
</html>