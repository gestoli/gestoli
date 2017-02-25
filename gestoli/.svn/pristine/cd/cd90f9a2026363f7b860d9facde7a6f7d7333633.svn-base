<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<html>
<head>

<title><fmt:message key="consulta.trazabilitat.titol" /></title>
<link rel="stylesheet" type="text/css" href="css/tree.css"
	media="screen" />
<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
<script type="text/javascript" src="js/yahoo/dom.js"></script>
<script type="text/javascript" src="js/yahoo/event.js"></script>
<script type="text/javascript" src="js/yahoo/treeview.js"></script>

<script type="text/javascript" language="javascript">
		var tree; 
		function treeInit() { 
		   	tree = new YAHOO.widget.TreeView("arbreAqui"); 
		   	var root = tree.getRoot(); 

		   	<%-- Node de Titol --%>
			var tmpNode_Titol = new YAHOO.widget.TextNode({label:"<c:out value="${titol}"/>",id:"Titol"}, root, true);
			tmpNode_Titol.labelStyle = "arbre_proces";
			
			<c:if test="${nomesLots == 'NO'}">
				var tmpNode_Diposits = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.diposits"/>",id:"Diposits"}, tmpNode_Titol, true);
				var tmpNode_Sortides = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.sortides"/>",id:"Sortides"}, tmpNode_Titol, true);
				var tmpNode_Lots = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.lots"/>",id:"Lots"}, tmpNode_Titol, true);
				var tmpNode_entradaOliva = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.finques"/>",id:"Finques"}, tmpNode_Titol, true);
			</c:if>
			

			<c:forEach var="fulla" items="${trazabilitat}" varStatus="status">
				
				<c:choose>
					
					<%-- Lot ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.Lot') == true}">                              
					<%-- Node de Lot --%>
						var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote"/> <c:out value="${fulla.codiAssignat}"/>",id:"<c:out value="${fulla.id}"/>"}, <c:if test="${nomesLots == 'NO'}">tmpNode_Lots</c:if><c:if test="${nomesLots == 'SI'}">tmpNode_Titol</c:if>, false);
						tmpNode_<c:out value="${fulla.id}"/>.labelStyle = "arbre_lot";																			
						<%-- Dades del lot --%>
						var tmpNode_<c:out value="${fulla.id}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.info"/>",id:"<c:out value="${fulla.id}"/>_info"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						tmpNode_<c:out value="${fulla.id}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla.zona}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.establiment"/>: <c:out value="${fulla.zona.establiment.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_0"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
							var tmpNode_<c:out value="${fulla.id}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.zona"/>: <c:out value="${fulla.zona.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_1"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.data}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.data"/>: <fmt:formatDate value="${fulla.data}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla.id}"/>_info_2"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>

						<c:if test="${not empty fulla.partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla.partidaOli}">${fulla.partidaOli.nom}</c:if></c:set>
							<c:if test="${not empty partidaOli}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla.id}"/>_info_3"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
							</c:if>									
							<c:set var="categoria"> <c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>
							<c:if test="${not empty categoria}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla.id}"/>_info_4"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
							</c:if>									
						</c:if>
						
						<c:if test="${not empty fulla.acidesa}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.acidesa"/>: <c:out value="${fulla.acidesa}"/>",id:"<c:out value="${fulla.id}"/>_info_5"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						
						<c:if test="${not empty fulla.etiquetatge}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.marca"/>: <c:out value="${fulla.etiquetatge.marca.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_6"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
							var tmpNode_<c:out value="${fulla.id}"/>_info_7 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.tipoEnvase"/>: <c:out value="${fulla.etiquetatge.tipusEnvas.nomSelect}"/>",id:"<c:out value="${fulla.id}"/>_info_7"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.litresPerduts}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_8 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.litresPerduts"/>: <c:out value="${fulla.litresPerduts}"/>",id:"<c:out value="${fulla.id}"/>_info_8"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
							<c:if test="${not empty fulla.motiuPerdua}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_9 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.motiuPerdua"/>: <c:out value="${fulla.motiuPerdua}"/>",id:"<c:out value="${fulla.id}"/>_info_9"}, tmpNode_<c:out value="${fulla.id}"/>_info_8, false);
							</c:if>
						</c:if>
						<c:if test="${not empty fulla.litresEnvassats}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_10 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.litresEnvassats"/>: <c:out value="${fulla.litresEnvassats}"/>",id:"<c:out value="${fulla.id}"/>_info_10"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);							
							
							<c:if test="${not empty fulla.numeroBotellesActuals}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_11 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroBotellesActuals"/>: <c:out value="${fulla.numeroBotellesActuals}"/>",id:"<c:out value="${fulla.id}"/>_info_11"}, tmpNode_<c:out value="${fulla.id}"/>_info_10, false);							
							</c:if>
							<c:if test="${not empty fulla.numeroBotellesInicials}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_12 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroBotellesInicials"/>: <c:out value="${fulla.numeroBotellesInicials}"/>",id:"<c:out value="${fulla.id}"/>_info_12"}, tmpNode_<c:out value="${fulla.id}"/>_info_10, false);							
							</c:if>
							<c:if test="${not empty fulla.numeroLot}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_13 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroLot"/>: <c:out value="${fulla.numeroLot}"/>",id:"<c:out value="${fulla.id}"/>_info_13"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);							
							</c:if>
						</c:if>
						var tmpNode_<c:out value="${fulla.id}"/>_sortides = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.sortides"/>",id:"<c:out value="${fulla.id}"/>_info"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						tmpNode_<c:out value="${fulla.id}"/>_sortides.labelStyle = "arbre_info";
						var tmpNode_<c:out value="${fulla.id}"/>_devolucions = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.cerca.devolucions"/>",id:"<c:out value="${fulla.id}"/>_info"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						tmpNode_<c:out value="${fulla.id}"/>_devolucions.labelStyle = "arbre_info";
					</c:when>

					<%-- EntradaLot --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.EntradaLot') == true}">

						<c:if test="${fulla.esDevolucio == true}">
							<%-- Node de Devoluciót --%>
							var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="edicioProcessos.accio13"/> (<fmt:formatDate value="${fulla.data}" type="date" dateStyle="short" timeStyle="short"/>): ${fulla.botelles} bot.",id:"<c:out value="${fulla.id}"/>"}, tmpNode_<c:out value="${fulla.lot.id}"/>_devolucions, false);
						</c:if>			
						<c:if test="${fulla.esDevolucio != true}">
							<c:if test="${not empty fulla.dipositProcedencia}">
								var tmpNode_<c:out value="${fulla.lot.id}"/>_info_14 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entradaLot.procedencia"/>: <c:out value="${fulla.dipositProcedencia}"/>",id:"<c:out value="${fulla.lot.id}"/>_info_14"}, tmpNode_<c:out value="${fulla.lot.id}"/>_info, false);
							</c:if>
						</c:if>																										
					</c:when>

					<%-- SortidaLot --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.SortidaLot') == true}">
						<c:if test="${not empty fulla.vendaData}">
							<c:set var="data"><fmt:formatDate value="${fulla.vendaData}" type="date" dateStyle="short" timeStyle="short"/></c:set>									
							<c:set var="texto"> <fmt:message key="consulta.trazabilitat.sortidaLot"/> - <fmt:message key="consulta.trazabilitat.sortidaLot.venta"/> (<c:out value="${data}"/>)</c:set>									
							<%-- Node de SortidaLot --%>
							var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${texto}"/>",id:"<c:out value="${fulla.id}"/>"}, tmpNode_<c:out value="${fulla.lot.id}"/>_sortides, false);
							<c:if test="${not empty fulla.vendaNumeroBotelles}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.sortidaLot.numBotellas"/>: <c:out value="${fulla.vendaNumeroBotelles}"/>",id:"<c:out value="${fulla.id}"/>_info_1"}, tmpNode_<c:out value="${fulla.id}"/>, false);
							</c:if>
							<c:if test="${not empty fulla.vendaDestinatari}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.sortidaLot.vendaDestinatari"/>: <c:out value="${fulla.vendaDestinatari}"/>",id:"<c:out value="${fulla.id}"/>_info_2"}, tmpNode_<c:out value="${fulla.id}"/>, false);
							</c:if>
						</c:if>																							
					</c:when>	

					<%-- Diposit -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.Diposit') == true}">                              
						<%-- Node de Dipòsit --%>
						var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${fulla.codiAssignat}"/>",id:"<c:out value="${fulla.id}"/>"},tmpNode_Diposits, false);
						tmpNode_<c:out value="${fulla.id}"/>.labelStyle = "arbre_lot";																			
						<%-- Dades ded diposit --%>
						var tmpNode_<c:out value="${fulla.id}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.info"/>",id:"<c:out value="${fulla.id}"/>_info"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						tmpNode_<c:out value="${fulla.id}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla.materialDiposit}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.materialDiposit"/>: <c:out value="${fulla.materialDiposit.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_0"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.capacitat}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.capacitat"/>: <c:out value="${fulla.capacitat}"/>",id:"<c:out value="${fulla.id}"/>_info_1"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.establiment}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.establiment"/>: <c:out value="${fulla.establiment.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_2"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.zona}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.zona"/>: <c:out value="${fulla.zona.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_3"}, tmpNode_<c:out value="${fulla.id}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla.partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla.partidaOli}">${fulla.partidaOli.nom}</c:if></c:set>
							<c:if test="${not empty partidaOli}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla.id}"/>_info_4"}, tmpNode_<c:out value="${fulla.id}"/>, false);
							</c:if>									
							<c:set var="categoria"> <c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>
							<c:if test="${not empty categoria}">
								var tmpNode_<c:out value="${fulla.id}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla.id}"/>_info_5"}, tmpNode_<c:out value="${fulla.id}"/>, false);
							</c:if>									
						</c:if>
						<c:if test="${not empty fulla.volumActual}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.volumActual"/>: <c:out value="${fulla.volumActual}"/>",id:"<c:out value="${fulla.id}"/>_info_6"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						</c:if>
					</c:when>

					<%-- Sortida Diposit ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.SortidaDiposit') == true}">                              
						<%-- Node de SortidaDiposit --%>
						var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida"/> <c:out value="${fulla.dipositBySdiCoddor.codiAssignat}"/> (<fmt:formatDate value="${fulla.data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla.id}"/>"}, tmpNode_Sortides, false);
						tmpNode_<c:out value="${fulla.id}"/>.labelStyle = "arbre_lot";
						<c:if test="${not empty fulla.litres}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.litros"/>: <c:out value="${fulla.litres}"/>",id:"<c:out value="${fulla.id}"/>_info_0"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						</c:if>
						<c:if test="${not empty fulla.partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla.partidaOli}">${fulla.partidaOli.nom}</c:if></c:set>									
							var tmpNode_<c:out value="${fulla.id}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla.id}"/>_info_1"}, tmpNode_<c:out value="${fulla.id}"/>, false);
							<c:set var="categoria"> <c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla.partidaOli.categoriaOli && fulla.partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>									
							var tmpNode_<c:out value="${fulla.id}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla.id}"/>_info_2"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						</c:if>
						<c:if test="${not empty fulla.desti}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.desti"/>: <c:out value="${fulla.desti}"/>",id:"<c:out value="${fulla.id}"/>_info_3"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						</c:if>
						
						<c:if test="${not empty fulla.observacions}">
							var tmpNode_<c:out value="${fulla.id}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.observacions"/>: <c:out value="${fulla.observacions}"/>",id:"<c:out value="${fulla.id}"/>_info_4"}, tmpNode_<c:out value="${fulla.id}"/>, false);
						</c:if>
					</c:when>
					

					<%-- Entrada oliva --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%>
					<c:when test="${fn:contains(fulla.class.name, 'es.caib.gestoli.logic.model.DescomposicioPartidaOliva') == true}">
						var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.plantacio"/>: <c:out value="${fulla.descomposicioPlantacio.plantacio.nomComplet}"/> ",id:"<c:out value="${fulla.id}"/>"}, tmpNode_entradaOliva, false);
						<c:if test="${not empty fulla.descomposicioPlantacio.id}">	
							var tmpNode_<c:out value="${fulla.id}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.nomFinca"/>: <c:out value="${fulla.descomposicioPlantacio.plantacio.finca.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_0"}, tmpNode_<c:out value="${fulla.id}"/>, false);							
						</c:if>
						<c:if test="${not empty fulla.descomposicioPlantacio.varietatOliva.id}">	
							var tmpNode_<c:out value="${fulla.id}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.varietat"/>: <c:out value="${fulla.descomposicioPlantacio.varietatOliva.nom}"/>",id:"<c:out value="${fulla.id}"/>_info_1"}, tmpNode_<c:out value="${fulla.id}"/>, false);							
						</c:if>
						<c:if test="${not empty fulla.kilos}">	
							var tmpNode_<c:out value="${fulla.id}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.kilos"/>: <c:out value="${fulla.kilos}"/>",id:"<c:out value="${fulla.id}"/>_info_2"}, tmpNode_<c:out value="${fulla.id}"/>, false);							
						</c:if>
					</c:when>	
					
					
				</c:choose>
			</c:forEach>
		   tree.draw(); 
		}
		YAHOO.util.Event.addListener(window, "load", treeInit);
	</script>

</head>
<body>
	<div id="listadoAncho" class="arbol">
	
		<div id="arbreAqui"></div>
	
		<div class="separadorH"></div>
	
		<div class="btnCorto" onclick="javascript:history.back();"
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')">
			<a id="enlaceTornarForm" href="javascript:void(0);"><fmt:message key="proces.tornar"/></a>
		</div>
	</div>
</body>
</html>
