<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="producte.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

	<div id="listado"><%-- Tabla de resultados --%>
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
			<display:table 	name="llistat" requestURI="" id="producte" sort="list" export="true" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
				<display:column titleKey="partidaOli.camp.nom" headerClass="ancho160" sortable="true" sortProperty="nom">
					<a href="ProducteForm.html?id=<c:out value="${producte.id}"/>"><c:out value="${producte.nom}"/></a>
				</display:column>
				<display:column titleKey="partidaOli.camp.actiu" sortable="true" headerClass="ancho75 final">
					<c:choose>
						<c:when test="${producte.actiu}">
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
		   		<display:setProperty name="export.excel.filename" value="Productes.xls" />
		   		<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>

		<div class="separadorH"></div>

		<form name="formulario" action="ProducteForm.html"></form>

		<c:if test="${not empty esProductor || not empty esEnvasador}">
			<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
				onmouseout="underline('enlaceCrearForm')"
				onclick="document.formulario.submit();">
				<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
			</div>
		</c:if>
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

















