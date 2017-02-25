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
		   	<c:forEach var="fulla" items="${trazabilitat}" varStatus="status">
			
				<c:choose>
					<c:when test="${not empty fulla[2]}">
						<c:set var="parent">tmpNode_<c:out value="${fulla[2]}"/></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="parent">root</c:set>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.Elaboracio') == true}">                              
						<%-- Node de  Elaboracio --%>
						<c:set var="text"><fmt:message key="consulta.trazabilitat.elaboracio"/>: <c:out value="${fulla[1].litres}"/> l. de <c:if test="${not empty fulla[1].categoriaOli}"><c:out value="${fulla[1].categoriaOli.nom}"/></c:if></c:set>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${text}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot";
						<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot mangarrufes ygtvlabel";</c:if>
						<%-- Dades de l'elaboracio --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.infoElaboracio"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla[1].data}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.data"/>: <fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].numeroElaboracio}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.numeroEntrada"/>: <c:out value="${fulla[1].numeroElaboracio}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].responsable}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.responsable"/>: <c:out value="${fulla[1].responsable}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].acidesa}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.acidesa"/>: <c:out value="${fulla[1].acidesa}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].temperatura}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.temperatura"/>: <c:out value="${fulla[1].temperatura}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].talcMarcaComercial}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.talcMarca"/>: <c:out value="${fulla[1].talcMarcaComercial}"/>",id:"<c:out value="${fulla[0]}"/>_info_5"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].talcLot}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.talcLot"/>: <c:out value="${fulla[1].talcLot}"/>",id:"<c:out value="${fulla[0]}"/>_info_6"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].talcQuantitat}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_7 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.talcQuantitat"/>: <c:out value="${fulla[1].talcQuantitat}"/>",id:"<c:out value="${fulla[0]}"/>_info_7"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].observacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_8 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.elaboracio.observacions"/>: <c:out value="${fulla[1].observacions}"/>",id:"<c:out value="${fulla[0]}"/>_info_8"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>			
										
					</c:when>
					
					<%-- <c:when test="${fulla[1].class.name == 'es.caib.gestoli.logic.model.PartidaOliva'}"> --%>
					<c:when test="${fulla[3]== 'Partida'}">					
						<%-- Node de tipus PartidaOliva --%>
						<c:set var="text"><fmt:message key="consulta.trazabilitat.partida"/> <c:out value="${fulla[1].descPartida}"/></c:set>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${text}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot";
						<%-- Dades de la PartidaOliva --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.infoPartida"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						
						<c:if test="${not empty fulla[1].data}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.partida.data"/>: <fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].hora}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.partida.hora"/>: <c:out value="${fulla[1].hora}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].numeroEntrada}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.partida.numeroEntrada"/>: <c:out value="${fulla[1].numeroEntrada}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.partida.sana"/>: <c:choose><c:when test="${fulla[1].sana}"><fmt:message key="consulta.trazabilitat.partida.sana.si"/></c:when><c:when test="${!fulla[1].sana}"><fmt:message key="consulta.trazabilitat.partida.sana.no"/></c:when></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.Olivicultor') == true}"> 					
						<%-- Node de tipus Olivicultor --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.olivicultor"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<%-- Dades de la PartidaOliva --%>
						<c:if test="${not empty fulla[1].nom}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.olivicultor.nom"/>: <c:out value="${fulla[1].nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].codiDO}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.olivicultor.ra"/>: <c:out value="${fulla[1].codiDO}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].codiDOExperimental}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.olivicultor.re"/>: <c:out value="${fulla[1].codiDOExperimental}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>						
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.DescomposicioPartidaOliva') == true}"> 
					<%-- Node de tipus DescomposicioPartidaOliva --%>
						<c:if test="${not empty fulla[1].descomposicioPlantacio && not empty fulla[1].descomposicioPlantacio.varietatOliva}">
							var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.variedad"/> <c:out value="${fulla[1].descomposicioPlantacio.varietatOliva.nom}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
							<c:if test="${not empty fulla[1].kilos}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.kilos"/>: <c:out value="${fulla[1].kilos}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"},  tmpNode_<c:out value="${fulla[0]}"/>, false);
							</c:if>
							<c:if test="${not empty fulla[1].descomposicioPlantacio.plantacio}">
								var tmpNode_<c:out value="${fulla[0]}"/>_plantacio = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.plantacion"/>"}, tmpNode_<c:out value="${fulla[0]}"/>, false);								
								tmpNode_<c:out value="${fulla[0]}"/>_plantacio.labelStyle = "arbre_info";
								<c:if test="${not empty fulla[1].descomposicioPlantacio.plantacio.finca}">
									var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.plantacion.finca"/>: <c:out value="${fulla[1].descomposicioPlantacio.plantacio.finca.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_plantacio, false);								
								</c:if>
								<c:if test="${not empty fulla[1].descomposicioPlantacio.plantacio.municipi}">
									var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.plantacion.municipio"/>: <c:out value="${fulla[1].descomposicioPlantacio.plantacio.municipi.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_plantacio, false);								
								</c:if>
								<c:if test="${not empty fulla[1].descomposicioPlantacio.plantacio.poligon}">
									var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.plantacion.poligono"/>: <c:out value="${fulla[1].descomposicioPlantacio.plantacio.poligon}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>_plantacio, false);								
								</c:if>
								<c:if test="${not empty fulla[1].descomposicioPlantacio.plantacio.parcela}">
									var tmpNode_<c:out value="${fulla[0]}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.descomposicion.plantacion.parcela"/>: <c:out value="${fulla[1].descomposicioPlantacio.plantacio.parcela}"/>",id:"<c:out value="${fulla[0]}"/>_info_5"}, tmpNode_<c:out value="${fulla[0]}"/>_plantacio, false);								
								</c:if>
							</c:if>
						</c:if>								
									
					</c:when>
					
					<c:when test="${fulla[3]=='OliRetiratPropietario'}"> 					
						<%-- Node de EntradaDiposit --%>
						<c:if test="${not empty fulla[1].litres}">
							var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.retiradoPropietario"/>: <c:out value="${fulla[1].litres}"/> l. ",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						</c:if>																				
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.EntradaDiposit') == true}"> 
						<%-- Node de EntradaDiposit --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada"/> <c:out value="${fulla[1].diposit.codiAssignat}"/> (<fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<c:if test="${not empty fulla[1].litres}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.litros"/>: <c:out value="${fulla[1].litres}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla[1].partidaOli}">${fulla[1].partidaOli.nom}</c:if></c:set>									
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							<c:set var="categoria"> <c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>									
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].acidesa}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.acidesa"/>: <c:out value="${fulla[1].acidesa}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].observacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.observacions"/>: <c:out value="${fulla[1].observacions}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>														
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.SortidaDiposit') == true}"> 
						<%-- Node de SortidaDiposit --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida"/> <c:out value="${fulla[1].dipositBySdiCoddor.codiAssignat}"/> (<fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<c:if test="${not empty fulla[1].litres}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.litros"/>: <c:out value="${fulla[1].litres}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla[1].partidaOli}">${fulla[1].partidaOli.nom}</c:if></c:set>									
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							<c:set var="categoria"> <c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>									
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].desti}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.desti"/>: <c:out value="${fulla[1].desti}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].observacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.salida.observacions"/>: <c:out value="${fulla[1].observacions}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>															
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.Analitica') == true}"> 
						<%-- Node de Analitica --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica"/> (<fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						
						<%-- Node Informació General --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_anainfo = new YAHOO.widget.TextNode({label:"<fmt:message key="analitica.camp.infoGeneral"/>",id:"<c:out value="${fulla[0]}"/>_anainfo"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						<c:if test="${not empty fulla[1].data}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="analitica.camp.data"/>: <fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>_anainfo, false);
						</c:if>
						<c:if test="${not empty fulla[1].varietatOli}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="analitica.camp.varietatOli"/>: <c:out value="${fulla[1].varietatOli.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_anainfo, false);
						</c:if>
						<c:if test="${not empty fulla[1].partidaOli}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="analitica.camp.nomPartida"/>: <c:out value="${fulla[1].partidaOli.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_anainfo, false);
						</c:if>

						<%-- Node Sensorial --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_sensorial = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.analiticaSensorial"/>",id:"<c:out value="${fulla[0]}"/>_sensorial"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						<c:if test="${not empty fulla[1].analisiSensorialNomLaboratori}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.nomLaboratori"/>: <c:out value="${fulla[1].analisiSensorialNomLaboratori}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_sensorial, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiSensorialDataRecepcio}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.dataRecepcio"/>: <fmt:formatDate value="${fulla[1].analisiSensorialDataRecepcio}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>_sensorial, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiSensorialDataTast}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.dataTast"/>: <fmt:formatDate value="${fulla[1].analisiSensorialDataTast}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_5"}, tmpNode_<c:out value="${fulla[0]}"/>_sensorial, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiSensorialObservacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.observacions"/>: <c:out value="${fulla[1].analisiSensorialObservacions}"/>",id:"<c:out value="${fulla[0]}"/>_info_6"}, tmpNode_<c:out value="${fulla[0]}"/>_sensorial, false);
						</c:if>
						
						<%-- Node FisicoQuimic --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.analiticaFQ"/>",id:"<c:out value="${fulla[0]}"/>_FisicoQuimic"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						<c:if test="${not empty fulla[1].analisiFisicoQuimicNomLaboratori}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_7 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.nomLaboratori"/>: <c:out value="${fulla[1].analisiFisicoQuimicNomLaboratori}"/>",id:"<c:out value="${fulla[0]}"/>_info_7"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiFisicoQuimicDataRecepcio}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_8 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.dataRecepcio"/>: <fmt:formatDate value="${fulla[1].analisiFisicoQuimicDataRecepcio}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_8"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic , false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiFisicoQuimicDataInici}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_9 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.dataInici"/>: <fmt:formatDate value="${fulla[1].analisiFisicoQuimicDataInici}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_9"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiFisicoQuimicDataFi}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_10 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.dataFi"/>: <fmt:formatDate value="${fulla[1].analisiFisicoQuimicDataFi}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_10"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiFisicoQuimicValid}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_11 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.valid"/>: <c:choose><c:when test="${fulla[1].analisiFisicoQuimicValid}"><fmt:message key="consulta.trazabilitat.analitica.si"/></c:when><c:when test="${!fulla[1].analisiFisicoQuimicValid}"><fmt:message key="consulta.trazabilitat.analitica.no"/></c:when></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_11"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic, false);
						</c:if>
						<c:if test="${not empty fulla[1].analisiFisicoQuimicObservacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_12 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.analitica.observacions"/>: <c:out value="${fulla[1].analisiFisicoQuimicObservacions}"/>",id:"<c:out value="${fulla[0]}"/>_info_12"}, tmpNode_<c:out value="${fulla[0]}"/>_FisicoQuimic, false);
						</c:if>
					</c:when>
					
					<c:when test="${fn:contains(fulla[1].class.name, 'es.caib.gestoli.logic.model.Trasllat') == true}"> 
						<%-- Node de SortidaDiposit --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat"/> (<fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<c:if test="${not empty fulla[1].establimentByTdiCodeor}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.establimentByTdiCodeor"/>: <c:out value="${fulla[1].establimentByTdiCodeor.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].establimentByTdiCodede}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.establimentByTdiCodede"/>: <c:out value="${fulla[1].establimentByTdiCodede.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>

						var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.acceptatTrasllat"/>: <c:choose><c:when test="${not empty fulla[1].acceptatTrasllat && fulla[1].acceptatTrasllat}"><fmt:message key="consulta.trazabilitat.trasllat.si"/></c:when><c:when test="${not empty fulla[1].acceptatTrasllat && !fulla[1].acceptatTrasllat}"><fmt:message key="consulta.trazabilitat.trasllat.no"/></c:when><c:otherwise><fmt:message key="manteniment.pendiente"/></c:otherwise></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.traslladant"/>: <c:choose><c:when test="${not empty fulla[1].traslladant && fulla[1].traslladant}"><fmt:message key="consulta.trazabilitat.trasllat.si"/></c:when><c:otherwise><fmt:message key="consulta.trazabilitat.trasllat.no"/></c:otherwise></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.retornatEstablimentOrigen"/>: <c:choose><c:when test="${not empty fulla[1].retornatEstablimentOrigen && fulla[1].retornatEstablimentOrigen}"><fmt:message key="consulta.trazabilitat.trasllat.si"/></c:when><c:otherwise><fmt:message key="consulta.trazabilitat.trasllat.no"/></c:otherwise></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						<%--c:if test="${not empty not empty fulla[1].acceptatTrasllat && fulla[1].acceptatTrasllat}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.traslladat"/>: <c:choose><c:when test="${not empty fulla[1].traslladat && fulla[1].traslladat}"><fmt:message key="consulta.trazabilitat.trasllat.si"/></c:when><c:otherwise><fmt:message key="consulta.trazabilitat.trasllat.no"/></c:otherwise></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.trasllat.retornatEstablimentOrigen"/>: <c:choose><c:when test="${not empty fulla[1].retornatEstablimentOrigen && fulla[1].retornatEstablimentOrigen}"><fmt:message key="consulta.trazabilitat.trasllat.si"/></c:when><c:otherwise><fmt:message key="consulta.trazabilitat.trasllat.no"/></c:otherwise></c:choose>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if--%>																		
					</c:when>
					
					<c:when test="${fulla[3]=='Deposito'}">	
					<%-- Node de Deposito --%>
						
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${fulla[1].codiAssignat}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot";																			
						<%-- Dades ded diposit --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.info"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla[1].materialDiposit}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.materialDiposit"/>: <c:out value="${fulla[1].materialDiposit.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].capacitat}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.capacitat"/>: <c:out value="${fulla[1].capacitat}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].establiment}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.establiment"/>: <c:out value="${fulla[1].establiment.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].zona}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.zona"/>: <c:out value="${fulla[1].zona.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla[1].partidaOli}">${fulla[1].partidaOli.nom}</c:if></c:set>
							<c:if test="${not empty partidaOli}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							</c:if>									
							<c:set var="categoria"> <c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>
							<c:if test="${not empty categoria}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla[0]}"/>_info_5"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							</c:if>									
						</c:if>
						<c:if test="${not empty fulla[1].volumActual}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.volumActual"/>: <c:out value="${fulla[1].volumActual}"/>",id:"<c:out value="${fulla[0]}"/>_info_6"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
					</c:when>
					
					<c:when test="${fulla[3]=='DepositoGenerico'}">	
					<%-- Node de Deposito --%>
						
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${fulla[1].codiAssignat}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot";																			
						<%-- Dades ded diposit --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.info"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla[1].materialDiposit}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.materialDiposit"/>: <c:out value="${fulla[1].materialDiposit.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].capacitat}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.capacitat"/>: <c:out value="${fulla[1].capacitat}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].establiment}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.establiment"/>: <c:out value="${fulla[1].establiment.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].zona}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.deposito.zona"/>: <c:out value="${fulla[1].zona.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>						
											
					</c:when>						
					
					<c:when test="${fulla[3]=='Lote'}">	
					<%-- Node de Lote --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote"/> <c:out value="${fulla[1].codiAssignat}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_lot";																			
						<%-- Dades ded lote --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.info"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						<c:if test="${not empty fulla[1].zona}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.establiment"/>: <c:out value="${fulla[1].zona.establiment.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.zona"/>: <c:out value="${fulla[1].zona.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].data}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.data"/>: <fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>

						<c:if test="${not empty fulla[1].partidaOli}">
							<c:set var="partidaOli"> <c:if test="${not empty fulla[1].partidaOli}">${fulla[1].partidaOli.nom}</c:if></c:set>
							<c:if test="${not empty partidaOli}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entrada.partidaOli"/>: <c:out value="${partidaOli}"/>",id:"<c:out value="${fulla[0]}"/>_info_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							</c:if>									
							<c:set var="categoria"> <c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 1}"><fmt:message key="consulta.general.camp.categoriaOli.1"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 2}"><fmt:message key="consulta.general.camp.categoriaOli.2"/></c:if><c:if test="${not empty fulla[1].partidaOli.categoriaOli && fulla[1].partidaOli.categoriaOli.id == 3}"><fmt:message key="consulta.general.camp.categoriaOli.3"/></c:if></c:set>
							<c:if test="${not empty categoria}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.categoria"/>: <c:out value="${categoria}"/>",id:"<c:out value="${fulla[0]}"/>_info_4"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							</c:if>									
						</c:if>

						<c:if test="${not empty fulla[1].acidesa}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_5 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.acidesa"/>: <c:out value="${fulla[1].acidesa}"/>",id:"<c:out value="${fulla[0]}"/>_info_5"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].etiquetatge}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_6 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.marca"/>: <c:out value="${fulla[1].etiquetatge.marca.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_6"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							var tmpNode_<c:out value="${fulla[0]}"/>_info_7 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.tipoEnvase"/>: <c:out value="${fulla[1].etiquetatge.tipusEnvas.nomSelect}"/>",id:"<c:out value="${fulla[0]}"/>_info_7"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
						</c:if>
						<c:if test="${not empty fulla[1].litresPerduts}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_8 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.litresPerduts"/>: <c:out value="${fulla[1].litresPerduts}"/>",id:"<c:out value="${fulla[0]}"/>_info_8"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
							<c:if test="${not empty fulla[1].motiuPerdua}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_9 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.motiuPerdua"/>: <c:out value="${fulla[1].motiuPerdua}"/>",id:"<c:out value="${fulla[0]}"/>_info_9"}, tmpNode_<c:out value="${fulla[0]}"/>_info_8, false);
							</c:if>
						</c:if>
						<c:if test="${not empty fulla[1].litresEnvassats}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_10 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.litresEnvassats"/>: <c:out value="${fulla[1].litresEnvassats}"/>",id:"<c:out value="${fulla[0]}"/>_info_10"}, tmpNode_<c:out value="${fulla[0]}"/>, false);							
							
							<c:if test="${not empty fulla[1].numeroBotellesActuals}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_11 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroBotellesActuals"/>: <c:out value="${fulla[1].numeroBotellesActuals}"/>",id:"<c:out value="${fulla[0]}"/>_info_11"}, tmpNode_<c:out value="${fulla[0]}"/>_info_10, false);							
							</c:if>
							<c:if test="${not empty fulla[1].numeroBotellesInicials}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_12 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroBotellesInicials"/>: <c:out value="${fulla[1].numeroBotellesInicials}"/>",id:"<c:out value="${fulla[0]}"/>_info_12"}, tmpNode_<c:out value="${fulla[0]}"/>_info_10, false);							
							</c:if>
							<c:if test="${not empty fulla[1].numeroLot}">
								var tmpNode_<c:out value="${fulla[0]}"/>_info_13 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.lote.numeroLot"/>: <c:out value="${fulla[1].numeroLot}"/>",id:"<c:out value="${fulla[0]}"/>_info_13"}, tmpNode_<c:out value="${fulla[0]}"/>_info_10, false);							
							</c:if>
						</c:if>
					</c:when>
					
					<c:when test="${fulla[3]=='EntradaLot'}"> 
						<%-- Node de EntradaLot --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entradaLot"/>(<fmt:formatDate value="${fulla[1].data}" type="date" dateStyle="short" timeStyle="short"/>)",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<c:if test="${not empty fulla[1].zona}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entradaLot.zona"/>: <c:out value="${fulla[1].zona.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].dipositProcedencia}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.entradaLot.procedencia"/>: <c:out value="${fulla[1].dipositProcedencia}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>																								
					</c:when>
					
					<c:when test="${fulla[3]=='SortidaLot'}">
						<c:set var="data"> <c:if test="${not empty fulla[1].vendaData}"><fmt:formatDate value="${fulla[1].vendaData}" type="date" dateStyle="short" timeStyle="short"/></c:if><c:if test="${not empty fulla[1].canviZonaData}"><fmt:formatDate value="${fulla[1].canviZonaData}" type="date" dateStyle="short" timeStyle="short"/></c:if></c:set>									
						<c:set var="texto"> <fmt:message key="consulta.trazabilitat.sortidaLot"/> <c:if test="${not empty fulla[1].vendaData}"> - <fmt:message key="consulta.trazabilitat.sortidaLot.venta"/></c:if><c:if test="${not empty fulla[1].canviZonaData}">- <fmt:message key="consulta.trazabilitat.sortidaLot.cambiZona"/></c:if> (<c:out value="${data}"/>)</c:set>									
						<%-- Node de SortidaLot --%>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${texto}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						<c:if test="${not empty fulla[1].zona}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.sortidaLot.zona"/>: <c:out value="${fulla[1].zona.nom}"/>",id:"<c:out value="${fulla[0]}"/>_info_0"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].vendaNumeroBotelles}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.sortidaLot.numBotellas"/>: <c:out value="${fulla[1].vendaNumeroBotelles}"/>",id:"<c:out value="${fulla[0]}"/>_info_1"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>
						<c:if test="${not empty fulla[1].vendaDestinatari}">
							var tmpNode_<c:out value="${fulla[0]}"/>_info_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.sortidaLot.vendaDestinatari"/>: <c:out value="${fulla[1].vendaDestinatari}"/>",id:"<c:out value="${fulla[0]}"/>_info_2"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						</c:if>																							
					</c:when>		
					
					<c:when test="${fulla[1].class.name == 'es.caib.gesvit.model.Proces'}">
						<%-- Node de tipus procés --%>
						<c:choose>
								<c:when test="${fulla[1].tipus == 1}"><c:set var="proces"><fmt:message key="proces.entradaraim.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 2}"><c:set var="proces"><fmt:message key="proces.mescla.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 3}"><c:set var="proces"><fmt:message key="proces.premsadaraim.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 4}"><c:set var="proces"><fmt:message key="proces.compravi.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 5}"><c:set var="proces"><fmt:message key="proces.compraprod.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 6}"><c:set var="proces"><fmt:message key="proces.mostvi.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 7}"><c:set var="proces"><fmt:message key="proces.embotellat.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 8}"><c:set var="proces"><fmt:message key="proces.trasbals.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 9}"><c:set var="proces"><fmt:message key="proces.practicaenologica.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 10}"><c:set var="proces"><fmt:message key="proces.qualificar.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 11}"><c:set var="proces"><fmt:message key="proces.perdues.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 12}"><c:set var="proces"><fmt:message key="proces.ventavi.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 13}"><c:set var="proces"><fmt:message key="proces.desfangat.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 14}"><c:set var="proces"><fmt:message key="proces.elaboracio.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 15}"><c:set var="proces"><fmt:message key="proces.tancament.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 16}"><c:set var="proces"><fmt:message key="proces.etiquetat.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 17}"><c:set var="proces"><fmt:message key="proces.sangrat.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 18}"><c:set var="proces"><fmt:message key="proces.crearBancada.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 19}"><c:set var="proces"><fmt:message key="proces.posarenbota.titol"/></c:set></c:when>
								<c:when test="${fulla[1].tipus == 20}"><c:set var="proces"><fmt:message key="proces.descubat.titol"/></c:set></c:when>
						</c:choose>
						<c:set var="text"><c:out value="${proces}"/></c:set>
						var tmpNode_<c:out value="${fulla[0]}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${text}"/>",id:"<c:out value="${fulla[0]}"/>"}, <c:out value="${parent}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_proces";
						<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>.labelStyle = "arbre_proces mangarrufes ygtvlabel";</c:if>
						<%-- Dades del proces --%>
						var tmpNode_<c:out value="${fulla[0]}"/>_info = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.infoproc"/>",id:"<c:out value="${fulla[0]}"/>_info"}, tmpNode_<c:out value="${fulla[0]}"/>, false);
						tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info";
						<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_info.labelStyle = "arbre_info mangarrufes ygtvlabel";</c:if>
						<c:if test="${not empty fulla[1].dataCreacio}">
							var tmpNode_<c:out value="${fulla[0]}"/>_0 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.proces.datacrea"/>: <fmt:formatDate value="${fulla[1].dataCreacio}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_0"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_0.labelStyle = "mangarrufes ygtvlabel";</c:if>
						</c:if>
						<c:if test="${not empty fulla[1].dataExecucio}">
							var tmpNode_<c:out value="${fulla[0]}"/>_1 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.proces.dataexec"/>: <fmt:formatDate value="${fulla[1].dataExecucio}" type="date" dateStyle="short" timeStyle="short"/>",id:"<c:out value="${fulla[0]}"/>_1"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_1.labelStyle = "mangarrufes ygtvlabel";</c:if>
						</c:if>
						<c:if test="${not empty fulla[1].zonaViticola}">
							var tmpNode_<c:out value="${fulla[0]}"/>_2 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.proces.zonavit"/>: <c:out value="${fulla[1].zonaViticola}"/>",id:"<c:out value="${fulla[0]}"/>_2"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_2.labelStyle = "mangarrufes ygtvlabel";</c:if>
						</c:if>
						<c:if test="${not empty fulla[1].lotEmbotellat}">
							var tmpNode_<c:out value="${fulla[0]}"/>_4 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.proces.lotEmbotellat"/>: <c:out value="${fulla[1].lotEmbotellat}"/>",id:"<c:out value="${fulla[0]}"/>_4"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_4.labelStyle = "mangarrufes ygtvlabel";</c:if>
						</c:if>
	<c:set var="observ" value="${fulla[1].observacions}"/>
	<%
	        String observ = (String)pageContext.getAttribute("observ");
			if (observ != null)
	        	pageContext.setAttribute("observ", observ.replaceAll("\\r\\n|\\n", " "));
	%>
						<c:if test="${not empty fulla[1].observacions}">
							var tmpNode_<c:out value="${fulla[0]}"/>_3 = new YAHOO.widget.TextNode({label:"<fmt:message key="consulta.trazabilitat.proces.observacions"/>: <c:out value="${observ}"/>",id:"<c:out value="${fulla[0]}"/>_3"}, tmpNode_<c:out value="${fulla[0]}"/>_info, false);
							<c:if test="${fulla[4] == false}">tmpNode_<c:out value="${fulla[0]}"/>_3.labelStyle = "mangarrufes ygtvlabel";</c:if>
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
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>
</div>

</body>
</html>
