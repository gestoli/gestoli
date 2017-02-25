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
<title><fmt:message key="consulta.traza.analitica" /></title>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty analiticas}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<div class="trazabilidad"><c:if test="${empty analiticas}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if> <c:if test="${analiticas != null && not empty analiticas}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="analiticas" requestURI="" id="analitica" export="true"
		sort="list" cellpadding="0" cellspacing="0"
		class="listadoMedio selectable">
		<display:column titleKey="analitica.camp.data" sortable="true"
			sortProperty="data" headerClass="ancho75">
			<a
				href="AnaliticaForm.html?id=<c:out value="${analitica.id}"/>&amp;consulta=t&amp;traza=t"><c:out
				value="${analitica.dataFormat}" /></a>
		</display:column>
		<display:column titleKey="analitica.camp.varietatOli" sortable="true">
			<c:out value="${analitica.varietatOli.nom}" />
		</display:column>
		<display:column titleKey="analitica.camp.valid"
			headerClass="ancho160 final" sortable="true">
			<c:choose>
				<c:when test="${analitica.valid}">
					<fmt:message key="manteniment.si" />
				</c:when>
				<c:otherwise>
					<fmt:message key="manteniment.no" />
				</c:otherwise>
			</c:choose>
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
<form id="tornarForm" action="AltresConsultesTrazabilidadLotes.html"
	class="seguit"><input type="hidden" name="id"
	value="<c:out value="${param.idElaboracio}"/>" /></form>
</div>

</body>
</html>
