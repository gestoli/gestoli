<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="sortida.orujo.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
	</head>

	<body>
		<div id="listadoEstrecho"><!-- Tabla de resultados -->
			<div class="layoutSombraTabla ancho">
				<c:if test="${not empty llistat}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if>
				
				<display:table name="llistat" requestURI="" id="registre" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
					<display:column titleKey="sortida.orujo.camp.data" sortable="true" sortProperty="data">
						<fmt:formatDate value="${registre.data}" pattern="dd/MM/yyyy"/>
					</display:column>
					<display:column titleKey="sortida.orujo.camp.kilos" sortable="true">
						<c:out value="${registre.kilos}" />
					</display:column>
					<display:column titleKey="sortida.orujo.camp.albara" sortable="true">
						<c:out value="${registre.albara}" />
					</display:column>
					<display:column titleKey="sortida.orujo.camp.desti" sortable="true">
						<c:out value="${registre.desti}" />
					</display:column>
					<display:column titleKey="sortida.orujo.camp.document" sortable="true" headerClass="final">
						<c:out value="${registre.document}" />
					</display:column>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="SortidesOrujo.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
			<div class="separadorH"></div>

			<form name="formulario" action="SortidaOrujoForm.html"></form>

			<c:if test="${not empty esProductor}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
						onmouseout="underline('enlaceCrearForm')"
						onclick="document.location='SortidaOrujoForm.html';">
					<a id="enlaceCrearForm" href="javascript:void(0);">
						<fmt:message key="manteniment.crearnova" />
					</a>
				</div>
			</c:if>
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
