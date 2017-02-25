<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.controlPlagues.verificacio.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatControlPlaguesVerificacio" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="objectiu" titleKey="qualitat.controlPlagues.verificacio.camp.objectiu" headerClass="ancho100" sortable="true" url="/QualitatControlPlaguesVerificacioForm.html?idPlaga=${idPlaga}" paramId="id" paramProperty="id"/>
			<display:column property="dataVerificacio" titleKey="qualitat.controlPlagues.verificacio.camp.dataVerificacio" sortable="true"/>
			<display:column titleKey="qualitat.controlPlagues.verificacio.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatControlPlaguesVerificacio.responsableVerificacio.nom} ${qualitatControlPlaguesVerificacio.responsableVerificacio.llinatges}"/>
			</display:column>
			<display:column property="satisfactori" titleKey="qualitat.controlPlagues.verificacio.camp.satisfactori" sortable="true"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatControlPlaguesVerificacio.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatControlPlaguesVerificacioForm.html">
		<input id="idPlaga" name="idPlaga" value="<c:out value="${idPlaga}"/>" type="hidden" />
	</form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatControlPlagues.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>
				
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