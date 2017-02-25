<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.plaManteniment.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listado"><%-- Tabla de resultados --%>

	<c:set var="control" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="control" value="hidden" />
	</c:if>
	
	<div class="layoutSombraTabla ancho">
		<c:if
			test="${not empty llistat}">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
		</c:if> 
		<display:table name="llistat" requestURI="" id="qualitatPlaManteniment" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="equip.nom" titleKey="qualitat.plaManteniment.camp.equip" headerClass="ancho100" sortable="true" url="/QualitatPlaMantenimentForm.html" paramId="id" paramProperty="id"/>
			<display:column property="accions" titleKey="qualitat.plaManteniment.camp.accions" sortable="false"/>
			<display:column property="frecuencia" titleKey="qualitat.plaManteniment.camp.frecuencia" sortable="true"/>
			<display:column titleKey="qualitat.plaManteniment.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaManteniment.responsableIntern.nom} ${qualitatPlaManteniment.responsableIntern.llinatges} ${qualitatPlaManteniment.responsableExtern}"/>
			</display:column>
			<display:column headerClass="ancho32 ${control} final" class="${control}" sortable="false" titleKey="qualitat.plaManteniment.camp.control" url="/QualitatPlaMantenimentControl.html" paramId="idManteniment" paramProperty="id">
				<form id="controlForm_${qualitatPlaManteniment.id}" action="QualitatPlaMantenimentControl.html" method="post" class="mini">
					<input id="idManteniment" name="idManteniment" value="<c:out value="${qualitatPlaManteniment.id}"/>" type="hidden" /> 
					<input id="action" name="action" value="control" type="hidden" />
					<div id="controlForm" class="iconoSituar" title="<fmt:message key="qualitat.plaManteniment.camp.control"/>">
					</div>
				</form>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPlaManteniment.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatPlaMantenimentForm.html"></form>
	
	<c:if test="${not empty esEstAdministrador}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
	

	<div class="btnCorto" onmouseover="underline('generarPdf')"
		onmouseout="underline('generarPdf')"
		onclick="document.formularioPDF.submit();">
		<a id="generarPdf"	href="javascript:void(0);"><fmt:message key="manteniment.pdf" /></a>
	</div>
	<form name="formularioPDF" action="GenerarPdf.html" method="post">
		<input id="tipus" name="tipus" value="19" type="hidden" />
	</form>
	
	
			
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