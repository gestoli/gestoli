<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="usuari.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="usuari" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
			<display:column titleKey="usuari.camp.usuari" headerClass="ancho340" sortable="true" sortProperty="usuari">
				<a href="UsuariForm.html?id=<c:out value="${usuari.id}"/>">
					<c:out value="${usuari.usuari}" />
				</a>
			</display:column>
			<display:column titleKey="usuari.camp.email" headerClass="ancho175" sortable="true">
				<c:out value="${usuari.email}" />
			</display:column>
			<display:column titleKey="usuari.camp.grups" headerClass="final"  sortable="true">
				<c:forEach var="grup" items="${usuari.sortedGrups}" varStatus="status">
					<c:if test="${status.index == 0}">
						<c:out value="${grup.nom}" />
					</c:if>
					<c:if test="${status.index != 0}">, <c:out value="${grup.nom}" /></c:if>
				</c:forEach>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="usuaris.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>

	<div class="separadorH"></div>

	<form name="formulario" action="UsuariForm.html"></form>
	<c:if test="${not empty esDoGestor || not empty esEstAdministrador}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm" href="javascript:void(0);">
				<fmt:message key="manteniment.crearnou" />
			</a>
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

















