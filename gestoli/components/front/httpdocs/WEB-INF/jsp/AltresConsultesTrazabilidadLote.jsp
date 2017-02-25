<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.logic.model.DescomposicioPartidaOliva"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>
<title><fmt:message key="consulta.traza.lote" /></title>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty lotes}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<div class="trazabilidad"><c:if test="${empty lotes}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if> <c:if test="${lotes != null && not empty lotes}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="lotes" requestURI="" id="lote" export="true"
		sort="list" cellpadding="0" cellspacing="0"
		class="listadoMedio selectable">
		<display:column titleKey="consulta.traza.data" sortable="true"
			sortProperty="data" headerClass="ancho75">
			<a
				href="AltresConsultesTrazabilidadAnaliticas.html?id=<c:out value="${lote.id}"/><c:if test="${not empty param.id}">&idElaboracio=<c:out value="${param.id}"/></c:if>"><c:out
				value="${lote.dataFormat}" /></a>
		</display:column>
		<display:column titleKey="consulta.traza.codiAssignat" sortable="true"
			sortProperty="codiAssignat">
			<c:out value="${lote.codiAssignat}" />
		</display:column>
		<display:column titleKey="consulta.traza.numeroBotellesActuals"
			sortable="true" sortProperty="numeroBotellesActuals"
			headerClass="ancho120">
			<c:out value="${lote.numeroBotellesActuals}" />
		</display:column>
		<display:column titleKey="consulta.traza.litros" sortable="true"
			sortProperty="litresEnvassats" headerClass="ancho75">
			<fmt:formatNumber value="${lote.litresEnvassats}"
				maxFractionDigits="2" />
		</display:column>
		<display:column titleKey="consulta.traza.acidesa" sortable="true"
			sortProperty="acidesa" headerClass="ancho75 final">
			<fmt:formatNumber value="${lote.acidesa}" maxFractionDigits="2" />
		</display:column>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="llistat.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
				})
			</script>
</c:if>

<div class="btnCorto" onclick="submitForm('tornarForm')"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>
<form id="tornarForm" action="AltresConsultesTrazabilidad.html"
	class="seguit"><input type="hidden" name="idProductor"
	value="<c:out value="${param.idProductor}"/>" /></form>
</div>

</body>
</html>
