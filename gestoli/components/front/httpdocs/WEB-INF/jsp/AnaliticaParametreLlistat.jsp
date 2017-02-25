<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<c:choose>
	<c:when test="${not empty vista || not empty analitica}">
		<title><fmt:message key="establiment.llistitol" /></title>
	</c:when>
	<c:otherwise>
		<title><fmt:message key="establiment.llistitol" /></title>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="js/displaytag.js"></script>

</head>
<body>

<div id="listado"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if>
	<display:table name="llistat" requestURI="" id="analitica" export="true" sort="list" cellpadding="0" cellspacing="0" class="listado665 selectable">
		<display:column titleKey="analiticaParametre.camp.condicio" sortable="true" headerClass="ancho160" href="AnaliticaParametreForm.html" paramId="id" paramProperty="id">
			<c:if test="${not empty lang && lang == 'ca'}">
				<c:out value="${analitica.analiticaParametreTipus.nom}" />&nbsp;<fmt:message key="analiticaParametre.operador${analitica.operador}" />&nbsp;<c:out value="${analitica.valor}" />
			</c:if>
			<c:if test="${not empty lang && lang == 'es'}">
				<c:out value="${analitica.analiticaParametreTipus.nomCast}" />&nbsp;<fmt:message key="analiticaParametre.operador${analitica.operador}" />&nbsp;<c:out value="${analitica.valor}" />
			</c:if>
		</display:column>
		<display:column titleKey="analiticaParametre.camp.obligatori" sortable="true" headerClass="ancho160">
			<c:choose>
				<c:when test="${analitica.obligatori}"><fmt:message key="manteniment.si" /></c:when>
				<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="analiticaParametre.camp.varietat" sortable="true" headerClass="ancho160 final">
			<c:out value="${analitica.varietatOli.nom}" />
		</display:column>
		<display:column sortable="false" headerClass="ancho32 final" href="">
			<form id="deleteForm_${analitica.id}" action="AnaliticaParametre.html" method="post" class="mini" onsubmit="return confirm('<fmt:message key="analiticaParametre.borrar.segur"/>')">
				<input id="id" name="id" value="<c:out value="${analitica.id}"/>" type="hidden" /> 
				<input id="action" name="action" value="delete"	type="hidden" />
				<div id="eliminarForm" class="iconoBorrar"
					onclick="submitFormConfirm('deleteForm_${analitica.id}','<fmt:message key="manteniment.esborrar.confirm"/>');">
				</div>
			</form>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="ParametresAnalitiques.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table>
</div>

<div class="separadorH"></div>

<form name="formulario" action="AnaliticaParametreForm.html"></form>

	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')" onmouseout="underline('enlaceCrearForm')" onclick="document.formulario.submit();">
		<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>

	<div class="btnCorto" onmouseover="underline('enlaceTipusParametres')" onmouseout="underline('enlaceTipusParametres')" style="margin: 15px 0 0 0;">
		<a id="enlaceTipusParametres" href="AnaliticaParametreTipus.html"><fmt:message key="manteniment.tipus" /></a>
	</div>

</div>

<c:if test="${not empty llistat}">
	<!-- Colores en tablas -->
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>




</body>
</html>


















