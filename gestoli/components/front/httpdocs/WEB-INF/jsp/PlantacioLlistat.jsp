<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="plantacio.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
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
				
				<display:table name="llistat" requestURI="" id="plantacio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
					<display:column titleKey="plantacio.camp.municipi" sortable="true" sortProperty="municipi.nom">
						<a href="PlantacioForm.html?id=<c:out value="${plantacio.id}"/>&idOlivicultor=<c:out value="${plantacio.finca.olivicultor.id}"/>">
							<c:out value="${plantacio.municipi.nom}" />
						</a>
					</display:column>
					<display:column titleKey="plantacio.camp.poligon" headerClass="ancho100" sortable="true">
						<c:out value="${plantacio.poligon}" />
					</display:column>
					<display:column titleKey="plantacio.camp.parcela" headerClass="ancho100" sortable="true">
						<c:out value="${plantacio.parcela}" />
					</display:column>
					<display:column titleKey="plantacio.camp.activa" headerClass="ancho75 final" sortable="true">
						<c:choose>
							<c:when test="${plantacio.actiu}"><fmt:message key="manteniment.si" /></c:when>
							<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
						</c:choose>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="plantacions.xls" />
			<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
			<div class="separadorH"></div>

			<form name="formulario" action="PlantacioForm.html">

			<div class="botonesForm">
			<c:if test="${not empty esDoGestor}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
						onmouseout="underline('enlaceCrearForm')"
						onclick="document.location='PlantacioForm.html?idOlivicultor=<c:out value="${idOlivicultor}"/>&amp;idFinca=<c:out value="${param.idFinca}"/>&amp;crearPlantacio=t';">
					<a id="enlaceCrearForm" href="javascript:void(0);">
						<fmt:message key="manteniment.crearnova" />
					</a>
				</div>
				<div id="cancelarForm" class="btnCorto"
						onmouseover="underline('enlaceCancelarForm')"
						onmouseout="underline('enlaceCancelarForm')"
						onclick="document.location ='FincasForm.html?id=<c:out value="${param.idFinca}"/>&idOlivicultor=${idOlivicultor}';">
					<a id="enlaceCancelarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.tornar" />
					</a>
				</div>
			</c:if>
			<c:if test="${not empty esDoControlador}">
				<div id="cancelarForm" class="btnCorto"
						onmouseover="underline('enlaceCancelarForm')"
						onmouseout="underline('enlaceCancelarForm')"
						onclick="document.location ='FincasForm.html?id=<c:out value="${param.idFinca}"/>&idOlivicultor=${idOlivicultor}';">
					<a id="enlaceCancelarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.tornar" />
					</a>
				</div>
			</c:if>
			</div>
			
			</form>
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
