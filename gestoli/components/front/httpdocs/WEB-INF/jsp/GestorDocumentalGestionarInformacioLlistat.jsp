<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="informacio.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listadoAncho"><%-- Tabla de resultados --%>
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <display:table name="llistat" requestURI="" id="informacio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
	<display:column titleKey="informacio.camp.data" sortable="true"
		headerClass="ancho100" sortProperty="data">
		<a href="GestorDocumentalGestionarInformacioForm.html?id=<c:out value="${informacio.id}"/>"><fmt:formatDate
			value="${informacio.data}" type="date" dateStyle="short"
			timeStyle="short" /></a>
	</display:column>
	<display:column titleKey="informacio.camp.titol" sortable="true">
		<c:out value="${informacio.titol}" escapeXml="false" />
	</display:column>
	<display:column titleKey="informacio.camp.categoria" sortable="true"
		headerClass="ancho210">
		<c:out value="${informacio.gestorDocumentalCategoriaInformacio.nom}" escapeXml="false" />
	</display:column>
	<display:column titleKey="informacio.camp.documents" sortable="true"
		headerClass="ancho100 final">
		<c:choose>
			<c:when test="${informacio.teDocuments}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="informacio.camp.estat" sortable="true" headerClass="ancho210">
		<fmt:message key="qualitat.informacio.estat.${informacio.estat}" />
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="Informacions.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

<div class="separadorH"></div>

<form name="formulario" action="GestorDocumentalGestionarInformacioForm.html"></form>
<c:if test="${not empty esEstAdministrador}">
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();"><a id="enlaceCrearForm"
		href="javascript:void(0);"><fmt:message
		key="manteniment.crearnova" /></a></div>
</c:if></div>

<c:if test="${not empty llistat}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>


</body>
</html>
