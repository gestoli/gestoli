<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="tipusEnvas.llistitol" /></title>
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
</c:if> <display:table name="llistat" requestURI="" id="tipusEnvas" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
	<display:column titleKey="tipusEnvas.camp.volum" sortable="true"
		sortProperty="volum">
		<a href="TipusEnvasForm.html?id=<c:out value="${tipusEnvas.id}"/>"><c:out
			value="${tipusEnvas.volum}" /></a>
	</display:column>
	<display:column titleKey="tipusEnvas.camp.materialTipusEnvas"
		headerClass="ancho100" sortable="true">
		<c:out value="${tipusEnvas.materialTipusEnvas.nom}" />
	</display:column>
	<display:column titleKey="tipusEnvas.camp.color" headerClass="ancho100"
		sortable="true">
		<c:out value="${tipusEnvas.color.nom}" />
	</display:column>
	<display:column titleKey="tipusEnvas.camp.actiu"
		headerClass="ancho100 final" sortable="true">
		<c:choose>
			<c:when test="${tipusEnvas.actiu}">
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
	<display:setProperty name="export.excel.filename" value="Tipusenvas.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

<div class="separadorH"></div>

<form name="formulario" action="TipusEnvasForm.html"></form>
<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
	onmouseout="underline('enlaceCrearForm')"
	onclick="document.formulario.submit();"><a id="enlaceCrearForm"
	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
</div>


</div>

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










