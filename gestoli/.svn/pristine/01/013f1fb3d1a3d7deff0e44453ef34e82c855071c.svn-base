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
		<display:table name="llistat" requestURI="" id="qualitatPlaControlTransport" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="vehicle.nom" titleKey="qualitat.plaControlTransport.camp.vehicle" headerClass="ancho100" sortable="true" url="/QualitatPlaControlTransportForm.html" paramId="id" paramProperty="id"/>
			<display:column property="vehicle.numSerie" titleKey="qualitat.plaControlTransport.camp.matricula" sortable="false"/>
			<display:column property="dataVerificacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.plaControlTransport.camp.dataVerificacio" sortable="true"/>
			<display:column titleKey="qualitat.plaControlTransport.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaControlTransport.responsableVerificacio.nom} ${qualitatPlaControlTransport.responsableVerificacio.llinatges} "/>
			</display:column>
			<display:column property="estibaCorrectaString" titleKey="qualitat.plaControlTransport.camp.estibaCorrecta" sortable="true"/>
			<display:column property="netejaCorrectaString" titleKey="qualitat.plaControlTransport.camp.netejaCorrecta" sortable="true"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPlaControlTransport.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatPlaControlTransportForm.html"></form>

	<c:if test="${not empty esEstAdministrador}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
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