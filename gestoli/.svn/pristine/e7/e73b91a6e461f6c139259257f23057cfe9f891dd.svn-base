<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="marca.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listadoEstrecho"><%-- Tabla de resultados --%>
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <display:table name="llistat" requestURI="" id="marca" export="true" sort="list" cellpadding="0" cellspacing="0" class="listado665 selectable">
	<display:column titleKey="marca.camp.nom" sortable="true"
		sortProperty="nom">
		<a href="MarcaForm.html?id=<c:out value="${marca.id}"/>"><c:out
			value="${marca.nom}" /></a>
	</display:column>
	<display:column titleKey="marca.camp.denominacion_origen" headerClass="ancho100" sortable="true">
		<c:choose>
			<c:when test="${marca.denominacioOrigen}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="marca.camp.actiu" headerClass="ancho75"
		sortable="true">
		<c:choose>
			<c:when test="${marca.actiu}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="menu.acciones.oliva_taula" headerClass="ancho75"
		sortable="true">
		<c:choose>
			<c:when test="${marca.olivaTaula}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="marca.camp.data.alta" headerClass="ancho110">
		<fmt:formatDate value="${marca.dataAlta}" pattern="dd/MM/yyyy"/>
	</display:column>
	<display:column titleKey="marca.camp.data.baixa" headerClass="ancho110 final">
		<fmt:formatDate value="${marca.dataBaixa}" pattern="dd/MM/yyyy"/>
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="marques.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

<div class="separadorH"></div>

<form name="formulario" action="MarcaForm.html"></form>
<c:if test="${not empty esDoGestor}">
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


















