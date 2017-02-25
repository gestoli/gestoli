<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>
<title><fmt:message key="analiticaParametreTipus.llistitol" /></title>

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
	<display:table name="llistat" requestURI="" id="analitica" export="true" sort="list" cellpadding="0" cellspacing="0" class="listado665 selectable" defaultsort="1">
		<display:column titleKey="analiticaParametreTipus.camp.tipusControl" sortable="true" headerClass="ancho160" group="1" href="AnaliticaParametreForm.html" paramId="id" paramProperty="id">
			<fmt:message key="analiticaParametreTipus.tipusControl${analitica.tipusControl}" />
		</display:column>
		<display:column titleKey="analiticaParametreTipus.camp.ordre" sortable="true" headerClass="ancho160">
			<c:out value="${analitica.ordre}" />
		</display:column>
		<display:column titleKey="analiticaParametreTipus.camp.nomCatala" sortable="true" headerClass="ancho160">
			<c:out value="${analitica.nom}" />
		</display:column>
		<display:column titleKey="analiticaParametreTipus.camp.nomCastella" sortable="true" headerClass="ancho160">
			<c:out value="${analitica.nomCast}" />
		</display:column>
		<display:column titleKey="analiticaParametreTipus.camp.tipus" sortable="true" headerClass="ancho160">
			<fmt:message key="analiticaParametreTipus.tipus${analitica.tipus}" />
		</display:column>
		<display:column titleKey="analiticaParametreTipus.camp.actiu" sortable="true" headerClass="ancho160">
			<c:choose>
				<c:when test="${analitica.actiu}"><fmt:message key="manteniment.si" /></c:when>
				<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
			</c:choose>
		</display:column>
		<display:column sortable="false" headerClass="ancho32 final" href="">
			<form id="deleteForm_${analitica.id}" action="AnaliticaParametreTipus.html" method="post" class="mini" onsubmit="return confirm('<fmt:message key="analiticaParametreTipus.borrar.segur"/>')">
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
		<display:setProperty name="export.excel.filename" value="TipusParametresAnalitiques.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table>
</div>

<div class="separadorH"></div>

<form name="formulario" action="AnaliticaParametreTipusForm.html"></form>

	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')" onmouseout="underline('enlaceCrearForm')" onclick="document.formulario.submit();">
		<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>

	<div class="btnCorto" onmouseover="underline('enlaceTipusParametres')" onmouseout="underline('enlaceTipusParametres')" style="margin: 15px 0 0 0;">
		<a id="enlaceTipusParametres" href="AnaliticaParametre.html"><fmt:message key="manteniment.tornar" /></a>
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


















