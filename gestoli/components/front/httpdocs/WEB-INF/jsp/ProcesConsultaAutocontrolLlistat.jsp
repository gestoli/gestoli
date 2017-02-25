<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="autocontrol.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
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
</c:if> <display:table name="llistat" requestURI="" id="autocontrol" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
	<display:column titleKey="analitica.camp.data" sortable="true" sortProperty="data" headerClass="ancho75">
		<a href="AutocontrolForm.html?id=<c:out value="${autocontrol.id}"/>&amp;consulta=t"><c:out value="${autocontrol.dataFormat}" /></a>
	</display:column>
	<c:set var="tipus"><c:out value="${autocontrol.lot.tipusOlivaTaula}" /></c:set>
	<c:set var="tipus2"><c:out value="${autocontrol.bota.tipusOlivaTaula}" /></c:set>
	<display:column titleKey="establiment.vista.info.tipusOlivaTaula" sortable="true">
		<fmt:message key="autocontrol.tipus<c:if test="${not empty tipus}">${tipus}</c:if><c:if test="${not empty tipus2}">${tipus2}</c:if>"/>
	</display:column>
	<display:column titleKey="analitica.camp.valid" headerClass="ancho160 final" sortable="true">
		<c:choose>
			<c:when test="${autocontrol.apta}"><fmt:message key="manteniment.si" /></c:when>
			<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
		</c:choose>
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="autocontrols.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table>
</div>
<div class="separadorH"></div>


<form id="tornarForm" action="ProcesInici.html" class="seguit">
	<input type="hidden" id="tipusProces" name="tipusProces" value="<c:out value="${param.tipusProces}"/>" /> 
	<input type="hidden" id="pas" name="pas" value="<c:out value="${param.pas - 1}"/>" /> 
	<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${param.zonaId}"/>" /> 
	<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${param.establimentId}"/>" /> 
	<c:if test="${not empty param.fromEstabliment}">
		<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="<c:out value="${param.fromEstabliment}"/>" />
	</c:if>
</form>
<div class="btnCorto" onclick="submitForm('tornarForm')" onmouseover="underline('enlaceTornarForm')" onmouseout="underline('enlaceTornarForm')">
	<a id="enlaceTornarForm" href="javascript:void(0);"><fmt:message key="proces.tornar" /></a>
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
