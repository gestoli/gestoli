<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>
<title><fmt:message key="consulta.traza" /></title>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>

<form id="formulario"
	action="<%=request.getContextPath()%>/AltresConsultesTrazabilidad.html"
	method="post" class="extended seguit"><%--  SELECTOR POR USUARIOS
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select"/>
			<c:param name="path" value="formData.idProductor"/>
			<c:param name="title"><fmt:message key="consulta.traza.productor"/></c:param>
			<c:param name="camp" value="idProductor"/>
			<c:param name="required" value="required"/>
			<c:param name="selectItems" value="productores"/>
			<c:param name="selectItemsId" value="id"/>
			<c:param name="selectItemsValue" value="usuari"/>
			<c:param name="selectSelectedValue"value="${formData.idProductor}"/>
			<c:param name="clase" value="campoFormTresCuartos"/>
		</c:import>
		 --%> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idEstabliment" />
	<c:param name="title">
		<fmt:message key="consulta.partidaOliva.camp.establiment" />
	</c:param>
	<c:param name="camp" value="idEstabliment" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="establecimientos" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.idEstabliment}" />
	<c:param name="clase" value="campoFormTresCuartos" />
</c:import>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="aceptarForm" class="btnCorto"
	onclick="submitForm('formulario');"
	onmouseover="underline('enlaceAceptarForm')"
	onmouseout="underline('enlaceAceptarForm')"><a
	id="enlaceAceptarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.aceptar" /></a></div>
</div>

<c:if test="${empty llistat}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if></form>

<div class="separadorH"></div>

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="llistat" requestURI="" id="elaboracion" export="true"
		 sort="list" cellpadding="0" cellspacing="0"
		class="listadoAncho selectable">
		<display:column titleKey="consulta.traza.data" sortable="true"
			sortProperty="data" headerClass="ancho75">
			<a
				href="AltresConsultesTrazabilidadLotes.html?id=<c:out value="${elaboracion.id}"/><c:if test="${not empty param.idProductor }">&idProductor=<c:out value="${param.idProductor}"/></c:if>"><c:out
				value="${elaboracion.dataFormat}" /></a>
		</display:column>
		<display:column titleKey="consulta.traza.numElaboracio"
			sortable="true" sortProperty="numeroElaboracio"
			headerClass="ancho120">
			<c:out value="${elaboracion.numeroElaboracio}" />
		</display:column>
		<display:column titleKey="consulta.traza.establiment" sortable="true"
			sortProperty="establimentName">
			<c:out value="${elaboracion.establimentName}" />
		</display:column>
		<display:column titleKey="consulta.traza.litros" sortable="true"
			sortProperty="litres" headerClass="ancho50">
			<fmt:formatNumber value="${elaboracion.litres}" maxFractionDigits="2" />
		</display:column>
		<c:choose>
			<c:when
				test="${fn:contains(elaboracion.nomVarietat, 'mezcla') == true}">
				<display:column titleKey="consulta.traza.variedad" sortable="true"
					sortProperty="nomVarietat" headerClass="final ancho120">
					<fmt:message key="consulta.traza.mezcla" />
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column titleKey="consulta.traza.variedad" sortable="true"
					sortProperty="nomVarietat" headerClass="final ancho120">100% <c:out
						value="${elaboracion.nomVarietat}" />
				</display:column>
			</c:otherwise>
		</c:choose>
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


</body>
</html>
